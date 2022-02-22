//The Main Class. 
class Puzzle {
	public static void main(String args[]) {
		int[][] initial_state = { { 1, -1, 3 }, { 8, 6, 4 }, { 7, 5, 2 } };
		int[][] S2 = { { 1, 2, 3 }, { 8, 6, 4 }, { 7, 5, -1 } };
		int[][] S4 = { { 1, 2, 3 }, { 7, -1, 4 }, { 6, 8, 5 } };
		int[][] S6 = { { 1, 3, -1 }, { 7, 2, 4 }, { 6, 8, 5 } };
		int[][] S8 = { { 1, 3, 4 }, { 7, -1, 2 }, { 6, 8, 5 } };
		int[][] S10 = { { -1, 1, 4 }, { 7, 3, 2 }, { 6, 8, 5 } };
		int[][] S20 = { { 6, -1, 8 }, { 7, 2, 3 }, { 4, 1, 5 } };
		Problem problem = new Problem(S4);
		PSearcher searcher = new PSearcher(problem);
		// P.clear();
		double last_time = System.nanoTime();
		ActionSequence AS = searcher.Search("ASTAR");
		long time = System.nanoTime();
		P.println((time - last_time) / 1000000000);

		AS.printList();
		AS.printActionSequence(problem);
	}
}
