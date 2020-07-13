package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public int[][] sites;  //the status of sites, 0 if close, 1 if open
    private WeightedQuickUnionUF union, union0;  //check the connectivity of sites
    private int NumOfOpen;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("the size must be positive");
        }
        sites = new int[N][N];
        NumOfOpen = 0;
        union = new WeightedQuickUnionUF(N * N + 2); //N*N real sites plus virtual top and bottom sites.
        union0 = new WeightedQuickUnionUF(N * N + 1);  //N*N real sites plus virtual top site.
    }

    private void checkValidity(int value) {
        if (value < 0 || value > sites.length - 1) {
            throw new IndexOutOfBoundsException("the row/col must be between 0 and N-1");
        }
    }

    private int SitesToUnion(int i, int j) {
        return i * sites.length + j;
    }

    public void open(int row, int col) {
        checkValidity(row);
        checkValidity(col);
        sites[row][col] = 1;
        NumOfOpen += 1;
        int N = sites.length;
        if (row - 1 >= 0) {
            if (isOpen(row - 1, col)) {
                union.union(SitesToUnion(row-1,col), SitesToUnion(row, col));
                union0.union(SitesToUnion(row-1,col), SitesToUnion(row, col));
            }
        } else {
            union.union(N*N, SitesToUnion(row, col));
            union0.union(N*N, SitesToUnion(row, col));
        }
        if (col - 1 >= 0) {
            if (isOpen(row, col - 1)) {
                union.union(SitesToUnion(row, col-1), SitesToUnion(row, col));
                union0.union(SitesToUnion(row, col-1), SitesToUnion(row, col));
            }
        }
        if (row + 1 <= N - 1) {
            if (isOpen(row + 1, col)) {
                union.union(SitesToUnion(row, col), SitesToUnion(row+1, col));
                union0.union(SitesToUnion(row, col), SitesToUnion(row+1, col));
            }
        } else {
            union.union(N*N+1, SitesToUnion(row, col));
        }
        if (col + 1 <= N - 1) {
            if (isOpen(row, col + 1)) {
                union.union(SitesToUnion(row, col), SitesToUnion(row, col+1));
                union0.union(SitesToUnion(row, col), SitesToUnion(row, col+1));
            }
        }
    }
    public boolean isOpen(int row, int col) {
        checkValidity(row);
        checkValidity(col);
        return sites[row][col] == 1;
    }
    public boolean isFull(int row, int col) {
        checkValidity(row);
        checkValidity(col);
        int N = sites.length;
        if (union0.connected(N*N, SitesToUnion(row, col))) {
            return true;
        } else {
            return false;
        }
    }

    public int numberOfOpenSites() {
        return NumOfOpen;
    }

    public boolean percolates() {
        int N = sites.length;
        if (union.connected(N*N, N*N+1)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(2, 2);
        System.out.println(p.percolates());
        p.open(1, 2);
        p.open(1, 1);
        p.open(0, 1);
        p.open(2, 0);
        System.out.println(p.percolates());
        System.out.println(p.isFull(2, 0));
    }
}
