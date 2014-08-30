import org.fest.util.Lists;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by yushi on 8/25/14.
 */
public class CompareNumber {
    public static String compare(String input, String answer) {
        int sameNumberPositionCount = 0;
        int sameNumberDifferentPositionCount = 0;
        char[] inputCharArray = input.toCharArray();
        char[] answerCharArray = answer.toCharArray();

        for (int i = 0; i < answerCharArray.length; i++) {
            if (answerCharArray[i] == inputCharArray[i]) {
                sameNumberPositionCount++;
            } else if (Arrays.toString(answerCharArray).indexOf(inputCharArray[i]) >= 0) {
                sameNumberDifferentPositionCount++;
            }
        }
        return String.format("%dA%dB", sameNumberPositionCount, sameNumberDifferentPositionCount);
    }

    public static ArrayList<String> getCompareNumbers(String input, String answer){
        char[] inputCharArray = input.toCharArray();
        char[] answerCharArray = answer.toCharArray();
        ArrayList<String> result = Lists.newArrayList();

        for (int i = 0; i < answerCharArray.length; i++) {
            if (answerCharArray[i] == inputCharArray[i]) {
                result.add(String.valueOf(answerCharArray[i]));
            }
        }

        return result;
    }
}
