//The problem formulation 
class Problem {
	int[][] initial_state;
	public static int[][] goalState = { { 1, 2, 3 }, { 8, -1, 4 }, { 7, 6, 5 } };

	Problem(int[][] initialState) {
		this.initial_state = initialState;
	}

	// make random possible initial state
	Problem() {
		int[][] temp = new int[3][3];
		do {
			temp = randPuzzle();
		} while (!isPossible(temp));
		this.initial_state = temp;
	}

	public int[][] Successor(int[][] s, String action) {
		int[][] state = new int[3][3];
		for (int k1 = 0; k1 <= 2; k1++)
			for (int k2 = 0; k2 <= 2; k2++)
				state[k1][k2] = s[k1][k2];
		int i = find_i(state);
		int j = find_j(state);
		if (i == -1 || j == -1) {
			System.out.println("Error: I couldn't find the empty place !!!!!!");
			return state;
		}
		switch (action) {
			case "u":
				if (i != 0) {
					state[i][j] = state[i - 1][j];
					state[i - 1][j] = -1;
				}
				break;

			case "up":
				if (i != 0) {
					state[i][j] = state[i - 1][j];
					state[i - 1][j] = -1;
				}
				break;

			case "d":
				if (i != 2) {
					state[i][j] = state[i + 1][j];
					state[i + 1][j] = -1;
				}
				break;

			case "down":
				if (i != 2) {
					state[i][j] = state[i + 1][j];
					state[i + 1][j] = -1;
				}
				break;

			case "l":
				if (j != 0) {
					state[i][j] = state[i][j - 1];
					state[i][j - 1] = -1;
				}
				break;

			case "left":
				if (j != 0) {
					state[i][j] = state[i][j - 1];
					state[i][j - 1] = -1;
				}
				break;

			case "r":
				if (j != 2) {
					state[i][j] = state[i][j + 1];
					state[i][j + 1] = -1;
				}
				break;

			case "right":
				if (j != 2) {
					state[i][j] = state[i][j + 1];
					state[i][j + 1] = -1;
				}
				break;

		}
		return state;
	}

	public boolean Goal_Test(int[][] state) {
		if (state[0][0] == 1 && state[0][1] == 2 && state[0][2] == 3
				&& state[1][0] == 8 && state[1][1] == -1 && state[1][2] == 4
				&& state[2][0] == 7 && state[2][1] == 6 && state[2][2] == 5)
			return true;
		else
			return false;
	}

	public int find_i(int[][] state) {
		for (int i = 0; i <= 2; i++)
			for (int j = 0; j <= 2; j++)
				if (state[i][j] == -1)
					return i;
		return -1;
	}

	public int find_j(int[][] state) {
		for (int i = 0; i <= 2; i++)
			for (int j = 0; j <= 2; j++)
				if (state[i][j] == -1)
					return j;
		return -1;
	}

	public static int misplaced(int[][] state) {
		int ans = 0, temp;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				temp = i * 3 + j + 1;
				if (state[i][j] < temp && state[i][j] != -1) {
					ans++;
				}
			}
		}
		return ans;
	}

	public static boolean isPossible(int[][] state) {
		int initialStateMP, goalStateMP;
		initialStateMP = misplaced(state);
		goalStateMP = misplaced(goalState);
		if (initialStateMP % 2 == 0 && goalStateMP % 2 == 0)
			return true;
		if (initialStateMP % 2 != 0 && goalStateMP % 2 != 0)
			return true;
		return false;
	}

	private int[][] randPuzzle() {
		int[] perm = P.randPerm(9);
		int[][] ans = new int[3][3];
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (perm[k] == 0) {
					ans[i][j] = -1;
				} else {
					ans[i][j] = perm[k];
				}
				k++;
			}
		}
		return ans;
	}
}
