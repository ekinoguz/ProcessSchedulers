
public class Tester {

  private final static long TIMEOUT = 2100000000; // in terms of nanotime.

  /** Runs the given scheduler. Timeout the scheduler if thread takes longer than it should. */
  private static void testScheduler(final Scheduler scheduler, final String inputFile,
      final String outputFile) {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          scheduler.schedule(inputFile, outputFile);
        } catch (Exception e) {
          System.out.println("Exception occurred:");
          e.printStackTrace();
        }
      }
    });

    thread.start();
    long endTime = System.nanoTime() + TIMEOUT;
    while (thread.isAlive() && !thread.isInterrupted()) {
      long currentTime = System.nanoTime();
      if (currentTime > endTime) {
        thread.interrupt();
        System.out.println("Interrupted");
      }

      // Not to abuse busy-waiting.
      try {
        Thread.sleep(20);
      } catch (InterruptedException e) {
      }
    }
    System.out.println("Execution finished after " + (System.nanoTime() - endTime + TIMEOUT)/1000000000.0);
  }

  public static void main(String[] args) {
    Scheduler scheduler;

    // Test FCFSScheduler
    scheduler = new FCFSScheduler();
    testScheduler(scheduler, "tests/fcfs1.input", "tests/fcfs1.output");

    scheduler = new FCFSScheduler();
    testScheduler(scheduler, "tests/fcfs2.input", "tests/fcfs2.output");

    scheduler = new FCFSScheduler();
    testScheduler(scheduler, "tests/fcfs3.input", "tests/fcfs3.output");

    scheduler = new FCFSScheduler();
    testScheduler(scheduler, "tests/fcfs4.input", "tests/fcfs4.output");

    // Test SRTFScheduler
    scheduler = new SRTFScheduler();
    testScheduler(scheduler, "tests/srtf1.input", "tests/srtf1.output");
    
    scheduler = new SRTFScheduler();
    testScheduler(scheduler, "tests/srtf2.input", "tests/srtf2.output");
    
    scheduler = new SRTFScheduler();
    testScheduler(scheduler, "tests/srtf3.input", "tests/srtf3.output");
    
    scheduler = new SRTFScheduler();
    testScheduler(scheduler, "tests/srtf4.input", "tests/srtf4.output");
    
    scheduler = new SRTFScheduler();
    testScheduler(scheduler, "tests/srtf5.input", "tests/srtf5.output");

    // Test PSScheduler
    scheduler = new PSScheduler();
    testScheduler(scheduler, "tests/ps1.input", "tests/ps1.output");

    scheduler = new PSScheduler();
    testScheduler(scheduler, "tests/ps2.input", "tests/ps2.output");

    scheduler = new PSScheduler();
    testScheduler(scheduler, "tests/ps3.input", "tests/ps3.output");

    scheduler = new PSScheduler();
    testScheduler(scheduler, "tests/ps4.input", "tests/ps4.output");

    scheduler = new PSScheduler();
    testScheduler(scheduler, "tests/ps5.input", "tests/ps5.output");
  }
}
