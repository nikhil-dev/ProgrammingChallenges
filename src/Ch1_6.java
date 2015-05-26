import java.util.Scanner;

public class Ch1_6 {
	public static void main(String[] args) {
		// read the number of inputs to execute
		Scanner scan = new Scanner(System.in);
		int nPrograms = Integer.parseInt(scan.nextLine());

		scan.nextLine(); // skip the first blank line

		// read program into RAM
		for (int i = 0; i < nPrograms; i++) {
			String command;
			String[] registers = new String[10];
			String[] RAM = new String[1000];
			for (int j = 0; j < registers.length; j++) {
				registers[j] = "000";
			}
			for (int j = 0; j < RAM.length; j++) {
				RAM[j] = "000";
			}
			int count = 0;

			// read instructions and store in RAM
			while ((command = scan.nextLine()).length() == 3) {
				RAM[count++] = command;
			}

			int instruction_count = 0;
			int program_counter = 0;
			// execute each instruction and count total instructions executed
			while (!(program_counter == -1)) {
				program_counter = parse(RAM, registers, program_counter);
				instruction_count++;
			}

			// print total instructions executed and a blank line
			System.out.println(instruction_count);
			if (!(i == nPrograms - 1)) { // only print a blank line if its not
											// the last result
				System.out.println();
			}
		}
	}

	public static int parse(String[] RAM, String[] registers, int pc) {
		String instruction = RAM[pc];
		int[] args = { Character.getNumericValue(instruction.charAt(0)),
				Character.getNumericValue(instruction.charAt(1)),
				Character.getNumericValue(instruction.charAt(2)) };
		switch (args[0]) {
		case 1:
			return -1;

		case 2:
			registers[args[1]] = to3str(args[2]);
			return pc + 1;

		case 3:
			registers[args[1]] = to3str((Integer.parseInt(registers[args[1]]) + args[2]) % 1000);
			return pc + 1;

		case 4:
			registers[args[1]] = to3str((Integer.parseInt(registers[args[1]]) * args[2]) % 1000);
			return pc + 1;

		case 5:
			registers[args[1]] = registers[args[2]];
			return pc + 1;

		case 6:
			registers[args[1]] = to3str((Integer.parseInt(registers[args[1]]) + Integer
					.parseInt(registers[args[2]])) % 1000);
			return pc + 1;

		case 7:
			registers[args[1]] = to3str((Integer.parseInt(registers[args[1]]) * Integer
					.parseInt(registers[args[2]])) % 1000);
			return pc + 1;

		case 8:
			registers[args[1]] = RAM[Integer.parseInt(registers[args[2]])];
			return pc + 1;

		case 9:
			RAM[Integer.parseInt(registers[args[2]])] = registers[args[1]];
			return pc + 1;

		case 0:
			if (Integer.parseInt(registers[args[2]]) == 0) {
				return pc + 1;
			} else {
				return Integer.parseInt(registers[args[1]]);
			}

		default:
			return -2;
		}

	}

	private static String to3str(int n) {
		String str = Integer.toString(n);
		if (str.length() == 3) {
			return str;
		} else if (str.length() == 2) {
			return "0" + str;
		} else {
			return "00" + str;
		}
	}
}
