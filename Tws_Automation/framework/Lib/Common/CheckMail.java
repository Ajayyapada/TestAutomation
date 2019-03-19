package Lib.Common;

	
	
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class CheckMail {
	
	//public static Message objMessage; 
	//public static InternetAddress addressFrom ;
	public static Multipart multipart;
	public static BodyPart messageBodyPart;
	public static DataSource source;



	public static Properties props ;
	public static Message objMessage;
	public static InternetAddress addressFrom;
	
	
	Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("ditipinak", "aarinprinci");
			}
			 });
	
	
	public static  void attaChment(String filePath) throws MessagingException
	{
		 
		
		
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
//		Session session = Session.getInstance(props,
//				  new javax.mail.Authenticator() {
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication("ditipinak", "aarinprinci");
//				}
//				 });
		//objMessage = new MimeMessage(session);
		addressFrom = new InternetAddress("ditipinak@gmail.com");
		messageBodyPart = new MimeBodyPart();
		source = new FileDataSource(filePath);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filePath);
		multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		objMessage.setContent(multipart);
		
	}
	
		
		public void send(String subject) throws MessagingException
		{
			props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
//			 Session session = Session.getInstance(props,
//					  new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication("ditipinak", "aarinprinci");
//					}
//					 });
			//objMessage = new MimeMessage(session);
			objMessage.setSubject(subject);
			msg("Hi");
			attaChment("E:/Test1.xlsx");
			Transport.send(objMessage);
		}
		
		public static void msg(String message) throws AddressException, MessagingException
		{
			 
			props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("ditipinak", "aarinprinci");
					}
					 });
			addressFrom = new InternetAddress("ditipinak@gmail.com");
			objMessage.setFrom(addressFrom);
			objMessage.setContent(message, "text/plain");
			
			
		}
		
		public static void main(String[] args) throws MessagingException {
			CheckMail m=new CheckMail();
			m.send("mail");
		}
		
	}



	


