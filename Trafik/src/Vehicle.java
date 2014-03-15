/* Representerar fordon (födelsetid och destination) */
public class Vehicle {

	private char dest;
	private int bornTime;
	
	public Vehicle(char destination){
		
		this.dest = destination;
		this.bornTime = Simulation.getTime();
		
	}
	
	public char getDestination(){
		
		return this.dest;
		
	}
	
	public int getTime(){
		
		return bornTime;
		
	}
	
	public String toString(){
		
		return "Vehicle going " + dest  + " that was born at " + bornTime + ".";
		
	}
}
