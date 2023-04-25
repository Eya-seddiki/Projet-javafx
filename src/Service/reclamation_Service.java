/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.ISerivce;
import Utils.MyConnexion;
import entites.reclamation;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author 21656
 */
public class reclamation_Service implements ISerivce<reclamation> {

    private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(reclamation t) throws SQLException {
        try {
            String requete = "INSERT INTO `reclamation`( `nom_reclamation`, `prenom_reclamation`, `destination_reclamation`, `description_reclamation`, `type_reclamation`, `id_user`) VALUES (?,?,?,?,?,?)";

            // binding vaalue 
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, t.getNom_reclamation());
            pst.setString(2, t.getPrenom_reclamation());
            pst.setString(3, t.getDestination_reclamation());
            pst.setString(4, t.getDescription_reclamation());
            pst.setString(5, t.getType_reclamation());
            pst.setInt(6, t.getId_user());
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void Supprimer(int t) throws SQLException {
        String requete = "DELETE FROM `reclamation` WHERE `id_reclamation`=" + String.valueOf(t) + "";
        try {

            PreparedStatement pst = c.prepareStatement(requete);
            pst.execute(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void Modifier(reclamation t, int id) throws SQLException {
        try {
            String requete = "UPDATE `reclamation` SET `nom_reclamation`=?,`prenom_reclamation`=?',`destination_reclamation`=?,`description_reclamation`=?,`type_reclamation`=?,`id_user`=? WHERE `id_reclamation`=?";

            // binding vaalue 
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, t.getNom_reclamation());
            pst.setString(2, t.getPrenom_reclamation());
            pst.setString(3, t.getDestination_reclamation());
            pst.setString(4, t.getDescription_reclamation());
            pst.setString(5, t.getType_reclamation());
            pst.setInt(6, t.getId_user());
            pst.setInt(7, id);
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<reclamation> Affichertout() throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new reclamation(rs.getInt("id_reclamation"), rs.getString("nom_reclamation"), rs.getString("prenom_reclamation"), rs.getString("destination_reclamation"), rs.getString("description_reclamation"), rs.getString("type_reclamation"), rs.getInt("id_user")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public ObservableList<reclamation> Affichertout_user(int id) throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (id == rs.getInt("id_user")) {
                    list.add(new reclamation(rs.getInt("id_reclamation"), rs.getString("nom_reclamation"), rs.getString("prenom_reclamation"), rs.getString("destination_reclamation"), rs.getString("description_reclamation"), rs.getString("type_reclamation"), rs.getInt("id_user")));

                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public ObservableList<reclamation> serach(String cas) throws SQLException {
        ObservableList<reclamation> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` where  id_reclamation LIKE '%" + cas + "%'or nom_reclamation LIKE '%" + cas + "%' or  prenom_reclamation LIKE '%" + cas + "%' or  destination_reclamation LIKE '%" + cas + "%' or  description_reclamation LIKE '%" + cas + "%' or  type_reclamation LIKE '%" + cas + "%' ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new reclamation(rs.getInt("id_reclamation"), rs.getString("nom_reclamation"), rs.getString("prenom_reclamation"), rs.getString("destination_reclamation"), rs.getString("description_reclamation"), rs.getString("type_reclamation"), rs.getInt("id_user")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public ObservableList<Integer> Reclamation_ids() throws SQLException {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `reclamation` ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getInt("id_reclamation"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public void Traitee(int id) throws SQLException {
        try {
            String requete = "UPDATE `reclamation` SET `type_reclamation`=?WHERE `id_reclamation`=?";

            // binding vaalue 
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, "Traite");

            pst.setInt(2, id);
            pst.execute();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String email_user_Traitee(int id) throws SQLException {
        String email = "";
        try {
            String requete = "SELECT u.email FROM reclamation rc INNER JOIN user u where rc.id_user = u.id and rc.id_reclamation=?";
            PreparedStatement ps = c.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.first()) {
                 email = rs.getString("email");
                break;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return email;
    }
}
