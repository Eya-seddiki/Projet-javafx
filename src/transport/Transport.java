/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transport;

import entity.Reservation;
import entity.Ressource;
import java.util.Date;
import service.ReservationService;
import service.RessourceService;

/**
 *
 * @author Narimen
 */
public class Transport {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
//        Ressource ajouter = new Ressource ("Mme nada","safa","safa");
//        Ressource modifier = new Ressource (2,"Mme nada","Symfony","Mobile");
//        RessourceService Re = new RessourceService();
//             
//        //Re.AjouterRessource(ajouter);
//        
//        //System.out.println(Re.AfficherRessource());
//        
//        //Re.SupprimerRessource(8);
//        
//        //Re.modifier(modifier);
//        
//        
//        
//        
//        /*----------------------------------------- TABLE RESERVATION ------------------------------------------------*/
//        
//        Date date_debut = new Date (System.currentTimeMillis());
//        Date date_fin = new Date (System.currentTimeMillis());
//        Reservation ajouterReservation = new Reservation ("Hiiiii",1,1);
      Reservation modifierReservation = new Reservation (26,null,null,"Azzouz",1,1);
//
//        
        ReservationService R = new ReservationService();
//
//        //R.AjouterReservation(ajouterReservation);
//        
//        //System.out.println(R.AfficherReservation());
//
//        //R.SupprimerReservation(17);
//        
//        
R.modifierReservation(modifierReservation);
      
        
        /*-----------------------------------------------------------------------------------------------------*/

    
      
        
        
        
    }
    
}
