class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int half = n / 2;

        // Frequency of characters
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // More than one odd frequency => impossible palindrome
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Only half of each frequency is needed for the left half
        for (int i = 0; i < 26; i++) {
            freq[i] /= 2;
        }

        char[] ans = new char[n];

        /*
         * First, try to make the left half equal to
         * target's left half as much as possible.
         */
        int pos = 0;

        while (pos < half) {
            int c = target.charAt(pos) - 'a';

            if (freq[c] == 0) {
                break;
            }

            ans[pos] = target.charAt(pos);
            freq[c]--;
            pos++;
        }

        /*
         * If we matched the complete left half,
         * construct the palindrome and check whether
         * it is already > target.
         */
        if (pos == half) {

            buildPalindrome(ans, half, middle);

            String candidate = new String(ans);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * Backtrack.
         *
         * At position pos, try to put the smallest character
         * strictly greater than target[pos].
         */
        while (true) {

            if (pos < half) {

                int current = target.charAt(pos) - 'a';

                for (int next = current + 1; next < 26; next++) {

                    if (freq[next] == 0) {
                        continue;
                    }

                    ans[pos] = (char) ('a' + next);
                    freq[next]--;

                    /*
                     * Fill everything after pos with the
                     * smallest possible characters.
                     */
                    int idx = pos + 1;

                    for (int c = 0; c < 26; c++) {
                        while (freq[c] > 0) {
                            ans[idx++] = (char) ('a' + c);
                            freq[c]--;
                        }
                    }

                    buildPalindrome(ans, half, middle);

                    return new String(ans);
                }
            }

            /*
             * Cannot increase at this position.
             * Move one position left and restore the
             * character that was used there.
             */
            if (pos == 0) {
                return "";
            }

            pos--;

            int restored = target.charAt(pos) - 'a';
            freq[restored]++;
        }
    }

    private void buildPalindrome(char[] ans, int half, char middle) {

        int n = ans.length;

        // Middle character for odd length
        if ((n & 1) == 1) {
            ans[half] = middle;
        }

        // Mirror left half
        for (int i = 0; i < half; i++) {
            ans[n - 1 - i] = ans[i];
        }
    }
}