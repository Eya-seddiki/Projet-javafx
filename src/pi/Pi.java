/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Controller.UserController;
import model.User;
import utils.connexionDB;

/**
 *
 * @author Seif
 */
public class Pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        connexionDB db = connexionDB.getInstance();
        
      //  User u = new User (22,"hjxxx@gmail.com","","123456",14545875,"seif");
        
      //String req = "INSERT INTO `user` ( `email`, `roles`, `password`, `telephone`, `name`) VALUES ('"+ u.getEmail() + "','" + u.getRoles() +"','" +u.getPassword() +"','"+u.getPassword()+"','"+u.getTelephone()+"','" +u.getName()+"')";

        UserController u1 = new UserController();
       // u1.ajouterUser(u);
        //System.out.println(u1.afficherUser());
       // u1.supprimerUser(51);
       // u1.modifierUser(u,46);
    }
    
}
