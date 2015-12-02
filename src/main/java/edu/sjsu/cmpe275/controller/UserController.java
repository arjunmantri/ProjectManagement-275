package edu.sjsu.cmpe275.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.dto.Project;
import edu.sjsu.cmpe275.service.impl.EmailServiceImpl;

/**
 * @author Team - 3
 * Controller class for handling requests related to User entity.
 */
@RestController
@RequestMapping("/api/v1/*")
@ComponentScan(basePackages = {"edu.sjsu.cmpe275.service.impl"})
public  class UserController {
	
	@Autowired
	public EmailServiceImpl smtpMailSender;
	

	@RequestMapping(method = RequestMethod.POST, value = "/user/{toEmailId}/{userName}/{passWord}", 
			produces = {MediaType.APPLICATION_JSON_VALUE})  
	public ResponseEntity<String> userSignUpEmail(
			@PathVariable String toEmailId, 
			@PathVariable String userName, 
			@PathVariable String passWord) {
			String body = "Please click to the given link for http://localhost:3000/thome/" + toEmailId;
			if(smtpMailSender.isUserValidated(toEmailId, passWord, userName)) {
				System.out.println("----User is already validated-----");
				// URL for home Page set success code for showing the home page
			} else {
				System.out.println("----User is not validated sends an email----");
				smtpMailSender.sendUserSignUpEmail(toEmailId, userName, passWord, body);
			}
			JSONObject response = new JSONObject();
			response.put("EmailSend", "SendSuccessfully");
			return new ResponseEntity<String>(response.toString() , HttpStatus.OK);
		// return success code that please click the email for validation
    }

	@RequestMapping(method = RequestMethod.GET, value = "/user/{emailId}/{hashCode}", produces = {
            MediaType.APPLICATION_JSON_VALUE})  
	void userEmailClick(
			@PathVariable String emailId, 
			@PathVariable Integer hashCode) {
			boolean user = smtpMailSender.getUserDetails(emailId, hashCode);
			System.out.println("--------User Validated--Return--True---"+user);
   }
	
	@RequestMapping(method = RequestMethod.GET, value = "/usersignin/{userName}/{password}", produces = {
            MediaType.APPLICATION_JSON_VALUE})  
	void userSignIn(@PathVariable String userName, @PathVariable String password) {
		    boolean validUser = smtpMailSender.validateUserName(userName);
		    if(validUser) {
			    boolean user = smtpMailSender.userSignIn(userName, password);
				System.out.println("--------True--If user exist-Username and password is correct---"+user);
				System.out.println("--------False--WrongPassword---"+user);
		    } else {
		    	System.out.println("--------User is not valid----------");
		    }
    }


	@RequestMapping(method = RequestMethod.POST, value = "/user/{toEmailId}/{ProjectId}",
			produces = {MediaType.APPLICATION_JSON_VALUE})
	void inviteUser(
			@PathVariable String toEmailId,
			@PathVariable long ProjectId) {
		//String body = "http://localhost:3000/thome/"+toEmailId +"/";
		String body = "http://localhost:8080/api/v1/project/dineshpandeysjsu@gmail";
		System.out.println("----Invite user email----");
		smtpMailSender.sendUserInviteEmailService(toEmailId, ProjectId, body);
	}

}
