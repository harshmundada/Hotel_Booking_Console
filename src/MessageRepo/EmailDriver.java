package MessageRepo;
import java.util.*;
import java.util.Random;

import jakarta.mail.internet.AddressException;
public class EmailDriver 
{
     public static String sendto="";
     static Email e=new Email();
	public static boolean Welcome() throws AddressException {
		String subject="Get Started: Welcome to Hotel Recommendation System!";
		String text="Dear New User,\r\n"
				+ "\r\n"
				+ "Welcome!\r\n"
				+ "\r\n"
				+ "We are excited to have you join our community.we strive to provide you with the best experience possible.\r\n"
				+ "Here are a few things you can do to get started:\r\n"
				+ "1. Explore our features and services.\r\n"
				+ "2. Check out our help center if you have any questions.\r\n"
				+ "3. Rate us on google.\r\n"
				+ "\r\n"
				+ "If you have any questions, feel free to reach out to our support team at hotelrecommend7@gmail.com\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "The Giris Techhub Team Team\r\n"
				+ "";
		String from="hotelrecommend7@gmail.com";
		int r=e.send(sendto,from,subject,text);
		if(r!=0)
			return true;
		else
			return false;
			
	}
	public static int Otp() throws AddressException
	{
		Random random = new Random();
        int otpNumber = random.nextInt(900000) + 100000;
		String subject="Your One-Time Password for Hotel Recommendation System!";
		String text="Your One-Time Password is: "+otpNumber+"  .\r\n"
				+ "\r\n"
				+ "This OTP is valid for 30 minutes. Please do not share this OTP with anyone for security reasons.\r\n"
				+ "\r\n"
				+ "If you did not request this OTP or if you have any concerns about your account security, please contact our support team immediately at hotelrecommend7@gmail.com.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "The giris techhub Team"
				+ "";
		String from="hotelrecommend7@gmail.com";
		int r=e.send(sendto,from,subject,text);
		if(r!=0)
			return otpNumber;
		else
			return -1;
			
	}

}
