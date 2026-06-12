public class UPIPayment implements PaymentProcessor {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("📱 Processing UPI payment of $" + amount + " to ID: " + upiId);
        return true; 
    }
}