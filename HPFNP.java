import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Ray on 3/1/2017.
 */
public class HPFNP {
    private ArrayList<Process> processes;
    private ArrayList<Process> completed;
    private Queue<Process> q1;
    private Queue<Process> q2;
    private Queue<Process> q3;
    private Queue<Process> q4;
    private static int QUANTA_MAX = 99;
    private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;



    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<Process>();
        for (int i = 1; i <= NUMBER_OF_PROCESSES_TO_MAKE; i++) {
            Process process = new Process("P" + i, i);
            processes.add(process);
        }
        Process.sortListByArrivalTime(processes);

        HPFNP hpfnp = new HPFNP(processes);

        String table = "";
        for (Process process : hpfnp.getProcesses()) {
            table += "[Process: " + String.format("%3s", process.getName()) + ", Arrival time: "
                    + String.format("%3d", process.getArrivalTime()) + ", Run time: "
                    + String.format("%3d", process.getGivenExecutionTime()) + ", Priority: " + process.getPriority()
                    + ", End time: " + String.format("%3d", process.getEndTime()) + ", Time started: "
                    + String.format("%3d", process.getStartExecutionTime()) + ", Turnaround time: "
                    + String.format("%3d", process.calculateTurnaroundTime()) + ", Wait time: "
                    + String.format("%3d", process.calculateWaitTime()) + ", Response time: "
                    + String.format("%3d", process.calculateResponseTime()) + "]\n";

        }
        System.out.println(table);
    }

    public ArrayList<Process> getProcesses() {
        return this.completed;
    }

    public HPFNP(ArrayList<Process> processes)
    {
        this.processes = processes;
        this.completed = new ArrayList<Process>();

        q1 = new PriorityQueue<>();
        q2 = new PriorityQueue<>();
        q3 = new PriorityQueue<>();
        q4 = new PriorityQueue<>();
        setup();
    }



    private void setup()
    {
        int quanta = 0;
        double turnAroundTime = 0;
        double responseTime = 0;
        double processesFinished = 0;
        double totalWaitTime = 0;

        while(quanta < QUANTA_MAX)
        {
            for(Process process : processes)
            {
                if(process.getArrivalTime() <= quanta) {
                    if (process.getPriority() == 1) {
                        q1.add(process);
                    } else if (process.getPriority() == 2) {
                        q2.add(process);
                    } else if (process.getPriority() == 3) {
                        q3.add(process);
                    } else if (process.getPriority() == 4) {
                        q4.add(process);
                    } else {
                        System.exit(1); // getting a non-existant priority.
                    }
                }

                if(!q1.isEmpty())
                {
                   q1.peek().setStartExecutionTime(quanta);
                    while (q1.peek().getExecutionTimeRemaining() > 0) {
                        q1.peek().decrementExecutionTimeRemaining();
                        quanta++;
                        System.out.print("[" + q1.peek().getName() + "]");
                    }
                    q1.peek().setEndTime(quanta);
                    completed.add(q1.peek());

                    processesFinished++;
                    turnAroundTime += q1.peek().calculateTurnaroundTime();
                    totalWaitTime += q1.peek().calculateWaitTime();
                    responseTime += q1.peek().calculateResponseTime();
                    q1.remove();
                }
                else if(!q2.isEmpty())
                {
                    q2.peek().setStartExecutionTime(quanta);
                    while (q2.peek().getExecutionTimeRemaining() > 0) {
                        q2.peek().decrementExecutionTimeRemaining();
                        quanta++;
                        System.out.print("[" + q2.peek().getName() + "]");
                    }
                    q2.peek().setEndTime(quanta);
                    completed.add(q2.peek());

                    processesFinished++;
                    turnAroundTime += q2.peek().calculateTurnaroundTime();
                    totalWaitTime += q2.peek().calculateWaitTime();
                    responseTime += q2.peek().calculateResponseTime();
                    q2.remove();
                }
                else if(!q3.isEmpty())
                {
                    q3.peek().setStartExecutionTime(quanta);
                    while (q3.peek().getExecutionTimeRemaining() > 0) {
                        q3.peek().decrementExecutionTimeRemaining();
                        quanta++;
                        System.out.print("[" + q3.peek().getName() + "]");
                    }
                    q3.peek().setEndTime(quanta);
                    completed.add(q3.peek());

                    processesFinished++;
                    turnAroundTime += q3.peek().calculateTurnaroundTime();
                    totalWaitTime += q3.peek().calculateWaitTime();
                    responseTime += q3.peek().calculateResponseTime();
                    q3.remove();
                }
                else if(!q4.isEmpty())
                {
                    q4.peek().setStartExecutionTime(quanta);
                    while (q4.peek().getExecutionTimeRemaining() > 0) {
                        q4.peek().decrementExecutionTimeRemaining();
                        quanta++;
                        System.out.print("[" + q4.peek().getName() + "]");
                    }
                    q4.peek().setEndTime(quanta);
                    completed.add(q4.peek());

                    processesFinished++;
                    turnAroundTime += q4.peek().calculateTurnaroundTime();
                    totalWaitTime += q4.peek().calculateWaitTime();
                    responseTime += q4.peek().calculateResponseTime();
                    q4.remove();
                }
                else
                {
                    System.out.print("[*]");
                    quanta++;
                }
            }
        }
    }
}
