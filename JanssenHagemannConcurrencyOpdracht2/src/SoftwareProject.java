import java.util.concurrent.Semaphore;

/**
 * 
 * @author Hagemann & Janssen
 *
 */
public class SoftwareProject {
	
	private static final int NR_OF_USERS = 5;
	private static final int NR_OF_DEVELOPERS = 7;
	private int developersAppliedForMeeting = 0;
	private int usersAppliedForMeeting = 0;
	
	private Semaphore meeting, invitation, applyForMeeting, atWork;
	private Semaphore mutex;
	
	private Thread[] users;
	private Thread[] developers;
	private ProjectLeader leader;
	
	public SoftwareProject() {
		users = new Thread[NR_OF_USERS];
		developers = new Thread[NR_OF_DEVELOPERS];
		
		for(int i = 0; i < NR_OF_USERS; i++) {
			users[i] = new User("user" + i);
			users[i].start();
		}
		
		for(int i = 0; i < NR_OF_DEVELOPERS; i++) {
			developers[i] = new SoftwareDeveloper("developer" + i);
			developers[i].start();
		}
		
		leader = new ProjectLeader("Klaas");
		leader.start();
	}
	
	/**
	 * Inner class voor de gebruiker
	 * @author Hagemann & Janssen
	 *
	 */
	class User extends Thread{
		
		private String name;
		
		public User(String name) {
			this.name = name;
		}

		public void run() {
			while (true) {			
				//TODO
			}
		}
		
	}
	
	/**
	 * Inner class voor de projectleider
	 * @author Hagemann & Janssen
	 *
	 */
	class ProjectLeader extends Thread {
		
		private String name;
		
		public ProjectLeader(String name) {
			this.name = name;
		}
		
		public void run() {
			while (true) {
				
				
				meet();
				
				
			}
		}
		
		//De meeting
		public void meet() {
			try {
				System.out.println("Projectleader is in a meeting");
				Thread.sleep((int)(Math.random() * 1000));
			} catch (InterruptedException e) {}
		}
	}
	
	/**
	 * Innerclass voor de softwareDevelopers
	 * @author Hagemann & Janssen
	 *
	 */
	class SoftwareDeveloper extends Thread {

		private String name;
		
		public SoftwareDeveloper(String name) {
			this.name = name;
		}
		
		public void run() {
			while (true) {			
				//TODO
			}
		}
	}

}
