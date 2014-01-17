
public class Burger extends Thread {
	private int id;
	private HerdenkingsDienst dienst;
	
	public Burger(int id, HerdenkingsDienst dienst) {
		this.id = id;
		this.dienst = dienst;
	}
	
	public String toString() {
		return "Burger" + id;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				justLive();
				dienst.naarDienst(BezoekerType.BURGER);
				respectBetuigen();
				dienst.verlaatDienst(BezoekerType.BURGER);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void justLive() {
		try {
			Thread.sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {
			
		}
	}
	
	private void respectBetuigen() {
		try {
			Thread.sleep((int) (Math.random() * 2000));
		} catch (InterruptedException e) {
			
		}
	}
	
	
}
