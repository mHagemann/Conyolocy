import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class HerdenkingsDienst {
	
	private final int MAX_AANTAL_BURGERS = 10;
	
	private	Lock lock;	
	private	Condition geenBurgers, wachtendeBurgers, wachtendeHoogwaardigheidsBekleders, geenBekleders;	
	
	private int aangemeldeBurgers = 0;
	private int aangemeldeBekleders = 0;
	
	private int burgersBinnen = 0;
	private int bekledersBinnen = 0;
	private boolean wachtendeBekleders = false;
	
	public HerdenkingsDienst() {
		
	 	lock = new ReentrantLock();
	 	geenBurgers = lock.newCondition();
	 	wachtendeBurgers = lock.newCondition();
	 	wachtendeHoogwaardigheidsBekleders = lock.newCondition();
	 	geenBekleders = lock.newCondition();
	 			
	}
	
	public void naarDienst(BezoekerType type) throws InterruptedException {
		lock.lock();
		try {
			
			switch (type) {
				case BURGER:
					
					aangemeldeBurgers++;
					System.out.println(((Burger) Thread.currentThread()).toString() + " heeft zich aangemeld.");
					
					while (bekledersBinnen() && (wachtendeBekleders)) {
						wachtendeBurgers.await();
					}
					
					aangemeldeBurgers--;
					System.out.println(((Burger) Thread.currentThread()).toString() + " gaat naar binnen.");
					burgersBinnen++;
				
					break;
					
				case HOOGWAARDIGHEIDSBEKLEDER:
					
					aangemeldeBekleders++;
					System.out.println(((Hoogwaardigheidsbekleder) Thread.currentThread()).toString() + " heeft zich aangemeld.");
					
					while (burgersBinnen()) {
						wachtendeHoogwaardigheidsBekleders.await();
					}
					
					aangemeldeBekleders--;
					System.out.println(((Hoogwaardigheidsbekleder) Thread.currentThread()).toString() + " gaat naar binnen.");
					bekledersBinnen++;
				
					break;
				
				case BEWAKER:
					
					System.out.println(((Bewaker) Thread.currentThread()).toString() + " gaat naar binnen.");
				
					break;

				default:
					break;
			}
			
		} finally {
			lock.unlock();
		}
		
	}
	
	public void verlaatDienst(BezoekerType type) throws InterruptedException {
		lock.lock();
		try {
			switch (type) {
				case BURGER:
					burgersBinnen--;
					System.out.println(((Burger) Thread.currentThread()).toString() + " heeft de dienst verlaten.");
					wachtendeBurgers.signal();
			
					break;
				case HOOGWAARDIGHEIDSBEKLEDER:
					bekledersBinnen--;
					System.out.println(((Hoogwaardigheidsbekleder) Thread.currentThread()).toString() + " heeft de dienst verlaten.");
			
					break;
			
				case BEWAKER:
					
					System.out.println(((Bewaker) Thread.currentThread()).toString() + " heeft de dienst verlaten.");
			
					break;

				default:
					break;
			}
			
			
		} finally {
			lock.unlock();
		}
		
	}
	
	//Returned true als er burgers binnen zijn
	private boolean burgersBinnen() {
		return (burgersBinnen != 0);
	}
	
	//Returned true als er hoogwaardigheidsbekleders binnen zijn
	private boolean bekledersBinnen() {
		return (bekledersBinnen != 0);
	}
	
	
}
