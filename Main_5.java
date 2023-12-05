import java.util.Scanner;

public class KnapsackDynamicProgramming {
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        System.out.println("Enter the weights of the items:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.println("Enter the values of the items:");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();

        int max_value = knapsack(weights, values, capacity);
        System.out.println("Maximum Value: " + max_value);

        scanner.close();
    }
}
// 2.	Using backtracking

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;

// public class KnapsackBacktracking {
//     private static int max_value = Integer.MIN_VALUE;
//     private static List<Integer> selected_items = new ArrayList<>();

//     public static void knapsack(int[] weights, int[] values, int capacity, int index, int current_weight, int current_value, List<Integer> current_selection) {
//         if (index == weights.length) {
//             if (current_value > max_value) {
//                 max_value = current_value;
//                 selected_items = new ArrayList<>(current_selection);
//             }
//             return;
//         }

//         if (current_weight + weights[index] <= capacity) {
//             current_selection.add(index);
//             knapsack(weights, values, capacity, index + 1, current_weight + weights[index], current_value + values[index], current_selection);
//             current_selection.remove(current_selection.size() - 1);
//         }

//         knapsack(weights, values, capacity, index + 1, current_weight, current_value, current_selection);
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the number of items: ");
//         int n = scanner.nextInt();

//         int[] weights = new int[n];
//         int[] values = new int[n];

//         System.out.println("Enter the weights of the items:");
//         for (int i = 0; i < n; i++) {
//             weights[i] = scanner.nextInt();
//         }

//         System.out.println("Enter the values of the items:");
//         for (int i = 0; i < n; i++) {
//             values[i] = scanner.nextInt();
//         }

//         System.out.print("Enter the knapsack capacity: ");
//         int capacity = scanner.nextInt();

//         List<Integer> current_selection = new ArrayList<>();
//         knapsack(weights, values, capacity, 0, 0, 0, current_selection);
//         System.out.println("Maximum Value: " + max_value);
//         System.out.println("Selected Items: " + selected_items);

//         scanner.close();
//     }
// 3.	Using branch and bound

// import java.util.Arrays;
// import java.util.Comparator;
// import java.util.PriorityQueue;
// import java.util.Scanner;

// public class KnapsackBranchAndBound {
//     static class Item {
//         int weight;
//         int value;
//         double ratio;
//         int index;

//         public Item(int weight, int value, int index) {
//             this.weight = weight;
//             this.value = value;
//             this.index = index;
//             this.ratio = (double) value / weight;
//         }
//     }

//     public static int knapsack(int[] weights, int[] values, int capacity) {
//         int n = weights.length;
//         Item[] items = new Item[n];

//         for (int i = 0; i < n; i++) {
//             items[i] = new Item(weights[i], values[i], i);
//         }

//         Arrays.sort(items, Comparator.comparingDouble(o -> -o.ratio));
//         PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> -o.ratio));

//         int currentWeight = 0;
//         int currentValue = 0;
//         int maxValue = 0;

//         for (int i = 0; i < n; i++) {
//             if (currentWeight + items[i].weight <= capacity) {
//                 currentWeight += items[i].weight;
//                 currentValue += items[i].value;
//                 pq.add(items[i]);
//             } else {
//                 double remainingRatio = items[i].ratio;
//                 while (!pq.isEmpty() && remainingRatio > pq.peek().ratio) {
//                     Item item = pq.poll();
//                     currentWeight -= item.weight;
//                     currentValue -= item.value;
//                 }
//                 currentWeight += items[i].weight;
//                 currentValue += items[i].value;
//                 pq.add(items[i]);
//             }

//             maxValue = Math.max(maxValue, currentValue);
//         }

//         return maxValue;
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.print("Enter the number of items: ");
//         int n = scanner.nextInt();

//         int[] weights = new int[n];
//         int[] values = new int[n];

//         System.out.println("Enter the weights of the items:");
//         for (int i = 0; i < n; i++) {
//             weights[i] = scanner.nextInt();
//         }

//         System.out.println("Enter the values of the items:");
//         for (int i = 0; i < n; i++) {
//             values[i] = scanner.nextInt();
//         }

//         System.out.print("Enter the knapsack capacity: ");
//         int capacity = scanner.nextInt();

//         int max_value = knapsack(weights, values, capacity);
//         System.out.println("Maximum Value: " + max_value);

//         scanner.close();
//     }
// }
