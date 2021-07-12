package com.techelevator.view;

import com.techelevator.Items.Candy;
import com.techelevator.Items.Chip;
import com.techelevator.Items.Drink;
import com.techelevator.Items.Gum;
import com.techelevator.VendingMachine;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class Money {
    public static BigDecimal balance = new BigDecimal(0.00).setScale(2);
    private static int quarters;
    private static int dimes;
    private static int nickels;
    private static double balance1;
    static LogWriter writer = new LogWriter();

    public static BigDecimal getBalance() {
        return Money.balance;
    }



    public static void feedMoney(int addMoney) {
        if (addMoney == 1) {
            balance = balance.add(new BigDecimal(1.00));
            try {
                writer.writer("FEED MONEY", new BigDecimal(1.00).setScale(2), balance);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (addMoney == 2) {
            balance = balance.add(new BigDecimal(2.00));
            try {
                writer.writer("FEED MONEY", new BigDecimal(2.00).setScale(2), balance);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (addMoney == 3) {
            balance = balance.add(new BigDecimal(5.00));
            try {
                writer.writer("FEED MONEY", new BigDecimal(5.00).setScale(2), balance);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (addMoney == 4)
            balance = balance.add(new BigDecimal(10.00));
        try {
            writer.writer("FEED MONEY", new BigDecimal(10.00).setScale(2), balance);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Current Money Provided: " + balance);

    }


    public static void giveChange (BigDecimal balance) {
        balance1 = (balance.doubleValue() * 100);
        quarters = ((int) balance1 / 25);
        balance1 = balance1 - (quarters * 25);
        dimes = ((int) balance1 / 10);
        balance1 = balance1 - (dimes * 10);
        nickels = (int) Math.round(balance1 / 5);
        Money.balance = balance.multiply(new BigDecimal(0.00));
        System.out.println("CHANGE:");
        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        }





    public static void purchase (String selector) {
        if (!VendingMachine.itemMap.containsKey(selector)) {
            System.out.println("ERROR: INPUT DOES NOT EXIST");
        }
        if (VendingMachine.itemMap.containsKey(selector)) {
            if (VendingMachine.itemStock.get(selector) == 0) {
                System.out.println("OUT OF STOCK");
            } else if (balance.compareTo(VendingMachine.itemPrice.get(selector)) == -1) {
                System.out.println("PLEASE DEPOSIT MORE MONEY");

            } else if (balance.compareTo(VendingMachine.itemPrice.get(selector)) ==1  || (balance.compareTo(VendingMachine.itemPrice.get(selector))==0)) {
                BigDecimal updatedBalance = balance;
                balance = balance.subtract(VendingMachine.itemPrice.get(selector));
                VendingMachine.itemStock.put(selector, VendingMachine.itemStock.get(selector) - 1);
                try {
                    writer.writer(VendingMachine.itemName.get(selector), updatedBalance, balance);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println("ITEM: " + VendingMachine.itemName.get(selector) + " BALANCE: " + balance);
                if (selector.contains("A")) {
                    Chip.getSound();
                } else if (selector.contains("B")) {
                    Candy.getSound();
                } else if (selector.contains("C")) {
                    Drink.getSound();
                } else if (selector.contains("D")) {
                    Gum.getSound();
                }
            }

        }
    }

    private void finalTransaction() {
        Money.giveChange(Money.getBalance());
        try {
            writer.writer("GIVE CHANGE", balance, new BigDecimal(0.00).setScale(2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}


