package no.ntnu.opsys.group2;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Scheduler {
  public double getFcfsAvgWaitingTime(List<Process> processes) {
    int t = 0;
    double totalWaitingTime = 0.0;

    processes = this.sortByArrivalTime(processes);

    for (Process process : processes) {
      int turnAroundTime = 0;
      int waitingTime = 0;
      boolean finished = false;
      while (!finished) {
        if (process.getArrivalTime() <= t) {
          t += process.getBurstTime();
          turnAroundTime = this.calculateTurnAroundTime(t, process.getArrivalTime());
          waitingTime = this.calculateWaitingTime(turnAroundTime, process.getBurstTime());
          finished = true;
        } else {
          t++;
        }
      }
      totalWaitingTime += waitingTime;
    }
    return totalWaitingTime / processes.size();
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
