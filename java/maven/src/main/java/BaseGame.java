import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by yushi on 8/28/14.
 */
public class BaseGame {
    public static final String MATCH = "4A0B";
    public static final String REGEX = "^\\d{4}$";
    public static final int MAX_COUNT = 6;
    public static final String INPUT_MESSAGE = "Please input your number(%d)";
    public static final String CONGRATULATIONS = "Congratulation";

    protected String answer;
    protected AnswerGenerator answerGenerator;
    protected BufferedReader bufferedReader;
    protected PrintStream printStream;

    public BaseGame(AnswerGenerator answerGenerator, BufferedReader bufferedReader, PrintStream printStream) {
        this.answerGenerator = answerGenerator;
        this.printStream = printStream;
        this.bufferedReader = bufferedReader;
        this.answer = String.valueOf(answerGenerator.getRandomNumber());
    }

    protected boolean checkInputValid(String input) {
        return input.matches(REGEX);
    }

    protected String compareAnswer(String input) {
        return CompareNumber.compare(input, answer);
    }

    protected boolean isMatchedAnswer(String input) {
        return compareAnswer(input).equals(MATCH);
    }

    protected ArrayList<String> getCompareNumbers(String input) {
        return CompareNumber.getCompareNumbers(input, answer);
    }

    protected String readInputFromConsole() {
        String input = "";
        do {
            try {
                input = bufferedReader.readLine();
                if (!checkInputValid(input)) {
                    printStream.println("Input must be four numbers ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!checkInputValid(input));

        return input;
    }
}
