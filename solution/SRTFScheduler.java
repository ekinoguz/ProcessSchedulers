import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedMap;


/**
 * Shortest remaining time first scheduler
 */
public class SRTFScheduler implements Scheduler {

  /** Debug flag. */
  private static boolean D = false;

  @Override
  public void schedule(String inputFile, String outputFile) {
    Map<Long, Process> processes = Utils.parse(inputFile);
    List<Process> scheduled = new ArrayList<Process>();

    PriorityQueue<Process> readyQueue = new PriorityQueue<Process>(processes.size(),
        new ExecutionTimeComparator());

    SortedMap<Integer, List<Long>> sortedArrivalMap = Process.sortArrivalTime(processes);
    Iterator<Integer> arrivalTimeIterator = sortedArrivalMap.keySet().iterator();

    Process scheduledProcess = null;
    long current = 0;
    long oldCurrent = 0;
    int newProcessArrivalTime = -1;
    while (true) {
      if (scheduled.size() == processes.size()) {
        break;
      }

      oldCurrent = current;

      if (D) System.out.println("==============");

      // Get the time on which new processes will arrive
      if (newProcessArrivalTime == -1 && arrivalTimeIterator.hasNext()) {
        newProcessArrivalTime = arrivalTimeIterator.next();
      }

      // Decide on the next scheduling time
      if (newProcessArrivalTime != -1 && scheduledProcess != null) {
        if (newProcessArrivalTime <= scheduledProcess.arrivalTime ||
            newProcessArrivalTime <= (current + scheduledProcess.executionTime)) {
          current = newProcessArrivalTime;
        } else {
          current += scheduledProcess.executionTime;
        }
      } else if (scheduledProcess != null) {
        current += scheduledProcess.executionTime;
      } else if (newProcessArrivalTime != -1) {
        current = newProcessArrivalTime;
      } else {
        throw new RuntimeException("Should never see this while deciding on next current time.");
      }

      if (D) System.out.println("@I: Current time is:" + current);

      // Add all the arrived processes to readyQueue
      if (current == newProcessArrivalTime) {
        if (D) System.out.println("@I: Process arrival time:" + newProcessArrivalTime +
            ", arrived processes are:" + sortedArrivalMap.get(newProcessArrivalTime));

        for (Long pid : sortedArrivalMap.get(newProcessArrivalTime)) {
          readyQueue.add(processes.get(pid));
        }
        newProcessArrivalTime = -1;
      }

      if (scheduledProcess != null) {
        scheduledProcess.executionTime -= (current - oldCurrent);

        if (D) {
          System.out.println("@@@S: Executed process pid:" + scheduledProcess.pid + " between=[" +
              oldCurrent + ", " + current + "]");
        }

        // Update all other processes as waiting
        for (Process process : readyQueue) {
          if (!process.equals(scheduledProcess) && process.arrivalTime < current) {
            if (D) {
              System.out.println("Process " + process.pid + " is waiting for " +
                  (current - oldCurrent));
            }
            process.waitTime += (current - oldCurrent);
          }
        }

        if (scheduledProcess.executionTime > 0) {
          readyQueue.add(scheduledProcess);
        } else {
          scheduledProcess.finishTime = current;
          scheduledProcess.turnaroundTime = current - scheduledProcess.arrivalTime;
          scheduled.add(scheduledProcess);
          if (D) System.out.println("@@@S: Process " + scheduledProcess.pid + " finished " +
              " execution at time " + current);
          scheduledProcess = null;
        }
      }

      if (D) System.out.println("@I: Ready queue:" + readyQueue);

      // Get the process with the shortest remaining time.
      if (readyQueue.size() > 0) {
        scheduledProcess = readyQueue.peek();
        if (scheduledProcess.arrivalTime <= current) {
          readyQueue.poll();
        } else {
          scheduledProcess = null;
        }
      }
    }

    Utils.write(scheduled, outputFile);
  }

  /** Process comparator according to process executionTime. */
  private class ExecutionTimeComparator implements Comparator<Process> {

    @Override
    public int compare(Process o1, Process o2) {
      if (o1.executionTime != o2.executionTime) {
        return o1.executionTime - o2.executionTime;
      } else {
        return o1.compareTo(o2);
      }
    }
  }
}
