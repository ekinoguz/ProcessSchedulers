/**
 * Interface to be implemented by each scheduler algorithm.
 */
public interface Scheduler {

  /**
   * Schedule the processes given in {@code inputFile} and write the results to
   * given {@code outputFile}.
   * @param inputFile
   * @param outputFile
   */
  public void schedule(String inputFile, String outputFile);
}
