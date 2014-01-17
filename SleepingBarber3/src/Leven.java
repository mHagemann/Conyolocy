/*
 * Opdracht 3
 * 
 * Author: Raymond Bakker & Jeroen Kurvink
 * 
 */
public class Leven {

	private Beurs beurs;
	
	private int nrkopers = 10, nrKijkers = 10;
	
	private Koper[] kopers = new Koper[nrkopers];
	private Kijker[] kijkers = new Kijker[nrKijkers];
	
	public Leven() {
		
		beurs = new Beurs();
		for(int b =0; b < nrkopers; b++) {
			kopers[b] = new Koper("Koper " + b, beurs);
			kopers[b].start();
		}
		for(int v = 0; v < nrKijkers; v++) {
			kijkers[v] = new Kijker("Kijker " + v, beurs);
			kijkers[v].start();
		}
	}
	
	
	public static void main(String[] args) {
		new Leven();
	}
}
