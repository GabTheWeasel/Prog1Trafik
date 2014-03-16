/**
 * The Class Lane.
 */
public class Lane {
	/** The cars in the lane */
	Vehicle[] cars;
	
	/**
	 * Constructs a lane with a specified capacity 
	 *
	 * @param length - The length (capacity) of the lane in number of vehicles
	 */
	public Lane(int length){
		cars = new Vehicle[length];
	}
	
	/**
	 * Access method for the vehicle in the first position 
	 *
	 * @return A reference to the vehicle in the first position
	 */
	public Vehicle getFirst() {
		return cars[0];
	}
	
	/**
	 * Checks if the last position is free 
	 *
	 * @return true if the last position is free (null) else false
	 */
	public boolean lastFree(){
		if (cars[cars.length-1] == null){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Put a vehicle in the last position 
	 *
	 * @param v - Vehicle to be put in the last position
	 * @throws RuntimeException - if the last position is not free
	 */
	public void putLast(Vehicle v) {
		if (lastFree()) {
			cars[cars.length-1] = v;
		} else {
			throw new RuntimeException("Last spot is full!");
		}
	}
	
	/**
	 * Removes the first vehicle (index 0) from the lane and makes it empty 
	 *
	 * @return The removed vehicle or null if the position was empty
	 */
	public Vehicle removeFirst() {
		if (getFirst() != null) {
			Vehicle v = getFirst();
			cars[0] = null;
			return v;
		}
		return null;
	}
	
	/**
	 * A string representation of the lane  
	 * @see java.lang.Object#toString()
	 * @return The string representation
	 */
	public String toString() {
		String s = "<";
		for (int a = 0; a < cars.length; a++) {
			if (cars[a] != null) {
				s = s + cars[a].getDestination();
			} else {
				s = s + " ";
			}
		}
		
		s = s + ">";
		return s;
	}
	
	/**
	 * Advances all except the first vehicle one position provided the target position is free.
	 * The process starts in the low end (i. e. at index 1). 
	 */
	public void step() {
		for (int a = 1; a < cars.length; a++) {
			if (cars[a] != null && cars[a-1] == null) {
				cars[a-1] = cars[a];
				cars[a] = null;
			}
		}
	}
	
	

}
