package gui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

/**
 * Created by monika03 on 24.05.15.
 */
public class PlotGraph {
    PlotGraph() {
        initializeData();
    }

    JFreeChart lineGraph;

    void initializeData() {
        XYSeries dataSet = new XYSeries("experimental data");

        dataSet.add(1, 1.6);
        dataSet.add(2, 3.2);
        dataSet.add(3, 4.8);
        dataSet.add(4, 6.4);
        dataSet.add(5, 8);
        XYSeriesCollection xySeriesCollection = new XYSeriesCollection(dataSet);

        XYDataset xyDataset = xySeriesCollection;
        lineGraph = ChartFactory.createXYLineChart
                ("Elementary charge",
                        "Electric charge",
                        "Number N",
                        xyDataset,
                        PlotOrientation.HORIZONTAL,
                        true,                //show legend
                        true,                // Show tooltips
                        false               //url show
                );


    }


}