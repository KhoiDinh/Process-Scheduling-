import java.util.ArrayList;

public class Statistics 
{

	private static float AverageSwitch(ArrayList<Process> list) 
	{
		float turnTime = 0.0f;
		for(Process p: list)
		{	
			turnTime += p.getSwitchTime();
		}
		return (turnTime / list.size());
	}


	private static float AverageWaitTime(ArrayList<Process> list) 
	{
		float waiting = 0.0f;
		for(Process p: list)
		{	waiting += p.getWait();	}
		return (waiting / list.size());
	}


	private static float AverageResponseTime(ArrayList<Process> list) 
	{
		float response = 0.0f;
		for(Process p: list)
		{
			response += p.getTimeValue();
		}
		return (response / list.size());
	}


	public static float ThroughputCalculationHelper(ArrayList<Process> a)
	{
		float count = 0;
		for(Process p: a)
		{
			if(p.getEndTime()<100)
			{
				count++;
			}
		}
		return count;
	}


	public static float ThroughputAverageCalculation(ArrayList<ArrayList<Process>> listOfLists)
	{
		float count = 0;
		for(ArrayList<Process> a: listOfLists)
		{
			count = ThroughputCalculationHelper(a);
		}
		return count/listOfLists.size();
	}

	public static String CalculateStats(ArrayList<Process> list)
	{
		return "Average Turnaround: " + AverageSwitch(list) +
				"\nAverage Waiting: " + AverageWaitTime(list) +
				"\nAverage Response: " + AverageResponseTime(list);
	}
}
