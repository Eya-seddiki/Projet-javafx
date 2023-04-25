/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Alert.AlertDialog;
import Service.reclamation_Service;
import Service.reponse_Service;
import entites.BadWords;
import entites.reclamation;
import entites.reponse;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author 21656
 */
public class FrontCrontroller implements Initializable {

    @FXML
    private Pane pnl_Reclamation;
    @FXML
    private TableView<reclamation> tab_Reclamation;
    @FXML
    private TableColumn<reclamation, Integer> col_id_rec;
    @FXML
    private TableColumn<reclamation, String> col_nom_reclamation;
    @FXML
    private TableColumn<reclamation, String> col_prenom_reclamation;
    @FXML
    private TableColumn<reclamation, String> col_destination_reclamation;
    @FXML
    private TableColumn<reclamation, String> col_description_reclamation;
    @FXML
    private TableColumn<reclamation, String> col_type_reclamation;
    @FXML
    private Button btn_ajout_reclamation;
    @FXML
    private TextArea txtdescription_reclamation;
    @FXML
    private TextField txtnom_reclamation;
    @FXML
    private TextField txtprenom_reclamation;
    @FXML
    private TextField txtdestination_reclamation;
    @FXML
    private Button btn_Reclamation;
    @FXML
    private Button btn_Sign_Out;
    @FXML
    private ImageView image_user;
    @FXML
    private Label username;
    private reclamation_Service serviceReclamation = new reclamation_Service();
    private TableColumn<reclamation, String> col_btnDelete_reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //reclamation 
        col_btnDelete_reclamation = new TableColumn("Supprimer");
        javafx.util.Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFactory
                = new Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>>() {
            public TableCell call(final TableColumn<reclamation, String> param) {
                final TableCell<reclamation, String> cell = new TableCell<reclamation, String>() {

                    final Button btn = new Button("Supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {//Si la cellelule est vide 
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {
                                reclamation u = getTableView().getItems().get(getIndex());

                                try {
                                    serviceReclamation.Supprimer(u.getId_reclamation());
                                } catch (SQLException ex) {

                                }

                                try {
                                    refreche_reclamation();
                                } catch (SQLException ex) {

                                }

                            });
                        
                            if (!getTableView().getItems().get(getIndex()).getType_reclamation().equals("Traite")) {
                                  setGraphic(btn);
                            setText(null);
                            }
                          
                        }
                    }
                };
                return cell;
            }
        };
        col_btnDelete_reclamation.setCellFactory(cellFactory);
        tab_Reclamation.getColumns().add(col_btnDelete_reclamation);

    }

    public void refreche_reclamation() throws SQLException {

        col_nom_reclamation.setCellValueFactory(new PropertyValueFactory<>("nom_reclamation"));

        col_prenom_reclamation.setCellValueFactory(new PropertyValueFactory<>("prenom_reclamation"));

        col_destination_reclamation.setCellValueFactory(new PropertyValueFactory<>("destination_reclamation"));

        col_description_reclamation.setCellValueFactory(new PropertyValueFactory<>("description_reclamation"));

        col_type_reclamation.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));

        col_id_rec.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        tab_Reclamation.getItems().clear();

        // 1 rahi statique maneha user id teeou 1 k bch tamlou integration id 1 ttbdel b seesion id
        tab_Reclamation.setItems(serviceReclamation.Affichertout_user(1));

    }

    @FXML
    private void ajouter_reclamation(ActionEvent event) throws Exception {
        BadWords.loadConfigs();

        {
            if (txtnom_reclamation.getText().equals("")) {
                  AlertDialog.showNotification("Error !", "Champ vide de txtnom_reclamation", AlertDialog.image_cross);
              

            } else if (txtnom_reclamation.getText().matches("^[0-9]+$")) {
                AlertDialog.showNotification("Error !", "il faut saisir des caracteres  ! txtnom_reclamation", AlertDialog.image_cross);

            } else if (BadWords.filterText(txtnom_reclamation.getText())) {
                       AlertDialog.showNotification("Error !", "bad words are not allowed ! txtnom_reclamation", AlertDialog.image_cross);

            } else if (txtprenom_reclamation.getText().equals("")) {
                AlertDialog.showNotification("Error !", "Champ vide de txtprenom_reclamation", AlertDialog.image_cross);
           

            } else if (txtprenom_reclamation.getText().matches("^[0-9]+$")) {
             AlertDialog.showNotification("Error !", "il faut saisir des caracteres  ! txtprenom_reclamation", AlertDialog.image_cross);
             

            } else if (BadWords.filterText(txtprenom_reclamation.getText())) {
                  AlertDialog.showNotification("Error !", "bad words are not allowed ! txtprenom_reclamation", AlertDialog.image_cross);
             
            } else if (txtprenom_reclamation.getText().equals("")) {
                AlertDialog.showNotification("Error !", "Champ vide de txtprenom_reclamation", AlertDialog.image_cross);
            //cntrl sais

            } else if (txtprenom_reclamation.getText().matches("^[0-9]+$")) {
                 AlertDialog.showNotification("Error !", "il faut saisir des caracteres  ! txtprenom_reclamation", AlertDialog.image_cross);
              

            } else if (BadWords.filterText(txtprenom_reclamation.getText())) {
                 AlertDialog.showNotification("Error !", "bad words are not allowed ! txtprenom_reclamation", AlertDialog.image_cross);
            
            } else if (txtdestination_reclamation.getText().equals("")) {
                       AlertDialog.showNotification("Error !", "Champ vide de txtdestination_reclamation", AlertDialog.image_cross);
            //cntrl sais

            } else if (txtdestination_reclamation.getText().matches("^[0-9]+$")) {
                AlertDialog.showNotification("Error !", "il faut saisir des caracteres  ! txtdestination_reclamation", AlertDialog.image_cross);
              

            } else if (BadWords.filterText(txtdestination_reclamation.getText())) {
                AlertDialog.showNotification("Error !", "bad words are not allowed ! txtdestination_reclamation", AlertDialog.image_cross);
                
            }  else if (txtdescription_reclamation.getText().equals("")) {
                AlertDialog.showNotification("Error !", "Champ vide de txtdescription_reclamation", AlertDialog.image_cross);
              //cntrl sais

            } else if (txtdescription_reclamation.getText().matches("^[0-9]+$")) {
                AlertDialog.showNotification("Error !", "il faut saisir des caracteres  ! txtdescription_reclamation", AlertDialog.image_cross);
           

            } else if (BadWords.filterText(txtdescription_reclamation.getText())) {
                AlertDialog.showNotification("Error !", "bad words are not allowed ! txtdescription_reclamation", AlertDialog.image_cross);
           
            } else {
                AlertDialog.showNotification("Ajout ", "Ajout", AlertDialog.image_checked);
                // 1 lahne tttbdel zeda b id ta session
                reclamation rec = new reclamation(txtnom_reclamation.getText(), txtprenom_reclamation.getText(), txtdestination_reclamation.getText(), txtdescription_reclamation.getText(), "Non traitee", 1);
                serviceReclamation.Ajouter(rec);
                refreche_reclamation();
            }
        }
    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btn_Reclamation) {
            pnl_Reclamation.toFront();
        }
    }

    @FXML
    private void annuler_reclamation(ActionEvent event) {

        txtnom_reclamation.clear();
        txtprenom_reclamation.clear();
        txtdestination_reclamation.clear();
        txtdescription_reclamation.clear();

    }

    @FXML
    private void afficher_reclamation(ActionEvent event) {
        try {
            //reclamation
            refreche_reclamation();
        } catch (SQLException ex) {

        }
    }

}
