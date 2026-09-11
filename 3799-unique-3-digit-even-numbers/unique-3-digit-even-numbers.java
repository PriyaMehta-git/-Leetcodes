import java.util.*;

class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> set = new HashSet<>();

        int n = digits.length;

        for (int i = 0; i < n; i++) {          // units digit
            if (digits[i] % 2 != 0) continue; // must be even

            for (int j = 0; j < n; j++) {      // tens digit
                if (j == i) continue;

                for (int k = 0; k < n; k++) {  // hundreds digit
                    if (k == i || k == j) continue;
                    if (digits[k] == 0) continue; // no leading zero

                    int num = digits[k] * 100
                            + digits[j] * 10
                            + digits[i];

                    set.add(num);
                }
            }
        }

        return set.size();
    }
}