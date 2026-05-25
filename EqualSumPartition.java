public class EqualSumPartition {

    public static boolean canPartition(int[] arr) {

        // Step 1:
        // Find total sum of all elements
        int totalSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        // Step 2:
        // If total sum is odd,
        // equal partition is impossible
        if (totalSum % 2 != 0) {
            return false;
        }

        // Step 3:
        // Target sum we need to find
        // because two subsets must have equal sum
        int target = totalSum / 2;

        int n = arr.length;

        // Step 4:
        // DP table creation
        // rows -> elements
        // columns -> sum values
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Step 5:
        // Sum 0 is always possible
        // using empty subset
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Step 6:
        // Fill the DP table
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= target; j++) {

                // Current element
                int currentElement = arr[i - 1];

                // If current element is smaller
                // or equal to current target sum
                if (currentElement <= j) {

                    // Two choices:
                    // 1. Include current element
                    // 2. Exclude current element
                    dp[i][j] =
                            dp[i - 1][j - currentElement]
                            || dp[i - 1][j];

                } else {

                    // Cannot include current element
                    // because it is bigger than j
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Final answer
        // Can we make target sum using all elements?
        return dp[n][target];
    }

    public static void main(String[] args) {

        int[] arr = {1, 5, 11, 5};

        boolean result = canPartition(arr);

        System.out.println(result);
    }
}