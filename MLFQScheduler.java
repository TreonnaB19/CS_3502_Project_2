import java.util.*;

public class MLFQScheduler implements Scheduler {
    private final int[] timeQuantums = {4, 8}; // Tune as needed
    private final int maxLevel = timeQuantums.length;

    @Override
    public List<Process> schedule(List<Process> processList) {
        List<Process> processes = new ArrayList<>();
        for (Process p : processList) {
            processes.add(p.copy());
        }

        List<Queue<Process>> queues = new ArrayList<>();
        for (int i = 0; i <= maxLevel; i++) {
            queues.add(new LinkedList<>());
        }

        List<Process> completed = new ArrayList<>();
        int time = 0;

        Map<String, Integer> processLevels = new HashMap<>();

        while (completed.size() < processes.size()) {
            for (Process p : processes) {
                if (p.arrivalTime == time) {
                    queues.get(0).add(p);
                    processLevels.put(p.name, 0);
                }
            }

            Process current = null;
            int currentLevel = -1;

            for (int i = 0; i < queues.size(); i++) {
                if (!queues.get(i).isEmpty()) {
                    current = queues.get(i).poll();
                    currentLevel = i;
                    break;
                }
            }

            if (current == null) {
                time++;
                continue;
            }

            if (current.startTime == -1) {
                current.startTime = time;
                current.responseTime = time - current.arrivalTime;
            }

            int quantum = (currentLevel < timeQuantums.length) ? timeQuantums[currentLevel] : Integer.MAX_VALUE;
            int actualRunTime = Math.min(quantum, current.remainingTime);
            int runUntil = time + actualRunTime;

            for (int t = time + 1; t <= runUntil; t++) {
                for (Process p : processes) {
                    if (p.arrivalTime == t) {
                        queues.get(0).add(p);
                        processLevels.put(p.name, 0);
                    }
                }
            }

            current.remainingTime -= actualRunTime;
            time += actualRunTime;

            if (current.remainingTime == 0) {
                current.finishTime = time;
                current.turnaroundTime = current.finishTime - current.arrivalTime;
                current.waitingTime = current.turnaroundTime - current.burstTime;
                completed.add(current);
            } else {
                int nextLevel = Math.min(currentLevel + 1, maxLevel);
                queues.get(nextLevel).add(current);
                processLevels.put(current.name, nextLevel);
            }
        }

        return completed;
    }
}
