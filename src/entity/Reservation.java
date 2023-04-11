/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Narimen
 */
public class Reservation {
    int id;
    Date date_debut;
    Date date_fin;
    String description_reservation;
    int ressource_id;
    int user_id;

    public Reservation() {
    }

    public Reservation(int id, Date date_debut, Date date_fin, String description_reservation, int ressource_id, int user_id) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description_reservation = description_reservation;
        this.ressource_id = ressource_id;
        this.user_id = user_id;
    }

    public Reservation(Date date_debut, Date date_fin, String description_reservation, int ressource_id, int user_id) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description_reservation = description_reservation;
        this.ressource_id = ressource_id;
        this.user_id = user_id;
    }

    public Reservation(int id, String description_reservation, int ressource_id, int user_id) {
        this.id = id;
        this.description_reservation = description_reservation;
        this.ressource_id = ressource_id;
        this.user_id = user_id;
    }

    public Reservation(String description_reservation, int ressource_id, int user_id) {
        this.description_reservation = description_reservation;
        this.ressource_id = ressource_id;
        this.user_id = user_id;
    }

    public Reservation(int id_rese, Date date_debut, Date date_fin, String descr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", description_reservation=" + description_reservation + ", ressource_id=" + ressource_id + ", user_id=" + user_id + '}';
    }

    public int getId() {
        return id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getDescription_reservation() {
        return description_reservation;
    }

    public int getRessource_id() {
        return ressource_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setDescription_reservation(String description_reservation) {
        this.description_reservation = description_reservation;
    }

    public void setRessource_id(int ressource_id) {
        this.ressource_id = ressource_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
