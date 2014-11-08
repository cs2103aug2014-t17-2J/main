/**
 * 
 */
package testCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//@author A0112887X -unused
/**
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ObservableListTest.class, StringHandlerTest.class,
        ParserManagerTest.class, CommandValidateTest.class,
        DateParserTest.class,
        PriorityParserTest.class, UndoHandlerTest.class, TestLogicManager.class })
//BasicDataHandlerTest.class,

public class AllTests {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("AllTests");
    }

}
