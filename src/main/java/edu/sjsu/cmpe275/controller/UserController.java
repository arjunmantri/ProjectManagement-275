package edu.sjsu.cmpe275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.sjsu.cmpe275.service.impl.EmailServiceImpl;

/**
 * @author Team - 12
 * Controller class for handling requests related to User entity.
 */
@RestController
@RequestMapping("/api/v1/*")
@ComponentScan(basePackages = {"edu.sjsu.cmpe275.service.impl"})
public  class UserController {
	
	@Autowired
	public EmailServiceImpl smtpMailSender;
	

	@RequestMapping(method = RequestMethod.GET, value = "/user/{toEmailId}/{userName}/{passWord}", produces = {
            MediaType.APPLICATION_JSON_VALUE})  
	void userSignUpEmail(@PathVariable String toEmailId, @PathVariable String userName, 
			@PathVariable String passWord) {
			String body = "Please click to the given link";
			smtpMailSender.sendUserSignUpEmail(toEmailId, userName, passWord, body);
    }

	
	/*
	JSONObject response = new JSONObject();
	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> getTest(@RequestBody User reqPar){
		String userName = reqPar.getUserName();
		String password = reqPar.getPassword();
		System.out.println("userName is "+userName+" & password is "+password);
			try {
				response.put("name", "mahesh");
			} catch (JSONException e) {
				e.printStackTrace();
			}
	    return new ResponseEntity<String>(response.toString(),HttpStatus.OK); 

	} */
}
