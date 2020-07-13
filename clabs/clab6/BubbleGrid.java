public class BubbleGrid {
    private int[][] bubbles, darts;
    private UnionFind bubbleUnion;
    public BubbleGrid(int[][] bubbles, int[][] darts) {
        this.bubbles = bubbles;
        this.darts = darts;
        bubbleUnion = new UnionFind(bubbles.length * bubbles[0].length);
    }

    private void connect() {
        int row = bubbles.length;
        int column = bubbles[0].length;
        for (int i = 1; i < row; i += 1) {
            for (int j = 0; j < column; j += 1) {
                if (bubbles[i][j] == 1) {
                    if (bubbles[i-1][j] == 1) {
                        bubbleUnion.union(column*i+j, column*(i-1)+j);
                    } else if (j > 0) {
                        if (bubbles[i][j-1] == 1) {
                            bubbleUnion.union(column*i+j, column*i+j-1);
                        }
                    }
                }
            }
        }
    }

    private void disconnect() {
        bubbleUnion = new UnionFind(bubbles.length * bubbles[0].length);
    }


    public int[] popBubbles() {
        int row = bubbles.length;
        int column = bubbles[0].length;
        int[] drops = new int[darts.length];
        for (int k = 0; k < darts.length; k++) {
            bubbles[darts[k][0]][darts[k][1]] = 0; //pop the bubble
            connect();  //check the connectivity
            int drop = 0;
            for (int i = 1; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    if (bubbles[i][j] == 1) {
                        if (bubbleUnion.find(column*i+j) >= column) {
                            drop += 1;
                        }
                    }
                }
            }
            disconnect();
            drops[k] = drop;
        }
        return drops;
    }

    public static void main(String[] args) {
        int[][] bubbles = new int[][]{{1,1,0},{1,0,0},{1,1,0},{1,1,1}};
        int[][] darts = new int[][]{{2,2},{2,0}};
        BubbleGrid bg = new BubbleGrid(bubbles, darts);
        for (int i : bg.popBubbles()) {
            System.out.println(i);
        }
    }
}
