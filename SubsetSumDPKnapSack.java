/*The Subset Sum Problem is a classic Dynamic Programming problem.

Problem Statement

Given an array of integers and a target sum, determine whether there exists a subset of the array whose sum is equal to the target sum.

Example:

Array = [3, 34, 4, 12, 5, 2]
Target Sum = 9

Output:

True

Because subset [4, 5] gives sum 9.

Idea Behind DP

At every element, we have 2 choices:

Include the current element
Exclude the current element

We store already computed results using DP.

DP State

Let:

dp[i][j]

Mean:

Is it possible to get sum j using first i elements?

DP Formula

If current element is greater than sum:

dp[i][j] = dp[i-1][j]

Otherwise:

dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]]
Visualization Formula

Central recurrence:

dp[i][j]=dp[i−1][j]∨dp[i−1][j−arr[i−1]]

Dry Run

Array:

[3, 4, 5]

Target:

9

We check:

Can we make 9 using subsets?

Possible subset:

4 + 5 = 9

Answer = TRUE*/

public class SubsetSumDPKnapSack {

    static boolean subsetSum(int[] arr, int sum) {

        int n = arr.length;

        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Sum 0 is always possible
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill DP table
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= sum; j++) {

                // If current element is greater than sum
                if (arr[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                }

                else {

                    // Include OR Exclude
                    dp[i][j] =
                        dp[i - 1][j] ||
                        dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }

    public static void main(String[] args) {

        int[] arr = {3, 34, 4, 12, 5, 2};

        int sum = 9;

        if (subsetSum(arr, sum)) {
            System.out.println("Subset exists");
        }

        else {
            System.out.println("Subset does not exist");
        }
    }
}