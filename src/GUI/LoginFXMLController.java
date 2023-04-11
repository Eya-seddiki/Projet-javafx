/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.User;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class LoginFXMLController implements Initializable {

   
    /**
     * Initializes the controller class.
     */
      @FXML
    private Button switchRegister;

    @FXML
    private TextField EmailL;

    @FXML
    private TextField PasswordL;

    @FXML
    private Button bouttonLogin;
    
    
          
       

       
   
    public void login(ActionEvent event) {
   /* String email = EmailL.getText();
    String password = PasswordL.getText();
    
      //if (email.equals("seifeddin.manai@esprit.tn") && password.equals("wxcvbn")) 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADO-DOC :: Success Message");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenue Admin");
        alert.showAndWait();
*/
  
       try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UserFXML.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }}
    
     public void switchRegister(ActionEvent event) {
   /* String email = EmailL.getText();
    String password = PasswordL.getText();
    
      //if (email.equals("seifeddin.manai@esprit.tn") && password.equals("wxcvbn")) 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ADO-DOC :: Success Message");
        alert.setHeaderText(null);
        alert.setContentText("Bienvenue Admin");
        alert.showAndWait();
*/
  
       try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterFXML.fxml"));
             Parent root = loader.load();
             Scene scene = new Scene(root);
             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             stage.setScene(scene);
             stage.show();
             stage.toFront();
         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
    
    
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
