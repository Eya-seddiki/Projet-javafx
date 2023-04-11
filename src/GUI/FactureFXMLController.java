/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Facture;
import Entities.Rendezvous;
import Services.ServiceFacture;
import Services.ServiceRendezvous;
import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FactureFXMLController implements Initializable {
    ServiceFacture ps = new ServiceFacture();
      ServiceRendezvous S = new ServiceRendezvous();

    @FXML
    private TextField sciencetf;
    @FXML
    private TextField rendezvousidtf;
    @FXML
    private TextField paiementtf;
    @FXML
    private TableView<Facture> tableprod;
    @FXML
    private TableColumn<Facture, Integer> idf;
    @FXML
    private Button btnfacajou;
    @FXML
    private TableColumn<Facture, Integer> nbf;
    @FXML
    private TableColumn<Facture, String> typef;
    @FXML
    private TableColumn<Facture, Integer> rendezvousf;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private TextField idmodifierField;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
      @FXML
    private ComboBox<Rendezvous> combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Rendezvous> listoffre = S.afficher();
            
            combo.setItems(FXCollections.observableArrayList(listoffre));
            combo.setConverter(new StringConverter<Rendezvous>() {
                @Override
                public String toString(Rendezvous object) {
                    return object.getNomRendezvous();
                }
                
                @Override
                public Rendezvous fromString(String string) {
                    return combo.getItems().stream().filter(a -> a.getNomRendezvous().equals(string)).findFirst().orElse(null);
                }
            });
            
            try {
                getEvents();
            } catch (SQLException ex) {
                Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FactureFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    
//    public void senddata() throws SQLException
//{
//
//    idf.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("id"));
//    
//    nbf.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("nb_science"));
//    
//    typef.setCellValueFactory(new PropertyValueFactory<Facture,String>("type_de_paiement"));
//    typef.setEditable(true);
//    typef.setCellFactory(TextFieldTableCell.forTableColumn());
//    
//    rendezvousf.setCellValueFactory(new PropertyValueFactory<Facture,Integer>("rendezvous_id"));
//    
//    
//    tableprod.setItems(FXCollections.observableArrayList(ps.afficher()));
//    ObservableList<Facture> oList = FXCollections.observableArrayList(ps.afficher());
//        FilteredList<Facture> filteredData = new FilteredList<Facture>(oList, b -> true);
//        
//        SortedList<Facture> sortedList = new SortedList <Facture>(filteredData);
//        sortedList.comparatorProperty().bind(tableprod.comparatorProperty())    ;
//        tableprod.setItems(sortedList);
//    
//}
    
//
    public void getEvents() throws SQLException  {  
        // TODO
        List<Facture> Factures = ps.recupererfacture(); // this.delete();
        ObservableList<Facture> olp = FXCollections.observableArrayList(Factures);
        tableprod.setItems(olp);
        idf.setCellValueFactory(new PropertyValueFactory("id"));
        nbf.setCellValueFactory(new PropertyValueFactory("nb_science"));
        typef.setCellValueFactory(new PropertyValueFactory("type_de_paiement"));
        rendezvousf.setCellValueFactory(new PropertyValueFactory("rendezvous_id"));
          ObservableList<Facture> oList = FXCollections.observableArrayList(ps.recupererfacture());
       FilteredList<Facture> filteredData = new FilteredList<Facture>(oList, b -> true);
        
        SortedList<Facture> sortedList = new SortedList <Facture>(filteredData);
        sortedList.comparatorProperty().bind(tableprod.comparatorProperty())    ;
        tableprod.setItems(sortedList);
    }//get events
    @FXML
    private void ajouterfacture(ActionEvent event) throws SQLException {
        
       /*  int part=0;
        if ((sciencetf.getText().length() == 0) || (rendezvousidtf.getText().length() == 0) || (paiementtf.getText().length() == 0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText("Error!");
            alert.setContentText("Fields cannot be empty");
            alert.showAndWait();
        }

       
            else
            {
        Facture r = new Facture();
       r.setNb_science(Integer.valueOf(sciencetf.getText()));
       r.setRendezvous_id(Integer.valueOf(rendezvousidtf.getText()));
        r.setType_de_paiement(paiementtf.getText());

        
        //lel image
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information ");
            alert.setHeaderText("facture add");
            alert.setContentText("Event added successfully!");
            alert.showAndWait();      
            ps.add(r);
            reset();
//        getEvents();
           // senddata();
        
    }}
     private void reset() {
        sciencetf.setText("");
        rendezvousidtf.setText("");
        paiementtf.setText("");*/
   if ((sciencetf.getText().isEmpty()) || (paiementtf.getText().isEmpty() || (combo.getSelectionModel().getSelectedIndex()==-1))){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        
        Facture d= new Facture( Integer.valueOf(sciencetf.getText()), paiementtf.getText(),combo.getSelectionModel().getSelectedItem().getIdRendezvous());
           System.out.println(d);
           ps.add(d);;
         getEvents();
    }
     
    @FXML
    private void modifierfacture(ActionEvent event) throws SQLException {
            
          Facture e = new Facture();
        e.setId(Integer.valueOf(idmodifierField.getText()));
        e.setNb_science(Integer.valueOf(sciencetf.getText()));
        e.setType_de_paiement(paiementtf.getText());
//        e.setRendezvous_id(Integer.valueOf(rendezvousidtf.getText())); 
            
        ps.modifierFacture(e);
       reset();
        getEvents();
        
    }

    @FXML
    private void supprimerfacture(ActionEvent event) throws SQLException {
                  Facture e = tableprod.getItems().get(tableprod.getSelectionModel().getSelectedIndex());
        try {
            ps.supprimerfacture(e);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText("Event delete");
        alert.setContentText("Event deleted successfully!");
        alert.showAndWait();        
      getEvents(); 
        
    }
    
  
    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void choisirfacture(MouseEvent event) {
         Facture e = tableprod.getItems().get(tableprod.getSelectionModel().getSelectedIndex());     
        //idLabel.setText(String.valueOf(e.getId_event()));
        idmodifierField.setText(String.valueOf(e.getId()));
        paiementtf.setText(e.getType_de_paiement());
        sciencetf.setText(String.valueOf(e.getNb_science()));
        //rendezvousidtf.setText(String.valueOf(e.getRendezvous_id()));
        
    }


    
    
}
