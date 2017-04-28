package amazon.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * number to letters are like what are on telephone pad.
 * Created by andrew on 4/28/2017.
 */
public class LetterCombination {
        private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

        public List<String> letterCombinations(String digits) {
            List<String> ret = new LinkedList<String>();
            if(digits!=null && digits.length()>0){
                //first prefix is empty ""
                //next offset is 0
                combination("", digits, 0, ret);
            }
            return ret;
        }

    /**
     * with this predix, process the next offset.
     * at right time, when offset equals digits length, adding prefix to ret and stop further recursion
     * @param prefix
     * @param digits
     * @param offset
     * @param ret
     */
        private void combination(String prefix, String digits, int offset, List<String> ret) {
            if (offset >= digits.length()) {
                ret.add(prefix);
                return;
            }
            String letters = KEYS[(digits.charAt(offset) - '0')];
            for (int i = 0; i < letters.length(); i++) {
                //when return from recursion, prefix is same as before, so that it can be used for next letter
                combination(prefix + letters.charAt(i), digits, offset + 1, ret);
            }
        }

}
