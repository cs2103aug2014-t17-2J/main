package logic;

import java.util.ArrayList;

import com.google.common.collect.Multimap;

/**
 * For Waimin: Please edit :D This class consist simple implementation for
 * searching... Modify as u please.
 */
public class SearchEngine {

    /**
     * @param source
     *            the input source that need to be searched
     * @param searchInput
     *            the searchInput to be searched
     * @return the result that matched with the searchInput(case insensitive)
     */
    public ArrayList<Task> searchCaseInsensitive(String description) {
        // TODO Auto-generated method stub
        return null;
    }

    public ArrayList<Task> searchCaseInsensitive(
            Multimap<String, Task> multimap, String searchInput) {
        ArrayList<Task> searchList = new ArrayList<Task>();
        if (invalidList(multimap) || invalidSearchInput(searchInput)) {
            return searchList;
        }

        searchInput = searchInput.trim().toLowerCase();

        for (String key : multimap.keys()) {
            for (Task task : multimap.get(key)) {

                if (task.getDescription().toLowerCase().contains(searchInput)) {
                    searchList.add(task);
                }
            }
        }
        return searchList;
    }

    /**
     * @param source
     * @return whether the list consist anything
     */
    private boolean invalidList(Multimap<String, Task> source) {
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
    public ArrayList<Task> searchSensitive(Multimap<String, Task> multimap,
            String searchInput) {
        ArrayList<Task> searchList = new ArrayList<Task>();
        if (invalidList(multimap) || invalidSearchInput(searchInput)) {
            return searchList;
        }


        for (String key : multimap.keys()) {
            for (Task task : multimap.get(key)) {

                if (task.getDescription().contains(searchInput)) {
                    searchList.add(task);
                }
            }
        }
        return searchList;
    }

}
