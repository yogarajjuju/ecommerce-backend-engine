// InsufficientStockException.java
public class InsufficientStockException extends RuntimeException {
    
    // Constructor that passes our custom error message to the parent class
    public InsufficientStockException(String message) {
        super(message);
    }
}