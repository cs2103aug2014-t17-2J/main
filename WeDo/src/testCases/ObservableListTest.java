/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.junit.Test;

import dataStorage.ObservableList;

/**
 * This test case...
 * 
 * @author Kuan Tien Long
 *
 */
public class ObservableListTest {

    /**
     * @author Kuan Tien Long observer class used for testing
     */
    class MyObserver implements Observer {
        private Object storedArg;

        @Override
        public void update(Observable o, Object arg) {
            setStoredArg(arg);
        }

        /**
         * @return the storedArg
         */
        public Object getStoredArg() {
            return storedArg;
        }

        /**
         * @param storedArg
         *            the storedArg to set
         */
        public void setStoredArg(Object storedArg) {
            this.storedArg = storedArg;
        }
    }

    @Test
    public void test() {
        testNullList();
        testValidList();
        testObserver();

    }

    /**
     * This function tests all the function of observable with a null list
     */
    private void testNullList() {
        ObservableList<String> list = new ObservableList<String>(null);
        testGetEmptyList(list);
        testRemoveEmptyList(list);
        testAddNullList(list);
        testReplaceWithNullList(list);
        testReplaceNullList(list);

    }

    /**
     * This function tests all the function of observable with a valid list
     */
    private void testValidList() {
        ArrayList<String> expectedList = new ArrayList<String>();
        ArrayList<String> storedList = new ArrayList<String>();
        ObservableList<String> list = new ObservableList<String>(storedList);

        testGetEmptyList(list);
        testRemoveEmptyList(list);
        testAddValidList(expectedList, list);
        testRemoveValidIndex(expectedList, list);
        testRemoveInvalid(expectedList, list);
        testRemoveNull(expectedList, list);
        testReplaceWithNullList(list);
        testReplaceEmptyList(expectedList, list);
        testReplaceValidList(expectedList, list);
        testAddReplacedList(expectedList, list);
        testRemoveReplacedList(expectedList, list);

    }

    /**
     * This function will test if the observer received the updated argument
     */
    private void testObserver() {

        ArrayList<String> storedList = new ArrayList<String>();
        ArrayList<String> newList = new ArrayList<String>();
        ObservableList<String> list = new ObservableList<String>(storedList);

        MyObserver observer1 = new MyObserver();
        MyObserver observer2 = new MyObserver();
        list.addObserver(observer1);
        list.addObserver(observer2);

        testObserverAdd(list, observer1, observer2);
        testObserverRemove(list, observer1, observer2);
        testObserverReplace(newList, list, observer1, observer2);

    }

    private void testObserverReplace(ArrayList<String> newList,
            ObservableList<String> list, MyObserver observer1,
            MyObserver observer2) {
        testReplaceEmptyList(newList, list);
        assertEquals((Object) newList, observer1.getStoredArg());
        assertEquals((Object) newList, observer2.getStoredArg());

        newList.add("something");
        newList.add("your thing");
        list.replaceList(newList);
        assertEquals((Object) newList, observer1.getStoredArg());
        assertEquals((Object) newList, observer2.getStoredArg());
    }

    private void testObserverRemove(ObservableList<String> list,
            MyObserver observer1, MyObserver observer2) {
        assertTrue(list.remove(null));
        assertEquals((Object) null, observer1.getStoredArg());
        assertEquals((Object) null, observer2.getStoredArg());

        assertTrue(list.remove(0));
        assertEquals((Object) "hello", observer1.getStoredArg());
        assertEquals((Object) "hello", observer2.getStoredArg());
    }

    private void testObserverAdd(ObservableList<String> list,
            MyObserver observer1, MyObserver observer2) {
        assertTrue(list.add("hello"));
        assertEquals((Object) "hello", observer1.getStoredArg());
        assertEquals((Object) "hello", observer2.getStoredArg());

        assertTrue(list.add("yo"));
        assertEquals((Object) "yo", observer1.getStoredArg());
        assertEquals((Object) "yo", observer2.getStoredArg());

        assertTrue(list.add(null));
        assertEquals((Object) null, observer1.getStoredArg());
        assertEquals((Object) null, observer2.getStoredArg());
    }

    private void testReplaceNullList(ObservableList<String> list) {
        testReplaceWithEmptyList(list);
        testReplaceWithValidList(list);
    }

    private void testReplaceWithValidList(ObservableList<String> list) {
        ArrayList<String> newList = new ArrayList<String>();
        ArrayList<String> expectedList = new ArrayList<String>();
        newList.add("Hello");
        newList.add("time");
        expectedList.add("Hello");
        expectedList.add("time");
        list.replaceList(newList);
        assertEquals(expectedList, list.getList());
    }

    private void testReplaceWithEmptyList(ObservableList<String> list) {
        list.replaceList(new ArrayList<String>());
        assertEquals(new ArrayList<String>(), list.getList());
    }

    private void testAddNullList(ObservableList<String> list) {
        assertFalse(list.add(null));
        assertFalse(list.add("What will happen"));
        assertEquals(null, list.getList());
    }

    private void testRemoveReplacedList(ArrayList<String> expectedList,
            ObservableList<String> list) {
        assertFalse(list.remove("hello"));
        assertTrue(list.remove("Hello"));
        expectedList.remove("Hello");
        assertEquals(expectedList, list.getList());
    }

    private void testAddReplacedList(ArrayList<String> expectedList,
            ObservableList<String> list) {
        assertTrue(list.add("here now"));
        expectedList.add("here now");
    }

    private void testReplaceValidList(ArrayList<String> expectedList,
            ObservableList<String> list) {
        ArrayList<String> newList = new ArrayList<String>();
        newList.add("Hello");
        newList.add("time");
        expectedList.add("Hello");
        expectedList.add("time");
        list.replaceList(newList);
        assertEquals(expectedList, list.getList());
    }

    private void testReplaceEmptyList(ArrayList<String> expectedList,
            ObservableList<String> list) {
        list.replaceList(new ArrayList<String>());
        expectedList.clear();
        assertEquals(new ArrayList<String>(), list.getList());
    }

    private void testReplaceWithNullList(ObservableList<String> list) {
        list.replaceList(null);
        assertEquals(null, list.getList());
    }

    private void testRemoveNull(ArrayList<String> expectedList,
            ObservableList<String> list) {
        assertTrue(list.remove(null));
        expectedList.remove(null);
        assertEquals(expectedList, list.getList());
    }

    private void testRemoveInvalid(ArrayList<String> expectedList,
            ObservableList<String> list) {
        assertFalse(list.remove(10));
        assertFalse(list.remove("ehh"));
        assertEquals(expectedList, list.getList());
    }

    private void testRemoveValidIndex(ArrayList<String> expectedList,
            ObservableList<String> list) {
        assertTrue(list.remove(1));
        expectedList.remove(1);
    }

    private void testAddValidList(ArrayList<String> expectedList,
            ObservableList<String> list) {
        assertTrue(list.add(null));
        assertTrue(list.add("What will happen"));
        expectedList.add(null);
        expectedList.add("What will happen");
        assertEquals(expectedList, list.getList());
    }

    private void testGetEmptyList(ObservableList<String> list) {
        assertEquals(null, list.get(3));
    }

    private void testRemoveEmptyList(ObservableList<String> list) {
        assertFalse(list.remove(1));
        assertFalse(list.remove("what"));
        assertFalse(list.remove(null));
    }

}
