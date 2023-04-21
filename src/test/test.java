/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.evenement;
import entities.participation;
import java.sql.Date;
import java.sql.SQLException;
import services.EvenementService;
import services.ParticipationService;


/**
 *
 * @author asus
 */
public class test {
    
      public static void main(String[] args) {   
          
          Date d=Date.valueOf("2023-06-11");
          Date d1=Date.valueOf("2024-04-12");
        try {
            //kifeh ya9ra el orde fel base de donnée , kifeh 3raf nom event bch n3amarha f nom 
//            evenement e = new evenement(2,10,"nom21", "type21","image21.png","description21",d1,d);
//            evenement e1 = new evenement(5,1,"nom3", "type3","image3.png","description3",d,d1);
//            evenement e2 = new evenement(6,2,"nom4", "type4","image4.png","description4",d,d1);
            //evenement e3 = new evenement(5,"fffff", "nom5", "type5","image5.png","description5",d,d1);
            
           //  evenement e4=new evenement( 32,10,"nom", "type", "image", "lieu", "color1", d, d1) ;

            participation p=new participation(43,d,358,34,"hhh");
//            participation p1=new participation(d1,3,4);
//            participation p2=new participation(27,d1,55,45);

            ParticipationService ps=new ParticipationService();
           //ps.ajouterParticipation(p);
          //  ps.ajouterParticipation(p1);
           // ps.ajouterParticipation(p2);
           // ps.modifierParticipation(p2);
            ps.supprimerParticipation(p);
            System.out.println("");
            EvenementService Ev = new EvenementService();
            //Ev.ajouterEvenement(e4);  
            //Ev.ajouter(p);
          // Ev.modifierEvenement(e4);
            //Ev.supprimerEvenement(e4);
            System.out.println(Ev.recupererEvenement());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
