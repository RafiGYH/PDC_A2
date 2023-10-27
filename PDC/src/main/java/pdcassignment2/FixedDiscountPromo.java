/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * FixedDiscountPromo.Java - Responsible for Fixed amount promo codes
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

public class FixedDiscountPromo implements PromoCode {

    private String code;
    private double discountAmount;

    public FixedDiscountPromo(double discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    public String getDiscountDescription() {
        return "$" + discountAmount + " off";
    }

    @Override
    public double discount(double originalAmount) {
        return originalAmount - discountAmount; // Return the fixed discount amount
    }
    
    @Override
    public String getCode() {
        return code;
    }
}
