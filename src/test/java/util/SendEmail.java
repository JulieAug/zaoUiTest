package util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by Anthony on 2017/06/08
 */

public class SendEmail {
	private static String classPath = "classpath:applicationContext-mail.xml";

	public static ApplicationContext ac;

	public static void email(String moduleName,String filePath)
			throws UnsupportedEncodingException, MessagingException {
		ac = new ClassPathXmlApplicationContext(classPath);
		JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) ac.getBean("javaMailSenderImpl");
		MailBean mailBean = new MailBean();
		mailBean.setFrom(javaMailSenderImpl.getUsername());
		mailBean.setFromName(javaMailSenderImpl.getUsername());
		Properties properties = javaMailSenderImpl.getJavaMailProperties();
		String sendtoString = (String) properties.get("mail.sendto");
		String[] sendto = sendtoString.split(",");
		mailBean.setToEmails(sendto);
		mailBean.setSubject(moduleName);
		MailUtil mailUtil = new MailUtil();
		String context = mailUtil.readFile(filePath);
		mailBean.setContext(context);
		MailSenderService mailSenderService = new MailSenderService();
		mailSenderService.setJavaMailSenderImpl(javaMailSenderImpl);
		mailSenderService.sendMail(mailBean);

	}

}
