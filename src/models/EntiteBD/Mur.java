package com.company.models.EntiteBD;

import java.util.ArrayList;
import java.util.Date;

public class Mur {
    public static int nbmur=0;
    private String id;
    private Profil profil=new Profil();
    private int cin=profil.getCin();
    private ArrayList<Message> messages=new ArrayList<>();
    private Date date;



    public Mur(String id,Profil profil) {
        this.id = id;
        this.profil = profil;
        this.messages=new ArrayList<>();
        this.date=new Date();
        nbmur++;

    }
    public Mur(String id,int p) {
        this.id = id;
        this.cin = p;
        this.messages=new ArrayList<>();
        this.date=new Date();
        nbmur++;

    }
/*public void afficheMessages(){
        for(int i=0;i<messages.size();i++){
            System.out.println(messages.get(i).toString());
        }
}*/
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

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}
