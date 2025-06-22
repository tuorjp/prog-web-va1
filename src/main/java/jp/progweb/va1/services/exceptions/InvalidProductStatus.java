package jp.progweb.va1.services.exceptions;

public class InvalidProductStatus extends RuntimeException {
    public InvalidProductStatus(String message) {
        super(message);
    }
}
