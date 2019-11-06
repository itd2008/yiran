package com.yiran.message.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 消息通知日志表 notify_msg_log
 * 
 * @author yiran
 * @date 2019-03-08
 */
public class NotifyMsgLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 信息发送流水号 */
	private String msgOrderNo;
	/** 备注信息(信息返回BizId) */
	private String remarks;
	/** 业务类型 */
	private String businessType;
	/** 联系电话 */
	private String phone;
	/** 模板ID */
	private String templateId;
	/** 模板类型 */
	private String templateContent;
	/** 发送数据 */
	private Date senddata;
	/** 发送状态 */
	private String status;
	/** 发送响应消息ID */
	private String smsid;
	/** 返回码 */
	private String code;
	/** 返回消息 */
	private String msg;
	/** 删除标记（0：正常；1：删除） */
	private String delFlag;
	/** 响应时间 */
	private Date responseDate;
	/** 短信验证码 */
	private String verifyCode;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setMsgOrderNo(String msgOrderNo) 
	{
		this.msgOrderNo = msgOrderNo;
	}

	public String getMsgOrderNo() 
	{
		return msgOrderNo;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}
	public void setBusinessType(String businessType) 
	{
		this.businessType = businessType;
	}

	public String getBusinessType() 
	{
		return businessType;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setTemplateId(String templateId) 
	{
		this.templateId = templateId;
	}

	public String getTemplateId() 
	{
		return templateId;
	}
	public void setTemplateContent(String templateContent) 
	{
		this.templateContent = templateContent;
	}

	public String getTemplateContent() 
	{
		return templateContent;
	}
	public void setSenddata(Date senddata) 
	{
		this.senddata = senddata;
	}

	public Date getSenddata() 
	{
		return senddata;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setSmsid(String smsid) 
	{
		this.smsid = smsid;
	}

	public String getSmsid() 
	{
		return smsid;
	}
	public void setCode(String code) 
	{
		this.code = code;
	}

	public String getCode() 
	{
		return code;
	}
	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public String getMsg() 
	{
		return msg;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}
	public void setResponseDate(Date responseDate) 
	{
		this.responseDate = responseDate;
	}

	public Date getResponseDate() 
	{
		return responseDate;
	}
	public void setVerifyCode(String verifyCode) 
	{
		this.verifyCode = verifyCode;
	}

	public String getVerifyCode() 
	{
		return verifyCode;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("msgOrderNo", getMsgOrderNo())
            .append("remarks", getRemarks())
            .append("businessType", getBusinessType())
            .append("phone", getPhone())
            .append("templateId", getTemplateId())
            .append("templateContent", getTemplateContent())
            .append("senddata", getSenddata())
            .append("status", getStatus())
            .append("smsid", getSmsid())
            .append("code", getCode())
            .append("msg", getMsg())
            .append("delFlag", getDelFlag())
            .append("responseDate", getResponseDate())
            .append("verifyCode", getVerifyCode())
            .toString();
    }
}
