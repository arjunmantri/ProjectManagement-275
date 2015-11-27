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
import edu.sjsu.cmpe275.service.interfaces.IEmailService;

@Service
@ComponentScan(basePackages = {"edu.sjsu.cmpe275.*"})
@Transactional
public class EmailServiceImpl {//implements IEmailService {
	
	EmailServiceImpl() {
		
	}

	@Autowired
	public JavaMailSender javaMailSender;

	@Autowired 
	public EmailDAOImpl emailDaoImpl;
	
	@Autowired 
	public User user;
	
	//@Override
	public void sendUserSignUpEmail (String toEmail, String userName, String password, String body) {
		user.setEmail(toEmail);
		user.setPassword(password);
		user.setUserName(userName);
		user.setHashCode(toEmail.hashCode());
		if(isUserExist(toEmail)) {
			emailDaoImpl.updateUser(user);
		} else {
			emailDaoImpl.createUserSignUp(user);
		}
		
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
	
	//@Override
	public boolean getUserDetails(String emaild, Integer hashCode) {
		User user = emailDaoImpl.getUserDetail(emaild);
		if(user!= null && (user.getHashCode().equals(hashCode)) && emaild.equals(user.getEmail())) {
			user.setValidated("true");
		} else {
			return false;
		}
		return true;
	}
	
	//@Override
	public boolean isUserValidated(String emailId, String password, String userName) {
		return emailDaoImpl.isUserValidated(emailId, password, userName);
	}
	
	public boolean isUserExist(String emailId) {
		User user = emailDaoImpl.getUserDetail(emailId);
		if(user != null) {
			return true;
		}
		return false;
	}
	
	public boolean userSignIn(String userName, String password) {
		User user = emailDaoImpl.userSignIn(userName, password);
		if(user == null) {
			return false;
		} 
		return true;
	}
	
	public boolean validateUserName(String userName) {
		return emailDaoImpl.validateUserName(userName);
	}
}