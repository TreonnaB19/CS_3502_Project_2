import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Process> processes = Arrays.asList(
            new Process("P1", 0, 8, 1),
            new Process("P2", 1, 4, 2),
            new Process("P3", 2, 9, 1),
            new Process("P4", 3, 5, 3)
        );

        List<Scheduler> schedulers = Arrays.asList(
            new SRTFScheduler(),
            new MLFQScheduler()
        );

        String[] labels = {"SRTF", "MLFQ"};

        for (int i = 0; i < schedulers.size(); i++) {
            Scheduler scheduler = schedulers.get(i);
            List<Process> results = scheduler.schedule(processes);
            printMetrics(results, labels[i]);
        }
    }

    public static void printMetrics(List<Process> processes, String label) {
        double totalWaiting = 0;
        double totalTurnaround = 0;
        double totalResponse = 0;

        int totalTime = 0;
        int idleTime = 0;
        int firstArrival = Integer.MAX_VALUE;
        int lastFinish = Integer.MIN_VALUE;

        for (Process p : processes) {
            totalWaiting += p.waitingTime;
            totalTurnaround += p.turnaroundTime;
            totalResponse += p.responseTime;

            firstArrival = Math.min(firstArrival, p.arrivalTime);
            lastFinish = Math.max(lastFinish, p.finishTime);
        }

        totalTime = lastFinish - firstArrival;
        double cpuUtil = ((double) (totalTime - idleTime) / totalTime) * 100.0;
        double throughput = (double) processes.size() / totalTime;

        System.out.println("\n=== " + label + " Metrics ===");
        System.out.printf("%-8s%-12s%-12s%-12s%-12s%-12s\n", "Process", "Arrival", "Burst", "Waiting", "Turnaround", "Response");
        for (Process p : processes) {
            System.out.printf("%-8s%-12d%-12d%-12d%-12d%-12d\n",
                    p.name, p.arrivalTime, p.burstTime, p.waitingTime, p.turnaroundTime, p.responseTime);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", totalWaiting / processes.size());
        System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaround / processes.size());
        System.out.printf("Average Response Time: %.2f\n", totalResponse / processes.size());
        System.out.printf("CPU Utilization: %.2f%%\n", cpuUtil);
        System.out.printf("Throughput: %.2f processes/unit time\n", throughput);
    }
}
