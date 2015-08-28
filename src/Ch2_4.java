import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ch2_4 {
	public static void main(String[] args) {
		// Read in dictionary and sentence and make a list for each
		Scanner scan = new Scanner(System.in);
		int dictSize = Integer.parseInt(scan.nextLine());
		List<String> dict = new ArrayList<String>();
		for (int i = 0; i < dictSize; i++) {
			dict.add(scan.nextLine());
		}
		String message = scan.nextLine();

		// Start matching search algorithm

		Map<Character, Character> map = getMatch(dict,
				Arrays.asList(message.split(" ")),
				new HashMap<Character, Character>());

		// Produce decrypted text and print
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < message.length(); i++) {
			if (map == null) {
				builder.append(message.charAt(i) == ' ' ? ' ': '*');
			}
			else {
				builder.append(message.charAt(i) == ' ' ? ' ': map.get(message.charAt(i)));
			}
		}
		System.out.println(builder.toString());
	}

	private static Map<Character, Character> getMatch(List<String> dictionary,
			List<String> message, Map<Character, Character> map) {
		String currentWord = message.get(0);
		for (String guessWord : dictionary) {
			// Take a potential guess for the first word build a map that agrees
			// with the given map
			Map<Character, Character> newMap = buildMap(currentWord, guessWord);
			if (newMap == null) {
				continue;
			}
			newMap = combineMaps(map, newMap);
			if (newMap == null) {
				continue;
			}

			// Call recursive method
			if (message.size() != 1) {
				List<String> messageCopy = new ArrayList<String>(message);
				messageCopy.remove(0);
				newMap = getMatch(dictionary, messageCopy, newMap);
			}

			// If valid map, return it. If null, try a different word
			if (newMap != null) {
				return newMap;
			}
		}

		// After trying all possible guesses for first word, if no valid map
		// found, return null.
		return null;
	}

	private static Map<Character, Character> buildMap(String currentWord,
			String guessWord) {
		if (currentWord.length() != guessWord.length()) {
			return null;
		}
		Map<Character, Character> map = new HashMap<Character, Character>();
		for (int i = 0; i < currentWord.length(); i++) {
			char currMsgChar = currentWord.charAt(i);
			char currGuessChar = guessWord.charAt(i);
			if (map.containsKey(currMsgChar)
					&& map.get(currMsgChar) != currGuessChar) {
				return null;
			}
			map.put(currMsgChar, currGuessChar);
		}
		return map;
	}

	private static Map<Character, Character> combineMaps(
			Map<Character, Character> map1, Map<Character, Character> map2) {
		HashMap<Character, Character> newMap = new HashMap<Character, Character>();
		for (Map.Entry<Character, Character> entry : map1.entrySet()) {
			newMap.put(entry.getKey(), entry.getValue());
		}
		for (Map.Entry<Character, Character> entry : map2.entrySet()) {
			if (newMap.containsKey(entry.getKey())
					&& newMap.get(entry.getKey()) != entry.getValue()) {
				return null;
			}
			if (newMap.values().contains(entry.getValue()) && !newMap.containsKey(entry.getKey())) {
				return null;
			}
			if (newMap.values().contains(entry.getValue()) && newMap.get(entry.getKey()) != entry.getValue()) {
				return null;
			}
			newMap.put(entry.getKey(), entry.getValue());
		}
		return newMap;
	}
}
