package com.company.controller;

import com.company.connexionBD.ConnexionBD;
import com.company.models.EntiteBD.Groupe;

import java.sql.ResultSet;
import java.util.Date;

public class GroupeController {
    ConnexionBD  cx;

    ProfilController pc;
        public GroupeController(){
       pc =new ProfilController();
            cx=new ConnexionBD();
            cx.driver();
            cx.OpenConnexion();
        }
    public void creerGroupe() {
        String req = "CREATE TABLE IF NOT EXISTS Groupe \n" +
                "(\n" +
                "    idgr VARCHAR(100) PRIMARY KEY NOT NULL,\n" +
                "    nom VARCHAR(100),\n" +
                "    genre VARCHAR(100),\n" +
                "    datecréation VARCHAR(255),\n" +
                "    createur INT references Profil(cin)  \n" +
                ");";
        cx.updateExec(req);

    }
    public Groupe recupGrpdenomgenre(String nom, String genre) throws Exception{

        String req="select * from Groupe  where nom='"+nom+"'and genre='"+genre+"';";
        ResultSet rs=cx.selectExec(req);
        String id="";
        String nomg="";
        String genreg="";
        Date dategrp=new Date() ;
        int cin=0;
        boolean estMembreGroupe=false;
        boolean estMembrePage=false;

        while(rs.next()){
            id=rs.getString(1);
            nomg=rs.getString(2);
            genreg=rs.getString(3);
            dategrp=rs.getDate(4);
            cin=rs.getInt(5);


        }
        return new Groupe(nomg,genreg,new java.util.Date(dategrp.getTime()),pc.recupprofildeid(cin));
    }
    public String recupGrpdenomgenreString(String nom, String genre) throws Exception{

        String req="select * from Groupe  where nom='"+nom+"'and genre='"+genre+"';";
        ResultSet rs=cx.selectExec(req);
        String id="";
        String nomg="";
        String genreg="";
        Date dategrp=new Date() ;
        int cin=0;
        boolean estMembreGroupe=false;
        boolean estMembrePage=false;

        while(rs.next()){
            id=rs.getString(1);
            nomg=rs.getString(2);
            genreg=rs.getString(3);
            dategrp=rs.getDate(4);
            cin=rs.getInt(5);


        }
        return "{\n" +
                "id :"+id+",\n " +
                "nom: "+nomg+",\n"+
                "genre: "+genreg+",\n"+
                "date:"+dategrp+",\n"+
                "cin: "+cin+"\n"+

                " }";       }
    public void inserergroupe(Groupe g) {
        String requetegrp = "INSERT INTO Groupe (idgr,nom,genre,datecréation,createur) values('" + g.getId() + "','" + g.getNom() + "','"
                + g.getGenre() + "','" + new java.sql.Date(g.getdatecreation().getTime()) + "'," + g.getCreateur().getCin() + ");";
        cx.updateExec(requetegrp);

    }
    public void supprimerGroupe(String id){
        String req="Delete from Groupe where idgr='"+id+"';";
        cx.updateExec(req);

    }
    public String recupidgroupe(String nom,String genre) throws Exception{
        String idgrp="";
        String req="select idgr from Groupe where nom='"+nom+"' and genre='"+genre+"';";
        ResultSet rs=cx.selectExec(req);
        while(rs.next()){
            idgrp=rs.getString(1);
        }
        return idgrp;

    }
    public void updateGroupe(String id,String nom,String genre){

        String req="Update  Groupe set nom= '"+nom+"',genre='"+genre+"'where  idgr='"+id+"'";
        cx.updateExec(req);

    }

}
