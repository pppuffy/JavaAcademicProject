/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhou.ReadEmail;

import com.zhou.MyGraph.MyGraph;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author Cheng
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ReadEmail read = new ReadEmail();

        Boolean flag = true;
        while (flag) {
            System.out.println("************************************");
            System.out.println("Please Select Operation: \r\n 1. Generate Graph \r\n 2. Degree Analysis \r\n 3. Closeness Analysis \r\n 4. Farness Analysis \r\n 5. Betweeness Analysis \r\n 6. Exit... ");
            System.out.println("************************************");
            Scanner scan = new Scanner(System.in);
            int scanInput = scan.nextInt();
            if (scanInput == 1) {
                System.out.println("Starting Generating Graph......");
                try {           
                    // FileInputStream fis = new FileInputStream("C:/users/cheng/desktop/MyGraph.ser");
                    FileInputStream fis = new FileInputStream("tmpFile/PartialGraph.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    MyGraph result = (MyGraph) ois.readObject();
                    ois.close();
                    
                  // System.out.println(result.getGraph().edgeSet().size());
                    
                    read.visualizationGraph(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (scanInput == 2) {
                System.out.println("Starting Degree Analysis......");
                try {

                    FileInputStream fis = new FileInputStream("tmpFile/MyGraph.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    MyGraph result = (MyGraph) ois.readObject();
                    ois.close();

                    read.degreeAnalysis(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (scanInput == 3) {
                System.out.println("Starting Closeness Analysis......");
                try {
                    FileInputStream fis = new FileInputStream("tmpFile/MyGraph.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    MyGraph result = (MyGraph) ois.readObject();
                    ois.close();
                    read.closenessAnalysis(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (scanInput == 4) {
                System.out.println("Starting Farness Analysis......");
                try {
                    FileInputStream fis = new FileInputStream("tmpFile/MyGraph.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    MyGraph result = (MyGraph) ois.readObject();
                    ois.close();
                    read.farnessAnalysis(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else if (scanInput == 5) {
                System.out.println("Starting Betweeness Analysis......");
                try {
                    FileInputStream fis = new FileInputStream("tmpFile/MyGraph.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    MyGraph result = (MyGraph) ois.readObject();
                    ois.close();
                    read.betweenessAnalysis(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (scanInput == 6) {
                System.out.println("Bye Bye......");
                System.exit(0);
            }else{
                System.out.println("Please check your selection");
            }

        }

        //***********************************************Start Initial       
//        read.findEmployeeNames();  //Create Employee List
//        read.create148Vertex();  //Create first 148 Vertex               
//        MyGraph graph=read.Create148Graph();  //create Graph 
//        try {
//			FileOutputStream fos = new FileOutputStream("C:/users/cheng/desktop/My148Graph.ser");
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			oos.writeObject(graph);
//			oos.close();
//
//            FileInputStream fis = new FileInputStream("C:/users/cheng/desktop/MyGraph.ser");
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            MyGraph result = (MyGraph) ois.readObject();
//            ois.close();
//
//            read.performActionTest(result);
//            //read.visualizationGraph(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
