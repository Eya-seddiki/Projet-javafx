/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Rendezvous;
import Services.ServiceRendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RendezfrontController implements Initializable {
          ObservableList<Rendezvous> events;
    ServiceRendezvous SR=new ServiceRendezvous();

    @FXML
    private TextField nomtf;
    @FXML
    private TextField lieutf;
    @FXML
    private TextField prenomtf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField usertf;
    @FXML
    private DatePicker datetf;
    @FXML
    private Button btnrendez;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterrendezvous(ActionEvent event) {
         
        
         int part=0;
        if ((nomtf.getText().length() == 0) || (prenomtf.getText().length() == 0) || (lieutf.getText().length() == 0) || (emailtf.getText().length() == 0)|| (usertf.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }
       else if (NoDate() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("la date date doit être aprés la date d'aujourd'hui");
            alert.showAndWait();
        }
       
            else
            {
                
        Rendezvous r = new Rendezvous();
        r.setNomRendezvous(nomtf.getText());
        r.setPrenomRendezvous(prenomtf.getText());
        r.setEmailRendezvous(emailtf.getText());
        java.util.Date date_debut=java.util.Date.from(datetf.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqlDate = new Date(date_debut.getTime());
        r.setDateRendezvous(sqlDate);
        r.setLieuRendezvous(lieutf.getText());
        r.setUser_id(Integer.valueOf(usertf.getText()));
        
        //lel image
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("Event add");
            alert.setContentText("Event added successfully!");
            alert.showAndWait(); 
//            SMSSender SS = new SMSSender() ; 
//        SS.SMSSender();
           // SendMail.sendMail("ala.chebil@esprit.tn");
                                  try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("FactureFXML.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
           
            
        try {
            SR.add(r);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
//        getEvents();
      
        
        
    }
    }
         private void reset() {
        nomtf.setText("");
        prenomtf.setText("");
        usertf.setText("");
        emailtf.setText("");
        lieutf.setText("");
        datetf.setValue(null);    
    }
              private boolean NoDate() {
         LocalDate currentDate = LocalDate.now();     
         LocalDate myDate = datetf.getValue(); 
         int comparisonResult = myDate.compareTo(currentDate);      
         boolean test = true;
        if (comparisonResult < 0) {
        // myDate est antérieure à currentDate
        test = true;
        } else if (comparisonResult > 0) {
         // myDate est postérieure à currentDate
         test = false;
        }
        return test;
    }

    
}
