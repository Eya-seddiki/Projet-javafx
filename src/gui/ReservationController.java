/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entity.Reservation;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.ArrayBufferView.length;
import service.ReservationService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ReservationController implements Initializable {

    private TextField tx_reservation;
    @FXML
    private TextField tx_user;
    @FXML
    private TextField tx_ressource;
    @FXML
    private TextField tf_descrip;
    @FXML
    private DatePicker dp_debut;
    @FXML
    private DatePicker dp_fin;
    private ReservationService ReservationService = new ReservationService();
    @FXML
    private TableView<Reservation> tab_reserv;
    @FXML
    private TableColumn<Reservation,String> tb_id;
    @FXML
    private TableColumn<Reservation,String>  tb_debut;
    @FXML
    private TableColumn<Reservation,String>  tb_fin;
    @FXML
    private TableColumn<Reservation,String>  tb_description;
    @FXML
    private TableColumn<Reservation,String>  tb_ressource;
    @FXML
    private TableColumn<Reservation,String>  tb_user;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private ImageView qrcodee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
          tb_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_debut.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        tb_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        tb_description.setCellValueFactory(new PropertyValueFactory<>("description_reservation"));
        tb_ressource.setCellValueFactory(new PropertyValueFactory<>("ressource_id"));
        tb_user.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            // récupère les données des utilisateurs depuis la base de données
            List<Reservation> catarticleList = ReservationService.AfficherReservation();
     // tf_descrip.setVisible(false);
        
        // affiche les données dans le tableau
        tab_reserv.getItems().setAll(catarticleList);
        // TODO
       
    }    
public static boolean estChaineValide (String chaine){
    if (!chaine.matches("[a-zA-Z]+") || chaine.trim().isEmpty()){
    return false;}
    return true;}

    @FXML
    private void click_on_ajouetr(ActionEvent event) throws SQLException {
         int id_rese = Integer.parseInt(tx_reservation.getText());
         Date  date_debut=java.sql.Date.valueOf(dp_debut.getValue());
         Date  date_fin=java.sql.Date.valueOf(dp_fin.getValue());
         
        String descr=tf_descrip.getText();
        int id_user=Integer.parseInt(tx_user.getText());
        int id_ressou=Integer.parseInt(tx_ressource.getText());
         if(date_debut.before(date_fin)&&estChaineValide(descr))
         {
                 ReservationService sp=new ReservationService();
     
   Reservation a = new Reservation(id_rese,date_debut,date_fin,descr,id_user,id_ressou);
   sp.AjouterReservation(a); 
         }
         else{
             // System.out.println("Erreur Controle de Saisie");
         Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("controle de saisie");
            alert.setHeaderText(null);
            alert.setContentText("veuillez saisir les champs correctement ");
            alert.showAndWait();
         }

  
                    List<Reservation> catarticleList = ReservationService.AfficherReservation();

                tab_reserv.getItems().setAll(catarticleList);

    }

    @FXML
    private void click_on_supprimer(ActionEvent event) throws SQLException {
        
         Reservation selectedReservation = (Reservation) tab_reserv.getSelectionModel().getSelectedItem();
       
        if (selectedReservation == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No category selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a category in the table.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deletion");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected category?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           
            ReservationService.SupprimerReservation(selectedReservation);
             List<Reservation> aricleList = ReservationService.AfficherReservation();
        
        // affiche les données dans le tableau
        tab_reserv.getItems().setAll(aricleList);
        }
    }

    @FXML
    private void click_on_modifier(ActionEvent event) throws SQLException {
             int id_rese = Integer.parseInt(tx_reservation.getText());
         Date  date_debut=java.sql.Date.valueOf(dp_debut.getValue());
         Date  date_fin=java.sql.Date.valueOf(dp_fin.getValue());
         
        String descr=tf_descrip.getText();
        int id_ressource = Integer.parseInt(tx_ressource.getText());
                int id_user=Integer.parseInt(tx_user.getText());
     ReservationService sp=new ReservationService();
   Reservation a = new Reservation(id_rese,date_debut,date_fin,descr,id_ressource,id_user);
   sp.modifierReservation(a);
   List<Reservation> aricleList = ReservationService.AfficherReservation();
        
        // affiche les données dans le tableau
        tab_reserv.getItems().setAll(aricleList);
    }

    @FXML
    private void click_on_Mail(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("sendEmail.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void edp1(MouseEvent event) {
            final Reservation selectedItem = tab_reserv.getSelectionModel().getSelectedItem();
        Reservation prod = ReservationService.GetById(selectedItem.getId());
        tf_descrip.setText(prod.getDescription_reservation());
        tx_ressource.setText(String.valueOf(prod.getRessource_id()));
        tx_user.setText(String.valueOf(prod.getUser_id()));
         try {
           
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            String Information = "Date debut : "+prod.getDate_debut()+"\n"+"date fin : "+prod.getDate_fin()+"\n descriptif : "+prod.getDescription_reservation();
            int width = 300;
            int height = 300;
            
            BufferedImage bufferedImage = null; 
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            
            

            
            qrcodee.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            // TODO
        } catch (WriterException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
