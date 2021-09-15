import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TFIDFTest {

    TFIDF t1;

    @Before
    public void setUp() throws Exception {
        t1 = new TFIDF("./src/files/test.txt");
    }

    @Test
    public void numDocuments() {
        int num = t1.numDocuments();
        assertEquals(5, num);
    }

    @Test
    public void getTF() {

    }

    @Test
    public void getIDF() {
    }

    @Test
    public void query() {
        double tfidf1 = t1.query("fox", 0);
        System.out.println(tfidf1);

        double tfidf2 = t1.query("tHe", 1);
        System.out.println(tfidf2);

        double tfidf3 = t1.query("quIcK", 3);
        System.out.println(tfidf3);
    }
}