/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Rendezvous;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;



import Services.ServiceRendezvous;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class HomeController implements Initializable {

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
    private Button btnrendez;
    @FXML
    private DatePicker datetf;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
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
    
    
      ObservableList<Rendezvous> events;
    ServiceRendezvous SR=new ServiceRendezvous();
    @FXML
    private TextField idmodifierField;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            //getEvents();
            senddata();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
//getEvents();
        
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

    @FXML
    private void ajouterrendezvous(ActionEvent event) throws SQLException {
        
        
        
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
        try {
            SR.add(r);
            reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
//        getEvents();
         senddata();
        
    }}
     private void reset() {
        nomtf.setText("");
        prenomtf.setText("");
        usertf.setText("");
        emailtf.setText("");
        lieutf.setText("");
        datetf.setValue(null);    
    }



    @FXML
    private void modifierrendezvous(ActionEvent event) {
        
        
          Rendezvous e = new Rendezvous();
        e.setIdRendezvous(Integer.valueOf(idmodifierField.getText()));
        e.setNomRendezvous(nomtf.getText());
        e.setPrenomRendezvous(prenomtf.getText());
        e.setEmailRendezvous(emailtf.getText()); 
        Date d=Date.valueOf(datetf.getValue());
        e.setDateRendezvous(d);
        e.setLieuRendezvous(lieutf.getText());
        e.setUser_id(Integer.valueOf(usertf.getText()));
        SR.modifier(e);
        reset();
        getEvents();        
    }

    @FXML
    private void supprimerrendezvous(ActionEvent event) throws SQLException {
        
        
          Rendezvous e = tableRendez.getItems().get(tableRendez.getSelectionModel().getSelectedIndex());
        try {
            SR.supprimerRendezVous(e);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();        
        //getEvents();   
       senddata();
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
    public void getEvents() {  
         try {
            // TODO
            List<Rendezvous> rendezvouss = SR.recupererrendezVous();
            ObservableList<Rendezvous> olp = FXCollections.observableArrayList(rendezvouss);
            tableRendez.setItems(olp);
            nomTV.setCellValueFactory(new PropertyValueFactory("nom_rendezvous"));
            prenomTV.setCellValueFactory(new PropertyValueFactory("prenom_rendezvous"));
            emailTv.setCellValueFactory(new PropertyValueFactory("email_rendezvous"));
            lieuTV.setCellValueFactory(new PropertyValueFactory("lieu_rendezvous"));
            useridTV.setCellValueFactory(new PropertyValueFactory("user_id"));
            dateTV.setCellValueFactory(new PropertyValueFactory("date_rendezvous"));
            
           // this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }//get events

    @FXML
    private void choisirRendezvous(MouseEvent event) {
        
          Rendezvous e = tableRendez.getItems().get(tableRendez.getSelectionModel().getSelectedIndex());     
        //idLabel.setText(String.valueOf(e.getId_event()));
        idmodifierField.setText(String.valueOf(e.getIdRendezvous()));
        nomtf.setText(e.getNomRendezvous());
        prenomtf.setText(e.getPrenomRendezvous());
        emailtf.setText(e.getEmailRendezvous());
        lieutf.setText(e.getLieuRendezvous());
        //dateEventField.setValue((e.getDate()));
        usertf.setText(String.valueOf(e.getUser_id()));
         
 
    }

    
}
