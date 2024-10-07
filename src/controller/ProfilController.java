package com.company.controller;

import com.company.models.EntiteBD.Mur;
import com.company.models.EntiteBD.Profil;
import com.company.models.Graphe.Graph;
import com.company.connexionBD.ConnexionBD;

import java.sql.ResultSet;
import java.util.*;

public class ProfilController {
    ConnexionBD cx;
    public ProfilController(){
        cx=new ConnexionBD();
        cx.driver();
     cx.OpenConnexion();


    }
    public void creerProfil() {
        String req = "CREATE TABLE IF NOT EXISTS Profil \n" +
                "(\n" +
                " Cin INT PRIMARY KEY NOT NULL,\n" +
                " nom VARCHAR(100),\n" +
                " prenom VARCHAR(100),\n" +
                " sexe VARCHAR(255),\n" +
                " email VARCHAR(255),\n" +
                " mdp VARCHAR(255) ,\n" +
                "url VARCHAR(255) ,\n"+
                "estMembrePage BOOLEAN ,\n"+
                "estMembreGroupe BOOLEAN \n"+

                ");";
        cx.updateExec(req);

    }

    public void insererProfil(Profil p) throws Exception {
        String reqprofil="select cin from Profil where cin= "+p.getCin();
        ResultSet rs=cx.selectExec(reqprofil);
        if(!rs.next()) {
            String requetepr = "INSERT INTO Profil (cin,nom,prenom,sexe,email,mdp,url,estMembrePage,estMembregroupe) values(" + p.getCin() + ",'" + p.getNom() + "','"
                    + p.getPrenom() + "','" + p.getSexe() + "','" + p.getEmail() + "','" + p.getMdp() + "','" + p.getUrl() + "'," + p.isEstMembrePage() + "," + p.isEstMembreGroupe() + ");";
            System.out.println(cx.updateExec(requetepr));
            insererMur(p.getMur());
        }
    }
    public void insererMur(Mur m) throws Exception{
        String reqprofil="select profil from Mur where profil= "+m.getProfil();
        ResultSet rs=cx.selectExec(reqprofil);
        if(!rs.next()){
        String req = "insert into Mur values ('"+m.getId()+"',"+m.getProfil().getCin()+",'"+new java.sql.Date(m.getDate().getTime())+"');";
        cx.updateExec(req);}
    }
    public boolean verifier(String email, String mdp) {

        boolean v = false;
        try {
            String reqverif = "Select email,mdp  from Profil where email='" + email + "' and mdp='" + mdp + "';";
            ResultSet rs = cx.selectExec(reqverif);

            while (rs.next()) {

                v = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;

    }
    public boolean verifierid(String nom, String prenom) {

        boolean v = false;
        try {
            String reqverifid = "Select nom,prenom  from Profil where nom='" + nom + "' and prenom='" + prenom + "';";
            ResultSet rs = cx.selectExec(reqverifid);

            while (rs.next()) {

                v = true;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;

    }
    public String infos(String nomp,String prenomp) throws Exception{
        int cin=0;
        String nom="";
        String prenom="";
        String sexe="";
        String email="";
        String url="";
        boolean estMembreGroupe;
        boolean estMembrePage;
        String req="select cin,nom,prenom,sexe,email,url,estMembreGroupe,EstMembrePage from Profil where nom='"+nomp+"'and prenom='"+prenomp+"';";
        ResultSet rs= cx.selectExec(req);

        while(rs.next()){
            cin=rs.getInt(1);
            nom=rs.getString(2);
            prenom=rs.getString(3);
            sexe=rs.getString(4);
            email=rs.getString(5);
            url=rs.getString(6);
            estMembreGroupe=rs.getBoolean(7);
            estMembrePage=rs.getBoolean(8);


        }
        return "{\n" +
                "cin :"+cin+" " +
                ",\n nom: "+nom+
                " ,\n prenom: "+prenom+
                " ,\n sexe: "+sexe+
                "'\n email: "+email+
                "'\n url :"+url+
                "\n }";
    }
    public String recupNomPrenomfromemail(String email) throws Exception {

        String nom="";
        String prenom="";
        String nompr="";
        String req = "select nom ,prenom from Profil where email='"+email +"';";
        ResultSet rs = cx.selectExec(req);
        while (rs.next()) {

            nom=rs.getString(1);
            prenom=rs.getString(2);


        }


        return nom+"-"+prenom;


    }
    public int  nbProfils() throws Exception{
        int nb=0;
        String req=" select  count(*) as nbProfils from Profil";
         ResultSet rs=cx.selectExec(req);
         while (rs.next()){
             nb=rs.getInt(1);
         }
return nb;
    }
    public Profil recupprofildeid(int id ) throws Exception{
        String req="select * from Profil where cin="+id+";";
        ResultSet rs=cx.selectExec(req);
        int cin=0;
        String nomp="";
        String prenomp="";
        String sexe="";
        String email="";
        String url="";
        boolean estMembreGroupe=false;
        boolean estMembrePage=false;

        while(rs.next()){
            cin=rs.getInt(1);
            nomp=rs.getString(2);
            prenomp=rs.getString(3);
            sexe=rs.getString(4);
            email=rs.getString(5);
            url=rs.getString(7);
            estMembreGroupe=rs.getBoolean(8);
            estMembrePage=rs.getBoolean(9);

        }
        return new Profil(cin,nomp,prenomp,sexe,email,url,estMembrePage,estMembreGroupe);
    }
    public Profil recupProfil(String nom,String prenom) throws Exception{
        String req="select * from Profil  where nom='"+nom+"'and prenom='"+prenom+"';";
        ResultSet rs=cx.selectExec(req);
        int cin=0;
        String nomp="";
        String prenomp="";
        String sexe="";
        String email="";
        String url="";
        boolean estMembreGroupe=false;
        boolean estMembrePage=false;

        while(rs.next()){
            cin=rs.getInt(1);
            nomp=rs.getString(2);
            prenomp=rs.getString(3);
            sexe=rs.getString(4);
            email=rs.getString(5);
            url=rs.getString(7);
            estMembreGroupe=rs.getBoolean(8);
            estMembrePage=rs.getBoolean(9);

        }
        return new Profil(cin,nomp,prenomp,sexe,email,url,estMembrePage,estMembreGroupe);
    }
    /*public String profilAmis(Profil p) throws Exception{
        int cin=0;
        String nom="";
        String prenom="";
        String sexe="";
        String email="";
        String url="";
        String req="select cin,nom,prenom,sexe,email,url from Profil where nom='"+p.getNom()+"';";
        ResultSet rs= cx.selectExec(req);

        while(rs.next()){
            cin=rs.getInt(1);
            nom=rs.getString(2);
            prenom=rs.getString(3);
            sexe=rs.getString(4);
            email=rs.getString(5);
            url=rs.getString(6);

        }
        return "{\n" +
                "cin :"+cin+" " +
                ",\n nom: "+nom+
                " ,\n prenom: "+prenom+
                " ,\n sexe: "+sexe+
                "'\n email: "+email+
                "'\n url :"+url+
                "\n }";
    }*/
    /*public String profilAmis(String nomp,String prenomp) throws Exception{
        int cin=0;
        String nom="";
        String prenom="";
        String sexe="";
        String email="";
        String url="";
        boolean estMembreGroupe=false;
        boolean estMembrePage=false;
        String req="select cin,nom,prenom,sexe,email,url from Profil where nom='"+nom+"'and prenom='"+prenomp+"';";
        ResultSet rs= cx.selectExec(req);

        while(rs.next()){
            cin=rs.getInt(1);
            nom=rs.getString(2);
            prenom=rs.getString(3);
            sexe=rs.getString(4);
            email=rs.getString(5);
            url=rs.getString(6);
            estMembreGroupe=rs.getBoolean(7);
            estMembrePage=rs.getBoolean(8);

        }
        return "{\n" +
                "cin :"+cin+" " +
                ",\n nom: "+nom+
                " ,\n prenom: "+prenom+
                " ,\n sexe: "+sexe+
                "'\n email: "+email+
                "'\n url :"+url+
                "'\n MembreGroupe? :"+estMembreGroupe+
                "'\n MembrePage? :"+estMembrePage+
                "\n }";
    }*/
    public void controlMembrePage(Profil p){
        System.out.println(p.getPages().size());
        if(p.getPages().size()>0){
            p.setEstMembrePage(true);
            if(!p.isEstMembrePage()){
                String req="Update Profil Set estMembrePage= true where cin="+p.getCin();
                cx.updateExec(req);
            }
        }
    }
    public void controlMembreGroupe(Profil p){
        System.out.println(p.getGroupes().size());
        if(p.getGroupes().size()>0){
            if(p.isEstMembreGroupe()==false){
            p.setEstMembreGroupe(true);
            System.out.println(p.isEstMembreGroupe());

                String req="Update Profil Set estMembreGroupe=true where cin="+p.getCin();
                cx.updateExec(req);
            }
        }}
    public ArrayList<Profil> getFriendSearching(Graph socialNetwork) {
        System.out.println("entrer nom d'utilisateur cherché");
        Scanner sc = new Scanner(System.in);
        String NomUserSearched = sc.nextLine();



        boolean visited[] = new boolean[socialNetwork.getSommets().size()];
        ArrayList<Profil> userFound = new ArrayList<Profil>();
        Arrays.fill(visited,false); // initialisation de tableau visited a false
        backtrack1(socialNetwork.getSommets(),NomUserSearched,visited,userFound,false,socialNetwork.getSommets().size(),0);
        return userFound;

    }
    public void backtrack1(ArrayList<Profil> users, String name, boolean[] visited, List<Profil> userFound, boolean q, int nbrTotal, int index) {

        for (Profil p : users) {
            if (q) break;
            if (!visited[p.getCin()])
            {
                visited[p.getCin()] = true;
                if (nbrTotal != index)
                {
                    if (p.getNom().equals(name))
                    {
                        userFound.add(p);
                    } else {
                        backtrack1(p.getAmis(), name, visited, userFound, q, nbrTotal, index++);
                        if (!q)
                        {
                            visited[p.getCin()] = false;
                        }
                    }

                } else {
                    q = true;
                }
            }

        }
    }
    public int getCurrentid() throws Exception{
        int id=0;
        String req="select idcurrentid from currentid ;";
        ResultSet rs=cx.selectExec(req);
        while (rs.next()){
            id=rs.getInt(1);
        }
        return id;
    }
    public int incrementercurrentid() throws Exception{
    int id=(getCurrentid()+1);
        String req2="Update currentid set idcurrentid ="+id+";";
        cx.updateExec(req2);
        return id;
    }
    public void resetCurrentid ()throws Exception{
        String req2="Update currentid set idcurrentid ="+0+";";
        cx.updateExec(req2);
    }



    public Profil recupprofildemailmdp(String email,String mdp) throws Exception{
        Profil pcourant;
        String nomprenom=recupNomPrenomfromemail(email);
        String [] tab=nomprenom.split("-");
        pcourant=recupProfil(tab[0],tab[1]);

        return pcourant;
    }



}
