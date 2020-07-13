import java.util.Random;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int pathLength = 0;
        int level = 0;
        while (true) {
            if (N >= Math.pow(2, level)) {
                for (int i = 0; i < Math.pow(2, level); i++) {
                    pathLength += level;
                }
                N -= Math.pow(2, level);
                level++;
            } else {
                for (int i = 0; i < N; i++) {
                    pathLength += level;
                }
                break;
            }
        }
        return pathLength;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return optimalIPL(N) / (double) N;
    }

    public static void DTSAndInsert(BST<Integer> b) {
        Random r = new Random();
        b.deleteTakingSuccessor(b.getRandomKey());
        b.add(r.nextInt());
    }

    public static void DTRAndInsert(BST<Integer> b) {
        Random r = new Random();
        b.deleteTakingRandom(b.getRandomKey());
        b.add(r.nextInt());
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 8; i++) {
            System.out.println(optimalIPL(i));
        }
        System.out.println(optimalAverageDepth(Experiments.tree_size));
    }
}
