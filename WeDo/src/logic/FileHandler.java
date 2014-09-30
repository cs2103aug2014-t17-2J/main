package logic;

import java.util.ArrayList;

public class FileHandler {
	
	private String fileName;
	
	
	public FileHandler() {
		
	}

    /**@param the name of the file to be written
     * @return the status of writing to file
     */
	public String writeToFile(String fName) {
		
		return null;
	}
	
	/**@param the name of the list to retrieve
     * @return the list specified
     */
	public ArrayList<Task> getList(String listName) {
			if(listName.equals("today")) {
				return getToday();
			}
			else if(listName.equals("tomorrow")) {
				return getTmr();
			}
			else if(listName.equals("upcoming")) {
				return getUpcoming();
			}
			else if(listName.equals("someday")) {
				return getSomeday();
			}
			else {
				return null;
			}
	}
	/**
     * @return the list dated today
     */
	public ArrayList<Task> getToday(){
		return null;
	}
	
	/**
     * @return the list dated tomorrow
     */
	public ArrayList<Task> getTmr(){
		return null;
		
	}
	
	/**@param
     * @return the list of future tasks
     */
	public ArrayList<Task> getUpcoming(){
		return null;
	}

	/**@param
     * @return floating tasks
     */
	public ArrayList<Task> getSomeday(){
		return null;
	}
}
