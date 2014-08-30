import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by yushi on 8/29/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class FightGuessGameTest {
    private FightGuessGame fightGuessGame;

    @Mock
    private AnswerGenerator answerGenerator;

    @Mock
    private PrintStream printStream;

    @Mock
    private BufferedReader bufferedReader;

    private InOrder inOrder;

    @Before
    public void steUp() {
        fightGuessGame = new FightGuessGame(answerGenerator, bufferedReader, printStream);
        inOrder = inOrder(printStream);
    }

    @Test
    public void shouldPrintInputMessageForFirstUserWhenGameStart() throws IOException {
        //given
        String result = getInputMessageForUser(FightGuessGame.FIRST_USER, FightGuessGame.MAX_COUNT);
        given(bufferedReader.readLine()).willReturn("1234");
        given(answerGenerator.getRandomNumber()).willReturn(4567);

        //when
        fightGuessGame.playGame();

        //then
        inOrder.verify(printStream).println(result);
    }

    @Test
    public void shouldUserRemainTimesReduceOneWhenAnotherUserGuessOneNumber() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("1567");

        //when
        fightGuessGame.playGame();

        //then
        inOrder.verify(printStream).println(getInputMessageForUser(FightGuessGame.FIRST_USER, FightGuessGame.MAX_COUNT));
        inOrder.verify(printStream).println("1A0B");
        inOrder.verify(printStream).println(getInputMessageForUser(FightGuessGame.SECOND_USER, FightGuessGame.MAX_COUNT - 1));
    }

    @Test
    public void shouldPrintCongratulationWhenOneUserMatched() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("1234");

        //when
        fightGuessGame.playGame();

        //then
        inOrder.verify(printStream).println(getInputMessageForUser(FightGuessGame.FIRST_USER, FightGuessGame.MAX_COUNT));
        inOrder.verify(printStream).println(String.format("%s%s, %s", FightGuessGame.YOU_GOT_IT,
                FightGuessGame.CONGRATULATIONS, FightGuessGame.FIRST_USER));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldUserNotSubtractOneTimeWhenAnotherUserMatchOneHasMatchedSybol() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("1567").willReturn("1543");

        //when
        fightGuessGame.playGame();

        //then
        verify(printStream).println(getInputMessageForUser(FightGuessGame.FIRST_USER, 5));
    }

    @Test
    public void shouldUserWinWhenAnotherUserHaveNoTimes() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("5678");

        //when
        fightGuessGame.playGame();

        //then
        inOrder.verify(printStream).println(String.format("%s, %s", FightGuessGame.CONGRATULATIONS, FightGuessGame.SECOND_USER));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintSwitchUserInputMessage() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("5678");

        //when
        fightGuessGame.playGame();

        //then
        inOrder.verify(printStream).println(getInputMessageForUser(FightGuessGame.FIRST_USER, 6));
        inOrder.verify(printStream).println(getInputMessageForUser(FightGuessGame.SECOND_USER, 6));
        inOrder.verify(printStream).println(getInputMessageForUser(FightGuessGame.FIRST_USER, 5));
        inOrder.verify(printStream).println(getInputMessageForUser(FightGuessGame.SECOND_USER, 5));
    }

    @Test
    public void shouldPrintCompareResultWhenNotFullMatched() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("1324");

        //when
        fightGuessGame.playGame();

        //then
        inOrder.verify(printStream).println("2A2B");
        inOrder.verify(printStream).println(getInputMessageForUser(FightGuessGame.SECOND_USER, 4));
    }

    private String getInputMessageForUser(String userName, int remainTimes) {
        String temp = String.format(FightGuessGame.INPUT_MESSAGE, remainTimes);
        return String.format("%s, %s", temp, userName);
    }
}
