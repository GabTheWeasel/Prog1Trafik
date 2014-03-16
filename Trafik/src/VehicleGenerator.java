/**
 * Generates Vehicle with (different) intesity.
 */
import java.util.ArrayList;

public class VehicleGenerator {
  private ArrayList<Integer> periods;
  private ArrayList<Double> intensity;
  private double turnIntensity;
  
  private int time;   // internal clock
  private int index;  // current period
  private int period; // total time period
  
  /**
   * Hard coded intensities
   */
  public VehicleGenerator() {
    periods = new ArrayList<Integer>();
    intensity = new ArrayList<Double>();
    periods.add(30); intensity.add(0.2);
    periods.add(40); intensity.add(0.7);
    periods.add(50); intensity.add(0.9);
    periods.add(60); intensity.add(0.7);
    turnIntensity = 0.6;
    period = periods.get(periods.size()-1);
    time = 0;
    index = 0;
  }
  
  public void setPeriods(ArrayList<Integer> a) {
	  this.periods = a;
	  period = periods.get(periods.size()-1);
  }

  public void setIntensity(ArrayList<Double> a) {
	  this.intensity = a;
  }

  public void setTurnIntensity(double a) {
	  this.turnIntensity = a;
  }  
  /**
   * Generate a vehicle
   * @return A vehicle or null
   */
  public Vehicle step() {
    time = time+1;
    if (time>=period) {
       time = 0;
       index = 0;
    } 
    
    if (time>=periods.get(index)) {
      index++;
    }
    
    Vehicle retur = null;
    double prob = intensity.get(index);
    
    if (Math.random()<prob) {
      if (Math.random()<turnIntensity) {
        retur = new Vehicle('S');
      } else {
        retur = new Vehicle('W');
      }
    }
    return retur;
  }
  
  
  public String toString() {
     return "< " + time + ", " + index + ", " + 
       periods.get(index) + ", " + intensity.get(index) + ">";  
  }
  
  /**
   * Test program for the VehicleGenerator
   */
  public static void main(String[] args) {
    VehicleGenerator vg = new VehicleGenerator();
    for (int i= 0; i<65; i++) {
      System.out.print(vg);
      Vehicle v = vg.step();
      if (v!=null) {
        System.out.print("  Vehicle out: <" + 
                           v.getDestination() + ", " + v.getTime() + ">" );
      }
      System.out.println();
    }
    
  }
}