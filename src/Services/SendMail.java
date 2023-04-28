/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author HP
 */
public class SendMail {
    
    
       public static void sendMail(String recepient) throws MessagingException{
   
       Properties properties = new Properties();
       properties.put("mail.smtp.auth","true");
       properties.put("mail.smtp.starttls.enable","true");
       properties.put("mail.smtp.host","smtp.gmail.com");
       properties.put("mail.smtp.port","587");
       
       String MyEmail = "razisniha@gmail.com";
       String passowrd = "umrepwvdpxjfuwrl";
       
       Session session = Session.getInstance(properties, new Authenticator(){
       @Override 
       protected PasswordAuthentication getPasswordAuthentication(){
           return new PasswordAuthentication(MyEmail,passowrd);
       
           
          
       }
       
   });
       
     
   Message message = prepareMessage(session, MyEmail, recepient);
   Transport.send(message);
       System.out.println("nice");
    
         
   }

    private static Message prepareMessage(Session session,String MyEmail , String recepient) {
       try {
           LocalDate dateActuelle = LocalDate.now();
        Date dateSQL = Date.valueOf(dateActuelle);
           Message message = new MimeMessage(session);
           message.setFrom(new InternetAddress(MyEmail));
           message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
           message.setSubject("participation avec succe");
           message.setText("blabla"+String.valueOf(dateSQL));
           
           MimeBodyPart attachmentBodyPart = new MimeBodyPart();
          
            // Create a MimeMultipart object to hold the message body parts
            MimeMultipart multipart = new MimeMultipart();

        
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This is a test email from Java."+String.valueOf(dateSQL));

            multipart.addBodyPart(messageBodyPart);

       

            Path attachmentPath = Paths.get("C:\\xampp\\htdocs\\photo\\ala.jpg");
             byte[] attachmentData = Files.readAllBytes(attachmentPath);
            attachmentBodyPart.setContent(attachmentData, Files.probeContentType(attachmentPath));

        
            attachmentBodyPart.setFileName(attachmentPath.getFileName().toString());

         
            attachmentBodyPart.setFileName(attachmentPath.getFileName().toString());

     
            multipart.addBodyPart(attachmentBodyPart);


            message.setContent(multipart);

           return message; 
       } catch (Exception ex) {
           Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
       }return null;
    }
    
}
