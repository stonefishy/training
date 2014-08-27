import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GuessTest {
    @Mock
    private AnswerGenerator answerGenerator;

    @Test
    public void shouldInvokeGetRandomNumberOfAnswerGenerator() throws Exception {
        //given
        Guess guess = new Guess(answerGenerator);
        given(answerGenerator.getRandomNumber()).willReturn(1234);

        //when
        guess.combineCalled("1234");

        //then
        verify(answerGenerator).getRandomNumber();
    }

    @Test
    public void shouldMatchAllWhenGivenSameNumber() throws Exception {
        //given
        String input1 = "1234";
        String input2 = "4321";
        String input3 = "5678";
        String input4 = "5638";

        Guess guess = new Guess(answerGenerator);
        given(answerGenerator.getRandomNumber()).willReturn(1234);

        //when
        String result1 = guess.combineCalled(input1);
        String result2 = guess.combineCalled(input2);
        String result3 = guess.combineCalled(input3);
        String result4 = guess.combineCalled(input4);

        //then
        assertThat(result1).isEqualTo("4A0B");
        assertThat(result2).isEqualTo("0A4B");
        assertThat(result3).isEqualTo("0A0B");
        assertThat(result4).isEqualTo("1A0B");
    }
}
