package com.company.models.EntiteBD;

import java.util.Date;
import com.company.controller.ProfilController;
public class Message  {
    private String contenu;
    private  String id;
    private  String sujet;
    private  Profil expediteur;
    private Profil destinataire;
    private Date d;
    public     static  int nbbmessages;
    ProfilController c=new ProfilController();
    public Message(String sujet,String contenu,Profil expediteur,Profil destinataire) throws Exception{
      this.id=id;
this.sujet=sujet;
this.contenu=contenu;
this.expediteur=expediteur;
this.destinataire=destinataire;
this.contenu=contenu;
this.d=new Date();
this.id="idm "+Integer.toString(c.incrementercurrentid());
       nbbmessages++;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }
    public void setSujet(String sujet) {
        this.sujet = sujet;
    }
    public Profil getexpediteur() {
        return expediteur;
    }
    public void setexpediteur(Profil expediteur) {
        this.expediteur = expediteur;
    }
    public Profil getDestinataire() {
        return destinataire;
    }
    public void setDestinataire(Profil destinataire) {
        this.destinataire = destinataire;
    }
    public String toString(){
        return "Le message "+id+" dérit par "+contenu+" emi de "+expediteur.getNom()+"-"+expediteur.getPrenom()+
                " recu par "+destinataire.getNom()+"-"+destinataire.getPrenom();
}

 

    public String getContenu() {
        return contenu;
    }
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
