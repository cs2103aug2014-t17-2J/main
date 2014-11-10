/**
 * 
 */
package testCases;

import static org.junit.Assert.*;

import java.util.EnumSet;

import logic.command.commandList.AddCommand;
import logic.command.commandList.Command;
import logic.parser.ParserFlags;

import org.junit.Test;

//@author A0112675H
/**
 *
 */
public class CommandValidateTest {

    @Test
    public void test() {
        EnumSet<ParserFlags> myFlags = EnumSet.noneOf(ParserFlags.class);

        Command addCommand = new AddCommand();
        myFlags.add(ParserFlags.COMMAND_FLAG);
        myFlags.add(ParserFlags.DESCRIPTION_FLAG);

        assertTrue(addCommand.validate(myFlags));

        myFlags.remove(ParserFlags.COMMAND_FLAG);

        assertFalse(addCommand.validate(myFlags));

        myFlags = EnumSet.noneOf(ParserFlags.class);
        assertFalse(addCommand.validate(myFlags));

        myFlags.add(ParserFlags.DATE_FLAG);
        assertFalse(addCommand.validate(myFlags));

        myFlags.add(ParserFlags.PRIORITY_FLAG);
        assertFalse(addCommand.validate(myFlags));

        myFlags.add(ParserFlags.DESCRIPTION_FLAG);
        assertFalse(addCommand.validate(myFlags));

        myFlags.add(ParserFlags.COMMAND_FLAG);
        assertTrue(addCommand.validate(myFlags));

    }

}
