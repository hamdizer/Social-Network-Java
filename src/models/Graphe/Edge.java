package com.company.models.Graphe;
import com.company.models.EntiteBD.Profil;
public class Edge {
    private Profil i;//destination
    private Profil j;//source

    private int cost =1;


    public Edge(Profil i, Profil j){
        this.i=i;
        this.j=j;
        this.cost=1;
    }


    public int getCost() {
        return cost;
    }

    public Profil getJ() {
        return j;
    }
    public void setJ(Profil j) {
        this.j = j;
    }
    public Profil getI() {
        return i;
    }
    public void setI(Profil i) {
        this.i = i;
    }

    public String toString(){
        return "("+"source: "+i.getCin()+"cible :"+j.getCin()+ ")";
    }
}
