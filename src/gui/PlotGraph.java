//package gui;
//
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartFrame;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.xy.XYDataset;
//import org.jfree.data.xy.XYSeries;
//import org.jfree.data.xy.XYSeriesCollection;
//
//import javax.swing.*;
//import java.util.ArrayList;
//
///**
// * Created by monika03 on 24.05.15.
// */
//public class PlotGraph {
//    private ArrayList<Double> values;
//    PlotGraph(MillikanFrame frame) {
//        initializeData();
//        values=frame.estimated;
//    }
//
//    JFreeChart lineGraph;
//
//    void initializeData() {
//        XYSeries dataSet = new XYSeries("experimental data");
//
//
//        if(values==null)
//            dataSet.add(0.,0.);
//        else
//            for(int i=1;i<=values.size();i++)
//                dataSet.add(i,values.get(i));
//        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(dataSet);
//
//        XYDataset xyDataset = xySeriesCollection;
//        lineGraph = ChartFactory.createXYLineChart
//                ("Elementary charge",
//                        "Electric charge",
//                        "Number N",
//                        xyDataset,
//                        PlotOrientation.HORIZONTAL,
//                        true,                //show legend
//                        true,                // Show tooltips
//                        false               //url show
//                );
//
//
//    }
//
//
//}