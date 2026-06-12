public interface PaymentProcessor {
    // Any class that implements this MUST write the logic for this method
    boolean processPayment(double amount);
}