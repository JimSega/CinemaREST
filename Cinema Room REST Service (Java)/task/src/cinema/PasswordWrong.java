package cinema;

public class PasswordWrong extends RuntimeException{
    public PasswordWrong (String message) {
        super(message);
    }
}
