/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otp;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Ravindu Weerasinghe
 */
public class OTP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
      while(true) {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter your email : ");
        int length = 10;
        String otp =new String(passwordGenerator(length)); 
      
        sendMail(s.next(),otp);
        System.out.println("Enter your OTP : ");
        if(s.next().equals(otp)) 
        {
            System.out.println("Access Granted...");
            break;
        }else {
            System.out.println("Access can not be Granted please try again");
        }
    	}     
    }//main method
    
    static char[] passwordGenerator(int length){
        
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String simpleLetters = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 
        String symbols = "!@#$%^&*_=+-/.?<>)";
        
        String values = capitalLetters + simpleLetters + numbers + symbols;
        
        Random random = new Random();
        
        char[] password = new char[length];
        
        //generate the password
        for(int i=0; i<length; i++){
            password[i] = values.charAt(random.nextInt(values.length()));
        }
        
        return password;
    }//passwordGenertator method
    
        static void sendMail(String email, String password){
        try{
            String host ="smtp.gmail.com" ;
            String user = "otpsendercom@gmail.com";
            String subject = "This is your OTP";
            String body = "OTP is : " + password;
            boolean sessionDebug = false;

            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(email)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(body);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, "passcomotp");
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("OTP send successfully");
        }catch(Exception e)
        {
            System.out.println(e);
        }

    }//senMail method
        
}//class OTP


