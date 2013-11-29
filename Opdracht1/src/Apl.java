import java.lang.reflect.Array;
import java.util.ArrayList;

public class Apl {

	private static int aantalGetallen = 100000;
	private static int[] heleGetallen;
	private static ArrayList<Integer> gesorteerdeLijst = new ArrayList<Integer>(aantalGetallen);
	public static void main(String[] args) {

		for(int i = 0; i < 5; i++) {
			
			heleGetallen = new int[aantalGetallen];
			gesorteerdeLijst = new ArrayList<Integer>(aantalGetallen);
			
			vulArray();
			
			for(int j = 0; j < aantalGetallen; j++) {
				gesorteerdeLijst.add(0);
			}
			
			// start de tuntime stopwatch
			long startTime = System.currentTimeMillis();
		
			opdracht1(heleGetallen);

			// stop en print de runtime
			long endTime = System.currentTimeMillis();
			System.out.println("\nSorting took " + (endTime - startTime) + " ms");

		}
		
		
//		System.out.println(gesorteerdeLijst.toString());
//		for (int i = 0; i < heleGetallen.length; i++) {
//			System.out.print(heleGetallen[i] + ", ");
//		}
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
			for (x = i; x > 0 && getal < array[x - 1]; x--) {
				array[x] = array[x - 1];
				gesorteerdeLijst.set(x, array[x-1]);
			}
			array[x] = getal;
			gesorteerdeLijst.set(x, getal);
		}
	}
	
//	public void sort1(int[] a){
//		
//		for () {
//			
//		}
//		
//	}
	
//	public static void opdracht1(int[] a) {
//		for (int i = 1; i < aantalGetallen; i++) {
//			int j = i;
//			int getal = a[i];
//			while ((j > 0) && (a[j-1] > getal)) {
//				gesorteerdeLijst.add(j, a[j-1]);
//				j--;
//			}
//			
//			//gesorteerdeLijst.add(j, getal);
//			
//		}
//	}
	
	public static void opdracht2() {
		
	}
	
	public static void opdracht3() {
		
	}
	
	public static void opdracht4() {
		
	}
	
}
