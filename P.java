public class P {
  // I use this class as my personal library in all my java projects
  // It can be so usefull :)
  public static final String reset = "\u001B[0m";
  public static final String black = "\u001B[30m";
  public static final String red = "\u001B[31m";
  public static final String green = "\u001B[32m";
  public static final String yellow = "\u001B[33m";
  public static final String blue = "\u001B[34m";
  public static final String purple = "\u001B[35m";
  public static final String cyan = "\u001B[36m";
  public static final String white = "\u001B[37m";
  public static final String bgBlack = "\u001B[40m";
  public static final String bgRed = "\u001B[41m";
  public static final String bgGreen = "\u001B[42m";
  public static final String bgYellow = "\u001B[43m";
  public static final String bgBlue = "\u001B[44m";
  public static final String bgPurple = "\u001B[45m";
  public static final String bgCyan = "\u001B[46m";
  public static final String bgWhite = "\u001B[47m";

  public static void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void print(Object arg) {
    System.out.print(arg);
  }

  public static void print(Object... args) {
    for (Object arg : args) {
      System.out.print(arg);
      System.out.print(" ");
    }
  }

  public static void println() {
    System.out.println();
  }

  public static void println(Object arg) {
    System.out.println(arg);
  }

  public static void println(Object... args) {
    for (Object arg : args) {
      System.out.print(arg);
      System.out.print(" ");
    }
    System.out.println();
  }

  public static void line(Object arg, int len) {
    for (int i = 0; i < len; i++) {
      System.out.print(arg);
    }
    System.out.println();
  }

  public static void line(Object arg) {
    int len = 100;
    for (int i = 0; i < len; i++) {
      System.out.print(arg);
    }
    System.out.println();
  }

  public static void line(int len) {
    Object arg = "*";
    for (int i = 0; i < len; i++) {
      System.out.print(arg);
    }
    System.out.println();
  }

  public static void line() {
    Object arg = "*";
    int len = 100;
    for (int i = 0; i < len; i++) {
      System.out.print(arg);
    }
    System.out.println();
  }

  public static void print1D(int[] args) {
    for (int arg : args) {
      System.out.print(arg + "\t");
    }
    System.out.println();
  }

  public static void print1D(double[] args) {
    for (double arg : args) {
      System.out.print(arg + "\t");
    }
    System.out.println();
  }

  public static void print2D(int[][] args2D) {
    for (int[] args : args2D) {
      for (int arg : args) {
        System.out.print(arg + "\t");
      }
      System.out.println();
    }
  }

  public static void print2D(double[][] args2D) {
    for (double[] args : args2D) {
      for (double arg : args) {
        System.out.print(arg + "\t");
      }
      System.out.println();
    }
  }

  public static void puzzle(int[][] args2D) {
    System.out.print(reset);
    int l = 0;
    for (int[] args : args2D) {
      l = args.length;
      System.out.print(blue);
      for (int i = 0; i < l; i++) {
        System.out.print("+---");
      }
      System.out.println("+");
      System.out.print("|");
      for (int arg : args) {
        if (arg == -1) {
          System.out.print(bgRed + "   " + reset + blue + "|");
        } else {
          System.out.print(" " + green + arg + blue + " |");
        }
      }
      System.out.println();
    }
    for (int i = 0; i < l; i++) {
      System.out.print("+---");
    }
    System.out.print("+");
    System.out.println(reset);
  }

  public static void mergeSort(double[] a, int n) {
    if (n < 2) {
      return;
    }
    int mid = n / 2;
    double[] l = new double[mid];
    double[] r = new double[n - mid];

    for (int i = 0; i < mid; i++) {
      l[i] = a[i];
    }
    for (int i = mid; i < n; i++) {
      r[i - mid] = a[i];
    }
    mergeSort(l, mid);
    mergeSort(r, n - mid);

    merge(a, l, r, mid, n - mid);
  }

  public static void merge(
      double[] a, double[] l, double[] r, int left, int right) {

    int i = 0, j = 0, k = 0;
    while (i < left && j < right) {
      if (l[i] <= r[j]) {
        a[k++] = l[i++];
      } else {
        a[k++] = r[j++];
      }
    }
    while (i < left) {
      a[k++] = l[i++];
    }
    while (j < right) {
      a[k++] = r[j++];
    }
  }

  public static double[] randArray(int size) {
    double[] ans = new double[size];
    for (int i = 0; i < size; i++) {
      ans[i] = Math.random();
    }
    return ans;
  }

  public static int binarySearch(double arr[], int l, int r, double x) {
    if (r >= l) {
      int mid = l + (r - l) / 2;

      // If the element is present at the
      // middle itself
      if (arr[mid] == x)
        return mid;

      // If element is smaller than mid, then
      // it can only be present in left subarray
      if (arr[mid] > x)
        return binarySearch(arr, l, mid - 1, x);

      // Else the element can only be present
      // in right subarray
      return binarySearch(arr, mid + 1, r, x);
    }

    // We reach here when element is not present
    // in array
    return -1;
  }

  public static int[] randPerm(int size) {
    double[] temp = randArray(size);
    double[] temp2 = copy(temp);
    int[] ans = new int[size];
    mergeSort(temp, size);
    for (int i = 0; i < size; i++) {
      ans[i] = binarySearch(temp, 0, size - 1, temp2[i]);
    }
    return ans;
  }

  public static double[] copy(double[] args) {
    double[] ans = new double[args.length];
    for (int i = 0; i < args.length; i++) {
      ans[i] = args[i];
    }
    return ans;
  }

  public static int[][] copy(int[][] args) {
    int[][] ans = new int[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        ans[i][j] = args[i][j];
      }
    }
    return ans;
  }
}
