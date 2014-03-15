/* En trafiksignal */

public class Light {
	
	private int p;
	private int gr;
	private int clock;
	
	public Light(int period, int green){
		
		this.p = period;
		this.gr = green;
		this.clock = 0;
		
	}
	
	public void step(){
		
		if(clock < p-1){
			clock++;
		}
		else{
			clock = 0;
		}
		
	}
	
	public boolean isGreen(){
		
		if(clock < gr){
			return true;
		}
		
		return false;
		
	}
	
	public String toString(){
		
		if(isGreen()){
			return "(G)";
			//return "Green light with a period of " + p + " and a green period of " + gr + ".";
		}
		
		return "(R)";
		//return "Red light with a period of " + p + " and a green period of " + gr + ".";
	}

}
