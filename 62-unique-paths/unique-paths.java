class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for( int i=0;i<m;i++)
            dp[i][0]=1;
        for( int j=0;j<n;j++)
        dp[0][j]=1;
        for( int a=1;a<m;a++){
            for( int b=1;b<n;b++){
                dp[a][b]=dp[a-1][b]+ dp[a][b-1];
            }
        }
        return dp[m-1][n-1];
    }
}