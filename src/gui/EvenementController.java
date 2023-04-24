/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.UserDetails;
import entities.evenement;
import entities.participation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import services.EvenementService;
import services.ParticipationService;
import services.SendMail;

import javax.mail.MessagingException;


/**
 * FXML Controller class
 *
 * @author asus
 */
public class EvenementController implements Initializable {

    int idEvent;
    @FXML
    private Label nomEventLabel;
    @FXML
    private Label typeEventLabel;
    @FXML
    private Label descriptionEventLabel;
    @FXML
    private Label dateEventLabel;
    @FXML
    private Button participerEventButton;
    @FXML
    private Label nb_participantsLabel;
    
    UserDetails u=new UserDetails();
    ParticipationService Ps=new ParticipationService();
    @FXML
    private TextField ideventF;
    @FXML
    private TextField iduserF;
    
    EvenementService Ev=new EvenementService();
    @FXML
    private ImageView imageview;
    @FXML
    private Label participantComplet;
    @FXML
    private TextField idPartField;
    @FXML
    private Button annulerButton;
    @FXML
    private Label dateFinEventLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ideventF.setVisible(false);
                iduserF.setVisible(false);
                participantComplet.setVisible(false);
                annulerButton.setVisible(false);
                idPartField.setVisible(false);

    }    
    private evenement eve=new evenement();
    
    public void setEvennement(evenement e) {
        this.eve=e;
        nomEventLabel.setText(e.getNom_evenement());
        typeEventLabel.setText(e.getType_evenement());
        descriptionEventLabel.setText(e.getLieu_evenement());
        dateEventLabel.setText(String.valueOf(e.getDate_debut()));
        nb_participantsLabel.setText(String.valueOf(e.getNbrparticipants()));
        dateFinEventLabel.setText(String.valueOf(e.getDate_fin()));
        ideventF.setText(String.valueOf(e.getId()));
        iduserF.setText(String.valueOf(1));
         String path = e.getImage_evenement();
         File file=new File(path);
         Image img = new Image(file.toURI().toString());
         imageview.setImage(img);

    }
    public void setIdevent(int idevent){
        this.idEvent=idevent;
    }
    


    @FXML
    private void participerEvent(MouseEvent event) throws SQLException, MessagingException {

        LocalDate dateActuelle = LocalDate.now();
        Date dateSQL = Date.valueOf(dateActuelle);
        participation p=new participation(dateSQL,Integer.parseInt(iduserF.getText()),Integer.parseInt(ideventF.getText()),"test");
        
        Ps.ajouterParticipation(p);

        idPartField.setText(String.valueOf(47));
        annulerButton.setVisible(true);
        participerEventButton.setVisible(false);
        
 
        SendMail.sendMail("boulares.montassar@esprit.tn");
                
        }
    
    public void arreterEvent()
    {
        participerEventButton.setVisible(false);
        participantComplet.setVisible(true);
    }

    @FXML
    private void annulerParticipation(ActionEvent event) throws SQLException {
        participation p=new participation();
        p.setId(Integer.parseInt(idPartField.getText()));
        Ps.DeleteParticipation(p);
        participerEventButton.setVisible(true);
        annulerButton.setVisible(false);
    }
    
 

    private void ajouterAvis(ActionEvent event) {
        
         try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterAvis.fxml"));
            ideventF.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @FXML
    private void go_cod(MouseEvent event) {
try {                       
                            
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterAvis.fxml"));
        Parent root = loader.load();
          AjouterAvisController destController = loader.getController();
//          destController.prodredcu(ps.selectProduitById(ps.getProductIdByName(nomProduirLabel.getText())));
          
         destController.setIdevent(Integer.parseInt(ideventF.getText()));
                    Scene sence = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(sence);
                    stage.show();
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
    
}
