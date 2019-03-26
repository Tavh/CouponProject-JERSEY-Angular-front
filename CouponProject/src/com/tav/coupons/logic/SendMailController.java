package com.tav.coupons.logic;

import java.util.Properties; 

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.tav.coupons.beans.UserDetails;

public class SendMailController {

// ----------------------------------------- Sends a mail with user details ----------------------------------------------

	public void sendMail (UserDetails userDetails) {

		final String username = "tavcoupons@gmail.com"; // A scape-goat gmail account 
		final String password = "srduicukzh1";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("silvermill123@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(userDetails.getEmail()));// "The user's email"
			message.setSubject("Welcome to Tav's coupon service!");
			message.setText("Hello, " + userDetails.getUsername() + "! Your account has been created succesfully"
				+ "\n\n Your password is : " + userDetails.getPassword());

			Transport.send(message);

			System.out.println("An email was sent to " + userDetails.getEmail());

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
