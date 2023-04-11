/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Controller.UserController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.util.converter.FloatStringConverter;
import model.User;




/**
 * FXML Controller class
 *
 * @author Seif
 */
public class UserFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
     ObservableList<String> roley =FXCollections.observableArrayList("Type","admin","user");
     UserController ps = new UserController();
    
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

    @FXML
    private TableColumn<User, String> tve;

    @FXML
    private TableColumn<User, Integer> tvi;

    @FXML
    private TableColumn<User, String> tvn;

    @FXML
    private TableColumn<User, String> tvp;

    @FXML
    private TableColumn<User, String> tvr;

    @FXML
    private TableColumn<User, Integer> tvt;
    @FXML
    private TableView<User> tableprod;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // roleu.setItems((ObservableList<String>) roleu);
        //System.out.println(roleu.getSelectionModel().getSelectedIndex());
       roleu.setItems(roley);
       tableprod.setEditable(true);
        afficher();
    }    
    
    
        public void afficher()
{
     tvi.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
    tvn.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
    tvn.setEditable(true);
    tvn.setCellFactory(TextFieldTableCell.forTableColumn());
    tve.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
    tve.setEditable(true);
    tve.setCellFactory(TextFieldTableCell.forTableColumn());
    tvp.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
    tvp.setEditable(true);
    tvp.setCellFactory(TextFieldTableCell.forTableColumn()); 
    tvr.setCellValueFactory(new PropertyValueFactory<User,String>("roles"));
    tvr.setEditable(true);
    tvr.setCellFactory(TextFieldTableCell.forTableColumn());
    tvt.setCellValueFactory(new PropertyValueFactory<User,Integer>("telephone"));
    tvt.setEditable(true);
    
    
 
    tableprod.setItems(FXCollections.observableArrayList( ps.afficherUser()));
    ObservableList<User> oList = FXCollections.observableArrayList(ps.afficherUser());
        FilteredList<User> filteredData = new FilteredList<User>(oList, b -> true);
        
        SortedList<User> sortedList = new SortedList <User>(filteredData);
        sortedList.comparatorProperty().bind(tableprod.comparatorProperty())    ;
        tableprod.setItems(sortedList);
    
    
}
        
        
        
    @FXML
    void onActionadd(ActionEvent event) {
        
         if(nomu.getText().isEmpty()  || emailu.getText().isEmpty() || passwordu.getText().isEmpty() ||telephoneu.getText().isEmpty() || (roleu.getSelectionModel().getSelectedIndex())==-1)
        {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
       
        
            //float x = parseFloat(prixp.getText());
            //User u = new User(nomu.getText(),emailu.getText(),"xxxx",passwordu.getText(),Integer.parseInt(telephoneu.getText()),1);
            User u = new User(emailu.getText(), roley.get(roleu.getSelectionModel().getSelectedIndex()), passwordu.getText(), Integer.parseInt(telephoneu.getText()), nomu.getText());
            System.out.println(u);
            ps.ajouterUser(u);
            afficher();
            
            
                }
        
    
    @FXML
    void onActionsupp(ActionEvent event) {
         User u = tableprod.getSelectionModel().getSelectedItem();
        ps.supprimerUser(u.getId());
        afficher();
    }

    @FXML
    private void nommu(TableColumn.CellEditEvent<User, String> event) {
        User F = tableprod.getSelectionModel().getSelectedItem();
         F.setName(event.getNewValue());
         ps.modifierU(F);
    }
                
    
}
