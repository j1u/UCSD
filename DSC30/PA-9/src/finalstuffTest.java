import org.junit.Test;

import static org.junit.Assert.*;

public class finalstuffTest {

    @Test
    public void methodT1() {
        finalstuff f = new finalstuff();
        String[] correct = f.method("S1h2r3o4u5d6");
        assertArrayEquals(new String[]{"Shroud", "123456"}, correct);
    }

    @Test
    public void methodT3() {
        finalstuff f = new finalstuff();
        String[] correct = f.method("l");
        assertArrayEquals(new String[]{"l", ""}, correct);
    }

    @Test
    public void methodT2() {
        finalstuff f = new finalstuff();
        String[] correct = f.method("");
        assertArrayEquals(new String[]{"", ""}, correct);
    }
}