package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.Money;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String SECOND_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SECOND_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SECOND_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SECOND_MENU_OPTIONS = { SECOND_MENU_OPTION_FEED_MONEY, SECOND_MENU_OPTION_SELECT_PRODUCT, SECOND_MENU_OPTION_FINISH_TRANSACTION};


	private static final String MONEY_MENU_OPTION_ONE = "Feed 1 dollar";
	private static final String MONEY_MENU_OPTION_TWO = "Feed 2 dollars";
	private static final String MONEY_MENU_OPTION_FIVE = "Feed 5 dollars";
	private static final String MONEY_MENU_OPTION_TEN = "Feed 10 dollars";
	private static final String[] MONEY_MENU_OPTIONS ={ MONEY_MENU_OPTION_ONE, MONEY_MENU_OPTION_TWO, MONEY_MENU_OPTION_FIVE, MONEY_MENU_OPTION_TEN };
	private static final String[] NEXT = {"RETURN"};


	public static final List<String> vendingMachineItemList = new ArrayList<>();
	private Menu menu;



	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {
		VendingMachine.itemOrganizer();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vendingMachineItems();
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				PurchaseChoiceOptions();

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Exiting Program");
			}
		}
	}

	private void vendingMachineItems() throws FileNotFoundException {
		File vendItems = new File("vendingmachine.csv");
		try (Scanner listReader = new Scanner(vendItems)) {
			while (listReader.hasNextLine()) {
				String vendLine = listReader.nextLine();
				VendingMachineCLI.vendingMachineItemList.add(vendLine);
			}
			for (String s : VendingMachineCLI.vendingMachineItemList) {
				System.out.println(s);
			}
		}
	}


	private void PurchaseChoiceOptions() throws FileNotFoundException {
		String purchaseChoice = "";
		while(!purchaseChoice.contentEquals("Back")){
		purchaseChoice = (String) menu.getChoiceFromOptions(SECOND_MENU_OPTIONS);
		if (purchaseChoice.equals(SECOND_MENU_OPTION_FEED_MONEY)) {
			feedMoneyTransaction();
		}else if (purchaseChoice.equals(SECOND_MENU_OPTION_SELECT_PRODUCT)) {
			purchaseProductTransaction();

		} else if (purchaseChoice.equals(SECOND_MENU_OPTION_FINISH_TRANSACTION)) {
			finalTransaction();

		}
	}}

	private void feedMoneyTransaction() throws FileNotFoundException {
		String moneyDisplay = "";
		moneyDisplay = (String) menu.getChoiceFromOptions(MONEY_MENU_OPTIONS);
		if (moneyDisplay.equals(MONEY_MENU_OPTION_ONE)) {
			Money.feedMoney(1);
		} else if (moneyDisplay.equals(MONEY_MENU_OPTION_TWO)) {
			Money.feedMoney(2);
		} else if (moneyDisplay.equals(MONEY_MENU_OPTION_FIVE)) {
			Money.feedMoney(3);
		} else if (moneyDisplay.equals(MONEY_MENU_OPTION_TEN)) {
			Money.feedMoney(4);
		}


	}

	private void purchaseProductTransaction() throws FileNotFoundException {
		Set<String> itemMapKeys = VendingMachine.itemMap.keySet();
		for (String item: itemMapKeys) {
			System.out.println(VendingMachine.itemMap.get(item) + "|" + VendingMachine.itemStock.get(item) + " IN STOCK");
		}
		System.out.println("1) Return");
		Scanner input = new Scanner(System.in);
		String code = input.nextLine();
		if (code.equals("1")) {
			PurchaseChoiceOptions();
		} else {
			Money.purchase(code);
			purchaseProductTransaction();
		}
		}



	private void finalTransaction() {
		Money.giveChange(Money.getBalance());

	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}




}
