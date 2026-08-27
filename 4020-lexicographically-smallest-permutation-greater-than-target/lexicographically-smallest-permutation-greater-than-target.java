class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Try to make the answer greater at position i.
        for (int i = n - 1; i >= 0; i--) {

            // We need target[0 ... i-1] to be exactly matched.
            // Remove the prefix from our available characters.
            int[] cnt = freq.clone();

            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (cnt[x] == 0) {
                    possible = false;
                    break;
                }

                cnt[x]--;
            }

            if (!possible) continue;

            // At position i, choose the smallest character
            // strictly greater than target[i].
            int cur = target.charAt(i) - 'a';

            int bigger = -1;

            for (int c = cur + 1; c < 26; c++) {
                if (cnt[c] > 0) {
                    bigger = c;
                    break;
                }
            }

            if (bigger == -1) continue;

            // Use the bigger character.
            cnt[bigger]--;

            StringBuilder ans = new StringBuilder();

            // Same prefix as target.
            ans.append(target, 0, i);

            // First position where we become greater.
            ans.append((char) ('a' + bigger));

            // Remaining characters in smallest order.
            for (int c = 0; c < 26; c++) {
                while (cnt[c] > 0) {
                    ans.append((char) ('a' + c));
                    cnt[c]--;
                }
            }

            return ans.toString();
        }

        return "";
    }
}