import java.util.Arrays;

/**
 * Created by yushi on 8/25/14.
 */
public class CompareNumber {
    public static final String REGEX = "^\\d{4}$";

    public static String compare(String input, String answer) throws Exception {
        if (!input.matches(REGEX) || !answer.matches(REGEX)) {
            throw new Exception("Input and answer value must be four number");
        }

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
}
