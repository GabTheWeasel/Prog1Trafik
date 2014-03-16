import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
   * Instantiates a new traffic system and sets it up by reading from the file 
   * settings.txt which is found in the root folder. It will read through all of
   * the integers there and pass them on or use them
   *
   * @param vg - The VehicleGenerator
   */
  public TrafficSystem(VehicleGenerator vg) {
	  /*The ArrayLists periods and intensity and the double turnIntensity are 
	   used for changing how vg spawns cars. The ArrayList values is used for
	   all of the other settings*/
	  ArrayList<Integer> values = new ArrayList<Integer>();
	  ArrayList<Integer> periods = new ArrayList<Integer>();
	  ArrayList<Double> intensity = new ArrayList<Double>();
	  double turnIntensity = 0;
	  this.vg = vg;
	  
	  //This try & catch reads from the file and assigns values
	  try {
		  Scanner scan = new Scanner(new InputStreamReader(
			  new FileInputStream("settings.txt"), "UTF-8"));
		/*This for-loop reads the seven
		first integers found in settings.txt. 
		They are formatted as follows:
		name (Strictly for the user, as the program doesn't take note of these.
		value
		Blank line*/
		  for (int a = 0; a < 7; a++) {
			  scan.nextLine();
			  values.add(scan.nextInt());
			  scan.nextLine();
			  scan.nextLine();
		  }
		  scan.nextLine();
		  turnIntensity = scan.nextDouble(); //Here it reads the turnIntensity
		  scan.nextLine();					//as a double
		  scan.nextLine();
		  scan.nextLine();
		  
		  while (scan.hasNextDouble()) {
			  intensity.add(scan.nextDouble()); //This while-loop will read the
			  scan.nextLine();					//intensity-valuesuntil it 
		  }										//reaches the blank line
		  
		  scan.nextLine();
		  scan.nextLine();
		  
		  while (scan.hasNextInt()) {		//this loop reads the period values
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
		  this.s1 = new Light(values.remove(0), values.remove(0));
		  this.s2 = new Light(values.remove(0), values.remove(0));
		  
		  
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InputMismatchException e) {
		e.printStackTrace();
	}
  }
  
  
  /**
   * Advances the whole traffic system one timestep. Makes use
   * of components step methods
   */
  public void step() {
	  //First of all, the lights are stepped
	  s1.step();
	  s2.step();
	  
	  //Then, the lanes are checked to see if there is a car in the first position, 
	  //and if the light is green. if so, the car is removed (drives off the road)
	  if (s1.isGreen() && r1.getFirst() != null) {
		  carsW++; //keeps track of the amount of cars that has passed
		  
		//Tracks the amount of time spent going through the road
		  carsWTimeTotal = carsWTimeTotal + 
				  (Simulation.getTime()-r1.getFirst().getTime()); 
		  
		//Asserts if the car was the slowest car through or not
		  if (carsWTimeMax < (Simulation.getTime()-r1.getFirst().getTime())) {
			//If so, the maximum is set to it
			  carsWTimeMax = (Simulation.getTime()-r1.getFirst().getTime());
		  }
		  r1.removeFirst(); 	// And the car is removed
	  }
	  if (s2.isGreen() && r2.getFirst() != null) {	//the same as previous block
		  carsS++;									//but for this lane
		  carsSTimeTotal = carsSTimeTotal + (Simulation.getTime()-
				  r2.getFirst().getTime());
		  if (carsSTimeMax < (Simulation.getTime()-r2.getFirst().getTime())) {
			  carsSTimeMax = (Simulation.getTime()-r2.getFirst().getTime());
		  }
		  r2.removeFirst();
	  }
	  
	  r1.step();	//steps lane r1
	  r2.step();	//steps lane r2
	  
	  /*The following block checks to see if there is a car at the front of r0
		and checks which direction it's going*/
	  if (r0.getFirst() != null) {	
		  if (r0.getFirst().getDestination() == 'S') {
			  //then it determines if it can go and moves it
			  if (r2.lastFree()) {
			  	r2.putLast(r0.removeFirst());
			  } else {
				  r2full++;
				  //if it was full, it adds to the full-counter for the lane
			  }
		  } else if (r0.getFirst().getDestination() == 'W') {
			  if (r1.lastFree()) {
				  	r1.putLast(r0.removeFirst());
			  } else {
					  r1full++;
			  }
		  }
	  }
	  
	  r0.step();	//steps r0
	  
	  //stores vg's step and determines if it had a car. If so, the car is added
	  //to the queue
	  Vehicle temp = vg.step();
	  if (temp != null) {
		  queue.add(temp);
	  }
	  
	  //If there is a car in the queue and r0's last spot is empty, it adds the
	  //car to the queue
	  if (queue.size() > 0 && r0.lastFree()) {
		  r0.putLast(queue.remove(0));
	  }
  }
  
  /**
   * Prints currently collected statistics.
   */  
  public void printStatistics() {
	System.out.println("r1 average time = " + ((double)carsWTimeTotal/carsW) +
			", r1 max time =  " + carsWTimeMax + ", r1 blocked time = " 
			+ r1full );
	System.out.println("r2 average time = " + ((double)carsSTimeTotal/carsS) +
			", r2 max time =  " + carsSTimeMax + ", r2 blocked time = " 
			+ r2full );
  }
  
  /**
   * Prints the current situation using toString-methods in 
   * lights and lanes.
   */
  public void print() {
	  System.out.println(s1.toString() + r1.toString() + " " + r0.toString() + 
			  " Queue = [" + queue.size() + "]");
	  System.out.println(s2.toString() + r2.toString());
  }
}
