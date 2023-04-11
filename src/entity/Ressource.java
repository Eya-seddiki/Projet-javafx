/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Narimen
 */
public class Ressource {

    
    
    
    int id;
    String type_ressource;
    String disponibilite_ressource;
    String nom_ressource;

    public Ressource() {
    }

    public Ressource(int id, String type_ressource, String disponibilite_ressource, String nom_ressource) {
        this.id = id;
        this.type_ressource = type_ressource;
        this.disponibilite_ressource = disponibilite_ressource;
        this.nom_ressource = nom_ressource;
    }

    public Ressource(String type_ressource, String disponibilite_ressource, String nom_ressource) {
        this.type_ressource = type_ressource;
        this.disponibilite_ressource = disponibilite_ressource;
        this.nom_ressource = nom_ressource;
    }

    @Override
    public String toString() {
        return "Ressource{" + "id=" + id + ", type_ressource=" + type_ressource + ", disponibilite_ressource=" + disponibilite_ressource + ", nom_ressource=" + nom_ressource + '}';
    }

    public int getId() {
        return id;
    }

    public String getType_ressource() {
        return type_ressource;
    }

    public String getDisponibilite_ressource() {
        return disponibilite_ressource;
    }

    public String getNom_ressource() {
        return nom_ressource;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType_ressource(String type_ressource) {
        this.type_ressource = type_ressource;
    }

    public void setDisponibilite_ressource(String disponibilite_ressource) {
        this.disponibilite_ressource = disponibilite_ressource;
    }

    public void setNom_ressource(String nom_ressource) {
        this.nom_ressource = nom_ressource;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
