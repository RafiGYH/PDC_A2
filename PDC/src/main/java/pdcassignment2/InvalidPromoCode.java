package pdcassignment2;

public class InvalidPromoCode implements PromoCode {

    @Override
    public double discount(double originalAmount) {
        // The promo code is invalid or it doesn't exist; return the original amount
        return originalAmount;
    }
}