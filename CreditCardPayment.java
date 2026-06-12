// Notice the exact spelling of PaymentProcessor here
public class CreditCardPayment implements PaymentProcessor {
    // You must declare the variable here first!
    private String cardNumber;

    // The Constructor MUST match the class name exactly (Capital C)
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("💳 Processing credit card payment of $" + amount + " for card ending in " + cardNumber.substring(cardNumber.length() - 4));
        return true; 
    }
}