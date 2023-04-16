/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entity.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import utils.MyDB;
/**
 *
 * @author Narimen
 */
public class ReservationService {
    
    
    
  Connection cnx;
   // @Override
    public static boolean estChaineValide (String chaine){
    if (!chaine.matches("[a-zA-Z]+") || chaine.trim().isEmpty()){
    return false;}
    return true;}
   
   public void AjouterReservation(Reservation t) {
       // Vérifier si la description contient des mots interdits
    if (contientMauvaisMots(t.getDescription_reservation())) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText("La description contient des mots interdits !");
        alert.showAndWait();
        return;
    } 
       try {
        //String qry ="INSERT INTO `Participation`(`date_participation`,`description_participation`,`evenement_id `,`user_id`) VALUES ('"+t.getDate_participation()+"','"+t.getDescription_participation()+"','"+t.getEvenement_id()+"','"+t.getUser_id()+"')";
        String qry ="INSERT INTO `Reservation`(`description_reservation`,`date_debut`,`date_fin`,`ressource_id`,`user_id`) VALUES ('"+t.getDescription_reservation()+"','"+t.getDate_debut()+"','"+t.getDate_fin()+"','"+t.getRessource_id()+"','"+t.getUser_id()+"')";
 
        cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
         //   if(estChaineValide(qry))
           // {
            stm.executeUpdate(qry);
            //}
              //              else{
            // System.out.println("erreur");     
              //                      }
                
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
   }
     private boolean contientMauvaisMots(String texte) {
    List<String> mauvaisMots = Arrays.asList("SII", "BLOO", "BLII");
    for (String mot : mauvaisMots) {
        if (texte.toLowerCase().contains(mot.toLowerCase())) {
            return true;
        }
    }
    return false;


    }
   
   
   
    public List<Reservation> AfficherReservation() {
        List<Reservation> Reservation = new ArrayList<>();
      try {
            String qry ="SELECT * FROM `Reservation` ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Reservation p =new Reservation();
                p.setId(rs.getInt(1));
                p.setDate_debut(rs.getDate(2));
                p.setDate_fin(rs.getDate(3));
                p.setDescription_reservation(rs.getString(4));
                p.setRessource_id(rs.getInt(5));
                p.setUser_id(rs.getInt(6));


                Reservation.add(p);

            }
            return Reservation;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Reservation;
        
        
        
        
    }
   
   
         //@Override
    public void SupprimerReservation(Reservation c) {
//try {
//            String qry ="DELETE from Reservation where id = "+id+";";
//            cnx = MyDB.getInstance().getCnx();
//      
//            Statement stm =cnx.createStatement();
//            
//            stm.executeUpdate(qry);
//            
//        } catch (SQLException ex) {
//             System.out.println(ex.getMessage());
//        }
 String requete="delete from Reservation where id = "+c.getId();
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ReservationService.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    
    
   // @Override
    public void modifierReservation(Reservation c) {
  try {
            String qry ="UPDATE Reservation SET `description_reservation`='"+c.getDescription_reservation()+"',`ressource_id`='"+c.getRessource_id()+"',`user_id`='"+c.getUser_id()+"' WHERE id="+c.getId()+";";    
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }  
    }
     
 
  

    
    
    
    }
