import java.util.*;

public class Process {
	private int arrivalTime;
	private int givenExecutionTime;
	private int executionTimeRemaining;
	private int startExecutionTime;
	private int endTime;
	private int priority;
	private Random random;
	private String name;

	private static int MAX_WAIT_TIME = 5;
	private static int HIGHEST_PRIORITY = 1;

	public Process(int seed) {
		this.name = "";
		random = new Random(seed);
		arrivalTime = generateIntTime(99);
		givenExecutionTime = generateIntTime(10) + 1;
		executionTimeRemaining = givenExecutionTime;
		priority = random.nextInt(4) + 1;

		endTime = -1;
		startExecutionTime = -1;
	}

	private int generateIntTime1(int min, int max) {
		return random.nextInt() * (max - min) + min;
	}
	
	private int generateIntTime(int range) {
		return random.nextInt(range);
	}

	// Turnaround time – time from arrival to completion.
	// Response time – time from arrival to the first instance of output.
	// Execution time – time from start of execution
	// Throughput is the number of jobs per hour that the system completes

	public Process(String name, int seed) {
		this.name = name;
		random = new Random(seed);
		arrivalTime = generateIntTime(99);
		givenExecutionTime = generateIntTime(10) + 1;
		executionTimeRemaining = givenExecutionTime;
		priority = random.nextInt(4) + 1;

		endTime = -1;
		startExecutionTime = -1;
	}

	public static void sortList(ArrayList<Process> list) {
		Comparator<Process> comparator = new Comparator<Process>() {
			public int compare(Process process1, Process process2) {
				if (process1.arrivalTime < process2.arrivalTime) {
					return -1;
				} else if (process1.arrivalTime < process2.arrivalTime) {
					return 1;
				} else {
					return 0;
				}
			}
		};
		Collections.sort(list, comparator);
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setGivenExecutionTime(int givenExecutionTime) {
		this.givenExecutionTime = givenExecutionTime;
	}

	public void setExecutionTimeRemaining(int executionTimeRemaining) {
		this.executionTimeRemaining = executionTimeRemaining;
	}

	public void setStartExecutionTime(int startExecutionTime) {
		this.startExecutionTime = startExecutionTime;
	}

	public void setEndTime(int endTime) {
		this.endTime = endTime;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getGivenExecutionTime() {
		return givenExecutionTime;
	}

	public int getExecutionTimeRemaining() {
		return executionTimeRemaining;
	}

	public int getStartExecutionTime() {
		return startExecutionTime;
	}

	public int getEndTime() {
		return endTime;
	}

	public int getPriority() {
		return priority;
	}

	public String getName() {
		return name;
	}

	public void decrementExecutionTimeRemaining() {
		executionTimeRemaining--;
	}

	public void increasePriority() {
		if (priority > HIGHEST_PRIORITY) {
			priority--;
		}
	}
	
	public int calculateTurnaroundTime() {
		return endTime - arrivalTime;
	}
	
	public int calculateWaitTime() {
		return calculateTurnaroundTime() - givenExecutionTime;
	}
	
	public int calculateResponseTime() {
		return startExecutionTime - arrivalTime;
	}

	/*
	public Process clone() {
		Process process = new Process();
		process.setArrivalTime(arrivalTime);
		process.setGivenExecutionTime(givenExecutionTime);
		process.setName(name);
		process.setPriority(priority);
		return process;
	}
	*/
}
