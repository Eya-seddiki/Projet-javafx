/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.WriterException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.util.StringConverter;
import model.Demande;
import model.Offre;
import model.User;
import services.DemandeServices;
import services.OffreServices;
import services.PDFGenerator;
import services.UserServices;
import services.mail;

/**
 * FXML Controller class
 *
 * @author dell
 */

public class DemandeFXMLController implements Initializable {
    
         DemandeServices s = new DemandeServices();
         UserServices U = new UserServices();
         OffreServices ps = new OffreServices();
         PDFGenerator pdf = new PDFGenerator();
         ObservableList<String> nomooffery =FXCollections.observableArrayList();
         mail maill = new mail();

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
    private TableColumn<Demande, String> nomd;

    @FXML
    private ComboBox<Offre> nomoffred;
    
     


    @FXML
    private TextField pdff;

    @FXML
    private TableView<Demande> tableviewdemande;

    @FXML
    private TableColumn<Demande, String> traietmentd;
    @FXML
    private TableColumn<Demande, String> maild;
      @FXML
    private ComboBox<User> maildd;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Offre> listoffre = ps.afficherOffre();
          List<User> listuser = U.afficherUser();
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
               
        
        maildd.setItems(FXCollections.observableArrayList(listuser));
		maildd.setConverter(new StringConverter<User>() {
			@Override
			public String toString(User object) {
				return object.getEmail();
			}

			@Override
			public User fromString(String string) {
				return maildd.getItems().stream().filter(a -> a.getEmail().equals(string)).findFirst().orElse(null);
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
    //nomd.setCellValueFactory(new PropertyValueFactory<Demande,Integer>("id_offre"));
    //nomd.setEditable(true);
     // maild.setCellValueFactory(new PropertyValueFactory<Demande,Integer>("id_user"));
    //maild.setEditable(true);
    ///////////////////
   nomd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Demande, String>, ObservableValue<String>>() {
       @Override
       public ObservableValue<String> call(TableColumn.CellDataFeatures<Demande, String> param) {
          				return (param.getValue().getId_offre() != 0)
						? new SimpleStringProperty(ps.findoffre(param.getValue().getId_offre()).getNom_offre())
						: null;
           //To change body of generated methods, choose Tools | Templates.
       }

			
		});
    
    maild.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Demande, String>, ObservableValue<String>>() {
       @Override
       public ObservableValue<String> call(TableColumn.CellDataFeatures<Demande, String> param) {
          				return (param.getValue().getId_user() != 0)
						? new SimpleStringProperty(U.finduser(param.getValue().getId_user()).getEmail())
                                                : null;
           //To change body of generated methods, choose Tools | Templates.
       }

			
		});
    ////////////////////////
    //nomoffred.getSelectionModel().getSelectedItem().getId_offre()
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
        
        
        ////////
       

    
}


    

    @FXML
    void ajouterdemande(ActionEvent event) {
        if ((pdff.getText().isEmpty()) || (descriptiondemande.getText().isEmpty() || (nomoffred.getSelectionModel().getSelectedIndex()==-1) ||(maildd.getSelectionModel().getSelectedIndex()==-1) )){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        
        Demande d= new Demande(nomoffred.getSelectionModel().getSelectedItem().getId_offre(),maildd.getSelectionModel().getSelectedItem().getId(), pdff.getText(), descriptiondemande.getText());
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
        
      //  maildd.setText(String.valueOf(e.getId_user())); 
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
      @FXML
    void générerpdf(ActionEvent event) {
        if(tableviewdemande.getSelectionModel().getSelectedItem()!= null){
        Demande P = tableviewdemande.getSelectionModel().getSelectedItem();
        
            try {
                try {
					pdf.GeneratePdf("Produit", P);
				} catch (BadElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } catch (IOException ex) {
                Logger.getLogger(DemandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(DemandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DemandeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
}

    }
  @FXML
    void envoyermail(ActionEvent event) throws SQLException, WriterException, IOException {
        Demande d = new Demande();
         Demande ddd = tableviewdemande.getSelectionModel().getSelectedItem();
         int idd = ddd.getId_user();
         String mailutil = U.finduser(idd).getEmail();
          int idoff = ddd.getId_offre();
         String nomoffre = ps.findoffre(idoff).getNom_offre();
        String Information = "id de l'offre : "+ddd.getId_offre()+"\n"+"nom de l'offre  : "+nomoffre+"\n"+"votre mail est   : "+mailutil+"votre demande est   : "+ddd.getTraitement();
      
        Demande a;
       
            
    

 Demande dd = tableviewdemande.getItems().get(tableviewdemande.getSelectionModel().getSelectedIndex()); 
 int id = dd.getId_user();

            ////////////////////////
         // mailutilsateur= maildd.getSelectionModel().getSelectedItem().getEmail();
         String asma = U.finduser(id).getEmail();
          //String mailutilsateur = paysMap.get(id);
       
        maill.sendMail(asma,  mailutil , Information);
        String trait = dd.getTraitement();
        
        dd.setTraitement("traite");
        s.modifierdemande(dd);
        traietmentd.setCellValueFactory(new PropertyValueFactory<Demande,String>("traitement"));
    traietmentd.setEditable(true);
    traietmentd.setCellFactory(TextFieldTableCell.forTableColumn());
        
    }
    @FXML
    void statistique(ActionEvent event) {
// Create a map to store the frequency of each type
    Map<String, Integer> typeFrequency = new HashMap<>();

    // Loop through the items in the TableView
    for (Demande eventt : tableviewdemande.getItems()) {
        String type = eventt.getTraitement();
        if (typeFrequency.containsKey(type)) {
            typeFrequency.put(type, typeFrequency.get(type) + 1);
        } else {
            typeFrequency.put(type, 1);
        }
    }

    // Create a PieChart data set
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (String type : typeFrequency.keySet()) {
        int frequency = typeFrequency.get(type);
        double percentage = (double) frequency / tableviewdemande.getItems().size() * 100;
        String percentageText = String.format("%.2f%%", percentage);
        PieChart.Data slice = new PieChart.Data(type + " " + percentageText, frequency);
        pieChartData.add(slice);
    }

    // Create a PieChart with the data set
    PieChart chart = new PieChart(pieChartData);

    // Show percentage values in the chart's tooltip
    for (final PieChart.Data data : chart.getData()) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(String.format("%.2f%%", (data.getPieValue() / tableviewdemande.getItems().size() * 100)));
        Tooltip.install(data.getNode(), tooltip);
    }

    // Show the chart in a new window
    Scene scene = new Scene(chart);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();

}
}
    


