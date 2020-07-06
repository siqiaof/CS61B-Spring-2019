import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void testStudentArray() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder message = new StringBuilder("\n");
        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.3) {
                sad.addLast(i);
                ads.addLast(i);
                message.append("addLast(" + i + ")\n");
            } else if (numberBetweenZeroAndOne < 0.6) {
                sad.addFirst(i);
                ads.addFirst(i);
                message.append("addFirst(" + i + ")\n");
            } else if (numberBetweenZeroAndOne < 0.8 && ads.size() > 0) {
                message.append("removeLast()\n");
                assertEquals(message.toString(), ads.removeLast(), sad.removeLast());
            } else if (ads.size() > 0) {
                message.append("removeFirst()\n");
                assertEquals(message.toString(), ads.removeFirst(),sad.removeFirst());
            }
        }
    }
}
