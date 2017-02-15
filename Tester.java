import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Tester
{
	public static void main(String[] args) {

		ArrayList<Process> list1 = new ArrayList <Process>();
		ArrayList<Process> list2 = new ArrayList <Process>();
        ArrayList<Process> list3 = new ArrayList <Process>();
        ArrayList<Process> list4 = new ArrayList <Process>();
        ArrayList<Process> list5 = new ArrayList <Process>();


		//Creates processes and random values to them
		int numberOfRuns = 30;
		for(int i=0; i<numberOfRuns*5; i++)
		{
			Process n = new Process(i);
            if (i < numberOfRuns )
            {   list1.add(n); }
            else if (i < numberOfRuns * 2)
            {   list2.add(n); }
            else if (i < numberOfRuns *3)
            {   list3.add(n); }
            else if (i < numberOfRuns *4)
            {   list4.add(n); }
            else
            {   list5.add(n); }


		}

		SortLists(list1, list2, list3, list4, list5);

		// print out lists
		System.out.println("Pre scheduled process lists:");
		System.out.println("list1: \n" + printProcessList(list1) );


		int count = 1;
		float avgThroughput = 0;

		// FCFS results
		System.out.println("First Come First Served");        
		ArrayList<ArrayList<Process>> FCFSclone = deepCopy(list1, list2, list3, list4, list5);
		for (ArrayList<Process> list: FCFSclone)
		{
			System.out.println("*Processing list"+count+++"*");
			FCFS fcfs = new FCFS(list);
			System.out.println(printTimeline());
			System.out.println(printStringList(fcfs.getStringList()));;
			System.out.println(Statistics.CalculateStats(fcfs.getValues()));
			//            System.out.println(printProcessList(fcfs.getValues()));
			avgThroughput+=Statistics.ThroughputCalculationHelper(fcfs.getValues());
			System.out.println("Throughput: " + Statistics.ThroughputCalculationHelper(fcfs.getValues()));
		}
		System.out.println("Avgerage Throughput: "+avgThroughput/5+"\n");
		avgThroughput=0;
		System.out.println("\n\n");
	}

	/**
	 * Sorts lists by arrival time.
	 */
	@SuppressWarnings("unused")
	private static void SortLists(ArrayList<Process> list1,
			ArrayList<Process> list2, ArrayList<Process> list3,
			ArrayList<Process> list4, ArrayList<Process> list5) 
	{
		Comparator<Process> comparator = new Comparator<Process>()
		{
			public int compare(Process process1, Process process2) 
			{
				return new Float(process1.getArrivalTime()).compareTo(new Float( process2.getArrivalTime()));
			}
		};
		Collections.sort(list1, comparator);
		Collections.sort(list2, comparator);
		Collections.sort(list3, comparator);
		Collections.sort(list4, comparator);
		Collections.sort(list5, comparator);
	}


	public static String printProcessList(List<Process> processList) 
	{
		String s = "";
		for(Process process : processList) 
		{
			s+="[Name: " + String.format("%3s", process.getName()) 
			+ " --> Arrival Time: " + String.format("%10f", process.getArrivalTime()) 
			+ ", Run Time: " + String.format("%9f", process.getRunTime()) 
			+ ", Priority: " + process.getPriority() 
			+ ", Final runtime: " + String.format("%9f", process.getRunTime())
			+ ", Time started: " + String.format("%9d", process.getActualTime())
			+ ", Turn Around time: " + String.format("%9f", process.getSwitchTime())
			+ ", Waiting time: " + String.format("%9f", process.getWait())
			+ ", Responce time: " + String.format("%9f", process.getTimeValue())
			+ "]\n";
		}
		return s;
	}

	/**
	 * Concatenates Processes log using list separating different strings with ]s.
	 * @param stringList the string list
	 * @return a String of the history
	 */
	public static String printStringList(List<String> stringList) 
	{
		String previousString = stringList.get(0);

		String output = "[";
		for(String string : stringList) 
		{
			if(string.equals(previousString)) 
			{
				output += String.format("%3s", string) + "|";
			}
			else
			{
				output = output.substring(0, output.length() - 1); // remove last |
				output += "]" + String.format("%3s", string) + "|";
				previousString = string;
			}
		}
		output = output.substring(0, output.length() - 1); // remove last |
		output += "]";
		return output;
	}

	/**
	 * Prints out a time line that can match the process log printout.
	 * @param stringList the string list
	 * @return a String time line of processes
	 */
	public static String printTimeline() 
	{

		String output = "[";
		for(int i = 0; i<100; i++) {
			output += String.format("%3d",i) + "|";
		}
		output = output.substring(0, output.length() - 1); // remove last |
		output += "]";
		return output;
	}

	/**
	 * Takes 5 lists and makes clones of them
	 * @param list1, list2, list3, list4, list5
	 * @return clone is an arraylist of 5 arraylist of processes
	 */
	public static ArrayList<ArrayList<Process>> deepCopy( ArrayList<Process> list1, ArrayList<Process> list2,
			ArrayList<Process> list3, ArrayList<Process> list4, ArrayList<Process> list5) 
	{
		ArrayList<Process> list1clone = new ArrayList<Process>();
		ArrayList<Process> list2clone = new ArrayList<Process>();
		ArrayList<Process> list3clone = new ArrayList<Process>();
		ArrayList<Process> list4clone = new ArrayList<Process>();
		ArrayList<Process> list5clone = new ArrayList<Process>();
		ArrayList<ArrayList<Process>> clone = new ArrayList<ArrayList<Process>>();

		for(Process p: list1)
		{  
			list1clone.add(p.clone()); 
		}
		clone.add(list1clone);

		for(Process p: list2)
		{  
			list2clone.add(p.clone());  
		}
		clone.add(list2clone);

		for(Process p: list3)
		{   
			list3clone.add(p.clone());  
		}
		clone.add(list3clone);

		for(Process p: list4)
		{  
			list4clone.add(p.clone());  
		}
		clone.add(list4clone);

		for(Process p: list5)
		{   
			list5clone.add(p.clone());   
		}
		clone.add(list5clone);

		return clone;
	}
}
