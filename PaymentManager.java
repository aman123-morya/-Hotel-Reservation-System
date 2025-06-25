public class PaymentManager {
    public boolean processPayment(String customerName, double amount) {
        System.out.println("Payment of Rs." + amount + " from " + customerName + " processed successfully.");
        return true;
    }
}

