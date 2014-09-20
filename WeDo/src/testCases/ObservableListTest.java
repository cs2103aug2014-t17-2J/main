/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import dataStorage.ObservableList;

/**
 * This test case...
 * @author Kuan Tien Long
 *
 */
public class ObservableListTest {

    @Test
    public void test() 
    {
        testNullList();
        
    }

    /**
     * 
     */
    private void testNullList() 
    {
        ObservableList<String> list = new ObservableList<String>(null);
        assertEquals(null, list.get(3));
        assertFalse(list.add("What will happen"));
        assertEquals(null,list.getList());
    }
    
    

}
