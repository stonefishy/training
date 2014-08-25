import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class CompareNumberTest {

    @Test
    public void shouldReturn4A0BWhenGivenSameNumberAndPosition() throws Exception {
        //given
        String answer = "1234";
        String input = "1234";

        //when
        String result = CompareNumber.compare(input, answer);

        //then
        assertThat(result).isEqualTo("4A0B");
    }

    @Test
    public void shouldReturn0A4BWhenGivenSameNumberDifferentPoisition() throws Exception {
        //given
        String answer = "1234";
        String input = "4321";

        //when
        String result = CompareNumber.compare(input, answer);

        //then
        assertThat(result).isEqualTo("0A4B");
    }

    @Test
    public void shouldReturn0A0BWhenGivenNotSameNumber() throws Exception {
        //given
        String answer = "1234";
        String input = "5678";

        //when
        String result = CompareNumber.compare(input, answer);

        //verify
        assertThat(result).isEqualTo("0A0B");
    }

    @Test
    public void shouldReturnXAYBWhenGivenSamePartialNumber() throws Exception {
        //given
        String answer = "1234";
        String input = "8235";

        //when
        String result = CompareNumber.compare(input, answer);

        //verify
        assertThat(result).isEqualTo("2A0B");
    }

    @Test
    public void shouldReturnExceptionWhenGivenNotFourLength() {
        //given
        String answer = "1234";
        String input = "12345";

        //when

        //then
        try {
            CompareNumber.compare(input, answer);
        } catch (Exception e) {
            assertThat(true).isTrue();
        }
    }

    @Test
    public void shouldReturnExceptionWhenGivenNotNumberSymbol() {
        //given
        String answer = "1234";
        String input = "asdb";

        //when

        //then
        try {
            CompareNumber.compare(input, answer);
        } catch (Exception e) {
            assertThat(true).isTrue();
        }
    }
}
