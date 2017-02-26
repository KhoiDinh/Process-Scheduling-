import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Tester
{
	public static void main(String[] args) 
	{
		ArrayList<String>ThroughputAverages = new ArrayList<String>();
		ArrayList<Process> list1 = new ArrayList <Process>();
		ArrayList<Process> list2 = new ArrayList <Process>();
		ArrayList<Process> list3 = new ArrayList <Process>();
		ArrayList<Process> list4 = new ArrayList <Process>();
		ArrayList<Process> list5 = new ArrayList <Process>();

		int processCount = 1;
		float avgThroughput = 0;

		int numberOfRuns = 30;
		for(int i=0; i<numberOfRuns*5; i++)
		{
			Process n = new Process(i);
			if (i < numberOfRuns )
			{  
				list1.add(n); 
			}
			else if (i < numberOfRuns * 2)
			{  
				list2.add(n); 
			}
			else if (i < numberOfRuns *3)
			{  
				list3.add(n);
			}
			else if (i < numberOfRuns *4)
			{  
				list4.add(n); 
			}
			else
			{   
				list5.add(n); 
			}
		}

		SortLists(list1, list2, list3, list4, list5);

		System.out.println("Intial process lists:");
		System.out.println("List1: ");
		System.out.println(getProcessList(list1));
		System.out.println("List2: ");
		System.out.println(getProcessList(list2));;
		System.out.println("List3: ");
		System.out.println(getProcessList(list3));;
		System.out.println("List4: ");
		System.out.println(getProcessList(list4));;
		System.out.println("List5: ");
		System.out.println(getProcessList(list5));;
		System.out.println();

		//FCFS
		System.out.println("FCFS (First Come First Served)");        
		ArrayList<ArrayList<Process>> FCFScopy = copyProcesses(list1, list2, list3, list4, list5);
		for (int i=0; i< FCFScopy.size();i++)//(ArrayList<Process> list: FCFScopy)
		{
			ArrayList<Process> list = FCFScopy.get(i);
			System.out.println("PROCESSING List"+ processCount );
			
			processCount++;
			
			FCFS fcfs = new FCFS(list);
			
			System.out.println(printTemplate());
			System.out.println(printFilledTemplate(fcfs.getStringList()));;
			System.out.println(Statistics.getStatistics(fcfs.getValues()));
			avgThroughput+=Statistics.ThroughputCalculationHelper(fcfs.getValues());
			System.out.println("Throughput: " + Statistics.ThroughputCalculationHelper(fcfs.getValues()));
			System.out.println();
		}
		//System.out.println("Average Throughput: "+avgThroughput/5+"\n");
		ThroughputAverages.add(avgThroughput/5 +"");
		avgThroughput=0;
		System.out.println("\n\n");
		
		getThroughputAverages(ThroughputAverages);//goes at the very end
	}

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


	public static String getProcessList(List<Process> processList) 
	{
		/*
		 * value += "Name: " + String.format("%3s", process.getName())+"\t" 
			+ "Arrival Time: " + String.format("%10f", process.getArrivalTime()) +"\t" 
			+ "Run Time: " + String.format("%9f", process.getRunTime()) +"\t" 
			+ "Priority: " + process.getPriority() +"\t" 
			+ "Final runtime: " + String.format("%9f", process.getRunTime())+"\t" 
			+ "Time started: " + String.format("%3d", process.getActualTime())+"\t" 
			+ "Turn Around time: " + String.format("%9f", process.getSwitchTime())+"\t" 
			+ "Waiting time: " + String.format("%10f", process.getWait())+"\t" 
			+ "Response time: " + String.format("%10f", process.getTimeValue())
			+ "\n";
		 */
		String value = "";
		for(int i=0; i<processList.size();i++)//(Process process : processList)
		{
			Process process = processList.get(i);
			
			value += "Name/ Number Value: " + String.format("%3s", process.getName())+"\t" 
			+ "Arrival Time: " + String.format("%10f", process.getArrivalTime()) +"\t" 
			+ "Run Time: " + String.format("%9f", process.getRunTime()) +"\t" 
			+ "Priority: " + process.getPriority()
			+ "\n";
		}
		return value;
	}


	public static String printFilledTemplate(List<String> stringList) 
	{
		String previousString = stringList.get(0);

		String output = "{";
		for(int i=0; i <stringList.size();i++)//(String string : stringList) 
		{
			String string = stringList.get(i);
			if(string.equals(previousString)) 
			{
				output +=  string +"\t";
			}
			else
			{
				output = output.substring(0, output.length() - 1); 
				output += "}\t" +  string+"\t";
				previousString = string;
			}
		}
		output = output.substring(0, output.length() - 1);
		output += "}";
		return output;
	}


	public static String printTemplate() 
	{
		String output = "{";
		for(int i = 0; i<100; i++) 
		{
			output += i+"\t";
		}
		output = output.substring(0, output.length() - 1);
		output += "}";
		return output;
	}

	
	public static ArrayList<ArrayList<Process>> copyProcesses( ArrayList<Process> list1, ArrayList<Process> list2,
			ArrayList<Process> list3, ArrayList<Process> list4, ArrayList<Process> list5) 
	{
		ArrayList<Process> clone1 = new ArrayList<Process>();
		ArrayList<Process> clone2 = new ArrayList<Process>();
		ArrayList<Process> clone3 = new ArrayList<Process>();
		ArrayList<Process> clone4 = new ArrayList<Process>();
		ArrayList<Process> clone5 = new ArrayList<Process>();
		ArrayList<ArrayList<Process>> clone = new ArrayList<ArrayList<Process>>();

		for(Process p: list1)
		{  
			clone1.add(p.clone()); 
		}
		clone.add(clone1);

		for(Process p: list2)
		{  
			clone2.add(p.clone());  
		}
		clone.add(clone2);

		for(Process p: list3)
		{   
			clone3.add(p.clone());  
		}
		clone.add(clone3);

		for(Process p: list4)
		{  
			clone4.add(p.clone());  
		}
		clone.add(clone4);

		for(Process p: list5)
		{   
			clone5.add(p.clone());   
		}
		clone.add(clone5);

		return clone;
	}
	
	
	 public static void getThroughputAverages(ArrayList<String> ThroughputAverages)
	    {
	    	System.out.println
	    	("FCFS Throughput Average: " + ThroughputAverages.get(0) + "\n");
	    	
	    }
}
