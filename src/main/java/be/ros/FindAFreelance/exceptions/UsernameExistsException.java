package be.ros.FindAFreelance.exceptions;

public class UsernameExistsException extends RuntimeException{

    public UsernameExistsException() {
        super("Cet username est déjà pris, veuillez en choisir un autre");
    }

    public UsernameExistsException(String message) {
        super(message);
    }
}
