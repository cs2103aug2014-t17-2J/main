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
        assertFalse(list.remove(1));
        assertFalse(list.remove("what"));
        assertFalse(list.remove(null));
        assertFalse(list.add(null));
        assertFalse(list.add("What will happen"));
        assertEquals(null,list.getList());
        list.replaceList(null);
        assertEquals(null, list.getList());
        list.replaceList(new ArrayList<String>());
        assertEquals(new ArrayList<String>(), list.getList());
        ArrayList<String> newList = new ArrayList<String>();
        ArrayList<String> expectedList = new ArrayList<String>();
        newList.add("Hello");
        newList.add("time");
        expectedList.add("Hello");
        expectedList.add("time");
        list.replaceList(newList);
        assertEquals(expectedList, list.getList());

    }
    
    

}
