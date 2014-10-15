/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.util.EnumSet;

import logic.taskParser.ParserFlags;

import org.junit.Test;

/**
 * @author Kuan Tien Long
 *
 */
public class ParserFlagTest {

    @Test
    public void test() {
        EnumSet<ParserFlags> myFlags = EnumSet.noneOf(ParserFlags.class);

        myFlags.add(ParserFlags.COMMAND_FLAG);
        myFlags.add(ParserFlags.DESCRIPTION_FLAG);

        assertTrue(ParserFlags.isParseValid(myFlags));

        myFlags.remove(ParserFlags.COMMAND_FLAG);

        assertFalse(ParserFlags.isParseValid(myFlags));

        myFlags = EnumSet.noneOf(ParserFlags.class);
        assertFalse(ParserFlags.isParseValid(myFlags));

        myFlags.add(ParserFlags.DATE_FLAG);
        assertFalse(ParserFlags.isParseValid(myFlags));

        myFlags.add(ParserFlags.PRIORITY_FLAG);
        assertFalse(ParserFlags.isParseValid(myFlags));

        myFlags.add(ParserFlags.DESCRIPTION_FLAG);
        assertFalse(ParserFlags.isParseValid(myFlags));

        myFlags.add(ParserFlags.COMMAND_FLAG);
        assertTrue(ParserFlags.isParseValid(myFlags));

    }

}
