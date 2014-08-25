/**
 * Created by yushi on 8/25/14.
 */
public class Guess {
    private final AnswerGenerator answerGenerator;

    public Guess(AnswerGenerator answerGenerator){
        this.answerGenerator = answerGenerator;
    }

    public String combineCalled(String input) throws Exception {
        String answer = String.valueOf(answerGenerator.getRandomNumber());
        return CompareNumber.compare(input, answer);
    }
}
