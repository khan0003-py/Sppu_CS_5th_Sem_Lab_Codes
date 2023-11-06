import java.util.Scanner;

class Process {
    String processName;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnAroundTime;
    int waitingTime;
    int remainingTime;

    Process(String processName, int arrivalTime, int burstTime) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class SJF_Preemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, awt = 0, atat = 0;
        System.out.println("Enter Number of Processes: ");
        n = sc.nextInt();

        Process[] processes = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time of Process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.println("Enter Burst Time of Process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes[i] = new Process("P" + (i + 1), arrivalTime, burstTime);
        }

        int currentTime = 0;
        int completedProcesses = 0;

        while (completedProcesses < n) {
            int shortestRemainingTime = Integer.MAX_VALUE;
            int selectedProcess = -1;

            for (int i = 0; i < n; i++) {
                if (processes[i].arrivalTime <= currentTime && processes[i].remainingTime < shortestRemainingTime && processes[i].remainingTime > 0) {
                    shortestRemainingTime = processes[i].remainingTime;
                    selectedProcess = i;
                }
            }

            if (selectedProcess == -1) {
               
                currentTime++;
            } else {
            
                processes[selectedProcess].remainingTime--;

                if (processes[selectedProcess].remainingTime == 0) {
                    completedProcesses++;
                    processes[selectedProcess].completionTime = currentTime + 1;
                    processes[selectedProcess].turnAroundTime = processes[selectedProcess].completionTime - processes[selectedProcess].arrivalTime;
                    processes[selectedProcess].waitingTime = processes[selectedProcess].turnAroundTime - processes[selectedProcess].burstTime;
                    awt += processes[selectedProcess].waitingTime;
                    atat += processes[selectedProcess].turnAroundTime;
                }

                currentTime++;
            }
        }

        float averageWaitingTime = (float) awt / n;
        float averageTurnAroundTime = (float) atat / n;

        System.out.println("Process\tAT \tBT \tCT \tTAT \tWT");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].processName + "\t" + processes[i].arrivalTime + "\t"
                    + processes[i].burstTime + "\t" + processes[i].completionTime + "\t"
                    + processes[i].turnAroundTime + "\t" + processes[i].waitingTime);
        }
        sc.close();
        System.out.println();
        System.out.println("Average waiting time : " + averageWaitingTime);
        System.out.println("Average turn around time : " + averageTurnAroundTime);
        System.out.println();
        System.out.println("By Arshad Khan : @whoami0003.py");
        System.out.println();
    }
}
