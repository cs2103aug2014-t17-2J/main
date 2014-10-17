package dataStorage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logic.utility.Task;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@SuppressWarnings("unchecked")

public class FileHandler {

	private String fileName;
	private final String ID = "ID";
	private final String DESCRPTION = "Description";
	private final String S_DATE = "Start Date";
	private final String E_DATE = "End Date";
	private final String S_TIME = "Start Time";
	private final String E_TIME = "End Time";
	private final String PRIORITY = "Priority";
	private final String STATUS = "Status";
	
	public FileHandler() {
		
		fileName = "WeDo.txt";
		createFile();
			
	}
	
	
	public void createFile() {

		try {
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fstream);

			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	
	}
	
	public void clear() {

		try {
			FileWriter fstream = new FileWriter(fileName, false);
			BufferedWriter bw = new BufferedWriter(fstream);
			
			bw.write("");
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * @param three lists to be written to the file
	 * @return the status of writing to file
	 */
	public String writeToFile(String type,ArrayList<Task> taskList) {
		
		JSONObject tasks = toJSON(type,taskList);
		
		try {
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fstream);
			
			bw.write(tasks.toString());
			bw.newLine();
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	
		
		return null;
	}
	
	public String writeLog(String log) {
		
		
		try {
			FileWriter fstream = new FileWriter("LogFile.txt", true);
			BufferedWriter bw = new BufferedWriter(fstream);
			
			bw.write(log);
			bw.newLine();
			bw.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	
		
		return null;
	}
	
	
	public JSONObject toJSON(String type,ArrayList<Task> tasks) {
		
		JSONObject taskObj = new JSONObject();
		JSONArray allTask = new JSONArray();
		
		for(Task t:tasks) {
			allTask.add(taskToJSON(t));
		}
		
		taskObj.put(type, allTask);
		System.out.println(taskObj.toString());
		
		return taskObj;
	}
	
	private JSONObject taskToJSON(Task task) {
		
		JSONObject tmp = new JSONObject();
		
		tmp.put(ID, task.getID());
		tmp.put(DESCRPTION, task.getDescription());
		tmp.put(S_DATE, task.getStarDate());
		tmp.put(E_DATE, task.getEndDate());
		tmp.put(S_TIME, task.getStartTime());
		tmp.put(E_TIME, task.getEndTime());
		tmp.put(PRIORITY, task.getPriority());
		tmp.put(STATUS, task.isValid());
		
		return tmp;
	}

	/**
	 * @param the
	 *            name of the list to retrieve
	 * @return the list specified
	 */
	public ArrayList<Task> getList(String listName) {
		if (listName.equals("today")) {
			return getToday();
		} else if (listName.equals("tomorrow")) {
			return getTmr();
		} else if (listName.equals("upcoming")) {
			return getUpcoming();
		} else if (listName.equals("someday")) {
			return getSomeday();
		} else {
			return null;
		}
	}

	/**
	 * @return the list dated today
	 */
	public ArrayList<Task> getToday() {
		return null;
	}

	/**
	 * @return the list dated tomorrow
	 */
	public ArrayList<Task> getTmr() {
		return null;

	}

	/**
	 * @param
	 * @return the list of future tasks
	 */
	public ArrayList<Task> getUpcoming() {
		return null;
	}

	/**
	 * @param
	 * @return floating tasks
	 */
	public ArrayList<Task> getSomeday() {
		return null;
	}
}
