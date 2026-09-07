class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long[] dp = new long[26];

        for (char c : s.toCharArray()) {
            int x = c - 'a';

            long total = 1; // empty subsequence
            for (long v : dp) {
                total = (total + v) % MOD;
            }

            dp[x] = total;
        }

        long ans = 0;
        for (long v : dp) {
            ans = (ans + v) % MOD;
        }

        return (int) ans;
    }
}