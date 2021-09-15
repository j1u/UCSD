import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BloomFilterJuniorTest {
    BloomFilterJunior t1;

    @Before
    public void setUp() throws Exception {
        t1 = new BloomFilterJunior(50);
    }

    @Test
    public void insert() {
        t1.insert("ssfhj");
        assertTrue(t1.lookup("ssfhj"));
        t1.insert("dfsa");
        assertTrue(t1.lookup("dfsa"));
        t1.insert("sadfg");
        assertTrue(t1.lookup("sadfg"));
        t1.insert("hjrw");
        assertTrue(t1.lookup("hjrw"));
        t1.insert("Asdf");
        assertTrue(t1.lookup("Asdf"));
    }

    @Test
    public void lookup() {
        t1.insert("ssfhj");
        assertTrue(t1.lookup("ssfhj"));
        t1.insert("dfsa");
        assertTrue(t1.lookup("dfsa"));
        t1.insert("sadfg");
        assertTrue(t1.lookup("sadfg"));
        t1.insert("hjrw");
        assertTrue(t1.lookup("hjrw"));
        t1.insert("Asdf");
        assertTrue(t1.lookup("Asdf"));
    }
}