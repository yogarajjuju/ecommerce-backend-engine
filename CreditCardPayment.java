public class CreditCardPayment implements PaymentProcess{
    private String cardnumber;

    public CreditcardPayment(String cardnumber){
        this.cardnumber = cardnumber;

    }
    @Override
    public boolean processPayment(double amount){
        System.out.println("💳 Processing credit card payment of $" + amount + " for card ending in " + cardNumber.substring(cardNumber.length() - 4));
        return true; // Simulate a successful payment
    }
}