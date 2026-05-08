/*

Understanding 0/1 Knapsack Memoization Step by Step

Your program solves the 0/1 Knapsack Problem using Recursion + Memoization (DP).

Problem Statement

We have:

Item	Weight	Value
1	1	1
2	3	4
3	4	5
4	5	7

Bag Capacity = 7

Goal:

Pick items such that:
1. Total weight <= capacity
2. Total value is maximum
Function Meaning
knapsack(wt, val, capacity, n, dp)

Meaning:

Using first n items,
find maximum profit
for given capacity.
Why n + 1 and capacity + 1 ?
int[][] dp = new int[n + 1][capacity + 1];

Suppose:

n = 4
capacity = 7

Then array size becomes:

dp[5][8]

Why?

Because we also store:

0 items
0 capacity
DP Table Meaning
dp[i][j]

means:

Maximum profit using first i items
with bag capacity j
Why Row 0 Exists?

Row 0 means:

No items available

Profit = 0.

Why Column 0 Exists?

Column 0 means:

Bag capacity is 0

Cannot take anything.

Profit = 0.

Visual Structure
        Capacity
       0 1 2 3 4 5 6 7

Items 0
      1
      2
      3
      4

So:

Need extra row and column.

That is why:

dp[n+1][W+1]

Why wt[n - 1] ?

Arrays in Java are:

0 indexed

Example:

Index	Weight
0	1
1	3
2	4
3	5

But n starts from:

1 to 4

So:

n	Actual Item Index
1	0
2	1
3	2
4	3

Therefore:

wt[n - 1]

accesses correct item.

Example

Suppose:

n = 4

Then:

wt[n - 1]
= wt[3]
= 5

which is the last item.

Base Case
if (n == 0 || capacity == 0)

Meaning:

Case 1
No items left
Case 2
Bag is full

Profit becomes:

0
Memoization Check
if (dp[n][capacity] != -1)

Meaning:

Already solved this problem before.

So directly return answer.

This avoids repeated recursion.

What Are We Storing in dp ?

We store:

Maximum profit for a state

State means:

(number of items, capacity)

Example:

dp[3][5] = 6

means:

Using first 3 items
and capacity 5,
maximum profit is 6.
Understanding the Else Block

This is the MOST IMPORTANT PART.

Condition
if (wt[n - 1] > capacity)

Meaning:

Current item is too heavy.

So we skip it.

Else Block
else

Meaning:

Current item can fit inside the bag.

Now we have:

TWO CHOICES
Choice 1 → Include Item
int include = val[n - 1]
        + knapsack(
            wt,
            val,
            capacity - wt[n - 1],
            n - 1,
            dp
        );

Meaning:

Take current item

So:

Add its value
val[n-1]
Reduce remaining capacity
capacity - wt[n-1]
Move to previous items
n - 1

because 0/1 knapsack allows item only once.

Example

Suppose:

capacity = 7
current weight = 5
current value = 7

If included:

Remaining capacity = 2
Profit = 7 + solve remaining
Choice 2 → Exclude Item
int exclude = knapsack(
        wt,
        val,
        capacity,
        n - 1,
        dp
);

Meaning:

Skip current item

Capacity remains same.

Move to previous items.

Then Take Maximum
dp[n][capacity] = Math.max(include, exclude);

Meaning:

Store best possible profit.
Formula

dp[n][W]=max(val[n−1]+dp[n−1][W−wt[n−1]], dp[n−1][W])

Why n - 1 in Recursive Calls?

Because:

Current item is already processed.

Whether:

included
OR
excluded

we move to remaining items.

Recursive Tree Thinking

Suppose current item = 5kg

                    Item 5
                   /      \
             Include      Exclude
                /            \
          solve smaller   solve smaller

Each recursive call reduces problem size.

Final Understanding

Your DP table stores:

Best possible profit
for every combination
of:
(items, capacity)



*/

import java.util.Arrays;

public class KnapsackDP {

    // DP Memoization Function
    public static int knapsack(int[] wt, int[] val, int capacity, int n, int[][] dp) {

        // Base Case
        if (n == 0 || capacity == 0) {
            return 0;
        }

        // If already calculated
        if (dp[n][capacity] != -1) {
            return dp[n][capacity];
        }

        // If weight is greater than capacity
        if (wt[n - 1] > capacity) {
            dp[n][capacity] = knapsack(wt, val, capacity, n - 1, dp);
        }

        // Choice Diagram
        else {

            int include = val[n - 1]
                    + knapsack(wt, val, capacity - wt[n - 1], n - 1, dp);

            int exclude = knapsack(wt, val, capacity, n - 1, dp);

            dp[n][capacity] = Math.max(include, exclude);
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {

        int[] wt = {1, 3, 4, 5};
        int[] val = {1, 4, 5, 7};

        int capacity = 7;
        int n = wt.length;

        // DP Table
        int[][] dp = new int[n + 1][capacity + 1];

        // Fill with -1
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int result = knapsack(wt, val, capacity, n, dp);

        System.out.println("Maximum Profit = " + result);
    }
}