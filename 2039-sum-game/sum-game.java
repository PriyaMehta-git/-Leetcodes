class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int diff = 0;   // left sum - right sum
        int q = 0;      // left ? - right ?

        for (int i = 0; i < n / 2; i++) {

            char left = num.charAt(i);
            char right = num.charAt(n - 1 - i);

            if (left == '?') {
                q++;
            } else {
                diff += left - '0';
            }

            if (right == '?') {
                q--;
            } else {
                diff -= right - '0';
            }
        }

        // Alice loses only when:
        // 2 * diff + 9 * q == 0
        return 2 * diff + 9 * q != 0;
    }
}