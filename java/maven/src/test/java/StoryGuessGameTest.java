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
public class StoryGuessGameTest {
    @Mock
    private AnswerGenerator answerGenerator;

    @Mock
    private StoryGuessGame storyGuessGame;

    @Mock
    private PrintStream printStream;

    @Mock
    private BufferedReader bufferedReader;

    private InOrder inOrder;

    @Before
    public void setUp() {
        storyGuessGame = new StoryGuessGame(answerGenerator,bufferedReader,printStream);
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
        storyGuessGame.playGame();

        //then
        inOrder.verify(printStream).println(StoryGuessGame.WELCOME);
        inOrder.verify(printStream).println();
        inOrder.verify(printStream).println(String.format(StoryGuessGame.INPUT_MESSAGE, StoryGuessGame.MAX_COUNT));
    }

    @Test
    public void shouldPrintGameOverWhenNotMatchAllTimes() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("5678");

        //when
        storyGuessGame.playGame();

        //then
        inOrder.verify(printStream).println(StoryGuessGame.GAME_OVER);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldPrintResultAndMessageWhenNotMatch() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("2345");

        //when
        storyGuessGame.playGame();

        //then
        inOrder.verify(printStream).println("0A3B");
        inOrder.verify(printStream).println();
        inOrder.verify(printStream).println(String.format(StoryGuessGame.INPUT_MESSAGE, 5));
    }

    @Test
    public void shouldPrintCongratulationsWhenOneMatch() throws Exception {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("1234");

        //when
        storyGuessGame.playGame();

        //then
        inOrder.verify(printStream).println(StoryGuessGame.CONGRATULATIONS);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldNotPrint4A0BWhenOneMatch() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("1234");

        //when
        storyGuessGame.playGame();

        //then
        verify(printStream, never()).println(StoryGuessGame.MATCH);
    }

    @Test
    public void shouldPrint6TimesSequenceMessageWhenNotMatch() throws IOException {
        //given
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        given(bufferedReader.readLine()).willReturn("5678");

        //when
        storyGuessGame.playGame();

        //then
        inOrder.verify(printStream).println(String.format(StoryGuessGame.INPUT_MESSAGE,6));
        inOrder.verify(printStream).println(String.format(StoryGuessGame.INPUT_MESSAGE,5));
        inOrder.verify(printStream).println(String.format(StoryGuessGame.INPUT_MESSAGE,4));
        inOrder.verify(printStream).println(String.format(StoryGuessGame.INPUT_MESSAGE,3));
        inOrder.verify(printStream).println(String.format(StoryGuessGame.INPUT_MESSAGE,2));
        inOrder.verify(printStream).println(String.format(StoryGuessGame.INPUT_MESSAGE,1));
        inOrder.verify(printStream).println(StoryGuessGame.GAME_OVER);
        inOrder.verifyNoMoreInteractions();
    }
}
