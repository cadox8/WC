package es.projectalpha.wc.core.exceptions;

public class NullInventorySaveException extends Exception{

    public NullInventorySaveException(){}

    public NullInventorySaveException(String message){
        super(message);
    }
}
