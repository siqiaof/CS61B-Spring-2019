import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {
    @Test
    public void tests(){
        UnionFind u = new UnionFind(5);
        u.union(0, 1);
        u.union(1, 2);
        assertTrue(u.connected(0, 2));
        u.union(3, 4);
        assertFalse(u.connected(0, 4));
        u.union(0, 3);
        assertEquals((int) 1, u.find(3));
    }
}
