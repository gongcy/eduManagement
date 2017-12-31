package com.niit216.basic.tools;

import com.niit216.basic.auth.tools.DesUtil;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 邮件管理的工具类
 * @author 钟述林 20150702
 *
 */
public class MailTools {
	
	public static final String KEY = "MAIL_KEY";

	public static String PROTOCOL = getProperties("protocol"); //邮件协议
	
	public static String HOST = getProperties("host"); //发送的协议地址
	
	public static String PORT = getProperties("port"); //发送的协议端口号
	
	/** 获取配置对应值 */
	public static String getProperties(String name) {
		try {
			return PropertiesUtil.getMailProperties().getProperty(name);
		} catch (Exception e) {
			return "";
		}
	}
	
	/** 获取发送者的邮箱地址 */
	public static String getMailAddress() {
		return getProperties("mailAddress");
	}
	
	/** 获取接收方显示的名称 */
	public static String getShowName() {
		return getProperties("showName");
	}
	
	/** 获取发送者对应的邮箱密码 */
	public static String getMailPassword() {
		String res = getProperties("mailPassword");
		res = DesUtil.unPassword(KEY, res);
		return res;
	}
	
	/**
	 * 发送邮件
	 * @param title 邮件标题
	 * @param receiver 接口者邮箱地址
	 * @param annexPath 附件文件路径
	 * @param content 邮件内容
	 * @param conPath 邮件内容中的图片地址
	 */
	public static void sendMail(String title, String receiver, String content, String annexPath, String conPath) {  
		try {
			Properties properties = new Properties();
			properties.setProperty("mail.smtp.auth","true");//设置验证机制
			properties.setProperty("mail.transport.protocol", PROTOCOL);//发送邮件协议
			properties.setProperty("mail.smtp.host", HOST);//设置邮箱服务器地址
			properties.setProperty("mail.smtp.port", PORT);
			Session session = Session.getInstance(properties, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(getMailAddress(), getMailPassword());
				}
			});
			session.setDebug(false);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(getMailAddress(), getShowName(), "UTF-8"));
			message.setSubject(title);
			message.setRecipients(RecipientType.TO,InternetAddress.parse(receiver));//接收人
//			message.setRecipients(RecipientType.CC,InternetAddress.parse("zsl131@hotmail.com"));//抄送人
//			message.setRecipients(RecipientType.BCC,InternetAddress.parse("1348800595@qq.com"));//密送人
			MimeBodyPart bodyPartAttch = createAttachMent(annexPath);//附件
			MimeBodyPart bodyPartContentAndPic = createContentAndPic(content, conPath);//文本内容
			MimeMultipart mimeMuti = new MimeMultipart("mixed");
			if(bodyPartAttch!=null) {
				mimeMuti.addBodyPart(bodyPartAttch);
			}
			mimeMuti.addBodyPart(bodyPartContentAndPic);
			message.setContent(mimeMuti);
			message.saveChanges();
			//message.setContent("Michael", "text/html;charset=gbk");
			
			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	        CommandMap.setDefaultCommandMap(mc);
			
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    } 
	
	/** 创建附件 */
	public static MimeBodyPart createAttachMent(String path) throws MessagingException{
		if(path!=null && !"".equals(path.trim())) {
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			FileDataSource dataSource = new FileDataSource( new File(path));
			mimeBodyPart.setDataHandler(new DataHandler(dataSource));
			mimeBodyPart.setFileName(dataSource.getName());
			return mimeBodyPart;
		}
		return null;
	}
	/** 创建文本和图片 */
	public static MimeBodyPart createContentAndPic(String content,String path) throws MessagingException{
		MimeMultipart mimeMutiPart = new MimeMultipart("related");
		if(path!=null && !"".equals(path.trim())) {
			try {
				//图片
				MimeBodyPart picBodyPart = new MimeBodyPart();
				FileDataSource fileDataSource = new FileDataSource( new File(path));
				picBodyPart.setDataHandler(new DataHandler(fileDataSource));
				picBodyPart.setFileName(fileDataSource.getName());
				mimeMutiPart.addBodyPart(picBodyPart);
			} catch (Exception e) { //如果创建图片时报错，则不用添加图片
			}
		}
		//文本
		MimeBodyPart contentBodyPart = new MimeBodyPart();
		contentBodyPart.setContent(content,"text/html;charset=UTF-8");
		mimeMutiPart.addBodyPart(contentBodyPart);
		//图片和文本结合
		MimeBodyPart allBodyPart = new MimeBodyPart();
		allBodyPart.setContent(mimeMutiPart);
		return allBodyPart;
	}
	
	public static void main(String[] args) {
		String str = DesUtil.password(KEY, "ynjkw2153555");
		System.out.println(str);
	}
}
