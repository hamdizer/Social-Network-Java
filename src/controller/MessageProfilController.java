package com.company.controller;
import com.company.models.EntiteBD.MessageProfil;
import com.company.connexionBD.ConnexionBD;

public class MessageProfilController {
    ConnexionBD      cx;

    public MessageProfilController(){
       cx=new ConnexionBD();
        cx.driver();
        cx.OpenConnexion();
    }
    public void createMessageProfil(){
        String reqm = "CREATE TABLE IF NOT EXISTS MessageProfil \n" +
                "(\n" +
                " id VARCHAR(100) PRIMARY KEY NOT NULL,\n" +
                " idmessage VARCHAR(100) references Message(id),\n" +
                " date VARCHAR(100) ,\n" +
                " cinprofil int references Profil(cin)\n" +
                ");";
        cx.updateExec(reqm);


    }
    public void insertMessageProfil(MessageProfil mp){
        String req = "insert into MessageProfil values('"+mp.getId()+"','"+mp.getM().getId()+"','"+mp.getDate()+"',"+mp.getP().getCin()+");";
        cx.updateExec(req);


    }
}
