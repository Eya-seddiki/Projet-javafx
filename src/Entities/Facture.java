/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author chino
 */

public class Facture {
    int id , nb_science,rendezvous_id;
    String type_de_paiement;

    public Facture() {
    }


    public Facture(int id, int nb_science, String type_de_paiement, int rendezvous_id) {
        this.id = id;
        this.type_de_paiement = type_de_paiement;
        this.nb_science = nb_science;
        this.rendezvous_id = rendezvous_id;
    }

    public Facture( int nb_science, String type_de_paiement, int rendezvous_id) {
       
                this.nb_science = nb_science;
this.type_de_paiement = type_de_paiement;
        this.rendezvous_id = rendezvous_id;
    }

    public Facture( int rendezvous_id,int nb_science) {
       this.type_de_paiement = type_de_paiement;
        this.nb_science = nb_science;
    }

   


  
    public int getId() {
        return id;
    }

    public int getNb_science() {
        return nb_science;
    }

    public int getRendezvous_id() {
        return rendezvous_id;
    }

    public String getType_de_paiement() {
        return type_de_paiement;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNb_science(int nb_science) {
        this.nb_science = nb_science;
    }

    public void setRendezvous_id(int rendezvous_id) {
        this.rendezvous_id = rendezvous_id;
    }

    public void setType_de_paiement(String type_de_paiement) {
        this.type_de_paiement = type_de_paiement;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", nb_science=" + nb_science + ", rendezvous_id=" + rendezvous_id + ", type_de_paiement=" + type_de_paiement + '}';
    }
    
}
