package com.company.controller;

import com.company.models.EntiteBD.Message;
import com.company.models.EntiteBD.MessageProfil;
import com.company.models.EntiteBD.Profil;
import com.company.connexionBD.ConnexionBD;

import java.sql.ResultSet;
import java.util.Date;

public class MessageController {
    ConnexionBD         cx;

    MessageProfilController mpc;
    public MessageController(){
       mpc= new MessageProfilController();
         cx=new ConnexionBD();
        cx.driver();
        cx.OpenConnexion();
    }
    public void creerMessage() {
        String reqm = "CREATE TABLE IF NOT EXISTS Message \n" +
                "(\n" +
                " id VARCHAR(255) PRIMARY KEY NOT NULL,\n" +
                " sujet VARCHAR(100),\n" +
                " contenu VARCHAR(100),\n" +
                " expéditeur INT references Profil(cin),\n" +
                " destinataire INT references Profil(cin) \n" +
                ");";
        cx.updateExec(reqm);

    }

    public void insererMessage(Message m) {
        String requetem = "INSERT INTO Message (id, sujet, contenu, expéditeur, destinataire) values ('"+ m.getId()+"','"+ m.getSujet() +"','"
                +m.getContenu()+"',"+ m.getexpediteur().getCin()+","+m.getDestinataire().getCin()+");";
        cx.updateExec(requetem);
    }
    public void PosterMessageSelfProfil(Message m, Profil p, String id) throws Exception{
        mpc.createMessageProfil();
        Date d=new Date();
        MessageProfil mp= new MessageProfil(p,m, d);
        mpc.insertMessageProfil(mp);


    }
    public void PostMessageAmisProfile(Message m,Profil p) throws Exception{
        mpc.createMessageProfil();
        Date d=new Date();
        MessageProfil mp= new MessageProfil(p,m,d);
        mpc.insertMessageProfil(mp);


    }
    public String messagesMur(Profil p) throws Exception{
        String ch="";
        String id="";
        String sujet="";
        String contenu="";
        int expediteur=0;
        int destinataire=0;
        String req="select Message.id,sujet,contenu,expéditeur,destinataire from Message "
                +" where Message.destinataire="+p.getCin()+";";
        ResultSet rs=cx.selectExec(req);
        while (rs.next()){
            id=rs.getString(1);
            sujet=rs.getString(2);
            contenu=rs.getString(3);
            expediteur=rs.getInt(4);
            destinataire=rs.getInt(5);
            ch=ch+" Le message "+id+" de sujet "+sujet+" de contenu "+contenu+" envoyé par "+expediteur+" recu par "+destinataire+"\n";
        }
        return ch;
    }

}
