import java.lang.reflect.Array;
import java.util.ArrayList;

public class Apl {

	private static int aantalGetallen = 1000;
	private static int[] heleGetallen;
	private static ArrayList<Integer> gesorteerdeLijst = new ArrayList<Integer>(
			aantalGetallen);
	
	//De subArrays van de ongesorteerde array.
	private static ArrayList<int[]> subArrays = new ArrayList<int[]>();

	public static void main(String[] args) {
		
		opdracht3();
		
//		//----TEST MERGE----
//		ArrayList<Integer> list1 = new ArrayList<Integer>();
//		ArrayList<Integer> list2 = new ArrayList<Integer>();
//
//		list1.add(4);
//		list1.add(20);
//		list1.add(33);
//		list1.add(55);
//		
//		list2.add(5);
//		list2.add(47);
//		list2.add(48);
//		list2.add(49);
//		list2.add(50);
//		list2.add(53);
//		list2.add(59);
//		
//		System.out.println(merge(list1, list2).toString());
//		// ----EINDE TEST----
		
//		for (int i = 0; i < 5; i++) {
//			heleGetallen = new int[aantalGetallen];
//			gesorteerdeLijst = new ArrayList<Integer>(aantalGetallen);
//		
//			heleGetallen = vulArray(heleGetallen, aantalGetallen);
//		
//			for (int j = 0; j < aantalGetallen; j++) {
//				gesorteerdeLijst.add(0);
//			}
//		
//			opdracht1(heleGetallen);
//		}
//
//		for (int i = 0; i < 5; i++) {
//			opdracht2();
//		}
	}

	/**
	 * Opdracht 1.1 - De gehele lijst wordt in ��n thread gesorteerd middels insertion sort.
	 * @param array
	 */
	public static void opdracht1(int[] array) {

		long start = System.currentTimeMillis();

		Thread t = new InsertionSort(array, gesorteerdeLijst);
		t.start();

		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long eind = System.currentTimeMillis();

		System.out.println("Sorteren opdracht 1 duurde: " + (eind - start)
				+ " ms.");

	}

	/**
	 * Opdracht 1.2 - hier wordt de lijst opgedeeld in twee stukken en vervolgens door twee threads gesorteerd.
	 */
	public static void opdracht2() {

		int n = aantalGetallen / 2;

		int[] a1 = new int[n];
		int[] a2 = new int[n];
		a2 = vulArray(a2, n);
		a1 = vulArray(a1, n);

		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		for (int j = 0; j < n; j++) {
			list1.add(0);
			list2.add(0);
		}

		long start = System.currentTimeMillis();

		Thread t1 = new InsertionSort(a1, list1);
		Thread t2 = new InsertionSort(a2, list2);
		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assert list1.size() == n : "wrong size list 1 " + list1.size();

		ArrayList<Integer> result = merge(list1, list2);

		long eind = System.currentTimeMillis();

		System.out.println("Sorteren opdracht 2 duurde: " + (eind - start) + " ms.");
		System.out.println(result.toString());

	}
	
	/**
	 * Opdracht 1.3
	 */
	public static void opdracht3() {

		int threshold = 1000;							// drempel waarde, maximum aantal getallen per thread.
		int N = 50000;									// aantal getallen.
		int noThreads = 0;								// hoeveelheid threads.
		Thread[] threads;								// De threads;
		ArrayList<ArrayList<Integer>> subSortedLists;	// De gesorteerde sub array's.
		ArrayList<Integer> sortedList;					// De gesorteerde lijst.
	
		int[] unsorted = new int[N];					// array aanmaken van N groot
		unsorted = vulArray(unsorted, N);				// array vullen met willekeurige getallen.
		
		// De array opdelen aan de hand van de threshold. De lijst wordt hier opgedeeld in stukjes kleiner dan de threshold
		splitArray(unsorted, threshold);
		System.out.println(subArrays.size());
		
		//Het aantal threads is de groote van de subArray's lijst. Dit zijn de hoeveelheid sub arrays.
		noThreads = subArrays.size();
		
		//Threads aanmaken en starten.
		threads = new Thread[noThreads];
		for(int i = 0; i < noThreads; i++) {
			
			//========================================================================================================================
			//Het opsplitsen in kleinere array's en in meerdere threads werkt nu :D Alleen moet het sorteren nog goed gemaakt worden.
			//de ArrayList waar hij in gesorteerd wordt "gesorteerdeArrayList" moet nog worden gemaakt.
			//We moeten een manier vinden om al deze lijsten aan te maken, zodat we gelijk aantal ongesorteerde sublijstjes hebben
			//als het aantal sub arraylists hebben waar ze in gesorteerd moeten worden.
			//Vervolgens kunnen we deze lijstjes mergen met de merge methode en dan de tijden nog meten ;)
			//Verder niet vergeten dit commentaar (en al het andere nutteloze commentaar) weg te halen :P hahaha
			//========================================================================================================================
			
			threads[i] = new InsertionSort(subArrays.get(i), gesorteerdeArrayList); // <--- de ArrayList waarin de ongesorteerde subarray gesorteerd wordt.
			threads[i].start();
		}
		
		//Wachten op .join() van alle threads.
		for(int i = 0; i < noThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * Methode die de array opdeelt in kleinere stukjes. Als de array groter is dan de drempelwaarde, dan deelt de methode de array in twee�n.
	 * Mochten de ontstaande subArray's nog steeds groter zijn dan de opgegeven drempelwaarde, zal de methode ook deze array's opdelen.
	 * Dit gaat zo door tot er subArray's ontstaan met een grootte onder de drempelwaarde. Deze array's worden vervolgens in een ArrayList gestopt.
	 * @param array - de array die opgedeeld moet worden.
	 * @param threshold - de drempel waarde
	 */
	public static void splitArray(int[] array, int threshold) {
		int[] subArray1 = null;
		int[] subArray2 = null;
		
		if(array.length <= threshold){
			return;
		} else {
			int midIndex = (int) Math.round(array.length/2);
			subArray1 = new int[midIndex];
			subArray2 = new int[array.length - midIndex];
			for(int i = 0; i < midIndex; i++) {
				subArray1[i] = array[i];
			}
			int j = 0;
			for(int i = midIndex; i < array.length; i++) {
				subArray2[j] = array[i];
				j++;
			}
		}		
		if(subArray2.length > threshold && subArray2.length > threshold) {
			splitArray(subArray1, threshold);
			splitArray(subArray2, threshold);
		} else {
			subArrays.add(subArray1);
			subArrays.add(subArray2);
		}
	}

	/**
	 * Methode die 2 lijsten op de goede volgorde samenvoegt
	 * @param list1
	 * @param list2
	 * @return de samengevoegde lijst
	 */
	public static ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> result = new ArrayList<Integer>();

		int index1 = 0;
		int index2 = 0;

		while (index1 < list1.size() && index2 < list2.size()) {
		
			if (list1.get(index1) < list2.get(index2)) {
				result.add(list1.get(index1));
				index1++;
			} else {
				result.add(list2.get(index2));
				index2++;
			}
		}
		
		if (list1.size() <= index1) {
			for (int i = index2; i < list2.size(); i++) {
				result.add(list2.get(i));
			}
		} else if (list2.size() <= index2) {
			for (int i = index1; i < list1.size(); i++) {
				result.add(list1.get(i));
			}
		}
		
		return result;

	}
	
	/**
	 * Methode voor het vullen van de array met willekeurige hele getallen.
	 */
	public static int[] vulArray(int[] array, int aantalGetallen) {
		for (int i = 0; i < aantalGetallen; i++) {

			// random getal tussen 0 en 100
			int getal = randInt(0, 100);

			array[i] = getal;
		}
		return array;
	}

	/**
	 * Methode voor het genereren van een radom getal tussen twee grenzen.
	 * 
	 * @param min - ondergrens
	 * @param max - bovengrens
	 * @return het gegenereerde random getal
	 */
	public static int randInt(int min, int max) {
		assert min < max : "Het maximum is kleiner dan het minimum";

		return min + (int) (Math.random() * max);
	}

}
