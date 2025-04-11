package edu.unimagdalena.exceptions;

public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(){
        super("Flight not Found");
     }
     public FlightNotFoundException(String messaje){
        super(messaje);
     }
}
