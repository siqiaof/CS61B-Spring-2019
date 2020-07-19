import static org.junit.Assert.*;
import org.junit.Test;

public class TestRollingString {
    @Test
    public void someTests() {
        RollingString r = new RollingString("abcd", 4);
        System.out.println(r.hashCode());
        r.addChar('a');
        assertEquals("bcda", r.toString());
        System.out.println(r.hashCode());
    }
}
