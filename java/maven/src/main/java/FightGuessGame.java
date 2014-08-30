import org.fest.util.Lists;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Created by yushi on 8/29/14.
 */
public class FightGuessGame extends BaseGame implements IGame {
    protected static final String FIRST_USER = "UserA";
    protected static final String SECOND_USER = "UserB";
    protected static final String YOU_GOT_IT = "You got it.";

    private ArrayList<GamePlayer> playerList = Lists.newArrayList();
    private ArrayList<String> matchedSymbols = Lists.newArrayList();
    private GamePlayer playerA;
    private GamePlayer playerB;

    public FightGuessGame(AnswerGenerator answerGenerator, BufferedReader bufferedReader, PrintStream printStream) {
        super(answerGenerator, bufferedReader, printStream);
        playerA = new GamePlayer(FightGuessGame.FIRST_USER, FightGuessGame.MAX_COUNT);
        playerB = new GamePlayer(FightGuessGame.SECOND_USER, FightGuessGame.MAX_COUNT);
        playerList.add(playerA);
        playerList.add(playerB);
    }

    @Override
    public void playGame() {
        initGame();
        gameCore();
    }

    private void initGame() {
        for (GamePlayer player : playerList) {
            player.setRemainTimes(MAX_COUNT);
        }

        answer = String.valueOf(answerGenerator.getRandomNumber());
        matchedSymbols.clear();
    }

    private void gameCore() {
        while (hasTimesInPlayers()) {
            for (GamePlayer player : playerList) {
                printStream.println(getInputMessageForUser(player));
                String input = readInputFromConsole();
                if (isMatchedAnswer(input)) {
                    printStream.println(String.format(YOU_GOT_IT + "%s, %s", CONGRATULATIONS, player.getUserName()));
                    return;
                }
                printStream.println(compareAnswer(input));
                calculateTimesByCompareResult(getCompareNumbers(input), player);
            }
        }

        if (playerA.getRemainTimes() <= 0) {
            printStream.println(String.format("%s, %s", CONGRATULATIONS, playerB.getUserName()));
        } else if (playerB.getRemainTimes() <= 0) {
            printStream.println(String.format("%s, %s", CONGRATULATIONS, playerA.getUserName()));
        }
    }

    private void calculateTimesByCompareResult(ArrayList<String> listSymbols, GamePlayer player) {
        player.subtractOneTime();

        for (String symbol : listSymbols) {
            if (!matchedSymbols.contains(symbol)) {
                matchedSymbols.add(symbol);
                for (GamePlayer gp : playerList) {
                    if (gp != player) {
                        gp.subtractOneTime();
                    }
                }
            }
        }
    }

    private boolean hasTimesInPlayers() {
        for (GamePlayer player : playerList) {
            if (player.getRemainTimes() <= 0) {
                return false;
            }
        }
        return true;
    }

    private String getInputMessageForUser(GamePlayer player) {
        String temp = String.format(FightGuessGame.INPUT_MESSAGE, player.getRemainTimes());
        return String.format("%s, %s", temp, player.getUserName());
    }

    public static void main(String[] args) {
        IGame fightGuessGame = new FightGuessGame(new AnswerGenerator(), new BufferedReader(new InputStreamReader(System.in)), System.out);
        fightGuessGame.playGame();
    }
}
