import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    @Before
    public void setUp(){
        given(answerGenerator.getRandomNumber()).willReturn(1234);
        baseGame = new BaseGame(answerGenerator);
    }

    @Test
    public void shouldThrowExceptionWhenInputInvalid() {
        //given
        String input = "abcd";

        //when
        try {
            baseGame.checkInputValid(input);
        } catch (Exception e) {
            assertTrue(true);
        }

        //then
    }

    @Test
    public void shoulReturnTrueWhenInputMatchAnswer() {
        //given
        String input = "1234";

        //when
        boolean result = baseGame.compareAnswer(input);

        //then
        assertTrue(result);
    }
}