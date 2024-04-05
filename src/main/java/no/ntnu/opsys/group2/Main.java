package no.ntnu.opsys.group2;

import java.util.ArrayList;
import java.util.List;

/**
 * The Main class represents the runner class in the project.
 * 
 * @author  Group 2
 * @version v1.0 (2024.04.05)
 */
public class Main {
  /**
   * Is the main starting point of the project. The operating system on the computer expects to
   * find a publicly available method it can call without having to create an instance of a class
   * first.
   * 
   * <p>This method is standardized across programming languages and operating systems and must
   * have the method signature given below.</p>
   * 
   * @param args A fixed sized array of strings holding arguments provided from the command line
   *             during the startup of the application
   */
  public static void main(String[] args) {
    fcfsAlgorithm();
    // prioritySchedulingAlgorithm();
  }

  /**
   * Runs the First Come First Serve algorithm with a defined set of processes.
   */
  private static void fcfsAlgorithm() {
    List<Process> processes = new ArrayList<>();
    Process p1 = new Process(1, 4, 5);
    Process p2 = new Process(2, 6, 4);
    Process p3 = new Process(3, 0, 3);
    Process p4 = new Process(4, 6, 2);
    Process p5 = new Process(5, 5, 4);
    processes.add(p1);
    processes.add(p2);
    processes.add(p3);
    processes.add(p4);
    processes.add(p5);
    Scheduler sc = new Scheduler();
    sc.fcfsAlgo(processes);

    System.out.println("Average Waiting Time: " + sc.getAvgWaitingTime());
    System.out.println("Average Turn Around Time: " + sc.getAvgTurnAroundTime());
  }

  /**
   * Runs the Preemptive Priority Scheduling algorithm with a defined set of processes.
   */
  private static void prioritySchedulingAlgorithm() {
    List<Process> processes = new ArrayList<>();
    Process p1 = new Process(1, 0, 11, 2);
    Process p2 = new Process(2, 5, 28, 0);
    Process p3 = new Process(3, 12, 2, 3);
    Process p4 = new Process(4, 2, 10, 1);
    Process p5 = new Process(5, 9, 16, 4);
    processes.add(p1);
    processes.add(p2);
    processes.add(p3);
    processes.add(p4);
    processes.add(p5);
    Scheduler sc = new Scheduler();
    sc.prioritySchedulingAlgo(processes);

    System.out.println("Average Waiting Time: " + sc.getAvgWaitingTime());
    System.out.println("Average Turn Around Time: " + sc.getAvgTurnAroundTime());
  }
}
