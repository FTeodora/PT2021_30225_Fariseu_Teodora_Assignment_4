package business.exceptions;

public class LoginException extends Exception{
    public LoginException(String username){
        super("User "+username+" not found ");
    }
    public LoginException(){
        super("Incorrect password");
    }
}
