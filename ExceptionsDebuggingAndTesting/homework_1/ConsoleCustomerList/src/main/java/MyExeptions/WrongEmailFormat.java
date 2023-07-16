package MyExeptions;

public class WrongEmailFormat extends RuntimeException {

    public WrongEmailFormat(String message) {
        super(message);
    }
}
