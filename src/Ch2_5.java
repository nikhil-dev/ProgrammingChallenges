import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Ch2_5 {
	public static void main (String[] args) {
		// Read the input shuffles
		Scanner scan = new Scanner(System.in);
		int nShuffles = scan.nextInt();
		List<List<Integer>> shuffles = new ArrayList<List<Integer>>();
		for (int i = 0; i < nShuffles; i++) {
			List<Integer> shuffle = new ArrayList<Integer>();
			for (int j = 0; j < 52; j++) {
				shuffle.add(scan.nextInt());
			}
			shuffles.add(shuffle);
		}
		List<Integer> shuffleSequence = new ArrayList<Integer>();
		while (scan.hasNextInt()) {
			shuffleSequence.add(scan.nextInt());
		}
		
		// Create the initial state
		List<String> deck = new ArrayList<String>();
		List<String> suits = Arrays.asList("Clubs", "Diamonds", "Hearts", "Spades");
		List<String> values = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace");
		for (String suit : suits) {
			for (String value : values) {
				deck.add(value + " of " + suit);
			}
		}
		
		// Apply the shuffle
		for (int index : shuffleSequence) {
			deck = applyShuffle(deck, shuffles.get(index - 1));
		}
		
		// Print the deck
		for (String card : deck) {
			System.out.println(card);
		}
	}
	
	private static List<String> applyShuffle(List<String> deck, List<Integer> shuffle) {
		List<String> newDeck = new ArrayList<String>();
		System.out.println(shuffle.size());
		System.out.println(deck.size());
		for (int oldPosition : shuffle) {
			newDeck.add(deck.get(oldPosition - 1));
		}
		return newDeck;
	}
}
