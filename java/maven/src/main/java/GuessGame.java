import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by yushi on 8/27/14.
 */
public class GuessGame extends BaseGame {
    public static final String GAME_OVER = "Game Over";
    public static final String WELCOME = "Welcome!";

    private int curCount = 0;

    private BufferedReader bufferedReader;
    private PrintStream printStream;

    public GuessGame(AnswerGenerator answerGenerator, BufferedReader bufferedReader, PrintStream printStream) {
        super(answerGenerator);
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
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
        printStream.println(String.format(PROMPT_MSG, MAX_COUNT));
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
                    printStream.println(String.format(PROMPT_MSG, MAX_COUNT - curCount));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getCompareResult(String input) throws Exception {
        String compare = compareAnswer(input);

        if (compare.equals(MATCH)) {
            return CONGRATULATIONS;
        } else if (curCount == MAX_COUNT) {
            return GAME_OVER;
        }

        return compare;
    }

    private String readInputFromConsole() {
        String input = null;
        try {
            input = bufferedReader.readLine();
            checkInputValid(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }

    public static void main(String[] args) {
        GuessGame guessGame = new GuessGame(new AnswerGenerator(), new BufferedReader(new InputStreamReader(System.in)), System.out);
        guessGame.playGame();
    }
}
