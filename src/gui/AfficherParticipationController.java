/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.evenement;
import entities.participation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import services.ParticipationService;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import services.EvenementService;




//////////////////

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
    private TableColumn<participation, String> ideventTv;
    @FXML
    private TableColumn<participation, String> datePartTv;
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
    EvenementService Ev= new EvenementService();
    
    ParticipationService ps= new ParticipationService();
    
    ParticipationService Ps=new ParticipationService();
    @FXML
    private TextField datepartField1;
    @FXML
    private TextField idEventFIND;
    @FXML
    private Button ExportPDF;

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
            ideventTv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<participation, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<participation, String> param) {
                    
                  
                return (param.getValue().getEvenement_id()!= 0)
						? new SimpleStringProperty(Ev.findEvenement(param.getValue().getEvenement_id()).getNom_evenement())
						: null;
                }
            });
          //  ideventTv.setCellValueFactory(new PropertyValueFactory("evenement_id"));
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

    @FXML
    private void ExportPDF(ActionEvent event)throws FileNotFoundException, SQLException, IOException, BadElementException, DocumentException {
      
        
        /*
           // Se connecter à la base de données
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/integration", "root", "");
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM participation");

    // Créer un nouveau document PDF
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("output.pdf"));
    document.open();

    // Ajouter une image au document
    Image image = Image.getInstance("logo2.png");
    image.setAlignment(Element.ALIGN_CENTER);
    document.add(image);

    // Ajouter les données au document
    while (resultSet.next()) {
        String data1 = resultSet.getString("user_id");
        String data2 = resultSet.getString("evenement_id");
        String data3 = resultSet.getString("date_participation");
        String data4 = resultSet.getString("description_participation");
        Paragraph paragraph = new Paragraph(data1 + " - " + data2 + " - " + data3 + " - " + data4);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
    }

    // Fermer le document et la connexion à la base de données
    document.close();
    connection.close();
    
 */
    
        
       // evenement tab_Recselected = evenementTv.getSelectionModel().getSelectedItem();
       
               long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("Date d'aujourdhui : " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Voici un rapport détaillé de notre application qui contient tous les Participants . Pour chaque participant, nous fournissons des informations telles que la date d'aujourdhui :" + DateRapport );
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(4);
            //On créer l'objet cellule.
            PdfPCell cell;
            //contenu du tableau.
            table.addCell("user_id");
            table.addCell("evenement_id");
            table.addCell("date_participation");
            table.addCell("description_participation");
            participation r = new participation();
            ps.recupererParticipation().forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getUser_id())); 
                table.addCell(String.valueOf(e.getEvenement_id()));    
                table.addCell(String.valueOf(e.getDate_participation()));
                table.addCell(String.valueOf(e.getDescription_participation()));
            }
            );
            
            Image img = Image.getInstance("C:\\xampp2\\htdocs\\PI\\Evenement\\logo.png");

       img.scaleAbsoluteHeight(80);
       img.scaleAbsoluteWidth(130);
       img.setAlignment(Image.ALIGN_CENTER);
       document.add(img);
            document.add(ph1);
            document.add(ph2);
            document.add(table);
             } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
        File file = new File(DateLyoum + ".pdf");
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) //checks file exists or not  
        {
            desktop.open(file); //opens the specified file   
        }
    }
    
    
    
    
}
