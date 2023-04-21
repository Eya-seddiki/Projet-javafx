/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.evenement;
import entities.participation;
import java.io.IOException;
import services.ParticipationService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AfficherParticipationController implements Initializable {

    @FXML
    private TableView<participation> tableParticipation;
    @FXML
    private TableColumn<participation, Integer> iduserTv;
    @FXML
    private TableColumn<participation, Integer> ideventTv;
    @FXML
    private TableColumn<participation, String> datePartTv;
    @FXML
    private Button modifierPartBtn;
    @FXML
    private Button supprimerPartBtn;
    @FXML
    private TextField idread;
    @FXML
    private TextField iduserField;
    @FXML
    private TextField ideventField;
    @FXML
    private DatePicker datepartField;
    
    ParticipationService Ps=new ParticipationService();
    @FXML
    private TextField datepartField1;
    @FXML
    private TextField idEventFIND;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getParticipation();
        idEventFIND.setVisible(false);
        idread.setVisible(false);
        datepartField.setVisible(false);
    }    

    @FXML
    private void modifierParticipation(ActionEvent event) throws SQLException {
        
         participation pa = new participation();
        pa.setId(Integer.valueOf(idread.getText()));
        pa.setEvenement_id(Integer.valueOf(ideventField.getText()));
        pa.setUser_id(Integer.valueOf(iduserField.getText()));
            Date d=Date.valueOf(datepartField.getValue());
        pa.setDate_participation(d);
        //pa.setDate_part(datepartField.getText());
       
        Ps.modifierParticipation(pa);
        resetPart();
        getParticipation();
           
        
    }

    @FXML
    private void supprimerParticipation(ActionEvent event) {
         participation p = tableParticipation.getItems().get(tableParticipation.getSelectionModel().getSelectedIndex());
      
        try {
            Ps.DeleteParticipation(p);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("participation delete");
        alert.setContentText("participation deleted successfully!");
        alert.showAndWait();
        getParticipation();
     
    }

    @FXML
    private void choisirParticipation(MouseEvent event) {
        participation part = tableParticipation.getItems().get(tableParticipation.getSelectionModel().getSelectedIndex());
        
        idread.setText(String.valueOf(part.getId()));
        ideventField.setText(String.valueOf(part.getEvenement_id()));
        iduserField.setText(String.valueOf(part.getUser_id())); 
        datepartField1.setText(String.valueOf(part.getDate_participation()));
        //datepartField.setValue((part.getDate_part()));
        
    }
    
    
    public void getParticipation(){
        try {
            // TODO
            List<participation> part = Ps.recupererParticipation();
            ObservableList<participation> olp = FXCollections.observableArrayList(part);
            tableParticipation.setItems(olp);
            iduserTv.setCellValueFactory(new PropertyValueFactory("user_id"));
            ideventTv.setCellValueFactory(new PropertyValueFactory("evenement_id"));
            datePartTv.setCellValueFactory(new PropertyValueFactory("date_participation"));
            // this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
    public void resetPart() {
        idread.setText("");
        ideventField.setText("");
        iduserField.setText("");
        datepartField.setValue(null);
        
    }

 
    private void populateTable(ObservableList<participation> branlist){
       tableParticipation.setItems(branlist);
   
       }

    @FXML
    private void go_to_back(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("ajouterEvenement.fxml"));
            idread.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void findPart(KeyEvent event) throws SQLException {
                     ParticipationService ps=new ParticipationService(); 
        participation b= new participation();
        ObservableList<participation>filter= ps.rechherchePartByEvent(Integer.valueOf(idEventFIND.getText()));
        populateTable(filter);
    }
}
