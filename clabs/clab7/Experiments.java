import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static int tree_size = 5000;
    public static int repeating_times = 50000;

    public static void experiment1() {
        Random r = new Random();
        List<Double> y1Values = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        BST<Integer> b = new BST<>();
        for (int i = 0; i < tree_size; i++) {
            xValues.add(i+1);
            b.add(r.nextInt());
            y1Values.add(b.getAverageDepth());
            y2Values.add(ExperimentHelper.optimalAverageDepth(i+1));
        }



        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("items").yAxisTitle("average depth").build();
        chart.addSeries("average depth of BST", xValues, y1Values);
        chart.addSeries("average depth of optimal BST", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        BST<Integer> b = new BST<>();
        for (int i = 0; i < tree_size; i++) {
            b.add(r.nextInt());
        }
        xValues.add(0);
        yValues.add(b.getAverageDepth());
        for (int i = 1; i <= repeating_times; i++) {
            xValues.add(i);
            ExperimentHelper.DTSAndInsert(b);
            yValues.add(b.getAverageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("repeating times").yAxisTitle("average depth").build();
        chart.addSeries("E2: average depth of BST", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        Random r = new Random();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        BST<Integer> b = new BST<>();
        for (int i = 0; i < tree_size; i++) {
            b.add(r.nextInt());
        }
        xValues.add(0);
        yValues.add(b.getAverageDepth());
        for (int i = 1; i <= repeating_times; i++) {
            xValues.add(i);
            ExperimentHelper.DTRAndInsert(b);
            yValues.add(b.getAverageDepth());
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("repeating times").yAxisTitle("average depth").build();
        chart.addSeries("E3: average depth of BST", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        //experiment1();
        //experiment2();
        //experiment3();
    }
}
