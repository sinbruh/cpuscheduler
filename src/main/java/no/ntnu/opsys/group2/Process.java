package no.ntnu.opsys.group2;

/**
 * The Process class represents a process used in one of the algorithms.
 * 
 * @author  Group 2
 * @version v1.0 (2024.04.05)
 */
public class Process {
  private int id;
  private int arrivalTime;
  private int burstTime;
  private int waitTime;
  private int completionTime;
  private int priority;
  private boolean done;

  /**
   * Constructs an instance of the Process class.
   * 
   * <p>This constructor is used to construct instances specifically used in the First Come First
   * Serve algorithm.</p>
   * 
   * @param id The specifed process ID
   * @param arrivalTime The specified arrical time
   * @param burstTime The specified burst time
   */
  public Process(int id, int arrivalTime, int burstTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    // These fields are not used in the algorithm
    this.waitTime = 0;
    this.completionTime = 0;
    this.priority = 0;
    this.done = false;
  }

  /**
   * Constructs an instance of the Process class.
   * 
   * <p>This constructor is used to construct instances specifically used in the Preemptive
   * Priority Scheduling algorithm.</p>
   * 
   * @param id The specified process ID
   * @param arrivalTime The specified arrival time
   * @param burstTime The specified burst time
   * @param priority The specified priority
   */
  public Process(int id, int arrivalTime, int burstTime, int priority) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.waitTime = 0;
    this.completionTime = 0;
    this.priority = priority;
    this.done = false;
  }

  /**
   * Returns the process ID.
   * 
   * @return The process ID
   */
  public int getId() {
    return this.id;
  }

  /**
   * Returns the arrival time.
   * 
   * @return The arrival time
   */
  public int getArrivalTime() {
    return this.arrivalTime;
  }

  /**
   * Returns the burst time.
   * 
   * @return The burst time
   */
  public int getBurstTime() {
    return this.burstTime;
  }

  /**
   * Returns the total wait time.
   * 
   * @return The total wait time
   */
  public int getWaitTime() {
    return this.waitTime;
  }

  /**
   * Returns the completion time.
   * 
   * @return The completion time
   */
  public int getCompletionTime() {
    return this.completionTime;
  }
  
  /**
   * Returns the priority.
   * 
   * @return The priority
   */
  public int getPriority() {
    return this.priority;
  }

  /**
   * Returns true if the process is done or false otherwise
   * 
   * @return True if the process is done or false otherwise
   */
  public boolean isDone() {
    return this.done;
  }

  /**
   * Sets the burst time to the specified burst time.
   * 
   * @param burstTime The specified burst time
   */
  public void setBurstTime(int burstTime) {
    this.burstTime = burstTime;
  }

  /**
   * Sets the completion time to the specified completion time.
   * 
   * @param completionTime The specified completion time
   */
  public void setCompletionTime(int completionTime) {
    this.completionTime = completionTime;
  }

  /**
   * Ticks the process by decrementing the burst time
   * 
   * <p>The process is declared done if it has no remaining burst time.</p>
   */
  public void tick() {
    this.burstTime--;
    if (this.burstTime == 0) {
      this.done = true;
    }
  }

  /**
   * Wait ticks the process by incrementing the wait time.
   */
  public void waitTick() {
    this.waitTime++;
  }
}
