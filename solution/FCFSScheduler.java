import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * First comes first served scheduler.
 */
public class FCFSScheduler implements Scheduler {

  /** Debug flag. */
  private static boolean D = false;

  @Override
  public void schedule(String inputFile, String outputFile) {
    Map<Long, Process> processes = Utils.parse(inputFile);
    List<Process> scheduled = new ArrayList<Process>();

    long current = 0;
    while (processes.size() > 0) {

      Iterator<Process> it = processes.values().iterator();
      Process earliest = it.next();
      // Find the one which arrives earliest
      while (it.hasNext()) {
        earliest = earliest.compareArrivalTime(it.next());
      }
      processes.remove(earliest.pid);

      if (earliest.arrivalTime > current) {
        current = earliest.arrivalTime;
      }

      if (D) System.out.println("Executing process " + earliest.pid + " between:[" +
          current + ", " + (current + earliest.executionTime) + "]");

      if (current == 0) {
        current = earliest.arrivalTime;
      }

      earliest.waitTime = current - earliest.arrivalTime;
      earliest.finishTime = current + earliest.executionTime;
      earliest.turnaroundTime = earliest.finishTime - earliest.arrivalTime;
      scheduled.add(earliest);

      current = earliest.finishTime;
    }
    Utils.write(scheduled, outputFile);
  }
}
