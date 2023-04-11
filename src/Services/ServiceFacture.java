/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Facture;
import Utiles.MyDB;
import static java.rmi.Naming.list;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author chino
 */
public class ServiceFacture {
    
                  Facture p =new Facture();
                  
                     Connection cnx;
    public Statement ste;
    public PreparedStatement pst;


   // @Override

    public ServiceFacture() {
               cnx = MyDB.getInstance().getCnx();

    }
 
    
    public void add(Facture t)  {
        try {
        String qry ="INSERT INTO `facture`( `nb_science`, `type_de_paiement`,`rendezvous_id` ) VALUES ('"+t.getNb_science()+"','"+t.getType_de_paiement()+"','"+t.getRendezvous_id()+"')";
       // String qry ="INSERT INTO `facture`( `nb_science`, `type_de_paiement` ) VALUES ('"+t.getNb_science()+"','"+t.getType_de_paiement()+"')";

        cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    
    }
//        public List<Rendezvous> afficher() throws SQLException {
//        List<Rendezvous> Factures = new ArrayList<>();
// 
//            String qry ="SELECT * FROM `Rendezvous` ";
//            cnx = MyDB.getInstance().getCnx();
//            Statement stm = cnx.createStatement();
//            ResultSet rs = stm.executeQuery(qry);
//            while(rs.next()){
//                p.setIdRendezvous(rs.getInt(1));
//                p.setNomRendezvous(rs.getString("nom_rendezvous"));
//                  p.setPrenomRendezvous(rs.getString("prenom_rendezvous"));
//                    p.setLieuRendezvous(rs.getString("lieu_rendezvous"));
//                      p.setEmailRendezvous(rs.getString("email_rendezvous"));
//                        p.setDateRendezvous(rs.getDate("date_rendezvous"));
//                        p.setColor(rs.getString("color"));
//                         p.setUser_id(rs.getInt("user_id"));
//          
//                //p.setAdresses(rs.getString(5));
//
//                Factures.add(p);
//
//            }
//            return Factures;
//        
//        
    
    public List<Facture> afficher() throws SQLException  {
        List<Facture> Factures = new ArrayList<>();
      try {
          Facture p = new Facture();
            String qry ="SELECT * FROM Facture ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                p.setId(rs.getInt(1));
                p.setNb_science(rs.getInt(2));
                p.setType_de_paiement(rs.getString(3));
                p.setRendezvous_id(rs.getInt(4));

                Factures.add(p);

            }
            return Factures;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Factures;
        
    }
    
    public List<Facture> recupererfacture() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        List<Facture> evenements = new ArrayList<>();
        String s = "select * from facture";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Facture e = new Facture();
            e.setId(rs.getInt("id"));
            e.setNb_science(rs.getInt("nb_science"));
            e.setRendezvous_id(rs.getInt("rendezvous_id"));
            e.setType_de_paiement(rs.getString("type_de_paiement"));
           

            evenements.add(e);

        }
        return evenements;
    }
    
//         public List<Facture> recupererfacture() throws SQLException {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//
//        List<Facture> evenements = new ArrayList<>();
//        String s = "select * from Facture";
//        Statement st = cnx.createStatement();
//        ResultSet rs = st.executeQuery(s);
//        while (rs.next()) {
//            Facture e = new Facture();
//            e.setId(rs.getInt("id"));
//            e.setNb_science(rs.getInt("nb_sciences"));
//            e.setType_de_paiement(rs.getString("type_de_paiement"));
//            e.setRendezvous_id(rs.getInt("rendezvous_id"));
//            
//
//            evenements.add(e);
//
//        }
//        return evenements;
//    }
     // @Override
    public void modifier(Facture t) {
 try {
            String qry ="UPDATE facture SET `nb_science`='"+t.getNb_science()+"',`type_de_paiement`='"+t.getType_de_paiement()+"',`Rendezvous_id`='"+t.getRendezvous_id()+"' WHERE id="+t.getId()+";";    
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        }
//@Override
    public void supprimer(int id) {
try {
            String qry ="DELETE from facture where id = "+id+";";
      cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }
         public void supprimerfacture(Facture e) throws SQLException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "delete from facture where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, e.getId());
        ps.executeUpdate();
        System.out.println("event with id= " + e.getId() + "  is deleted successfully");
    }
    
         
         
          public void modifierFacture(Facture e) throws SQLException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "UPDATE facture SET nb_science = ?,type_de_paiement = ?,rendezvous_id=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, e.getNb_science());
        ps.setString(2, e.getType_de_paiement());
        ps.setInt(3, e.getRendezvous_id());
        ps.setInt(4, e.getId());
        ps.executeUpdate();
    }
    
}
