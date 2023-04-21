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
public class evenement {
    
        private int id;
        private int nbrparticipants;
    private String nom_evenement,type_evenement,image_evenement,lieu_evenement,color;
    private Date date_debut,date_fin;

    public evenement() {
    }

    public evenement(int id, int nbrparticipants, String nom_evenement, String type_evenement, String image_evenement, String lieu_evenement, String color, Date date_debut, Date date_fin) {
        this.id = id;
        this.nbrparticipants = nbrparticipants;
        this.nom_evenement = nom_evenement;
        this.type_evenement = type_evenement;
        this.image_evenement = image_evenement;
        this.lieu_evenement = lieu_evenement;
        this.color = color;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    public evenement( int nbrparticipants, String nom_evenement, String type_evenement, String image_evenement, String lieu_evenement, String color, Date date_debut, Date date_fin) {
    
        this.nbrparticipants = nbrparticipants;
        this.nom_evenement = nom_evenement;
        this.type_evenement = type_evenement;
        this.image_evenement = image_evenement;
        this.lieu_evenement = lieu_evenement;
        this.color = color;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", nbrparticipants=" + nbrparticipants + ", nom_evenement=" + nom_evenement + ", type_evenement=" + type_evenement + ", image_evenement=" + image_evenement + ", lieu_evenement=" + lieu_evenement + ", color=" + color + ", date_debut=" + date_debut + ", date_fin=" + date_fin + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrparticipants() {
        return nbrparticipants;
    }

    public void setNbrparticipants(int nbrparticipants) {
        this.nbrparticipants = nbrparticipants;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public String getType_evenement() {
        return type_evenement;
    }

    public void setType_evenement(String type_evenement) {
        this.type_evenement = type_evenement;
    }

    public String getImage_evenement() {
        return image_evenement;
    }

    public void setImage_evenement(String image_evenement) {
        this.image_evenement = image_evenement;
    }

    public String getLieu_evenement() {
        return lieu_evenement;
    }

    public void setLieu_evenement(String lieu_evenement) {
        this.lieu_evenement = lieu_evenement;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }
    
    

    
    
}
