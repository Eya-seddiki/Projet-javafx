/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author chino
 */
public class Rendezvous {
   int id,user_id;
    String nomRendezvous,prenomRendezvous,lieuRendezvous,emailRendezvous,color;
    Date dateRendezvous; 

    public Rendezvous() {
    }
    

    public Rendezvous(int id,  String nomRendezvous, String prenomRendezvous, String lieuRendezvous, String emailRendezvous, Date dateRendezvous, String color,int user_id) {
        this.id = id;
        this.nomRendezvous = nomRendezvous;
        this.prenomRendezvous = prenomRendezvous;
        this.lieuRendezvous = lieuRendezvous;
        this.emailRendezvous = emailRendezvous;
        this.dateRendezvous = dateRendezvous;
        this.color = color;
        this.user_id = user_id;
       
    }

    public Rendezvous(String nomRendezvous, String prenomRendezvous, String lieuRendezvous, String emailRendezvous, Date dateRendezvous,int user_id) {

        this.nomRendezvous = nomRendezvous;
        this.prenomRendezvous = prenomRendezvous;
        this.lieuRendezvous = lieuRendezvous;
        this.emailRendezvous = emailRendezvous;
           this.dateRendezvous = dateRendezvous;
      
        this.user_id = user_id;
     
    }
        public Rendezvous(String nomRendezvous, String prenomRendezvous, String lieuRendezvous, String emailRendezvous, Date dateRendezvous) {

        this.nomRendezvous = nomRendezvous;
        this.prenomRendezvous = prenomRendezvous;
        this.lieuRendezvous = lieuRendezvous;
        this.emailRendezvous = emailRendezvous;
           this.dateRendezvous = dateRendezvous;
 
     
    }

    public int getIdRendezvous() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public String getNomRendezvous() {
        return nomRendezvous;
    }

    public String getPrenomRendezvous() {
        return prenomRendezvous;
    }

    public String getLieuRendezvous() {
        return lieuRendezvous;
    }

    public String getEmailRendezvous() {
        return emailRendezvous;
    }

    public String getColor() {
        return color;
    }

    public Date getDateRendezvous() {
        return dateRendezvous;
    }

    public void setIdRendezvous(int id) {
        this.id = id;
    }



    public void setNomRendezvous(String nomRendezvous) {
        this.nomRendezvous = nomRendezvous;
    }

    public void setPrenomRendezvous(String prenomRendezvous) {
        this.prenomRendezvous = prenomRendezvous;
    }

    public void setLieuRendezvous(String lieuRendezvous) {
        this.lieuRendezvous = lieuRendezvous;
    }

    public void setEmailRendezvous(String emailRendezvous) {
        this.emailRendezvous = emailRendezvous;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDateRendezvous(Date dateRendezvous) {
        this.dateRendezvous = dateRendezvous;
    }

    @Override
    public String toString() {
        return "Rendezvous{" + "idRendezvous=" + id +  ", nomRendezvous=" + nomRendezvous + ", prenomRendezvous=" + prenomRendezvous + ", lieuRendezvous=" + lieuRendezvous + ", emailRendezvous=" + emailRendezvous + ", color=" + color + ", dateRendezvous=" + dateRendezvous + '}';
    }
    
}
