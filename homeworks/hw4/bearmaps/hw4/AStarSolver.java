package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private LinkedList<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, Vertex> edgeTo;
    private DoubleMapPQ<Vertex> pq;
    private AStarGraph<Vertex> G;
    private Vertex goal;

    public AStarSolver(AStarGraph<Vertex> G, Vertex start, Vertex goal, double timeout) {
        Stopwatch sw = new Stopwatch();
        this.G = G;
        this.goal = goal;
        distTo = new HashMap<>();
        edgeTo = new HashMap<>();
        pq = new DoubleMapPQ<>();
        solution = new LinkedList<>();
        pq.add(start, G.estimatedDistanceToGoal(start, goal));
        distTo.put(start, 0.0);
        while (sw.elapsedTime() < timeout) {
            Vertex p = pq.removeSmallest();
            numStatesExplored += 1;
            for (WeightedEdge<Vertex> e : G.neighbors(p)) {
                relax(e);
            }
            if (pq.size() == 0 || pq.getSmallest() == goal) {
                Vertex curr = goal;
                while (!curr.equals(start)) {
                    solution.addFirst(curr);
                    curr = edgeTo.get(curr);
                }
                solution.addFirst(start);
                solutionWeight = distTo.get(goal);
                outcome = SolverOutcome.SOLVED;
                timeSpent = sw.elapsedTime();
                return;
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
        timeSpent = sw.elapsedTime();
    }

    private void relax(WeightedEdge<Vertex> e) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if (!distTo.containsKey(q) || distTo.get(p) + w < distTo.get(q)) {
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(e.to(), p);
            if (pq.contains(q)) {
                pq.changePriority(q, distTo.get(q) + G.estimatedDistanceToGoal(q, goal));
            } else {
                pq.add(q, distTo.get(q) + G.estimatedDistanceToGoal(q, goal));
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
