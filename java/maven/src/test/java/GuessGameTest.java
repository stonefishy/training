import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by yushi on 8/27/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class GuessGameTest {
    @Mock
    private AnswerGenerator answerGenerator;

    @InjectMocks
    private GuessGame guessGame;

    @Mock
    private PrintStream printStream;

    @Mock
    private BufferedReader bufferedReader;

    private InOrder inOrder;

    @Before
    public void setUp() {
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
        inOrder.verify(printStream).println(String.format(GuessGame.START_INFO, GuessGame.MAX_COUNT));
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
        verify(printStream, never()).println("4A0B");
    }

    @Test
    public void shouldReturnGameOverWhenNotAllMatch() throws Exception {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("5678");

        //when
        guessGame.playGame();

        //then
        verify(printStream).println("Game Over");
    }
}
