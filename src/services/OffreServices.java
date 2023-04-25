/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.IoffreController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Offre;
import utils.connexionDB;

/**
 *
 * @author dell
 */
public class OffreServices  {
    
   Statement ste;
   Connection conn = connexionDB.getInstance().getConnexion();
   
    
   public void ajouterOffre(Offre f)  {
       
       try{
           String req ="INSERT INTO `offre` (`nom_offre`,`datepub_offre`) VALUES ('" + f.getNom_offre() + "','" + f.getDatepub_offre() + "')";
           ste = conn.createStatement();
           ste.executeUpdate(req);
           System.err.println("Offre Ajouter!!!");
           
       } catch (SQLException ex){
           System.out.println("Offre non ajoute");
           System.out.println(ex.getMessage());
       }
       
       
           
   }
   
   
   
   public void supprimerOffre(int id_offre){
       try {
           String req = "DELETE from `offre` WHERE id_offre = " + id_offre;
            ste = conn.createStatement();
           ste.executeUpdate(req);
           System.out.println("Offre Deleted!!!");
           
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
       }
   }
   
   
  public List<Offre> afficherOffre(){
       List<Offre> list = new ArrayList<>();
       try {
           String req ="Select * from offre";
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           while (RS.next()) {
            Offre f = new Offre();
             f.setId_offre(RS.getInt(1));
                f.setNom_offre(RS.getString("nom_offre"));
                f.setDatepub_offre(RS.getDate("datepub_offre"));
             
                 
               list.add(f);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }
  
  
  public List<Offre> recupereroffre() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        List<Offre> offres = new ArrayList<>();
        String s = "select * from offre";
        Statement st = conn.createStatement();// nesta3leha fel affichage
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Offre f = new Offre();
            f.setNom_offre(rs.getString("nom_offre"));
                f.setDatepub_offre(rs.getDate("datepub_offre"));
           
            f.setId_offre(rs.getInt("id_offre"));
            offres.add(f);

        }
        return offres;
    }
  public Offre findoffre(int id_offre) {
		Offre f = new Offre();
		try {
			String req = "Select * from offre where id_offre =" + id_offre;
			ste = conn.createStatement();

			ResultSet RS = ste.executeQuery(req);
			RS.first();
			f.setId_offre(RS.getInt("id_offre"));
			 f.setNom_offre(RS.getString("nom_offre"));
                         f.setDatepub_offre(RS.getDate("datepub_offre"));
           //     f.setDatepub_offre(RS.getDate("datepub_offre"));
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors du lecture d'un Album");
			System.out.println(ex.getMessage());
		}

		return (f != null) ? f : null;
	}
  public List<Offre> afficherOffree(int id_offre){
       List<Offre> list = new ArrayList<>();
       try {
           String req ="Select * from offre where id_offre ="+id_offre;
         
           Statement st = conn.createStatement();
           ResultSet RS = st.executeQuery(req);
           while (RS.next()) {
            Offre f = new Offre();
             f.setId_offre(RS.getInt(1));
                f.setNom_offre(RS.getString("nom_offre"));
                f.setDatepub_offre(RS.getDate("datepub_offre"));
             
                 
               list.add(f);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }
  
   public void modifieroffre (Offre F){
      
      try {
         
          String req ="UPDATE offre SET `nom_offre`='"+F.getNom_offre()+"',`datepub_offre`='"+F.getDatepub_offre()+"' WHERE id_offre="+F.getId_offre()+";";
          Statement st = conn.createStatement();
          st.executeUpdate(req);
          System.out.println("offre updated !!");
          
      }catch (SQLException ex) {
          System.out.println("offre not Updated");
          System.out.println(ex.getMessage());
      }
  }

       
}
