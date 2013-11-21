import java.lang.reflect.Array;
import java.util.ArrayList;

public class Apl {

	private static int[] heleGetallen;
	private static ArrayList<Integer> gesorteerdeLijst = new ArrayList<Integer>();
	private static int aantalGetallen = 10000;
	public static void main(String[] args) {

		heleGetallen = new int[aantalGetallen];
		vulArray();
		
		// start de tuntime stopwatch
		long startTime = System.currentTimeMillis();
		
		opdracht1(heleGetallen);

		// stop en print de runtime
		long endTime = System.currentTimeMillis();
		
//		for (int i = 0; i < heleGetallen.length; i++) {
//			System.out.print(heleGetallen[i] + ", ");
//		}
		
		System.out.println("\nSorting took " + (endTime - startTime) + " ms");
	}

	/**
	 * Methode voor het vullen van de array met willekeurige hele getallen.
	 */
	public static void vulArray() {
		for (int i = 0; i < aantalGetallen; i++) {

			// random getal tussen 0 en 100
			int getal = randInt(0, 100);

			heleGetallen[i] = getal;
			//System.out.println("Getal: " + heleGetallen[i]);
		}
	}

	/**
	 * Methode voor het genereren van een radom getal tussen twee grenzen.
	 * @param min - ondergrens
	 * @param max - bovengrens
	 * @return het gegenereerde random getal
	 */
	public static int randInt(int min, int max) {
		assert min < max : "Het maximum is kleiner dan het minimum";
		
		return min + (int) (Math.random() * max);
	}
	
	public static void opdracht1(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int getal = array[i];
			int x;
			for (x = i - 1; x >= 0 && getal < array[x]; x--) {
				array[x + 1] = array[x];
			}
			array[x + 1] = getal;
		}
	}
	
	public static void opdracht2() {
		
	}
	
	public static void opdracht3() {
		
	}
	
	public static void opdracht4() {
		
	}
	
}
