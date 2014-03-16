/**
 * Represents a vehicle (Birthtime and destination)
 */
public class Vehicle {

	/** The destination */
	private char dest;
	/** The born time. */
	private int bornTime;
	
	/**
	 * Constructs a vehicle
	 *
	 * @param destination the destination ( W or S)
	 */
	public Vehicle(char destination) {
		
		this.dest = destination;
		this.bornTime = Simulation.getTime();
		
	}
	
	/**
	 * Returns the vehicles destination
	 *
	 * @return the destination
	 */
	public char getDestination() {
		
		return this.dest;
		
	}
	
	/**
	 * Returns the birthtime of the vehicle
	 *
	 * @return the time the car was created
	 */
	public int getTime() {
		
		return bornTime;
		
	}
	
	/** Returns a string representation of the object
	 *  
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		return "Vehicle going " + dest  + " that was born at " + bornTime + ".";
		
	}
}
