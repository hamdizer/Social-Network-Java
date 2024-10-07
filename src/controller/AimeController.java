package com.company.controller;

import com.company.models.EntiteBD.AimePageProfil;
import com.company.connexionBD.ConnexionBD;

public class AimeController {
    ConnexionBD cx;
    public AimeController() {
        cx=new ConnexionBD();
        cx.OpenConnexion();
        cx.driver();


    }
    public void creerAime(){
        String req="CREATE TABLE IF NOT EXISTS Aime \n" +
                "(\n" +
                "    id int PRIMARY KEY NOT NULL,\n" +
                "    type VARCHAR(100),\n" +
                "    expediteur int references Profil(cin),\n" +
                "    page int references Page(idp)\n "+
                ");";
        cx.updateExec(req);

    }

    public void insererAimePageProfil(AimePageProfil APP){
        String req= "INSERT INTO Aime (id, pageaime, expediteur,date) values('" + APP.getId() + "','" + APP.getPageaime().getId() + "',"+
                APP.getProfil().getCin() + "," + new java.sql.Date(APP.getDateaime().getTime())+ "');";
        cx.updateExec(req);

    }
}
