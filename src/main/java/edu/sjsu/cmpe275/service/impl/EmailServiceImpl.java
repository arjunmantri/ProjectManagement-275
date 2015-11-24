package edu.sjsu.cmpe275.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail (String to, String subject, String body) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
				messageHelper = new MimeMessageHelper(message, true);
				messageHelper.setTo(to);
				messageHelper.setSubject(subject);
				messageHelper.setText(body, true);
				javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}