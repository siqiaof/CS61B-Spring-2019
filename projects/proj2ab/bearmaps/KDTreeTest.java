package bearmaps;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void randomTest() {
        double x, y;
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            x = StdRandom.uniform(0.0, 100.0);
            y = StdRandom.uniform(0.0, 100.0);
            points.add(new Point(x, y));
        }
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);

        for (int i = 0; i < 100; i++) {
            x = StdRandom.uniform(0.0, 100.0);
            y = StdRandom.uniform(0.0, 100.0);
            assertEquals(nps.nearest(x, y), kdt.nearest(x, y));
        }
    }

    @Test
    public void timeTest() {
        double x, y;
        long start, end;
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            x = StdRandom.uniform(0.0, 100.0);
            y = StdRandom.uniform(0.0, 100.0);
            points.add(new Point(x, y));
        }
        NaivePointSet nps = new NaivePointSet(points);
        KDTree kdt = new KDTree(points);

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            x = StdRandom.uniform(0.0, 100.0);
            y = StdRandom.uniform(0.0, 100.0);
            nps.nearest(x, y);
        }
        end = System.currentTimeMillis();
        double naiveTime = (end - start)/1000.0;

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            x = StdRandom.uniform(0.0, 100.0);
            y = StdRandom.uniform(0.0, 100.0);
            kdt.nearest(x, y);
        }
        end = System.currentTimeMillis();
        double kdTime = (end - start)/1000.0;

        System.out.println("naiveTime: " + naiveTime);
        System.out.println("kdTime: " + kdTime);

    }
}
