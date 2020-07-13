package hw2;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] results;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be positive");
        }
        results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            results[i] = pf.experiment(p);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(results.length);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(results.length);
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(100, 1000, new PercolationFactory());
        System.out.println(ps.mean());
    }
}
