package com.company.controller;

import com.company.models.EntiteBD.Message;
import com.company.models.EntiteBD.Mur;
import com.company.models.EntiteBD.Profil;
import com.company.connexionBD.ConnexionBD;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class MurController {
ConnexionBD cx;
    ProfilController pc;
    public MurController(){
        pc=new ProfilController();
      cx=new ConnexionBD();
        cx.driver();
        cx.OpenConnexion();
    }

    public String infoMur(String nom,String prenom ) throws Exception{
        String nomp="";
        String prenomp="";
        String sexe="";
        String email="";
        String url="";
        ArrayList<String> messageslist=new ArrayList<>();
        ArrayList<Message>messages;
        Date d = new Date();

        String req="select Profil.nom,Profil.prenom,Profil.sexe,Profil.email,Profil.url from Mur,Profil where Mur.profil=Profil.cin and Profil.nom='"+nom+"' and Profil.prenom='"+prenom+"';";
        ResultSet rs= cx.selectExec(req);


        while(rs.next()){
            nomp=rs.getString(1);
            prenomp=rs.getString(2);
            sexe=rs.getString(3);
            email=rs.getString(4);
            url=rs.getString(5);



        }

        return "{\n" +
                "nom :"+nomp+",\n " +
                "prenom: "+prenomp+",\n"+
                "sexe: "+sexe+",\n"+
                "email "+email+",\n"+
                "url "+url+",\n"+
                "\n }";
    }
    public void recupinfoMurfromidprofil(String nom,String prenom) throws Exception{
        Profil p=pc.recupProfil(nom,prenom);


    }
    public Mur recupMur(Profil p) throws Exception{
        String id="";
        int profil=0;
        Date datem=null;
        String req="Select id,profil,datem from Mur where profil="+p.getCin()+";";
        ResultSet rs=cx.selectExec(req);
        while(rs.next()){
            id=rs.getString(1);
            profil=rs.getInt(2);


        }

        return new Mur(id,profil);
    }
    public void creeMur(){
        String req="Create Table  IF NOT EXISTS Mur"+
                "(\n" +
                "    id Varchar(100)  NOT NULL PRIMARY KEY,\n" +
                "    profil int references Profil(cin),\n" +
                "    datem date \n "+
                ");";
        cx.updateExec(req);
    }


}
