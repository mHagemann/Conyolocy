
public class ProjectLeader extends Thread {
	
	private String name;
	
	public ProjectLeader(String name) {
		this.name = name;
	}
	
	public void run() {
		while (true) {			
			meet();
		}
	}
	
	public void meet() {
		try {
			System.out.println("Projectleader is in a meeting");
			Thread.sleep((int)(Math.random() * 1000));
		} catch (InterruptedException e) {}
	}
}
