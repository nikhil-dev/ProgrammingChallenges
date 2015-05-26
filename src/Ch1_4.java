import java.util.Scanner;

public class Ch1_4 {
	private static final int VERICAL = 0, HORIZONTAL = 1;

	private static boolean[][] desc = {
			{ true, true, true, false, true, true, true },// 0
			{ false, false, false, false, false, true, true },
			{ false, true, true, true, true, true, false },
			{ false, false, true, true, true, true, true },
			{ true, false, false, true, false, true, true },
			{ true, false, true, true, true, false, true },
			{ true, true, true, true, true, false, true },
			{ false, false, true, false, false, true, true },
			{ true, true, true, true, true, true, true },
			{ true, false, true, true, true, true, true } };

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		String n = scan.next();

		while (Integer.parseInt(n) != 0 || size != 0) {
			// print output
			printLCDDigits(n, size);

			// read new input
			size = scan.nextInt();
			n = scan.next();
		}
	}

	public static void printLCDDigits(String n, int size) {
		int[] digits = getDigits(n);

		for (int i = 0; i < digits.length; i++) {
			if (desc[digits[i]][2]) {
				print("-", size, HORIZONTAL);
			} else {
				print(" ", size, HORIZONTAL);
			}
		}
		System.out.println();

		for (int level = 0; level < size; level++) {
			for (int i = 0; i < digits.length; i++) {
				if (desc[digits[i]][0] && desc[digits[i]][5]) {
					print("||", size, VERICAL);
				} else if (desc[digits[i]][0]) {
					print("| ", size, VERICAL);
				} else if (desc[digits[i]][5]) {
					print(" |", size, VERICAL);
				} else {
					print("  ", size, VERICAL);
				}
			}
			System.out.println();
		}

		for (int i = 0; i < digits.length; i++) {
			if (desc[digits[i]][3]) {
				print("-", size, HORIZONTAL);
			} else {
				print(" ", size, HORIZONTAL);
			}
		}
		System.out.println();

		for (int level = 0; level < size; level++) {
			for (int i = 0; i < digits.length; i++) {
				if (desc[digits[i]][1] && desc[digits[i]][6]) {
					print("||", size, VERICAL);
				} else if (desc[digits[i]][1]) {
					print("| ", size, VERICAL);
				} else if (desc[digits[i]][6]) {
					print(" |", size, VERICAL);
				} else {
					print("  ", size, VERICAL);
				}
			}
			System.out.println();
		}

		for (int i = 0; i < digits.length; i++) {
			if (desc[digits[i]][4]) {
				print("-", size, HORIZONTAL);
			} else {
				print(" ", size, HORIZONTAL);
			}
		}
		System.out.println();

	}

	private static int[] getDigits(String n) {
		int[] digits = new int[n.length()];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = Integer.parseInt("" + n.charAt(i));
		}
		return digits;
	}

	private static void print(String str, int size, int orientation) {
		if (orientation == VERICAL) {
			String line = "" + str.charAt(0) + multiply(" ", size)
					+ str.charAt(1) + " ";
			System.out.print(line);

		} else {
			System.out.print(" " + multiply(str, size) + "  ");
		}
	}

	private static String multiply(String str, int times) {
		String result = "";
		for (int i = 0; i < times; i++) {
			result = result + str;
		}
		return result;
	}
}
