import static org.fest.assertions.api.Assertions.assertThat;
import org.junit.Test;

public class AnswerGeneratorTest {
    private AnswerGenerator answerGenerator = new AnswerGenerator();

    @Test
    public void shouldReturnFourNumber() {
        //given
        int answer = 4;

        //when
        int result = answerGenerator.getRandomNumber();

        //then
        assertThat(String.valueOf(result).length()).isEqualTo(answer);
    }

    @Test
    public void shouldReturnNonRepeatNumber() {
        //given

        //when
        int result = answerGenerator.getRandomNumber();

        //then
        assertThat(hasContainSameNumber(result)).isFalse();
    }

    private boolean hasContainSameNumber(int number) {
        String result = String.valueOf(number);
        String temp = "";
        for (Character c : result.toCharArray()) {
            if (temp.indexOf(c) > 0) {
                return true;
            }

            temp = temp.concat(c.toString());
        }

        return false;
    }
}
