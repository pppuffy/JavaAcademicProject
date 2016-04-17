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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import jdk.nashorn.internal.ir.PropertyNode;



public class MapSortingHelper {

	public static <T extends Object, V> List<V> stripValues(List<Entry<V, T>> list) {
		List<V> r = new ArrayList<V>();
		for (Entry<V, ?> e : list) {
			r.add(e.getKey());
		}
		return r;
	}

/*
	public static Double sum(Class<Double> clz, Collection<Double> collection) {
		double sum = 0.0;
		for (Double d : collection) {
			sum += d;
		}
		return sum;
	}

	public static Integer sum(Collection<Integer> collection) {
		int sum = 0;
		for (Integer i : collection) {
			sum += i;
		}
		return sum;
	}

	public static Long sum(Collection<Long> collection) {
		long sum = 0;
		for (Long l : collection) {
			sum += l;
		}
		return sum;
	}
*/

	public static <V> List<Entry<V, Double>> sortedListD(Map<V, Double> map) {

		List<Entry<V, Double>> list = new ArrayList<Entry<V, Double>>();
		list.addAll(map.entrySet());

		Collections.sort(list, CMP_DOUBLE);
		Collections.reverse(list);

		return list;
	}

	public static List<Entry<PropertyNode, Integer>> sortedListI(Map<PropertyNode, Integer> map) {

		List<Entry<PropertyNode, Integer>> list = new ArrayList<Entry<PropertyNode, Integer>>();
		list.addAll(map.entrySet());

		Collections.sort(list, CMP_INTEGER);
		Collections.reverse(list);

		return list;
	}

	public static CompareDouble CMP_DOUBLE = new CompareDouble();
	public static CompareInteger CMP_INTEGER = new CompareInteger();

	public static class CompareDouble<V> implements Comparator<Entry<V, Double>> {
		@Override
		public int compare(Entry<V, Double> o1, Entry<V, Double> o2) {
			return o1.getValue().compareTo(o2.getValue());
		}
	}

	public static class CompareInteger implements Comparator<Entry<PropertyNode, Integer>> {
		@Override
		public int compare(Entry<PropertyNode, Integer> o1, Entry<PropertyNode, Integer> o2) {
			return o1.getValue().compareTo(o2.getValue());
		}
	}

}
