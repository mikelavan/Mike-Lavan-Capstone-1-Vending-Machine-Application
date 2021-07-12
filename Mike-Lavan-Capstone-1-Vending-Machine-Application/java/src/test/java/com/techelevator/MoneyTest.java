package com.techelevator;

import com.techelevator.view.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class MoneyTest {
    @Before
    public void loadItems() throws FileNotFoundException {
//        VendingMachine.itemOrganizer();

    }

    @Test
    public void feedMoneyTest_whereAdding10_bringsBalanceTo10() {
        Money.feedMoney(4);

        Assert.assertEquals(new BigDecimal(10.00).setScale(2), Money.balance);

    }

    @Test
    public void feedMoneyTest_withInvalidData_returns0() {
        Money.feedMoney(9);

        Assert.assertEquals(new BigDecimal(00.00).setScale(2), Money.balance);
    }

    @Test
    public void giveChangeTest_shouldReturnAllTheMoney() {

        Money.feedMoney(1);
        Money.giveChange(Money.balance);
        Assert.assertTrue(new BigDecimal(0.00).compareTo(Money.balance) == 0);
    }

    @Test
    public void purchaseTest_whereEnoughMoney_buysProduct() {
        Money.feedMoney(4);
        Money.purchase("B1");
        Assert.assertEquals(new BigDecimal(10.00).setScale(2), Money.balance);


    }


}
