package no.ntnu.opsys.group2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Scheduler class represents the class that contains the algorithms used in the assignment.
 * 
 * @author  Group 2
 * @version v1.0 (2024.04.05)
 */
public class Scheduler {
  private double avgTurnAroundTime;
  private double avgWaitingTime;

  /**
   * Returns the average Waiting Time.
   * 
   * @return The average Waiting Time
   */
  public double getAvgWaitingTime() {
    return avgWaitingTime;
  }

  /**
   * Returns the average Turn Around Time.
   * 
   * @return The average Turn Around Time
   */
  public double getAvgTurnAroundTime() {
    return avgTurnAroundTime;
  }

  /**
   * Returns a list containing the Average Waiting Time and Average Turn Around Time respectively
   * from a specified list of processes using First Come First Serve algorithm.
   * 
   * @param processes A specifed list of processes
   * @return A list containing the Average Waiting Time and Average Turn Around Time respectively
   */
  public List<Double> fcfsAlgo(List<Process> processes) {
    // Defines variable to store results
    List<Double> results = new ArrayList<>();

    // Defines ticks
    int t = 0;
    // Defines variables to store total results
    double totalTurnAroundTime = 0.0;
    double totalWaitingTime = 0.0;

    // Sorts processes by arrival time
    processes = this.sortByArrivalTime(processes);

    for (Process process : processes) {
      int turnAroundTime = 0;
      int waitingTime = 0;
      boolean finished = false;
      while (!finished) {
        // Checks if the process has arrived at the current tick
        if (process.getArrivalTime() <= t) {
          // Jumps to the tick where the burst time is finished
          t += process.getBurstTime();
          // Calculates Turn Around Time and Waiting Time for the current process
          turnAroundTime = this.calculateTurnAroundTime(t, process.getArrivalTime());
          waitingTime = this.calculateWaitingTime(turnAroundTime, process.getBurstTime());
          finished = true;
        } else {
          t++;
        }
      }
      // Adds the Turn Around Time and Waiting time for the current process to the total results
      totalTurnAroundTime += turnAroundTime;
      totalWaitingTime += waitingTime;
    }
    // Sets the results to the scheduler object
    avgTurnAroundTime = (totalTurnAroundTime / processes.size());
    avgWaitingTime = (totalWaitingTime / processes.size());

    return results;
  }

  public void prioritySchedulingAlgo(List<Process> processes) {
    int size = processes.size();

    // Defines ticks
    int tick = 0;

    List<Process> arrived = new ArrayList<>();
    List<Process> done = new ArrayList<>();
    boolean finished = false;

    while (!finished) {

      // Moves processes that have arrived to the arrived list
      Iterator<Process> processIterator = processes.iterator();
      while (processIterator.hasNext()) {
        Process process = processIterator.next();
        if (process.getArrivalTime() <= tick) {
          arrived.add(process);
          processIterator.remove();
        }
      }

      Process activeProcess;
      if (!arrived.isEmpty()) {
        arrived = arrived.stream().sorted(Comparator.comparing(Process::getPriority))
                  .collect(Collectors.toList());
        activeProcess = arrived.get(0);

        activeProcess.tick();
        for (int i = 1; i < arrived.size(); i++) {
          arrived.get(i).waitTick();
        }
        if (activeProcess.isDone()) {
          activeProcess.setCompletionTime(tick);
          done.add(activeProcess);
          arrived.remove(activeProcess);
        }
      }
      tick++;
      finished = arrived.isEmpty() && processes.isEmpty();
    }

    double totalTurnAroundTime = 0.0;
    double totalWaitingTime = 0.0;

    for (Process process : done) {
      int waitingTime = process.getWaitTime();
      System.out.println("Process " + process.getId() + " waiting time: " + waitingTime);
      int turnAroundTime = process.getArrivalTime() + process.getCompletionTime();
        totalTurnAroundTime += turnAroundTime;
        totalWaitingTime += waitingTime;
    }

    this.avgTurnAroundTime = (totalTurnAroundTime / size);
    this.avgWaitingTime = (totalWaitingTime / size);
  }

  /**
   * Returns the calculated Turn Around Time for a process by the specifed completion time and the
   * specified arrival time.
   * 
   * @param completionTime The specified completion time
   * @param arrivalTime The specified arriva time
   * @return The calculated Turn Around Time for a process
   */
  private int calculateTurnAroundTime(int completionTime, int arrivalTime) {
    return completionTime - arrivalTime;
  }

  /**
   * Returns the calculated Waiting Time for a proess by the specified Turn Around Time for the
   * process and the specified burst time.
   * 
   * @param turnAroundTime The specified Turn Around Time for the process
   * @param burstTime The specified burst time
   * @return The calculated Waiting Time for a process
   */
  private int calculateWaitingTime(int turnAroundTime, int burstTime) {
    return turnAroundTime - burstTime;
  }

  /**
   * Returns the set of processes sorted by arrival time from the specified set of processes.
   * 
   * @param processes The specifed set of processes
   * @return The set of processes sorted by arrival time
   */
  private List<Process> sortByArrivalTime(List<Process> processes) {
    return processes.stream().sorted(Comparator.comparing(Process::getArrivalTime))
           .collect(Collectors.toList());
  }
}
