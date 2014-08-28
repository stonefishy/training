import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InOrder;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by yushi on 8/27/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class GuessGameTest {
    @Mock
    private AnswerGenerator answerGenerator;

    @Mock
    private GuessGame guessGame;

    @Mock
    private PrintStream printStream;

    @Mock
    private BufferedReader bufferedReader;

    private InOrder inOrder;

    @Before
    public void setUp() {
        guessGame = new GuessGame(answerGenerator,bufferedReader,printStream);
        inOrder = inOrder(printStream);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void shouldPrintStartInfoWhenGameStart() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("1234");

        //when
        guessGame.playGame();

        //then
        inOrder.verify(printStream).println(GuessGame.WELCOME);
        inOrder.verify(printStream).println();
        inOrder.verify(printStream).println(String.format(GuessGame.START_INFO, BaseGame.MAX_COUNT));
    }

    @Test
    public void shouldPrintGameOverWhenNotMatchAllTimes() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("5678");

        //when
        guessGame.playGame();

        //then
        inOrder.verify(printStream).println(GuessGame.GAME_OVER);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintResultAndMessageWhenNotMatch() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("2345");

        //when
        guessGame.playGame();

        //then
        inOrder.verify(printStream).println("0A3B");
        inOrder.verify(printStream).println();
        inOrder.verify(printStream).println(String.format(GuessGame.START_INFO, 5));
    }

    @Test
    public void shouldPrintCongratulationsWhenOneMatch() throws Exception {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("1234");

        //when
        guessGame.playGame();

        //then
        inOrder.verify(printStream).println("Congratulations");
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldNotPrint4A0BWhenOneMatch() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("1234");

        //when
        guessGame.playGame();

        //then
        verify(printStream, never()).println(BaseGame.MATCH);
    }

    @Test
    public void shouldPrint6TimesSequenceMessageWhenNotMatch() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("5678");

        //when
        guessGame.playGame();

        //then
        inOrder.verify(printStream).println(String.format(GuessGame.START_INFO,6));
        inOrder.verify(printStream).println(String.format(GuessGame.START_INFO,5));
        inOrder.verify(printStream).println(String.format(GuessGame.START_INFO,4));
        inOrder.verify(printStream).println(String.format(GuessGame.START_INFO,3));
        inOrder.verify(printStream).println(String.format(GuessGame.START_INFO,2));
        inOrder.verify(printStream).println(String.format(GuessGame.START_INFO,1));
        inOrder.verify(printStream).println(GuessGame.GAME_OVER);
        inOrder.verifyNoMoreInteractions();
    }
}
