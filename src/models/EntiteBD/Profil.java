package com.company.models.EntiteBD;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import com.company.controller.ProfilController;
public class Profil implements Comparable<Profil> {
    ProfilController c=new ProfilController();
    public static int nbprofils=0;
    private int cin;
    private String nom;
    private String prenom;
    private String sexe;
    private String email;
    private String mdp;
    private String url;
    private Mur mur;

    private boolean estMembreGroupe;
    private boolean estMembrePage;
   // private ArrayList<Message>messages;
    private  ArrayList<Page> pages=new ArrayList<Page>();
    LinkedList<Profil> ShortestPath=new LinkedList<Profil>();
   private ArrayList<Profil> amis=new ArrayList<Profil>();
    private ArrayList<Groupe> groupes=new ArrayList<Groupe>();
    public Profil(){
    }

    public Mur getMur() {
        return mur;
    }

    public void setMur(Mur mur) {
        this.mur = mur;
    }

    public Profil(int cin, String nom, String prenom, String sexe, String email, String mdp, String url) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.mdp = mdp;
        this.url=url;
        this.estMembreGroupe=false;
        this.estMembrePage=false;
         //messages=new ArrayList<Message>();
      this.mur = new Mur("idmur"+Mur.nbmur,this);
         nbprofils++;
    }
    public Profil(int cin, String nom, String prenom, String sexe, String email, String mdp,String url,boolean estMembrePage,boolean estMembreGroupe) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.mdp = mdp;
        this.url=url;
        this.estMembreGroupe=estMembreGroupe;
        this.estMembrePage=estMembrePage;
        //messages=new ArrayList<Message>();
        this.mur = new Mur("idmur"+Mur.nbmur,this);


        nbprofils++;
    }
    public Profil(int cin, String nom, String prenom, String sexe, String email,String url,boolean estMembrePage,boolean estMembreGroupe) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.url=url;
        this.estMembreGroupe=estMembreGroupe;
        this.estMembrePage=estMembrePage;
        this.mur = new Mur("idmur"+Mur.nbmur,this);

        nbprofils++;
    }
    public Profil(int cin, String nom, String prenom, String sexe, String email, String mdp) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.email = email;
        this.mdp = mdp;
        this.mur = new Mur("idmur"+Mur.nbmur,this);

        nbprofils++;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getCin() {
        return cin;
    }
    public void setCin(int cin) {
        this.cin = cin;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public LinkedList<Profil> getShortestPath() {
        return ShortestPath;
    }
    public void setShortestPath(LinkedList<Profil> shortestpath) {
        ShortestPath = shortestpath;
    }
    public ArrayList<Profil> getAmis() {
        return amis;
    }
    public void setAmis(ArrayList<Profil> amis) {
        this.amis = amis;
    }

    public boolean isEstMembreGroupe() {
        return estMembreGroupe;
    }

    public void setEstMembreGroupe(boolean estMembreGroupe) {
        this.estMembreGroupe = estMembreGroupe;
    }
    public boolean isEstMembrePage() {
        return estMembrePage;
    }

    public void setEstMembrePage(boolean estMembrePage) {
        this.estMembrePage = estMembrePage;
    }

    //public ArrayList<Message> getMessages() {
     //   return messages;
    //}

    //public void setMessages(ArrayList<Message> messages) {
     //   this.messages = messages;
   // }
    public ArrayList<Page> getPages() {
        return pages;
    }
    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
    }
    public ArrayList<Groupe> getGroupes() {
        return groupes;
    }
    public void setGroupes(ArrayList<Groupe> groupes) {
        this.groupes = groupes;
    }
    public DemandeInvitation envoyerDemande(Profil m, String idinvit, Profil d) throws Exception{
        return new DemandeInvitation(m,d,new Date());
    }
    public String toString(){
        return "Le profil de cin "+getCin()+" "+getNom()+" "+getPrenom()+"est un "+getSexe()+
                " d'adresse email "+getEmail()+"d'url "+getUrl();
    }



    @Override
    public int compareTo(Profil o) {
        return 0;
    }
}
