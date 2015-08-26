// This program is meant to take in just one instance of a sample input for this problem. 
// You'd have to make some changes to make it work for multiple inputs.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Ch2_2 {
	public static void main(String[] args) {
		// Read in the hands.
		String[] hand1 = new String[5];
		String[] hand2 = new String[5];
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < hand1.length; i++) {
			hand1[i] = scan.next();
		}
		for (int i = 0; i < hand2.length; i++) {
			hand2[i] = scan.next();
		}

		// Determine the type of hand and the ranking and give it a number.
		String score1 = getScore(hand1);
		String score2 = getScore(hand2);

		// Compare and print
		System.out.println(getResult(score1.split(":"), score2.split(":")));

	}

	private static String getResult(String[] blackScore, String[] whiteScore) {
		String[] strength = { "highcard", "pair", "twopairs", "3kind",
				"straight", "flush", "fullhouse", "4kind", "straightflush" };
		char[] valueOrder = { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J',
				'Q', 'K', 'A' };
		if (Arrays.asList(strength).indexOf(blackScore[0]) > Arrays.asList(
				strength).indexOf(whiteScore[0])) {
			return "Black wins.";
		} else if (Arrays.asList(strength).indexOf(blackScore[0]) < Arrays
				.asList(strength).indexOf(whiteScore[0])) {
			return "White wins.";
		}
		for (int i = blackScore.length - 1; i > 0; i--) {
			if (indexOf(valueOrder, blackScore[i].charAt(0)) > indexOf(valueOrder, whiteScore[i].charAt(0)))
				return "Black wins.";
			else if (indexOf(valueOrder, blackScore[i].charAt(0)) < indexOf(valueOrder, whiteScore[i].charAt(0)))
				return "White wins.";
		}
		return "Tie.";
	}
	
	private static int indexOf(char[] array, char elem) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == elem)
				return i;
		}
		return -1;
	}

	private static String getScore(String[] hand) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		char values[] = new char[hand.length];
		boolean sameSuit = true;
		char highest = hand[0].charAt(0);
		for (int i = 0; i < hand.length; i++) {
			int count = map.containsKey(Character.toString(hand[i].charAt(0))) ? map
					.get(Character.toString(hand[i].charAt(0))) : 0;
			map.put(Character.toString(hand[i].charAt(0)),
					count + 1);
			values[i] = hand[i].charAt(0);
			if (i > 0 && hand[i] != hand[i - 1])
				sameSuit = false;
			if (values[i] > highest) {
				highest = values[i];
			}
		}
		boolean hasConsecutiveValues = true;
		Arrays.sort(values);
		for (int i = 0; i < values.length; i++) {
			if (i > 0 && values[i] - values[i - 1] != 1) {
				hasConsecutiveValues = false;
			}
		}
		int topK = 2;
		Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return o1.getValue() - o2.getValue();
			}
		};
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<Map.Entry<String, Integer>>(
				topK, comparator);
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (pq.size() <= topK) {
				pq.add(entry);
			} else if (entry.getValue() > pq.peek().getValue()) {
				pq.poll();
				pq.add(entry);
			}
		}
		Map.Entry<String, Integer> firstHighest = pq.poll();
		Map.Entry<String, Integer> secondHighest = pq.poll();
		if (hasConsecutiveValues && sameSuit) {
			return "straightflush:" + Character.toString(highest);
		} else if (firstHighest.getValue() == 4) {
			return "4kind:"
					+ Character.toString(firstHighest.getKey().charAt(0));
		} else if (firstHighest.getValue() == 3
				&& secondHighest.getValue() == 2) {
			return "fullhouse:"
					+ Character.toString(firstHighest.getKey().charAt(0));
		} else if (sameSuit) {
			return "flush:" + Character.toString(highest);
		} else if (hasConsecutiveValues) {
			return "straight:" + Character.toString(highest);
		} else if (firstHighest.getValue() == 3) {
			return "3kind:"
					+ Character.toString(firstHighest.getKey().charAt(0));
		} else if (firstHighest.getValue() == 2
				&& secondHighest.getValue() == 2) {
			char[] removedValues = removeElement(values, firstHighest.getKey()
					.charAt(0));
			removedValues = removeElement(removedValues, secondHighest.getKey()
					.charAt(0));
			Arrays.sort(removedValues);
			return "twopairs:"
					+ Character.toString(firstHighest.getKey().charAt(0)) + ":"
					+ Character.toString(secondHighest.getKey().charAt(0))
					+ ":" + Character.toString(removedValues[0]);
		} else if (firstHighest.getValue() == 2) {
			char[] removedValues = removeElement(values, firstHighest.getKey()
					.charAt(0));
			Arrays.sort(removedValues);
			return "pair:"
					+ Character.toString(firstHighest.getKey().charAt(0)) + ":"
					+ Character.toString(removedValues[0]) + ":"
					+ Character.toString(removedValues[1]) + ":"
					+ Character.toString(removedValues[2]);
		} else {
			Arrays.sort(values);
			return "highcard:" + Character.toString(values[0]) + ":"
					+ Character.toString(values[1]) + ":"
					+ Character.toString(values[2]) + ":"
					+ Character.toString(values[3]) + ":"
					+ Character.toString(values[4]);
		}
	}

	private static char[] removeElement(char[] array, char elem) {
		List<Character> newList = new ArrayList<Character>();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != elem)
				newList.add(array[i]);
		}
		char[] newArray = new char[newList.size()];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = newList.get(i);
		}
		return newArray;
	}
}