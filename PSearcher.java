//This class solves the 8-puzzle problem 
class PSearcher {
	PSTree tree;
	Queue queue;
	Problem problem;

	// considering a default goal state.
	PSearcher(Problem p) {
		problem = p;
		// generate a search node according to initial state.
		PSTNode search_node = new PSTNode(problem.initial_state, null, null, 0, 0, null, null, null, null);
		tree = new PSTree(search_node);

		// add the generated search node to the search queue.
		QNode qn = new QNode(search_node, null, null);
		queue = new Queue();
		queue.addFirst(qn);

	}

	// the main search method.
	public ActionSequence Search(String strategy) {
		switch (strategy) {
			case "DFS":
				return DFS();
			case "BFS":
				return BFS();
			case "IDS":
				return IDS();
			case "GREEDY":
				return GREEDY();
			case "UCS":
				return UCS();
			case "ASTAR":
				return ASTAR();
			default:
				return null;
		}

	}

	// the main search method with one parameter
	public ActionSequence Search(String strategy, int parameter) {
		switch (strategy) {
			case "DLS":
				return DLS(parameter);
			default:
				return null;
		}

	}

	// Depth First Search Strategy.
	private ActionSequence DFS() {
		System.out.println(P.red + "DFS Search Started" + P.reset);
		boolean find = false;
		QNode current;
		int mehrdad = -1;
		do {
			mehrdad++;
			// remove the first node from the queue.
			current = queue.removeFirst();

			// P.line();
			// P.println("Queue: " + queue.size + " Depth: " + (current.element.depth + 1));
			P.clear();
			P.print("Queue: " + queue.size + "\tDepth: " + (current.element.depth + 1));
			P.println("\tCost: " + current.element.path_cost + "\t\titer: " + (mehrdad));
			// printState(current.eleme/nt.state);
			// P.println("\n\n\n");

			// test the state of the node:: if the state of the current node is satisfies
			// the goal state
			if (problem.Goal_Test(current.element.state))
				find = true;

			// else, expand current node.
			else {
				PSTNode up_child = Expand(current.element, "u");
				tree.addNodeUp(up_child, current.element);
				QNode up_child_qn = new QNode(up_child, null, null);
				queue.addFirst(up_child_qn);

				PSTNode down_child = Expand(current.element, "d");
				tree.addNodeDown(down_child, current.element);
				QNode down_child_qn = new QNode(down_child, null, null);
				queue.addFirst(down_child_qn);

				PSTNode left_child = Expand(current.element, "l");
				tree.addNodeLeft(left_child, current.element);
				QNode left_child_qn = new QNode(left_child, null, null);
				queue.addFirst(left_child_qn);

				PSTNode right_child = Expand(current.element, "r");
				tree.addNodeRight(right_child, current.element);
				QNode right_child_qn = new QNode(right_child, null, null);
				queue.addFirst(right_child_qn);
			}
		} while (!find);
		return Path2Root(current.element);
	}

	// Breadth First Search Strategy.
	private ActionSequence BFS() {
		P.println(P.red + "BFS Search Started" + P.reset);
		boolean find = false;
		QNode current;
		int mehrdad = -1;
		do {
			mehrdad++;
			// remove the first node from the queue.
			current = queue.removeFirst();

			// P.line();
			// P.println("Queue: " + queue.size + " Depth: " + (current.element.depth + 1));
			// printState(current.element.state);
			// P.println("\n\n\n");
			P.clear();
			P.print("Queue: " + queue.size + "\tDepth: " + (current.element.depth + 1));
			P.println("\tCost: " + current.element.path_cost + "\t\titer: " + (mehrdad));

			// test the state of the node:: if the state of the current node is satisfies
			// the goal state
			if (problem.Goal_Test(current.element.state))
				find = true;

			// else, expand current node.
			else {
				PSTNode up_child = Expand(current.element, "u");
				tree.addNodeUp(up_child, current.element);
				QNode up_child_qn = new QNode(up_child, null, null);
				queue.addLast(up_child_qn);

				PSTNode down_child = Expand(current.element, "d");
				tree.addNodeDown(down_child, current.element);
				QNode down_child_qn = new QNode(down_child, null, null);
				queue.addLast(down_child_qn);

				PSTNode left_child = Expand(current.element, "l");
				tree.addNodeLeft(left_child, current.element);
				QNode left_child_qn = new QNode(left_child, null, null);
				queue.addLast(left_child_qn);

				PSTNode right_child = Expand(current.element, "r");
				tree.addNodeRight(right_child, current.element);
				QNode right_child_qn = new QNode(right_child, null, null);
				queue.addLast(right_child_qn);

			}

		} while (!find);

		return Path2Root(current.element);
	}

	// Depth-Limited Search Strategy.
	private ActionSequence DLS(int L) {
		P.println(P.red + "DLS Search Started" + P.reset);
		boolean find = false;
		QNode current;
		int mehrdad = -1;
		do {
			mehrdad++;
			if (queue.size == 0)
				return null;

			// remove the first node from the queue.
			current = queue.removeFirst();

			// P.line();
			// P.println("Queue: " + queue.size + " Depth: " + (current.element.depth + 1));
			// printState(current.element.state);
			// P.println("\n\n\n");
			P.clear();
			P.print("Queue: " + queue.size + "\tDepth: " + (current.element.depth + 1));
			P.println("\tCost: " + current.element.path_cost + "\t\titer: " + (mehrdad));

			// test the state of the node:: if the state of the current node is satisfies
			// the goal state
			if (problem.Goal_Test(current.element.state))
				find = true;

			// else, expand current node.
			else if (current.element.depth <= L - 1) {
				PSTNode up_child = Expand(current.element, "u");
				tree.addNodeUp(up_child, current.element);
				QNode up_child_qn = new QNode(up_child, null, null);
				queue.addFirst(up_child_qn);

				PSTNode down_child = Expand(current.element, "d");
				tree.addNodeDown(down_child, current.element);
				QNode down_child_qn = new QNode(down_child, null, null);
				queue.addFirst(down_child_qn);

				PSTNode left_child = Expand(current.element, "l");
				tree.addNodeLeft(left_child, current.element);
				QNode left_child_qn = new QNode(left_child, null, null);
				queue.addFirst(left_child_qn);

				PSTNode right_child = Expand(current.element, "r");
				tree.addNodeRight(right_child, current.element);
				QNode right_child_qn = new QNode(right_child, null, null);
				queue.addFirst(right_child_qn);
			}

		} while (!find);

		return Path2Root(current.element);
	}

	// Iterative Deeping Search Strategy.
	private ActionSequence IDS() {
		P.println(P.red + "ILS Search Started" + P.reset);
		boolean find = false;
		QNode current;
		int l = 0;
		while (true) {
			PSearcher searcher = new PSearcher(problem);
			ActionSequence AS = searcher.Search("DLS", l);
			if (AS != null)
				return AS;
			l++;
		}
	}

	//
	private ActionSequence UCS() {
		P.println(P.red + "UCS Search Started" + P.reset);
		boolean find = false;
		QNode current;
		int mehrdad = 0;
		do {
			// P.println(mehrdad);
			// remove the first node from the queue.
			current = queue.removeFirst();
			// P.println("c-cost:", current.element.path_cost);
			// if (queue.size > 150) {
			// // queue.printQueue();
			// return null;
			// }

			// P.line();
			P.clear();
			P.print("Queue: " + queue.size + "\tDepth: " + (current.element.depth + 1));
			P.println("\tCost: " + current.element.path_cost + "\t\titer: " + (mehrdad));
			// printState(current.element.state);
			// P.println("\n\n\n");

			// test the state of the node:: if the state of the current node is satisfies
			// the goal state

			if (problem.Goal_Test(current.element.state))
				find = true;

			// else, expand current node.
			else {
				PSTNode up_child = Expand(current.element, "u");
				tree.addNodeUp(up_child, current.element);
				QNode up_child_qn = new QNode(up_child, null, null);
				queue.addSortedByG(up_child_qn);

				PSTNode down_child = Expand(current.element, "d");
				tree.addNodeDown(down_child, current.element);
				QNode down_child_qn = new QNode(down_child, null, null);
				queue.addSortedByG(down_child_qn);

				PSTNode left_child = Expand(current.element, "l");
				tree.addNodeLeft(left_child, current.element);
				QNode left_child_qn = new QNode(left_child, null, null);
				queue.addSortedByG(left_child_qn);

				PSTNode right_child = Expand(current.element, "r");
				tree.addNodeRight(right_child, current.element);
				QNode right_child_qn = new QNode(right_child, null, null);
				queue.addSortedByG(right_child_qn);
			}
			// P.println(queue.size);
			mehrdad++;
		} while (!find);
		return Path2Root(current.element);
	}

	private ActionSequence GREEDY() {
		P.println(P.red + "UCS Search Started" + P.reset);
		boolean find = false;
		QNode current;
		int mehrdad = 0;
		do {
			// P.println(mehrdad);
			// remove the first node from the queue.
			current = queue.removeFirst();
			// P.println("c-cost:", current.element.path_cost);
			// if (queue.size > 150) {
			// // queue.printQueue();
			// return null;
			// }

			// P.line();
			P.clear();
			P.print("Queue: " + queue.size + "\tDepth: " + (current.element.depth + 1));
			P.println("\tCost: " + current.element.path_cost + "\t\titer: " + (mehrdad));
			// printState(current.element.state);
			// P.println("\n\n\n");

			// test the state of the node:: if the state of the current node is satisfies
			// the goal state

			if (problem.Goal_Test(current.element.state))
				find = true;

			// else, expand current node.
			else {
				PSTNode up_child = Expand(current.element, "u");
				tree.addNodeUp(up_child, current.element);
				QNode up_child_qn = new QNode(up_child, null, null);
				queue.addSortedByH(up_child_qn);

				PSTNode down_child = Expand(current.element, "d");
				tree.addNodeDown(down_child, current.element);
				QNode down_child_qn = new QNode(down_child, null, null);
				queue.addSortedByH(down_child_qn);

				PSTNode left_child = Expand(current.element, "l");
				tree.addNodeLeft(left_child, current.element);
				QNode left_child_qn = new QNode(left_child, null, null);
				queue.addSortedByH(left_child_qn);

				PSTNode right_child = Expand(current.element, "r");
				tree.addNodeRight(right_child, current.element);
				QNode right_child_qn = new QNode(right_child, null, null);
				queue.addSortedByH(right_child_qn);
			}
			// P.println(queue.size);
			mehrdad++;
		} while (!find);
		return Path2Root(current.element);
	}

	private ActionSequence ASTAR() {
		P.println(P.red + "UCS Search Started" + P.reset);
		boolean find = false;
		QNode current;
		int iter = 0;
		do {
			// P.println(mehrdad);
			// remove the first node from the queue.
			current = queue.removeFirst();
			// P.println("c-cost:", current.element.path_cost);
			// if (queue.size > 150) {
			// // queue.printQueue();
			// return null;
			// }

			// P.line();
			P.clear();
			P.print("Queue: " + queue.size + "\tDepth: " + (current.element.depth + 1));
			P.println("\tCost: " + current.element.path_cost + "\t\titer: " + (iter));
			P.println("\th: " + Huristic.h(current) + "\tf: " + Huristic.f(current));
			// printState(current.element.state);
			// P.println("\n\n\n");

			// test the state of the node:: if the state of the current node is satisfies
			// the goal state

			if (problem.Goal_Test(current.element.state))
				find = true;

			// else, expand current node.
			else {
				PSTNode up_child = Expand(current.element, "u");
				tree.addNodeUp(up_child, current.element);
				QNode up_child_qn = new QNode(up_child, null, null);
				queue.addSortedByF(up_child_qn);

				PSTNode down_child = Expand(current.element, "d");
				tree.addNodeDown(down_child, current.element);
				QNode down_child_qn = new QNode(down_child, null, null);
				queue.addSortedByF(down_child_qn);

				PSTNode left_child = Expand(current.element, "l");
				tree.addNodeLeft(left_child, current.element);
				QNode left_child_qn = new QNode(left_child, null, null);
				queue.addSortedByF(left_child_qn);

				PSTNode right_child = Expand(current.element, "r");
				tree.addNodeRight(right_child, current.element);
				QNode right_child_qn = new QNode(right_child, null, null);
				queue.addSortedByF(right_child_qn);
			}
			// P.println(queue.size);
			iter++;
		} while (!find);
		return Path2Root(current.element);
	}

	// this method returns the path from a node to the root in the search tree.
	private ActionSequence Path2Root(PSTNode node) {
		ActionSequence path = new ActionSequence();
		while (node.parent != null) {
			Action action = new Action(node.action, null, null);
			path.addLast(action);
			node = node.parent;
		}
		return path;
	}

	// this method expands a given node according to different actions.
	private PSTNode Expand(PSTNode node, String action) {
		int[][] s = new int[3][3];
		s = problem.Successor(node.state, action);
		PSTNode child = new PSTNode(s, node, action, (node.depth + 1), (node.path_cost + 1), null, null, null, null);
		// P.println(child.path_cost);

		return child;
	}

	// Print a given state
	// Go to P.puzzle :)
	private void printState(int[][] state) {
		System.out.println("\n\n\n");
		for (int i = 0; i <= 2; i++)
			System.out.println(" \t\t\t" + state[i][0] + "\t" + state[i][1] + "\t" + state[i][2]);
	}
}
