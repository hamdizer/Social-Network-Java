package com.company.models.Graphe;
import com.company.controller.AmisController;
import com.company.controller.ProfilController;
import com.company.models.EntiteBD.AmisProfil;
import com.company.models.EntiteBD.Profil;
import com.company.connexionBD.ConnexionBD;

import java.util.*;

public class Graph {
    private static final int NO_PARENT = -1;
    private ArrayList<Profil> sommets;
     int[][] adjacencyMatrix;

    private ArrayList<Edge> arcs;
    private AmisController am=new AmisController();
    ConnexionBD cx;
    ProfilController pc=new ProfilController();
    AmisController amc=new AmisController();
    public ArrayList<Profil> getSommets() {
        return sommets;
    }
    public void setSommets(ArrayList<Profil> sommets) {
        this.sommets = sommets;
    }
    public ArrayList<Edge> getArcs() {
        return arcs; }
    public void setArcs(ArrayList<Edge> arcs) {
        this.arcs = arcs;
    }
    public Graph() throws Exception {
        sommets = new ArrayList<Profil>();
        arcs = new ArrayList<Edge>();
        adjacencyMatrix=new int[pc.nbProfils()][pc.nbProfils()];
    for(int i=0;i<sommets.size();i++){
      for(int j=0;j<sommets.size();j++){
          adjacencyMatrix[i][j]=0;
      }
    }
    }
    public Graph(int n) {
        sommets = new ArrayList<Profil>(n);
        arcs = new ArrayList<Edge>(); }
    public void ajouterProfil(Profil m) {
        sommets.add(m);
    }
    public void supprimerProfil(Profil m) {
        sommets.remove(m);
    }
    public void ajouterArc(Profil src, Profil dst) throws Exception {
        Edge e = new Edge(src, dst);
        arcs.add(e);
        this.adjacencyMatrix[src.getCin()][dst.getCin()]=1;
        this.adjacencyMatrix[dst.getCin()][src.getCin()]=1;
        AmisProfil ap=new AmisProfil(src,dst);
        amc.insererAmisProfil(ap);
        AmisProfil ap2=new AmisProfil(dst,src);
        amc.insererAmisProfil(ap2);




    }

    public ArrayList<Profil> Suggerer(Profil profil) throws Exception {
        Profil courant;
        ArrayList<Profil> amis = new ArrayList<>();
        ArrayList<Profil> amis1 = new ArrayList<>();
        amis = amc.amis(profil);
        for (int i = 0; i < amis.size(); i++) {
            amis1 = amc.amis(amis.get(i));
        }
        for (int i = 0; i < amis1.size(); i++) {
            ArrayList<Profil> ami = amc.amis(amis1.get(i));
            for (int j = 0; j < ami.size(); j++) {
                if (!amis1.contains(ami.get(j)))
                    amis.add(ami.get(j));
            }


        }
        return amis;

    }
public int[][] getAdjacencyMatrix(){
        return adjacencyMatrix;
}
    public int[][] setAdjacencyMatrix(int [][] adjacencyMatrix){
        return this.adjacencyMatrix=adjacencyMatrix;
    }


    public void supprimer(Edge e) {
        arcs.remove(e);
        System.out.println("Le membre " + e.getI().getNom() + " - " + e.getI().getPrenom() +
                " est amis avec " + e.getJ().getNom() + " - " + e.getJ().getPrenom());
    }
    public ArrayList<Profil> voisins(Profil src) {
        ArrayList<Profil> voisins = new ArrayList<Profil>();
        for (int i = 0; i < arcs.size(); i++) {
            if (arcs.get(i).getI() == src) {
                voisins.add(arcs.get(i).getJ());
            }
        }
        System.out.println(voisins.size());
        return voisins;
    }


//suggession
public   void dijkstra(
                             int startVertex)
{
    int nVertices = adjacencyMatrix[0].length;


    int[] shortestDistances = new int[nVertices];


    boolean[] added = new boolean[nVertices];


    for (int vertexIndex = 0; vertexIndex < nVertices;
         vertexIndex++)
    {
        shortestDistances[vertexIndex] = Integer.MAX_VALUE;
        added[vertexIndex] = false;
    }


    shortestDistances[startVertex] = 0;


    int[] parents = new int[nVertices];


    parents[startVertex] = NO_PARENT;

    for (int i = 1; i < nVertices; i++)
    {


        int nearestVertex = -1;
        int shortestDistance = Integer.MAX_VALUE;
        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++)
        {
            if (!added[vertexIndex] &&
                    shortestDistances[vertexIndex] <
                            shortestDistance)
            {
                nearestVertex = vertexIndex;
                shortestDistance = shortestDistances[vertexIndex];
            }
        }


        added[nearestVertex] = true;


        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++)
        {
            int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

            if (edgeDistance > 0
                    && ((shortestDistance + edgeDistance) <
                    shortestDistances[vertexIndex]))
            {
                parents[vertexIndex] = nearestVertex;
                shortestDistances[vertexIndex] = shortestDistance +
                        edgeDistance;
            }
        }
    }

    printSolution(startVertex, shortestDistances, parents);
    System.out.println();
}
    public   void printSolution(int startVertex,
                                      int[] distances,
                                      int[] parents)
    {
        int nVertices = distances.length;
        System.out.print("Utilisateur courant\t\t Distance\t\tamis intermédiaire");

        for (int vertexIndex = 0;
             vertexIndex < nVertices;
             vertexIndex++)
        {
            if (vertexIndex != startVertex&&distances[vertexIndex]>1)
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t\t\t\t\t");
                System.out.print(distances[vertexIndex] + "\t\t\t\t\t");
                printPath(vertexIndex, parents);
            }
        }
    }


    public   void printPath(int currentVertex,
                                  int[] parents)
    {


        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.print(currentVertex + " ");
    }




    }















