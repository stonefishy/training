import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * Created by yushi on 8/30/14.
 */
public class GuessGame {
    protected static final String CHOOSE_MODE = "Please choose game mode(1.Story, 2.Fight):";

    private final PrintStream printStream;
    private final BufferedReader bufferedReader;

    public GuessGame(BufferedReader bufferedReader, PrintStream printStream) {
        this.bufferedReader = bufferedReader;
        this.printStream = printStream;
    }

    public void play() throws IOException {
        IGame game = null;

        while (game == null) {
            game = createGame();
        }

        game.playGame();
    }

    private IGame createGame() throws IOException {
        printStream.println(CHOOSE_MODE);
        String input = bufferedReader.readLine();
        IGame game = null;

        if (input.equals(GameMode.STORY.getModeNumber())) {
            game = new StoryGuessGame(new AnswerGenerator(), bufferedReader, printStream);
        } else if (input.equals(GameMode.FIGHT.getModeNumber())) {
            game = new FightGuessGame(new AnswerGenerator(), bufferedReader, printStream);
        }

        return game;
    }

    public static void main(String[] args) {
        GuessGame guessGame = new GuessGame(new BufferedReader(new InputStreamReader(System.in)), System.out);
        try {
            guessGame.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
