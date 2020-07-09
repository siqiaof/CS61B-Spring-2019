package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        assertEquals(1, (int) arb.dequeue());
        arb.enqueue(4);
        assertEquals(2, (int) arb.peek());

        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(3);
        arb2.enqueue(2);
        arb2.enqueue(3);
        assertFalse(arb2.equals(arb));
        arb2.enqueue(4);
        System.out.println(arb2.equals(arb));
    }
}
