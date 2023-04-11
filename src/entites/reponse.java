/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author 21656
 */
public class reponse {
    private int id_reponse;
    private String reponse;
    private int id_reclamation;

    public reponse(int id_reponse, String reponse, int id_reclamation) {
        this.id_reponse = id_reponse;
        this.reponse = reponse;
        this.id_reclamation = id_reclamation;
    }

    public reponse(String reponse, int id_reclamation) {
        this.reponse = reponse;
        this.id_reclamation = id_reclamation;
    }

    @Override
    public String toString() {
        return "reponse{" + "id_reponse=" + id_reponse + ", reponse=" + reponse + ", id_reclamation=" + id_reclamation + "} \n";
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    
    
}
