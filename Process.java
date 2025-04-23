public class Process {
    public String name;
    public int arrivalTime;
    public int burstTime;
    public int remainingTime;
    public int priority;

    public int startTime = -1;
    public int finishTime;
    public int waitingTime;
    public int turnaroundTime;
    public int responseTime;

    public Process(String name, int arrivalTime, int burstTime, int priority) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }

    public Process copy() {
        return new Process(name, arrivalTime, burstTime, priority);
    }
}
