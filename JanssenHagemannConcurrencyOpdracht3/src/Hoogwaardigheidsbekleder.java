
public class Hoogwaardigheidsbekleder extends Thread {
	private int id;
	private HerdenkingsDienst dienst;
	
	public Hoogwaardigheidsbekleder(int id, HerdenkingsDienst dienst) {
		this.id = id;
		this.dienst = dienst;
	}
	
	public String toString() {
		return "Hoogwaardigheidsbekleder" + id;
	}
	
	@Override
	public void run() {
		
		while (true) {
			try {
				justLive();
				dienst.naarDienst(BezoekerType.HOOGWAARDIGHEIDSBEKLEDER);
				respectBetuigen();
				dienst.verlaatDienst(BezoekerType.HOOGWAARDIGHEIDSBEKLEDER);
				
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
