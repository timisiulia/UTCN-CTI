package model;

public class Task implements Comparable<Task> {
    private int arrivalTime=-1;
    private int finishTime=-1;
    private int processingPeriod=-1;
    private int id;

    public Task() {

    }

    public Task(int arrivingTime, int processingTime,int id)
    {
        this.arrivalTime = arrivingTime;
        this.processingPeriod = processingTime;
        this.id=id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getProcessingPeriod() {
        return processingPeriod;
    }

    public void setProcessingPeriod(int processingPeriod) {
        this.processingPeriod = processingPeriod;
    }
    public String toString()
    {
        return "(" + id + "," + arrivalTime + "," + processingPeriod + ")";
    }

    @Override
    public int compareTo(Task t1) {
        return this.arrivalTime-t1.getArrivalTime();
    }
}
