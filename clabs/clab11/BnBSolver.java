import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private List<Bear> bears;
    private List<Bed> beds;
    private BnB result;

    private class BnB {
        private List<Bear> bears;
        private List<Bed> beds;
        public BnB(List<Bear> bears, List<Bed> beds) {
            this.bears = bears;
            this.beds = beds;
        }
    }

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        this.bears = bears;
        this.beds = beds;
        result = solver(bears, beds);
    }

    private BnB solver(List<Bear> bears, List<Bed> beds) {
        if (bears.size() <= 1) {
            return new BnB(bears, beds);
        } else {
            LinkedList<Bear> bearLess = new LinkedList<>();
            LinkedList<Bear> bearEqual = new LinkedList<>();
            LinkedList<Bear> bearGreater = new LinkedList<>();
            LinkedList<Bed> bedLess = new LinkedList<>();
            LinkedList<Bed> bedEqual = new LinkedList<>();
            LinkedList<Bed> bedGreater = new LinkedList<>();

            partition(bears, beds, bearLess, bearEqual, bearGreater
                    , bedLess, bedEqual, bedGreater);
            BnB less = solver(bearLess, bedLess);
            BnB greater = solver(bearGreater, bedGreater);
            return new BnB(bearConnector(less.bears, bearEqual, greater.bears),
                    bedConnector(less.beds, bedEqual, greater.beds));

        }
    }

    private List<Bear> bearConnector(List<Bear> a, List<Bear> b, List<Bear> c) {
        LinkedList<Bear> connected = new LinkedList<>();
        for (Bear bear : a) {
            connected.addLast(bear);
        }
        for (Bear bear : b) {
            connected.addLast(bear);
        }
        for (Bear bear : c) {
            connected.addLast(bear);
        }
        return connected;
    }

    private List<Bed> bedConnector(List<Bed> a, List<Bed> b, List<Bed> c) {
        LinkedList<Bed> connected = new LinkedList<>();
        for (Bed bed : a) {
            connected.addLast(bed);
        }
        for (Bed bed : b) {
            connected.addLast(bed);
        }
        for (Bed bed : c) {
            connected.addLast(bed);
        }
        return connected;
    }

    private void partition(List<Bear> bears, List<Bed> beds,
                           LinkedList<Bear> bearLess, LinkedList<Bear> bearEqual,
                           LinkedList<Bear> bearGreater, LinkedList<Bed> bedLess,
                           LinkedList<Bed> bedEqual, LinkedList<Bed> bedGreater) {
        Bed bedPivot = beds.get(StdRandom.uniform(beds.size()));
        Bear bearPivot = bears.get(0);

        for (Bear b : bears) {
            if (b.compareTo(bedPivot) < 0) {
                bearLess.addLast(b);
            } else if (b.compareTo(bedPivot) == 0) {
                bearEqual.addLast(b);
                bearPivot = b;
            } else  {
                bearGreater.addLast(b);
            }
        }

        for (Bed b : beds) {
            if (b.compareTo(bearPivot) < 0) {
                bedLess.addLast(b);
            } else if (b.compareTo(bearPivot) == 0) {
                bedEqual.addLast(b);
            } else  {
                bedGreater.addLast(b);
            }
        }

    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return result.bears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return result.beds;
    }
}
