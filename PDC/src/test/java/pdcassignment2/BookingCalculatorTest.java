package pdcassignment2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BookingCalculatorTest {

    // Calculates the total price for a standard cinema without any promo code
    @Test
    public void testCalculateTotalPriceStandardCinema() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema standardCinema = new StandardCinema("Standard", 100);
        PromoCode promoCode = null;
        double price = calculator.calculateTotalPrice(2, 3, promoCode, standardCinema);
        assertEquals(60.0, price, "Total price for 2 adults and 3 children in a standard cinema should be $60");
    }

    // Calculates the total price for a premium cinema without any promo code
    @Test
    public void testCalculateTotalPricePremiumCinema() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema premiumCinema = new PremiumCinema("Premium", 100, 10);
        PromoCode promoCode = null;
        double price = calculator.calculateTotalPrice(2, 3, promoCode, premiumCinema);
        assertEquals(110.0, price, "Total price for 2 adults and 3 children in a premium cinema should be $110");
    }

    // Calculates the total price with negative numbers of tickets
    @Test
    public void testCalculateTotalPriceWithNegativeTickets() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema standardCinema = new StandardCinema("Standard", 100);
        PromoCode promoCode = null;
        double price = calculator.calculateTotalPrice(-2, -3, promoCode, standardCinema);
        assertEquals(0.0, price, "Total price for negative tickets should be $0");
    }

    // Calculates the total price with zero tickets
    @Test
    public void testCalculateTotalPriceWithZeroTickets() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema standardCinema = new StandardCinema("Standard", 100);
        PromoCode promoCode = null;
        double price = calculator.calculateTotalPrice(0, 0, promoCode, standardCinema);
        assertEquals(0.0, price, "Total price for zero tickets should be $0");
    }

    // Calculates the total price with an invalid promo code
    @Test
    public void testCalculateTotalPriceWithInvalidPromoCode() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema premiumCinema = new PremiumCinema("Premium", 100, 10);
        PromoCode invalidPromoCode = new InvalidPromoCode();
        double price = calculator.calculateTotalPrice(2, 3, invalidPromoCode, premiumCinema);
        assertEquals(110.0, price, "Total price for 2 adults and 3 children in a premium cinema with an invalid promo code should be $110");
    }

    // Calculates the total price with a 100% discount
    @Test
    public void testCalculateTotalPriceWith100PercentDiscount() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema premiumCinema = new PremiumCinema("Premium", 100, 10);
        PromoCode hundredPercentDiscount = new PercentageDiscountPromo(100);
        double price = calculator.calculateTotalPrice(2, 3, hundredPercentDiscount, premiumCinema);
        assertEquals(0.0, price, "Total price for 2 adults and 3 children in a premium cinema with a 100% discount should be $0");
    }

    // Calculates the total price with more than 100% discount
    @Test
    public void testCalculateTotalPriceWithMoreThan100PercentDiscount() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema premiumCinema = new PremiumCinema("Premium", 100, 10);
        PromoCode moreThanHundredPercentDiscount = new PercentageDiscountPromo(150);
        double price = calculator.calculateTotalPrice(2, 3, moreThanHundredPercentDiscount, premiumCinema);
        assertEquals(0.0, price, "Total price for 2 adults and 3 children in a premium cinema with more than 100% discount should be $0");
    }

    // Calculates the total price with a fixed discount greater than total price
    @Test
    public void testCalculateTotalPriceWithFixedDiscountGreaterThanTotalPrice() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema standardCinema = new StandardCinema("Standard", 100);
        PromoCode fixedDiscount = new FixedDiscountPromo(100);
        double price = calculator.calculateTotalPrice(2, 3, fixedDiscount, standardCinema);
        assertEquals(0.0, price, "Total price for 2 adults and 3 children in a standard cinema with a fixed discount greater than total price should be $0");
    }

    // Calculates the total price for a premium cinema with zero tickets
    @Test
    public void testCalculateTotalPricePremiumCinemaWithZeroTickets() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema premiumCinema = new PremiumCinema("Premium", 100, 10);
        PromoCode promoCode = null;
        double price = calculator.calculateTotalPrice(0, 0, promoCode, premiumCinema);
        assertEquals(0.0, price, "Total price for zero tickets in a premium cinema should be $0");
    }

    // Calculates the total price for a standard cinema with a promo code
    @Test
    public void testCalculateTotalPriceStandardCinemaWithPromoCode() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema standardCinema = new StandardCinema("Standard", 100);
        PromoCode promoCode = new FixedDiscountPromo(10);
        double price = calculator.calculateTotalPrice(2, 3, promoCode, standardCinema);
        assertEquals(50.0, price, "Total price for 2 adults and 3 children in a standard cinema with a $10 fixed discount should be $50");
    }

    // Calculates the total price for a premium cinema with a promo code
    @Test
    public void testCalculateTotalPricePremiumCinemaWithPromoCode() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema premiumCinema = new PremiumCinema("Premium", 100, 10);
        PromoCode promoCode = new PercentageDiscountPromo(10);
        double price = calculator.calculateTotalPrice(2, 3, promoCode, premiumCinema);
        assertEquals(99.0, price, "Total price for 2 adults and 3 children in a premium cinema with a 10% discount should be $99");
    }

    // Calculates the total price by applying a promo code twice
    @Test
    public void testCalculateTotalPriceApplyingPromoCodeTwice() {
        BookingCalculator calculator = new BookingCalculator();
        Cinema standardCinema = new StandardCinema("Standard", 100);
        PromoCode promoCode = new FixedDiscountPromo(10);
        double price = calculator.calculateTotalPrice(2, 3, promoCode, standardCinema);
        assertEquals(50.0, price, "Total price for 2 adults and 3 children in a standard cinema with a $10 fixed discount applied twice should be $50");
    }
}