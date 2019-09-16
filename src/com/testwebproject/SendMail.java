package com.testwebproject;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {
	
	public static void send(String toSend, String fnameemail) {
		
		final String userr = "salmanshanu99@gmail.com";
		final String paswrd = "exJATBMW4R8dh4m--:)($@";
		
		// get that particular user's name in future developments to address him/her in the email to make the email look like a personalized one ----> 	DONE NOW. IMPLEMENTED IT !!  
		
		
		String body = "Hi <b>" + fnameemail + "!</b> <br><br>" + "We received your password change request. <br><br>" + "<i> Please use the link below to set a new password.</i> <br><br>";  // You can use \n \r or \t if you encapsulate your string (email body) within <p> tag (paragraph tag)
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); // check 465 and 25 port as well.  
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true"); // security is TLS (Standard Encryption ----> which is industry standard. Checked Amazon email that I got and they used TLS)
		
		Session session = Session.getInstance(props, new Authenticator() 
		{                                     // this session class is from javax.mail package not the javax.websocket one !!
			
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(userr, paswrd);
			}
		
		});
		
		try {
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userr));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toSend));
			message.setSubject("Forgot Password?"); // This is the subject, recipient gets to see (Try to make the subject bold) I tried a lot but in vain. I think the solution is Jlabel class 
			String link = "<b> <i> <a href=\"http://localhost:8080/WebProject/reset_password.jsp?email="+ toSend + "\" target=\"_blank\"> Reset Password </a> </i> </b>";   // target = "_blank" opens URL in a new tab 
			
			message.setText(body + link, "UTF-8", "html");  // This is the body of the email. Send the link to reset_password.jsp in the body
			Transport.send(message);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
