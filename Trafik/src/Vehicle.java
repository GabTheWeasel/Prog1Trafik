/* Represents a vehicle (Birthtime and destination) */
public class Vehicle {

	private char dest;
	private int bornTime;
	
	/* Constructs a vehicle */
	public Vehicle(char destination){
		
		this.dest = destination;
		this.bornTime = Simulation.getTime();
		
	}
	
	/* Returns the vehicles destination */ 
	public char getDestination(){
		
		return this.dest;
		
	}
	
	/* Returns the birthtime of the vehicle */
	public int getTime(){
		
		return bornTime;
		
	}
	
	/* Returns a string representation of the object */
	public String toString(){
		
		return "Vehicle going " + dest  + " that was born at " + bornTime + ".";
		
	}
}
