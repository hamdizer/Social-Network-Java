package com.company.models.EntiteBD;

import com.company.controller.ProfilController;

import java.util.Date;

public class AmisProfil {
    private String id;
    private Profil expediteur;
    private Profil destinataire;
    private Date d;
    ProfilController pc=new ProfilController();
    public static int nbamispr;
    public AmisProfil(Profil expediteur,Profil destinataire)throws Exception{
        this.id="idap "+Integer.toString(pc.incrementercurrentid());
        this.destinataire=destinataire;
        this.expediteur
                =expediteur;
       d=new Date();
       nbamispr++;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profil getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Profil expediteur) {
        this.expediteur = expediteur;
    }

    public Profil getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(Profil destinataire) {
        this.destinataire = destinataire;
    }
}
