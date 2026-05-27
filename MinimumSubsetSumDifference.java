/*
Minimum Subset Sum Difference
Problem Statement

Given an array of positive integers, divide it into two subsets such that:

|sum1 - sum2|

is minimum.

Return the minimum difference.

Example
arr = {1, 6, 11, 5}

Possible partitions:

{1,6,5} = 12
{11} = 11

Difference:

|12 - 11| = 1

Answer:

1
Core Idea

Suppose:

totalSum = S

If one subset has sum:

s1

Then second subset automatically has:

s2 = S - s1

Difference:

|s2 - s1|

Substitute:

|(S - s1) - s1|

Simplify:

|S - 2*s1|

So we need to find a subset sum s1
such that:

|S - 2*s1|

is minimum.

Formula

min∣S−2s
1
∣

DP Approach

We first solve:

Subset Sum DP

Find all possible subset sums.

Then check only till:

totalSum / 2

because after that differences repeat symmetrically.
*/


public class MinimumSubsetSumDifference {

    public static int minDifference(int[] arr) {

        int n = arr.length;

        // Step 1:
        // Calculate total sum
        int totalSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        // Step 2:
        // DP table for subset sum
        boolean[][] dp = new boolean[n + 1][totalSum + 1];

        // Sum 0 is always possible
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Step 3:
        // Fill DP table
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= totalSum; j++) {

                int current = arr[i - 1];

                // If current element can be included
                if (current <= j) {

                    dp[i][j] =
                            dp[i - 1][j - current]
                            || dp[i - 1][j];

                } else {

                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Step 4:
        // Find minimum difference
        int minDiff = Integer.MAX_VALUE;

        // Check only till totalSum/2
        for (int s1 = 0; s1 <= totalSum / 2; s1++) {

            // If subset sum s1 is possible
            if (dp[n][s1]) {

                int s2 = totalSum - s1;

                int diff = Math.abs(s2 - s1);

                minDiff = Math.min(minDiff, diff);
            }
        }

        return minDiff;
    }

    public static void main(String[] args) {

        int[] arr = {1, 6, 11, 5};

        int result = minDifference(arr);

        System.out.println(result);
    }
}