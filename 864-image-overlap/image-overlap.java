class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        // Store positions of 1s
        List<int[]> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1)
                    a.add(new int[]{i, j});

                if (img2[i][j] == 1)
                    b.add(new int[]{i, j});
            }
        }

        // Count each possible shift
        Map<String, Integer> map = new HashMap<>();
        int ans = 0;

        for (int[] p1 : a) {
            for (int[] p2 : b) {

                int dx = p2[0] - p1[0];
                int dy = p2[1] - p1[1];

                String key = dx + "," + dy;

                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);

                ans = Math.max(ans, count);
            }
        }

        return ans;
    }
}