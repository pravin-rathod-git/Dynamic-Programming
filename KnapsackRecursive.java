public class KnapsackRecursive {

    // Recursive function for 0/1 Knapsack
    public static int knapsack(int[] wt, int[] val, int capacity, int n) {

        // Base Case
        if (n == 0 || capacity == 0) {
            return 0;
        }

        // If current item's weight is greater than capacity,
        // skip this item
        if (wt[n - 1] > capacity) {
            return knapsack(wt, val, capacity, n - 1);
        }

        // Choice Diagram:
        // 1. Include the item
        // 2. Exclude the item
        else {
            int include = val[n - 1] +
                    knapsack(wt, val, capacity - wt[n - 1], n - 1);

            int exclude = knapsack(wt, val, capacity, n - 1);

            return Math.max(include, exclude);
        }
    }

    public static void main(String[] args) {

        int[] wt = {1, 3, 4, 5};
        int[] val = {1, 4, 5, 7};

        int capacity = 7;
        int n = wt.length;

        int result = knapsack(wt, val, capacity, n);

        System.out.println("Maximum Profit = " + result);
    }
}