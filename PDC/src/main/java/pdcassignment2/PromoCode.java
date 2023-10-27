/* Auckland University of Technology (AUT), COMP603 Program Design and Construction
 * 
 * Project 2 - Cinema Booking System
 * PromoCode.Java - Responsible for the interface which the other promo code classes implemenet
 * 
 * @Authors Group #60 | Thomas Brears #20122554 & Rafi Yusaf-Horsfall 20119318
 * @Created August 2023
 * @Modified October 2023
 */
package pdcassignment2;

public interface PromoCode {

    double discount(double price);
    String getCode();
}
