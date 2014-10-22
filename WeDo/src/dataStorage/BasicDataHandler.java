package dataStorage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Observer;

import logic.command.UndoHandler;
import logic.utility.Task;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import edu.emory.mathcs.backport.java.util.Collections;

public class BasicDataHandler implements DataHandler {

	private final String TODAY = "today";
	private final String TOMORROW = "tomorrow";
	private final String UPCOMING = "upcoming";
	private final String SOMEDAY = "someday";
	private final String DEADLINE = "deadLine";
	private final String TIMED = "timed";
	private final String FLOATING = "floating";

	private String currentList;

	UndoHandler undoHandler = new UndoHandler();
	FileHandler fileHandler;

	ObservableList<Task> observableList;
	Multimap<String, Task> mainList;
	Multimap<LocalDate, Task> deadLineList, timedList;
	ArrayList<Task> floatingList;

	public BasicDataHandler() {
		fileHandler = new FileHandler();
		populateLists();
		observableList = new ObservableList<Task>(new ArrayList<Task>(getToday()));
		// currentList = TODAY;
		fileHandler.writeLog(LocalTime.now() + " : DataHandler initialized");

	}

	public ArrayList<Task> getToday() {
		ArrayList<Task> today = new ArrayList<Task>(deadLineList.get(LocalDate.now()));
		today.addAll(timedList.get(LocalDate.now()));
		
		return today;
	}

	private Task todayTask() {
		Task task = new Task();
		task.setEndDate(LocalDate.now());
		return task;
	}

	public BasicDataHandler(ObservableList<Task> observableList) {
		fileHandler = new FileHandler();
		populateLists();
		this.observableList = observableList;
		observableList.replaceList(new ArrayList<Task>(mainList.get(TODAY)));
		currentList = TODAY;
		fileHandler.writeLog(LocalTime.now() + " : DataHandler initialized");
	}

	public ObservableList<Task> getObservableList() {

		fileHandler.writeLog(LocalTime.now() + " : ObservableList retrieved!");
		return observableList;
	}

	public void addObserver(Observer observer) {
		observableList.addObserver(observer);
		fileHandler.writeLog(LocalTime.now() + " : Added observer "
				+ observer.toString());
	}

	/**
	 * This function add all the lists into a Multimap according to list type
	 * 
	 * @return whether the operation is successful.
	 */
	public boolean populateLists() {

		mainList = ArrayListMultimap.create();
		deadLineList = ArrayListMultimap.create();
		timedList = ArrayListMultimap.create();
		floatingList = new ArrayList<Task>();
		//
		deadLineList.putAll(addToMultimap(fileHandler.getList(DEADLINE)));
		timedList = addToMultimap(fileHandler.getList(TIMED));
		floatingList = fileHandler.getList(FLOATING);
		// addToMultimap(TODAY, fileHandler.getList(TODAY));
		// addToMultimap(TOMORROW, fileHandler.getList(TOMORROW));
		// addToMultimap(UPCOMING, fileHandler.getList(UPCOMING));
		// addToMultimap(SOMEDAY, fileHandler.getList(SOMEDAY));

		return false;
	}

	/**
	 * This function put the Arraylist of tasks into a specific key of the
	 * Multimap
	 */
	public Multimap<LocalDate, Task> addToMultimap(ArrayList<Task> tasks) {

		Multimap<LocalDate, Task> tmp = ArrayListMultimap.create();

		for (Task t : tasks) {
			tmp.put(t.getEndDate(), t);
		}

		return tmp;

	}

	/**
	 * This function retrieve a specific list from main list
	 * 
	 * @param list
	 *            name to be displayed
	 * @return list of the tasks to be displayed
	 */
	public ArrayList<Task> getListFromMain(String name) {
		ArrayList<Task> tmp = new ArrayList<Task>(mainList.get(name));

		return tmp;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#addTask(logic.Task)
	 */
	@Override
	public boolean addTask(Task task) {

		String taskType = determineTaskType(task);

		if (taskType.equals(DEADLINE)) {
			deadLineList.put(task.getEndDate(), task);

		} else if (taskType.equals(TIMED)) {
			timedList.put(task.getEndDate(), task);
		} else {
			floatingList.add(task);
		}

		if (onDisplay(task) == true) {
			observableList.add(task);
		}
		// observableList.add(task);
		mainList.put(determineDate(task), task);
		save();
		System.out.println(task.getID() + " is added");

		// fileHandler.read("deadLine");
		fileHandler.writeLog(LocalTime.now() + " : Added Task " + task.getID());

		return true;
	}

	public String save() {

		fileHandler.clear();
		// fileHandler.writeToFile(new
		// ArrayList<Task>(deadLineList.values()),new
		// ArrayList<Task>(timedList.values()),floatingList);
		ArrayList<Task> tmp = new ArrayList<Task>(deadLineList.values());
		tmp.addAll(timedList.values());
		tmp.addAll(floatingList);
		fileHandler.writeToFile(tmp);
		// fileHandler.writeToFile(TIMED, new
		// ArrayList<Task>(timedList.values()));
		// fileHandler.writeToFile(FLOATING,floatingList);

		// fileHandler.writeToFile("deadLine",
		// new ArrayList<Task>(mainList.values()));
		fileHandler.writeLog(LocalTime.now() + " : Saved!");
		return null;
	}

	/**
	 * Check if the task should be added to or removed from both main list and
	 * observable list
	 * 
	 * @param task
	 * @return whether the task should be on display
	 */
	public boolean onDisplay(Task task) {

		if (determineDate(task).equalsIgnoreCase(currentList)) {
			return true;
		} else {
			return false;
		}
	}

	private String determineTaskType(Task task) {
		if (task.getEndDate() == LocalDate.MAX
				&& task.getStartDate() == LocalDate.MAX) {
			return FLOATING;
		} else if (task.getEndTime() == LocalTime.MAX
				&& task.getStartTime() == LocalTime.MAX
				&& task.getStartDate() == LocalDate.MAX
				&& task.getEndDate() != LocalDate.MAX) {
			return DEADLINE;
		} else {
			return TIMED;
		}
	}

	private int daysFromToday(LocalDate date) {
		LocalDate today = LocalDate.now();
		int numDays;

		if (date == LocalDate.MAX) {
			return -1; // someday
		} else {
			for (numDays = 0; !today.equals(date); numDays++) {
				today = today.plusDays(1);
			}

			System.out.println("dayss " + numDays);
			return numDays;
		}

	}

	/**
	 * This function determines the date of the task to know which key of the
	 * map it should be added.
	 * 
	 * @param task
	 * @return
	 */
	public String determineDate(Task task) {

		switch (daysFromToday(task.getEndDate())) {

		case -1:
			return SOMEDAY;
		case 0:
			return TODAY;
		case 1:
			return TOMORROW;
		default:
			return UPCOMING;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#clearTask(java.time.LocalDate,
	 * java.time.LocalDate)
	 */
	@Override
	public boolean clearTask(LocalDate starDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#getDisplayedTasks()
	 */
	@Override
	public ArrayList<Task> getDisplayedTasks(LocalDate startDate,
			LocalDate endDate) {
		return observableList.getList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#setDisplayedTasks(java.util.ArrayList)
	 */
	@Override
	public void setDisplayedTasks(ArrayList<Task> displayedTask) {
		observableList.replaceList(displayedTask);
		fileHandler.writeLog(LocalTime.now() + " : changed displayed list ");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.BasicDataHandler#canRemove(int)
	 */
	public boolean indexValid(int index) {

		if (index >= (observableList.getList().size()) || index < 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
     * 
     */
	private void clearDisplayedList() {
		observableList.clearList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.BasicDataHandler#getList(java.time.LocalDate,
	 * java.time.LocalDate)
	 */
	public ArrayList<Task> getList(LocalDate starDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#remove(int)
	 */
	public boolean removeTask(int index) {
		if (indexValid(index)) {

			fileHandler.writeLog(LocalTime.now() + " : deleted "
					+ observableList.get(index));

			System.out.println("deleted " + observableList.get(index));
			mainList.remove(determineDate(getTask(index)), getTask(index));
			observableList.remove(index);
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#editTask()
	 */
	@Override
	public boolean editTask(Task source, Task replacement) {

		fileHandler.writeLog(LocalTime.now() + " : edited " + source.getID());

		removeTask(source);
		addTask(replacement);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#getTask(int)
	 */
	@Override
	public Task getTask(int index) {

		return observableList.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#remove(logic.Task)
	 */
	@Override
	public boolean removeTask(Task task) {

		observableList.remove(task);
		mainList.remove(determineDate(task), task);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#getMainList()
	 */
	@Override
	public Multimap<String, Task> getMainList() {
		// TODO Auto-generated method stub
		return mainList;
	}

	public ArrayList<Task> getTasksRange(LocalDate start, LocalDate end) {

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dataStorage.DataHandler#view(java.lang.String)
	 */
	@Override
	public void view(Task task) {

		System.out.println(task.getStartDate().toString());
		System.out.println(task.getEndDate().toString());
		System.out.println(task.getDescription());
		ArrayList<Task> tmp = new ArrayList<Task>();
		String type = determineTaskType(task);

		if (task.getDescription().equals(SOMEDAY)) {
			observableList.replaceList(floatingList);

		} else if (type.equals(DEADLINE)) {

			tmp.addAll(deadLineList.get(task.getEndDate()));
			tmp.addAll(timedList.get(task.getEndDate()));
			observableList.replaceList(tmp);

		} else {
			tmp.addAll(new ArrayList<Task>(deadLineList.values()));
			tmp.addAll(new ArrayList<Task>(timedList.values()));
			observableList.replaceList(tmp);
		}

		// TODO Auto-generated method stub
		// if (task.getEndDate() != LocalDate.MAX) {
		// observableList.replaceList(new ArrayList<Task>(mainList
		// .get(determineDate(task))));
		// currentList = this.determineDate(task);
		// } else {
		// observableList.replaceList(new ArrayList<Task>(mainList.get(task
		// .getDescription())));
		// currentList = task.getDescription();
		// }

	}

}