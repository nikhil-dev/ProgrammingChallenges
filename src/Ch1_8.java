import java.util.List;


public class Ch1_8 {
	public static void main(String[] args) {
		// read in each problem instance, call the method and print out the result
	}
	
	public static String winner(List<String> candidates, int[][] votes) {
		while (!hasClearWinner(result)) {
			result = recount(candidates, votes);
		}
		
		return extractWinner(result);
	}
	
	
}
