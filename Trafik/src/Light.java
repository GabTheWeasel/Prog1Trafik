/**
 * Represents a traffic signal
 */
public class Light {
	
	private int p;	//The period for the light (green to green)
	private int gr;	//The amount of time the light is green
	private int clock;	//An integer containing the "time"
	
	/**
	 * Constructs and initializes a light
	 *
	 * @param period the period
	 * @param green the green
	 */
	public Light(int period, int green){
		
		this.p = period;
		this.gr = green;
		this.clock = 0;
		
	}
	
	/**
	 * Advances the internal clock by one
	 */
	public void step(){
		
		if(clock < p-1){
			clock++;
		}
		else{
			clock = 0;
		}
		
	}
	
	/**
	 * Checks if the light is green 
	 *
	 * @return true if the light is green else false
	 */
	public boolean isGreen(){
		
		if(clock < gr){
			return true;
		}
		
		return false;
		
	}
	
	
	/** 
	 * Returns a String representation of the light. (G) - Green or (R) - Red
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		if(isGreen()){
			return "(G)";
			//return "Green light with a period of " + p + " and a green period of " + gr + ".";
		}
		
		return "(R)";
		//return "Red light with a period of " + p + " and a green period of " + gr + ".";
	}

}
