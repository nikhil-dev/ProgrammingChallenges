import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Ch2_1 {
	public static void main(String [] args) {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNext()) {
			Set<Integer> set = new HashSet<Integer> ();
			int[] sequence = new int[scan.nextInt()];
			// Read in the list of integers.
			for (int i = 0; i < sequence.length; i++) {
				sequence[i] = scan.nextInt();
				if (i > 0) {
					int diff = Math.abs(sequence[i] - sequence[i-1]);
					if (diff > sequence.length - 1) {
						System.out.println("Not jolly");
						return;
					}
					if (set.contains(diff)) {
						System.out.println("Not jolly");
						return;
					}
					set.add(diff);
				}
			}
			System.out.println("Jolly");
		}
	}
}
