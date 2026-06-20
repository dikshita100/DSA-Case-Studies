import java.util.*;

public class ScholarshipKnapsack {

    public static void main(String[] args) {

        // Scholarship amounts (in lakhs)
        int[] cost = {4, 6, 5, 7, 3, 8, 2, 5};

        // Merit scores
        int[] merit = {10, 13, 12, 18, 8, 20, 6, 11};

        int n = cost.length;
        int budget = 24;

        int[][] dp = new int[n + 1][budget + 1];

        // Build DP Table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= budget; w++) {

                if (cost[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            merit[i - 1] + dp[i - 1][w - cost[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum Merit Score = " + dp[n][budget]);

        // Backtracking to find selected students
        System.out.println("\nSelected Students:");

        int w = budget;

        for (int i = n; i > 0 && w > 0; i--) {

            if (dp[i][w] != dp[i - 1][w]) {

                System.out.println(
                        "Student " + i +
                        " | Scholarship = " + cost[i - 1] + " Lakhs" +
                        " | Merit Score = " + merit[i - 1]
                );

                w -= cost[i - 1];
            }
        }
    }
}