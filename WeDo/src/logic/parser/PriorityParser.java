/**
 * 
 */
package logic.parser;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import logic.utility.KeyMatcher;
import logic.utility.StringHandler;


import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;

import definedEnumeration.Priority;

/**
 * @author Kuan Tien Long
 *
 */
public class PriorityParser {
    
    private String wordRemaining;
    private String wordUsed;
    private Priority priority;

    /**
     * <p> The source will be parsed to see if it contains date.
     * @param source the String to be parsed
     * @return if source contains valid priority 
     */
    public boolean tryParse(String source)
    {
        String firstTwoWords = StringHandler.getFirstTwoWords(source);
        String lastTwoWords = StringHandler.getLastTwoWords(source);
        
        
        if(!tryParsePriority(firstTwoWords)) // failed first two word
        {
            if(!tryParsePriority(lastTwoWords)) // failed second two words
            {
                return false;
            }
            else
            {
                priority = KeyMatcher.matchKey(createPriorityLevelFakeMultiMap(), lastTwoWords); 
                if(!isPriorityParsed())
                {
                    return false;
                }
                
                wordUsed = lastTwoWords;

            }
        }
        else
        {
                priority = KeyMatcher.matchKey(createPriorityLevelFakeMultiMap(), firstTwoWords);   
                if(!isPriorityParsed())
                {
                    return false;
                }

                wordUsed = firstTwoWords;

        }
        
        
        wordRemaining = StringHandler.removeFirstMatched(source, wordUsed);
        
        return true;
    }

    /**
     * @return if priority is successfully parsed
     */
    private boolean isPriorityParsed() {
        return priority != null;
        
    }

    /**
     * @param source the source to be deciphered
     * @return whether priority key word is found
     */
    private boolean tryParsePriority(String source) {
        String parsedWord = KeyMatcher.matchKey(createPriorityFakeMultiMap(),
                source);
        if (parsedWord == null) {
            return false;
        } else {
            return true;
        }

    }
    
    public Priority getPriority()
    {
        return priority;
    }
    
    public String getWordUsed()
    {
        return wordUsed;
    }
    
    public String getWordRemaining()
    {
        return wordRemaining;
    }



    

    private Multimap<String, String> createPriorityFakeMultiMap() {

        Multimap<String, String> availableActions = ArrayListMultimap.create();

        final Map<String, Collection<String>> priorityActions = ImmutableMap
                .<String, Collection<String>> of("priority",
                        Arrays.asList("priority", "pri"));

        for (String key : priorityActions.keySet()) {
            availableActions.putAll(key, priorityActions.get(key));
        }

        return availableActions;

    }

    /**
     * This function create a map of commands and add them to the multi map. The
     * real multi map will be generated by reading a file which consist the
     * available action word to match
     */
    private Multimap<Priority, String> createPriorityLevelFakeMultiMap() {
        Multimap<Priority, String> availableActions = ArrayListMultimap
                .create();

        final Map<Priority, Collection<String>> highPriorityActions = ImmutableMap
                .<Priority, Collection<String>> of(Priority.PRIORITY_HIGH,
                        Arrays.asList("high", "urgent", "now"));
        final Map<Priority, Collection<String>> mediumPriorityActions = ImmutableMap
                .<Priority, Collection<String>> of(Priority.PRIORITY_MEDIUM,
                        Arrays.asList("medium", "med", "later", "next time"));
        final Map<Priority, Collection<String>> lowPriorityActions = ImmutableMap
                .<Priority, Collection<String>> of(Priority.PRIORITY_LOW,
                        Arrays.asList("low", "kiv", "when free", "-no"));

        addMapToMultiMap(highPriorityActions, availableActions);
        addMapToMultiMap(mediumPriorityActions, availableActions);
        addMapToMultiMap(lowPriorityActions, availableActions);

        return availableActions;
    }

    private void addMapToMultiMap(final Map<Priority, Collection<String>> map,
            Multimap<Priority, String> availableActions) {
        for (Priority key : map.keySet()) {
            availableActions.putAll(key, map.get(key));
        }
    }

}
