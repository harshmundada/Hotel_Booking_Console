package MessageRepo;
import java.util.*;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
public class Email {
  public int send(String to,String from,String subject,String text) throws AddressException
  {
	  Properties p=new Properties();
	  p.put("mail.smtp.auth", true);
	  p.put("mail.smtp.host","smtp.gmail.com");
	  p.put("mail.smtp.port","587");
	  p.put("mail.smtp.starttls.enable",true);
	  String userName="hotelrecommend7";
		String password="wapv wnrz fuxa iolx";
	  Session s=Session.getInstance(p,new Authenticator()
			  {
		           public PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication(userName, password);
			  }
		          
			  });
	  Message m=new MimeMessage(s);
	  try {
		m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		m.setFrom(new InternetAddress(from));
		m.setSubject(subject);
		  m.setText(text);
		  Transport.send(m);
	       return 1;
	} catch (MessagingException e) {
		e.printStackTrace();
	}
	 
	       return 0;
  }
}
