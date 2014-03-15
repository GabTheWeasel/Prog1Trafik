import junit.framework.TestCase;

/**
 * A JUnit test case class.
 */
public class LaneTest extends TestCase {
  protected Lane aLane;
  
  protected void setUp() {
     aLane = new Lane(3);
  }

  public void testLane() {
    assertEquals(null,aLane.getFirst());
    assertTrue(aLane.lastFree());
    assertEquals(null,aLane.removeFirst());
    aLane.step();
    assertEquals(null,aLane.getFirst());
    assertTrue(aLane.lastFree());
    assertEquals(null,aLane.removeFirst());
    aLane.putLast(new Vehicle('X'));
    assertFalse(aLane.lastFree());
    aLane.step();
    assertTrue(aLane.lastFree());
    assertEquals(null,aLane.getFirst());
    aLane.step();
    assertTrue(aLane.lastFree());
    assertFalse(null==aLane.getFirst());
    assertFalse(null==aLane.removeFirst());
    assertTrue(null==aLane.removeFirst());
  }
  

  public void testException() {
    aLane.putLast(new Vehicle('Y'));
    try {
      aLane.putLast(new Vehicle('Z'));
    } catch (RuntimeException re) {
      return; 
    }
    fail("Expected RuntimeException at putLast if occupied place");
  }
  
}
