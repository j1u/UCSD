import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextEditorTest {
    TextEditor tst;

    @Before
    public void init(){
        tst = new TextEditor("FUCK YEAH MANE!");
    }

    @Test
    public void getTextTest() {
        assertEquals("FUCK YEAH MANE!", tst.getText());
    }

    @Test
    public void lengthTest() {
        assertEquals(15, tst.length());
    }

    @Test
    public void caseConvertTest() {
        tst.caseConvert(0, 5);
        assertEquals("fuck YEAH MANE!", tst.getText());
    }

    @Test
    public void insertTest() {
        tst.insert(0, "UM ");
        assertEquals("UM FUCK YEAH MANE!", tst.getText());
    }

    @Test
    public void deleteTest() {
        tst.delete(0, 5);
        assertEquals("YEAH MANE!", tst.getText());
    }

    @Test
    public void undoTest() {
        tst.caseConvert(0, 5);
        assertEquals("fuck YEAH MANE!", tst.getText());
        tst.undo();
        assertEquals("FUCK YEAH MANE!", tst.getText());

        tst.insert(0, "UM ");
        assertEquals("UM FUCK YEAH MANE!", tst.getText());
        tst.undo();
        assertEquals("FUCK YEAH MANE!", tst.getText());

        tst.delete(0, 5);
        assertEquals("YEAH MANE!", tst.getText());
        tst.undo();
        assertEquals("FUCK YEAH MANE!", tst.getText());
    }

    @Test
    public void redoTest() {
        tst.caseConvert(0, 5);
        assertEquals("fuck YEAH MANE!", tst.getText());
        tst.undo();
        assertEquals("FUCK YEAH MANE!", tst.getText());
        tst.redo();
        assertEquals("fuck YEAH MANE!", tst.getText());
        tst.undo();
        assertEquals("FUCK YEAH MANE!", tst.getText());

        tst.insert(0, "UM ");
        assertEquals("UM FUCK YEAH MANE!", tst.getText());
        tst.undo();
        assertEquals("FUCK YEAH MANE!", tst.getText());
        tst.redo();
        assertEquals("UM FUCK YEAH MANE!", tst.getText());
        tst.undo();
        assertEquals("FUCK YEAH MANE!", tst.getText());

        tst.delete(0, 5);
        assertEquals("YEAH MANE!", tst.getText());
        tst.undo();
        assertEquals("FUCK YEAH MANE!", tst.getText());
        tst.redo();
        assertEquals("YEAH MANE!", tst.getText());
        tst.undo();
        assertEquals("FUCK YEAH MANE!", tst.getText());
    }
}