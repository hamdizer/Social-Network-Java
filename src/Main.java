package com.company;

import com.company.models.EntiteBD.*;
import com.company.models.Graphe.Graph;
import com.company.controller.*;
import com.company.connexionBD.ConnexionBD;

import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        Profil pcourant = null;
        DemandeInvitation d = null;
        AmisProfil ap1;
        Profil recu=new Profil();
        String nompr="";
        String email="";
        String idp="";
        String nom = null;
        String prenom;
        String mdp="";

        int choix=0;
        try{
        ConnexionBD cx=new ConnexionBD();
        AimeController aic=new AimeController();
        AmisController amc=new AmisController();
        DemandeInviationController dic=new DemandeInviationController();
        GroupeController gc=new GroupeController();
        GroupeMembreController gmc=new GroupeMembreController();
        MessageController mc=new MessageController();
        MessageProfilController mpc=new MessageProfilController();
        MurController murc=new MurController();
        PageController pc=new PageController();
        ProfilController prc=new ProfilController();
       prc.creerProfil();
        murc.creeMur();
        mc.creerMessage();
        mpc.createMessageProfil();
        pc.creerPage();
        aic.creerAime();
        gc.creerGroupe();
        gmc.creerGroupeMembre();
        //c.initiasercurrent();

        Profil p1=new Profil(0,"Toumi","samar","femme","samar.toumi@gmail.com","mdp1","url1");

        Profil p2=new Profil(1,"Zeramdini","hamdi","homme","hamdi.zeramdini@gmail.com","mdp2","url2");

        Profil p3=new Profil(2,"nom3","prenom3","femme","email3","mdp3","url3");

        Profil p4=new Profil(3,"nom3","prenom4","femme","email4","mdp4","url4");

        Profil p5=new Profil(4,"nom5","prenom5","femme","email5","mdp5","url5");
        Profil p6=new Profil(5,"nom6","prenom6","homme","email6","mdp6","url6");
        Profil p7=new Profil(6,"nom7","prenom7","homme","email7","mdp7","url7");

            amc.creerAmis();
        dic.CreerDemandeInvit();



            prc.insererProfil(p1);
        prc.insererProfil(p2);
        prc.insererProfil(p3);
        prc.insererProfil(p4);
        prc.insererProfil(p5);
        prc.insererProfil(p6);
        prc.insererProfil(p7);

            Graph g=new Graph();
            g.ajouterProfil(p1);
            g.ajouterProfil(p2);
            g.ajouterProfil(p3);
            g.ajouterProfil(p4);
            g.ajouterProfil(p5);
            g.ajouterProfil(p6);
            g.ajouterProfil(p7);
            String chamis="";
            System.out.println(g.getAdjacencyMatrix().length);

         g.ajouterArc(p1,p2);
         g.ajouterArc(p1,p5);
         g.ajouterArc(p1,p6);
         g.ajouterArc(p2,p5);
         g.ajouterArc(p2,p7);
            g.ajouterArc(p2,p6);
            g.ajouterArc(p2,p4);
            g.ajouterArc(p3,p5);
            g.ajouterArc(p3,p7);
            g.ajouterArc(p4,p5);
            g.ajouterArc(p5,p6);






























            Scanner sc=new Scanner(System.in);
        System.out.println("entrez votre email");
        email=sc.nextLine();
        System.out.println("entrez votre mot de passe");
        mdp=sc.nextLine();


            nompr = prc.recupNomPrenomfromemail(email);

        if(prc.verifier(email ,mdp)) {
            pcourant = prc.recupprofildemailmdp(email, mdp);

            String nomprenom = prc.recupNomPrenomfromemail(email);
            String[] tab = nomprenom.split("-");
            pcourant = prc.recupProfil(tab[0], tab[1]);
            System.out.println("user identifié");
            System.out.println("bienvenue " + nompr);

            do {
                System.out.println("1:publier un message sur votre mur");
                System.out.println("2:publier un message sur le mur de votre ami");
                System.out.println("3:voir vos informations");
                System.out.println("4:voir le profil d'un ami");
                System.out.println("5:envoyer une demande invitation");
                System.out.println("6:accepter une demande invitation");
                System.out.println("7:annuler une demande invitation");
                System.out.println("8:voir le mur d'un ami");
                System.out.println("9:voir les amis de votre amis");
                System.out.println("10:suggession d'amis");
                System.out.println("11:créer page ou groupe");
                System.out.println("12:gérer page ou groupe");
                System.out.println("13:voir page ou groupe");
                System.out.println("14:Recherche");
                System.out.println("15:déconnexion");
                System.out.println("entrez votre choix");
                choix = Integer.parseInt(sc.nextLine());

                switch (choix) {
                    case 1: {
                        System.out.println("************"+pcourant.getNom()+" "+pcourant.getPrenom()+"***********");
                        System.out.println("le sujet du message :");
                        String sujet=sc.nextLine();
                        System.out.println(" le contenu du message :");
                        String contenu=sc.nextLine();
                        Message m = new Message( sujet, contenu, pcourant, pcourant);
                        mc.insererMessage(m);
                        MessageProfil mp = new MessageProfil(pcourant, m, new Date());
                        mpc.insertMessageProfil(mp);
                        String ch=mc.messagesMur(pcourant);
                        System.out.println(ch);
                        break;

                    }
                    case 2: {

                        System.out.println("donner le nom et le prenom du profil ");
                        String nomm = sc.nextLine();
                        String prenomm = sc.nextLine();
                        Profil p = prc.recupProfil(nomm, prenomm);

                        if(amc.estAmisAvec(p,pcourant)){
                            System.out.println("donne le sujet du message");
                            String sujet=sc.nextLine();
                            System.out.println("donne le contenu du message");
                            String contenu=sc.nextLine();
                            Message m = new Message( sujet, contenu, pcourant, p);
                            mc.insererMessage(m);
                            MessageProfil mp = new MessageProfil(p, m, new Date());
                            mpc.insertMessageProfil(mp);
                            String ch=mc.messagesMur(p);
                            System.out.println(ch);

                        }
                        else{
                            System.out.println("vous n'êtes pas authorisé à envoyer un message à "+p.getNom()+"-"+p.getPrenom());
                        }
                        break;
                    }
                    case 3: {
                        try {
                            String ch = prc.infos(pcourant.getNom(), pcourant.getPrenom());
                            System.out.println("vos informations sont :");
                            System.out.println(ch);
                            System.out.println("vos amis sont:");
                            ArrayList<Profil> a=amc.amis(pcourant);
                            System.out.println("le nombre de vos amis est "+a.size());
                            for (int i=0;i<a.size();i++){
                                System.out.println(a.get(i).getNom()+" "+a.get(i).getPrenom());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("donner le nom et prenom du profil dont vous souhaitez voir le profil");
                         nom = sc.nextLine();
                         prenom = sc.nextLine();
                        recu = prc.recupProfil(nom, prenom);
                        if (amc.estAmisAvec(recu, pcourant))
                            System.out.println(prc.infos(recu.getNom(), recu.getPrenom()));

                        else
                            System.out.println("vous n'êtes pas authorisé à voir le profil");

                    break;
                }
                case 5: {
                    String ch = "";
                    System.out.println("donner le nom et prenom du profil à envoyer l'invitation");
                    nom = sc.nextLine();
                     prenom = sc.nextLine();
                      recu = prc.recupProfil(nom, prenom);
                   d = new DemandeInvitation( pcourant, recu, new Date());
                   String id=d.getId();
                    dic.insererDemandeInvit(d);




                    break;

                }
                    case 6:
                        System.out.println("donner l'id de la demande à accepter");
                        String idd=sc.nextLine();
                        DemandeInvitation deminvit=dic.recupDemandeInvitdeid(idd);
                        if(deminvit.getDestinataire().getCin()==pcourant.getCin()){
                        dic.accepterDemandeInvit(idd);
                        Thread.sleep(5000);
                        DemandeInvitation dem=dic.recupDemandeInvitdeid(idd);
                        dem.accepter();
                        AmisProfil ap = new AmisProfil( prc.recupprofildeid(dem.getExpediteur().getCin()), prc.recupprofildeid(dem.getDestinataire().getCin()));
                        AmisProfil ap2= new AmisProfil( prc.recupprofildeid(dem.getDestinataire().getCin()), prc.recupprofildeid(dem.getExpediteur().getCin()));
                        amc.insererAmisProfil(ap);

                            g.ajouterArc(recu,prc.recupprofildeid(dem.getDestinataire().getCin()));
                            amc.insererAmisProfil(ap2);
                            g.ajouterArc(prc.recupprofildeid(dem.getDestinataire().getCin()),recu);
                        pcourant.setAmis(amc.amis(pcourant));
                        }
                        else System.out.println("la demande ne vous appartient pas");
                        break;


                case 7: {
                    String ch="";
                    System.out.println("donner l'id de l'invitation à annuler ");
                    String id = sc.nextLine();

                    DemandeInvitation deminv=dic.recupDemandeInvitdeid(id);


                    if(pcourant.getCin()==deminv.getDestinataire().getCin()){
                        dic.annulerDemandeInvit(id);
                    }
                    else
                        System.out.println("inauthorisé");

                    break;

                }
                case 8: {
                    System.out.println("donner le nom et prenom du profil dont vous souhaitez voir le mur");
                     nom = sc.nextLine();
                     prenom = sc.nextLine();
                     recu = prc.recupProfil(nom, prenom);

                    if (amc.estAmisAvec(recu,pcourant)||amc.estAmisAvec(recu,pcourant)){
                        System.out.println(murc.infoMur(nom,prenom));
String ch=mc.messagesMur(recu);
                        System.out.println(ch);
                        ArrayList<Profil> a=amc.amis(prc.recupProfil(nom,prenom));
                        System.out.println("Les amis de votre ami "+prc.recupProfil(nom,prenom).getNom()+" "+prc.recupProfil(nom,prenom).getPrenom()+" sont");
                        for (int i=0;i<a.size();i++){
                            System.out.println(a.get(i).getNom()+" "+a.get(i).getPrenom());
                        }
                    }
                    else
                        System.out.println("vous n'êtes pas authorisé à voir le profil");
                break;
                }
                    case 9:{
                      System.out.println("donner le nom et le prénom du l'ami");
                      String noma=sc.nextLine();
                        String prenoma=sc.nextLine();


                        ArrayList<Profil> amis= amc.amis(prc.recupProfil(noma,prenoma));
                       if(amc.estAmisAvec(pcourant,prc.recupProfil(noma,prenoma))||amc.estAmisAvec(prc.recupProfil(noma,prenoma),pcourant)){
                           System.out.println("Les amis  de "+noma+"-"+prenoma);
                           for(int i=0;i<amis.size();i++) {
                               pcourant.getAmis().add(prc.recupprofildeid(amis.get(i).getCin()));
                               System.out.println(amis.get(i).getNom() + "-" + amis.get(i).getPrenom());

                       }}
                       else
                           System.out.println("Un utilisateur ne peut voir que les amis de ses amis ");

                   break;
                    }
                    case 10:{
                        for(int i=0;i<prc.nbProfils();i++){
                            ArrayList<Integer> it=amc.recupid2(i+1);
                            for(int j=0;j<it.size();j++){
                                g.ajouterArc(prc.recupprofildeid(i),prc.recupprofildeid(it.get(j)));
                            }
                        }
                        g.dijkstra(pcourant.getCin());
                        break;
                    }
                    case 11:{
                        System.out.println("1:Creer page");
                        System.out.println("2:Creer groupe");

                        int ch=Integer.parseInt(sc.nextLine());
                        switch(ch){
                            case 1:{
                                System.out.println("entrer le nom et le genre du page ");
                                System.out.println("nom du page");
                                 nom=sc.nextLine();
                                System.out.println("genre du page");
                                String genre=sc.nextLine();

                                Page p=new Page(nom,genre,pcourant);
                                pc.insererPage(p);
                                prc.controlMembrePage(pcourant);
                             break;
                            }
                            case 2:{
                                System.out.println("entrer le nom et le genre du groupe ");
                                System.out.println("nom du groupe");
                                nom=sc.nextLine();
                                System.out.println("genre du groupe");
                                String genre=sc.nextLine();
                                Groupe gr=new Groupe(nom,genre,pcourant);
                                gc.inserergroupe(gr);
                                prc.controlMembreGroupe(pcourant);
                                break;
                            }
                        }

                    }
                    break;
                    case 12:
                        System.out.println("1:gerer page");
                        System.out.println("2:gerer groupe");
                        int choixpg=Integer.parseInt(sc.nextLine());
                        switch(choixpg) {
                            case 1:
                                System.out.println("1:modifier page");
                                System.out.println("2: supprimer page");
                                int choix3 = Integer.parseInt(sc.nextLine());
                                switch (choix3) {
                                    case 1:
                                        System.out.println("donner le nom et le genre de la page à modifier");
                                        System.out.println("nom du page à modifier");
                                        String nomp = sc.nextLine();
                                        System.out.println("genre du page à modifier");
                                        String genrep = sc.nextLine();
                                        System.out.println("nouveau nom");
                                        String newnomp = sc.nextLine();
                                        System.out.println("nouveau genre");
                                        String newgenrep = sc.nextLine();
                                        Page p = pc.recuppagedenomgenre(nomp, genrep);
                                        p.setNom(newnomp);
                                        p.setGenre(newgenrep);
                                        idp = pc.recupidPage(nomp, genrep);
                                        pc.updatePage(idp, newnomp, newgenrep);

                                        break;

                                    case 2:
                                        System.out.println("donner le nom et le genre de la page à supprimer");
                                        System.out.println("nom du page à supprimer");
                                        nomp = sc.nextLine();
                                        System.out.println("genre du page à supprimer");
                                        genrep = sc.nextLine();
                                        String id = pc.recupidPage(nomp, genrep);
                                        pc.supprimerPage(id);

                                        break;
                                }
                            case 2:
                                System.out.println("1:modifier groupe");
                                System.out.println("2:supprimer groupe");
                                System.out.println("3:ajouter profil au groupe");

                                int choix4 = Integer.parseInt(sc.nextLine());
                            switch (choix4) {
                                case 1:
                                    System.out.println("donner le nom et le genre du groupe à modifier");
                                    System.out.println("nom du groupe à modifier");
                                    String nomgr = sc.nextLine();
                                    System.out.println("genre du groupe à modifier");
                                    String genregr = sc.nextLine();
                                    System.out.println("nouveau nom");
                                    String newnomg = sc.nextLine();
                                    System.out.println("nouveau genre");
                                    String newgenreg = sc.nextLine();
                                    Groupe gr = gc.recupGrpdenomgenre(nomgr,genregr);
                                    gr.setNom(newnomg);
                                    gr.setGenre(newgenreg);
                                    idp = gc.recupidgroupe(nomgr, genregr);
                                    gc.updateGroupe(idp, newnomg, newgenreg);

                                    break;


                                case 2:
                                    System.out.println("donner le nom et le genre de la groupe à supprimer");
                                    System.out.println("nom du groupe à supprimer");
                                    nomgr = sc.nextLine();
                                    System.out.println("genre du groupe à supprimer");
                                    genregr = sc.nextLine();
                                    String id = gc.recupidgroupe(nomgr, genregr);
                                    gc.supprimerGroupe(id);

                                    break;
                                case 3:
                                    System.out.println("ajouter profil au groupe");
                                    System.out.println("donner le nom et le genre du groupe");
                                    System.out.println("nom du groupe");
                                    String nomg = sc.nextLine();
                                    System.out.println("genre du groupe");
                                    String genre = sc.nextLine();
                                    System.out.println("nom du membre à ajouter");
                                    nom = sc.nextLine();
                                    System.out.println("prenom du membre à ajouter");
                                    prenom = sc.nextLine();
                                    Profil pr = prc.recupProfil(nom, prenom);

                                    GroupeMembre gm = new GroupeMembre(gc.recupidgroupe(nomg, genre), pr.getCin());
                                    gmc.insererGroupeMembre(gm);
                                    Groupe groupe = gc.recupGrpdenomgenre(nomg, genre);
                                    groupe.getProfils().add(pr);
                                    pr.getGroupes().add(groupe);


                                    break;
                            }


                        }
                        break;
                    case 13:{
                        System.out.println("1: voir page");
                        System.out.println("2: voir groupe");
                        int choixp=Integer.parseInt(sc.nextLine());

                        switch (choixp){
                            case 1: System.out.println("donner le nom et le genre de la page à consulter");
                                System.out.println("nom du page ");
                                String  nomp=sc.nextLine();
                                System.out.println("genre du page");
                                String genrep=sc.nextLine();
                              String  id=pc.recupidPage(nomp,genrep);
                              String ch=pc.recuppagedenomgenreString(nomp,genrep);
                              System.out.println(ch);
                              break;
                            case 2:
                                System.out.println("donner le nom et le genre du groupe à consulter");
                                System.out.println("nom du groupe ");
                                String  nomg=sc.nextLine();
                                System.out.println("genre du groupe");
                                String genreg=sc.nextLine();
                                String chg=gc.recupGrpdenomgenreString(nomg,genreg);
                                System.out.println(chg);
                                break;

                        }



                        break;
                    }
                    case 14:{
                        ArrayList<Profil> profils=prc.getFriendSearching(g);
                        for(int i=0;i<profils.size();i++){
                            System.out.println(profils.get(i).toString());
                        }
                        break;
                    }
                    case 15:{
                        System.out.println("Au revoir!");
                        System.exit(0);
                        break;
                    }




            }



        }
            while(true);




        }


    }catch (Exception e){
        e.printStackTrace();
    }


}


}
