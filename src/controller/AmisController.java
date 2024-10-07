package com.company.controller;

import com.company.models.EntiteBD.AmisProfil;
import com.company.models.EntiteBD.Profil;
import com.company.connexionBD.ConnexionBD;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AmisController {
    ConnexionBD cx;
    ProfilController pc=new ProfilController();


    public AmisController() {
        cx = new ConnexionBD();
        cx.driver();
        cx.OpenConnexion();
    }

    public boolean estAmisAvec(Profil p, Profil q) {

        boolean v = false;
        try {
            String reqverif = "Select id1,id2  from Amis" + (p.getCin() + 1) + " where id1=" + p.getCin() + " and id2=" + q.getCin() + ";";
            ResultSet rs = cx.selectExec(reqverif);
            String reqverif2 = "Select id1,id2 from Amis" + (q.getCin() + 1) + " where id1=" + q.getCin() + " and id2=" + p.getCin() + ";";
            ResultSet rs2 = cx.selectExec(reqverif2);

            while (rs.next() && rs2.next()) {

                v = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;

    }

    public void creerAmis() {
        for (int i = 0; i < Profil.nbprofils; i++) {
            String reqamis = "CREATE TABLE IF NOT EXISTS Amis" + (i + 1) + " \n" +
                    "(\n" +
                    "idamis Varchar(100)PRIMARY KEY NOT NULL,\n" +
                    "id1 Int ,\n" +
                    "id2 Int ,\n " +
                    "dateamis date \n " +
                    ");";
            cx.updateExec(reqamis);
        }
    }

    public void Amis(Profil p) {
        for (int i = 0; i < p.getAmis().size(); i++) {

            String req = "CREATE TABLE Amis" + (i + 1) + "\n" +
                    "(\n" +
                    "    ida VARCHAR(100) PRIMARY KEY NOT NULL,\n" +
                    "    expediteur int references Profil(cin),\n" +
                    "    destinataire int references Profil(cin),\n" +
                    "    dateam date\n" +

                    ");";
            cx.updateExec(req);
        }
    }

    public void insererAmisProfil(AmisProfil ap) throws Exception {

        String req="Select id1,id2 from Amis"+ (ap.getExpediteur().getCin() + 1) +" where id1 = "+ap.getExpediteur().getCin()+" and id2 = "+ap.getDestinataire().getCin()+";";
        ResultSet rs=cx.selectExec(req);
        if(!rs.next()){
            String requetea = "INSERT INTO Amis" + (ap.getExpediteur().getCin() + 1) + " (idamis,id1,id2,dateamis) values ('" + ap.getId() + "',"
                    + ap.getExpediteur().getCin() + "," + ap.getDestinataire().getCin() + ",'" + new java.sql.Date(ap.getD().getTime()) + "');";
            cx.updateExec(requetea);
        }
    }

    public ArrayList<Integer> amidamis(Profil q) throws Exception {
        //ArrayList<Profil>p=new ArrayList<>();
        ArrayList<Integer> j = new ArrayList<>();
        ArrayList<Profil> i = amis(q);

        for (int it = 0; it < i.size(); it++) {
            String req2 = "select id1,id2 from amis"+(i.get(it).getCin()+ 1)+ " where id1 = " + i.get(it).getCin() + ";";
            ResultSet rs2 = cx.selectExec(req2);
            while (rs2.next()) {
                int cin1 = rs2.getInt(1);
                int cin2 = rs2.getInt(2);
                if (!j.contains(cin2) && cin2 != q.getCin()) {
                    j.add(cin2);
                }

            }



        }
        for(int c=0;c<i.size();c++)
            j.remove((Object)i.get(c).getCin());
        return j;
    }
public ArrayList<Integer> recupid2(int i) throws Exception{
        ArrayList<Integer> it=new ArrayList<>();
        String req="select id2 from Amis"+i+";";
        ResultSet rs=cx.selectExec(req);
        while (rs.next()){
            int id2=rs.getInt(1);
            if(!it.contains(id2))
                it.add(id2);
        }
        return it;
}

    public ArrayList<Profil> amis(Profil q) throws Exception {
        ArrayList<Profil> amis = new ArrayList<>();
        int cin = q.getCin();
        String req = "select id1,id2 from amis" + (cin + 1) + " where id1 =" + cin + ";";
        ResultSet rs = cx.selectExec(req);
        while (rs.next()) {
            int cin1 = rs.getInt(1);
            int cin2 = rs.getInt(2);
            if(!amis.contains(pc.recupprofildeid(cin2)))
            amis.add(pc.recupprofildeid(cin2));
        }
        return amis;

    }
}
