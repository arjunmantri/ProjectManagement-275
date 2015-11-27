package edu.sjsu.cmpe275.dao.interfaces;

import edu.sjsu.cmpe275.dto.User;


public interface IEmailDao {
	
	 void createUserSignUp(User user);
	
	 User getUserDetail(String emailId);
	
	 boolean isUserValidated(String emailId, String password, String userName);
	
	 void updateUser(User user);
	
	 User userSignIn(String userName, String password);
	 
	 boolean validateUserName(String userName);

}
