package com.company.models.EntiteBD;

import com.company.controller.ProfilController;

import java.util.Date;

public class AimePageProfil {
    private String id;
    private Profil  profil;
    private Page pageaime;
    private Date dateaime;
    ProfilController c=new ProfilController();
    public AimePageProfil(String id,Profil profil,Page pageaime) throws Exception{
        this.id="idap "+Integer.toString(c.incrementercurrentid());
        this.profil=profil;
        this.pageaime=pageaime;
        this.dateaime=new Date();

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Page getPageaime() {
        return pageaime;
    }

    public void setPageaime(Page pageaime) {
        this.pageaime = pageaime;
    }

    public Date getDateaime() {
        return dateaime;
    }

    public void setDateaime(Date dateaime) {
        this.dateaime = dateaime;
    }
}
