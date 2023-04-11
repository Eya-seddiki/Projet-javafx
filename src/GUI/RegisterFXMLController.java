/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Seif
 */
public class RegisterFXMLController implements Initializable {

    /**
     * Initializes the controller class.

*/
      @FXML
    private Button btndelete;

    @FXML
    private Button btninsert;

    @FXML
    private TextField emailu;

    @FXML
    private TextField nomu;

    @FXML
    private TextField passwordu;

    @FXML
    private ComboBox<String> roleu;

    @FXML
    private TextField telephoneu;

    
    
    
    
    
      public void switchLogin(ActionEvent event) {
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
             FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
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
