package ca.ubc.cs.cpsc210.servicecard.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


// Unit tests for FoodServiceCard class
class FoodServicesCardTest {

    private FoodServicesCard card;
    private int moneyPurchase; // money you spend to obtain each cash back chance.

    @BeforeEach
    public void Setup(){
        card = new FoodServicesCard(20000);
        moneyPurchase = FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK /
                FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED;
    }

    @Test
    public void testFoodServiceCard(){
        // initial balance = 0, reward = 0
        assertEquals(20000, card.getBalance());
        assertEquals(0, card.getRewardPoints());
    }

    @Test
    public void testReloadLowerBoundary(){
        // verify initial status: no reward, initial balance
        assertEquals(20000, card.getBalance());
        assertEquals(0, card.getRewardPoints());

        // Reload money first time
        card.reload(5);
        assertEquals(20000 + 5, card.getBalance());
        assertEquals(0, card.getRewardPoints());

        // Reload money twice
        card.reload(5);
        assertEquals(20000 + 5 + 5, card.getBalance());
        assertEquals(0, card.getRewardPoints());
    }

    @Test
    public void testMakePurchaseOnceAndFail(){
        // verify initial status: no reward, initial balance
        assertEquals(20000, card.getBalance());
        assertEquals(0, card.getRewardPoints());

        // make purchase, but fail
        assertFalse(card.makePurchase(20000 + 10));
        assertEquals(20000, card.getBalance());
        assertEquals(0, card.getRewardPoints());

    }

    @Test
    public void testMakePurchaseNoCashBack(){
        // verify initial status: no reward, initial balance
        assertEquals(20000, card.getBalance());
        assertEquals(0, card.getRewardPoints());

        // make purchase once, get reward points, no cash back
        assertTrue(card.makePurchase(moneyPurchase - 10));
        assertEquals( 20000 - (moneyPurchase - 10), card.getBalance());
        assertEquals(FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED * (moneyPurchase - 10),
                card.getRewardPoints());
    }

    @Test
    public void testMakePurchaseWithCashBack(){
        // verify initial status: no reward, initial balance
        assertEquals(20000, card.getBalance());
        assertEquals(0, card.getRewardPoints());

        // make purchase once, get reward, get cash back
        assertTrue(card.makePurchase(moneyPurchase + 1000));

        // verify account balance
        assertEquals( 20000 - ( moneyPurchase + 1000 ) +
                        (((moneyPurchase + 1000) * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED)  // reward points obtained from purchase
                        / FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK) // reward points need for each cash back
                        * FoodServicesCard.CASH_BACK_REWARD, // money obtained from each cash back
                card.getBalance());

        // verify reward point
        assertEquals(0 + (moneyPurchase + 1000) * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED // reward points obtained from purchase (before cash back)
                - (((moneyPurchase + 1000) * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED)
                / FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK) // how many cash back chance you have
                * FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK, card.getRewardPoints()); // reward points spend for each cash back

    }



}