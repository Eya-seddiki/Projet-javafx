/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reservation;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static jdk.nashorn.internal.objects.ArrayBufferView.length;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField tx_reservation;
    @FXML
    private TextField tx_user;
    @FXML
    private TextField tx_ressource;
    @FXML
    private TextField tf_descrip;
    @FXML
    private DatePicker dp_debut;
    @FXML
    private DatePicker dp_fin;
    private ReservationService ReservationService = new ReservationService();
    @FXML
    private TableView tab_reserv;
    @FXML
    private TableColumn tb_id;
    @FXML
    private TableColumn  tb_debut;
    @FXML
    private TableColumn  tb_fin;
    @FXML
    private TableColumn  tb_description;
    @FXML
    private TableColumn  tb_ressource;
    @FXML
    private TableColumn  tb_user;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        tb_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        tb_description.setCellValueFactory(new PropertyValueFactory<>("description_reservation"));
        tb_ressource.setCellValueFactory(new PropertyValueFactory<>("ressource_id"));
        tb_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            // récupère les données des utilisateurs depuis la base de données
            List<Reservation> catarticleList = ReservationService.AfficherReservation();
      
        
        // affiche les données dans le tableau
        tab_reserv.getItems().setAll(catarticleList);
        // TODO
    }    
public static boolean estChaineValide (String chaine){
    if (!chaine.matches("[a-zA-Z]+") || chaine.trim().isEmpty()){
    return false;}
    return true;}

    @FXML
    private void click_on_ajouetr(ActionEvent event) throws SQLException {
         int id_rese = Integer.parseInt(tx_reservation.getText());
         Date  date_debut=java.sql.Date.valueOf(dp_debut.getValue());
         Date  date_fin=java.sql.Date.valueOf(dp_fin.getValue());
         
        String descr=tf_descrip.getText();
        int id_user=Integer.parseInt(tx_user.getText());
        int id_ressou=Integer.parseInt(tx_ressource.getText());
         if(date_debut.before(date_fin)&&estChaineValide(descr))
         {
                 ReservationService sp=new ReservationService();
     
   Reservation a = new Reservation(id_rese,date_debut,date_fin,descr,id_user,id_ressou);
   sp.AjouterReservation(a); 
         }
         else{
             System.out.println("Erreur Controle de Saisie");
         }

  
                    List<Reservation> catarticleList = ReservationService.AfficherReservation();

                tab_reserv.getItems().setAll(catarticleList);

    }

    @FXML
    private void click_on_supprimer(ActionEvent event) throws SQLException {
        
         Reservation selectedReservation = (Reservation) tab_reserv.getSelectionModel().getSelectedItem();
       
        if (selectedReservation == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No category selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a category in the table.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deletion");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected category?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           
            ReservationService.SupprimerReservation(selectedReservation);
             List<Reservation> aricleList = ReservationService.AfficherReservation();
        
        // affiche les données dans le tableau
        tab_reserv.getItems().setAll(aricleList);
        }
    }

    @FXML
    private void click_on_modifier(ActionEvent event) throws SQLException {
             int id_rese = Integer.parseInt(tx_reservation.getText());
         Date  date_debut=java.sql.Date.valueOf(dp_debut.getValue());
         Date  date_fin=java.sql.Date.valueOf(dp_fin.getValue());
         
        String descr=tf_descrip.getText();
        int id_ressource = Integer.parseInt(tx_ressource.getText());
                int id_user=Integer.parseInt(tx_user.getText());
     ReservationService sp=new ReservationService();
   Reservation a = new Reservation(id_rese,date_debut,date_fin,descr,id_ressource,id_user);
   sp.modifierReservation(a);
   List<Reservation> aricleList = ReservationService.AfficherReservation();
        
        // affiche les données dans le tableau
        tab_reserv.getItems().setAll(aricleList);
    }
    
}
