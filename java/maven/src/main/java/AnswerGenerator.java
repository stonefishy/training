import java.util.Date;
import java.util.Random;

/**
 * Created by yushi on 8/25/14.
 */
public class AnswerGenerator {

    public static final int MAX_LENGTH = 4;
    public static final int MAX_NUMBER = 10;

    public int getRandomNumber() {
        String tempResult = "";

        while (tempResult.length() != MAX_LENGTH) {
            Random random = new Random(new Date().getTime());
            String numString = String.valueOf(random.nextInt(MAX_NUMBER));

            if (!tempResult.contains(numString))
                tempResult = tempResult.concat(numString);
        }

        return Integer.valueOf(tempResult);
    }
}
