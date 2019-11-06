package com.yiran.weixin.service.impl;
/*package com.yiran.project.weixin.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiran.project.weixin.kit.WeixinBasicKit;

@Service
@Transactional(readOnly = true)
public class WeixinQrService  implements IWeixinQrService {
	private static final Logger logger = LoggerFactory.getLogger(WeixinQrService.class);
	@Autowired
	private AgentWeixinqrDao weixinQrDao;
	@Autowired
	private IWqrService wqrService;
	public static final String FILEPATHTWODIMENSIONCODE_WEIXIN = "uploadFiles"+File.separator+"weixinQrImg"+File.separator; //微信二维码存放路径
	@Override
	@Transactional(readOnly = false)
	public WeixinQr insert(WeixinQr wq) {
		wq.setId(UUID.randomUUID().toString());
		User user=UserUtils.getUser();
		wq.setUserId(user.getId());
		if("".equals(wq.getQrData()) || wq.getQrData()==null){
			wq.setQrData(user.getUcenterUserId());
		}
		
		//设置场景值
		Integer maxSnum=weixinQrDao.getMaxSnum();
		if(maxSnum==null){
			maxSnum=0;
		}else{
			maxSnum=maxSnum+1;
		}
		wq.setSnum(maxSnum);
		wq.setStatus(1);
		if(wq.getSnum()==null) throw new RuntimeException("场景值不能为空");
		if(wq.getSnum()<=WeixinQr.MAX_BASE_SNUM) {
			//永久二维码
			WeixinQr twq = this.loadBySnum(wq.getSnum());
			if(twq!=null) throw new RuntimeException("固定二维码的场景值已经存在!");
			wq.setCreateDate(new Date());
			setQrTicket(wq,0);
			String urlString="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+wq.getTicket();
			try {
				FileDownload.downloadImage(urlString,wq.getTicket()+".png",PathUtil.getClasspath()+ FILEPATHTWODIMENSIONCODE_WEIXIN);
				//获取随机码
				String random=RandomUtil.generateString(3);
				logger.info("获取随机码用户拼接图片名称："+random);
				String yuantulujin = PathUtil.getClasspath() + FILEPATHTWODIMENSIONCODE_WEIXIN+wq.getTicket()+".png";
				logger.info("压缩图片的原始路径："+yuantulujin);
				//将图片压缩成118*122
				File img = new File(yuantulujin);
		        //压缩后图片的名称
				String imgName=wq.getTicket()+random+".png";
				logger.info("压缩后图片的名称："+imgName);
				String yasuohoutupian = PathUtil.getClasspath() + FILEPATHTWODIMENSIONCODE_WEIXIN+imgName;
				logger.info("压缩后图片的路径："+yasuohoutupian);
				FileOutputStream fos = new FileOutputStream(yasuohoutupian);
				 logger.info(">>>>>>>>>>>>>>开始压缩图片<<<<<<<<<<<<<<<<<<<");
		        ImgTools.thumbnail_w_h(img, 160, 160, fos);
		        logger.info(">>>>>>>>>>>>>>图片压缩完毕<<<<<<<<<<<<<<<<<<<");
				//将图片合并到指定的位置
		        Pic tt = (Pic) BeanFactoryContext.getService("pic");  
		        logger.info("Pic对象："+tt);
		        String hebingyuanshitupian = PathUtil.getClasspath() + FILEPATHTWODIMENSIONCODE_WEIXIN+"zhongwanqipai.jpg";
		        logger.info("合并原始图片："+hebingyuanshitupian);
		        BufferedImage d = tt.loadImageLocal(hebingyuanshitupian); 
		        String xuyaohebingtupian = PathUtil.getClasspath() + FILEPATHTWODIMENSIONCODE_WEIXIN+imgName;
		        logger.info("需要合并图片："+xuyaohebingtupian);
		        BufferedImage b = tt.loadImageLocal(xuyaohebingtupian);    
		        String heBingTuPian = "zhongwanqipai"+random+".jpg";
		        logger.info("合并后后图片的名称："+heBingTuPian);
		        wq.setQrimgname(heBingTuPian);
		        //往图片上写文件    
		        String hebinghouxintupian = PathUtil.getClasspath() + FILEPATHTWODIMENSIONCODE_WEIXIN+heBingTuPian;
		        logger.info("合并后的图片："+hebinghouxintupian);
		        logger.info(">>>>>>>>>>>>>>开始图片合并<<<<<<<<<<<<<<<<<<<");
		        tt.writeImageLocal(hebinghouxintupian, tt.modifyImagetogeter(b, d,145,502));    
		        logger.info(">>>>>>>>>>>>>>图片合并完毕<<<<<<<<<<<<<<<<<<<");
			} catch (Exception e) {
				e.getMessage();
			}
			weixinQrDao.insert(wq);
		} else {
			//临时二维码
			return addTempQr(wq);
		}
		return wq;
	}

	@Transactional(readOnly = false)
	private WeixinQr addTempQr(WeixinQr wq) {
		WeixinQr twq = this.loadBySnum(wq.getSnum());
		if(twq==null) {
			wq.setCreateDate(new Date());
			setQrTicket(wq,1);
			weixinQrDao.insert(wq);
			return wq;
		} else {
			if(checkExpired(twq)) {
				//先删除twq,之后添加wq
				twq.setId(UUID.randomUUID().toString());
				twq.setCreateDate(new Date());
				twq.setMsg(wq.getMsg());
				twq.setName(wq.getName());
				twq.setQrData(wq.getQrData());
				twq.setSnum(wq.getSnum());
				twq.setStatus(wq.getStatus());
				twq.setType(wq.getType());
				setQrTicket(twq,1);
				weixinQrDao.update(twq);
				return twq;
			} else {
				wq.setSnum((WeixinQr.MAX_BASE_SNUM+1)+RandomUtils.nextInt(0, 1000000));
				return addTempQr(wq);
			}
			
		}
	}

	private void setQrTicket(WeixinQr wq,int type) {
		String ticket;
		if(type==0) {
			ticket = wqrService.loadTicketByBaseQr(wq.getSnum());
		} else {
			ticket = wqrService.loadTicketByTempQr(wq.getSnum());
		}
		if(ticket==null||"".equals(ticket)) throw new RuntimeException("从微信获取二维码失败!");
		wq.setTicket(ticket);		
	}

	private boolean checkExpired(WeixinQr twq) {
		long t = System.currentTimeMillis()-twq.getCreateDate().getTime();
		if((t/1000)>60) return true;
		return false;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(String id) {
		weixinQrDao.delete(id);
	}

	@Override
	public WeixinQr load(String id) {
		return weixinQrDao.load(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(WeixinQr wq) {
		weixinQrDao.update(wq);
	}

	@Override
	public List<WeixinQr> listBaseQr(WeixinQr wq) {
		wq.setSnum(WeixinQr.MAX_BASE_SNUM);
		return weixinQrDao.listBaseQr(wq);
	}

	public Page<WeixinQr> findlistBaseQrPage(Page<WeixinQr> page, WeixinQr wq) {
		wq.setSnum(WeixinQr.MAX_BASE_SNUM);
		return super.findlistBaseQrPage(page, wq);
	}
	@Override
	public List<WeixinQr> listTempQr() {
		return weixinQrDao.listTempQr(WeixinQr.MAX_BASE_SNUM);
	}

	@Override
	public WeixinQr loadBySnum(int snum) {
		return weixinQrDao.loadBySnum(snum);
	}

	@Override
	public Integer getWeixinQrByuserId(String userId) {
		return weixinQrDao.getWeixinQrByuserId(userId);
	}

	@Override
	public Integer getBandAgentByUid(String uid) {
		return weixinQrDao.getBandAgentByUid(uid);
	}
	

}
*/