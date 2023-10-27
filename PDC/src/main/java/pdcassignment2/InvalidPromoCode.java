package pdcassignment2;

public class InvalidPromoCode implements PromoCode {

    private String code;
    
    @Override
    public double discount(double originalAmount) {
        // The promo code is invalid or it doesn't exist; return the original amount
        return originalAmount;
    }
    
    @Override
    public String getCode() {
        return null;
    }
}
