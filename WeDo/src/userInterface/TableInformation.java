package userInterface;

public class TableInformation {

	protected String task, description, startDate, endDate, startTime, endTime,
			priority;
	protected Boolean check;

	public TableInformation() {
		task = "";
		description = "";
		startDate = "";
		endDate = "";
		startTime = "";
		endTime = "";
		priority = "";
		check = false;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public Boolean getCheck(){
		return false;
	}
	
	public void setCheck(Boolean check){
		this.check = false;
	}
}
