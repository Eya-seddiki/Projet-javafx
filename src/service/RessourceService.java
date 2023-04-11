/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Ressource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
 
  
      
}
