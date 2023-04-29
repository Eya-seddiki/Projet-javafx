/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Demande;
import model.Offre;
import model.User;
import services.DemandeServices;
import services.OffreServices;
import services.UserServices;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class DemandefFXMLController implements Initializable {

    @FXML
    private TextArea descriptionf;
    @FXML
    private TextField cvf;
  @FXML
    private ComboBox<User> mailf;
    @FXML
    private ComboBox<Offre> nomoffref;
      @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
             DemandeServices s = new DemandeServices();
OffreServices ps = new OffreServices();
UserServices U = new UserServices();
         ObservableList<String> nomooffery =FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         List<Offre> listoffre = ps.afficherOffre();
        
        nomoffref.setItems(FXCollections.observableArrayList(listoffre));
		nomoffref.setConverter(new StringConverter<Offre>() {
			@Override
			public String toString(Offre object) {
				return object.getNom_offre();
			}

			@Override
			public Offre fromString(String string) {
				return nomoffref.getItems().stream().filter(a -> a.getNom_offre().equals(string)).findFirst().orElse(null);
			}
		});
                 List<User> listuser = U.afficherUser();
        
        mailf.setItems(FXCollections.observableArrayList(listuser));
		mailf.setConverter(new StringConverter<User>() {
			
             @Override
             public String toString(User object) {
				return object.getEmail();
             }

             @Override
             public User fromString(String string) {
				return mailf.getItems().stream().filter(a -> a.getEmail().equals(string)).findFirst().orElse(null);
             }
		});
    }    

    @FXML
    private void ajouterf(ActionEvent event) {
        if ((cvf.getText().isEmpty()) || (descriptionf.getText().isEmpty() || (nomoffref.getSelectionModel().getSelectedIndex()==-1) ||(mailf.getSelectionModel().getSelectedIndex()==-1) )){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        
        Demande d= new Demande(nomoffref.getSelectionModel().getSelectedItem().getId_offre(),mailf.getSelectionModel().getSelectedItem().getId(), cvf.getText(), descriptionf.getText());
           System.out.println(d);
           s.ajouterDemande(d);
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("offrefrontFXML.fxml"));
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

    @FXML
    private void insertion(ActionEvent event) throws FileNotFoundException, IOException {
         Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.pdf"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\ImageP\\\\"  + x + ".pdf";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            image.setImage(img);    
            cvf.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();          
        } else {
            System.out.println("error");
        }
    }
    
}
