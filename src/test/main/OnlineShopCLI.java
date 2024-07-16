package test.main;

import service.OnlineShopService;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents online shop where interaction with the user is taking place.
 * This is crucial class with main method. Also, several objects are created and many methods are called on them.
 * Thanks to Scanner object, do-while loops and switch solutions, user is able to use online shop.
 *
 * @author JB
 */

public class OnlineShopCLI {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OnlineShopService onlineShopService = new OnlineShopService(sc);

        System.out.println("Welcome in the OnlineShop Robaczek!");
        System.out.println("\nMake your selection using numbers:");

        int choice = 0;

        do {
            System.out.println("\n1. Start shopping");
            System.out.println("2. Leave the store");
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {
                    onlineShopService.startShopping();
                } else if (choice == 2) {
                    onlineShopService.leavingStoreWithNoPurchase();
                    return;
                } else {
                    System.out.println("Wrong selection. Select 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong type of selection. User numbers (1 or 2) to proceed.");
                sc.nextLine();
            }
        }
        while (choice != 1);

        int choice2 = 0;
        do {
            onlineShopService.printMenu();
            try {
                choice2 = sc.nextInt();
                sc.nextLine();
                switch (choice2) {
                    case 1 -> onlineShopService.browseAndPrintProducts();
                    case 2 -> onlineShopService.addChosenProductToCart();
                    case 3 -> onlineShopService.removeProductFromCart();
                    case 4 -> onlineShopService.checkProductAvailabilityByProductName();
                    case 5 -> onlineShopService.showCurrentCart();
                    case 6 -> onlineShopService.placeOrder();
                    case 7 -> onlineShopService.showShopDiscount();
                    case 8 -> onlineShopService.leavingStore();
                    default -> {
                        System.out.println("Wrong selection. Please use numbers 1-8.\n");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong type of selection. User numbers only (1 to 8) to proceed.\n");
                sc.nextLine();
            }
        }
        while (onlineShopService.openShop());
    }
}


