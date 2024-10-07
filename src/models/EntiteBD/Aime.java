package com.company.models.EntiteBD;

import com.company.controller.ProfilController;

public class Aime {
    private String id;
    private String type;
    private Profil expediteur;
    private Page page;
    ProfilController cx=new ProfilController();
    public Aime(String type,Profil expediteur,Page page)throws Exception{
        this.id="ida"+Integer.toString(cx.incrementercurrentid());
         this.type=type;
         this.expediteur=expediteur;
         this.page=page;
         expediteur.getPages().add(page);


    }
    public String toString(){
        return "L'aime d'id "+id+" de type "+type+" envoyé par "+expediteur.getNom()+"-"+expediteur.getPrenom()+
                "concerne la publication "+page.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Profil getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Profil expediteur) {
        this.expediteur = expediteur;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
