/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

/**
 * @author Kuan Tien Long
 *
 */
public class NattyParserTest {

    @Test
    public void test() {
        
        String init = "add this task today to friday deadline 2pm";
        Parser test1 = new Parser();
        
        
        List<DateGroup> dateGroups = new Parser().parse(init);
        
        int startPosition = init.length();
        int endPosition = 0;
        for (DateGroup dateGroup : dateGroups) {

        int position = dateGroup.getPosition();
        int length = dateGroup.getText().length();
        String dateText = dateGroup.getText();
        startPosition = Math.min(startPosition, position);
        endPosition = Math.max(position + length, endPosition);
        }
        
        String nattyDate = init.substring(startPosition, endPosition);
        System.out.println("Setted = " +nattyDate);
        
        }

}
