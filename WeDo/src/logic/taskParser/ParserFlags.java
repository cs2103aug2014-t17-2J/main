/**
 * 
 */
package logic.taskParser;

import java.util.EnumSet;

/**
 * @author Kuan Tien Long
 *         <p>
 *         ParserFlag keep track of parses that are executed successfully.
 *         <p>
 */
public enum ParserFlags {
    DATE_FLAG, PRIORITY_FLAG, DESCRIPTION_FLAG, COMMAND_FLAG;

    private static final EnumSet<ParserFlags> VALID_PARSE = EnumSet.of(
            ParserFlags.DESCRIPTION_FLAG, ParserFlags.COMMAND_FLAG);

    /**
     * <p>
     * Determine whether the parse occurred was valid by matching it with
     * VALID_PARSE which contains compulsory parse result(s) required
     * <p>
     * 
     * @param flagSet the set of ParserFlag to be tested
     * @return if it contains all of the VALID_PARSE flag
     */
    public static boolean isParseValid(EnumSet<ParserFlags> flagSet) {

        if (flagSet.containsAll(VALID_PARSE)) {
            return true;
        } else {
            return false;
        }
    }
}
