package bearmaps;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayHeapMinPQTest {
    @Test
    public void methodsTests() {
        ArrayHeapMinPQ<Character> arrayHeap = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Character> naive = new NaiveMinPQ<>();
        arrayHeap.add('a', 0.4);
        naive.add('a', 0.4);
        assertTrue(arrayHeap.contains('a'));

        arrayHeap.add('f', 0.5);
        naive.add('f', 0.5);
        arrayHeap.add('h', 0.6);
        naive.add('h', 0.6);
        arrayHeap.add('g', 0.7);
        naive.add('g', 0.7);
        arrayHeap.add('c', 0.2);
        naive.add('c', 0.2);
        arrayHeap.add('b', 0.3);
        naive.add('b', 0.3);
        arrayHeap.add('d', 0.1);
        naive.add('d', 0.1);
        arrayHeap.printSimple();
        assertEquals(naive.getSmallest(), arrayHeap.getSmallest());

        arrayHeap.changePriority('d', 0.8);
        naive.changePriority('d', 0.8);
        arrayHeap.printSimple();
        assertEquals(naive.getSmallest(), arrayHeap.getSmallest());

        arrayHeap.removeSmallest();
        naive.removeSmallest();
        arrayHeap.printSimple();
        assertEquals(naive.getSmallest(), arrayHeap.getSmallest());

        arrayHeap.changePriority('f', 0.1);
        naive.changePriority('f', 0.1);
        assertEquals(naive.getSmallest(), arrayHeap.getSmallest());
        arrayHeap.printSimple();
    }

    @Test
    public void timeTests() {
        ArrayHeapMinPQ<Integer> arrayHeap = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> naive = new NaiveMinPQ<>();
        int totalItems = 10000000;
        double priority;
        long start, end;
        for (int i = 0; i < totalItems; i += 1) {
            priority = StdRandom.uniform();
            arrayHeap.add(i, priority);
            naive.add(i, priority);
        }
        int item = StdRandom.uniform(totalItems);
        start = System.currentTimeMillis();
        arrayHeap.contains(item);
        end = System.currentTimeMillis();
        System.out.println("AHMPQ contains time: " + (end - start)/1000.0 +  " seconds.");
        start = System.currentTimeMillis();
        naive.contains(item);
        end = System.currentTimeMillis();
        System.out.println("NMPQ contains time: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        arrayHeap.getSmallest();
        end = System.currentTimeMillis();
        System.out.println("AHMPQ getSmallest time: " + (end - start)/1000.0 +  " seconds.");
        start = System.currentTimeMillis();
        naive.getSmallest();
        end = System.currentTimeMillis();
        System.out.println("NMPQ getSmallest time: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        arrayHeap.removeSmallest();
        end = System.currentTimeMillis();
        System.out.println("AHMPQ removeSmallest time: " + (end - start)/1000.0 +  " seconds.");
        start = System.currentTimeMillis();
        naive.removeSmallest();
        end = System.currentTimeMillis();
        System.out.println("NMPQ removeSmallest time: " + (end - start)/1000.0 +  " seconds.");

        item = StdRandom.uniform(totalItems);
        priority = StdRandom.uniform();
        start = System.currentTimeMillis();
        arrayHeap.changePriority(item, priority);
        end = System.currentTimeMillis();
        System.out.println("AHMPQ changePriority time: " + (end - start)/1000.0 +  " seconds.");
        start = System.currentTimeMillis();
        naive.changePriority(item, priority);
        end = System.currentTimeMillis();
        System.out.println("NMPQ changePriority time: " + (end - start)/1000.0 +  " seconds.");
    }
}
