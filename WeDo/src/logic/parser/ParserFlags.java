/**
 * 
 */
package logic.parser;

import java.util.EnumSet;


/**
 * @author A0112887X
 *         <p>
 *         ParserFlag keep track of parses that are executed successfully.
 *         <p>
 */
public enum ParserFlags {
    DATE_FLAG, PRIORITY_FLAG, DESCRIPTION_FLAG, COMMAND_FLAG;
    
    /**
     * Compare the set of flags ...
     * @param parseFlags
     * @param containFlags
     * @return
     */
    public static boolean containsOnly(EnumSet<ParserFlags> parseFlags, EnumSet<ParserFlags> containFlags)
    {
        if(parseFlags.containsAll(containFlags))
        {
            EnumSet<ParserFlags> temp = parseFlags.clone();
            temp.removeAll(containFlags);
            if(temp.isEmpty())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        
    }


}
