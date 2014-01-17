import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class HerdenkingsDienst {
	
	private	Lock lock;	
	private	Condition condition;
	
	private int aantalBurgers = 20;
	private int aantalHoogwaardigheidsBekleders = 10;
	private int aantalBewakers = 5;
	
	public HerdenkingsDienst() {
		
	 	lock = new ReentrantLock();
		condition = lock.newCondition();
	 	
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
	
	
	
	
}
