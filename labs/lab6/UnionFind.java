public class UnionFind {

    // TODO - Add instance variables?
    private int[] UnionTree;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        UnionTree = new int[n];
        for (int i = 0; i < n; i += 1) {
            UnionTree[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= UnionTree.length || vertex < 0) {
            throw new IllegalArgumentException(vertex + " is not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return UnionTree[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (connected(v1, v2)) {
            return;
        }
        int root1 = find(v1);
        int root2 = find(v2);
        if (parent(root1) < parent(root2)) {
            UnionTree[root1] += UnionTree[root2];
            UnionTree[root2] = root1;
        } else {
            UnionTree[root2] += UnionTree[root1];
            UnionTree[root1] = root2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        int root = vertex;
        while (parent(root) >= 0) {
            root = parent(root);
        }
        int temp;
        while (vertex != root && parent(vertex) != root){
            temp = parent(vertex);
            UnionTree[vertex] = root;
            vertex = temp;
        }
        return root;
    }

}
