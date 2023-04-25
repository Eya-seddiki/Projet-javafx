/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import interfaces.MyListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import model.Offre;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ModeleFXMLController implements Initializable {

    private Label namelabel1;
    private Label label1;
    private ImageView prodimg1;
    @FXML
    private Label nomEventLabel;
    @FXML
    private Label typeEventLabel;
int idoffre;
    /**
     * Initializes the controller class.
     */
    private void click(MouseEvent mouseEvent) {
        
        myListener.onClickListener(offre);
        
       
    }
    
    private Offre offre;
    
    private MyListener myListener;
     private Offre off=new Offre();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        public void setoffre(Offre e) {
        this.off=e;
        nomEventLabel.setText(e.getNom_offre());
        
        typeEventLabel.setText(String.valueOf(e.getDatepub_offre()));
       

    }
    public void setIdeoffre(int idoffre){
        this.idoffre=idoffre;
    }
    
}
