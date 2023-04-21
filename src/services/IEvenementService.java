/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IEvenementService<T> {
    
    public void ajouterEvenement(T t) throws SQLException;
    public void modifierEvenement(T t) throws SQLException;
    public void supprimerEvenement(T t) throws SQLException;
    public List<T> recupererEvenement() throws SQLException;
    
}
