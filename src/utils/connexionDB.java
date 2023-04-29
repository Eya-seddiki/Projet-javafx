/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connexionDB { 
    private final String url = "jdbc:mysql://localhost:3306/test6";
    public  static String url_upload = "file:///C:////xampp////htdocs////ImageP////";
    public static String url_target = "C:/Artifact-master/public/back/img/";
    private final String login = "root";
    private final String pwd = "";
    private Connection connexion;
    private static connexionDB instance;
   
   
  
    
    private connexionDB(){
        
        try {
            connexion =  DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion �tablie!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    public static connexionDB getInstance(){
    if (instance == null)
        instance = new connexionDB();
    return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }
}