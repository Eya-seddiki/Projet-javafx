/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.mysql.jdbc.Messages.getString;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Demande;

import utils.connexionDB;

/**
 *
 * @author Marwen.M
 */
public class DemandeServices  {
     Statement ste;
   Connection conn = connexionDB.getInstance().getConnexion();
   
    
   public void ajouterDemande (Demande D)  {
       
       try{
           String req ="INSERT INTO `demande` (`id_demande`,`id_offre`,`id_user`,`cv`,`description`,`traitement`) VALUES ('" + D.getId_demande() +"','" + D.getId_offre()+"','" + D.getId_user()+"','" + D.getCv() +"','" + D.getDescription()+ "','" + D.getTraitement() + "')";
           ste = conn.createStatement();
           ste.executeUpdate(req);
           System.err.println("Demande  Ajouter!!!");
           
       } catch (SQLException ex){
           System.out.println("Demande  non ajoute");
           System.out.println(ex.getMessage());
       }
       
       
           
   }
   public List<Demande> afficherDemande(){
       List<Demande> list = new ArrayList<>();
       try {
           String req ="Select * from demande";
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           while (RS.next()) {
            Demande D = new Demande();
            D.setId_demande(RS.getInt(1));
            D.setId_user(RS.getInt(2));
            D.setId_offre(RS.getInt(3));
            
                   
            D.setCv(RS.getString("cv"));
            D.setDescription(RS.getString("description"));
            D.setTraitement(RS.getString("traitement"));
                      
              
                
             list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }
   public List<Demande> afficherDemandeparid(int id_demande){
       List<Demande> list = new ArrayList<>();
       try {
           String req ="Select * from demande  where id_demande =" +id_demande;
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           while (RS.next()) {
            Demande D = new Demande();
            D.setId_demande(RS.getInt(1));
            D.setId_user(RS.getInt(2));
            D.setId_offre(RS.getInt(3));
            D.setCv(RS.getString("cv"));
            D.setDescription(RS.getString("description"));
            D.setTraitement(RS.getString("traitement"));
                      
              
                
            list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }
   public List<Demande> afficherDemandeparcategorie (){
       List<Demande> list = new ArrayList<>();
       try {
           String req ="Select * from demande  where traitement ='en cours de traitement'" ;
           
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           
           while (RS.next()) {
            Demande D = new Demande();
            D.setId_demande(RS.getInt(1));
            D.setId_user(RS.getInt(2));
            D.setId_offre(RS.getInt(3));
            D.setCv(RS.getString("cv"));
            D.setDescription(RS.getString("description"));
            D.setTraitement(RS.getString("traitement"));
                      
              
                
               list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }
   public void modifierdemande (Demande D){
      
      try {
         
          String req ="UPDATE demande SET `id_offre`='"+D.getId_offre()+"',`id_user`='"+D.getId_user()+"',`cv`='"+D.getCv()+"',`description`='"+D.getDescription()+"',`traitement`='"+D.getTraitement()+"' WHERE id_demande="+D.getId_demande()+";";
          Statement st = conn.createStatement();
          st.executeUpdate(req);
          System.out.println("demande updated !!");
          
      }catch (SQLException ex) {
          System.out.println("demande not Updated");
          System.out.println(ex.getMessage());
      }
  }

   
    public void supprimerDemande(int id_demande){
       try {
           String req = "DELETE from `demande` WHERE id_demande = " + id_demande;
            ste = conn.createStatement();
           ste.executeUpdate(req);
           System.out.println("demande Deleted!!!");
           
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
   }
}
