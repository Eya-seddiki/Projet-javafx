/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
import utils.connexionDB;

/**
 *
 * @author dell
 */
public class UserServices {
       Statement ste;
   Connection conn = connexionDB.getInstance().getConnexion();
   
 
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
    public List<User> afficherUserr( int id) {
        List<User> list = new ArrayList<>();
        try {
            String req = "select * from user where id="+id;

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
    
	public User finduser(int id) {
		User a = new User();
		try {
			String req = "Select * from user where id =" + id;
			ste = conn.createStatement();

			ResultSet RS = ste.executeQuery(req);
			RS.first();
			a.setId(RS.getInt("id"));
			a.setEmail(RS.getString("email"));
			 a.setRoles(RS.getString("roles"));
                a.setPassword(RS.getString("password"));
                a.setTelephone(RS.getInt("telephone"));
                a.setName(RS.getString("name"));
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors du lecture d'un Album");
			System.out.println(ex.getMessage());
		}

		return (a != null) ? a : null;
	}
}
