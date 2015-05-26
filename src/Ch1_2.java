import java.util.Scanner;

public class Ch1_2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = 1;
		int n = scan.nextInt();
		int m = scan.nextInt();
		while (!(n == 0 && m == 0)) {
			scan.nextLine();
			String[] grid = new String[n];
			for (int i = 0; i < n; i++){
				grid[i] = scan.nextLine();
			}
			String[] result = getField(grid);
			System.out.println("Field #" + num + ":");
			for (int i = 0; i < result.length; i++){
				System.out.println(result[i]);
			} 
			num++;
			n = scan.nextInt();
			m = scan.nextInt();
			System.out.println();
		}
	}
	
	public static String[] getField (String[] grid) {
		int[][] counts = new int[grid.length][grid[0].length()];
		// generate counts
		for (int i = 0; i < counts.length; i++){
			for (int j = 0; j < counts[0].length; j++) {
				counts[i][j] = getNumberOfAdjacentMines(grid, i, j);
			}
		}
		
		// convert int grid to array of Strings and return
		String[] result = new String[grid.length];
		for (int i = 0; i < counts.length; i++) {
			String str = "";
			for (int j = 0; j < grid[0].length(); j++) {
				if (grid[i].charAt(j) == '*')
					str += "*";
				else
					str += Integer.toString(counts[i][j]);
			}
			result[i] = str;
		}
		return result;
	}
	
	public static int getNumberOfAdjacentMines(String[] grid, int row, int col) {
		int count = 0;
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = col - 1; j <= col + 1; j++) {
				if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length()) // bounds check
					continue;
				else if (i == row && j == col) // don't count mine at the current position
					continue;
				else if (grid[i].charAt(j) == '*')
					count++;
			}
		}
		return count;
	}
	
}
