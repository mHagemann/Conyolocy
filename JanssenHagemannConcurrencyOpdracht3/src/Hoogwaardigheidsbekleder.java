
public class Hoogwaardigheidsbekleder extends Thread {
	private int id;
	
	public Hoogwaardigheidsbekleder(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Hoogwaardigheidsbekleder" + id;
	}
}