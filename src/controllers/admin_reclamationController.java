/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Service.reclamation_Service;
import entites.reclamation;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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
public class admin_reclamationController implements Initializable {

    @FXML
    private VBox vboxdrawer;
    @FXML
    private ImageView imagechange;
    @FXML
    private Label fullName;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnl_abonnement;
    @FXML
    private TextField txt_Seach;
    @FXML
    private TableView<reclamation> tabview;
    @FXML
    private TableColumn<reclamation, String> col_nom_reclamation;
    @FXML
    private TableColumn<reclamation, String> col_prenom_reclamation;
    @FXML
    private TableColumn<reclamation, String> col_destination;
    @FXML
    private TableColumn<reclamation, String> col_description;
    @FXML
    private TableColumn<reclamation, String> col_type;
    @FXML
    private TableColumn<reclamation, Integer> col_id_user;
    @FXML
    private TableColumn<reclamation, Integer> col_id;
    private reclamation_Service serviceReclamation = new reclamation_Service();

    private TableColumn<reclamation, String> col_btnDelet;
    @FXML
    private Button btn_reponse;
    @FXML
    private Button btn_reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            refreche();
        } catch (SQLException ex) {

        }

        col_btnDelet = new TableColumn("Supprimer");
        javafx.util.Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>> cellFactory
                = new Callback<TableColumn<reclamation, String>, TableCell<reclamation, String>>() {
            public TableCell call(final TableColumn<reclamation, String> param) {
                final TableCell<reclamation, String> cell = new TableCell<reclamation, String>() {

                    final Button btn = new Button("supprimer");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
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
                                    refreche();
                                } catch (SQLException ex) {

                                }

                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };
        col_btnDelet.setCellFactory(cellFactory);
        tabview.getColumns().add(col_btnDelet);
    }

    public void refreche() throws SQLException {

        col_nom_reclamation.setCellValueFactory(new PropertyValueFactory<>("nom_reclamation"));

        col_prenom_reclamation.setCellValueFactory(new PropertyValueFactory<>("prenom_reclamation"));

        col_destination.setCellValueFactory(new PropertyValueFactory<>("destination_reclamation"));

        col_description.setCellValueFactory(new PropertyValueFactory<>("description_reclamation"));

        col_type.setCellValueFactory(new PropertyValueFactory<>("type_reclamation"));

        col_id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));

        col_id.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        tabview.getItems().clear();

        tabview.setItems(serviceReclamation.Affichertout());

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

}
