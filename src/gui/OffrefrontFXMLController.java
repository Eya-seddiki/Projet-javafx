/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.MyListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Offre;
import org.controlsfx.control.Notifications;
import services.OffreServices;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class OffrefrontFXMLController implements Initializable {
    private Label label1;
    private GridPane  grid;
  private List<Offre> offress = new ArrayList<>();
  
    private MyListener myListener;

     private Offre chosenoffre;

    /**
     * Initializes the controller class.
     */
     
     OffreServices f = new OffreServices();
    @FXML
    private Pane nn;
    @FXML
    private Pane nn1;
    @FXML
    private Pane nn2;
    @FXML
    private GridPane gridEvent;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            afficheroffre();
        } catch (SQLException ex) {
            Logger.getLogger(OffrefrontFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            }    
  public void afficheroffre() throws SQLException{
         try {
            List<Offre> offresss = f.recupereroffre();
                        

            gridEvent.getChildren().clear();
            int row = 0;
            int column = 0;
            for (int i = 0; i < offresss.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modeleFXML.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                ModeleFXMLController controller = loader.getController();
                controller.setoffre(offresss.get(i));
                controller.setIdeoffre(offresss.get(i).getId_offre());
                gridEvent.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
                /*if(offress.get(i).getNbrparticipants()<=0)
                {
                 // Ev.supprimerEvenement(evenements.get(i));
                controller.arreterEvent();
                }*/
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    @FXML
    private void postuler(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("demandefFXML.fxml"));
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
}
