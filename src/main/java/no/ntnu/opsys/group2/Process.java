package no.ntnu.opsys.group2;

public class Process {
  private int id;
  private int arrivalTime;
  private int burstTime;
  private int waitTime;
  private int priority;
  private boolean done = false;
  private int completionTime;

  public Process(int id, int arrivalTime, int burstTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.waitTime = 0;
    this.priority = 0;
  }

  public Process(int id, int arrivalTime, int burstTime, int priority) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.waitTime = 0;
    this.priority = priority;
  }

  public int getArrivalTime() {
    return this.arrivalTime;
  }
  
  public int getBurstTime() {
    return this.burstTime;
  }

  public int getWaitTime() {
    return this.waitTime;
  }
  
  public int getPriority() {
    return this.priority;
  }

  public void tick() {
    this.burstTime--;
    if (this.burstTime == 0) {
      this.done = true;
    }
  }

  public void waitTick() {
    this.waitTime++;
  }

  public boolean isDone() {
    return done;
  }

  public void setCompletionTime(int tick) {
    this.completionTime = tick;
  }

  public int getCompletionTime() {
    return this.completionTime;
  }

  public int getId() {
    return id;
  }

  public void setBurstTime(int i) {
    this.burstTime = i;
  }
}
