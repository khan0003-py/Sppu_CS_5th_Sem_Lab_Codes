import java.util.Scanner;

class Process {
    String processName;
    int arrivalTime;
    int burstTime;
    int completionTime;
    int turnAroundTime;
    int waitingTime;
    int priority;

    Process(String processName, int arrivalTime, int burstTime, int priority) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class Priority  {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, awt = 0, atat = 0;
        System.out.println("Enter Number of Processes: ");
        n = sc.nextInt();

        Process[] processes = new Process[n];
        boolean b[] = new boolean[n];
        for (int i = 0; i < n; i++) {
            b[i] = false;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Enter Arrival Time of Process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.println("Enter Burst Time of Process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            System.out.println("Enter Priority of Process " + (i + 1) + ": ");
            int priority = sc.nextInt();
            processes[i] = new Process("P" + (i + 1), arrivalTime, burstTime, priority);
        }

        int currentTime = 0;
        boolean allCompleted = false;

        while (!allCompleted) {
            int highestPriority = Integer.MIN_VALUE;
            int selectedProcess = -1;

            for (int i = 0; i < n; i++) {
                if (!b[i] && processes[i].arrivalTime <= currentTime && processes[i].priority > highestPriority) {
                    highestPriority = processes[i].priority;
                    selectedProcess = i;
                }
            }

            if (selectedProcess == -1) {
                currentTime++;
            } else {
                b[selectedProcess] = true;
                currentTime += processes[selectedProcess].burstTime;
                processes[selectedProcess].completionTime = currentTime;
                processes[selectedProcess].turnAroundTime = processes[selectedProcess].completionTime - processes[selectedProcess].arrivalTime;
                processes[selectedProcess].waitingTime = processes[selectedProcess].turnAroundTime - processes[selectedProcess].burstTime;
                awt += processes[selectedProcess].waitingTime;
                atat += processes[selectedProcess].turnAroundTime;
            }

            allCompleted = true;
            for (int i = 0; i < n; i++) {
                if (!b[i]) {
                    allCompleted = false;
                    break;
                }
            }
        }

        float averageWaitingTime = (float) awt / n;
        float averageTurnAroundTime = (float) atat / n;

        System.out.println("Process\tAT \tBT \tPT \tCT \tTAT \tWT");
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i].processName + "\t" + processes[i].arrivalTime + "\t"
                    + processes[i].burstTime + "\t" + processes[i].priority + "\t" + processes[i].completionTime + "\t"
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
