package com.company.controller;

import com.company.models.EntiteBD.DemandeInvitation;
import com.company.connexionBD.ConnexionBD;

import java.sql.ResultSet;
import java.util.Date;

public class DemandeInviationController {
    ProfilController pc;
    ConnexionBD    cx;

    public DemandeInviationController(){
        pc=new ProfilController();
       cx=new ConnexionBD();
        cx.driver();
        cx.OpenConnexion();
    }
    public String recupidDemannde(String id) throws Exception{
        String idgrp="";
        String req="select id from Groupe where id='"+id+"';";
        ResultSet rs=cx.selectExec(req);
        while(rs.next()){
            idgrp=rs.getString(1);
        }
        return idgrp;

    }
    public void CreerDemandeInvit(){
        String req="Create Table IF NOT EXISTS DemandeInvitation"+
                "(\n" +
                "    id VARCHAR(200) PRIMARY KEY NOT NULL,\n" +
                "    dateinvit date,\n" +
                "    expediteur int references Profil(cin),\n" +
                "    destinataire int references Profil(cin),\n "+
                "    accepte BOOLEAN\n "+
                ");";
        cx.updateExec(req);
    }
    public void insererDemandeInvit(DemandeInvitation d){
        int accepte=0;
        if(d.isAccepte())
            accepte=1;
        else accepte=0;


        String reqd = "INSERT INTO DemandeInvitation (id,dateinvit,expediteur,destinataire,accepte) values('" + d.getId() + "','" + new java.sql.Date(d.getDate().getTime()) + "',"
                + d.getExpediteur().getCin() + "," + d.getDestinataire().getCin() +","+d.isAccepte()+");";
        cx.updateExec(reqd);
    }
    public void accepterDemandeInvit(String id){
        String req="Update DemandeInvitation  set accepte = true  where accepte=false and id='"+id+"';";
        cx.updateExec(req);
    }

    public DemandeInvitation recupDemandeInvitdeid(String id) throws Exception{

        String req="select * from DemandeInvitation  where id='"+id+"';";
        ResultSet rs=cx.selectExec(req);
        String idd="";
        Date dateinvit=null;
        int expediteur=0;
        int destinataire=0;
        boolean accepte=false;

        while(rs.next()){
            idd=rs.getString(1);
            dateinvit=rs.getDate(2);
            expediteur=rs.getInt(3);
            destinataire=rs.getInt(4);
            accepte=rs.getBoolean(5);


        }
        return new DemandeInvitation(pc.recupprofildeid(expediteur),pc.recupprofildeid(destinataire),dateinvit,accepte);}
    public void annulerDemandeInvit(String idd){
        String req = "DELETE from DemandeInvitation where id='"+idd+"';";
        cx.updateExec(req);
    }
}
