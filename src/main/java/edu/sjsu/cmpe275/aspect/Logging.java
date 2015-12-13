package edu.sjsu.cmpe275.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/*
 * Aspect implementaiton for logging
 */
@Aspect
public class Logging {

	@Before("execution(public * edu.sjsu.cmpe275.service.impl.ProjectServiceImpl.createPorject(..)")
	public void methodLogBefore(JoinPoint jointPoint) {
		Object [] argument = jointPoint.getArgs();
		String title = (String) argument[0];
		String description = (String) argument[1];
		String state = (String) argument[2];
		String projectOwnerEmail = (String) argument[3];
		System.out.println("User " + projectOwnerEmail + "created a project with title: " + title 
				+  " with description: " + description + " and state: " + state );
	}
	
		
	
}
