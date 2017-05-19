package com.wt.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class Mail {
//	private final String MAIL_SMTP_HOST = "mail.smtp.host";
	private final String MAIL_SMTP_HOST = "mail.host";
	private final String MAIL_SMTP_AUTH = "mail.smtp.auth";

	/**
	 * JavaMail发送邮件:普通文本邮件，可多个地址，无抄送和密送
	 * 
	 * @param mailType
	 *            发送短信邮箱的类型:126.com/sina.com.cn/163.com
	 * @param fromMailAddress
	 *            发送的邮箱地址:wt12312345@126.com
	 * @param fromMailPassword
	 *            发送的邮箱的密码:fdaasdasd
	 * @param toMailAddressesTo
	 *            发送到的邮箱地址
	 * @param title
	 *            发送的标题
	 * @param text
	 *            发送的内容
	 */
	public void Send(String mailType, String fromMailnickName,
			final String fromMailAddress, final String fromMailPassword,
			List<String> toMailAddressesTo, String title, String text) {
		try {
			Message msg = new MimeMessage(getSession(mailType, fromMailAddress,
					fromMailPassword));
			msg.setFrom(new InternetAddress(fromMailAddress, fromMailnickName));
			msg.setRecipients(Message.RecipientType.TO,
					getAddresses(toMailAddressesTo));
			msg.setSubject(title);
			msg.setContent(text, "text/html;charset=utf8");
			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("邮件发送成功");
		} catch (MessagingException m) {
			System.out.println("邮件发送失败:" + m.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * JavaMail发送邮件:普通文本邮件，可多个地址，无抄送和密送
	 * 
	 * @param mailType
	 *            发送短信邮箱的类型:126.com/sina.com.cn/163.com
	 * @param fromMailAddress
	 *            发送的邮箱地址:wt12312345@126.com
	 * @param fromMailPassword
	 *            发送的邮箱的密码:fdaasdasd
	 * @param toMailAddressesTO
	 *            发送到的邮箱地址
	 * @param toMailAddressesCC
	 *            抄送到的邮箱地址
	 * @param title
	 *            发送的标题
	 * @param text
	 *            发送的内容
	 */
	public boolean Send(String mailType, String fromMailnickName,
			final String fromMailAddress, final String fromMailPassword,
			List<String> toMailAddressesTO, List<String> toMailAddressesCC,
			String title, String text) {
		try {
			Session session = getSession(mailType, fromMailAddress,
					fromMailPassword);
			Message msg = new MimeMessage(session);
			System.out.println("获取连接成功");
			fromMailnickName = javax.mail.internet.MimeUtility
					.encodeText(fromMailnickName);
			// msg.setFrom(new InternetAddress(fromMailnickName + " <"
			// + fromMailAddress + ">"));
					
			msg.setFrom(new InternetAddress(fromMailAddress, fromMailnickName));
			msg.setRecipients(Message.RecipientType.TO,
					getAddresses(toMailAddressesTO));
			msg.setRecipients(Message.RecipientType.CC,
					getAddresses(toMailAddressesCC));
			msg.setSubject(title);
			msg.setContent(text, "text/html;charset=utf8");
			msg.setSentDate(new Date());
			//Transport.send(msg);
			
			Transport ts = session.getTransport();  
	        ts.connect("haiqia","haiqia");//登录本地发送邮件客户端  
	        ts.sendMessage(msg, msg.getAllRecipients());
			
			
			System.out.println("邮件发送成功");
			return true;
		} catch (MessagingException m) {
			System.out.println("邮件发送失败:" + m.getMessage());
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * JavaMail发送邮件:附件邮件，可多个地址，有正文内容，无抄送和密送
	 * 
	 * @param mailType
	 *            发送短信邮箱的类型:126.com/sina.com.cn/163.com
	 * @param fromMailAddress
	 *            发送的邮箱地址:wt12312345@126.com
	 * @param fromMailPassword
	 *            发送的邮箱的密码:fdaasdasd
	 * @param toMailAddressesTO
	 *            发送到的邮箱地址
	 * @param title
	 *            发送的标题
	 * @param text
	 *            发送的内容
	 * @param filePath
	 *            附件路径
	 * @param sendFileName
	 *            发送附件的名称，带后缀
	 */
	public void Send(String mailType, final String fromMailAddress,
			final String fromMailPassword, List<String> toMailAddressesTO,
			String title, String text, String filePath, String sendFileName) {
		try {
			Message msg = new MimeMessage(getSession(mailType, fromMailAddress,
					fromMailPassword));
			msg.setFrom(new InternetAddress(fromMailAddress));

			msg.setRecipients(Message.RecipientType.TO,
					getAddresses(toMailAddressesTO));
			msg.setSubject(title);
			msg.setSentDate(new Date());
			// 设置邮件内容和附件内容
			Multipart mp = new MimeMultipart();
			// 添加内容
			MimeBodyPart bodypart = new MimeBodyPart();
			bodypart.setContent(text, "text/html;charset=utf8");
			mp.addBodyPart(bodypart);
			// 添加附件
			BodyPart affixBody = new MimeBodyPart();
			DataSource datasource = new FileDataSource(filePath);
			affixBody.setDataHandler(new DataHandler(datasource));
			affixBody.setFileName(MimeUtility.encodeText(sendFileName,
					"GB2312", "B"));
			mp.addBodyPart(affixBody);
			msg.setContent(mp);
			Transport.send(msg);
			System.out.println("邮件发送成功");
		} catch (MessagingException m) {
			System.out.println("邮件发送失败:" + m.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * JavaMail发送邮件:附件邮件，可多个地址，有正文内容，无抄送和密送
	 * 
	 * @param mailType
	 *            发送短信邮箱的类型:126.com/sina.com.cn/163.com
	 * @param fromMailAddress
	 *            发送的邮箱地址:wt12312345@126.com
	 * @param fromMailPassword
	 *            发送的邮箱的密码:fdaasdasd
	 * @param toMailAddressesTO
	 *            发送到的邮箱地址
	 * @param toMailAddressesCC
	 *            抄送到的邮箱地址
	 * @param title
	 *            发送的标题
	 * @param text
	 *            发送的内容
	 * @param filePath
	 *            附件路径
	 * @param sendFileName
	 *            发送附件的名称，带后缀
	 */
	public void Send(String mailType, final String fromMailAddress,
			final String fromMailPassword, List<String> toMailAddressesTO,
			List<String> toMailAddressesCC, String title, String text,
			String filePath, String sendFileName) {
		try {
			Message msg = new MimeMessage(getSession(mailType, fromMailAddress,
					fromMailPassword));
			msg.setFrom(new InternetAddress(fromMailAddress));
			msg.setRecipients(Message.RecipientType.TO,
					getAddresses(toMailAddressesTO));
			msg.setRecipients(Message.RecipientType.CC,
					getAddresses(toMailAddressesCC));
			msg.setSubject(title);
			msg.setSentDate(new Date());
			// 设置邮件内容和附件内容
			Multipart mp = new MimeMultipart();
			// 添加内容
			MimeBodyPart bodypart = new MimeBodyPart();
			bodypart.setContent(text, "text/html;charset=utf8");
			mp.addBodyPart(bodypart);
			// 添加附件
			BodyPart affixBody = new MimeBodyPart();
			DataSource datasource = new FileDataSource(filePath);
			affixBody.setDataHandler(new DataHandler(datasource));
			affixBody.setFileName(MimeUtility.encodeText(sendFileName,
					"GB2312", "B"));
			mp.addBodyPart(affixBody);
			msg.setContent(mp);
			Transport.send(msg);
			System.out.println("邮件发送成功");
		} catch (MessagingException m) {
			System.out.println("邮件发送失败:" + m.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			try {
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private Session getSession(String mailType, final String fromMailAddress,
			final String fromMailPassword) {
		Properties props = new Properties();
		props.put(MAIL_SMTP_HOST, mailType);
		//props.put(MAIL_SMTP_AUTH, true);
		props.put("mail.transport.protocol", "smtp");

		// props.put("mail.smtp.host", "smtp.qq.com");
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.transport.protocol", "smtp");
		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromMailAddress,
						fromMailPassword);
			}
		};
		Session session = Session.getDefaultInstance(props, auth);
//		session.setDebug(true);
		return session;
	}

	private InternetAddress[] getAddresses(List<String> toMailAddresses)
			throws AddressException {
		InternetAddress[] addresses = new InternetAddress[toMailAddresses
				.size()];
		for (int i = 0; i < toMailAddresses.size(); i++) {
			addresses[i] = new InternetAddress(toMailAddresses.get(i)
					.toString());
		}
		return addresses;
	}
}
