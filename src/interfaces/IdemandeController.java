/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import model.Demande;


/**
 *
 * @author Marwen.M
 */
public interface IdemandeController {
     public void ajouterDemande(Demande D);
     public void supprimerDemande(int id_demande);
     public List<Demande> afficherDemande();
     public List<Demande>afficherDemandeparid(int id_demande);
     public List<Demande>afficherDemandeparcategorie(String traitement);
    public void modifierOffre(Demande  D , int id_demande);

}
