import java.util.Scanner;

public class Ch1_1 {
	
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			// read input and pass to solver
			int i = scan.nextInt();
			int j = scan.nextInt();
			int result = maxSequenceLengthFinder(i, j);
			
			// print output
			System.out.println(i + " " + j + " " + result);
		}
		scan.close();
	}
	
	public static int maxSequenceLengthFinder(int start, int end) {
		int max = -1;
		for (int i = start; i <= end; i++) {
			// find cycle length
			int length = findCycleLength(i);
			
			// update max
			if (length > max || max == -1)
				max = length;
		}
		return max;
	}
	
	public static int findCycleLength(int i) {
		int len = 1; // count the first number
		int curr = i;
		while (curr != 1) {
			len++;
			if (curr % 2 == 0) {
				curr = curr/2;
			}
			else {
				curr = 3*curr + 1;
			}
		}
		return len;
	}

}
