import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Ch2_7 {
	public static void main(String[] args) {
		HashMap<Integer, Contestant> map = new HashMap<Integer, Contestant>();
		TreeSet<Contestant> sortedTree = new TreeSet<Contestant>();
		Scanner scan = new Scanner(System.in);
		// Check if already correct submitted and add
		// penalty only if correct not already submitted and incorrect
		while (scan.hasNext()) {
			String line = scan.nextLine();
			int id = Integer.parseInt(line.split(" ")[0]);
			int problem = Integer.parseInt(line.split(" ")[1]);
			int time = Integer.parseInt(line.split(" ")[2]);
			String status = line.split(" ")[3];
			Contestant team = map.containsKey(id) ? map.get(id)
					: new Contestant(id);
			sortedTree.remove(team);
			if (status.equals("C") && !team.solved.contains(problem)) {
				team.totalPenalty += time;
				team.totalPenalty += team.incorrectCount.containsKey(problem) ? team.incorrectCount
						.get(problem)*20 : 0;
				team.solved.add(problem);
				team.problemsSolved++;
			} else if (status.equals("I") && !team.solved.contains(problem)) {
				int value = team.incorrectCount.containsKey(problem) ? team.incorrectCount
						.get(problem) : 0;
				team.incorrectCount.put(problem, ++value);
			}
			map.put(id, team);
			sortedTree.add(team);
		}

		// print if a answer has been submitted
		Iterator<Contestant> iter = sortedTree.descendingIterator();
		while (iter.hasNext()) {
			Contestant curr = iter.next();
			System.out.println(curr.id + " " + curr.problemsSolved + " " + curr.totalPenalty);
		}

	}

	private static class Contestant implements Comparable<Contestant> {
		int id;
		int problemsSolved;
		int totalPenalty;
		Set<Integer> solved = new HashSet<Integer>();
		Map<Integer, Integer> incorrectCount = new HashMap<Integer, Integer>();

		public Contestant(int id) {
			this.id = id;
		}
		
		public int compareTo(Contestant oppnt) {
			if (oppnt.problemsSolved != this.problemsSolved) {
				return this.problemsSolved - oppnt.problemsSolved;
			} else if (oppnt.totalPenalty != this.totalPenalty) {
				return oppnt.totalPenalty - this.totalPenalty;
			}
			return oppnt.id - this.id;
		}

		public int hashCode() {
			return id;
		}

		@Override
		public boolean equals(Object oppnt) {
			if (oppnt == null) {
				return false;
			} else if (!(oppnt instanceof Contestant)) {
				return false;
			} else {
				return this.id == ((Contestant) oppnt).id;
			}
		}
	}
}
