/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Interfaces.IuserController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import utils.connexionDB;

/**
 *
 * @author Seif
 */
public class UserController implements IuserController {
    Statement ste;
    Connection conn = connexionDB.getInstance().getConnexion();
    
    /**
     *
     * @param u
     */
    @Override
    public void ajouterUser(User u) {
        try {
            String req = "INSERT INTO `user` ( `email`, `roles`, `password`, `telephone`, `name`) VALUES ('"+ u.getEmail() + "','" + u.getRoles() +"','" +u.getPassword() +"','"+u.getTelephone()+"','" +u.getName()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("user ajoute");
            
            
        }catch (SQLException ex){
            System.out.println("user non ajoutee");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierUser(User u, int id) {
        try {
            String req = "UPDATE `user` SET  `email` = '"+ u.getEmail() + "', `roles` ='" + u.getRoles() +"' , `password` = '"+u.getPassword() +"' , `telephone` = '"+u.getTelephone()+"', `name` = '"+u.getName()+"' WHERE id =" +id;
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("user Updated");
            
            
        }catch (SQLException ex){
            System.out.println("user non Updated");
            System.out.println(ex.getMessage());
        }
    }
     public void modifierU (User F){
      
      try {
         
          String req ="UPDATE User SET `email`='"+F.getEmail()+"',`roles`='"+F.getRoles()+"'  , `password` = '"+F.getPassword() +"' , `telephone` = '"+F.getTelephone()+"', `name` = '"+F.getName()+"' WHERE id="+F.getId()+";";
          Statement st = conn.createStatement();
          st.executeUpdate(req);
          System.out.println("User updated !!");
          
      }catch (SQLException ex) {
          System.out.println("user not Updated");
          System.out.println(ex.getMessage());
      }
  }

    @Override
    public void supprimerUser(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id =" +id;
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("user deleted");
            
            
        }catch (SQLException ex){
            System.out.println("erreur");
            System.out.println(ex.getMessage());
        }
    
    }

    @Override
    public List<User> afficherUser() {
        List<User> list = new ArrayList<>();
        try {
            String req = "select * from user";
            Statement st = conn.createStatement();
            ResultSet RS = st.executeQuery(req);
            
            while (RS.next()) {
                User u = new User();
                u.setEmail(RS.getString("email"));
                u.setRoles(RS.getString("roles"));
                u.setPassword(RS.getString("password"));
                u.setTelephone(RS.getInt("telephone"));
                u.setName(RS.getString("name"));
                u.setId(RS.getInt(1));
                
                list.add(u);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

   
    
}

