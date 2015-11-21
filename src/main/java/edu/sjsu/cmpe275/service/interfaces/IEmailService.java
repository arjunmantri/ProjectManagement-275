package edu.sjsu.cmpe275.service.interfaces;

public interface IEmailService {
	void sendEMail(String to, String from, String subject, String body);
}
