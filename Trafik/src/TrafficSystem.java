import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Defines the components and behaviour of a specific traffic system
 * 
 */
public class TrafficSystem {
	
	/** 
	 * The lanes and lights used in the system
	 */
	private Lane r0;
	private Lane r1;
	private Lane r2;
	private Light s1;
	private Light s2;
	
	/** The vg. */
	private VehicleGenerator vg;
	
	/** The queue. */
	private ArrayList<Vehicle> queue = new ArrayList<Vehicle>();
	
	/**  
	 * The variables used for keeping track of time and cars for the statistics
	 */
	private int carsW = 0;
	private int carsWTimeMax = 0;
	private int carsWTimeTotal = 0;
	private int carsS = 0;
	private int carsSTimeMax = 0;
	private int carsSTimeTotal = 0;
	private int r1full = 0;
	private int r2full = 0;
	
  /**
   * Instantiates a new traffic system and sets it up by reading from the file settings.txt
   * which is found in the root folder. It will read through all of the integers there and 
   * pass them on or use them
   *
   * @param vg - The VehicleGenerator
   */
  public TrafficSystem(VehicleGenerator vg) {
	  /*The ArrayLists periods and intensity and the double turnIntensity are used for 
	  	changing how vg spawns cars. The ArrayList values is used for all of the other settings
	  */
	  ArrayList<Integer> values = new ArrayList<Integer>();
	  ArrayList<Integer> periods = new ArrayList<Integer>();
	  ArrayList<Double> intensity = new ArrayList<Double>();
	  double turnIntensity = 0;
	  this.vg = vg;
	  
	  //This try & catch reads from the file and assigns values
	  try {
		  Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("settings.txt"), "UTF-8"));
		  for(int a = 0; a < 7; a++){ 		//This for-loop reads the seven first integers found in settings.txt. 
			  scan.nextLine();				//They are formatted as follows:
			  values.add(scan.nextInt());	//name (Strictly for the user, as the program doesn't take note of these.
			  scan.nextLine();				//Value
			  scan.nextLine();				//Blank space
		  }
		  scan.nextLine();
		  turnIntensity = scan.nextDouble(); //Here it reads the turnIntensity as a double
		  scan.nextLine();
		  scan.nextLine();
		  scan.nextLine();
		  
		  while(scan.hasNextDouble()) {
			  intensity.add(scan.nextDouble()); //This while-loop will read the intensity-values
			  scan.nextLine();					//until it reaches the blank space
		  }
		  
		  scan.nextLine();
		  scan.nextLine();
		  
		  while(scan.hasNextInt()) {		//this loop reads the period values
			  periods.add(scan.nextInt());
			  scan.nextLine();
		  }
		  scan.close();
		  
		  //these change the settings of VG to the ones read from the file.
		  this.vg.setTurnIntensity(turnIntensity); 
		  this.vg.setPeriods(periods);
		  this.vg.setIntensity(intensity);
		  
		  
		  //Here the lanes are created with the lengths found in the file
		  this.r2 = new Lane(values.remove(0));
		  this.r1 = new Lane(values.remove(0));
		  this.r0 = new Lane(values.remove(0));
		  //Here the lights are created with the period times specified in the file
		  this.s1 = new Light(values.remove(0),values.remove(0));
		  this.s2 = new Light(values.remove(0),values.remove(0));
		  
		  
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
   * Prints currently collected statistics.
   */  
  public void printStatistics() {
	  System.out.println("r1 average time = " + ((double)carsWTimeTotal/carsW) + ", r1 max time =  " + carsWTimeMax + ", r1 blocked time = " + r1full );
	  System.out.println("r2 average time = " + ((double)carsSTimeTotal/carsS) + ", r2 max time =  " + carsSTimeMax + ", r2 blocked time = " + r2full );
  }
  
  /**
   * Prints the current situation using toString-methods in 
   * lights and lanes.
   */
  public void print() {
	  System.out.println(s1.toString() + r1.toString() + " " + r0.toString() + " Queue = [" + queue.size() + "]");
	  System.out.println(s2.toString() + r2.toString());
  }
  
}
