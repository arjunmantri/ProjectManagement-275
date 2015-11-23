package edu.sjsu.cmpe275.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;




import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import edu.sjsu.cmpe275.dao.impl.EmailDAOImpl;
import edu.sjsu.cmpe275.dao.interfaces.IEmailDao;
import edu.sjsu.cmpe275.dto.User;

@Service
@ComponentScan(basePackages = {"edu.sjsu.cmpe275.*"})
@Transactional
public class EmailServiceImpl {
	
	EmailServiceImpl() {
		
	}

	@Autowired
	public JavaMailSender javaMailSender;

	@Autowired 
	public EmailDAOImpl emailDaoImpl;
	
	@Autowired 
	public User user;
	
	
	public void sendUserSignUpEmail (String toEmail, String password, String userName, String body) {
		
		user.setEmail(toEmail);
		user.setPassword(password);
		user.setUserName(userName);
		user.setHashCode(toEmail.hashCode());
		emailDaoImpl.createUserSignUp(user);
		
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper;
		try {
				messageHelper = new MimeMessageHelper(message, true);
				messageHelper.setTo(toEmail);
				messageHelper.setSubject("Welcome to project management tool.");
				messageHelper.setText(body, true);
				javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}