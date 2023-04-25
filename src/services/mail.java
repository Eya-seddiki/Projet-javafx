/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author dell
 */
public class mail {
    public void sendMail(String recipient,  String mailutilisateur, String Information) throws WriterException, IOException {
        System.out.println(recipient);
        /*String host="ranim.ahmadi@esprit.tn";
        final String user="ranim.ahmadi@esprit.tn";//← my email address
        final String password="201JFT2214";//change accordingly   //← my email password
         */
        String host = "asmaf2408@gmail.com";  //← my email address
        final String user = "asmaf2408@gmail.com";//← my email address
        final String password = "qngpellfinxaxmhk";//change accordingly   //← my email password

        String to = recipient;//→ the EMAIL i want to send TO →
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
     
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
         int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
        BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        bufferedImage.createGraphics();
        Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(byteMatrix, "PNG", baos);
        byte[] imageData = baos.toByteArray();
        //////////
         DataSource source = new ByteArrayDataSource(imageData, "image/png");
        MimeBodyPart qrCodeAttachment = new MimeBodyPart();
        try {
           //  messageBodyPart.setText("Voici votre message et le QR code en pièce jointe :");
            qrCodeAttachment.setDataHandler(new DataHandler(source));
           qrCodeAttachment.setFileName("qrcode.png");
        } catch (MessagingException e) {
            e.printStackTrace();
            return;
        }
            /////////////////////////////////////////////////////////////////////////////////////////
        //My message :
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
             message.setSubject("QR code");
            message.setSubject(" entretien d'embauche ! ");
            MimeMultipart multipart = new MimeMultipart();
             
            
             multipart.addBodyPart(qrCodeAttachment);
               message.setContent(multipart);
               
               MimeBodyPart textPart = new MimeBodyPart();
    textPart.setText("Bonjour ,"  + mailutilisateur+", Nous vous remercions pour votre candidature . Après examen minutieux de votre dossier, nous sommes intéressés par votre profil et souhaitons vous rencontrer personnellement." );
    multipart.addBodyPart(textPart);
            BodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setText("Voici votre message et le QR code en pièce jointe :");
              multipart.addBodyPart(qrCodeAttachment);
              String messageText = "Hello, World!";
               message.setContent(multipart);
  
               
            //Text in emial :
            //message.setText("  → Text message for Your Appointement ← ");
            //Html code in email :
           /* message.setContent(multipart+
                    "<h1 style \"color:red\" >Le numero de votre commande est :   </h1> "
                            + "<br/> <h2>Nom du produit: " + libelleProduit + "</h2> </br> <img width=\"50%\" height=\"50%\" src=https://i.imgur.com/iYcBkOf.png>",
                    "text/html");*/

            //send the message
            Transport.send(message);

            System.out.println("message sent successfully via mail ... !!! ");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}



