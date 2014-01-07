
public class Burger extends Thread {
	private int id;
	
	public Burger(int id) {
		this.id = id;
	}
	public String toString() {
		return "Burger" + id;
	}
}
