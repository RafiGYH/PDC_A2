/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * PercentageDiscountPromo.Java - Responsible for the percentage based promo codes
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

public class PercentageDiscountPromo implements PromoCode {

    private double discountPercentage;

    public PercentageDiscountPromo(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    public String getDiscountDescription() {
        return discountPercentage + "% off";
    }

    @Override
    public double discount(double originalAmount) {
        double discount = originalAmount * (discountPercentage / 100);
        return originalAmount - discount; // Subtract the discount from the original amount
    }
}
