package jp.progweb.va1.services.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("Product not found with id: " + id);
    }
}
