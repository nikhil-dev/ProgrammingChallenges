import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ch1_7 {
	public static void main(String[] args) {
		char[][] board;
		int count = 1;
		Scanner scan = new Scanner(System.in);
		while (!isBoardEmpty(board = readBoard(scan))) {
			System.out.print("Game #" + count + ": ");
			System.out.println(isInCheck(board) + " king is in check.");
			count++;
			scan.nextLine();
		}
	}

	private static char[][] readBoard(Scanner scanner) {
		char[][] board = new char[8][8];
		for (int i = 0; i < board.length; i++) {
			String line = scanner.nextLine();
			board[i] = line.toCharArray();
		}
		return board;
	}

	private static boolean isBoardEmpty(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != '.')
					return false;
			}
		}
		return true;
	}

	private static String isInCheck(char[][] board) {
		// get kings' locations and list of opponents along with positions
		Point whiteKing = getKing(board, 'K');
		Point blackKing = getKing(board, 'k');

		// iterate over pieces and check if it attacks the king
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] != '.') {
					if (is(board[row][col], 'w')
							&& doesAttack(new Point(row, col), blackKing, board)) {
						return "black";
					}
					if (is(board[row][col], 'b')
							&& doesAttack(new Point(row, col), whiteKing, board)) {
						return "white";
					}
				}
			}
		}

		return "no";

	}

	private static Point getKing(char[][] board, char king) {
		Point pos = new Point(-1, -1);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == king)
					pos = new Point(i, j);
			}
		}
		return pos;
	}

	private static boolean is(char block, char type) {
		if (type == 'b' && block >= 'a' && block <= 'z')
			return true;
		else if (type == 'w' && block >= 'A' && block <= 'Z')
			return true;
		else
			return false;
	}

	private static boolean doesAttack(Point pt, Point king, char[][] board) {

		switch (board[pt.x][pt.y]) {
		case 'p':
			if (new Point(pt.x + 1, pt.y + 1).equals(king)
					|| new Point(pt.x + 1, pt.y - 1).equals(king)) {
				return true;
			}
			break;
		case 'P':
			if (new Point(pt.x - 1, pt.y + 1).equals(king)
					|| new Point(pt.x - 1, pt.y - 1).equals(king)) {
				return true;
			}
			break;
		case 'n':
		case 'N':
			for (Point pos : getPossibleKnightPos(pt)) {
				if (pos.equals(king)) {
					return true;
				}
			}
			break;
		case 'b':
		case 'B':
			for (Point pos : getPossibleBishopPos(pt, board)) {
				if (pos.equals(king)) {
					return true;
				}
			}
			break;

		case 'r':
		case 'R':
			for (Point pos : getPossibleRookPos(pt, board)) {
				if (pos.equals(king)) {
					return true;
				}
			}
			break;

		case 'q':
		case 'Q':
			for (Point pos : getPossibleQueenPos(pt, board)) {
				if (pos.equals(king)) {
					return true;
				}
			}
			break;

		case 'k':
		case 'K':
			if (new Point(pt.x + 1, pt.y + 1).equals(king)
					|| new Point(pt.x + 1, pt.y - 1).equals(king)
					|| new Point(pt.x - 1, pt.y + 1).equals(king)
					|| new Point(pt.x - 1, pt.y - 1).equals(king)
					|| new Point(pt.x, pt.y - 1).equals(king)
					|| new Point(pt.x, pt.y + 1).equals(king)
					|| new Point(pt.x + 1, pt.y).equals(king)
					|| new Point(pt.x - 1, pt.y).equals(king)) {
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}

	private static List<Point> getPossibleBishopPos(Point pt, char[][] board) {
		ArrayList<Point> pts = new ArrayList<Point>();
		int offset = 1;
		boolean br = false, tr = false, bl = false, tl = false;
		while (!br || !tr || !bl || !tl) {
			Point toAdd = new Point(pt.x + offset, pt.y + offset);
			if (emptyOrHasKing(toAdd, board) && !br)
				pts.add(toAdd);
			else
				br = true;

			toAdd = new Point(pt.x + offset, pt.y - offset);
			if (emptyOrHasKing(toAdd, board) && !bl)
				pts.add(toAdd);
			else
				bl = true;

			toAdd = new Point(pt.x - offset, pt.y + offset);
			if (emptyOrHasKing(toAdd, board) && !tr)
				pts.add(toAdd);
			else
				tr = true;

			toAdd = new Point(pt.x - offset, pt.y - offset);
			if (emptyOrHasKing(toAdd, board) && !tl)
				pts.add(toAdd);
			else
				tl = true;

			offset++;

		}
		return pts;

	}

	private static List<Point> getPossibleRookPos(Point pt, char[][] board) {
		ArrayList<Point> pts = new ArrayList<Point>();
		int offset = 1;
		boolean u = false, d = false, l = false, r = false;
		while (!u || !d || !r || !l) {
			Point toAdd = new Point(pt.x - offset, pt.y);
			if (emptyOrHasKing(toAdd, board) && !u)
				pts.add(toAdd);
			else
				u = true;

			toAdd = new Point(pt.x + offset, pt.y);
			if (emptyOrHasKing(toAdd, board) && !d)
				pts.add(toAdd);
			else
				d = true;

			toAdd = new Point(pt.x, pt.y + offset);
			if (emptyOrHasKing(toAdd, board) && !r)
				pts.add(toAdd);
			else
				r = true;

			toAdd = new Point(pt.x, pt.y - offset);
			if (emptyOrHasKing(toAdd, board) && !l)
				pts.add(toAdd);
			else
				l = true;

			offset++;

		}
		return pts;
	}

	private static List<Point> getPossibleQueenPos(Point pt, char[][] board) {
		List<Point> pts = getPossibleBishopPos(pt, board);
		pts.addAll(getPossibleRookPos(pt, board));
		return pts;
	}

	private static List<Point> getPossibleKnightPos(Point pt) {
		Point[] list = { new Point(pt.x + 2, pt.y + 1),
				new Point(pt.x + 1, pt.y + 2), new Point(pt.x + 2, pt.y - 1),
				new Point(pt.x + 1, pt.y - 2), new Point(pt.x - 2, pt.y + 1),
				new Point(pt.x - 1, pt.y + 2), new Point(pt.x - 2, pt.y - 1),
				new Point(pt.x - 1, pt.y - 2) };
		return Arrays.asList(list);

	}

	private static boolean emptyOrHasKing(Point pt, char[][] board) {
		if (pt.x >= 0 && pt.y >= 0 && pt.x < board.length
				&& pt.y < board[0].length && board[pt.x][pt.y] == '.')
			return true;
		if (pt.x >= 0 && pt.y >= 0 && pt.x < board.length
				&& pt.y < board[0].length && board[pt.x][pt.y] == 'k')
			return true;
		if (pt.x >= 0 && pt.y >= 0 && pt.x < board.length
				&& pt.y < board[0].length && board[pt.x][pt.y] == 'K')
			return true;
		else
			return false;
	}
}
