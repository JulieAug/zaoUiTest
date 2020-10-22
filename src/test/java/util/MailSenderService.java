package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * 
 * @author anthony
 * @date Jun 21, 2017
 * @updateTime 9:37:26 AM
 */

@Service
public class MailSenderService {
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	public JavaMailSenderImpl getJavaMailSenderImpl() {
		return javaMailSenderImpl;
	}

	public void setJavaMailSenderImpl(JavaMailSenderImpl javaMailSenderImpl) {
		this.javaMailSenderImpl = javaMailSenderImpl;
	}

	/**
	 * 创建MimeMessage
	 * 
	 * @param mailBean
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public MimeMessage createMimeMessage(MailBean mailBean) throws MessagingException, UnsupportedEncodingException {
		MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		messageHelper.setFrom(mailBean.getFrom(), mailBean.getFromName());
		messageHelper.setSubject(mailBean.getSubject());
		messageHelper.setTo(mailBean.getToEmails());
		messageHelper.setText(mailBean.getContext(), true); // html: true

		String attachFilePath = mailBean.getAttachFilePath();
		if (attachFilePath != null & !"".equals(attachFilePath)) {
			FileSystemResource file = new FileSystemResource(new File(attachFilePath));
			messageHelper.addAttachment(mailBean.getAttachFileName(), file);
		}

		return mimeMessage;
	}

	public void sendMail(MailBean mailBean) throws UnsupportedEncodingException, MessagingException {
		MimeMessage msg = createMimeMessage(mailBean);
		javaMailSenderImpl.send(msg);
	}

}