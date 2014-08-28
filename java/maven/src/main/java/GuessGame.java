import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by yushi on 8/27/14.
 */
public class GuessGame {
    public static final String CONGRATULATIONS = "Congratulations";
    public static final String GAME_OVER = "Game Over";
    public static final String WELCOME = "Welcome!";
    public static final String START_INFO = "Please input your number(%d)";
    public static final String MATCH = "4A0B";
    public static final String REGEX = "^\\d{4}$";
    public static final int MAX_COUNT = 6;

    private int curCount = 0;
    private String answer;

    private AnswerGenerator answerGenerator;
    private BufferedReader bufferedReader;
    private PrintStream printStream;

    public GuessGame(AnswerGenerator answerGenerator, BufferedReader bufferedReader, PrintStream printStream) {
        this.answerGenerator = answerGenerator;
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
    }

    private void checkInputValid(String input, String answer) throws Exception {
        if (!input.matches(REGEX) || !answer.matches(REGEX)) {
            throw new Exception("Input must be four numbers ");
        }
    }

    public void playGame() {
        initGame();
        gameCore();
    }

    private void initGame() {
        curCount = 0;
        answer = String.valueOf(answerGenerator.getRandomNumber());
        printStream.println(WELCOME);
        printStream.println();
        printStream.println(String.format(START_INFO, MAX_COUNT));
    }

    private void gameCore() {
        while (curCount < MAX_COUNT) {
            try {
                curCount++;
                String input = readInputFromConsole();
                String result = getCompareResult(input);
                printStream.println(result);
                if (result.equals(CONGRATULATIONS) || result.equals(GAME_OVER)) {
                    break;
                } else {
                    printStream.println();
                    printStream.println(String.format(START_INFO, MAX_COUNT - curCount));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getCompareResult(String input) throws Exception {
        checkInputValid(input, answer);
        String compare = CompareNumber.compare(input, answer);

        if (compare.equals(MATCH)) {
            return CONGRATULATIONS;
        } else if (curCount == MAX_COUNT) {
            return GAME_OVER;
        }

        return compare;
    }

    private String readInputFromConsole() {
        String str = null;
        try {
            str = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        GuessGame guessGame = new GuessGame(new AnswerGenerator(), new BufferedReader(new InputStreamReader(System.in)), System.out);
        guessGame.playGame();
    }
}
