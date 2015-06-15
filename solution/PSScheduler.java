import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.SortedMap;

/**
 * Proportional share scheduler
 * Take total shares to be 100.
 * A process will not run unless it is completely given the requested share.
 */
public class PSScheduler implements Scheduler {

  /** Debug flag. */
  private static boolean D = false;

  private final int TOTAL_SHARE = 100;

  @Override
  public void schedule(String inputFile, String outputFile) {
    Map<Long, Process> processes = Utils.parse(inputFile);
    List<Process> scheduled = new ArrayList<Process>();

    PriorityQueue<Process> readyQueue = new PriorityQueue<Process>(processes.size());
    PriorityQueue<Long> scheduleTimes = new PriorityQueue<Long>();

    // HashMap to keep track of <When, HowMuch> shares will be available.
    Map<Long, Integer> shareMap = new HashMap<Long, Integer>();

    SortedMap<Integer, List<Long>> sortedArrivalMap = Process.sortArrivalTime(processes);
    Iterator<Integer> arrivalTimeIterator = sortedArrivalMap.keySet().iterator();

    int availableShare = TOTAL_SHARE;
    long newProcessArrivalTime = arrivalTimeIterator.next();
    scheduleTimes.add(newProcessArrivalTime); // First arriving process is our initial time
    while (true) {
      if (scheduled.size() == processes.size()) {
        break;
      }

      long current = scheduleTimes.poll();
      if (D) System.out.println("->\n@I: Current time is " + current);

      // Update the {@code availableShare} if we have a process finishing.
      if (shareMap.containsKey(current)) {
        availableShare += shareMap.get(current);
        shareMap.remove(current);
        if (D) System.out.println(current + "> Updating the share to " + availableShare);
      }

      if (newProcessArrivalTime == current) {
        // Add all the arrived processes to readyQueue
        for (Long pid : sortedArrivalMap.get((int) newProcessArrivalTime)) {
          if (D) System.out.println("@@@I: Process " + pid + " is added to ready queue");
          readyQueue.add(processes.get(pid));
        }

        // Get the next time on which new processes will arrive
        if (arrivalTimeIterator.hasNext()) {
          newProcessArrivalTime = arrivalTimeIterator.next();
          if (!scheduleTimes.contains(newProcessArrivalTime)) {
            scheduleTimes.add(newProcessArrivalTime);
          }
        }
      }

      // If we have resources and processes waiting to be scheduled
      while (availableShare > 0 && readyQueue.size() > 0) {
        Process toBeScheduled = readyQueue.peek();
        if (toBeScheduled.share > availableShare) {
          // We do not have enough share, stop and wait for shares to be available
          break;
        }

        availableShare -= toBeScheduled.share;

        if (D) System.out.println(current + "> Scheduling " + toBeScheduled.pid + " that requires"
            + " share of " + toBeScheduled.share + ", available share is now " + availableShare);

        readyQueue.poll(); // remove the process from readyQueue
        toBeScheduled.waitTime = current - toBeScheduled.arrivalTime;
        toBeScheduled.finishTime = current + toBeScheduled.executionTime;
        toBeScheduled.turnaroundTime = toBeScheduled.finishTime - toBeScheduled.arrivalTime;
        int newShare = toBeScheduled.share;
        if (shareMap.containsKey(toBeScheduled.finishTime)) {
          newShare += shareMap.get(toBeScheduled.finishTime);
        }
        shareMap.put(toBeScheduled.finishTime, newShare);
        if (!scheduleTimes.contains(toBeScheduled.finishTime)) {
          scheduleTimes.add(toBeScheduled.finishTime);
        }
        scheduled.add(toBeScheduled);
      }
    }

    // write the scheduler output to {@code outputFile}
    Utils.write(scheduled, outputFile);
  }
}
