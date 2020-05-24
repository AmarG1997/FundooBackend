package com.blabz.fundoo.utility;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class Utility {

	private static final int JWT_TOKEN_VALIDITY = 0;

	private String secretKey = "it's_secret";

	@Autowired
	JavaMailSender javaMailSender;

	public String createToken(String email) {
		return Jwts.builder().claim("email", email).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 100000))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public void sendEmail(String email, String token) throws MessagingException {
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//		helper.setTo(email);
//		helper.setText("Click here to verify your account>" + token);
//		helper.setSubject("Verification Link");
//		javaMailSender.send(mimeMessage);
		String link="http://localhost:8080/validateUser?token=";
		String to = email;
		String from = "bridgelabzbookstore@gmail.com";
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "Book@123 ");
			}
		});
		session.setDebug(true);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Email verification link");
			message.setText("To verify email click here "+(link+token));
			Transport.send(message);
			System.out.println("Token is--->"+token);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public String getUserToken(String token) {
		System.out.println("now i am generating the token");
		String email="";
		String[] split_string = token.split("\\.");
		String base64EncodedBody = split_string[1];
		Base64 base64Url = new Base64(true);
		String body = new String(base64Url.decode(base64EncodedBody));
		try {
			JSONObject jsonObject = new JSONObject(body);
			email = (String) jsonObject.get("email");
			System.out.println("email is---->" + email);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return email;
	}

}
