import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class HerdenkingsDienst {
	
	private	Lock lock;	
	private	Condition geenBurgers, wachtendeBurgers, wachtendeBekleders, geenBekleders;
	
	private int aantalBurgers = 20;
	private int aantalHoogwaardigheidsBekleders = 10;
	private int aantalBewakers = 5;
	
	private int aangemeldeBurgers, aangemeldeBekleders;
	
	public HerdenkingsDienst() {
		
	 	lock = new ReentrantLock();
	 	geenBurgers = lock.newCondition();
	 	wachtendeBurgers = lock.newCondition();
	 	wachtendeBekleders = lock.newCondition();
	 	geenBekleders = lock.newCondition();
	 	
		for(int i = 0; i < aantalBurgers; i++) {
			new Burger(i).start();
		}
		for(int i = 0; i < aantalBewakers; i++) {
			new Bewaker(i).start();
		}
		for(int i = 0; i < aantalHoogwaardigheidsBekleders; i++) {
			new Hoogwaardigheidsbekleder(i).start();
		}
		
	}
	
	public void naarDienst(BezoekerType type) throws InterruptedException {
		lock.lock();
		try {
			
			switch (type) {
				case BURGER:
				
					aangemeldeBurgers++;
				
					break;
				case HOOGWAARDIGHEIDSBEKLEDER:
				
					break;
				
				case BEWAKER:
				
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
			
			
			
		} finally {
			lock.unlock();
		}
		
	}
	
	
}
