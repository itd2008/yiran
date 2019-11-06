package com.yiran.message.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.yiran.common.base.AjaxResult;
import com.yiran.message.domain.NotifyMsgLog;
import com.yiran.message.domain.SendAuthCodeRequest;
import com.yiran.message.domain.SendSMSResponse;
import com.yiran.message.domain.SysSmsConfig;
import com.yiran.message.domain.SysSmsTemplate;
import com.yiran.message.mapper.SysSmsTemplateMapper;
import com.yiran.message.service.INotifyMsgLogService;
import com.yiran.message.service.ISmsSendService;
import com.yiran.message.service.ISysSmsConfigService;
import com.yiran.message.util.SmsResultEnum;
import com.yiran.message.util.StringTemplateUtils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
@Service
public class SmsSendService implements ISmsSendService {
	private static final Logger logger = LoggerFactory.getLogger(SmsSendService.class);
	@Autowired
	private SysSmsTemplateMapper sysSmsTemplateMapper;
	@Autowired
	private INotifyMsgLogService notifyMsgLogService;
	@Autowired
	private ISysSmsConfigService sysSmsConfigService;
	
	@Override
	public AjaxResult sendAuthCode(SendAuthCodeRequest request) {
		logger.debug("发送短信验证码请求参数："+JSONUtil.toJsonStr(request));
		if(StringUtils.isBlank(request.getTemplateId())){
			return AjaxResult.error("模板ID不能为空");
		}else if(StringUtils.isBlank(request.getPhone())){
			return AjaxResult.error("手机号不能为空");
		}
		//根据短信模板ID获取模板信息
		SysSmsTemplate  smsTemplate = sysSmsTemplateMapper.selectSmsTemplateByTemplateId(request.getTemplateId());
		if(smsTemplate!=null){
			//获取模板内容
			String templateContent = smsTemplate.getTemplateContent();
			//6位随机验证码
			String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
			//准备参数
			Map<String, String> data =new HashMap<String, String>();
			data.put("code", verifyCode);
			//获取发送短信流水号
			String msgOrderNo = RandomUtil.randomString(32);
			//替换模板中的数据,获取最终发送的消息
			String sendData = StringTemplateUtils.render(templateContent, data);
			logger.debug("发送短信数据："+sendData);
			//记录发送信息到表
			saveMsgLog(smsTemplate,verifyCode,sendData,msgOrderNo,request.getPhone());
			//TODO：这里调用短息平台发送短信
			SendSMSResponse sendSMSResponse = sendSMS(verifyCode,request.getPhone(),request.getTemplateId());
			logger.debug("调用短息平台发送短信返回数据："+JSONUtil.toJsonStr(sendSMSResponse));
			//TODO:需要修改
			if("OK".equals(sendSMSResponse.getCode())){
				sendSMSResponse.setStatus("S");
				//更新发送记录表
				updateMsgLog(sendSMSResponse,msgOrderNo);
				return AjaxResult.success(SmsResultEnum.getByCode(sendSMSResponse.getCode()).getMessage())
						.put("msgOrderNo", msgOrderNo).put("code", "0000");
			}else{
				sendSMSResponse.setStatus("F");
				//更新发送记录表
				updateMsgLog(sendSMSResponse,msgOrderNo);
				return AjaxResult.error(SmsResultEnum.getByCode(sendSMSResponse.getCode()).getMessage())
						.put("code", "0003");
			}
			
		}
		return null;
	}

	private void updateMsgLog(SendSMSResponse sendSMSResponse, String msgOrderNo) {
		logger.debug("短信发送成功后更新记录数据，流水号："+msgOrderNo+"数据："+JSONUtil.toJsonStr(sendSMSResponse));
		NotifyMsgLog msgLog = new NotifyMsgLog();
		msgLog.setStatus(sendSMSResponse.getStatus());
		msgLog.setSmsid(sendSMSResponse.getSmsid());
		msgLog.setCode(sendSMSResponse.getCode());
		msgLog.setMsg(sendSMSResponse.getMsg());
		msgLog.setMsgOrderNo(msgOrderNo);
		msgLog.setRemarks(sendSMSResponse.getBizId());
		notifyMsgLogService.updateNotifyMsgLog(msgLog);
		logger.debug("短信发送记录数据更新完成......");
	}

	private void saveMsgLog(SysSmsTemplate smsTemplate, String verifyCode, String sendData,String msgOrderNo,String phone) {
		logger.debug("短信发送保存发送记录数据，流水号："+msgOrderNo+"发送数据："+sendData+"短信码："+verifyCode);
		NotifyMsgLog msgLog = new NotifyMsgLog();
		msgLog.setMsgOrderNo(msgOrderNo);
		msgLog.setBusinessType(smsTemplate.getBusinessType());
		msgLog.setPhone(phone);
		msgLog.setTemplateId(smsTemplate.getTemplateId());
		msgLog.setTemplateContent(sendData);
		msgLog.setSenddata(new Date());
		msgLog.setVerifyCode(verifyCode);
		notifyMsgLogService.insertNotifyMsgLog(msgLog);
		logger.debug("短信发送记录数据保存完成......");
	}

	/**
	 * 调用短信平台发送短信
	 * @param sendData
	 */
	private SendSMSResponse  sendSMS(String verifyCode,String phone,String templateId) {
		logger.debug("调用平台发短息，验证码：{}",verifyCode);
		SendSMSResponse sendSMSResponse = new SendSMSResponse();
		//判断当天该手机号发送短信次数是否超出限次
		SysSmsConfig smsConfig =sysSmsConfigService.selectSmsConfigBySmsKey(SysSmsConfig.KEY_SMS_LIMIT_TIMES);
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		String beginDate = simpleFormat.format(new Date())+" 00:00:00";
		logger.debug("调用平台发短息->查询手机号发送短信次数，开始时间：{}",beginDate);
		String endDate = simpleFormat.format(new Date())+" 23:59:59";
		logger.debug("调用平台发短息->查询手机号发送短信次数，结束时间：{}",endDate);
		//调用查询
		int smsCount = notifyMsgLogService.getSendSmSCount(beginDate, endDate, phone);
		logger.debug("手机号：{}，发送短信次数{}",phone,smsCount);
		//平台配置最大次数
		int maxCount = Integer.parseInt(smsConfig.getSmsValue());
		if(smsCount >= maxCount){
			sendSMSResponse.setCode("0003");
			sendSMSResponse.setMsg("短息发送失败，超出短信发送限额！");
			sendSMSResponse.setStatus("F");
		}else{
			 //调平台发短息
			 try {
				//产品名称:云通信短信API产品,开发者无需替换
				String product = "Dysmsapi";
				//产品域名,开发者无需替换
				String domain = "dysmsapi.aliyuncs.com";

				// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
				String accessKeyId = sysSmsConfigService.selectSmsConfigBySmsKey(SysSmsConfig.KEY_SMS_APPID).getSmsValue();
				String accessKeySecret = sysSmsConfigService.selectSmsConfigBySmsKey(SysSmsConfig.KEY_SMS_APPSECRET).getSmsValue();
				//可自助调整超时时间
				System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
				System.setProperty("sun.net.client.defaultReadTimeout", "10000");
				//初始化acsClient,暂不支持region化
				IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
				DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
				IAcsClient acsClient = new DefaultAcsClient(profile);
				
				//组装请求对象-具体描述见控制台-文档部分内容
		        SendSmsRequest request = new SendSmsRequest();
		        //必填:待发送手机号
		        request.setPhoneNumbers(phone);
		        //必填:短信签名-可在短信控制台中找到
		        request.setSignName(sysSmsConfigService.selectSmsConfigBySmsKey(SysSmsConfig.KEY_SMS_SHORT_SIGNATURE).getSmsValue());
		        //必填:短信模板-可在短信控制台中找到
		        request.setTemplateCode(templateId);
		        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		        request.setTemplateParam("{\"code\":\""+verifyCode+"\"}");
		        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		        //request.setSmsUpExtendCode("90997");
		        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		        request.setOutId("pandaxu");
		        //hint 此处可能会抛出异常，注意catch
		        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		        sendSMSResponse.setCode(sendSmsResponse.getCode());
				sendSMSResponse.setMsg(SmsResultEnum.getByCode(sendSMSResponse.getCode()).getMessage());
				sendSMSResponse.setBizId(sendSmsResponse.getBizId());
			} catch (ClientException e) {
				e.printStackTrace();
			}
		}
		return sendSMSResponse;
	}
	
	@Override
	public AjaxResult verifyMobileAuthCode(SendAuthCodeRequest request) {
		logger.debug("验证短信验证是否正确请求参数："+JSONUtil.toJsonStr(request));
		if(StringUtils.isBlank(request.getPhone())){
			return AjaxResult.error("手机号不能为空");
		}else if(StringUtils.isBlank(request.getVerifyCode())){
			return AjaxResult.error("短信验证码不能为空");
		}else if(StringUtils.isBlank(request.getMsgOrderNo())){
			return AjaxResult.error("短息流水号不能为空");
		}
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		//根据流水号获取消息对象
		NotifyMsgLog  msgLog = notifyMsgLogService.selectMsgLogByMsgOrderNo(request.getMsgOrderNo());
		logger.debug("验证短信验证是根据流水号获取消息对象："+JSONUtil.toJsonStr(msgLog));
		if(msgLog!=null){
			if(request.getPhone().equals(msgLog.getPhone()) && request.getVerifyCode().equals(msgLog.getVerifyCode())){
				//判断是否在有效期时间内
				SysSmsConfig smsConfig =sysSmsConfigService.selectSmsConfigBySmsKey(SysSmsConfig.KEY_SMS_EFFECTIVE_TIME);
				int minutes = 0;
				try {
					//短信发送时间
					String fromDate = simpleFormat.format(msgLog.getSenddata());
					//当前时间
					String toDate = simpleFormat.format(new Date());
					long from = simpleFormat.parse(fromDate).getTime();
					long to = simpleFormat.parse(toDate).getTime();
					minutes = (int) ((to - from)/(1000 * 60));
					logger.debug("验证时间-短信发送时间之差：分钟"+minutes);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//获取设置时间有效时间
				int effective_time = Integer.parseInt(smsConfig.getSmsValue());
				logger.debug("设置验证码过期时间"+effective_time);
				if(minutes > effective_time){
					return AjaxResult.error("验证码已过期").put("code", "0003");
				}
				return AjaxResult.success("验证通过").put("code", "0000");
			}else{//
				return AjaxResult.error("验证码不正确").put("code", "0003");
			}
		}
		return null;
	}

}
