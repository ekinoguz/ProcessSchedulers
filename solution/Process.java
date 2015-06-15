import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A mock process object with pid, arrival time, execution time, priority and share.
 */
public class Process implements Comparable<Process> {

  public final long pid;
  public final int arrivalTime;
  public int executionTime;
  public final int share;

  public long finishTime = 0;
  public long waitTime = 0;
  public long turnaroundTime = 0;

  /**
   * Create a process
   * @param pid A unique process id
   * @param arrivalTime Time when process arrives to scheduler
   * @param executionTime Total execution time of the process
   */
  public Process(long pid, int arrivalTime, int executionTime) {
    this.pid = pid;
    this.arrivalTime = arrivalTime;
    this.executionTime = executionTime;
    this.share = -1;
  }

  /**
   * Create a process
   * @param pid A unique process id
   * @param arrivalTime Time when process arrives to scheduler
   * @param executionTime Total execution time of the process
   * @param share An integer between (0, 100]
   */
  public Process(long pid, int arrivalTime, int executionTime, int share) {
    this.pid = pid;
    this.arrivalTime = arrivalTime;
    this.executionTime = executionTime;
    this.share = share;
  }

  public Process compareArrivalTime(Process other) {
    if (this.arrivalTime < other.arrivalTime) {
      return this;
    } else if (this.arrivalTime == other.arrivalTime) {
      return this.pid < other.pid ? this : other;
    } else {
      return other;
    }
  }

  @Override
  public int compareTo(Process o) {
    if (this.pid > o.pid) {
      return +1;
    } else {
      return -1;
    }
  }

  @Override
  public String toString() {
    return "(pid=" + pid + "," + "arrivalTime=" + arrivalTime + "," + "executionTime=" +
        executionTime + ")";
  }

  /**
   * Sorts given processes according to arrival time and returns a map.
   * @param processes Map of processes with <Pid, Process>
   * @return map with <ArrivalTime, List<Pid>>
   */
  public static SortedMap<Integer, List<Long>> sortArrivalTime(Map<Long, Process> processes) {
    SortedMap<Integer, List<Long>> sortedArrivalMap = new TreeMap<Integer, List<Long>>();

    for (Long pid : processes.keySet()) {
      int arrivalTime = processes.get(pid).arrivalTime;
      List<Long> pids = new ArrayList<Long>();
      if (sortedArrivalMap.containsKey(arrivalTime)) {
        pids = sortedArrivalMap.get(arrivalTime);
      }
      pids.add(pid);
      sortedArrivalMap.put(arrivalTime, pids);
    }

    return sortedArrivalMap;
  }
}
