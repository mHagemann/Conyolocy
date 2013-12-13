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
	
	private Semaphore meeting, invitation, applyForUserMeeting, applyForDeveloperMeeting;
	private Semaphore mutex;
	
	private Thread[] users;
	private Thread[] developers;
	private ProjectLeader leader;
	
	public SoftwareProject() {		
		users = new Thread[NR_OF_USERS];
		developers = new Thread[NR_OF_DEVELOPERS];
		
		meeting = new Semaphore(1, true); 
		invitation = new Semaphore(0, true);
		applyForDeveloperMeeting = new Semaphore(0, true);
		applyForUserMeeting = new Semaphore(0, true);
		mutex = new Semaphore(1);
		
		leader = new ProjectLeader("Klaas");
		leader.start();
		
		for(int i = 0; i < NR_OF_USERS; i++) {
			users[i] = new User("user" + i);
			users[i].start();
		}
		
		for(int i = 0; i < NR_OF_DEVELOPERS; i++) {
			developers[i] = new SoftwareDeveloper("developer" + i);
			developers[i].start();
		}
		
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
				try {
					justLive();
					applyForUserMeeting.release();
					System.out.println(name + " has applied for a meeting.");
									
					mutex.acquire();
					usersAppliedForMeeting++;
					mutex.release();
					
					invitation.acquire();
					System.out.println(name + " has acquired an invitation for a meeting.");
					
					mutex.acquire();
					usersAppliedForMeeting--;
					mutex.release();
					
				} catch(InterruptedException e){
					Thread.currentThread().interrupt();
				}
			}
		}
		
		// Gebruiker leeft
		private void justLive() {
			try {
				System.out.println(name + " is just living");
				Thread.sleep((int)(Math.random() * 10000));
			} catch (InterruptedException e) {}
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
				try {
					applyForDeveloperMeeting.acquire(3);;
					invitation.release(3);
					meeting.acquire();

					developerMeeting();
					
					
				} catch(InterruptedException e){
					Thread.currentThread().interrupt();
				}
				
				meeting.release();
				System.out.println("Projectleader is no longer in a meeting.");
			}
		}
		
		//De developer meeting
		private void developerMeeting() {
			try {
				System.out.println("Projectleader is in a developer meeting");
				Thread.sleep((int)(Math.random() * 1000));
			} catch (InterruptedException e) {}
		}
		
		//De user meeting
		private void userMeeting() {
			try {
				System.out.println("Projectleader is in a user meeting");
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
				try {
					work();
					applyForDeveloperMeeting.release();
					System.out.println(name + " has applied for a meeting.");
					
					mutex.acquire();
					developersAppliedForMeeting++;
					mutex.release();
					
					invitation.acquire();
					System.out.println(name + " has acquired an invitation for a meeting.");

					
					mutex.acquire();
					developersAppliedForMeeting--;
					mutex.release();
					
					
				} catch(InterruptedException e) {
					Thread.currentThread().interrupt();
				}

			}
		}
		
		// Developer werkt
		private void work() {
			try {
				System.out.println(name + " is working");
				Thread.sleep((int)(Math.random() * 10000));
			} catch (InterruptedException e) {}
		}
	}

}
