import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anySet;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InOrder;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by yushi on 8/30/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class GuessGameTest {
    @Mock
    private PrintStream printStream;

    @Mock
    private BufferedReader bufferedReader;

    private GuessGame guessGame;
    private InOrder inOrder;

    @Before
    public void setUp() {
        guessGame = new GuessGame(bufferedReader, printStream);
    }

    @Test
    public void shouldPrintChooseModeMessageWhenGameStart() throws IOException {
        //given
        given(bufferedReader.readLine()).willReturn("1").willReturn("1234");

        //when
        guessGame.play();

        //then
        verify(printStream).println(GuessGame.CHOOSE_MODE);
    }

    @Test
    public void shouldEnterStoryGameWhenInput1() throws IOException {
        //given
        given(bufferedReader.readLine()).willReturn("1").willReturn("1234");

        //when
        guessGame.play();

        //then
        verify(printStream).println(StoryGuessGame.WELCOME);
    }

    @Test
    public void shouldEnterFightGameWhenInput2() throws IOException {
        //given
        given(bufferedReader.readLine()).willReturn("2").willReturn("1234");
        String temp = String.format(FightGuessGame.INPUT_MESSAGE, FightGuessGame.MAX_COUNT);
        String result = String.format("%s, %s", temp, FightGuessGame.FIRST_USER);

        //when
        guessGame.play();

        //then
        verify(printStream).println(result);
    }
}
