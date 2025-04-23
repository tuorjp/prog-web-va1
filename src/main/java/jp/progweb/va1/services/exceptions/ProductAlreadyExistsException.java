package jp.progweb.va1.services.exceptions;

public class ProductAlreadyExistsException extends RuntimeException{
    public ProductAlreadyExistsException(Long id){
        super("Product with id " + id + " already exists");
    }

    public ProductAlreadyExistsException(String message) {
        super("Product: " + message + " already exists");
    }
}
