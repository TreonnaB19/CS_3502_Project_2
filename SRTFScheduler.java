import java.util.*;

public class SRTFScheduler implements Scheduler {

    @Override
    public List<Process> schedule(List<Process> processList) {
        List<Process> processes = new ArrayList<>();
        for (Process p : processList) {
            processes.add(p.copy());
        }

        List<Process> completed = new ArrayList<>();
        int time = 0;
        Process current = null;

        while (completed.size() < processes.size()) {
            Process shortest = null;
            for (Process p : processes) {
                if (p.arrivalTime <= time && p.remainingTime > 0) {
                    if (shortest == null || p.remainingTime < shortest.remainingTime) {
                        shortest = p;
                    }
                }
            }

            if (shortest == null) {
                time++;
                continue;
            }

            if (shortest.startTime == -1) {
                shortest.startTime = time;
                shortest.responseTime = time - shortest.arrivalTime;
            }

            shortest.remainingTime--;
            time++;

            if (shortest.remainingTime == 0) {
                shortest.finishTime = time;
                shortest.turnaroundTime = shortest.finishTime - shortest.arrivalTime;
                shortest.waitingTime = shortest.turnaroundTime - shortest.burstTime;
                completed.add(shortest);
            }
        }

        return completed;
    }
}
