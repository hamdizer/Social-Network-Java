package com.company.controller;

import com.company.models.EntiteBD.Page;
import com.company.models.EntiteBD.Profil;
import com.company.connexionBD.ConnexionBD;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class PageController {
    ConnexionBD  cx;
    ProfilController pc;
    public PageController(){
       pc=new ProfilController();
        cx=new ConnexionBD();
        cx.driver();
        cx.OpenConnexion();
    }
    public Page recuppagedenomgenre(String nom, String genre) throws Exception{

        String req="select * from Page  where nom='"+nom+"'and genre='"+genre+"';";
        ResultSet rs=cx.selectExec(req);
        String id="";
        String nomp="";
        String genrep="";
        Date datep=null;
        int cin=0;

        while(rs.next()){
            id=rs.getString(1);
            nomp=rs.getString(2);
            genrep=rs.getString(3);
            datep=rs.getDate(4);
            cin=rs.getInt(5);


        }
        return new Page(nomp,genrep,pc.recupprofildeid(cin),new java.sql.Date(datep.getTime()));
    }
    public String recuppagedenomgenreString(String nom, String genre) throws Exception{
        String req="select * from Page  where nom='"+nom+"'and genre='"+genre+"';";
        ResultSet rs=cx.selectExec(req);
        String id="";
        String nomp="";
        String genrep="";
        Date datep=null;
        int cin=0;

        while(rs.next()){
            id=rs.getString(1);
            nomp=rs.getString(2);
            genrep=rs.getString(3);
            datep=rs.getDate(4);
            cin=rs.getInt(5);


        }

        return "{\n" +
                "id :"+id+",\n " +
                "nom: "+nomp+",\n"+
                "genre: "+genrep+",\n"+
                "date:"+datep+",\n"+
                "cin: "+cin+"\n"+

                " }";    }

    public void creerPage() {
        String reqp = "CREATE TABLE IF NOT EXISTS Page \n" +
                "(\n" +
                "    idp VARCHAR(100) PRIMARY KEY NOT NULL,\n" +
                "    nom VARCHAR(100),\n" +
                "    genre VARCHAR(100),\n" +
                "    datep date,\n" +
                "    createur INT references Profil(cin) \n " +
                ");";
        cx.updateExec(reqp);

    }

    public void insererPage(Page p, Profil Createur) {
        String requetep = "INSERT INTO Page (idp,nom,genre,datep,createur) values('" + p.getId() + "','" + p.getNom() + "','"
                + p.getGenre() + "','" + p.getdatecreation() + "'," + Createur.getCin() + ");";
        cx.updateExec(requetep);

    }
    public String recupidPage(String nom,String genre) throws Exception{
        String idp="";
        String req="select * from Page where nom='"+nom+"' and genre='"+genre+"';";
        ResultSet rs=cx.selectExec(req);
        while(rs.next()){
            idp=rs.getString(1);
        }
        return idp;


    }

    public void supprimerPage(String id){
        String req="delete from Page where idp='"+id+"';";
        cx.updateExec(req);

    }

    public ArrayList<Page> PageCommune(Profil p, Profil q){
        ArrayList<Page>page=new ArrayList<>();
        for(int i=0;i<p.getPages().size();i++){
            if(q.getPages().contains(p.getAmis().get(i)))
                page.add(p.getPages().get(i));
        }
        return page;
    }
    public void insererPage(Page P) {
        String reqpg = "INSERT INTO Page (idp,nom,genre,datep,createur) values('" + P.getId() + "','" + P.getNom() + "','"
                + P.getGenre() + "','" + new java.sql.Date(P.getdatecreation().getTime()) + "'," +P.getCreateur().getCin()+");";
        cx.updateExec(reqpg);
    }
    public void updatePage(String id,String nom,String genre){

        String req="Update  Page set nom= '"+nom+"',genre='"+genre+"'where  idp='"+id+"'";
        cx.updateExec(req);

    }
}
