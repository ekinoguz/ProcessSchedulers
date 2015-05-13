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

    // Public test 3
    try {
      input = "tests/fcfs3.input";
      output = "tests/fcfs3.output";
      scheduler = new FCFSScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Public test 4
    try {
      input = "tests/fcfs4.input";
      output = "tests/fcfs4.output";
      scheduler = new FCFSScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Test SRTFScheduler
    // Public test 1
    try {
      input = "tests/srtf1.input";
      output = "tests/srtf1.output";
      scheduler = new SRTFScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Public test 2
    try {
      input = "tests/srtf2.input";
      output = "tests/srtf2.output";
      scheduler = new SRTFScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Public test 3
    try {
      input = "tests/srtf3.input";
      output = "tests/srtf3.output";
      scheduler = new SRTFScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Public test 4
    try {
      input = "tests/srtf4.input";
      output = "tests/srtf4.output";
      scheduler = new SRTFScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Public test 5
    try {
      input = "tests/srtf5.input";
      output = "tests/srtf5.output";
      scheduler = new SRTFScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Test PSScheduler
    // Public test 1
    try {
      input = "tests/ps1.input";
      output = "tests/ps1.output";
      scheduler = new PSScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }
    
    // Public test 2
    try {
      input = "tests/ps2.input";
      output = "tests/ps2.output";
      scheduler = new PSScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }
    
    // Public test 3
    try {
      input = "tests/ps3.input";
      output = "tests/ps3.output";
      scheduler = new PSScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Public test 4
    try {
      input = "tests/ps4.input";
      output = "tests/ps4.output";
      scheduler = new PSScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }

    // Public test 5
    try {
      input = "tests/ps5.input";
      output = "tests/ps5.output";
      scheduler = new PSScheduler();
      scheduler.schedule(input, output);
    } catch (Exception e) {
      // No exception should occur.
    }
  }
}
