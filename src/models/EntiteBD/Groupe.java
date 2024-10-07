package com.company.models.EntiteBD;
import com.company.controller.ProfilController;

import java.util.ArrayList;
import java.util.Date;

public class Groupe {
    public static int nbgroupe;
    private String id;
    private String nom;
    private String genre;
    private Profil createur=new Profil();

    private int createurPers=createur.getCin();
    private Date datecreation;
    ProfilController c=new ProfilController();
    private ArrayList<Profil> Profils=new ArrayList<Profil>();
    public Groupe(String nom,String genre,Profil createur) throws Exception{
        this.id=Integer.toString(c.incrementercurrentid());
        this.nom=nom;
        this.genre=genre;
        this.createur=createur;
        this.datecreation=new Date();
     Rejoindre(createur);
     nbgroupe++;
    }
    public Groupe(String nom,String genre ,Date datecreation,Profil createur) throws Exception{
        this.id="idg "+Integer.toString(c.incrementercurrentid());
        this.nom=nom;
        this.genre=genre;
        this.createur=createur;
        this.createurPers=createur.getCin();
        this.datecreation=datecreation;
        Rejoindre(createur);

        nbgroupe++;
    }
    public Groupe(String nom,String genre ,Date datecreation,int createurPers) throws Exception{
        this.id=Integer.toString(c.incrementercurrentid());
        this.nom=nom;
        this.genre=genre;
        this.createur=createur;
        this.createurPers=createurPers;

        Rejoindre(createur);
        nbgroupe++;
    }

    public String afficheProfils(){
        String Profil="";
        for(int i=0;i<Profils.size();i++){
            Profil=Profil+Profils.get(i).getNom()+"-"+Profils.get(i).getPrenom()+"|";
        }
        return Profil;
    }
    public int getCreateurPers() {
        return createurPers;
    }
    public void setcreateurPers(int createurPers) {
        this.createurPers = createurPers;
    }
    public String  getId() {
        return id;
    }
    public String toString(){
        return "Le groupe "+id+" intitulé "+nom+" de la catégorie "+genre +"crée le "+datecreation+
      "par "+createur.getNom()+"-"+createur.getPrenom()+" et dont les Profils sont "+afficheProfils();
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
    public Profil getCreateur() {
        return createur;
    }
    public void setCreateur(Profil createur) {
        this.createur = createur;
    }

    public Date getdatecreation() {
        return datecreation;
    }
    public void setdatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }
    public ArrayList<Profil> getProfils() {
        return Profils;
    }
    public void setProfils(ArrayList<Profil> Profils) {
        this.Profils = Profils;
    }
    public void Rejoindre(Profil m){
        Profils.add(m);     m.getGroupes().add(this);
    }
    public void Sanctionner(Profil m){
        Profils.remove(m);
    }
}
