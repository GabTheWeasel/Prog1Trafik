
/**
 * Main class for running a simulation
 * 
 * This class should not be changed in any way
 * 
 */
import java.util.Scanner;

public class Simulation {
  
  private static int time = 0;
  private static boolean simulationRun = false;
  
  /** 
   * Returns current simulation time 
   * @return current time step
   */
  public static int getTime() {
    return time;
  }
  
  /**
   * Sets the global time
   * Note: This method should ONLY be used by JUnit test programs
   * @param time new value for global time
   * @throws RuntimeException if called during simulation runs
   */
  public static void setTime(int time) {
    if (simulationRun) {
      throw new RuntimeException("stepTime may not be called in during simulation");
    }
    Simulation.time = time;
  }
  
  public static void main(String [] args) {
    simulationRun = true;
    Scanner sc = new Scanner(System.in);
    VehicleGenerator vg = new VehicleGenerator();
    TrafficSystem tf = new TrafficSystem(vg);
    try {
      // Start step by step in order to check correct behaviour
      for ( ;time<20; time++) {
        tf.step();
        tf.print();
        
        System.out.println("-----------------------------------");
        System.out.print("Press return to continue");
        sc.nextLine();
      }
      while (true) {
        for (int i=0; i<100; i++) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) { }
          time++;
          tf.step();
          tf.print();
          System.out.println("-----------------------------------");
        }
        tf.printStatistics();
        System.out.print("Continue (y/n)? ");
        String answer = sc.nextLine();
        if (answer.equals("n"))
          break;
      }
    } catch (RuntimeException re) {  // Take care of occured exceptions
      System.out.println("******* " + re.getMessage());
      re.printStackTrace();
      System.out.println();
      tf.printStatistics();
    }
  }
}




