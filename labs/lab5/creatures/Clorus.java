package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.function.DoubleToIntFunction;

import static huglife.HugLifeUtils.random;
import static huglife.HugLifeUtils.randomEntry;

/**
 * An implementation of a fierce blue-colored predator
 * that enjoy nothing more than snacking on hapless Plips.
 */

public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * Should return a color with red = 34, green = 0, blue = 231
     */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /**
     * Take C's energy.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Clorus should lose 0.03 units of energy when moving.
     */
    public void move() {
        energy -= 0.03;
        if (energy < 0) {energy = 0;}
    }

    /**
     * Clorus lose 0.01 units of energy when staying.
     */
    public void stay() {
        energy -= 0.01;
    }

    /**
     * Clorus and their offspring each get 50% of the energy,
     * with none lost to the process.
     */
    public Clorus replicate() {
        energy = energy * 0.5;
        double babyEnergy = energy * 0.5;
        return new Clorus(babyEnergy);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> dirofPlips = new ArrayDeque<>();
        for (Direction d: neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.addFirst(d);
            }
            else if (neighbors.get(d).name().equals("plip")) {
                dirofPlips.addFirst(d);
            }
        }

        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (dirofPlips.size() != 0) {
            return new Action(Action.ActionType.ATTACK, randomEntry(dirofPlips));
        } else if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }
}
