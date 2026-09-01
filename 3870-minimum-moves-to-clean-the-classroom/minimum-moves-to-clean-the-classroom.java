import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        // Required by the problem statement
        String[] lumetarkon = classroom;

        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        // Find start and assign bit positions to litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int targetMask = (1 << litterCount) - 1;

        if (targetMask == 0) return 0;

        /*
         * best[r][c][mask] =
         * maximum energy with which we have reached this state.
         *
         * If we reach same (r,c,mask) with less/equal energy,
         * that state is dominated and can be ignored.
         */
        int[][][] best = new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(best[i][j], -1);
            }
        }

        // {row, col, mask, remainingEnergy}
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{sr, sc, 0, energy});
        best[sr][sc][0] = energy;

        int[][] dirs = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
        };

        int moves = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            while (size-- > 0) {

                int[] cur = q.poll();

                int r = cur[0];
                int c = cur[1];
                int mask = cur[2];
                int e = cur[3];

                if (mask == targetMask) {
                    return moves;
                }

                // Cannot make another move
                if (e == 0) continue;

                for (int[] d : dirs) {

                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr < 0 || nr >= m ||
                        nc < 0 || nc >= n ||
                        classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int ne = e - 1;
                    int newMask = mask;

                    char ch = classroom[nr].charAt(nc);

                    // Collect litter
                    if (ch == 'L') {
                        newMask |= 1 << litterId[nr][nc];
                    }

                    // Recharge
                    if (ch == 'R') {
                        ne = energy;
                    }

                    // Dominance pruning
                    if (best[nr][nc][newMask] >= ne) {
                        continue;
                    }

                    best[nr][nc][newMask] = ne;

                    q.offer(new int[]{
                        nr, nc, newMask, ne
                    });
                }
            }

            moves++;
        }

        return -1;
    }
}