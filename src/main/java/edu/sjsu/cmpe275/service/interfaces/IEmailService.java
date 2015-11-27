package edu.sjsu.cmpe275.service.interfaces;

import org.springframework.stereotype.Component;


public interface IEmailService {
	
	void sendUserSignUpEmail (String toEmail, String password, String userName, String body);
	
	boolean getUserDetails(String emaild, Integer hashCode);
	
	boolean isUserValidated(String emailId, String password, String userName);
	
	boolean isUserExist(String emailId);
	
	void userSignIn(String userName, String password);
	
	boolean validateUserName(String userName);
}
