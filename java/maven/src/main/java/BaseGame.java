import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * Created by yushi on 8/28/14.
 */
public class BaseGame {
    public static final String MATCH = "4A0B";
    public static final String REGEX = "^\\d{4}$";
    public static final int MAX_COUNT = 6;
    public static final String PROMPT_MSG = "Please input your number(%d)";
    public static final String CONGRATULATIONS = "Congratulation";

    protected String answer;
    protected AnswerGenerator answerGenerator;
    protected BufferedReader bufferedReader;
    protected PrintStream printStream;

    public BaseGame(AnswerGenerator answerGenerator, PrintStream printStream, BufferedReader bufferedReader) {
        this.answerGenerator = answerGenerator;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.answer = String.valueOf(answerGenerator.getRandomNumber());
    }

    protected void checkInputValid(String input) throws Exception {
        if (!input.matches(REGEX)) {
            throw new Exception("Input must be four numbers ");
        }
    }

    protected String compareAnswer(String input) {
        return CompareNumber.compare(input, answer);
    }
}
