package MyExeptions;

public class WrongNameOrSurnameFormat extends RuntimeException {

    public WrongNameOrSurnameFormat(String message) {
        super(message);
    }
}
