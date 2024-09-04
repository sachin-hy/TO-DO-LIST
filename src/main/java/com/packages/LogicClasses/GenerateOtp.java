package com.packages.LogicClasses;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;


@Service
public class GenerateOtp {

	public void SendOtp(String useremail,String otp)
	{
	 final String from="shop79278@gmail.com";
	  final String pass="xqui ogjz aedb uuip";
	  String subject="Otp verification";
	  
	  
	  String to=useremail;
	  
	  
	  
     System.out.println("properties classs is initialized");
		
     Properties p=new Properties();
		p.put("mail.smtp.host","smtp.gmail.com");	
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.auth", "true");
		
		p.put("mail.transport.protocol", "smtp");	
		
		/*=========================================*/
		System.out.println("session class is called");
		
		Session session=Session.getDefaultInstance(p,new javax.mail.Authenticator() 
		{

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from,pass);
			}
			
		});
		session.setDebug(true);
		  
		System.out.println("mimemessage class is initalized");
		MimeMessage mm=new MimeMessage(session);
		try {
			mm.setFrom(new InternetAddress(from));
			mm.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			mm.setSubject(subject);
			mm.setText("Please enter this otp to verfy registration " + otp);
			
			Transport.send(mm);
			/*=========================================*/
			System.out.println("Email sent successfully 1");
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ses.setAttribute("otpnumber", otp);
		
	}
	
	 public String generateotp()
	  {
		  String numbers="0123456789";
		  
		  Random rd=new Random();
		  
		  String otp="";
		  
		  for(int i=0;i<6;i++)
		  {
			  int x= rd.nextInt(numbers.length());
			  
			  otp+=numbers.charAt(x);
		  }
		  
		  System.out.println("otp : "+otp);
		  
		   return otp;
	  }
	
}
