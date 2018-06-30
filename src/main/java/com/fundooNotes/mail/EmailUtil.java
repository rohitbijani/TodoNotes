package com.fundooNotes.mail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	@Autowired
    private JavaMailSender mailSender;  

	public void sendEmail(final String from, final String to,final String subject,final String msg){
		
		try {
			MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {			
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(to));  
					mimeMessage.setFrom(new InternetAddress(from));  
					mimeMessage.setSubject(subject);  
					mimeMessage.setText(msg);	
				}
			};
			
	        mailSender.send(messagePreparator);  
			System.out.println("Email Sent Successfully!!");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
}
