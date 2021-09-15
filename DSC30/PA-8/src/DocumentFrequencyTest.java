/*
 * Name: James Lu
 * PID: A16687580
 */

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DocumentFrequencyTest {

    DocumentFrequency t1;

    @Before
    public void setUp() throws Exception {
        t1 = new DocumentFrequency("./src/files/test.txt");
    }

    @Test
    public void numDocuments() {
        assertEquals(5, t1.numDocuments());
    }

    @Test
    public void query() {
        assertEquals(5, t1.query("quick"));
        assertEquals(2, t1.query("dog"));
        assertEquals(0, t1.query("dsc30"));
    }
}