package no.ntnu.opsys.group2;

public class Process {
  private int id;
  private int arrivalTime;
  private int burstTime;
  private int priority;

  public Process(int id, int arrivalTime, int burstTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.priority = 0;
  }

  public Process(int id, int arrivalTime, int burstTime, int priority) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.priority = priority;
  }

  public int getId() {
    return this.id;
  }
    
  public int getArrivalTime() {
    return this.arrivalTime;
  }
  
  public int getBurstTime() {
    return this.burstTime;
  }
  
  public int getPriority() {
    return this.priority;
  }
}
