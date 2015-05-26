import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Ch1_5 {
	public static void main(String[] args) {
		// read in the command
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		String[] commands = input.split(" ");
		char img[][] = new char[10][10];
		clear(img);

		while (!commands[0].equals("X")) {
			// read parameters based on which command it is and hand off
			// processing to the function
			switch (commands[0].charAt(0)) {
			case 'I':
				img = createImg(Integer.parseInt(commands[2]),
						Integer.parseInt(commands[1]));
				clear(img);
				break;
			case 'C':
				clear(img);
				break;
			case 'L':
				img[Integer.parseInt(commands[2]) - 1][Integer
						.parseInt(commands[1]) - 1] = commands[3].charAt(0);
				break;
			case 'V':
				verticalSegment(img, commands[4].charAt(0),
						Integer.parseInt(commands[1]) - 1,
						Integer.parseInt(commands[2]) - 1,
						Integer.parseInt(commands[3]) - 1);
				break;
			case 'H':
				horizontalSegment(img, commands[4].charAt(0),
						Integer.parseInt(commands[3]) - 1,
						Integer.parseInt(commands[1]) - 1,
						Integer.parseInt(commands[2]) - 1);
				break;
			case 'K':
				color(img, commands[5].charAt(0),
						Integer.parseInt(commands[2]) - 1,
						Integer.parseInt(commands[4]) - 1,
						Integer.parseInt(commands[1]) - 1,
						Integer.parseInt(commands[3]) - 1);
				break;
			case 'F':
				shade(img, commands[3].charAt(0),
						Integer.parseInt(commands[2]) - 1,
						Integer.parseInt(commands[1]) - 1);
				break;
			case 'S':
				System.out.println(commands[1]);
				print(img);
				break;
			default:
				break;
			}

			// read next command
			input = scan.nextLine();
			commands = input.split(" ");
		}
	}

	private static char[][] createImg(int rows, int cols) {
		char[][] img = new char[rows][cols];
		color(img, 'O', 0, rows - 1, 0, cols - 1);
		return img;
	}

	private static void clear(char[][] img) {
		color(img, 'O', 0, 0, img.length - 1, img[0].length - 1);
	}

	private static void verticalSegment(char[][] img, char color, int y,
			int x1, int x2) {
		color(img, color, Math.min(x1, x2), Math.max(x1, x2), y, y);
	}

	private static void horizontalSegment(char[][] img, char color, int x,
			int y1, int y2) {
		color(img, color, x, x, Math.min(y1, y2), Math.max(y1, y2));
	}

	private static void color(char[][] img, char color, int x1, int x2, int y1,
			int y2) {
		for (int i = x1; i <= x2; i++) {
			for (int j = y1; j <= y2; j++) {
				img[i][j] = color;
			}
		}
	}

	private static void print(char[][] img) {
		for (int i = 0; i < img.length; i++) {
			for (int j = 0; j < img[0].length; j++) {
				System.out.print(img[i][j]);
			}
			System.out.println();
		}
	}

	private static void shade(char[][] img, char color, int x, int y) {
		char oldColor = img[x][y];
		Stack<Point> queue = new Stack<Point>();
		Set<Point> visited = new HashSet<Point>();
		queue.push(new Point(x, y));

		while (queue.size() != 0) {
			Point curr = queue.pop();
			visited.add(curr);
			List<Point> neighbors = getNeighbors(curr, img);
			for (Point neighbor : neighbors) {
				if (img[neighbor.x][neighbor.y] == oldColor
						&& !visited.contains(neighbor)) {
					queue.push(neighbor);
				}
			}
			img[curr.x][curr.y] = color;
		}
	}

	private static List<Point> getNeighbors(Point pt, char[][] img) {
		ArrayList<Point> neighbors = new ArrayList<Point>();
		if (pt.y + 1 < img[0].length) {
			neighbors.add(new Point(pt.x, pt.y + 1));
		}
		if (pt.y - 1 >= 0) {
			neighbors.add(new Point(pt.x, pt.y - 1));
		}
		if (pt.x + 1 < img.length) {
			neighbors.add(new Point(pt.x + 1, pt.y));
		}
		if (pt.x - 1 >= 0) {
			neighbors.add(new Point(pt.x - 1, pt.y));
		}
		// System.out.println(pt + ": ");
		// System.out.println(neighbors);
		return neighbors;

	}
}
