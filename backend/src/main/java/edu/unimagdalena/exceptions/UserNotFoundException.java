package edu.unimagdalena.exceptions;

public class UserNotFoundException extends RuntimeException {
     public UserNotFoundException(){
        super("User not Found");
     }
     public UserNotFoundException(String messaje){
        super(messaje);
     }
}
