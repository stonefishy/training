/**
 * Created by yushi on 8/28/14.
 */
public class BaseGame {
    public static final String MATCH = "4A0B";
    public static final String REGEX = "^\\d{4}$";
    public static final int MAX_COUNT = 6;
    protected String answer;
    protected AnswerGenerator answerGenerator;

    public BaseGame(AnswerGenerator answerGenerator) {
        this.answerGenerator = answerGenerator;
        this.answer = String.valueOf(answerGenerator.getRandomNumber());
    }

    protected void checkInputValid(String input) throws Exception {
        if (!input.matches(REGEX)) {
            throw new Exception("Input must be four numbers ");
        }
    }

    protected boolean compareAnswer(String input) {
        String result = CompareNumber.compare(input, answer);
        return result.equals(MATCH);
    }
}
