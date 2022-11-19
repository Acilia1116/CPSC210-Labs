package ca.ubc.cs.cpsc210.servicecard.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodServiceCardTest_1 {

    private FoodServicesCard servicesCard;
    private int rewardPurchase; //花多少钱可以有一次cash back；
    public static int BALANCE = 20000;

    @BeforeEach
     void setUp() {
        servicesCard = new FoodServicesCard(BALANCE);
        rewardPurchase = FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK/FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED;
    }

    @Test
    void testConstructor() {
        assertEquals(BALANCE,servicesCard.getBalance());
        assertEquals(0,servicesCard.getRewardPoints());
    }

    @Test
    void testReload() {
        assertEquals(BALANCE,servicesCard.getBalance());
        assertEquals(0,servicesCard.getRewardPoints());
        // call to the method
        servicesCard.reload(10);
        assertEquals(BALANCE + 10,servicesCard.getBalance());
        assertEquals(0,servicesCard.getRewardPoints());
    }

    @Test
    void testMakePurchaseInvalid() {
        assertEquals(BALANCE,servicesCard.getBalance());
        assertEquals(0,servicesCard.getRewardPoints());
        // call to the method and test
        assertFalse(servicesCard.makePurchase(BALANCE + 10));
        assertEquals(BALANCE,servicesCard.getBalance());
        assertEquals(0,servicesCard.getRewardPoints());
    }

    @Test
    void testMakePurchaseNoCashBack() {
        assertEquals(BALANCE,servicesCard.getBalance());
        assertEquals(0,servicesCard.getRewardPoints());
        // call to the method
        assertTrue(servicesCard.makePurchase(rewardPurchase - 1));
        assertEquals(BALANCE-(rewardPurchase-1), servicesCard.getBalance());
        assertEquals( (rewardPurchase-1) * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED,servicesCard.getRewardPoints());
    }

    @Test
    void testMakeValidPurchaseAndCashBack() {
        assertEquals(BALANCE,servicesCard.getBalance());
        assertEquals(0,servicesCard.getRewardPoints());
        // call to the method
        assertTrue(servicesCard.makePurchase(rewardPurchase + 1000));
        // test balance:
        // balance = BALANCE - purchase + CASH_BACK
        // CASH_BACK  = times * CASH_BACK_REWARD
        // times = (points / POINTS_NEEDED_FOR_CASH_BACK) round downward;
        // point = REWARD_POINTS_PER_CENT_CHARGED * purchase

        assertEquals((BALANCE -(rewardPurchase+1000)) + ((FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED * (rewardPurchase + 1000))
                        /FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK)
                        * FoodServicesCard.CASH_BACK_REWARD,
                servicesCard.getBalance());

        // test point:
        // point = earned point - times * POINTS_NEEDED_FOR_CASH_BACK
        // earned point = REWARD_POINTS_PER_CENT_CHARGED * purchase
        // times = (earned point / POINTS_NEEDED_FOR_CASH_BACK) round downward;

        assertEquals((rewardPurchase+1000) * FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED
                        - ((FoodServicesCard.REWARD_POINTS_PER_CENT_CHARGED * (rewardPurchase + 1000))
                                /FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK)
                        * FoodServicesCard.POINTS_NEEDED_FOR_CASH_BACK,
                servicesCard.getRewardPoints());
    }
}
