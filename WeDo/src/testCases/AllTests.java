/**
 * 
 */
package testCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Kuan Tien Long
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ObservableListTest.class, StringHandlerTest.class,
    TaskManagerTest.class, ParserFlagTest.class, BasicDataHandlerTest.class})
public class AllTests {

}
