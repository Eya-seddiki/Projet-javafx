/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import static com.barcodelib.barcode.a.f.c.x;
import com.itextpdf.text.Document;
import com.google.zxing.WriterException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.util.StringConverter;
import javax.tools.FileObject;
import model.Demande;
import model.Offre;
import model.User;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.controlsfx.control.Notifications;
import services.DemandeServices;
import services.OffreServices;
import services.PDFGenerator;
import services.UserServices;
import services.cv;
import services.mail;

/**
 * FXML Controller class
 *
 * @author dell
 */

public class DemandeFXMLController implements Initializable {
    private PDDocument document;
    private PDFRenderer renderer;
         DemandeServices s = new DemandeServices();
         UserServices U = new UserServices();
         OffreServices ps = new OffreServices();
         PDFGenerator pdf = new PDFGenerator();
         cv cv1 = new cv();
         ObservableList<String> nomooffery =FXCollections.observableArrayList();
         mail maill = new mail();

     @FXML
    private TableColumn<Demande, String> cvd;
      @FXML
    private TableColumn<File, Button> bouton;

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
    

private void uploadimg(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (.JPG)", ".JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (.jpg)", ".jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (.PNG)", ".PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (.png)", ".png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

         File f = fileChooser.showOpenDialog(new Stage());

         cvd.setText(f.getAbsoluteFile().toURI().toString());
    }
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
    //////////////////////////////////////
    /* TableColumn<File, Button> buttonColumn = new TableColumn<>("Ouvrir le fichier");
             TableColumn<File, Button> setCellValueFactory = buttonColumn.setCellValueFactory(cellData -> {
                 File file = cellData.getValue();
                 Button button = new Button("Ouvrir");
                 button.setOnAction(event -> {
                     try {
                         Desktop.getDesktop().open(file);
                     } catch (IOException e) {
                         e.printStackTrace();
                     }
                 });
                 return new javafx.scene.control.TableCell<File, Button>() {
                     @Override
                     protected void updateItem(Button item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty || item == null) {
                             setGraphic(null);
                         } else {
                             setGraphic(item);
                         }
                     }
                 };
             });

        // Ajouter la colonne de boutons à la TableView
        tableviewdemande.getColumns().add(boutton);

        // Ajouter des fichiers à la TableView
        data.addAll(new File("path/to/directory").listFiles());*/
   /////////////////////////////////////
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
    private void ondescriptioncommit(TableColumn.CellEditEvent<Demande, String> event) {
        Demande a = tableviewdemande.getSelectionModel().getSelectedItem();
         a.setDescription(event.getNewValue());
         s.modifierdemande(a);
    }

    @FXML
    private void choisirdemande(javafx.scene.input.MouseEvent event) {
        Demande e = tableviewdemande.getItems().get(tableviewdemande.getSelectionModel().getSelectedIndex());

      /*
        pdff.setText(e.getCv());
        
      
        descriptiondemande.setText(e.getDescription());
        
      //  maildd.setText(String.valueOf(e.getId_user())); 
        ///////lel image
        String path = e.getCv();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                cview.setImage(img);*/
      
      try {
           //Demande e = tableviewdemande.getItems().get(tableviewdemande.getSelectionModel().getSelectedIndex());     

      
        
      
        
        
      //  maildd.setText(String.valueOf(e.getId_user())); 
        ///////lel image
       String path = e.getCv();
       // String DBPath = "C:\\xampp\\htdocs\\ImageP\\"  + x + ".pdf";
 //File file = new File("C:\\\\xampp\\\\htdocs\\\\ImageP\\\\68.pdf");

            // Load the PDF document
 //File file = new File(path);

            File file = new File(path);
            document = Loader.loadPDF(file);

            // Create a PDF renderer
            renderer = new PDFRenderer(document);

            // Render the first page
            PDPage page = document.getPage(0);

          BufferedImage image = renderer.renderImageWithDPI(0, 600, ImageType.RGB);


            // Convert the buffered image to a JavaFX image
            Image fxImage = SwingFXUtils.toFXImage(image, null);

            // Set the JavaFX image to the image view
            cview.setImage(fxImage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    
    private static class CustomTableCell<T> extends TableCell<File, File> {
        private final ImageView cview;
        private final Label label;

        public CustomTableCell() {
            cview = new ImageView();
            cview.setFitHeight(20);
            cview.setFitWidth(20);

            label = new Label();
            setGraphic(new VBox(cview, label));
        }

        protected void updateItem(File item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setGraphic(null);
            } else {
                label.setText(item.getName());
                try {
                    cview.setImage(new Image(new File("pdf-icon.png").toURI().toURL().toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
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
          private void choisirdemande(MouseEvent event) {

    
    //lel image
    Demande e = tableviewdemande.getItems().get(tableviewdemande.getSelectionModel().getSelectedIndex());

      
        pdff.setText(e.getCv());
        
      
        descriptiondemande.setText(e.getDescription());
        
      //  maildd.setText(String.valueOf(e.getId_user())); 
        ///////lel image
        String path = e.getCv();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                cview.setImage(img);
    
    /*
        pdff.setText(e.getCv());
        /////////
        String path = e.getCv();
               File file=new File(path);
              Image img = new Image(file.toURI().toString());
                cview.setImage(img);
        //////////////*/
        /*
try {
           //Demande e = tableviewdemande.getItems().get(tableviewdemande.getSelectionModel().getSelectedIndex());     

      
        
      
        
        
      //  maildd.setText(String.valueOf(e.getId_user())); 
        ///////lel image
      //  String path = e.getCv();
        String DBPath = "C:\\xampp\\htdocs\\ImageP\\"  + x + ".pdf";
 //File file = new File("C:\\\\xampp\\\\htdocs\\\\ImageP\\\\68.pdf");

            // Load the PDF document
 //File file = new File(path);

            //File file = new File(path);
            document = Loader.loadPDF(file);

            // Create a PDF renderer
            renderer = new PDFRenderer(document);

            // Render the first page
            PDPage page = document.getPage(0);

          BufferedImage image = renderer.renderImageWithDPI(0, 600, ImageType.RGB);


            // Convert the buffered image to a JavaFX image
            Image fxImage = SwingFXUtils.toFXImage(image, null);

            // Set the JavaFX image to the image view
            cview.setImage(fxImage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    */}

   



    
  @FXML
    void afficher(ActionEvent event) throws FileNotFoundException, IOException, DocumentException {
       
    /*  try {
           Demande e = tableviewdemande.getItems().get(tableviewdemande.getSelectionModel().getSelectedIndex());     

      
        pdff.setText(e.getCv());
        
      
        
        
      //  maildd.setText(String.valueOf(e.getId_user())); 
        ///////lel image
        String path = e.getCv();

            // Load the PDF document
            File file = new File("C:\\xampp\\htdocs\\ImageP\\68.pdf");

            //File file = new File(path);
            document = Loader.loadPDF(file);

            // Create a PDF renderer
            renderer = new PDFRenderer(document);

            // Render the first page
            PDPage page = document.getPage(0);

          BufferedImage image = renderer.renderImageWithDPI(0, 600, ImageType.RGB);


            // Convert the buffered image to a JavaFX image
            Image fxImage = SwingFXUtils.toFXImage(image, null);

            // Set the JavaFX image to the image view
            cview.setImage(fxImage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
    }
            //   File file=new File(path);
        
      //  maildd.setText(String.valueOf(e.getId_user())); 
        ///////lel image
      //  FileObject fileObject = tableviewdemande.getItems().get(rowIndex);

// Récupérer le contenu du fichier sous forme de tableau de bytes
 //Document document = new Document(file.toURI().toString())
//byte[] fileContent = file.toURI();

       
        
              
              

         //  cv1.doGet(path, response);
              
///////////
 
      /*  File file = new File(path); // crée un objet File à partir du chemin du fichier
        byte[] content = Files.readAllBytes(Paths.get(file.getAbsolutePath())); // lit le contenu du fichier dans un tableau de bytes
        
        response.setContentType("application/octet-stream"); // définit le type de contenu de la réponse à binaire
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\""); // définit le nom du fichier à télécharger
        response.getOutputStream().write(content); // envoie le contenu du fichier en tant que réponse HTTP
        response.getOutputStream().flush();
        response.getOutputStream().close();*/


    private void onimageedit(TableColumn.CellEditEvent<Demande, String> event) {
          FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (.JPG)", ".JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (.jpg)", ".jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (.PNG)", ".PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (.png)", ".png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

         File f = fileChooser.showOpenDialog(new Stage());
         if (f != null)
         {
             Demande p = tableviewdemande.getSelectionModel().getSelectedItem();
         p.setCv( f.getAbsoluteFile().toURI().toString());
         s.modifierdemande(p);
         senddata();
            
         }
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
    String statistique(ActionEvent event) {
          String percentageText =null;
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
       int  frequency = typeFrequency.get(type);
        double percentage = (double) frequency / tableviewdemande.getItems().size() * 100;
        percentageText = String.format("%.2f%%", percentage);
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
      Notifications notificationBuilder = Notifications.create()
        .title("demande non traite ")
        .text("il vous reste " + percentageText+"de demande non traite veuillez les traiter")
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
        return percentageText; 

}
    @FXML
    void retour(ActionEvent event) {
 try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("offreFXML.fxml"));
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
}
    


