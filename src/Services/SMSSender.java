package Services;

import Utiles.MyDB;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.sql.Connection;


/**
 *
 * @author msi
 */
public class SMSSender {
    
    Connection cnx ; 
    public SMSSender() {
    cnx = MyDB.getInstance().getCnx(); 
}
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "AC1a21e4e67bbbb1d337b894e263f39b31";
  public static final String AUTH_TOKEN = "350ccf0b9f980e67d74d4ff8bbe311ed";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        PhoneNumber clientPhoneNumber = new PhoneNumber("+21626995933");
        Message message = Message.creator(clientPhoneNumber, new PhoneNumber(""), "Votre rendezvous a été traitée").create();
        System.out.println(message.getSid());
  }
  
      public void SMSSender() {
    // Remplacez les informations de compte et de numéro de téléphone par les vôtres
    String accountSid = "AC1a21e4e67bbbb1d337b894e263f39b31";
    String authToken = "350ccf0b9f980e67d74d4ff8bbe311ed";
 
      try {
        // Récupérer le numéro de téléphone de l'utilisateur à partir de la base de données
        
        
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
            new PhoneNumber("+21626995933"),
            new PhoneNumber("+16813123312"),
            "Votre rendezvous a été traitée"
        ).create();
        
        System.out.println("SID du message : " + message.getSid());
    } catch (Exception ex) {
        System.out.println("Erreur : " + ex.getMessage());
    }

     }
}