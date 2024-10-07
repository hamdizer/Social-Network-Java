package com.company.controller;

import com.company.models.EntiteBD.GroupeMembre;
import com.company.connexionBD.ConnexionBD;

public class GroupeMembreController {
    ConnexionBD  cx;

    public GroupeMembreController(){
        cx=new ConnexionBD();
        cx.driver();

        cx.OpenConnexion();
    }
    public void creerGroupeMembre(){
        String reqm = "CREATE TABLE IF NOT EXISTS GroupeMembre \n" +
                "(\n" +
                " id Varchar(100) PRIMARY KEY NOT NULL,\n" +
                " idgrp VARCHAR(255) references Groupe(idg),\n" +
                " dategm date ,\n" +
                " cinprofil int references Profil(cin)\n" +
                ");";
        cx.updateExec(reqm);
    }
    public void insererGroupeMembre(GroupeMembre gm){
        String req= "INSERT INTO GroupeMembre (id, idgrp, cinprofil, dategm) values(' "+ gm.getId() + "','" + gm.getGroupe() + "',"+
                gm.getProfil() + ",'" +new java.sql.Date(gm.getDategm().getTime())+ "');";
        cx.updateExec(req);
    }
}
