package com.company.models.EntiteBD;

import com.company.controller.ProfilController;

import java.util.Date;

public class MessageProfil {
    private String id;
    private Profil p;
    private Message m;
    private Date date;
    public static int nbmsgprofils;
    ProfilController c=new ProfilController();


    public  MessageProfil(){

    }
    public MessageProfil(Profil p,Message m,Date date) throws Exception{
        this.id=id;
        this.p=p;
        this.m=m;
        this.date=date;
        this.id="idmp "+Integer.toString(c.incrementercurrentid());
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Profil getP() {
        return p;
    }

    public void setP(Profil p) {
        this.p = p;
    }

    public Message getM() {
        return m;
    }

    public void setM(Message m) {
        this.m = m;
    }
}
