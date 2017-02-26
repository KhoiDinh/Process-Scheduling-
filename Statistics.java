import java.util.ArrayList;

public class Statistics 
{

	private static float AverageSwitchTime(ArrayList<Process> list) 
	{
		float switchTime = 0.0f;
		for(int i=0; i <list.size(); i++)//for(Process p:list)
		{	
			Process chose = list.get(i);
			switchTime += chose.getSwitchTime();
		}
		return (switchTime / list.size());
	}


	private static float AverageWaitTime(ArrayList<Process> list) 
	{
		float waitTime = 0.0f;
		for(int i=0; i <list.size(); i++)//for(Process p:list)
		{	
			Process chose = list.get(i);
			waitTime += chose.getWait();	
		}
		return (waitTime / list.size());
	}


	private static float AverageResponseTime(ArrayList<Process> list) 
	{
		float responseTime = 0.0f;
		for(int i=0; i <list.size(); i++)//for(Process p:list)
		{	
			Process chose = list.get(i);
			responseTime += chose.getTimeValue();
		}
		return (responseTime / list.size());
	}


	public static float ThroughputCalculationHelper(ArrayList<Process> list)
	{
		float count = 0;
		for(int i=0; i <list.size(); i++)//for(Process p:list)
		{
			Process chose = list.get(i);
			if(chose.getEndTime()<100)
			{
				count++;
			}
		}
		return count;
	}


	public static float ThroughputAverageCalculation(ArrayList<ArrayList<Process>> processLists)
	{
		float count = 0;
		for(int i=0; i<processLists.size(); i++)//(ArrayList<Process> a: processLists)
		{
			ArrayList<Process>chosenList = processLists.get(i);
			count = ThroughputCalculationHelper(chosenList);
		}
		return count/processLists.size();
	}

	public static String getStatistics(ArrayList<Process> list)
	{
		return "Average Turnaround: " + AverageSwitchTime(list) +
				"\nAverage Waiting: " + AverageWaitTime(list) +
				"\nAverage Response: " + AverageResponseTime(list);
	}
}
