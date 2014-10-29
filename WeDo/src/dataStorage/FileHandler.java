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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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
	private final String DEADLINE = "deadLine";
	private final String TIMED = "timed";
	private final String FLOATING = "floating";


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
	 * @param three
	 *            lists to be written to the file
	 * @return the status of writing to file
	 */
	public String writeToFile(ArrayList<Task> deadLine, ArrayList<Task> timed,
			ArrayList<Task> floating) {

		JSONObject tasks = toJSON(deadLine, timed, floating);

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
	
	public String writeToFile(ArrayList<Task> tasks) {
	
		JSONObject jTasks = toJSON("tasks",tasks);

		try {
			FileWriter fstream = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(fstream);
			
			String[] sTasks = jTasks.toString().split(",");
			
			for(String s: sTasks) {
				bw.write(s);
				if(s.charAt(s.length()-1) == '}') {
					bw.newLine();
				}
				bw.newLine();
			}
			
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

	public JSONObject toJSON(ArrayList<Task> deadLine, ArrayList<Task> timed,
			ArrayList<Task> floating) {
		JSONObject all = new JSONObject();
		JSONArray lists = new JSONArray();

		lists.add(toJSON("deadLine", deadLine));
		lists.add(toJSON("timed", timed));
		lists.add(toJSON("floating", floating));
		all.put("all tasks", lists);

		return all;
	}

	private JSONObject toJSON(String type, ArrayList<Task> tasks) {

		JSONObject taskObj = new JSONObject();
		JSONArray allTask = new JSONArray();

		for (Task t : tasks) {
			allTask.add(taskToJSON(t));
		}

		taskObj.put(type, allTask);
		// System.out.println(taskObj.toString());

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
	
	public void resetID() {
		Task task = new Task();
		task.setID(0);
	}
	
	
	public Multimap<LocalDate,Task> getAllTasks(){
		
		Multimap<LocalDate,Task> tmp;
		tmp = ArrayListMultimap.create();
		
		resetID();
		
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray taskLists = (JSONArray) jsonObject.get("tasks");

			for (Object tObj : taskLists) {
				JSONObject j = (JSONObject) tObj;
				Task t = jsonToTask(j);
				tmp.put(t.getEndDate(), t);
				
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tmp;
		
		
	}

	public ArrayList<Task> getList(String type) {
		
		ArrayList<Task> tasks = new ArrayList<Task>();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray taskLists = (JSONArray) jsonObject.get("tasks");

			for (Object tObj : taskLists) {
				JSONObject j = (JSONObject) tObj;
				Task t = jsonToTask(j);
				if(determineTaskType(t).equalsIgnoreCase(type)) {
					tasks.add(t);

				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(tasks.toString());
		return tasks;

	}

	private String determineTaskType(Task task) {
		if (task.getEndDate().equals(LocalDate.MAX)
				&& task.getStartDate().equals(LocalDate.MAX)) {
			return FLOATING;
		} else if (!task.getStartDate().equals(LocalDate.MAX)
				&& !task.getEndDate().equals(LocalTime.MAX)) {
			return TIMED;
		} else {
			return DEADLINE;
		}
	}
	
	// @SuppressWarnings("unchecked")
	public ArrayList<Task> read(String type) {

		ArrayList<Task> tasks = new ArrayList<Task>();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(fileName));

			JSONObject jsonObject = (JSONObject) obj;
			JSONArray taskLists = (JSONArray) jsonObject.get(type);

			for (Object tObj : taskLists) {
				JSONObject j = (JSONObject) tObj;
				Task t = jsonToTask(j);
				tasks.add(t);
				System.out.println(t.toString());
			}

			// JSONObject test = (JSONObject) taskLists.get(0);

			// Task task = jsonToTask(test);

			// System.out.println(task.getID());
			// System.out.println(task.getDescription());
			// System.out.println(task.getStartDate());
			// System.out.println(task.getEndDate());
			// System.out.println(task.getStartTime());
			// System.out.println(task.getEndTime());
			// System.out.println(task.getPriority());
			// System.out.println(task.getCompleted());

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

		if (pri.equalsIgnoreCase(Priority.PRIORITY_HIGH.toString())) {
			return Priority.PRIORITY_HIGH;
		} else if (pri.equalsIgnoreCase(Priority.PRIORITY_MEDIUM.toString())) {
			return Priority.PRIORITY_MEDIUM;
		} else if (pri.equalsIgnoreCase(Priority.PRIORITY_LOW.toString())) {
			return Priority.PRIORITY_LOW;
		} else {
			return Priority.PRIORITY_UNDEFINED;
		}
	}

}
