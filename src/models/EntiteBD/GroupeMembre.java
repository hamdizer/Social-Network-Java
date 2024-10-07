package com.company.models.EntiteBD;

import com.company.controller.ProfilController;

import java.util.Date;

public class GroupeMembre {
    private String groupe;
    private int profil;
    private Date dategm;
    private String id;
    public static int nbgm;
    ProfilController c=new ProfilController();

    public GroupeMembre(String groupe, int profil) throws Exception {
        this.groupe = groupe;
        this.profil = profil;
        this.dategm=new Date();
        nbgm++;
        this.id="idgm"+Integer.toString(c.incrementercurrentid());

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public int getProfil() {
        return profil;
    }

    public void setProfil(int profil) {
        this.profil = profil;
    }

    public Date getDategm() {
        return dategm;
    }

    public void setDategm(Date dategm) {
        this.dategm = dategm;
    }
}
