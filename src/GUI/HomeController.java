/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Rendezvous;
import Services.SMSSender;
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
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.mail.MessagingException;
import Services.SendMail;
import javafx.scene.input.KeyEvent;



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
    @FXML
    private Button btnfacture;
    @FXML
    private Button excelbtn;
    @FXML
    private Button musicButton;
    @FXML
    private Button pauseMusicButton;
    @FXML
    private ImageView QrCode;
    @FXML
    private Button map;
    @FXML
    private TextField searchField;


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
    
    
           String path = "C:\\xampp\\htdocs\\music\\Alok & Alan Walker.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    
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
    private void ajouterrendezvous(ActionEvent event) throws SQLException, MessagingException {
        
        
        
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
            SMSSender SS = new SMSSender() ; 
        SS.SMSSender();
           // SendMail.sendMail("ala.chebil@esprit.tn");
           
            
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
    private void modifierrendezvous(ActionEvent event) throws SQLException {
        
        
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
//        getEvents(); 
         senddata();
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
    private void choisirRendezvous(MouseEvent event) throws IOException {
        
          Rendezvous e = tableRendez.getItems().get(tableRendez.getSelectionModel().getSelectedIndex());     
        //idLabel.setText(String.valueOf(e.getId_event()));
        idmodifierField.setText(String.valueOf(e.getIdRendezvous()));
        nomtf.setText(e.getNomRendezvous());
        prenomtf.setText(e.getPrenomRendezvous());
        emailtf.setText(e.getEmailRendezvous());
        lieutf.setText(e.getLieuRendezvous());
        //dateEventField.setValue((e.getDate()));
        usertf.setText(String.valueOf(e.getUser_id()));
        
        // lel qr code 
        
              String filename = SR.GenerateQrEvent(e);
            System.out.println("filename lenaaa " + filename);
            String path1="C:\\Users\\HP\\Desktop\\semestre 2\\java\\imgQr\\qrcode"+filename;
             File file1=new File(path1);
              Image img1 = new Image(file1.toURI().toString());
              //Image image = new Image(getClass().getResourceAsStream("src/utils/img/" + filename));
            QrCode.setImage(img1);  
         
 
    }

    @FXML
    private void facture(ActionEvent event) {
                       try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("FactureFXML.fxml"));
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

    @FXML
    private void excelrendez(ActionEvent event) {
        try{
String filename="C:\\Users\\HP\\Desktop\\semestre 2\\java\\fichierExcel\\dataEvent.xls" ;
    HSSFWorkbook hwb=new HSSFWorkbook();
    HSSFSheet sheet =  hwb.createSheet("new sheet");
    HSSFRow rowhead=   sheet.createRow((short)0);
rowhead.createCell((short) 0).setCellValue("nom ");
rowhead.createCell((short) 1).setCellValue("prenom");
rowhead.createCell((short) 2).setCellValue("lieu ");
List<Rendezvous> evenements = SR.recupererrendezVous();
  for (int i = 0; i < evenements.size(); i++) {          
HSSFRow row=   sheet.createRow((short)i);
row.createCell((short) 0).setCellValue(evenements.get(i).getNomRendezvous());
row.createCell((short) 1).setCellValue(evenements.get(i).getPrenomRendezvous());
row.createCell((short) 2).setCellValue(evenements.get(i).getDateRendezvous());
row.createCell((short) 3).setCellValue(evenements.get(i).getLieuRendezvous());
//row.createCell((short) 3).setCellValue((evenements.get(i).getDate()));
i++;
            }
int i=1;
    FileOutputStream fileOut =  new FileOutputStream(filename);
hwb.write(fileOut);
fileOut.close();
System.out.println("Your excel file has been generated!");
 File file = new File(filename);
        if (file.exists()){ 
        if(Desktop.isDesktopSupported()){
            Desktop.getDesktop().open(file);
        }}       
} catch ( Exception ex ) {
    System.out.println(ex);
}
        
    }

    @FXML
    private void pdfEvent(ActionEvent event) throws IOException {
        
          // evenement tab_Recselected = evenementTv.getSelectionModel().getSelectedItem();
               long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(DateRapport);//yyyyMMddHHmmss
        System.out.println("Date d'aujourdhui : " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            
            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
            document.open();
            Paragraph ph1 = new Paragraph("Voici un rapport détaillé de notre application qui contient tous les RENDEZ VOUS . Pour chaque rendez-vous, nous fournissons des informations telles que la date d'aujourdhui :" + DateRapport );
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(4);
            //On créer l'objet cellule.
            PdfPCell cell;
            //contenu du tableau.
            table.addCell("nom_RENDEZVOUS");
            table.addCell("prenom");
            table.addCell("lieu");
            table.addCell("date");
            Rendezvous r = new Rendezvous();
            SR.recupererrendezVous().forEach(e
                    -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getNomRendezvous()));
                table.addCell(String.valueOf(e.getPrenomRendezvous()));
                table.addCell(String.valueOf(e.getLieuRendezvous())); 
                table.addCell(String.valueOf(e.getDateRendezvous())); 
            }
            );
            
//            Image img = Image.getInstance("C:\\Users\\msi\\Desktop\\projet yocef\\reclamation\\src\\com\\img\\Exchange.png12.png");
//       img.scaleAbsoluteHeight(60);
//       img.scaleAbsoluteWidth(100);
//       img.setAlignment(Image.ALIGN_RIGHT);
//       document.add(img);
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

    @FXML
    private void playMusic(ActionEvent event) {
        
           mediaPlayer.play();
       // Image img = new Image("C:\\xampp\\htdocs\\music\\ala.jpg");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Musique Jouée");
//         Notifications notificationBuilder = Notifications.create()
//                .title("Musique")
//                .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void pauseMusic(ActionEvent event) {
        
         mediaPlayer.pause();
        //Image img = new Image("fllogo.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Musique")
                .text("      Musique Arrêtée");
        notificationBuilder.darkStyle();
        notificationBuilder.show();
    }

    @FXML
    private void map(ActionEvent event) throws IOException {

    Parent previousScene = FXMLLoader.load(getClass().getResource("Map.fxml"));
    Scene scene = new Scene(previousScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    
    }
    public void setlocal(String ad){
        this.lieutf.setText(ad);
    }

    @FXML
    private void search(KeyEvent event) {
        
        
        // TODO
        
        List<Rendezvous> rendezvouss = SR.chercherEvent(searchField.getText());
        ObservableList<Rendezvous> olp = FXCollections.observableArrayList(rendezvouss);
        tableRendez.setItems(olp);
        nomTV.setCellValueFactory(new PropertyValueFactory("nom_rendezvous"));
        prenomTV.setCellValueFactory(new PropertyValueFactory("prenom_rendezvous"));
        emailTv.setCellValueFactory(new PropertyValueFactory("email_rendezvous"));
        lieuTV.setCellValueFactory(new PropertyValueFactory("lieu_rendezvous"));
        useridTV.setCellValueFactory(new PropertyValueFactory("user_id"));
        dateTV.setCellValueFactory(new PropertyValueFactory("date_rendezvous"));
        
        // this.delete();
    }//get events

        
        
        
    }
    

    

