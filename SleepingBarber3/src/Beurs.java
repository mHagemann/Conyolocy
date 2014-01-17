import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Opdracht 3
 * 
 * Author: Raymond Bakker & Jeroen Kurvink
 * 
 */

public class Beurs {

	private Condition koperlijn, kijkerlijn, bothEntry;
	private int nrKijkersIn, nrkopersLijn, nrKijkersLijn, consecutiveBuyer;
	private boolean buyerEntered;
	Lock lock;
	
	public Beurs() {
		
		lock = new ReentrantLock();
		
		koperlijn = lock.newCondition();
		kijkerlijn = lock.newCondition();
		bothEntry = lock.newCondition();
	}

	public void naarbeurs(TicketType ticketType) throws InterruptedException {
		lock.lock();

		try {
			switch (ticketType) {
			case BUYER:
				
				nrkopersLijn++;
				System.out.println(((Koper) Thread.currentThread()).getName() + " wacht in rij");
				while (buyerNotAllowed()) {
					koperlijn.await();
				}
				System.out.println(((Koper) Thread.currentThread()).getName() + " gaat naar beurs");
				buyerEntered = true;
				nrkopersLijn--;
				
				if(consecutiveBuyer == 3) {
					consecutiveBuyer = 0;
				}
				bothEntry.signal();
				break;
			case VISITOR:
				while(VisitorNotAllowed()) {
					bothEntry.await();
				}
				System.out.println(((Kijker) Thread.currentThread()).getName() + " wacht in rij");
				nrKijkersLijn++;
				
				while (beursVol()) {
					kijkerlijn.await();
				}
				System.out.println(((Kijker) Thread.currentThread()).getName() + " gaat naar beurs");
				nrKijkersLijn--;
				nrKijkersIn++;
				break;

			default:
				break;
			}
		} finally {
			lock.unlock();
		}
	}

	public void calculateEntry() {

	}

	public void verlaatbeurs(TicketType ticketType)
			throws InterruptedException {
		lock.lock();

		try {

			switch (ticketType) {
			case BUYER:
				consecutiveBuyer += 1;
				buyerEntered = false;
				kijkerlijn.signalAll();
				koperlijn.signalAll();
				break;
			case VISITOR:
				System.out.println(((Kijker) Thread.currentThread()).getName() + " beurs verlaten");
				nrKijkersIn--;
				koperlijn.signal();
				break;
			default:
				break;
			}
		} finally {
			lock.unlock();
		}
	}

	private boolean VisitorNotAllowed() {
		return (consecutiveBuyer == 3 && nrkopersLijn > 0);
	}

	private boolean buyerNotAllowed() {
		return (buyerEntered || nrKijkersIn > 0 || (consecutiveBuyer == 3 && nrKijkersLijn > 0));
	}
	
	private boolean beursVol() {
		return (buyerEntered || (nrkopersLijn > 0 && consecutiveBuyer < 3));
	}

}
