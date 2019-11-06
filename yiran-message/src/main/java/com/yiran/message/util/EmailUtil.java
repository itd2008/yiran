package com.yiran.message.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiran.message.config.EmailConfig;

/**
 * email 工具类.
 */
public class EmailUtil {
	private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
    /**
     * 发送邮件
     * @param emailMessage Email对象
     * @throws Exception Email异常
     */
    public static boolean send(EmailConfig emailConfig, EmailMessage emailMessage) throws Exception {
    	
    	//是否发送成功
    	boolean isSendSuccess = false;
        // 检查输入参数
        checkArgumentValid(emailMessage);
       
        if (emailMessage.getType().equals(EmailType.PLAIN)) {
            // 简单邮件
            if (emailMessage.getAttachements() != null
                    && emailMessage.getAttachements().size() > 0) {
                // 有附件，构造MultiPartEmail
                MultiPartEmail email = new MultiPartEmail();
                setAttachment(email,emailConfig, emailMessage);
                setEmail(email, emailConfig, emailMessage);
                email.send();
                isSendSuccess = true;
            } else {
                // 没有附件，构造SimpleEmail
                SimpleEmail email = new SimpleEmail();
                setEmail(email, emailConfig, emailMessage);
                email.send();
                isSendSuccess = true;
            }
        } else if (emailMessage.getType().equals(EmailType.HTML)) {
            // HTML邮件
            HtmlEmail email = new HtmlEmail();
            email.setCharset("UTF-8");
            setAttachment(email, emailConfig, emailMessage);
            setEmail(email, emailConfig, emailMessage);
            email.setHtmlMsg(emailMessage.getHtmlMsg());
            email.send();
            isSendSuccess = true;
        }
        return isSendSuccess;
    }

    /**
     * 检查输入是否合法.
     * @param emailMessage
     */
    private static void checkArgumentValid(EmailMessage emailMessage){
        if (emailMessage == null) {
            throw new IllegalArgumentException("输入参数为空");
        }
        // 至少要有一个收件人
        if  ((emailMessage.getTo() == null || emailMessage.getTo().size() ==0)
                && ( emailMessage.getCc() == null || emailMessage.getCc().size() ==0)
                && ( emailMessage.getBcc() == null || emailMessage.getBcc().size() ==0)) {
            throw new IllegalArgumentException("收件人不能为空");
        }
    }
    
    /**
     * 加载邮件附件信息.
     * @param email 邮件对象
     * @param emailMessage 邮件信息对象
     * @throws EmailSendException 邮件发送异常
     */
    private static void setAttachment(MultiPartEmail email, EmailConfig emailConfig, EmailMessage emailMessage) throws Exception {
        for (Iterator<EmailMessageAttachment> iter = emailMessage.getAttachements().iterator(); iter.hasNext();) {
            EmailMessageAttachment messageAttachement = (EmailMessageAttachment) iter.next();
            EmailAttachment attachment = new EmailAttachment();
            try {
                // 如果附件没有名称,取文件名.
                String name = messageAttachement.getName();
                if (name == null) {
                    name = StringUtils.substringAfterLast(messageAttachement.getPath(), File.separator);
                }
                attachment.setName(MimeUtility.encodeText(name));
                if (messageAttachement.getDescription() != null) {
                    attachment.setDescription(MimeUtility.encodeText(messageAttachement.getDescription()));
                } else {
                    attachment.setDescription("");
                }
                attachment.setPath(messageAttachement.getPath());
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                email.attach(attachment);
            } catch (Exception e) {
                throw new Exception("构造Email附件出现异常");
            }
        }
    }
    
    /**
     * 设置邮件基本信息.
     * @param email 邮件对象
     * @param emailMessage 邮件信息对象
     * @throws EmailSendException 邮件发送异常
     */
    private static void setEmail(Email email,EmailConfig emailConfig, EmailMessage emailMessage) throws Exception {
        try {
            // 设置SMTP服务器名称
            email.setHostName(emailConfig.smtp);
            // 设置SMTP端口
            email.setSmtpPort(emailConfig.smtpPort);
            // 设置是否使用SSL
            email.setSSL(emailConfig.ssl);
            // 设置SSL端口
            if (email.isSSL()) {
                email.setSslSmtpPort(emailConfig.sslPort);
            }
            // 设置认证信息
            email.setAuthentication(emailConfig.loginName, emailConfig.password);            
            
            String charset = emailMessage.getCharset();
            //如果用户设置了自己的from，则用自己设置的from地址
            if(!StringUtils.isBlank(emailMessage.getFrom())){
                email.setFrom(emailMessage.getFrom());
            }else{
                // 设置缺省的from
                email.setFrom(emailConfig.sysFrom,emailConfig.alias,charset);
            }
            for (Iterator iter = emailMessage.getTo().iterator(); iter.hasNext();) {
                email.addTo((String) iter.next());
            }
            for (Iterator iter = emailMessage.getCc().iterator(); iter.hasNext();) {
                email.addCc((String) iter.next());
            }
            for (Iterator iter = emailMessage.getBcc().iterator(); iter.hasNext();) {
                email.addBcc((String) iter.next());
            }
            email.setCharset(charset);
            if (emailMessage.getSentDate() != null) {
                email.setSentDate(emailMessage.getSentDate());
            }
            email.setSubject(emailMessage.getSubject());
            if (emailMessage.getType().equals(EmailType.PLAIN)) {
                email.setMsg(emailMessage.getMsg());
            }
        } catch (EmailException e) {
            throw new Exception("构造Email出现异常");
        }
    }
    
    public static boolean sendHtmlEmail(String subject,String [] toUserEmails, String emailContent, EmailConfig emailConfig){
    	EmailMessage emailMessage = new EmailMessage();
		emailMessage.setType(EmailType.HTML);
		emailMessage.setSubject(subject);
		List<String> to = new ArrayList<String>();
		for(String toUserEmail : toUserEmails){
			to.add(toUserEmail);
		}
		emailMessage.setTo(to);
		emailMessage.setHtmlMsg(EmailTemplates.getTemplate(emailMessage.getSubject(),emailContent));
		try {
			boolean flag = EmailUtil.send(emailConfig, emailMessage);
			logger.info("邮件发送状态:{}",flag);
		} catch (Exception e) {
			logger.info("邮件发送异常{}",e);
			return false;
		}
		return true;
    }
    
}
