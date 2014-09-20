package logic;

import java.util.ArrayList;

import dataStorage.DataHandler;

/**
 * This class consist simple implementation for searching...
 * Modify as u please.
 */
public class SearchEngine {

    /**
     * @param source
     *            the input source that need to be searched
     * @param searchInput
     *            the searchInput to be searched
     * @return the result that matched with the searchInput(case insensitive)
     */
    
    /**
     * @param dataHandler
     * @param description
     * @return
     */
    public ArrayList<Task> searchCaseInsensitive(DataHandler dataHandler,
            String description) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ArrayList<String> searchCaseInsensitive(ArrayList<String> source,
            String searchInput) {
        ArrayList<String> searchList = new ArrayList<String>();
        if (invalidList(source) || invalidSearchInput(searchInput)) {
            return searchList;
        }

        final int INITIAL_INDEX = 1;
        int index = INITIAL_INDEX;
        searchInput = searchInput.trim().toLowerCase();

        for (String task : source) {

            if (task.toLowerCase().contains(searchInput)) {
                searchList.add(index + ". " + task);
            }
            index++;
        }
        return searchList;
    }

    /**
     * @param source
     * @return whether the list consist anything
     */
    private boolean invalidList(ArrayList<String> source) {
        return source == null || source.isEmpty();
    }

    /**
     * @param searchInput
     * @return whether the searchInput consist anything
     */
    private boolean invalidSearchInput(String searchInput) {
        return searchInput == null || searchInput.trim().isEmpty();
    }

    /**
     * @param source
     *            the input source that need to be searched
     * @param searchInput
     *            the searchInput to be searched
     * @return the result that matched with the searchInput(case sensitive)
     */
    public ArrayList<String> searchSensitive(ArrayList<String> source,
            String searchInput) {
        ArrayList<String> searchList = new ArrayList<String>();
        if (invalidList(source) || invalidSearchInput(searchInput)) {
            return searchList;
        }

        final int INITIAL_INDEX = 1;
        int index = INITIAL_INDEX;

        for (String task : source) {
            if (task.contains(searchInput)) {
                searchList.add(index + ". " + task);
            }
            index++;
        }
        return searchList;
    }



}
