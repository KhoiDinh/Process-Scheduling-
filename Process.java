import java.util.*;

public class Process {
	private float arrivalTime;
	private float givenExecutionTime;
	private float executionTimeRemaining;
	private float startExecutionTime;
	private float endTime;
	private float waitTime;
	private int priority;
	private Random random;
	private String name;

	private static int MAX_WAIT_TIME = 5;
	private static int HIGHEST_PRIORITY = 1;

	public Process() {
		
	}
	
	private float generateFloatTime(float min, float max) {
		return random.nextFloat() * (max - min) + min;
	}
	
	// Turnaround time – time from arrival to completion.
	// Response time – time from arrival to the first instance of output.
	// Execution time – time from start of execution
	// Throughput is the number of jobs per hour that the system completes

	public Process(String name, int seed) {
		this.name = name;
		random = new Random(seed);
		arrivalTime = generateFloatTime(0, 99);
		givenExecutionTime = generateFloatTime(0.1f, 10);
		executionTimeRemaining = givenExecutionTime;
		priority = random.nextInt(4) + 1;
		
		waitTime = 0;
		endTime = -1;
		startExecutionTime = -1;
	}

	public static void sortList(ArrayList<Process> list) {
		Comparator<Process> comparator = new Comparator<Process>() {
			public int compare(Process process1, Process process2) {
				return new Float(process1.arrivalTime).compareTo(new Float(process2.arrivalTime));
			}
		};
		Collections.sort(list, comparator);
	}
	
	public void setArrivalTime(float arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public void setGivenExecutionTime(float givenExecutionTime) {
		this.givenExecutionTime = givenExecutionTime;
	}

	public void setExecutionTimeRemaining(float executionTimeRemaining) {
		this.executionTimeRemaining = executionTimeRemaining;
	}

	public void setStartExecutionTime(float startExecutionTime) {
		this.startExecutionTime = startExecutionTime;
	}

	public void setEndTime(float endTime) {
		this.endTime = endTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public float getArrivalTime() {
		return arrivalTime;
	}
	
	public float getGivenExecutionTime() {
		return givenExecutionTime;
	}
	
	public float getExecutionTimeRemaining() {
		return executionTimeRemaining;
	}
	
	public float getStartExecutionTime() {
		return startExecutionTime;
	}
	
	public float getEndTime() {
		return endTime;
	}
	
	public float getWaitTime() {
		return waitTime;
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

	public void decrementWaitTime() {
		waitTime--;
	}

	public void incrementWaitTime() {
		waitTime++;
		/*
		if (waitTime == MAX_WAIT_TIME) {
			waitTime = 0;
			increasePriority();
		}
		*/
	}

	public void increasePriority() {
		if (priority > HIGHEST_PRIORITY) {
			priority--;
		}
	}
	
	public Process clone()
	{
		Process process = new Process();
		process.setArrivalTime(arrivalTime);
		process.setGivenExecutionTime(givenExecutionTime);
		process.setName(name);
		process.setPriority(priority);
		return process;
	}
	
}
