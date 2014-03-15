/**
 * Defines the components and behaviour of s specific traffic system
 * bleh
 */

import java.util.ArrayList;

public class TrafficSystem {
	Lane r0;
	Lane r1;
	Lane r2;
	Light s1;
	Light s2;
	VehicleGenerator vg;
	ArrayList<Vehicle> queue = new ArrayList<Vehicle>();
 
  public TrafficSystem(VehicleGenerator vg) {
	  this.vg = vg;
	  this.r0 = new Lane(9);
	  this.r1 = new Lane(7);
	  this.r2 = new Lane(7);
	  
	  this.s1 = new Light(5,2);
	  this.s2 = new Light(5,2);
	  
	  
  }
  
  
  /**
   * Advances the whole traffic system one timestep. Makes use
   * of components step methods
   */
  public void step() {
	  s1.step();
	  s2.step();
	  if (s1.isGreen()){
		  r1.removeFirst();
	  }
	  if (s2.isGreen()) {
		 r2.removeFirst(); 
	  }
	  r1.step();
	  r2.step();
	  
	  if(r0.getFirst() != null) {
		  if (r0.getFirst().getDestination() == 'S' && r2.lastFree()) {
			  r2.putLast(r0.removeFirst());
		  } else if (r0.getFirst().getDestination() == 'W' && r1.lastFree()) {
			  r1.putLast(r0.removeFirst());
		  }
	  }
	  r0.step();
	  Vehicle temp = vg.step();
	  if (temp != null) {
		  queue.add(temp);
	  }
	  
	  if(queue.size() > 0 && r0.lastFree()){
		  r0.putLast(queue.remove(0));
	  }
  }
  
  /**
   * Prints currently collected statistics
   */  
  public void printStatistics() {

  }
  
  /**
   * Prints the current situation using toString-methods in 
   * lights and lanes
   */
  public void print() {
 
  }
  
}
