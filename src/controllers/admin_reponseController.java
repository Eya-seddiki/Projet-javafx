/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.reclamation_Service;
import Service.reponse_Service;
import entites.reponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author 21656
 */
public class admin_reponseController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_reponse;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<reponse> tabview;
    @FXML
    private TableColumn<reponse, Integer> col_id_reclamation;
    @FXML
    private TableColumn<reponse, String> col_reponse;
    @FXML
    private TableColumn<reponse, Integer> col_id;
    @FXML
    private TextField txt_reponse;
    @FXML
    private ComboBox<Integer> combo_reclamation;
    @FXML
    private Button btn_ajout;
    private reponse_Service serviceReponse = new reponse_Service();
    private reclamation_Service serviceReclamaion = new reclamation_Service();

 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           search();
        Modifier();
        tabview.setEditable(true);
        try {
            refreche();
            combo_reclamation.setItems(serviceReclamaion.Reclamation_ids());
        } catch (SQLException ex) {

        }
        combo_reclamation.getSelectionModel().select(0);

    }
  public void search() {
        txt_Seach.setOnKeyReleased(e
                -> {
            if (txt_Seach.getText().equals("")) {

                try {
                    refreche();
                } catch (SQLException ex) {

                }

            } else {

                try {
                      col_reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        col_reponse.setCellFactory(TextFieldTableCell.<reponse>forTableColumn());
        col_id_reclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        col_id_reclamation.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_reponse"));
        tabview.getItems().clear();

                    tabview.setItems(serviceReponse.serach(txt_Seach.getText()));

                } catch (SQLException ex) {

                }

            }
        }
        );

    }
    public void refreche() throws SQLException {

        col_reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        col_reponse.setCellFactory(TextFieldTableCell.<reponse>forTableColumn());
        col_id_reclamation.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        col_id_reclamation.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id_reponse"));
        tabview.getItems().clear();

        tabview.setItems(serviceReponse.Affichertout());

    }

    public void Modifier() {

        col_reponse.setOnEditCommit((TableColumn.CellEditEvent<reponse, String> event) -> {
            TablePosition<reponse, String> pos = event.getTablePosition();

            String reponses = event.getNewValue();

            int row = pos.getRow();
            reponse ac = event.getTableView().getItems().get(row);

            ac.setReponse(reponses);
            try {
                serviceReponse.Modifier(ac, ac.getId_reponse());
            } catch (SQLException ex) {

            }
        });

        col_id_reclamation.setOnEditCommit((TableColumn.CellEditEvent<reponse, Integer> event) -> {
            TablePosition<reponse, Integer> pos = event.getTablePosition();

            Integer id_reclamation = event.getNewValue();

            int row = pos.getRow();
            reponse ab = event.getTableView().getItems().get(row);

            ab.setId_reclamation(id_reclamation);
            try {
                serviceReponse.Modifier(ab, ab.getId_reponse());
            } catch (SQLException ex) {

            }
        });

    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btn_reponse) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Admin_Reponse.fxml")));
            stage.setScene(scene);
            stage.show();

        }
        if (event.getSource() == btn_reclamation) {
            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();
            //stage.setMaximized(true);
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Admin_Reclamation.fxml")));
            stage.setScene(scene);
            stage.show();

        }
    }

    @FXML
    private void ajouter_reponse(ActionEvent event) throws SQLException {
        if (txt_reponse.getText().equals("")) {
            System.out.println("Champ vide de reponse");//cntrl sais

        } else if (txt_reponse.getText().matches("^[0-9]+$")) {
            System.out.println("il faut saisir des caracteres  ! reponse");

        } else {
            reponse res = new reponse(txt_reponse.getText(), combo_reclamation.getSelectionModel().getSelectedItem());
            serviceReponse.Ajouter(res);

            refreche();
        }
    }

}
