import junit.framework.TestCase;

/**
 * A JUnit test case class.
 */
public class VehicleTest extends TestCase {
  

  public void testGetTimeAndDestination() {
    Simulation.setTime(2);
    Vehicle u = new Vehicle('W');
    assertEquals(2, u.getTime());
    assertEquals('W', u.getDestination());
    Simulation.setTime(3);
    Vehicle v = new Vehicle('S');
    assertEquals(3, v.getTime());
    assertEquals('S', v.getDestination());
  }
  
}
