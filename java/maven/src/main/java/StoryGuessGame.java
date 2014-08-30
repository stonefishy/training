import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by yushi on 8/27/14.
 */
public class StoryGuessGame extends BaseGame implements IGame {
    public static final String GAME_OVER = "Game Over";
    public static final String WELCOME = "Welcome!";

    private int curCount = 0;

    public StoryGuessGame(AnswerGenerator answerGenerator, BufferedReader bufferedReader, PrintStream printStream) {
        super(answerGenerator, bufferedReader, printStream);
    }

    @Override
    public void playGame() {
        initGame();
        gameCore();
    }

    private void initGame() {
        curCount = 0;
        answer = String.valueOf(answerGenerator.getRandomNumber());
        printStream.println(WELCOME);
        printStream.println();
        printStream.println(String.format(INPUT_MESSAGE, MAX_COUNT));
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
                    printStream.println(String.format(INPUT_MESSAGE, MAX_COUNT - curCount));
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

    public static void main(String[] args) {
        IGame storyGuessGame = new StoryGuessGame(new AnswerGenerator(), new BufferedReader(new InputStreamReader(System.in)), System.out);
        storyGuessGame.playGame();
    }
}
