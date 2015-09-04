import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Ch2_6 {
	private static int INFINITY = -1;

	public static void main(String[] args) {
		// Read in the graph along with the edges
		Scanner scan = new Scanner(System.in);
		String nums = scan.nextLine();
		int nPapers = Integer.parseInt(nums.split(" ")[0]);
		int nAuthors = Integer.parseInt(nums.split(" ")[1]);
		Map<String, List<String>> neighbors = new HashMap<String, List<String>>();
		for (int i = 0; i < nPapers; i++) {
			String line = scan.nextLine();
			String[] authors = parseAuthors(line.split(":")[0]);
			for (int j = 0; j < authors.length; j++) {
				String author = authors[j];
				List<String> currNeighbors = neighbors.containsKey(author) ? neighbors
						.get(author) : new ArrayList<String>();
				for (int k = 0; k < authors.length; k++) {
					if (!(k == j)) {
						currNeighbors.add(authors[k]);
					}
				}
				neighbors.put(author, currNeighbors);
			}
		}

		List<String> authors = new ArrayList<String>();
		for (int i = 0; i < nAuthors; i++) {
			authors.add(scan.nextLine());
		}
		String target = "Erdos, P.";

		// Print distances
		for (String author : authors) {
			int dist = getDistance(neighbors, target, author);
			System.out.println(author + " " + (dist > 0 ? dist : "infinity"));
		}

	}

	private static int getDistance(Map<String, List<String>> graph, String target, String start) {
		Set<String> visited = new HashSet<String>();
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(new Node(start, 0));
		while (!q.isEmpty()) {
			Node curr = q.poll();
			Set<Node> neighbors = getNeighbors(graph, curr);
			visited.add(curr.data);
			for (Node neighbor : neighbors) {
				if (neighbor.data.equals(target)) {
					return neighbor.distance;
				}
				if (!visited.contains(neighbor.data)) {
					q.add(neighbor);
				}
			}
		}
		return INFINITY;
	}

	private static String parse(String name) {
		if (name.charAt(name.length() - 1) == '.') {
			return name;
		}
		return name + ".";
	}
	
	private static Set<Node> getNeighbors (Map<String, List<String>> graph, Node node) {
		Set<Node> set = new HashSet<Node>();
		for (String neighborData : graph.get(node.data)) {
			set.add(new Node(neighborData, node.distance + 1));
		}
		return set;
	}

	private static class Node {
		private String data;
		private int distance;

		public Node(String data, int dist) {
			this.data = data;
			this.distance = dist;
		}
	}
	
	private static String[] parseAuthors (String authorsLine) {
		List<String> authors = new ArrayList<String> ();
		int start = 0;
		int end = 0;
		for (int i = 0; i < authorsLine.length(); i++) {
			if ( (i > 0 && authorsLine.charAt(i) == ',' && authorsLine.charAt(i-1) == '.') || i == authorsLine.length() - 1) {
				end = i < authorsLine.length() - 1 ? i : i+1;
				authors.add(authorsLine.substring(start, end));
				start = i + 2; // Skip the space
			}
		}
		return (String[]) authors.toArray(new String[authors.size()]);
	}

}