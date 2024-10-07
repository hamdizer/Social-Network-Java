package com.company.models.EntiteBD;
import com.company.controller.ProfilController;

import java.util.ArrayList;
import java.util.Date;

public class Page {
    private String id;
    private String nom;
    private String genre;
    private Date datecreation;
    private Profil createur=new Profil();
    private int createurpg=createur.getCin();
     static int nbpages;
     ProfilController cx=new ProfilController();
    private ArrayList<Profil> Profilsaimes = new ArrayList<Profil>();

    public Page(String nom, String genre, Profil createur) throws Exception{

        nbpages++;
        this.nom = nom;
        this.genre = genre;
        this.datecreation = new Date();
        this.createur = createur;
        this.createurpg=createur.getCin();
       this.id="idp "+cx.getCurrentid();
        cx.incrementercurrentid();

    }
    public Page(String nom, String genre, Profil createur,Date datecreation) throws Exception{
        nbpages++;
        this.nom = nom;
        this.genre = genre;
        this.datecreation = datecreation;
        this.createur = createur;
        this.createurpg=createur.getCin();
        this.id="idp "+cx.getCurrentid();
        cx.incrementercurrentid();

    }
    public Page( String nom, String genre, int createurpg) throws Exception {
        nbpages++;
        this.nom = nom;
        this.genre = genre;
        this.datecreation = new Date();
        this.createurpg=createur.getCin();
        this.id="idp "+cx.getCurrentid();
        cx.incrementercurrentid();



    }
    public Page( String nom, String genre, int createurpg,Date datecreation) throws Exception {
        this.id= Integer.toString(cx.incrementercurrentid());
        this.nom = nom;
        this.genre = genre;
        this.datecreation = datecreation;
        this.createurpg=createur.getCin();
        nbpages++;

    }

    public String getId() {
        return id;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Profil getCreateur() {
        return createur;
    }

    public void setCreateur(Profil createur) {
        this.createur = createur;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getdatecreation() {
        return datecreation;
    }

    public void setdatecration(Date datecration) {
        this.datecreation = datecration;
    }



    public ArrayList<Profil> getProfilsaimes() {
        return Profilsaimes;
    }

    public void setProfilsaimes(ArrayList<Profil> Profilsaimes) {
        this.Profilsaimes = Profilsaimes;
    }

    public Aime aimer( String type, Profil exp, Page post) throws Exception {
        return new Aime(type, exp, post);

    }

    public String toString() {
        return "La page d'id " + id + " " + nom  +
                "crée par " + createur.getNom() + "-" + createur.getPrenom() +
                " le " + datecreation + " dont les Profils qui l'aimes sont " + Profilsaimes();
    }

    public String Profilsaimes() {
        String aimes = "";
        for (int i = 0; i < Profilsaimes.size(); i++) {
            aimes = aimes + Profilsaimes.get(i).getNom() + " - " + Profilsaimes.get(i).getPrenom() + " | ";
        }
        return aimes;
    }
}


