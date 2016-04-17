/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhou.MyTools;

/**
 *
 * @author Cheng
 */
public interface CentralityMeasure<V> {

	/**
	 * Calculates the centrality given a graph.
	 * @return A map of nodes and their centrality scores
	 */
	public CentralityResult<V> calculate();
}