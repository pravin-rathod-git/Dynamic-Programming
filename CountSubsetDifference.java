public class CountSubsetDifference {

    public static int countSubsets(int[] arr, int diff) {

        int totalSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        // Invalid case
        if ((totalSum + diff) % 2 != 0) {
            return 0;
        }

        int target = (totalSum + diff) / 2;

        int n = arr.length;

        int[][] dp = new int[n + 1][target + 1];

        // Initialization
        // Sum 0 can be formed by empty subset
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // DP Table Filling
        for (int i = 1; i <= n; i++) {

            for (int j = 0; j <= target; j++) {

                // Include + Exclude
                if (arr[i - 1] <= j) {

                    dp[i][j] =
                        dp[i - 1][j - arr[i - 1]]
                        + dp[i - 1][j];

                } else {

                    // Exclude only
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][target];
    }

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 3};

        int diff = 1;

        System.out.println(
            "Count = " + countSubsets(arr, diff)
        );
    }
}