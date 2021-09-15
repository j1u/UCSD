import org.junit.Test;

import static org.junit.Assert.*;

public class PrefixNotationTest {

    @Test
    public void evaluateTest() {
        assertEquals(47, PrefixNotation.evaluate("+ 5 * 6 7"));
        assertEquals(77, PrefixNotation.evaluate("* + 5 6 7"));
        assertEquals(5, PrefixNotation.evaluate("* + 1 / 2 + 3 4 5"));
        assertEquals(262, PrefixNotation.evaluate("+ * 10 + 5 21 / 9 4"));
    }

}