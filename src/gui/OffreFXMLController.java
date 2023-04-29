/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import services.OffreServices;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author dell
 */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Offre;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import services.OffreServices;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;



public class OffreFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
     OffreServices ps = new OffreServices();

    @FXML
    private Button btndelete;

    @FXML
    private Button btninsert;

     @FXML
    private DatePicker datePicker;

    @FXML
    private TextField nomf;
    @FXML
    private TextField rech;
    
     @FXML
    private TableColumn<Offre, DatePicker> datef;
     @FXML
    private Button search;
        @FXML
    private TextField searchtxt;


    @FXML
    private TableColumn<Offre, Integer> idf;

    @FXML
    private TableColumn<Offre, String> nmf;

    @FXML
    private TableView<Offre> tableprod;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        senddata();
        tableprod.setEditable(true);
    }    
    
    
    @FXML
    void demande(ActionEvent event) throws IOException {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("demandeFXML.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            stage.toFront();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());

     
    }
    Notifications notificationBuilder = Notifications.create()
        .title("Produit Ajouter")
        .text("votre offre a été ajoutee avec succes")
        .graphic(null)
        .hideAfter(javafx.util.Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("ajout avec succes");
            }
               });
        notificationBuilder.showConfirm();
   //String asma =statistique( event);
    }
    @FXML
    void onActionSearch(ActionEvent event) {

    }
    public void senddata()
{ //Remplir les colonnes :
    idf.setCellValueFactory(new PropertyValueFactory<Offre, Integer>("id_offre"));
    nmf.setCellValueFactory(new PropertyValueFactory<Offre,String>("nom_offre"));
    nmf.setEditable(true);
    nmf.setCellFactory(TextFieldTableCell.forTableColumn());
    datef.setCellValueFactory(new PropertyValueFactory<Offre,DatePicker>("datepub_offre"));
    datef.setEditable(true);
    tableprod.setItems(FXCollections.observableArrayList( ps.afficherOffre()));
    ObservableList<Offre> oList = FXCollections.observableArrayList(ps.afficherOffre());
        FilteredList<Offre> filteredData = new FilteredList<Offre>(oList, b -> true);
        searchtxt.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( m -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (m.getNom_offre().toLowerCase().indexOf(lowerCaseFilter)!=-1) {
                    return true;
                } else return false;
            });
        });
        SortedList<Offre> sortedList = new SortedList <Offre>(filteredData);
        sortedList.comparatorProperty().bind(tableprod.comparatorProperty())    ;
        tableprod.setItems(sortedList);
    
        ///////////////
          
}
     @FXML
    void onoffredatedit(TableColumn.CellEditEvent<Offre, String> event) {
Offre F = tableprod.getSelectionModel().getSelectedItem();
         F.setNom_offre(event.getNewValue());
         ps.modifieroffre(F);
    }

    @FXML
    void onoffrenomedit(TableColumn.CellEditEvent<Offre, String> event) {
       

    }
    @FXML
    void deletoffre(ActionEvent event) {
        Offre f = tableprod.getSelectionModel().getSelectedItem();
        ps.supprimerOffre(f.getId_offre());
        senddata();
    }
    
   
   
private boolean NoDate() {
         LocalDate currentDate = LocalDate.now();     
         LocalDate myDate = datePicker.getValue(); 
         int comparisonResult = myDate.compareTo(currentDate);      
         boolean test = true;
        if (comparisonResult <= 0) {
        // myDate est antérieure à currentDate
        test = true;
        } else if (comparisonResult >=0) {
         // myDate est postérieure à currentDate
         test = false;
        }
        return test;
    }
    @FXML
    void insertoffre(ActionEvent event) {
         if (nomf.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le nom");
            alert.show();
         }
         else if (NoDate() == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("la date date doit être aprés la date d'aujourd'hui");
            alert.showAndWait();
            
            
        }
         else {
                
                
                     // Date myDate = Date.valueOf(datePicker.getValue().toString());
            LocalDate currentDate = LocalDate.now();
            LocalDate selectedDate = datePicker.getValue();
           java.sql.Date datee = java.sql.Date.valueOf(datePicker.getValue());
           Offre f = new Offre (nomf.getText(),datee);
             System.out.println(datee);
             ps.ajouterOffre(f);
             senddata();
                      Notifications notificationBuilder = Notifications.create()
        .title("Produit Ajouter")
        .text("votre offre a été ajoutee avec succes")
        .graphic(null)
        .hideAfter(javafx.util.Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT)
        .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("ajout avec succes");
            }
               });
        notificationBuilder.showConfirm();
    }
       
             
           
          
         }


}
    
