import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Utility functions for the project.
 */
public class Utils {

  /** Debug flag. */
  private static boolean D = false;

  /**
   * Parser to read a set of processes from input file.
   * @param filename The input file
   * @return a map of processes where pid is the key and {@link Process} is the value
   */
  public static Map<Long, Process> parse(final String filename) {
    Map<Long, Process> processes = new HashMap<Long, Process>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line = "";
      while ((line = reader.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(line);
        long processId = Long.parseLong(st.nextToken());
        int arrivalTime = Integer.parseInt(st.nextToken());
        int exectionTime = Integer.parseInt(st.nextToken());
        if (exectionTime > 100) {
          reader.close();
          throw new RuntimeException("Burst time must be at most 100 " +filename);
        }
        int share = -1;
        if (st.hasMoreTokens()) {
          share = Integer.parseInt(st.nextToken());
        }
        processes.put(processId, new Process(processId, arrivalTime, exectionTime, share));
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.err.println("Exception while opening file " + filename);
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("Exception while reading file " + filename);
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Exception occured reading file " + filename);
      e.printStackTrace();
    }
    return processes;
  }

  /**
   * Write results of scheduling to given filename.
   * @param processes
   * @param filename
   */
  public static void write(List<Process> processes, String filename) {
    // Sort the processes according to pid
    Collections.sort(processes);

    // Build the output where each line is:
    // <process-id> <finish-time> <wait-time> <turnaround-time>
    // Last line is:
    // <average-wait-time> <average-turnaround-time>
    StringBuilder builder = new StringBuilder();
    double totalWaitTime = 0;
    double totalTurnaroundTime = 0;
    for (Process process : processes) {
      builder.append(process.pid + " ");
      builder.append(process.finishTime + " ");
      builder.append(process.waitTime + " ");
      builder.append(process.turnaroundTime + "\n");
      totalWaitTime += process.waitTime;
      totalTurnaroundTime += process.turnaroundTime;
    }

    builder.append(Math.round(totalWaitTime/(double) processes.size()) + " ");
    builder.append(Math.round(totalTurnaroundTime/(double) processes.size()));

    try {
      PrintWriter writer = new PrintWriter(filename);
      writer.print(builder);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (D) {
      System.out.println("Writing to file " + filename);
      System.out.println("<Pid> <FinishTime> <WaitTime> <TurnAroundTime>");
      System.out.println(builder);
    }
  }

  /**
   * Main function to generate random process inputs.
   * @param args
   */
  public static void main(String[] args) {
//    int count = 100;
//    for (int i = 1; i < 1999; i++) {
//      System.out.println(i + " 1 " + count);
//      count -= 1;
//      if (count == 0) {
//        count = 100;
//      }
//    }

//    Random rndm = new Random();
//    List<String> outputs = new ArrayList<String>();
//    int count = 100;
//    int arrivalTime = 1;
//    for (long i = 0; i < 100; i++) {
////      outputs.add(i + " " + (int)Math.ceil(rndm.nextInt(100) + 1) + " " +
////          (int)Math.ceil(rndm.nextInt(100) + 1) + " " +
////          (int)Math.ceil(rndm.nextInt(100) + 1));
//      outputs.add( (i+1) + " " + arrivalTime + " " + count);
//      count -= 1;
//      if (count == 0) {
//        count = 100;
//        arrivalTime += 6000;
//      }
//    }
////    Collections.shuffle(outputs);
//    for (String str : outputs) {
//      System.out.println(str);
//    }
  }
}
