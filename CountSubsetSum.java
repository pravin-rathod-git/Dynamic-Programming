public class CountSubsetSum {

    public static int countSubsets(int[] arr, int sum) {

        int n = arr.length;

        // DP table
        // rows -> elements
        // cols -> sums
        int[][] dp = new int[n + 1][sum + 1];

        // Sum 0 can always be formed
        // using empty subset
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill DP table
        for (int i = 1; i <= n; i++) {

            for (int j = 0; j <= sum; j++) {

                // Current element
                int current = arr[i - 1];

                // If current element can be included
                if (current <= j) {

                    // include + exclude
                    dp[i][j] =
                            dp[i - 1][j - current]
                            + dp[i - 1][j];

                } else {

                    // Cannot include current element
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Final answer
        return dp[n][sum];
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 3};

        int sum = 6;

        int result = countSubsets(arr, sum);

        System.out.println(result);
    }
}