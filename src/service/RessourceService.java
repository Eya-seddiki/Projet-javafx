/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Reservation;
import entity.Ressource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author Narimen
 */
public class RessourceService {
    

  Connection cnx;
   // @Override
    
   public void AjouterRessource(Ressource t) {
        try {
        String qry ="INSERT INTO `Ressource`(`type_ressource`,`disponibilite_ressource`,`nom_ressource`) VALUES ('"+t.getType_ressource()+"','"+t.getDisponibilite_ressource()+"','"+t.getNom_ressource()+"')";
     cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
                
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    
    }
    
    
   
   
   
    public List<Ressource> AfficherRessource() {
        List<Ressource> Ressource = new ArrayList<>();
      try {
            String qry ="SELECT * FROM `Ressource` ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Ressource p =new Ressource();
                p.setId(rs.getInt(1));
                p.setType_ressource(rs.getString(2));
                p.setDisponibilite_ressource(rs.getString(3));
                p.setNom_ressource(rs.getString(4));

                Ressource.add(p);

            }
            return Ressource;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Ressource;
        
        
        
        
    }
      public List<Ressource> recuperer() {
        List<Ressource> list =new ArrayList<>();
        try {
            String req = "select * from Ressource";
            Statement st = cnx.createStatement();
            ResultSet rs =st.executeQuery(req);
            while(rs.next()){
                Ressource p = new Ressource();
                p.setId(rs.getInt("id"));
                p.setType_ressource(rs.getString("type_ressource"));
                p.setDisponibilite_ressource(rs.getString("disponibilite_ressource"));
                p.setNom_ressource(rs.getString("nom_ressource"));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
             public  Ressource GetById(int id) {
        return recuperer().stream().filter(e -> e.getId()== id).findFirst().get();

    }
      //@Override
    public void SupprimerRessource(Ressource r) throws SQLException {
//try {
//            String qry ="DELETE from Ressource where id = "+id+";";
//            cnx = MyDB.getInstance().getCnx();
//      
//            Statement stm =cnx.createStatement();
//            
//            stm.executeUpdate(qry);
//            
//        } catch (SQLException ex) {
//             System.out.println(ex.getMessage());
//        }  
    String req = "DELETE FROM `Ressource` WHERE id=?";
       PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, r.getId());
        ps.executeUpdate();}

    
  
   // @Override
    public void modifier(Ressource t) {
 try {
            String qry ="UPDATE Ressource SET `type_ressource`='"+t.getType_ressource()+"',`disponibilite_ressource`='"+t.getDisponibilite_ressource()+"',`nom_ressource`='"+t.getNom_ressource()+ "' WHERE id="+t.getId()+";";    
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        }

    public void AfficherRessource(Ressource ajouter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
  
 public Ressource readByTitre(String type) {
                   Ressource t = null;
         try {
             String requete = "SELECT * FROM Ressource WHERE type_ressource like '%"+type+"%'";
             
             
             Statement st = cnx.createStatement();
             ResultSet rst = st.executeQuery(requete);
             while (rst.next()) {
                 t = new Ressource(rst.getInt(1),//or rst.getInt(1)
                         rst.getString(2),
                         rst.getString(3),
                         rst.getString(4));        }
             
             
         } catch (SQLException ex) {
             Logger.getLogger(RessourceService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return t;
}
 
// public Map<String, Integer> StatistiquesRessource() {
//        Map<String, Integer> res = new HashMap<String, Integer>();
//        try {
//            String req = "Select disponibilite_ressource,count(*) from  `ressource` group by disponibilite_ressource";
//            Statement st = cnx.createStatement();
//            ResultSet RS = st.executeQuery(req);
//            while (RS.next()) {
//                res.put(RS.getString("disponibilite_ressource"), RS.getInt("count(*)"));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return res;
//    }

    public Map<String, Integer> statistiquesRessource() {
        Map<String, Integer> res = new HashMap<>();
        try {
            
            String req = "Select disponibilite_ressource,count(*) from  `ressource` group by disponibilite_ressource";
            cnx = MyDB.getInstance().getCnx();

            Statement st = cnx.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                res.put(RS.getString("disponibilite_ressource"), RS.getInt("count(*)"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }

     
}
