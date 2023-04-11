/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Objects;

/**
 *
 * @author 21656
 */
public class reclamation {
    private int id_reclamation;
    private String nom_reclamation;
    private String prenom_reclamation;
    private String destination_reclamation;
    private String description_reclamation;
    private String type_reclamation;
    private int id_user;

    public reclamation() {
    }

    public reclamation(int id_reclamation, String nom_reclamation, String prenom_reclamation, String destination_reclamation, String description_reclamation, String type_reclamation, int id_user) {
        this.id_reclamation = id_reclamation;
        this.nom_reclamation = nom_reclamation;
        this.prenom_reclamation = prenom_reclamation;
        this.destination_reclamation = destination_reclamation;
        this.description_reclamation = description_reclamation;
        this.type_reclamation = type_reclamation;
        this.id_user = id_user;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getNom_reclamation() {
        return nom_reclamation;
    }

    public void setNom_reclamation(String nom_reclamation) {
        this.nom_reclamation = nom_reclamation;
    }

    public String getPrenom_reclamation() {
        return prenom_reclamation;
    }

    public void setPrenom_reclamation(String prenom_reclamation) {
        this.prenom_reclamation = prenom_reclamation;
    }

    public String getDestination_reclamation() {
        return destination_reclamation;
    }

    public void setDestination_reclamation(String destination_reclamation) {
        this.destination_reclamation = destination_reclamation;
    }

    public String getDescription_reclamation() {
        return description_reclamation;
    }

    public void setDescription_reclamation(String description_reclamation) {
        this.description_reclamation = description_reclamation;
    }

    public String getType_reclamation() {
        return type_reclamation;
    }

    public void setType_reclamation(String type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    public reclamation(String nom_reclamation, String prenom_reclamation, String destination_reclamation, String description_reclamation, String type_reclamation, int id_user) {
        this.nom_reclamation = nom_reclamation;
        this.prenom_reclamation = prenom_reclamation;
        this.destination_reclamation = destination_reclamation;
        this.description_reclamation = description_reclamation;
        this.type_reclamation = type_reclamation;
        this.id_user = id_user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final reclamation other = (reclamation) obj;
        if (this.id_reclamation != other.id_reclamation) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.nom_reclamation, other.nom_reclamation)) {
            return false;
        }
        if (!Objects.equals(this.prenom_reclamation, other.prenom_reclamation)) {
            return false;
        }
        if (!Objects.equals(this.destination_reclamation, other.destination_reclamation)) {
            return false;
        }
        if (!Objects.equals(this.description_reclamation, other.description_reclamation)) {
            return false;
        }
        if (!Objects.equals(this.type_reclamation, other.type_reclamation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {//affichage
        return "reclamation{" + "id_reclamation=" + id_reclamation + ", nom_reclamation=" + nom_reclamation + ", prenom_reclamation=" + prenom_reclamation + ", destination_reclamation=" + destination_reclamation + ", description_reclamation=" + description_reclamation + ", type_reclamation=" + type_reclamation + ", id_user=" + id_user + "} \r\n";
    }
    
    
}
