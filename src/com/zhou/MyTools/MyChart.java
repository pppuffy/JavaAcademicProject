/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zhou.MyTools;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class MyChart extends JFrame {

    public MyChart() throws IOException {
        super();
        setSize(1920, 800);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
       
        
    }

 
    //create line chart
    public DefaultCategoryDataset createDataset(Double array1[], String array2[]) {

        DefaultCategoryDataset linedataset = new DefaultCategoryDataset();

        String series = "Degree Analysis";

        for (int i = 0; i < array1.length; i++) {

            Double x = array1[i];
            String y = array2[i];
            linedataset.addValue(x, series, y);
        }

        return linedataset;
    }   
    public void createChart(Double array1[], String array2[]) {

        try {
            JFreeChart chart = ChartFactory.createLineChart("Closeness Analysis",
                    "Employee Name",
                    "Degree",
                    this.createDataset(array1, array2),
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    true
            );

            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(Color.white);
            //plot.setDomainGridlinesVisible(true); 
            plot.setDomainGridlinePaint(Color.BLACK);
            plot.setRangeGridlinePaint(Color.GRAY);
            plot.setNoDataMessage("No Data");

            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            //rangeAxis.setAutoRangeIncludesZero(true); 
            rangeAxis.setUpperMargin(0.20);
            rangeAxis.setLabelAngle(Math.PI / 2.0);
            rangeAxis.setAutoRange(false);

            LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot
                    .getRenderer();
            renderer.setBaseItemLabelsVisible(true);
            renderer.setSeriesPaint(0, Color.red);
            renderer.setBaseShapesFilled(true);
            renderer.setBaseItemLabelsVisible(true);
            renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                    ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
            renderer
                    .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());

            renderer.setBaseItemLabelFont(new Font("Dialog", 0, 8));

            plot.setRenderer(renderer);

            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(1920, 650));
            JPanel panel = new JPanel();
            panel.add(chartPanel);
            add(panel);
            setVisible(true);
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    //create column chart
    public CategoryDataset createDataset2(Integer array1[], String array2[]) 
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < array1.length; i++) {

            int x = array1[i];
            String y = array2[i];
            dataset.setValue(x, y, "   ");
        }

        return dataset;
    }
    public void createChart2(Integer array1[], String array2[]) throws FileNotFoundException, IOException {
        CategoryDataset dataset = createDataset2(array1,array2);
        JFreeChart chart = ChartFactory.createBarChart("Degree Analysis", "Employee",
                "Degree", dataset, PlotOrientation.VERTICAL, true, true, false); 
        chart.setTitle(new TextTitle("Employee Degree", new Font("宋体", Font.BOLD + Font.ITALIC, 15)));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 10));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1600, 650));
        JPanel panel = new JPanel();
        panel.add(chartPanel);
        add(panel);
        setVisible(true);
        
 
    }
    
    
    public CategoryDataset createDataset3(Double array1[], String array2[]) 
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < array1.length; i++) {

            double x = array1[i];
            String y = array2[i];
            dataset.setValue(x, y, "   ");
        }

        return dataset;
    }
    public void createChart3(Double array1[], String array2[]) throws FileNotFoundException, IOException {
        CategoryDataset dataset = createDataset3(array1,array2);
        JFreeChart chart = ChartFactory.createBarChart("Farness Analysis", "Employee",
                "Farness", dataset, PlotOrientation.VERTICAL, true, true, false); 
        chart.setTitle(new TextTitle("Farness Analysis", new Font("宋体", Font.BOLD + Font.ITALIC, 15)));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 10));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 650));
        JPanel panel = new JPanel();
        panel.add(chartPanel);
        add(panel);
        setVisible(true);
        
 
    }

    public void createChart4(Double array1[], String array2[]) throws FileNotFoundException, IOException {
        CategoryDataset dataset = createDataset3(array1,array2);
        JFreeChart chart = ChartFactory.createBarChart("Betweeness Analysis", "Employee",
                "Betweeness", dataset, PlotOrientation.VERTICAL, true, true, false); 
        chart.setTitle(new TextTitle("Betweeness Analysis", new Font("宋体", Font.BOLD + Font.ITALIC, 15)));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis categoryAxis = plot.getDomainAxis();
        categoryAxis.setLabelFont(new Font("微软雅黑", Font.BOLD, 10));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1800, 650));
        JPanel panel = new JPanel();
        panel.add(chartPanel);
        add(panel);
        setVisible(true);
        
 
    }
    
   
}
