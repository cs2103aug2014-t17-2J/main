package testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.StringHandler;

public class StringHandlerTest {

    @Test
    public void test() {
        
        getFirstWordTest();
        removeFirstMatchedTest();
        
        assertEquals("20/09/2014", StringHandler.convertImplicitFormalDate("20/09/14"));
        assertEquals("22/11/2000", StringHandler.convertImplicitFormalDate("22/11/00"));
        assertEquals("01/02/2099", StringHandler.convertImplicitFormalDate("01/02/99"));
        assertEquals("3/03/2019", StringHandler.convertImplicitFormalDate("3/03/19"));
        assertEquals("5/2/2010", StringHandler.convertImplicitFormalDate("5/2/10"));
        assertEquals("15/8/2009", StringHandler.convertImplicitFormalDate("15/8/09"));
        

        assertEquals("20/09/2014", StringHandler.convertImplicitFormalDate("20-09-14"));
        assertEquals("22/11/2000", StringHandler.convertImplicitFormalDate("22-11-00"));
        assertEquals("01/02/2099", StringHandler.convertImplicitFormalDate("01-02-99"));
        assertEquals("3/03/2019", StringHandler.convertImplicitFormalDate("3-03-19"));
        assertEquals("5/2/2010", StringHandler.convertImplicitFormalDate("5-2-10"));
        assertEquals("15/8/2009", StringHandler.convertImplicitFormalDate("15/8/09"));
    }

    private void getFirstWordTest() {
        assertEquals("add", StringHandler.getFirstWord("add Pizza"));
        assertEquals(null, StringHandler.getFirstWord(""));
        assertEquals(null, StringHandler.getFirstWord("       "));
        assertEquals(null, StringHandler.getFirstWord(null));
        assertEquals("greedIsGood", StringHandler.getFirstWord("greedIsGood"));
        assertEquals("Pig", StringHandler.getFirstWord("Pig is flying !!!"));
    }

    private void removeFirstMatchedTest() {
        assertEquals("Pizza",
                StringHandler.removeFirstMatched("add Pizza", "add"));
        assertEquals("pig eat me",
                StringHandler.removeFirstMatched("pig eat me", "add"));
        assertEquals("pig eat me",
                StringHandler.removeFirstMatched("pig eat me", null));
        assertEquals(null, StringHandler.removeFirstMatched(null, "add"));
        assertEquals(null, StringHandler.removeFirstMatched(null, null));
    }

}
