package MyExeptions;

public class WrongPhoneFormat extends RuntimeException {

    public WrongPhoneFormat(String message) {
        super(message);
    }
}
