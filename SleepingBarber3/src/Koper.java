/*
 * Opdracht 3
 * 
 * Author: Raymond Bakker
 * 
 */
public class Koper extends Thread {

	private Beurs beurs;

	public Koper(String name, Beurs beurs) {
		super(name);
		this.beurs = beurs;
	}

	public void run() {
		while (true) {
			try {
				justLive();
				beurs.naarbeurs(TicketType.BUYER);
				Thread.sleep((int) (Math.random() * 5000));
				beurs.verlaatbeurs(TicketType.BUYER);
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
