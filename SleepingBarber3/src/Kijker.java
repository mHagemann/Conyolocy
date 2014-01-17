/*
 * Opdracht 3
 * 
 * Author: Jeroen Kurvink
 * 
 */

public class Kijker extends Thread {

	private Beurs beurs;

	public Kijker(String name, Beurs beurs) {
		super(name);
		this.beurs = beurs;
	}

	public void run() {
		while (true) {
			try {
				justLive();
				
				beurs.naarbeurs(TicketType.VISITOR);
				
				Thread.sleep((int) (Math.random() * 5000));
				
				beurs.verlaatbeurs(TicketType.VISITOR);
				
				Thread.sleep((int) (Math.random() * 5000));
				
			} catch (InterruptedException e) {
				//Todo
			}
		}
	}

	private void justLive() {
		try {
			System.out.println(getName() + " doet iets");
			Thread.sleep((int) (Math.random() * 5000));
		} catch (InterruptedException e) {
			//Todo
		}
	}
}
