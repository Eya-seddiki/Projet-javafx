/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import static javafx.scene.input.KeyCode.T;
import Entities.Rendezvous;
import java.sql.SQLException;

/**
 *

 * @author chino
 */
public interface Iservice<T> {
     public void add(Rendezvous r)throws SQLException;
    public List<T> afficher() throws SQLException;
   // public void modifier(T t);
    //public void supprimer(T t)throws SQLException;
//    public List<T>RechercheAnnonces(String noms);
}
