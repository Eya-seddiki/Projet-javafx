/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class participation extends evenement{
    private int id;        
private Date date_participation;    
private int user_id;
public int evenement_id; 
public String description_participation;
public evenement evenement;

    public evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(evenement evenement) {
        this.evenement = evenement;
    }

    public participation() {
    }

    public participation(int id, Date date_participation, int user_id, int evenement_id, String description_participation) {
        this.id = id;
        this.date_participation = date_participation;
        this.user_id = user_id;
        this.evenement_id = evenement_id;
        this.description_participation = description_participation;
    }

    
     public participation( Date date_participation, int user_id, int evenement_id, String description_participation) {
        
        this.date_participation = date_participation;
        this.user_id = user_id;
        this.evenement_id = evenement_id;
        this.description_participation = description_participation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_participation() {
        return date_participation;
    }

    public void setDate_participation(Date date_participation) {
        this.date_participation = date_participation;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    public String getDescription_participation() {
        return description_participation;
    }

    public void setDescription_participation(String description_participation) {
        this.description_participation = description_participation;
    }
  

}
