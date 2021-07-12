package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class VendingMachine {

    private static File itemsList = new File("vendingmachine.csv");
    public static String[] listArray = new String[4];
    public static Map<String, String> itemMap = new TreeMap<>();
    public static Map<String, Integer> itemStock = new TreeMap<>();
    public static Map<String, BigDecimal> itemPrice = new TreeMap<>();
    public static Map<String, String> itemType = new TreeMap<>();
    public static Map<String, String> itemName = new TreeMap<>();


    public static void itemOrganizer() throws FileNotFoundException {
        try (Scanner readList = new  Scanner(itemsList)) {
            while (readList.hasNextLine()) {
                String listLine = readList.nextLine();
                listArray = listLine.split("\\|");
                itemMap.put(listArray[0], listLine);
                itemPrice.put(listArray[0], new BigDecimal(listArray[2]));
                itemName.put(listArray[0], listArray[1]);
                itemStock.put(listArray[0], 5);
                itemType.put(listArray[0], listArray[3]);

            }


        }

    }
}













