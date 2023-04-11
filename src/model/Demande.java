/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Marwen.M
 */
public class Demande {
       private int id_demande,id_offre;
        private int id_user=1;
    private String cv,description,traitement="en cours de traitement";

    public Demande(int id_offre, String cv, String description) {
        this.id_offre = id_offre;
        this.cv = cv;
        this.description = description;
    }

    public Demande(int id_demande, int id_offre,int id_user, String cv, String description, String traitement) {
        this.id_demande = id_demande;
        this.id_offre = id_offre;
        this.id_user = id_user;
        this.cv = cv;
        this.description = description;
        this.traitement = traitement;
    }

    
    public Demande() {
    }

    public Demande(int id_offre,String cv, String description, String traitement) {
         this.id_offre = id_offre;
         
        this.cv = cv;
        this.description = description;
        this.traitement = traitement;
    }

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }
    
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    @Override
    public String toString() {
        return "Demande{" + "id_demande=" + id_demande + ", id_offre=" + id_offre + ", id_user=" + id_user + ", cv=" + cv + ", description=" + description + ", traitement=" + traitement + '}';
    }

    
}
