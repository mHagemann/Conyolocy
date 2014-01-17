
public class Burger extends Thread {
	private int id;
	
	public Burger(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Burger" + id;
	}
	
	@Override
	public void run() {
		while (true) {
			
			justLive();
			
			
		}
	}
	
	private void justLive() {
		try {
			Thread.sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {
			
		}
	}
}
