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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class FuzzyUtil {

    public static interface NormalizedHandler<V> {
        public void normalized(V element, double value);
    }

    public static <V> void minMaxNormalize(Map<V, Double> map, NormalizedHandler<V> handler) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (Double d : map.values()) {
            if (d > max)
                max = d;
            if (d < min)
                min = d;
        }
        double max_min = max - min;
        if (Double.compare(max_min, 0.0) == 0)
            max_min = 1.0;

        for (Entry<V, Double> e : map.entrySet()) {
            handler.normalized(e.getKey(), (e.getValue() - min) / (max_min));
        }
    }

    public static <V> Map<V, Double> minMaxNormalize(Map<V, Double> map) {
        final Map<V, Double> res = new HashMap<V, Double>();
        minMaxNormalize(map, new NormalizedHandler<V>() {
            @Override
            public void normalized(V element, double value) {
                res.put(element, value);
            }
        });
        return res;
    }
}

