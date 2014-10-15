/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.util.EnumSet;

import logic.taskParser.ParserFlag;

import org.junit.Test;

/**
 * @author Kuan Tien Long
 *
 */
public class ParserFlagTest {

    @Test
    public void test() {
        EnumSet<ParserFlag> myFlags = EnumSet.noneOf(ParserFlag.class);

        myFlags.add(ParserFlag.COMMAND_FLAG);
        myFlags.add(ParserFlag.DESCRIPTION_FLAG);

        assertTrue(ParserFlag.isParseValid(myFlags));

        myFlags.remove(ParserFlag.COMMAND_FLAG);

        assertFalse(ParserFlag.isParseValid(myFlags));

        myFlags = EnumSet.noneOf(ParserFlag.class);
        assertFalse(ParserFlag.isParseValid(myFlags));

        myFlags.add(ParserFlag.DATE_FLAG);
        assertFalse(ParserFlag.isParseValid(myFlags));

        myFlags.add(ParserFlag.PRIORITY_FLAG);
        assertFalse(ParserFlag.isParseValid(myFlags));

        myFlags.add(ParserFlag.DESCRIPTION_FLAG);
        assertFalse(ParserFlag.isParseValid(myFlags));

        myFlags.add(ParserFlag.COMMAND_FLAG);
        assertTrue(ParserFlag.isParseValid(myFlags));

    }

}
