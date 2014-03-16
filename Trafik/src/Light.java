/* Represents a traffic signal */

public class Light {
	
	private int p;
	private int gr;
	private int clock;
	
	/* Constructs and initializes a light */
	public Light(int period, int green){
		
		this.p = period;
		this.gr = green;
		this.clock = 0;
		
	}
	
	/* Advances the internal clock by one */
	public void step(){
		
		if(clock < p-1){
			clock++;
		}
		else{
			clock = 0;
		}
		
	}
	
	/* Checks if the light is green or not */ 
	public boolean isGreen(){
		
		if(clock < gr){
			return true;
		}
		
		return false;
		
	}
	
	
	/* Returns a String representation of the light. (G) - Green or (R) - Red */ 
	public String toString(){
		
		if(isGreen()){
			return "(G)";
			//return "Green light with a period of " + p + " and a green period of " + gr + ".";
		}
		
		return "(R)";
		//return "Red light with a period of " + p + " and a green period of " + gr + ".";
	}

}
