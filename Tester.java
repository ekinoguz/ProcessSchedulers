
public class Tester {

  private final static long TIMEOUT = 2100000000; // in terms of nanotime.

  /** Runs the given scheduler. Timeout the scheduler if thread takes longer than it should. */
  @SuppressWarnings("deprecation")
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
        System.out.println(inputFile + " interrupted");
        thread.stop(); // stop the thread...
      }

      // Not to abuse busy-waiting.
      try {
        Thread.sleep(20);
      } catch (InterruptedException e) {
      }
    }
    System.out.println(inputFile + " execution finished after " +
        (System.nanoTime() - endTime + TIMEOUT)/1000000000.0);
  }

  public static void main(String[] args) {
    Scheduler scheduler;

    // Test FCFSScheduler
    scheduler = new FCFSScheduler();
    testScheduler(scheduler, "p-tests/fcfs1.input", "p-tests/fcfs1.output");
    testScheduler(scheduler, "p-tests/fcfs2.input", "p-tests/fcfs2.output");
    testScheduler(scheduler, "p-tests/fcfs3.input", "p-tests/fcfs3.output");
    testScheduler(scheduler, "p-tests/fcfs4.input", "p-tests/fcfs4.output");

    // Test SRTFScheduler
    scheduler = new SRTFScheduler();
    testScheduler(scheduler, "p-tests/srtf1.input", "p-tests/srtf1.output");
    testScheduler(scheduler, "p-tests/srtf2.input", "p-tests/srtf2.output");
    testScheduler(scheduler, "p-tests/srtf3.input", "p-tests/srtf3.output");
    testScheduler(scheduler, "p-tests/srtf4.input", "p-tests/srtf4.output");
    testScheduler(scheduler, "p-tests/srtf5.input", "p-tests/srtf5.output");

    // Test PSScheduler
    scheduler = new PSScheduler();
    testScheduler(scheduler, "p-tests/ps1.input", "p-tests/ps1.output");
    testScheduler(scheduler, "p-tests/ps2.input", "p-tests/ps2.output");
    testScheduler(scheduler, "p-tests/ps3.input", "p-tests/ps3.output");
    testScheduler(scheduler, "p-tests/ps4.input", "p-tests/ps4.output");
    testScheduler(scheduler, "p-tests/ps5.input", "p-tests/ps5.output");

    // Private Tests
    scheduler = new FCFSScheduler();
    testScheduler(scheduler, "p-tests/fcfs100.input", "p-tests/fcfs100.output");
    testScheduler(scheduler, "p-tests/fcfs101.input", "p-tests/fcfs101.output");
    testScheduler(scheduler, "p-tests/fcfs200.input", "p-tests/fcfs200.output");
    testScheduler(scheduler, "p-tests/fcfs900.input", "p-tests/fcfs900.output");
    testScheduler(scheduler, "p-tests/fcfs901.input", "p-tests/fcfs901.output");

    scheduler = new SRTFScheduler();
    testScheduler(scheduler, "p-tests/srtf100.input", "p-tests/srtf100.output");
    testScheduler(scheduler, "p-tests/srtf101.input", "p-tests/srtf101.output");
    testScheduler(scheduler, "p-tests/srtf200.input", "p-tests/srtf200.output");
    testScheduler(scheduler, "p-tests/srtf900.input", "p-tests/srtf900.output");
    testScheduler(scheduler, "p-tests/srtf901.input", "p-tests/srtf901.output");

    scheduler = new PSScheduler();
    testScheduler(scheduler, "p-tests/ps100.input", "p-tests/ps100.output"); // 100 random process, share 1
    testScheduler(scheduler, "p-tests/ps101.input", "p-tests/ps101.output"); // 9999 super long process, share 3
    testScheduler(scheduler, "p-tests/ps102.input", "p-tests/ps102.output"); // 4999 super long process, share 77
    testScheduler(scheduler, "p-tests/ps200.input", "p-tests/ps200.output"); // 2 super long process
    testScheduler(scheduler, "p-tests/ps900.input", "p-tests/ps900.output"); // Super long process ids
    testScheduler(scheduler, "p-tests/ps901.input", "p-tests/ps901.output"); // Shuffled super long process ids
  }
}
