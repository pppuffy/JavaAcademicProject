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
public interface PropertyContainer {
    public <T> T getProperty(Class<T> clazz, String key);
    public <T> void setProperty(String key, T property);
    public Iterable<String> propertyNames();
}