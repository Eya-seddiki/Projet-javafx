/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adodoc;
import Entities.Facture;
import Entities.Rendezvous;
import Services.ServiceFacture;
import Services.ServiceRendezvous;
import Utiles.MyDB;
import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author chino
 */
public class Adodoc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        //     A a = A.getInstance();
//     A a1 =A.getInstance();
//        System.out.println(a.hashCode());
//        System.out.println(a1.hashCode());
//        MyDB a = MyDB.getInstance();
//        MyDB a1 = MyDB.getInstance();
//        System.out.println(a.hashCode());
//        System.out.println(a1.hashCode());
        Facture p = new Facture(1,"jdid",1);
        ServiceFacture sp = new ServiceFacture();
        //sp.add(p);
       System.out.println( sp.recupererfacture());
       //  Facture p1 = new Facture(28,10,"jdid",15);
         //Facture p2 = new Facture(28,10,"jdid",15);

       //sp.modifier(p1); 
       //sp.supprimer(28); 
       Date date_rendezvous = new Date (System.currentTimeMillis());
       //Rendezvous r1 = new Rendezvous("jdiiiddd","razi","razi","razi",date_rendezvous,"razi",35);
            Rendezvous r2 = new Rendezvous(52,"aziz","razi","razi","razi",date_rendezvous,"razi",35);
        ServiceRendezvous Re = new ServiceRendezvous();
         // Re.add(r1);
       // System.out.println( Re.afficher());
        //System.out.println( Re.recupererrendezVous());
        
        //Re.modifier(r2);
       // Re.supprimer(54);



    }
    
    
}
