import java.util.Random;

public class Process 
{
	private float arrivalTime;  
	private float expectedTotalRunTime;
	private int priority;
	private int endTime;
	private Random random;
	private int actualTime;
	private String name;
	
	private int quantaWait;
	private float quantaTime;
	
	private static int QUANTAWAIT = 5;
	private static int PRIORITY = 5;
	
	
	public Process()
	{
		name = "none";
		
		arrivalTime = 200;
		expectedTotalRunTime =1000;
		priority = -1;
		quantaWait =0;
		actualTime = -1;
		endTime = -1;
		quantaTime = 0.0f;
	}
	public Process(int number)
	{
		name = number +"";
		Random random = new Random();
		arrivalTime = random.nextFloat() * 99.0f;
		expectedTotalRunTime = random.nextFloat() * 10.0f;
		priority = random.nextInt(4) + 1;
		
		quantaWait =0;
		quantaTime = 0.0f;
		endTime= -1;
		actualTime =-1;
	}
	
	public int getActualTime()
	{
		return actualTime;
	}
	
	public float getArrivalTime()
	{
		return arrivalTime;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public int getQuantaWait()
	{
		return quantaWait;
	}
	
	public float getTimeValue()
	{
		return actualTime - arrivalTime;
	}
	
	public float getRunTime()
	{
		return expectedTotalRunTime;
	}
	
	public float getSwitchTime()
	{
		return endTime - arrivalTime + 1;
	}
	
	public float getWait()
	{
		return endTime- arrivalTime - quantaTime;
	}
	
	public float getQuantaTime()
	{
		return quantaTime;
	}
	
	public float getEndTime()
	{
		return endTime;
	}

	public void incrementQuanta()
	{
		this.quantaTime = quantaTime + 1;
	}
	
	public void setRunTime(float expectedTotalRunTime)
	{
		this.expectedTotalRunTime = expectedTotalRunTime;
	}
	
	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	public void setArrivalTime(float arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	
	public void setTime(float arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setActualTime(int actualTime)
	{
		this.actualTime = actualTime;
	}
	
	public void setEndTime(int endTime)
	{
		this.endTime = endTime;
	}
	
	public void decrementExpectedTotalRunTime()
	{
		expectedTotalRunTime--;
	}
	
	public void decrementQuantaWait()
	{
		quantaWait--;
	}
	
	public void incrementQuantaWait()
	{
		quantaWait++;
		
		if(quantaWait == QUANTAWAIT)
		{
			quantaWait = 0;
			increasePriority();
		}
	}
	
	public void increasePriority()
	{
		if(priority > PRIORITY)
		{
			priority--;
		}
	}
	
	public Process clone()
	{
		Process cloned = new Process();
		
		cloned.setArrivalTime(arrivalTime);
		cloned.setRunTime(expectedTotalRunTime);
		cloned.setName(name);
		cloned.setPriority(priority);
		
		return cloned;
	}
}
