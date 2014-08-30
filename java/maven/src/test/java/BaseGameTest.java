import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

/**
 * Created by yushi on 8/28/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseGameTest {
    private BaseGame baseGame;

    @Mock
    private AnswerGenerator answerGenerator;

    @Mock
    private PrintStream printStream;

    @Mock
    private BufferedReader bufferedReader;

    @Before
    public void setUp(){
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        baseGame = new BaseGame(answerGenerator, bufferedReader, printStream);
    }

    @Test
    public void shoulReturnMatchWhenInputMatchAnswer() {
        //given
        String input = "1234";

        //when
        String result = baseGame.compareAnswer(input);

        //then
        assertEquals(result, BaseGame.MATCH);
    }
}
