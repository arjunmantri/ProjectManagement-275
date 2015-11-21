package edu.sjsu.cmpe275.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.cmpe275.dto.User;





/**
 * @author vtupe
 *Controller class for handling requests related to User entity.
 */
@RestController
@RequestMapping("/api/v1/*")
public  class UserController {
	
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

	}




}
