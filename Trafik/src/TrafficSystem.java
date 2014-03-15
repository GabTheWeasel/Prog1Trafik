/**
 * Defines the components and behaviour of s specific traffic system
 * bleh
 */

import java.util.ArrayList;

public class TrafficSystem {
	private Lane r0;
	private Lane r1;
	private Lane r2;
	private Light s1;
	private Light s2;
	private VehicleGenerator vg;
	private ArrayList<Vehicle> queue = new ArrayList<Vehicle>();
	private int carsW = 0;
	private int carsWTimeMax = 0;
	private int carsWTimeTotal = 0;
	private int carsS = 0;
	private int carsSTimeMax = 0;
	private int carsSTimeTotal = 0;
	private int r1full = 0;
	private int r2full = 0;
	
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
	  if (s1.isGreen() && r1.getFirst() != null){
		  carsW++;
		  carsWTimeTotal = carsWTimeTotal + (Simulation.getTime()-r1.getFirst().getTime());
		  if (carsWTimeMax < (Simulation.getTime()-r1.getFirst().getTime())) {
			  carsWTimeMax = (Simulation.getTime()-r1.getFirst().getTime());
		  }
		  r1.removeFirst();
	  }
	  if (s2.isGreen() && r2.getFirst() != null) {
		  carsS++;
		  carsSTimeTotal = carsSTimeTotal + (Simulation.getTime()-r2.getFirst().getTime());
		  if (carsSTimeMax < (Simulation.getTime()-r2.getFirst().getTime())) {
			  carsSTimeMax = (Simulation.getTime()-r2.getFirst().getTime());
		  }
		  r2.removeFirst();
	  }
	  r1.step();
	  r2.step();
	  
	  if(r0.getFirst() != null) {
		  if (r0.getFirst().getDestination() == 'S') {
			  if (r2.lastFree()){
			  	r2.putLast(r0.removeFirst());
			  } else {
				  r2full++;
			  }
		  } else if (r0.getFirst().getDestination() == 'W') {
			  if (r1.lastFree()){
				  	r1.putLast(r0.removeFirst());
			  } else {
					  r1full++;
			  }
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
	  System.out.println("r1 average time = " + ((double)carsWTimeTotal/carsW) + ", r1 max time =  " + carsWTimeMax + ", r1 blocked time = " + r1full );
	  System.out.println("r2 average time = " + ((double)carsSTimeTotal/carsS) + ", r2 max time =  " + carsSTimeMax + ", r2 blocked time = " + r2full );
  }
  
  /**
   * Prints the current situation using toString-methods in 
   * lights and lanes
   */
  public void print() {
	  System.out.println(s1.toString() + r1.toString() + " " + r0.toString() + " Queue = [" + queue.size() + "]");
	  System.out.println(s2.toString() + r2.toString());
  }
  
}
