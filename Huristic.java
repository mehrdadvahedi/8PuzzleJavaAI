public class Huristic {
  // Goal state
  public static int[][] goalState = Problem.goalState;

  // Number of misplaced tiles
  public static int h1(int[][] state) {
    int count = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (state[i][j] == goalState[i][j]) {
          count++;
        }
      }
    }
    return count;
  }

  // Sum of Manhattan distances of the tiles from their
  // goal positions
  public static int h2(int[][] state) {
    int ans = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        ans += h2_distCalculator(state[i][j], i, j);
      }
    }
    return ans;
  }

  public static int h2_distCalculator(int stateXY, int x, int y) {
    // find state[x][y] in goalState in first
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (stateXY == goalState[i][j]) {
          // now we can calculate simple dist
          return Math.abs(i - x) + Math.abs(j - y);
        }
      }
    }
    // Impossible mode -> !(0<=x<3 && 0<=y<3)
    return -1;
  }

  // Sum of Eucledian distances of the tiles from their
  // goal positions
  public static double h3(int[][] state) {
    double ans = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        ans += h3_eucledianDistCalculator(state[i][j], i, j);
      }
    }
    return ans;
  }

  public static double h3_eucledianDistCalculator(int stateXY, int x, int y) {
    // find state[x][y] in goalState in first
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (stateXY == goalState[i][j]) {
          // now we can calculate eucledian dist
          // 1 1 -> 0 2 => sqrt(|1-0|^2 + |1-2|^2) = sqrt(2)
          return Math.sqrt(Math.pow(Math.abs(i - x), 2) + Math.pow(Math.abs(j - y), 2));
        }
      }
    }
    // Impossible mode -> !(0<=x<3 && 0<=y<3)
    return -1;
  }

  // Number of tiles out of row + Number of tiles out of
  // column
  public static int h4(int[][] state) {
    int ans = 0;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        ans += h4_isOutOfRow(state[i][j], j);
        ans += h4_isOutOfCol(state[i][j], i);
      }
    }
    return ans;
  }

  public static int h4_isOutOfRow(int stateXY, int y) {
    for (int x = 0; x < 3; x++) {
      if (goalState[x][y] == stateXY)
        return 0;
    }
    return 1;
  }

  public static int h4_isOutOfCol(int stateXY, int x) {
    for (int y = 0; y < 3; y++) {
      if (goalState[x][y] == stateXY)
        return 0;
    }
    return 1;
  }

  public static double h(QNode node) {
    double tempH1, tempH2, tempH3, tempH4;
    // tempH1 = h1(node.element.state);
    tempH2 = h2(node.element.state);
    // tempH3 = h3(node.element.state);
    // tempH4 = h4(node.element.state);
    // return Math.max(Math.max(tempH1, tempH2), Math.max(tempH3, tempH4));
    return tempH2;
  }

  public static int g(QNode node) {
    return node.element.path_cost;
  }

  public static double f(QNode node) {
    double ans = g(node) + h(node);
    return ans;
  }

  public static double f1(QNode node) {
    double ans = g(node) + h1(node.element.state);
    return ans;
  }

  public static double f2(QNode node) {
    double ans = g(node) + h2(node.element.state);
    return ans;
  }

  public static double f3(QNode node) {
    double ans = g(node) + h3(node.element.state);
    return ans;
  }

  public static double f4(QNode node) {
    double ans = g(node) + h4(node.element.state);
    return ans;
  }

}
