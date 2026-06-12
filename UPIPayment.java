public class UPIPayment implements Paymentprocess{
    public String upiID;

    public UPIPayment(String upiID){
        this.upiId = upiID;
    }
    @Override
    public boolean processPayment(double amount) {
        System.out.println("📱 Processing UPI payment of $" + amount + " to ID: " + upiId);
        return true; // Simulate a successful payment
    }
}