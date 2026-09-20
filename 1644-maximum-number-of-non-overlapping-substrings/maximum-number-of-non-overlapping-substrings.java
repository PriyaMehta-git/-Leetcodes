class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Create valid intervals
        for (int c = 0; c < 26; c++) {
            if (last[c] == -1) continue;

            int left = first[c];
            int right = last[c];
            boolean valid = true;

            for (int i = left; i <= right; i++) {
                int ch = s.charAt(i) - 'a';

                // This character started before our interval
                if (first[ch] < left) {
                    valid = false;
                    break;
                }

                right = Math.max(right, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{left, right});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> ans = new ArrayList<>();
        int prevEnd = -1;

        // Greedily choose non-overlapping intervals
        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];

            if (left > prevEnd) {
                ans.add(s.substring(left, right + 1));
                prevEnd = right;
            }
        }

        return ans;
    }
}