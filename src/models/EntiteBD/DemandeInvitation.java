package com.company.models.EntiteBD;

import com.company.controller.ProfilController;

import java.util.Date;


public class DemandeInvitation {
    ProfilController c=new ProfilController();
    private String id;
    private Date date;
    private Profil expediteur;
    private Profil destinataire;
    private boolean accepte=false;
    public static int nbdem=0;

    public DemandeInvitation() {
    }

    public DemandeInvitation( Profil expediteur, Profil destinataire, Date date) throws Exception{
        nbdem++;
        this.expediteur=expediteur;
        this.destinataire=destinataire;
        this.date=date;
        this.accepte=false;
this.id="idd "+Integer.toString(c.incrementercurrentid());

    }

    public DemandeInvitation( int expediteur, int destinataire, Date date) throws Exception{
        nbdem++;
        this.expediteur.setCin(expediteur);
        this.destinataire.setCin(destinataire);
        this.id= Integer.toString(c.incrementercurrentid());

        this.date=date;
        this.accepte=false;


    }
    public DemandeInvitation( Profil expediteur, Profil destinataire, Date date,boolean accepte) throws Exception{
        nbdem++;
        this.expediteur=expediteur;
        this.destinataire=destinataire;
        this.id= Integer.toString(c.incrementercurrentid());
        this.date=date;
        this.accepte=accepte;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    public boolean accepter(){
        expediteur.getAmis().add(destinataire);
        destinataire.getAmis().add(expediteur);
      this.setAccepte(true);
return true;

    }

}
