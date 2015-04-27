public class Tester {

  public static void main(String[] args) {
    String input;
    String output;
    Scheduler scheduler;

    // Test FCFSScheduler
    // Public test 1
    try {
      input = "tests/fcfs1.input";
      output = "tests/fcfs1.output";
      scheduler = new FCFSScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Public test 2
    try {
      input = "tests/fcfs2.input";
      output = "tests/fcfs2.output";
      scheduler = new FCFSScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Test SJFScheduler

    // Test PSScheduler
  }
}
