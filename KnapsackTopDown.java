/*
First Important Thing

There is confusion in names.

Approach	Another Name
Memoization	Top-Down DP
Tabulation	Bottom-Up DP

But in interviews/classes, many people call this table method "Top Down".

Your code above is actually:

Tabulation / Bottom-Up DP

because we build table from small answers to big answers.

How to Convert Recursive Code into DP Table

This is the MOST IMPORTANT CONCEPT in DP.

Step 1 → Write Recursive Function

Recursive logic:

knapsack(n, W)

Meaning:

Maximum profit
using first n items
with capacity W
Step 2 → Identify Changing Variables

In recursion:

knapsack(wt, val, capacity, n)

Changing variables are:

n
capacity

These become:

Rows and Columns of DP table
Step 3 → Create DP Table

Since:

n changes from 0 → n
capacity changes from 0 → W

we create:

dp[n+1][W+1]

DP Meaning
dp[i][j]

means:

Maximum profit
using first i items
with capacity j
Step 4 → Convert Base Case

Recursive base case:

if(n == 0 || W == 0)
    return 0;

Convert into table:

if(i == 0 || j == 0)
    dp[i][j] = 0;

Why?

Because:

Condition	Meaning
i == 0	no items
j == 0	bag capacity 0

Profit becomes 0.

Step 5 → Convert Recursive Calls
Recursive Include Case

Recursive:

val[n-1] + knapsack(W - wt[n-1], n-1)

Table version:

val[i-1] + dp[i-1][j-wt[i-1]]
Recursive Exclude Case

Recursive:

knapsack(W, n-1)

Table version:

dp[i-1][j]
Final Formula

dp[i][j]=max(val[i−1]+dp[i−1][j−wt[i−1]], dp[i−1][j])

How Table Fills

We solve smaller problems first.

Example:

dp[1][1]
dp[1][2]
dp[1][3]
...
dp[4][7]

Every cell uses already solved smaller cells.

Understanding Loop
for(int i=0; i<=n; i++)

Meaning:

Traverse items
for(int j=0; j<=capacity; j++)

Meaning:

Traverse capacities
Understanding This Condition
wt[i - 1] <= j

Meaning:

Can current item fit in bag?

If YES:

include/exclude choice

If NO:

skip item
Why i - 1 ?

Because arrays are:

0 indexed

But DP rows start from:

1

So:

DP Row	Actual Item Index
1	0
2	1
3	2

Hence:

wt[i - 1]
Dry Run Example

Suppose:

wt = {1,3,4,5}
val = {1,4,5,7}
capacity = 7

At:

dp[2][4]

Meaning:

Using first 2 items
with capacity 4

Choices:

Include item 2
4 + dp[1][1]
Exclude item 2
dp[1][4]

Take maximum.

Time Complexity

O(n×W)

Space Complexity

O(n×W)

Full DP Conversion Trick (VERY IMPORTANT)

Whenever converting recursion to DP:

Step	Action
1	Write recursive code
2	Find changing variables
3	Create DP table
4	Convert base case
5	Convert recursive calls into table formula
6	Fill table systematically
IBH (Intuition Based Hypothesis)
Hypothesis

Recursive solution repeatedly solves same subproblems.

Instead of recursion:

Store answers in table
Build from smaller answers
Reuse them directly


*/


public class KnapsackTopDown {

    public static int knapsack(int[] wt, int[] val, int capacity, int n) {

        // DP Table
        int[][] dp = new int[n + 1][capacity + 1];

        // Build table row by row
        for (int i = 0; i <= n; i++) {

            for (int j = 0; j <= capacity; j++) {

                // Base Case
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                }

                // If item can fit
                else if (wt[i - 1] <= j) {

                    int include = val[i - 1]
                            + dp[i - 1][j - wt[i - 1]];

                    int exclude = dp[i - 1][j];

                    dp[i][j] = Math.max(include, exclude);
                }

                // If item cannot fit
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][capacity];
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