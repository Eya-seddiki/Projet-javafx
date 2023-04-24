/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reservation;
import entity.Ressource;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.RessourceService;

/**
 * FXML Controller class
 *
 * @author Narimen
 */
public class RessourceController implements Initializable {

    @FXML
    private TextField tf_id;
    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_dispo;
    @FXML
    private TextField tf_type;
    @FXML
    private Button btn_ajout;
    @FXML
    private TableColumn<Ressource,String> fx_id;
    @FXML
    private TableColumn<Ressource,String> fx_type;
    @FXML
    private TableColumn<Ressource,String> fx_dispo;
    @FXML
    private TableColumn<Ressource,String> fx_nom;
    private RessourceService RessourceService = new RessourceService();
    @FXML
    private TableView<Ressource>  afficher_tab;
    @FXML
    private Button btn_supp;
    @FXML
    private Button btn_modifier;
    @FXML
    private TextField rech;
    @FXML
    private Button btn_ajout1;
    @FXML
    private TextField searchField;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO       idColumn.setCellValueFactory(new PropertyValueFactory<>("id_cat_artic"));
        fx_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fx_type.setCellValueFactory(new PropertyValueFactory<>("type_ressource"));
        fx_dispo.setCellValueFactory(new PropertyValueFactory<>("disponibilite_ressource"));
        
        fx_nom.setCellValueFactory(new PropertyValueFactory<>("nom_ressource"));
            // récupère les données des utilisateurs depuis la base de données
            List<Ressource> catarticleList = RessourceService.AfficherRessource();
            searchField.textProperty().addListener((observable, oldValue, newValue) -> search());
      
             tf_id.setVisible(false);

        // affiche les données dans le tableau
        afficher_tab.getItems().setAll(catarticleList);
        
        // TODO
        // TODO
    }    
public static boolean estChaineValide (String chaine){
    if (!chaine.matches("[a-zA-Z]+") || chaine.trim().isEmpty()){
    return false;}
    return true;}
    @FXML
    private void click_on_ajouter(ActionEvent event) {
    //int id_ressource =Integer.parseInt( tf_id.getText());
        String type_ressource = tf_type.getText();
        String disponibilite_ressource = tf_dispo.getText();      
        String nom_ressource = tf_nom.getText();      
         if(estChaineValide(type_ressource)&&estChaineValide(disponibilite_ressource)&&estChaineValide(nom_ressource))
         {
            RessourceService cas=new RessourceService();
   Ressource  ca= new Ressource( type_ressource, disponibilite_ressource,nom_ressource);
   cas.AjouterRessource(ca); 
         }
         else{
             //System.out.println("Erreur Controle de Saisie");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("controle de saisie");
            alert.setHeaderText(null);
            alert.setContentText("veuillez saisir les champs correctement ");
            alert.showAndWait();
         }
  // List<categorie_article> aricleList = catgorie_article_Service.afficher
  
   List<Ressource> catarticleList = RessourceService.AfficherRessource();
      
        
        // affiche les données dans le tableau
        afficher_tab.getItems().setAll(catarticleList);

    }

    @FXML
    private void click_on_supprimer(ActionEvent event) throws SQLException {
      Ressource selectedRessource =  (Ressource) afficher_tab.getSelectionModel().getSelectedItem();
       
        if (selectedRessource == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No user selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select an article in the table.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deletion");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the selected article?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
           
            RessourceService.SupprimerRessource(selectedRessource);
             List<Ressource> articleList = RessourceService.AfficherRessource();
        
        // affiche les données dans le tableau
        afficher_tab.getItems().setAll(articleList);
        }
    }
    
    

    @FXML
    private void click_on_modifier(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(tf_id.getText());
              String type = tf_type.getText();
        String dispo = tf_dispo.getText();
        String nom = tf_nom.getText();
     RessourceService sp=new RessourceService();
   Ressource a = new Ressource(id,type,dispo,nom);
   sp.modifier(a);
   List<Ressource> ressourceList = RessourceService.AfficherRessource();
        
        // affiche les données dans le tableau
        afficher_tab.getItems().setAll(ressourceList);
    }

    
    @FXML
    private void recherche(KeyEvent event) {
       if (event.getCode() == KeyCode.ENTER) {
        String type = rech.getText();
        Ressource searchedModel = RessourceService.readByTitre(type);
        if (searchedModel != null) {
            afficher_tab.getItems().setAll(searchedModel);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Titre found");
            alert.setHeaderText(null);
            //alert.setContentText("No ressource found with the given TYPE.");
              alert.setTitle("ERREUR");
               alert.setContentText("Please select a ressource in the table.");
            alert.showAndWait();
            
        }
    }




    }

    @FXML
    private void click_on_SMS(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("sendSMS.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
        private void search() {
            String query = searchField.getText();
            ObservableList<Ressource> filteredList = FXCollections.observableArrayList();
               List<Ressource> ressourceList = RessourceService.AfficherRessource();

            for (Ressource ressource : ressourceList) {
                if (ressource.getType_ressource().toLowerCase().contains(query.toLowerCase())||ressource.getDisponibilite_ressource().toLowerCase().contains(query.toLowerCase())||ressource.getNom_ressource().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(ressource);
                }
            }
    afficher_tab.setItems(filteredList);
    
}

    @FXML
    private void edp1(MouseEvent event) {
          final Ressource selectedItem = afficher_tab.getSelectionModel().getSelectedItem();
        Ressource prod = RessourceService.GetById(selectedItem.getId());
        tf_id.setText(String.valueOf(prod.getId()));
        tf_type.setText(prod.getType_ressource());
        tf_dispo.setText(String.valueOf(prod.getDisponibilite_ressource()));
        tf_nom.setText(String.valueOf(prod.getNom_ressource()));
    }
    
    
}


