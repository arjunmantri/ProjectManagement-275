package edu.sjsu.cmpe275.service.interfaces;

import org.springframework.stereotype.Component;


public interface IEmailService {
	void sendEmail(String to, String from, String subject, String body);
}
