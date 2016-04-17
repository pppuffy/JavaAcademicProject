/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhou.MyTools;

import java.util.*;


import org.jgrapht.Graph;

import java.util.*;

/**
 *
 * @author Cheng
 */
public class BrandesBetweennessCentrality<V, E> implements CentralityMeasure<V> {

	private static <V> Map<V, List<V>> createP(Set<V> nodes) {
		Map<V, List<V>> P = new HashMap<V, List<V>>();
		for (V v : nodes) {
			P.put(v, new ArrayList<V>());
		}
		return P;
	}

	private static <V> Map<V, Integer> initIntMap(Collection<V> nodes, V s, int defval, int selval) {
		Map<V, Integer> m = new HashMap<V, Integer>();
		for (V v : nodes) {
			m.put(v, v.equals(s) ? selval : defval);
		}
		return m;
	}

	private Graph<V, E> graph;

	public BrandesBetweennessCentrality(Graph<V, E> graph) {
		this.graph = graph;
	}

	public CentralityResult<V> calculate() {

		Set<V> V = graph.vertexSet();
                
                

		Map<V, Double> CB = new HashMap<V, Double>();
		for (V v : V)     
                            CB.put(v, 0.0);
                        
			

		for (V s : V) {
                        
                     if(!s.toString().contains("@")){   
                    
			Stack<V> S = new Stack<V>();
			Map<V, List<V>> P = createP(V);
			Map<V, Integer> rho = initIntMap(V, s, 0, 1);
			Map<V, Integer> d = initIntMap(V, s, -1, 0);

			Queue<V> Q = new LinkedList<V>();

			Q.add(s);

			while (!Q.isEmpty()) {

				V v = Q.poll();
				S.push(v);

				for (E edge : graph.edgesOf(v)) {

					V w = graph.getEdgeSource(edge);
					if (w.equals(v)) {
						w = graph.getEdgeTarget(edge);
					}

					// System.out.println("v neighbour w: " + v + ", " + w + " d[w] = " + d.get(w));

					// w found for the first time?
					if (d.get(w) < 0) {
						Q.add(w);
						d.put(w, d.get(v) + 1);
					}
					// shortest path to w via v?
					if ((int) d.get(w) == (int) d.get(v) + 1) {
						rho.put(w, rho.get(w) + rho.get(v));
						P.get(w).add(v);
					}
				}
			}

			Map<V, Double> delta = new HashMap<V, Double>();
			for (V v : V)
				delta.put(v, 0.0);

			while (!S.isEmpty()) {
				V w = S.pop();
				for (V v : P.get(w)) {
					double tmp = delta.get(v) + (((double) rho.get(v) / rho.get(w)) * (1 + delta.get(w)));
                                        
					delta.put(v, tmp);
				}

				if (!w.equals(s)) {
					CB.put(w, CB.get(w) + delta.get(w));
				}
			}
		}
                  
        }

		// normalize
		/*
		double H = (V.size() - 1)*(V.size() - 2)/2;
		for (V n : CB.keySet()) {
			CB.put(n, CB.get(n) / H);
		}
		*/

        //CentralityResult<V> r = new CentralityResult<V>(CB, true);

        CentralityResult<V> r = new CentralityResult<V>(FuzzyUtil.minMaxNormalize(CB), true);

		return r;
	}
    
}
