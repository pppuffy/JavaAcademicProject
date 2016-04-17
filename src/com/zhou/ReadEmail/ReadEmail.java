/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhou.ReadEmail;

import com.mxgraph.layout.*;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.orthogonal.mxOrthogonalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraphView;
import com.zhou.MyTools.BrandesBetweennessCentrality;
import com.zhou.MyTools.CentralityResult;
import com.zhou.MyTools.MyChart;
import com.zhou.MyGraph.MyGraph;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;

public class ReadEmail {

    /**
     * @param args the command line arguments
     */
    private final String mailDirOringinal = "C:\\Users\\Cheng\\Desktop\\mailData";         //Email Data Folder String
    private final File dataDir = new File(mailDirOringinal);

    private MyGraph myGraph;
    private CopyOnWriteArrayList<String> employeeList;

    public ReadEmail() {
        myGraph = new MyGraph();
        employeeList = new CopyOnWriteArrayList<>();

    }

    public MyGraph getMyGraph() {
        return myGraph;
    }

    public CopyOnWriteArrayList<String> getEmployeeList() {
        return employeeList;
    }

    public MyGraph CreateGraph() throws IOException {

        String mailDirOringinal2 = "C:\\Users\\Cheng\\Desktop\\testData3";         //Email Data Folder String
        File dataDir2 = new File(mailDirOringinal2);

        String dataDirFolders[] = dataDir2.list(); //Get All folders
        for (String dataDirFolder : dataDirFolders) {     //Get Each Employee Folder
            // System.out.println("Person Name: " + dataDirFolder);
            File dataDirFolders2 = new File(mailDirOringinal2 + "\\" + dataDirFolder);
            String dataDirFolders2Array[] = dataDirFolders2.list();  //Get all folders in Each Employee Folder
            for (String dataDirFolder2String : dataDirFolders2Array) {
                File dataDirFolder3 = new File(mailDirOringinal2 + "\\" + dataDirFolder + "\\" + dataDirFolder2String); // Get one folder of One employee
                File dataDirFolder3Files[] = dataDirFolder3.listFiles();                                              //Get files in one folder of One employee
                if (dataDirFolder3Files != null) {
                    for (Object o : dataDirFolder3Files) {                                                             //Get Each File
                        if (o != null) {
                            File dataDirFolder2File = (File) o;
                            //System.out.println(dataDirFolder2File.toString());
                            if (dataDirFolder2File.isFile()) {
                                String v1 = getFrom(dataDirFolder2File);                      //Extract from
                                String v2String = getTo(dataDirFolder2File);                  //Extract to
                                if (v1 != null && v2String != null) {
                                    v2String = v2String.trim();
                                    String v2StringArray[] = v2String.split(",");
                                    for (String s : v2StringArray) {
                                        if (isEmail(s) && isEmail(v1) && !v1.equals(s)) {

                                            // System.out.println("From: "+v1+" To: "+s);
                                            String foundFrom = findTheEmployeeByEmail(v1);
                                            String foundTo = findTheEmployeeByEmail(s);

//                                                
                                            if (foundFrom == null) {
                                                foundFrom = v1;
                                            }
                                            if (foundTo == null) {
                                                foundTo = s;
                                            }

                                            //System.out.println("From: "+foundFrom+" To: "+foundTo);
                                            if (!foundFrom.equals(foundTo)) {
                                                myGraph.myAddEdge(foundFrom, foundTo);
                                            }

                                            //myGraph.myAddEdge(v1, s);                   //create Edge
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return myGraph;

    }

    public MyGraph Create148Graph() throws IOException {

        String mailDirOringinal2 = "C:\\Users\\Cheng\\Desktop\\testData3";         //Email Data Folder String
        File dataDir2 = new File(mailDirOringinal2);

        String dataDirFolders[] = dataDir2.list(); //Get All folders
        for (String dataDirFolder : dataDirFolders) {     //Get Each Employee Folder
            // System.out.println("Person Name: " + dataDirFolder);
            File dataDirFolders2 = new File(mailDirOringinal2 + "\\" + dataDirFolder);
            String dataDirFolders2Array[] = dataDirFolders2.list();  //Get all folders in Each Employee Folder
            for (String dataDirFolder2String : dataDirFolders2Array) {
                File dataDirFolder3 = new File(mailDirOringinal2 + "\\" + dataDirFolder + "\\" + dataDirFolder2String); // Get one folder of One employee
                File dataDirFolder3Files[] = dataDirFolder3.listFiles();                                              //Get files in one folder of One employee
                if (dataDirFolder3Files != null) {
                    for (Object o : dataDirFolder3Files) {                                                             //Get Each File
                        if (o != null) {
                            File dataDirFolder2File = (File) o;
                            //System.out.println(dataDirFolder2File.toString());
                            if (dataDirFolder2File.isFile()) {
                                String v1 = getFrom(dataDirFolder2File);                      //Extract from
                                String v2String = getTo(dataDirFolder2File);                  //Extract to
                                if (v1 != null && v2String != null) {
                                    v2String = v2String.trim();
                                    String v2StringArray[] = v2String.split(",");
                                    for (String s : v2StringArray) {
                                        if (isEmail(s) && isEmail(v1) && !v1.equals(s)) {

                                            // System.out.println("From: "+v1+" To: "+s);
                                            String foundFrom = findTheEmployeeByEmail(v1);
                                            String foundTo = findTheEmployeeByEmail(s);

//                                                
                                            if (foundFrom == null) {
                                                foundFrom = v1;
                                            }
                                            if (foundTo == null) {
                                                foundTo = s;
                                            }

                                            //System.out.println("From: "+foundFrom+" To: "+foundTo);
                                            if (!foundFrom.equals(foundTo) && !foundFrom.contains("@") && !foundTo.contains("@")) {
                                                myGraph.myAddEdge(foundFrom, foundTo);
                                            }

                                            //myGraph.myAddEdge(v1, s);                   //create Edge
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return myGraph;

    }

    public void findEmployeeNames() throws FileNotFoundException, IOException {

        employeeList = new CopyOnWriteArrayList();

        //*********************************************************************************************************************LOOP MAIL DATA FOLDERS
        String dataDirFolders[] = dataDir.list(); //Get All folders
        for (String dataDirFolder : dataDirFolders) {     //Get Each Employee Folder
            // System.out.println("Person Name: " + dataDirFolder);
            if (dataDirFolder.equals("harris-s")) {
                employeeList.add("Harris Steven");
                continue;
            }
            if (dataDirFolder.equals("hain-m")) {
                employeeList.add("Mary Hain");
                continue;
            }
            if (dataDirFolder.equals("horton-s")) {
                employeeList.add("Stanley Horton");
                continue;
            }
            if (dataDirFolder.equals("lay-k")) {
                employeeList.add("Lay Kenneth");
                continue;
            }
            if (dataDirFolder.equals("martin-t")) {
                employeeList.add("Thomas A Martin");
                continue;
            }
            if (dataDirFolder.equals("skilling-j")) {
                employeeList.add("Jeff Skilling");
                continue;
            }
            if (dataDirFolder.equals("white-s")) {
                employeeList.add("White Stacey W");
                continue;
            }

            File dataDirFolders2 = new File(mailDirOringinal + "\\" + dataDirFolder);
            String dataDirFolders2Array[] = dataDirFolders2.list();  //Get all folders in Each Employee Folder
            for (String dataDirFolder2String : dataDirFolders2Array) {
                File dataDirFolder3 = new File(mailDirOringinal + "\\" + dataDirFolder + "\\" + dataDirFolder2String); // Get one folder of One employee               

                if (dataDirFolder3.getName().equals("sent_items")) {
                    //System.out.println(dataDirFolder);
                    File dataDirFolder3SentItems = new File(mailDirOringinal + "\\" + dataDirFolder + "\\" + dataDirFolder2String + "\\" + "1");
                    //System.out.println(dataDirFolder3SentItems.getName());
                    //*********************************************Read File
                    BufferedReader br = new BufferedReader(new FileReader(dataDirFolder3SentItems));
                    try {
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();

                        while (line != null) {

                            if (line.contains("X-From")) {
                                //System.out.println(line);
                                line = transferToName(line);

                                employeeList.add(line);
                                break;
                            }
                            line = br.readLine();

                        }
                    } finally {
                        br.close();
                    }
                    //**********************************************Read File

                    break;
                } else if (dataDirFolder3.getName().equals("_sent_mail")) {
                    //System.out.println(dataDirFolder);
                    File dataDirFolder3SentItems = new File(mailDirOringinal + "\\" + dataDirFolder + "\\" + dataDirFolder2String + "\\" + "1");
                    //System.out.println(dataDirFolder3SentItems.getName());
                    //*********************************************Read File
                    BufferedReader br = new BufferedReader(new FileReader(dataDirFolder3SentItems));
                    try {
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();

                        while (line != null) {

                            if (line.contains("X-From")) {
                                //System.out.println(line);
                                line = transferToName(line);
                                employeeList.add(line);
                                break;
                            }
                            line = br.readLine();

                        }
                    } finally {
                        br.close();
                    }
                    //**********************************************Read File

                    break;
                } else if (dataDirFolder3.getName().equals("sent")) {
                    //System.out.println(dataDirFolder);
                    File dataDirFolder3SentItems = null;
                    File dataDirFolder3SentItems1 = new File(mailDirOringinal + "\\" + dataDirFolder + "\\" + dataDirFolder2String + "\\" + "1");
                    File dataDirFolder3SentItems2 = new File(mailDirOringinal + "\\" + dataDirFolder + "\\" + dataDirFolder2String + "\\" + "2");
                    File dataDirFolder3SentItems3 = new File(mailDirOringinal + "\\" + dataDirFolder + "\\" + dataDirFolder2String + "\\" + "3");

                    if (dataDirFolder3SentItems2.exists()) {
                        dataDirFolder3SentItems = dataDirFolder3SentItems2;
                    } else if (dataDirFolder3SentItems3.exists()) {
                        dataDirFolder3SentItems = dataDirFolder3SentItems3;
                    } else if (dataDirFolder3SentItems1.exists()) {
                        dataDirFolder3SentItems = dataDirFolder3SentItems1;
                    }

                    //System.out.println(dataDirFolder3SentItems.getName());
                    //*********************************************Read File
                    BufferedReader br = new BufferedReader(new FileReader(dataDirFolder3SentItems));
                    try {
                        StringBuilder sb = new StringBuilder();
                        String line = br.readLine();

                        while (line != null) {

                            if (line.contains("X-From")) {
                                //System.out.println(line);
                                line = transferToName(line);
                                employeeList.add(line);
                                break;
                            }
                            line = br.readLine();

                        }
                    } finally {
                        br.close();
                    }
                    //**********************************************Read File

                    break;
                }
            }
        }

        //remove one dulplicate employee
        // System.out.println(employeeList.size());
        //return employeeList;
        //*********************************************************************************************************************LOOP MAIL DATA FOLDERS
    }

    public void create148Vertex() {
        //System.out.println("Start creating 150 vertex: " + employeeList.size());
        for (String s : employeeList) {
            myGraph.getGraph().addVertex(s);
            //System.out.println(s);
        }
    }

    public String findTheEmployeeByEmail(String email) throws IOException {

        if (email.equalsIgnoreCase("susan.scott@enron.com")) {
            return "Susan Scott";
        }
        if (email.equalsIgnoreCase("paul.y'barbo@enron.com")) {
            return "Paul Y'Barbo";
        }

        int atPosition = email.indexOf("@");
        String email2 = email.substring(0, atPosition);
        String email3[] = email2.split("\\.+");

        if (email3.length == 1) {
            email3 = new String[2];
            email3[0] = email2.substring(0, 1);
            email3[1] = email2.substring(1);
        }

        //System.out.println(string+"--Head:"+stringHead);
        //     
        boolean found = false;
        for (int j = 0; j < employeeList.size(); j++) {            //loop employeeList

            if (found) {

                //System.out.println("Email Match to: " + employeeList.get(j - 1));        
                return employeeList.get(j - 1);
                //break;
            }
            int flag = 0;
            String employee = employeeList.get(j);
            String employeeName = employee;
            String employeeNameArr[] = employeeName.split(" ");

            for (int i = 0; i < email3.length; i++) {               //loop email3

                String email3Element = email3[i];

                if (!email3Element.equals("")) {
                    String email3ElementHead = email3Element.substring(0, 1);
                    for (int k = 0; k < employeeNameArr.length; k++) {         //loop each employee name
                        String employeeNameArrElement = employeeNameArr[k];
                        String employeeNameArrElementHead = employeeNameArrElement.substring(0, 1);
                        //System.out.println(employeeNameArrElement+"--Head:"+employeeNameArrElementHead);
                        if (i != email3.length - 1) {
                            if (email3Element.equalsIgnoreCase(employeeNameArrElement) || email3ElementHead.equalsIgnoreCase(employeeNameArrElementHead)) {
                                flag++;
                                //System.out.println(email3Element + "  " + employeeNameArrElement);
                                //System.out.println(flag);
                                break;
                                //System.out.println(flag);
                            }
                        }
                        if (i == email3.length - 1) {
                            if (email3Element.equalsIgnoreCase(employeeNameArrElement)) {
                                //System.out.println(email3Element+"  "+employeeNameArrElement);
                                flag++;
                                //System.out.println(flag);
                                if (flag == email3.length) {
                                    found = true;
                                    //System.out.println(email3Element + "  " + employeeNameArrElement);
                                    //System.out.println(flag);
                                    if (j == employeeList.size() - 1) {
                                        //System.out.println("Email Match to: " + employeeList.get(j - 1));           
                                        return employeeList.get(j);
                                    }
                                }
                            }
                        }
                    }//
                }

            }//
        }
        return null;
    }

    public void visualizationGraph(MyGraph g) throws IOException {
        JFrame frame = new JFrame("Grap Visualization");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1080, 1000);
        JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter(g.getGraph());

        mxCircleLayout layout = new mxCircleLayout(graphAdapter);

        layout.execute(graphAdapter.getDefaultParent());

        mxGraphComponent component = new mxGraphComponent(graphAdapter);

        frame.add(component);
        frame.setVisible(true);

    }

    public void degreeAnalysis(MyGraph graph) throws IOException {
        Set<String> setAllVertex = graph.getGraph().vertexSet();
        Iterator iterAllVertex = setAllVertex.iterator();

        //*********Degree Analysis
        Hashtable hashmap = new Hashtable();

        while (iterAllVertex.hasNext()) {
            String s = (String) iterAllVertex.next();
            if (!s.contains("@")) {
                hashmap.put(s, graph.getGraph().degreeOf(s));

            }
        }
        ArrayList<String> list = sortValue(hashmap);

        //*************Visualization Degree
//        Set<String> set = hashmap.keySet();
        ArrayList<String> list1 = new ArrayList();
        ArrayList<Integer> list2 = new ArrayList();

        Iterator iter = list.iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();

            list1.add((String) entry.getKey());
            list2.add((Integer) entry.getValue());
        }
//        for (String s : set) {
//
//            list1.add(s);
//            list2.add(graph.getGraph().degreeOf(s));
//            i++;
//        }

        String[] x = list1.toArray(new String[list1.size()]);
        Integer[] y = list2.toArray(new Integer[list2.size()]);
        MyChart myChart = new MyChart();
        myChart.createChart2(y, x);
    }

    public void closenessAnalysis(MyGraph graph) {
//        Set<String> setAllVertex = graph.getGraph().vertexSet();
//        Iterator iterAllVertex2 = setAllVertex.iterator();
//        Hashtable<String, Double> hashCloseness = new Hashtable();
//        while (iterAllVertex2.hasNext()) {
//            String employee = (String) iterAllVertex2.next();
//            if (!employee.contains("@")) {
//                double closeness = 0;              
//                // System.out.println(ele);
//                Set setLackOneElement = new HashSet(setAllVertex);
//                setLackOneElement.remove(employee);
//                for (Object o : setLackOneElement) {
//                    String everySingleVertex = (String) o;
//                    if (!everySingleVertex.contains("@")) {
//                        List list = DijkstraShortestPath.findPathBetween(graph.getGraph(), employee, everySingleVertex);
//                        if (list != null) {
//                            closeness += list.size();
//                        }      
//                    }
//                }
//                hashCloseness.put(employee, closeness);
//            }
//        }
        try {
//            FileOutputStream fos = new FileOutputStream("C:/users/cheng/desktop/Closeness.ser");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(hashCloseness);
//            oos.close();

            FileInputStream fis = new FileInputStream("tmpFile/Closeness.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Hashtable result = (Hashtable) ois.readObject();
            ois.close();
            //ArrayList<String> list = sortValue2(result);
            //*******Visualization Closeness
            ArrayList<String> list = sortValue2(result);

            ArrayList<String> list1 = new ArrayList();
            ArrayList<Double> list2 = new ArrayList();

            //Iterator iter = list.iterator();
            Iterator iter2 = list.iterator();
            int i = 0;
            while (iter2.hasNext()) {
                if (i == 10) {
                    break;
                }
                Map.Entry entry = (Map.Entry) iter2.next();
                if (graph.getGraph().degreeOf((String) entry.getKey()) > 500) {
                    list1.add((String) entry.getKey());
                    list2.add((Double) entry.getValue());
                    i++;
                    //System.out.println("Name: "+(String) entry.getKey()+" Closeness: "+(Double) entry.getValue());
                }

            }
            String[] x = list1.toArray(new String[list1.size()]);
            Double[] y = list2.toArray(new Double[list2.size()]);
            MyChart myChart = new MyChart();
            myChart.createChart(y, x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void farnessAnalysis(MyGraph graph) {
//        Set<String> setAllVertex = graph.getGraph().vertexSet();
//        Iterator iterAllVertex2 = setAllVertex.iterator();
//        Hashtable<String, Double> hashCloseness = new Hashtable();
//        while (iterAllVertex2.hasNext()) {
//            String employee = (String) iterAllVertex2.next();
//            if (!employee.contains("@")) {
//                double closeness = 0;
//                int num = 0;
//                // System.out.println(ele);
//                Set setLackOneElement = new HashSet(setAllVertex);
//                setLackOneElement.remove(employee);
//                for (Object o : setLackOneElement) {
//
//                    String everySingleVertex = (String) o;
//
//                    if (!everySingleVertex.contains("@")) {
//                        List list = DijkstraShortestPath.findPathBetween(graph.getGraph(), employee, everySingleVertex);
//                        if (list != null) {
//                            closeness += list.size();
//                            num++;
//                            //System.out.println(employee + " To " + everySingleVertex + " :" + list.size());
//                        }
//                    }
//
//                }
//                String s = new java.text.DecimalFormat("#.00").format(closeness / num);
//                closeness = Double.parseDouble(s);
//                hashCloseness.put(employee, closeness);
//                //System.out.println("Employee: " + employee + " , Closeness: " + closeness + " Num: " + num);
//            }
//        }
        try {
//            FileOutputStream fos = new FileOutputStream("C:/users/cheng/desktop/Farness.ser");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(hashCloseness);
//            oos.close();

            FileInputStream fis = new FileInputStream("tmpFile/Farness.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Hashtable result = (Hashtable) ois.readObject();
            ois.close();
            //ArrayList<String> list = sortValue2(result);
            //*******Visualization Closeness
            ArrayList<String> list = sortValue2(result);
            Iterator iter = list.iterator();
//            while (iter.hasNext()) {
//                System.out.println(iter.next());
//            }
            ArrayList<String> list1 = new ArrayList();
            ArrayList<Double> list2 = new ArrayList();
            //Iterator iter = list.iterator();
            Iterator iter2 = list.iterator();
            int i = 0;
            while (iter2.hasNext()) {
                if (i == 10) {
                    break;
                }
                Map.Entry entry = (Map.Entry) iter2.next();
                if (graph.getGraph().degreeOf((String) entry.getKey()) > 500) {
                    list1.add((String) entry.getKey());
                    list2.add((Double) entry.getValue());
                    i++;
                   // System.out.println("Name: " + (String) entry.getKey() + " Farness: " + (Double) entry.getValue() + " Degree:" + graph.getGraph().degreeOf((String) entry.getKey()));
                }
            }
            String[] x = list1.toArray(new String[list1.size()]);
            Double[] y = list2.toArray(new Double[list2.size()]);
            MyChart myChart = new MyChart();
            myChart.createChart3(y, x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void betweenessAnalysis(MyGraph graph) throws FileNotFoundException, IOException, ClassNotFoundException {
//        BrandesBetweennessCentrality bb = new BrandesBetweennessCentrality(graph.getGraph());
//        CentralityResult<String> cr = bb.calculate();
//        List<Entry<String,Double>> list = cr.getSorted();
//        Hashtable hm=new Hashtable();
//        for(Entry<String,Double> e:list){
//            hm.put(e.getKey(), e.getValue());
//        }

        try {
//            FileOutputStream fos = new FileOutputStream("C:/users/cheng/desktop/Betweeness.ser");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(hm);
//            oos.close();
            
            FileInputStream fis = new FileInputStream("tmpFile/Betweeness.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Hashtable result = (Hashtable) ois.readObject();
            ois.close();
            
            ArrayList<String> listA = new ArrayList();
            ArrayList<Double> listB = new ArrayList();
            ArrayList<String> list2 = sortValue3(result);

            Iterator it = list2.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String entryKey = (String) entry.getKey();
                Double entryValue = (Double) entry.getValue();
                if (!entryKey.contains("@")) {
                    
                    String s = new java.text.DecimalFormat("#.00").format(entryValue);
                    entryValue = Double.parseDouble(s);
                    
                    //System.out.println("Employee: " + entryKey + " Betweeness: " + entryValue);
                    listA.add((String) entry.getKey());
                    listB.add((Double) entry.getValue());
                    
                    
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
            String[] x = listA.toArray(new String[listA.size()]);
            Double[] y = listB.toArray(new Double[listB.size()]);
            MyChart myChart = new MyChart();
            myChart.createChart4(y, x);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList sortValue(Hashtable<?, Integer> hashTable) {

        ArrayList<Map.Entry<?, Integer>> list = new ArrayList(hashTable.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<?, Integer>>() {

            @Override
            public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

//        Iterator iter = list.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
        return list;

    }

    public ArrayList sortValue2(Hashtable<?, Double> hashTable) {

        ArrayList<Map.Entry<?, Double>> list = new ArrayList(hashTable.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<?, Double>>() {

            @Override
            public int compare(Map.Entry<?, Double> o2, Map.Entry<?, Double> o1) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

//        Iterator iter = list.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
        return list;

    }
    
    public ArrayList sortValue3(Hashtable<?, Double> hashTable) {

        ArrayList<Map.Entry<?, Double>> list = new ArrayList(hashTable.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<?, Double>>() {

            @Override
            public int compare(Map.Entry<?, Double> o1, Map.Entry<?, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

//        Iterator iter = list.iterator();
//        while (iter.hasNext()) {
//            System.out.println(iter.next());
//        }
        return list;

    }
    

    public String transferToName(String line) {

        if (line.contains("<")) {
            int aaaLength = line.length();
            int xPosition = line.indexOf("m");
            line = line.substring(xPosition + 2);
            int rightPosition = line.indexOf("<");
            line = line.substring(0, rightPosition);
            line = line.replace(",", "");
            line = line.trim();
            // System.out.print(line);
        } else {
            int aaaLength = line.length();
            int xPosition = line.indexOf("m");
            line = line.substring(xPosition + 2);
            line = line.replace(",", "");
            line = line.trim();
            //System.out.print(line);
        }

        return line;

    }

    public String readFile(File fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public Boolean isEmail(String s) {
        Matcher m = Pattern.compile("[a-zA-Z0-9_.'+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(s);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public String getFrom(File fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String from = "";
            while (line != null) {

                if (line.contains("From:") && !line.contains("X-From")) {

                    Matcher m = Pattern.compile("[a-zA-Z0-9_.'+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(line);
                    while (m.find()) {
                        return from = m.group();
                        //System.out.println("From:"+from);

                    }
                    break;
                }
                line = br.readLine();

            }
        } finally {
            br.close();
        }
        return null;
    }

    public String getTo(File fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String to = "";

            while (line != null) {

                if (line.contains("To:") && !line.contains("X-To")) {

                    Matcher m = Pattern.compile("[a-zA-Z0-9_.'+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(line);
                    while (m.find()) {
                        to += m.group() + ",";
                    }

                    String line2 = br.readLine();

                    while (!line2.contains("Subject")) {
                        Matcher m2 = Pattern.compile("[a-zA-Z0-9_.'+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(line2);
                        while (m2.find()) {
                            to += m2.group() + ",";
                        }
                        line2 = br.readLine();
                        if (line2 == null) {
                            break;
                        }
                    }

                    //System.out.println("To:"+to);
                    return to;

                }

                line = br.readLine();
            }

        } finally {
            br.close();
        }
        return null;
    }

}
