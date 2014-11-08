package testCases;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.utility.StringHandler;

//@author A0112887X 
/**
 *
 */
public class StringHandlerTest {

    @Test
    public void test() {
        
        getFirstWordTest();
        removeFirstMatchedTest();
        convertImplicitFormalDateTest();
        convertFormalDateTest();
        convertDateDDMMTest();
        
       
    }

    private void convertFormalDateTest() {
        assertEquals("2014/09/20", StringHandler.convertFormalDate("20/09/2014"));
        assertEquals("1900/11/22", StringHandler.convertFormalDate("22/11/1900"));
        assertEquals("2121/02/01", StringHandler.convertFormalDate("01/02/2121"));
        assertEquals("2019/03/3", StringHandler.convertFormalDate("3/03/2019"));
        assertEquals("2010/2/5", StringHandler.convertFormalDate("5/2/2010"));
        assertEquals("2009/8/15", StringHandler.convertFormalDate("15/8/2009"));
        assertEquals("1909/8/2 at here", StringHandler.convertFormalDate("2/8/1909 at here"));
        assertEquals("hello, is 2109/8/09 at here", StringHandler.convertFormalDate("hello, is 09/8/2109 at here"));
        assertEquals("hello, is 2012/9/19", StringHandler.convertFormalDate("hello, is 19/9/2012"));
        
    
        assertEquals("2014/09/20", StringHandler.convertFormalDate("20-09-2014"));
        assertEquals("1900/11/22", StringHandler.convertFormalDate("22-11-1900"));
        assertEquals("2121/02/01", StringHandler.convertFormalDate("01-02-2121"));
        assertEquals("2019/03/3", StringHandler.convertFormalDate("3-03-2019"));
        assertEquals("2010/2/5", StringHandler.convertFormalDate("5-2-2010"));
        assertEquals("2009/8/15", StringHandler.convertFormalDate("15-8-2009"));
        assertEquals("1909/8/2 at here", StringHandler.convertFormalDate("2/8/1909 at here"));
        assertEquals("hello, is 2109/8/09 at here", StringHandler.convertFormalDate("hello, is 09/8/2109 at here"));
        assertEquals("hello, is 2012/9/19", StringHandler.convertFormalDate("hello, is 19/9/2012"));
        
    }

    private void convertImplicitFormalDateTest() {
        assertEquals("20/09/2014", StringHandler.convertDateDDMMYY("20/09/14"));
        assertEquals("22/11/2000", StringHandler.convertDateDDMMYY("22/11/00"));
        assertEquals("01/02/2099", StringHandler.convertDateDDMMYY("01/02/99"));
        assertEquals("3/03/2019", StringHandler.convertDateDDMMYY("3/03/19"));
        assertEquals("5/2/2010", StringHandler.convertDateDDMMYY("5/2/10"));
        assertEquals("15/8/2009", StringHandler.convertDateDDMMYY("15/8/09"));
        assertEquals("2/8/2009 at here", StringHandler.convertDateDDMMYY("2/8/09 at here"));
        assertEquals("hello, is 09/8/2009 at here", StringHandler.convertDateDDMMYY("hello, is 09/8/09 at here"));
        assertEquals("hello, is 19/9/2012", StringHandler.convertDateDDMMYY("hello, is 19/9/12"));

        
        assertEquals("20/09/2014", StringHandler.convertDateDDMMYY("20-09-14"));
        assertEquals("22/11/2000", StringHandler.convertDateDDMMYY("22-11-00"));
        assertEquals("01/02/2099", StringHandler.convertDateDDMMYY("01-02-99"));
        assertEquals("3/03/2019", StringHandler.convertDateDDMMYY("3-03-19"));
        assertEquals("5/2/2010", StringHandler.convertDateDDMMYY("5-2-10"));
        assertEquals("15/8/2009", StringHandler.convertDateDDMMYY("15-8-09"));
        assertEquals("2/8/2009 at here", StringHandler.convertDateDDMMYY("2-8-09 at here"));
        assertEquals("hello, is 09/8/2009 at here", StringHandler.convertDateDDMMYY("hello, is 09-8-09 at here"));
        assertEquals("hello, is 19/09/2012", StringHandler.convertDateDDMMYY("hello, is 19/09/12"));
    }
    
    private void convertDateDDMMTest()
    {
        assertEquals("20/09/14", StringHandler.convertDateDDMM("20/09/14"));
        assertEquals("22/11/2014", StringHandler.convertDateDDMM("22/11"));
        assertEquals("01/02/2014", StringHandler.convertDateDDMM("01/02"));
        assertEquals("3/03/19", StringHandler.convertDateDDMM("3/03/19"));
        assertEquals("5/2/10", StringHandler.convertDateDDMM("5/2/10"));
        assertEquals("15/8/09", StringHandler.convertDateDDMM("15/8/09"));
        assertEquals("2/8/09 at here", StringHandler.convertDateDDMM("2/8/09 at here"));
        assertEquals("2/8/2014 at here", StringHandler.convertDateDDMM("2/8 at here"));
        assertEquals("hello, is 09/8/2014 at here", StringHandler.convertDateDDMM("hello, is 09/8 at here"));
        assertEquals("hello, is 09/8/09 at here", StringHandler.convertDateDDMM("hello, is 09/8/09 at here"));
        assertEquals("hello, is 19/9/12", StringHandler.convertDateDDMM("hello, is 19/9/12"));
        assertEquals("hello, is 19/9/2014", StringHandler.convertDateDDMM("hello, is 19/9"));
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
