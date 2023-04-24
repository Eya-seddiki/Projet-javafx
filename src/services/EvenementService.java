/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

//import com.sun.javafx.iio.ImageStorage.ImageType;
import entities.evenement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.MyDB;
import javafx.collections.ObservableList;

//**************//
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author asus
 */
public class EvenementService implements IEvenementService<evenement> {

    Connection cnx;
    public Statement ste;
    public PreparedStatement pst;

    public EvenementService() {
        cnx = MyDB.getInstance().getCnx();

    }

    @Override
    public void ajouterEvenement(evenement e) throws SQLException {

        String requete = "INSERT INTO `evenement` (`nom_evenement`,`type_evenement`,`image_evenement`,`lieu_evenement`,`date_debut`,`date_fin`,`nb_participants`,`color`) "
                + "VALUES (?,?,?,?,?,?,?,?);";
        try {
            pst = (PreparedStatement) cnx.prepareStatement(requete);
            pst.setString(1, e.getNom_evenement());
            pst.setString(2, e.getType_evenement());
            pst.setString(3, e.getImage_evenement());
            pst.setString(4, e.getLieu_evenement());
            pst.setDate(5, e.getDate_debut());
            pst.setDate(6, e.getDate_fin());
            pst.setInt(7, e.getNbrparticipants());
            pst.setString(8, e.getColor());
            pst.executeUpdate();
            System.out.println("event " + e.getNom_evenement() + " added successfully");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @Override
    public void modifierEvenement(evenement e) throws SQLException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "UPDATE evenement SET nom_evenement = ?,type_evenement = ?,image_evenement=?,lieu_evenement = ?,date_debut=?,date_fin=?,nb_participants=?,color=? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, e.getNom_evenement());
        ps.setString(2, e.getType_evenement());
        ps.setString(3, e.getImage_evenement());
        ps.setString(4, e.getLieu_evenement());
        ps.setDate(5, e.getDate_debut());
        ps.setDate(6, e.getDate_fin());
        ps.setInt(7, e.getNbrparticipants());
        ps.setString(8, e.getColor());
        ps.setInt(9, e.getId());
        ps.executeUpdate();
    }

    @Override
    public void supprimerEvenement(evenement e) throws SQLException {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String req = "delete from evenement where id= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, e.getId());
        ps.executeUpdate();
        System.out.println("event with id= " + e.getId()+ "  is deleted successfully");
    }

    @Override
    public List<evenement> recupererEvenement() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        List<evenement> evenements = new ArrayList<>();
        String s = "select * from evenement";
        Statement st = cnx.createStatement();// nesta3leha fel affichage
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            evenement e = new evenement();
            e.setNom_evenement(rs.getString("nom_evenement"));
            e.setType_evenement(rs.getString("type_evenement"));
            e.setImage_evenement(rs.getString("image_evenement"));
            e.setLieu_evenement(rs.getString("lieu_evenement"));
            e.setDate_debut(rs.getDate("date_debut"));
            e.setDate_fin(rs.getDate("date_fin"));
            e.setColor(rs.getString("color"));
            e.setNbrparticipants(rs.getInt("nb_participants"));
            e.setId(rs.getInt("id"));
            evenements.add(e);

        }
        return evenements;
    }

    public evenement FetchOneEvent(int id) {
        evenement event = new evenement();
        String requete = "SELECT * FROM `evenement` where id=" + id;

        try {
            ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);

            while (rs.next()) {
                event = new evenement(rs.getInt("id"), rs.getInt("nb_participants"), rs.getString("nom_evenement"), rs.getString("type_evenement"), rs.getString("image_evenement"), rs.getString("lieu_evenement"),rs.getString("color"), rs.getDate("date_debut"), rs.getDate("date_fin"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return event;
    }

    
    public ObservableList<evenement> FetchEvents() {
        ObservableList<evenement> events = FXCollections.observableArrayList();
        String requete = "SELECT * FROM `evenement`";
        try {
            ste = (Statement) cnx.createStatement();
            ResultSet rs = ste.executeQuery(requete);

            while (rs.next()) {
                events.add(new evenement(rs.getInt("id"), rs.getInt("nb_rparticipants"), rs.getString("nom_evenement"), rs.getString("type_evenement"), rs.getString("image_evenement"), rs.getString("lieu_evenement"),rs.getString("color"), rs.getDate("date_debut"), rs.getDate("date_fin")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return events;
    }
    
    public String GenerateQrEvent(evenement event) throws FileNotFoundException, IOException {
        String eventName = " event name: " + event.getNom_evenement() + "\n" + "  event type: " + event.getType_evenement() + "\n" + "   event place: " + event.getLieu_evenement() + "\n";
        ByteArrayOutputStream out = QRCode.from(eventName).to(ImageType.JPG).stream();
        String filename = event.getNom_evenement() + "_QrCode.jpg";
        //File f = new File("src\\utils\\img\\" + filename);
                File f = new File("C:\\xampp2\\htdocs\\PI\\Evenement\\imgQr\\qrcode" + filename);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        fos.flush();
       
        System.out.println("qr yemshijawou behy");
        return filename;
    }

    public ObservableList<evenement> chercherEvent(String chaine) {
        String sql = "SELECT * FROM evenement WHERE (nom_evenement LIKE ? or type_evenement LIKE ?  ) order by nom_evenement ";
        //Connection cnx= Maconnexion.getInstance().getCnx();
        String ch = "%" + chaine + "%";
        ObservableList<evenement> myList = FXCollections.observableArrayList();
        try {

            Statement ste = cnx.createStatement();
            // PreparedStatement pst = myCNX.getCnx().prepareStatement(requete6);
            PreparedStatement stee = cnx.prepareStatement(sql);
            stee.setString(1, ch);
            stee.setString(2, ch);

            ResultSet rs = stee.executeQuery();
            while (rs.next()) {
                evenement e = new evenement();

                e.setNom_evenement(rs.getString("nom_evenement"));
                e.setType_evenement(rs.getString("type_evenement"));
                e.setImage_evenement(rs.getString("image_evenement"));
                e.setLieu_evenement(rs.getString("lieu_evenement"));
                e.setDate_debut(rs.getDate("date_debut"));
                e.setDate_debut(rs.getDate("date_fin"));
                e.setNbrparticipants(rs.getInt("nb_participants"));
                e.setColor(rs.getString("color"));
                e.setId(rs.getInt("id"));

                myList.add(e);
                System.out.println("event trouvé! ");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<evenement> trierEvent()throws SQLException {
        List<evenement> evenements = new ArrayList<>();
        String s = "select * from evenement order by nom_evenement ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            evenement e = new evenement();
            e.setNom_evenement(rs.getString("nom_evenement"));
                e.setType_evenement(rs.getString("type_evenement"));
                e.setImage_evenement(rs.getString("image_evenement"));
                e.setLieu_evenement(rs.getString("lieu_evenement"));
                e.setDate_debut(rs.getDate("date_debut"));
                e.setDate_debut(rs.getDate("date_fin"));
                e.setNbrparticipants(rs.getInt("nb_participants"));
                e.setColor(rs.getString("color"));
                e.setId(rs.getInt("id"));
            evenements.add(e);
        }
        return evenements;
    }  
    
    
    
    
    
    public evenement findEvenement(int id) {
		evenement e = new evenement();
		try {
			
                        String req = "Select * from Evenement where id =" + id;
			ste = cnx.createStatement();

			ResultSet rs = ste.executeQuery(req);
			rs.first();
                e.setId(rs.getInt("id"));
                e.setNom_evenement(rs.getString("nom_evenement"));
                e.setType_evenement(rs.getString("type_evenement"));
                e.setImage_evenement(rs.getString("image_evenement"));
                e.setLieu_evenement(rs.getString("lieu_evenement"));
                e.setDate_debut(rs.getDate("date_debut"));
                e.setDate_debut(rs.getDate("date_fin"));
                e.setNbrparticipants(rs.getInt("nb_participants"));
                e.setColor(rs.getString("color"));
                
                
			ste.close();
		} catch (SQLException ex) {
			System.err.println("Probleme lors du lecture d'un Album");
			System.out.println(ex.getMessage());
		}

		return (e != null) ? e : null;
	}
    
    
}
