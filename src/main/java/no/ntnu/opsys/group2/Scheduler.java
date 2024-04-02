package no.ntnu.opsys.group2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Scheduler {
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
    // Adds the average of the total results to the result list
    results.add(totalTurnAroundTime / processes.size());
    results.add(totalWaitingTime / processes.size());

    return results;
  }

  private int calculateTurnAroundTime(int completionTime, int arrivalTime) {
    return completionTime - arrivalTime;
  }

  private int calculateWaitingTime(int turnAroundTime, int burstTime) {
    return turnAroundTime - burstTime;
  }

  private List<Process> sortByArrivalTime(List<Process> processes) {
    return processes.stream().sorted(Comparator.comparing(Process::getArrivalTime))
           .collect(Collectors.toList());
  }
}
