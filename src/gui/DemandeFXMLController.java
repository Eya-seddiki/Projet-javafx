/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import java.util.Random;
import javafx.stage.FileChooser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.util.StringConverter;
import model.Demande;
import model.Offre;
import services.DemandeServices;
import services.OffreServices;

/**
 * FXML Controller class
 *
 * @author dell
 */

public class DemandeFXMLController implements Initializable {
    
         DemandeServices s = new DemandeServices();
         OffreServices ps = new OffreServices();
         ObservableList<String> nomooffery =FXCollections.observableArrayList();

     @FXML
    private TableColumn<Demande, String> cvd;
     @FXML
    private ImageView cview;
    @FXML
    private Button cvdemande;

    @FXML
    private TableColumn<Demande, String> descrid;

    @FXML
    private TextField descriptiondemande;

    @FXML
    private TableColumn<Demande, Integer> idd;

    @FXML
    private TableColumn<Demande, Integer> nomd;

    @FXML
    private ComboBox<Offre> nomoffred;
    
     


    @FXML
    private TextField pdff;

    @FXML
    private TableView<Demande> tableviewdemande;

    @FXML
    private TableColumn<Demande, String> traietmentd;
    @FXML
    private TableColumn<Demande, Integer> maild;
    @FXML
    private TextField maildd;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Offre> listoffre = ps.afficherOffre();
        
        nomoffred.setItems(FXCollections.observableArrayList(listoffre));
		nomoffred.setConverter(new StringConverter<Offre>() {
			@Override
			public String toString(Offre object) {
				return object.getNom_offre();
			}

			@Override
			public Offre fromString(String string) {
				return nomoffred.getItems().stream().filter(a -> a.getNom_offre().equals(string)).findFirst().orElse(null);
			}
		});
                
                senddata();
                        tableviewdemande.setEditable(true);

        
        }
    

    

    /**
     * Initializes the controller class.
     */
    

    public void senddata()
{
   idd.setCellValueFactory(new PropertyValueFactory<Demande, Integer>("id_demande"));
       //idd.setCellFactory(TextFieldTableCell.forTableColumn());	
    nomd.setCellValueFactory(new PropertyValueFactory<Demande,Integer>("id_offre"));
    nomd.setEditable(true);
      maild.setCellValueFactory(new PropertyValueFactory<Demande,Integer>("id_user"));
    maild.setEditable(true);
    //nomd.setCellFactory(TextFieldTableCell.forTableColumn());	
    cvd.setCellValueFactory(new PropertyValueFactory<Demande,String>("cv"));
    cvd.setEditable(true);
    cvd.setCellFactory(TextFieldTableCell.forTableColumn());
    descrid.setCellValueFactory(new PropertyValueFactory<Demande,String>("description"));
    descrid.setEditable(true);
    descrid.setCellFactory(TextFieldTableCell.forTableColumn()); 
    traietmentd.setCellValueFactory(new PropertyValueFactory<Demande,String>("traitement"));
    traietmentd.setEditable(true);
    traietmentd.setCellFactory(TextFieldTableCell.forTableColumn());
    
    
    
 
    
   tableviewdemande.setItems(FXCollections.observableArrayList( s.afficherDemande()));
    ObservableList<Demande> oList = FXCollections.observableArrayList(s.afficherDemande());
        FilteredList<Demande> filteredData = new FilteredList<Demande>(oList, b -> true);
        
        SortedList<Demande> sortedList = new SortedList <Demande>(filteredData);
        sortedList.comparatorProperty().bind(tableviewdemande.comparatorProperty())    ;
        tableviewdemande.setItems(sortedList);
    
}


    

    @FXML
    void ajouterdemande(ActionEvent event) {
        if ((pdff.getText().isEmpty()) || (descriptiondemande.getText().isEmpty() || (nomoffred.getSelectionModel().getSelectedIndex()==-1))){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        
        Demande d= new Demande(nomoffred.getSelectionModel().getSelectedItem().getId_offre(), pdff.getText(), descriptiondemande.getText());
           System.out.println(d);
           s.ajouterDemande(d);
           senddata();
        }

    
        @FXML
    void supprimerdemande(ActionEvent event) {
Demande f = tableviewdemande.getSelectionModel().getSelectedItem();
        s.supprimerDemande(f.getId_demande());
        senddata();
    }
    
    
   
    
    @FXML
    void uploadpdf(ActionEvent event)throws FileNotFoundException, IOException {
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
            cview.setImage(img);    
            pdff.setText(DBPath);
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
      void on(ActionEvent event) {

    }

   

    @FXML
    private void choisirEvent(javafx.scene.input.MouseEvent event) {
        Demande e = tableviewdemande.getItems().get(tableviewdemande.getSelectionModel().getSelectedIndex());     

      
        pdff.setText(e.getCv());
        
      
        descriptiondemande.setText(e.getDescription());
        
        maildd.setText(String.valueOf(e.getId_user())); 
        ///////lel image
        String path = e.getCv();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                cview.setImage(img);
    }

    @FXML
    private void ondescriptioncommit(TableColumn.CellEditEvent<Demande, String> event) {
        Demande a = tableviewdemande.getSelectionModel().getSelectedItem();
         a.setDescription(event.getNewValue());
         s.modifierdemande(a);
    }
    
}
    


