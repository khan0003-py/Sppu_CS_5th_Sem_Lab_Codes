import java.util.Scanner;

class Process {
    String processName;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnAroundTime;
    int waitingTime;

    Process(String processName, int arrivalTime, int burstTime) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class FCFS {

    public static void main(String[] args) {
        int arrivalTime;
        int burstTime;
        int currentvalue = 1;
        float averageWaitingTime;
        float averageTurnAroundTime;
        int avt = 0;
        int atat = 0;

        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Enter Number of Process  : ");
        n = sc.nextInt();

        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time of Process " + (i + 1) + " : ");
            arrivalTime = sc.nextInt();
            System.out.println("Enter Burst Time of Process " + (i + 1) + " : ");
            burstTime = sc.nextInt();
            processes[i] = new Process("P" + (i + 1), arrivalTime, burstTime);
        }
        for (int i = 0; i < n; i++) {
            currentvalue += processes[i].burstTime;
            processes[i].completionTime = currentvalue;
            processes[i].turnAroundTime = processes[i].completionTime - processes[i].arrivalTime;
            processes[i].waitingTime = processes[i].turnAroundTime - processes[i].burstTime;
        }

        for (int i = 0; i < n; i++) {
            avt += processes[i].waitingTime;
            atat += processes[i].turnAroundTime;
        }

        averageWaitingTime = (float) avt / n;
        averageTurnAroundTime = (float) atat / n;

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();
        System.out.println("Process\tAT \tBT \tCT \tTAT \tWT");
        System.out.println();
        System.out.println("-------------------------------------------");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].processName + "\t" + processes[i].arrivalTime + "\t"
                    + processes[i].burstTime + "\t" + processes[i].completionTime + "\t" + processes[i].turnAroundTime
                    + "\t" + processes[i].waitingTime);
        }
        System.out.println();
        System.out.println("-------------------------------------------");

        System.out.println();
        System.out.println("Average waiting time : " + averageWaitingTime);
        System.out.println();
        System.out.println("Average turn around time : " + averageTurnAroundTime);
        System.out.println();
        System.out.println();
        System.out.println("By Arshad Khan : @whoami0003.py");
    }
}
