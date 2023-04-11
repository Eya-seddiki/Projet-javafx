/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import services.OffreServices;
import services.DemandeServices;
import java.sql.Date;
import java.time.Clock;
import model.Demande;
import model.Offre;
import utils.connexionDB;

/**
 *
 * @author dell
 */
public class Pidevjava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //long now = System.currentTimeMillis();
        Date datepub_offre = new Date (System.currentTimeMillis());
        System.out.println(datepub_offre);
        // TODO code application logic here
        connexionDB db1 = connexionDB.getInstance();
                OffreServices f1 = new OffreServices();

       //Offre f = new Offre (6,"asma",datepub_offre);
       // Offre f2 = new Offre ("moodifie",datepub_offre);
       Offre modifier = new Offre (500,"raziiiiiii",datepub_offre);
       OffreServices A = new OffreServices();

    //f1.ajouterOffre(f5);
        //System.out.println(f1.afficherOffre());
      // f1.modifieroffre(modifier);
        //System.out.println(f1.rechProduit(455));
       //f1.supprimerOffre(499);
        //Demande D = new Demande (1,1,1,"asma","asma","asma");
        Demande D2 = new Demande (63,"azizturki","k","k");
        DemandeServices d1 = new DemandeServices();
        //d1.ajouterDemande(D2);
         // d1.ajouterDemande(D2);
         //d1.supprimerDemande(1);
       //System.out.println(d1.afficherDemande());
        Demande D1 = new Demande (3,1,1,"asmaaaaa","zizo","a");

         //d1.modifierdemande(D1);
          //System.out.println(d1.afficherDemandeparid(12));
           //System.out.println(d1.afficherDemandeparcategorie());
    }
    
}
