package testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.StringHandler;

public class StringHandlerTest {

    @Test
    public void test() {
        getFirstWordTest();
        
        removeFirstMatchedTest();
        
            
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
        assertEquals("Pizza", StringHandler.removeFirstMatched("add Pizza", "add"));
        assertEquals("pig eat me", StringHandler.removeFirstMatched("pig eat me","add"));
        assertEquals("pig eat me", StringHandler.removeFirstMatched("pig eat me",null));
        assertEquals(null, StringHandler.removeFirstMatched(null,"add"));
        assertEquals(null, StringHandler.removeFirstMatched(null,null));
    }

}
