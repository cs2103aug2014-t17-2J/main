/**
 * 
 */
package logic.parser;

import logic.utility.StringHandler;

/**
 * @author Kuan Tien Long
 *
 */
public class DescriptionParser 
{
    private String wordUsed;
    private String wordRemaining;
    private String description;
    
    /**
     * <p> The source will be parsed to see if it contains date.
     * @param source the String to be parsed
     * @return if source contains valid description 
     */
    public boolean tryParse(String source)
    {
        if (source == null) {
            return false;
        }

        source = source.trim();

        if (source.isEmpty()) 
        {
            return false;
        }
        
        String possibleCommand = StringHandler.getFirstWord(source);
        source = StringHandler.removeFirstMatched(source, possibleCommand);
        wordUsed = description = source.trim();
        wordRemaining = possibleCommand;

        
        if (source.isEmpty()) 
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }

    /**
     * @return the wordUsed
     */
    public String getWordRemaining() {
        return wordRemaining;
    }

    /**
     * @return the wordRemaining
     */
    public String getWordUsed() {
        return wordUsed;
    }
    
    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    
}
