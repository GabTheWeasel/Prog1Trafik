/* En fil */ 
public class Lane {
	Vehicle[] cars;
	
	public Lane(int length){
		cars = new Vehicle[length];
	}
	
	public Vehicle getFirst() {
		return cars[0];
	}
	
	public boolean lastFree(){
		if (cars[cars.length-1] == null){
			return true;
		} else {
			return false;
		}
	}
	
	public void putLast(Vehicle v) {
		if (lastFree()) {
			cars[cars.length-1] = v;
		} else {
			throw new RuntimeException("Last spot is full!");
		}
	}
	
	public Vehicle removeFirst() {
		if (getFirst() != null) {
			Vehicle v = getFirst();
			cars[0] = null;
			return v;
		}
		return null;
	}
	
	public void step() {
		for (int a = 1; a < cars.length; a++) {
			if (cars[a] != null && cars[a-1] == null) {
				cars[a-1] = cars[a];
				cars[a] = null;
			}
		}
	}
	
	

}
