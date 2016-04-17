/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhou.MyGraph;

import com.zhou.MyTools.CentralityResult;
import com.zhou.MyTools.BrandesBetweennessCentrality;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;

public class MyGraph implements Serializable {

    private SimpleWeightedGraph<String, DefaultWeightedEdge> graph;
    private HashMap hash;

    public MyGraph() {
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        hash = new HashMap();

    }

    public SimpleWeightedGraph<String, DefaultWeightedEdge> getGraph() {
        return graph;
    }

    public void myAddEdge(String s1, String s2) {

        if (!hasVertex(s1)) {
            graph.addVertex(s1);
        }
        if (!hasVertex(s2)) {
            graph.addVertex(s2);
        }

        if (!hasEdge(s1, s2)) {
            DefaultWeightedEdge e1 = graph.addEdge(s1, s2);
            graph.setEdgeWeight(e1, 1);
        } else {
            DefaultWeightedEdge e1 = graph.getEdge(s1, s2);
            double newWeight = graph.getEdgeWeight(e1) + 1;
            graph.setEdgeWeight(e1, newWeight);
        }

    }

    public Boolean hasVertex(String s) {
        if (graph.containsVertex(s)) {
            return true;
        }
        return false;
    }

    public Boolean hasEdge(String s1, String s2) {
        if (!hasVertex(s1)) {
            graph.addVertex(s1);
        }
        if (!hasVertex(s2)) {
            graph.addVertex(s2);
        }
        if (graph.containsEdge(s1, s2)) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        SimpleWeightedGraph<String, DefaultWeightedEdge> g = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";
        String v5 = "v5";


        // add the vertices
        
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v1);
        g.addVertex(v5);
        
        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v5);
        
        BrandesBetweennessCentrality bb=new BrandesBetweennessCentrality(g);
        CentralityResult<String> cr = bb.calculate();
        
        
        List list=cr.getSorted();
        
        Iterator it=list.iterator();
        
        while(it.hasNext()){
            System.out.println(it.next());
        }
        

  

    }
}
