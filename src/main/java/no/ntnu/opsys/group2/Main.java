package no.ntnu.opsys.group2;

import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
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
    System.out.println(sc.prioritySchedulingAlgo(processes).get(0));
    System.out.println(sc.prioritySchedulingAlgo(processes).get(1));
  }
}
