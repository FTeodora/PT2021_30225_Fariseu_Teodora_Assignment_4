package business.exceptions;

public class BadInputException extends Exception{
    public BadInputException(String fieldName,String reason){
        super(fieldName+": "+reason);
    }
}
