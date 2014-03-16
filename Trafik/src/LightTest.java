import junit.framework.TestCase;

/**
 * A JUnit test case class.
 */
public class LightTest extends TestCase {
  
  public void testLight() {
    Light s = new Light(5,2);
    assertTrue(s.isGreen()); 
    s.step();
    assertTrue(s.isGreen()); 
    s.step();
    assertFalse(s.isGreen()); 
    s.step();
    assertFalse(s.isGreen()); 
    s.step();
    assertFalse(s.isGreen()); 
    s.step();
    assertTrue(s.isGreen()); 
  }
  
}
