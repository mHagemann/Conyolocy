
public class Bewaker extends Thread {
	private int id;
	
	public Bewaker(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Bewaker" + id;
	}

}
