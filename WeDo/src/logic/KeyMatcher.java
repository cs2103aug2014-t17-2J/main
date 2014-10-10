/**
 * 
 */
package logic;

import com.google.common.collect.Multimap;

/**
 * @author Kuan Tien Long
 *
 */
public class KeyMatcher 
{

    /**
     * This function search for values inside the multi-map that matches with any word inside the input
     * Return key which contains the value, return null if no matches
     * @param multimap
     * @param input
     * @return
     */
    public static<K> K matchKey(Multimap<K, String> multimap, String input)
    {
        input = input.toLowerCase();
        input = " " + input + " ";
        
        for (K key : multimap.keys()) 
        {
            for (String value : multimap.get(key))
            {
                if(input.contains(" " + value.toLowerCase() + " "))
                {
                    return key;
                }
            }
        }
        return null;
    }
}
