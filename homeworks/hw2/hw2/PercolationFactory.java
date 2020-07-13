package hw2;

import static edu.princeton.cs.introcs.StdRandom.permutation;

public class PercolationFactory {
    public Percolation make(int N) {
        return new Percolation(N);
    }
    public double experiment(Percolation p) {
        int N = p.sites.length;
        int[] random = permutation(N*N);
        for (int i : random) {
            p.open(i / N, i % N);
            if (p.percolates()) {
                break;
            }
        }
        return p.numberOfOpenSites()/(N*N*1.0);
    }
}
