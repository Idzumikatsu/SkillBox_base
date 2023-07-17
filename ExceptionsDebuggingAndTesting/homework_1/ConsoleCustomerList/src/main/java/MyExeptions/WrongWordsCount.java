package MyExeptions;

public class WrongWordsCount extends RuntimeException {

    public WrongWordsCount(String message) {
        super(message);
    }
}
