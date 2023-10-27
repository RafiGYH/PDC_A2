package pdcassignment2;

import java.util.HashMap;
import java.util.Map;

public class PromoCodeValidator {
    private Map<String, Double> fixedDiscountPromoCodes;
    private Map<String, Double> percentageDiscountPromoCodes;

    public PromoCodeValidator() {
        fixedDiscountPromoCodes = new HashMap<>();
        percentageDiscountPromoCodes = new HashMap<>();
        
        // Fixed Discount Promo Codes
        fixedDiscountPromoCodes.put("F5", 5.0);
        fixedDiscountPromoCodes.put("F2", 2.0);
        fixedDiscountPromoCodes.put("F10", 10.0);
        
        // Percentage Discount Promo Codes
        percentageDiscountPromoCodes.put("P10", 10.0);
        percentageDiscountPromoCodes.put("P20", 20.0);
        percentageDiscountPromoCodes.put("P5", 5.0);
        percentageDiscountPromoCodes.put("P2", 2.0);
    }

    public boolean isValidPromoCode(String code) {
        return fixedDiscountPromoCodes.containsKey(code) || percentageDiscountPromoCodes.containsKey(code);
    }

    public PromoCode getPromoCode(String code) {
        if (fixedDiscountPromoCodes.containsKey(code)) {
            return new FixedDiscountPromo(fixedDiscountPromoCodes.get(code));
        } else if (percentageDiscountPromoCodes.containsKey(code)) {
            return new PercentageDiscountPromo(percentageDiscountPromoCodes.get(code));
        }
        return null;
    }
}
