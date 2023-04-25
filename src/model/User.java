/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dell
 */
public class User {
     private int id;
    private String email;
    private String roles;
    private String password;
    private int telephone ;
    private String name;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
      
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User( String email, String roles, String password, int telephone, String name) {
        
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.telephone = telephone;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", roles=" + roles + ", password=" + password + ", telephone=" + telephone + ", name=" + name + '}';
    }
    
    
}
