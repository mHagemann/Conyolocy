
public class Bewaker extends Thread {
	private int id;
	private HerdenkingsDienst dienst;
	
	public Bewaker(int id, HerdenkingsDienst dienst) {
		this.id = id;
		this.dienst = dienst;
	}
	
	public String toString() {
		return "Bewaker" + id;
	}

	@Override
	public void run() {
		while (true) {
			try {
				justLive();
				dienst.naarDienst(BezoekerType.BEWAKER);
				respectBetuigen();
				dienst.verlaatDienst(BezoekerType.BEWAKER);
				
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
