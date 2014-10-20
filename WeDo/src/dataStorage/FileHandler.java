package dataStorage;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import logic.utility.Task;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import definedEnumeration.Priority;


@SuppressWarnings("unchecked")

public class FileHandler {
	
	final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

	private String fileName;
	private final String ID = "ID";
	private final String DESCRPTION = "Description";
	private final String S_DATE = "Start Date";
	private final String E_DATE = "End Date";
	private final String S_TIME = "Start Time";
	private final String E_TIME = "End Time";
	private final String PRIORITY = "Priority";
	private final String STATUS = "Completed";
	
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
//		System.out.println(taskObj.toString());
		
		return taskObj;
	}
	
	private JSONObject taskToJSON(Task task) {
		
		JSONObject tmp = new JSONObject();
		
		tmp.put(ID, task.getID());
		tmp.put(DESCRPTION, task.getDescription());
		tmp.put(S_DATE, task.getStartDate().toString());
		tmp.put(E_DATE, task.getEndDate().toString());
		tmp.put(S_TIME, task.getStartTime().toString());
		tmp.put(E_TIME, task.getEndTime().toString());
		tmp.put(PRIORITY, task.getPriority().toString());
		tmp.put(STATUS, task.getCompleted());
		
		return tmp;
	}
	
	// @SuppressWarnings("unchecked")
	    public ArrayList<Task> read(String type) {
	    	
	    	ArrayList<Task> tasks = new ArrayList<Task>();
	        JSONParser parser = new JSONParser();
	 
	        try {
	 
	            Object obj = parser.parse(new FileReader(fileName));
	 
	            JSONObject jsonObject = (JSONObject) obj;
	            JSONArray taskLists = (JSONArray) jsonObject.get(type);
	            
	            for(Object tObj: taskLists) {
	            	JSONObject j = (JSONObject) tObj;
	            	Task t = jsonToTask(j);
	            	tasks.add(t);
//	            	System.out.println(t.toString());
	            }
	            
	            
//	            JSONObject test = (JSONObject) taskLists.get(0);
	            
//	            Task task = jsonToTask(test);
	            
//	            System.out.println(task.getID());
//	            System.out.println(task.getDescription());
//	            System.out.println(task.getStartDate());
//	            System.out.println(task.getEndDate());
//	            System.out.println(task.getStartTime());
//	            System.out.println(task.getEndTime());
//	            System.out.println(task.getPriority());
//	            System.out.println(task.getCompleted());

	            
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        System.out.println(tasks.toString());
	        return tasks;
	    }
	
	
	private Task jsonToTask(JSONObject jTask) {
		
		Task task = new Task();
		
		task.setID(Integer.parseInt(jTask.get(ID).toString()));
		task.setDescription(jTask.get(DESCRPTION).toString());
		task.setStartDate(LocalDate.parse(jTask.get(S_DATE).toString()));
		task.setEndDate(LocalDate.parse(jTask.get(E_DATE).toString()));
		task.setStartTime(LocalTime.parse(jTask.get(S_TIME).toString()));
		task.setEndTime(LocalTime.parse(jTask.get(E_TIME).toString()));
		task.setPriority(checkPriority(jTask));
		task.setCompleted((boolean) jTask.get(STATUS));
		
		
		return task;
		
	}
	
	private Priority checkPriority(JSONObject jTask) {
		
		
		String pri = jTask.get(PRIORITY).toString();
		
		if(pri.equalsIgnoreCase(Priority.PRIORITY_HIGH.toString())) {
			return Priority.PRIORITY_HIGH;
		} else if(pri.equalsIgnoreCase(Priority.PRIORITY_MEDIUM.toString())) {
			return Priority.PRIORITY_MEDIUM;
		} else if(pri.equalsIgnoreCase(Priority.PRIORITY_LOW.toString())) {
			return Priority.PRIORITY_LOW;
		} else {
			return Priority.PRIORITY_UNDEFINED;
		}
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
