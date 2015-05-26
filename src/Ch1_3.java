import java.util.Scanner;

public class Ch1_3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numPeople = scan.nextInt();
		while (numPeople != 0) {
			// read the input
			double[] spends = new double[numPeople];
			for (int i = 0; i < numPeople; i++) {
				spends[i] = scan.nextDouble();
			}

			// call the function to compute total to move hands
			double result = getTotalMoneyTransferred(spends);

			// print result
			System.out.println(Math.round(result*100)/100.0);
			
			// read new input
			numPeople = scan.nextInt();
		}

	}
	
	private static double getTotalMoneyTransferred(double[] spends) {
		// find average spend and round
		double avg = findSum(spends)/spends.length;
		
		// for each spend less than the average add it to total to be transferred
		double total = 0;
		for (int i = 0; i < spends.length; i++) {
			if (spends[i] < avg) {
				total += avg - spends[i];
			}
		}
		
		// return result
		return total;
	}
	
	private static double findSum(double[] nums) {
		double total = 0;
		for (int i = 0; i < nums.length; i++) {
			total += nums[i];
		}
		return total;
	}
}
