package Lib.Common;
import java.lang.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart; import javax.mail.internet.MimeMessage; import javax.mail.internet.MimeMultipart;

import Lib.Controller.Driver;

public class ResultMailer {

	public void PushMail(Class ClsObj, boolean status){

		final String username = "moveproductsteam@gmail.com";
		final String password = "homestore";
		String ClsName = ClsObj.getPackage().getName().toString();
		String MailContent = "";
		if (status){
			MailContent = "<h2>Execution Result</h2><br><table>  <tr>  <tr>  <td>" + ClsName + "</td>  <td bgcolor=\"green\" width=\"100\"/> </tr> </table>";
			
		}
		else{
			MailContent = "<h2>Execution Result</h2><br><table>  <tr>  <tr>  <td>" + ClsName + "</td>  <td bgcolor=\"red\" width=\"100\"/> </tr> </table>";
		}
			
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.phx.move.com");
        props.put("mail.smtp.socketFactory.port", "25");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
		
		
		
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("moveproductsteam@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("Cristina.Monte@move.com;Murthi.Subramani@move.com;Madhusudhana.Shivalingappa@move.com"));
			message.setRecipients(Message.RecipientType.CC,InternetAddress.parse("krishnakishore.g@move.com"));
			message.setSubject(":: ADT Execution Result ::");
			Multipart mp = new MimeMultipart();
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(MailContent, "text/html");
			mp.addBodyPart(htmlPart);
			message.setContent(mp);
			//message.setText("Dear Mail Crawler \n\n No spam to my email, please!");
			Transport.send(message);
//			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	

    public void send(String from, String to,String MailSub, String MailMsg, ArrayList<String> attachments) throws MessagingException, UnknownHostException {
    	try{
    		
//    		System.out.println("inside send method");
//	 	 String localhostname = java.net.InetAddress.getLocalHost().getHostName();
//	 	 com.sun.security.auth.module.NTSystem NTSystem = new com.sun.security.auth.module.NTSystem();
//	 	 System.out.println(NTSystem.getName());
	 	 Calendar c = Calendar.getInstance();
	 	 String DateTime = c.getTime().toString();
//	 	 String OS = System.getProperty("os.name") + " - " + System.getProperty("os.version");
//	 	 String Arch = System.getProperty("os.arch");
//	 	 String Env = Driver.Init.GetBaseURL();
//	 	 String Browser = Driver.Init.GetAUTBrowser();
//	 	 String HtmlTop = "<html><body bgcolor=\"#FFFFCC\"><hr><div><font color=\"#0029A3\"><h3>Automated Test Execution Status - " + DateTime + "</h3></font><div><kbd>Executed at: " + localhostname + "<br>Executed by: " + NTSystem.getName() + "<br>Host OS: " + OS + "<br>Architecture: " + Arch + "<br>Environment: " + Env + "<br>Browser: " + Browser + "</kbd></div></div><hr><div>";
//	      HtmlTop += "<table align=\"center\" cellpadding\"5\" border=\"2\"><tr><thead><th>Test ID</th><th>Test Name</th><th>Status</th><th>Passed Step Count</th><th>Failed Step Count</th></thead></tr>";
//	      String HtmlBtm = "</table><hr></div><div><strong><br>Thanks<br>Move ADT</strong><br><br><em> Note:� This is an auto generated mail from an unmonitored mail box. Please do not reply directly to this email address.� If you have any questions regarding this email or would like to be removed from this notification distribution, please send an email to�<a href=mailto:automationdevteam@move.com>#Technology ADTFE</a>�</em><br></div><hr></body></html>";
//	      String HtmlDyno = "";
	      String HtmlMaster = MailMsg;
//	      String[] Temp1 = MailMsg.trim().split("`");
//	      for(int LoopA=0; LoopA<Temp1.length; LoopA++){
//	             String[] Temp2 = Temp1[LoopA].trim().split("~");
//	             if (Temp2[4].toLowerCase().equals("passed")){
//                     HtmlDyno += "<tr><td>" + Temp2[0] +"</td><td>"+Temp2[1]+ "</td><td bgcolor=\"green\" align=\"center\"></td><td align=\"center\">" + Temp2[2] + "</td><td align=\"center\">" + Temp2[3] + "</td></tr>";
//	             	}
//	             else{
//                     
//                     HtmlDyno += "<tr><td>" + Temp2[0] +"</td><td>"+Temp2[1]+ "</td><td bgcolor=\"red\" align=\"center\"></td><td align=\"center\">" + Temp2[2] + "</td><td align=\"center\">" + Temp2[3] + "</td></tr>";
//	             	}               
//	      }
	      //HtmlMaster = HtmlTop + HtmlDyno + HtmlBtm;
	      Properties props = new Properties();
	      props.put("mail.smtp.host", "smtp.phx.move.com");
	      props.put("mail.smtp.socketFactory.port", "25");
	      props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.port", "25");
	      Session session = Session.getInstance(props,new javax.mail.Authenticator() {protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication("sfdcqaautomation@move.com", "adtadmin28!");}});
	      Message message = new MimeMessage(session);
	      message.setRecipients(Message.RecipientType.BCC,InternetAddress.parse(to));
	      InternetAddress addressFrom = new InternetAddress(from);
	      message.setFrom(addressFrom);
	      message.addFrom(new InternetAddress[] { new InternetAddress(from) });
	     // message.setSubject("Automated Test Execution Status - " + DateTime);
	      message.setSubject(MailSub);
	      MimeBodyPart mbp1 = new MimeBodyPart();
	      mbp1.setText(HtmlMaster);
	      mbp1.setContent(HtmlMaster, "text/html");
	      Multipart mp = new MimeMultipart();
	      mp.addBodyPart(mbp1);
//	      for (String fileName: attachments){
//	         MimeBodyPart mbp = new MimeBodyPart();
//	         FileDataSource fds = new FileDataSource(fileName);
//	         mbp.setDataHandler(new DataHandler(fds));
//	         mbp.setFileName(fds.getName());
//	         mp.addBodyPart(mbp);
//	      }
	      message.setContent(mp);
	      message.setSentDate(new Date());
	      Transport.send(message); 
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
    }
}

