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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FrontController implements Initializable {
 @FXML
    private TableView<Rendezvous> tableRendez;
    @FXML
    private TableColumn<Rendezvous, String> nomTV;
    @FXML
    private TableColumn<Rendezvous, String> prenomTV;
    @FXML
    private TableColumn<Rendezvous, String> lieuTV;
    @FXML
    private TableColumn<Rendezvous, String> emailTv;
    @FXML
    private TableColumn<Rendezvous, DatePicker> dateTV;
    @FXML
    private TableColumn<Rendezvous, Integer> useridTV;
    @FXML
    private Button btnrendez;
    ServiceRendezvous SR=new ServiceRendezvous();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
         // TODO
         senddata();
     } catch (SQLException ex) {
         Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
     }
     
    }    

    @FXML
    private void choisirRendezvous(MouseEvent event) {
    }

    @FXML
    private void ajouterrendezvous(ActionEvent event) {
               try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
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
        public void senddata() throws SQLException
{

    nomTV.setCellValueFactory(new PropertyValueFactory<Rendezvous,String>("nomRendezvous"));
    nomTV.setEditable(true);
    nomTV.setCellFactory(TextFieldTableCell.forTableColumn());
    
    prenomTV.setCellValueFactory(new PropertyValueFactory<Rendezvous,String>("prenomRendezvous"));
    prenomTV.setEditable(true);
    prenomTV.setCellFactory(TextFieldTableCell.forTableColumn());
    
    lieuTV.setCellValueFactory(new PropertyValueFactory<Rendezvous,String>("lieuRendezvous"));
    lieuTV.setEditable(true);
    lieuTV.setCellFactory(TextFieldTableCell.forTableColumn());
    
    emailTv.setCellValueFactory(new PropertyValueFactory<Rendezvous,String>("emailRendezvous"));
    emailTv.setEditable(true);
    emailTv.setCellFactory(TextFieldTableCell.forTableColumn());
    
    dateTV.setCellValueFactory(new PropertyValueFactory<Rendezvous,DatePicker>("dateRendezvous"));
    dateTV.setEditable(true);
    
    useridTV.setCellValueFactory(new PropertyValueFactory<Rendezvous,Integer>("user_id"));
    
    
    tableRendez.setItems(FXCollections.observableArrayList(SR.recupererrendezVous()));
    ObservableList<Rendezvous> oList = FXCollections.observableArrayList(SR.recupererrendezVous());
        FilteredList<Rendezvous> filteredData = new FilteredList<Rendezvous>(oList, b -> true);
        
        SortedList<Rendezvous> sortedList = new SortedList <Rendezvous>(filteredData);
        sortedList.comparatorProperty().bind(tableRendez.comparatorProperty())    ;
        tableRendez.setItems(sortedList);
    
}
    
}
