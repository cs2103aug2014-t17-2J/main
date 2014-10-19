/**
 * 
 */
package logic.parser;

import java.util.EnumSet;

/**
 * @author Kuan Tien Long
 *         <p>
 *         ParserFlag keep track of parses that are executed successfully.
 *         <p>
 */
public enum ParserFlags {
    DATE_FLAG, PRIORITY_FLAG, DESCRIPTION_FLAG, COMMAND_FLAG;

    private static final EnumSet<ParserFlags> VALID_ADD_SET = EnumSet.of(
            ParserFlags.DESCRIPTION_FLAG, ParserFlags.COMMAND_FLAG);

    /**
     * <p>
     * Determine whether the valid command is parsed
     * <p>
     * 
     * @param flagSet the set of ParserFlag to be tested
     * @return if it contains of the Command flag
     */
    public static boolean isCommandParsed(EnumSet<ParserFlags> flagSet) {

        if (flagSet.contains(COMMAND_FLAG)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * <p>
     * Determine whether the parse occurred was valid by matching it with
     * VALID_PARSE which contains compulsory parse result(s) for add command required
     * <p>
     * 
     * @param flagSet the set of ParserFlag to be tested
     * @return if it contains all of the VALID_ADD_SET flag
     */
    public static boolean isParseValidForAdd(EnumSet<ParserFlags> flagSet) {

        if (flagSet.containsAll(VALID_ADD_SET)) {
            return true;
        } else {
            return false;
        }
    }    
    
    
    /**
     * <p>
     * Determine whether the command is valid
     * <p>
     * 
     * @param flagSet the set of ParserFlag to be tested
     * @return if it contains more than 1 flags
     */
    public static boolean isCommandValid(EnumSet<ParserFlags> flagSet) {
        return flagSet.size()>1;
    }
    
    
    
}
