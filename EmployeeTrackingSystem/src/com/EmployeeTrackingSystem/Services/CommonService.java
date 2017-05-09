package com.EmployeeTrackingSystem.Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.EmployeeTrackingSystem.Utilities.DbConnection;

public class CommonService 
{
	Connection 	con 			=	DbConnection.getConnection();
	PreparedStatement p;
	
	//.........................Delete by id...............................//
	public int Delete(int id,String table,String field)
	{
		int   k = 0;
		try {
			p 	=	con.prepareStatement("delete from "+table+" where "+field+" = ?");
			p.setInt(1,id);
			k 	=	p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
	
	//.........................Change Status...............................//
	public int ChangeStatus(int id,String status,String table,String field)
	{
		int   k = 0;
		try {
			p 	=	con.prepareStatement("update "+table+" set status = ? where "+field+" = ?");
			p.setString(1, status);
			p.setInt(2,id);
			k 	=	p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
	
	//.................................Send Mail..............................//
	   public int sendMail(String from,String to,String subject,String msg) 
	   {
		   	final String userName ="project.demoreply@gmail.com"; //PUT YOUR EMAIL ID HERE
		    final String password="projectsdemos";  // YOUR PASSWORD
			 try
		     {
				 Properties properties = new Properties();
		         properties.put("mail.smtp.host", "smtp.gmail.com");
		         properties.put("mail.smtp.port", "587");
		         properties.put("mail.smtp.starttls.enable", "true") ;
		         properties.put("mail.smtp.auth", "true") ;

		         Session session = Session.getInstance(properties,new Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication(){
		                return new PasswordAuthentication(userName, password);
		            }
		         });
		        Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress(from));
	            message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));
	            message.setSubject(subject);
	            message.setContent(msg,"text/html;     charset=utf-8");
	            Transport.send(message);
		     }
			 catch (Exception e) 
			 {
				e.printStackTrace();
				return 3;
			 }
			 return 1;
	   }
	   
}
