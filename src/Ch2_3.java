import java.util.HashSet;
import java.util.Scanner;


public class Ch2_3 {
	public static void main (String[] args) {
		// Read in the number of days and h values.
		Scanner scan = new Scanner(System.in);
		int nDays = scan.nextInt();
		int nParties = scan.nextInt();
		int[] h = new int[nParties];
		for (int i = 0; i < nParties; i++) {
			h[i] = scan.nextInt();
		}
		
		// Make a hashset of hartal days
		HashSet<Integer> hartalDays = new HashSet<Integer> ();
		
		// Add to hashset
		for (int i = 0; i < nParties; i++) {
			int day = h[i];
			while (day <= nDays) {
				if (!fridayOrSaturday (day)) {
					hartalDays.add(day);
				}
				day = day + h[i];
			}
		}
		
		// Return the length of the hashset
		System.out.println(hartalDays.size());
	}
	
	private static boolean fridayOrSaturday (int day) {
		if (day % 7 == 6 || day % 7 == 0) {
			return true;
		}
		return false;
	}
}
