package service;

import entity.Cart;
import entity.Customer;
import entity.Order;
import entity.products.Product;
import manager.ProductManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * This class gathers many method related strictly to the online shop functionality.
 */

public class OnlineShopService {
    private final ProductManager productManager = new ProductManager();
    private final Scanner sc;
    private final OrderProcessor orderProcessor = new OrderProcessor();
    private final Cart cart = new Cart();
    private boolean shopOpen = true;

    public OnlineShopService(Scanner scanner) {
        this.sc = scanner;
    }

    public boolean openShop() {
        return shopOpen;
    }

    public void closeShop() {
        shopOpen = false;
    }

    public void startShopping() {
        System.out.println("Lets start shopping!");
    }

    /**
     * This void method browse and print all products in the store.
     */
    public void browseAndPrintProducts() {
        System.out.println("PRODUCTS IN ROBACZEK SHOP:\n");
        List<Product> products = productManager.getAllProducts();
        products.forEach(System.out::println);
        System.out.println("\nAbove you can see all available products. Please chose again what would you like to do.\n");
    }

    /**
     * This void method asks for product name to be added to the cart and adds it, if product in selected quantity is available in the shop.
     *
     * @throws IllegalArgumentException If chosen quantity is not available.
     * @throws InputMismatchException   If quantity is not provided by numbers only.
     */
    public void addChosenProductToCart() {
        try {
            System.out.println("Please provide the name of selected product to be added to your basket: ");
            String userProductName = sc.nextLine().toLowerCase();

            List<String> productsNames = productManager.getProductsNames();
            addProductToCart(productsNames, userProductName);
        } catch (InputMismatchException e) {
            System.err.println("Wrong input. Please provide quantity by numbers only.\n");
            sc.nextLine();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method provides adding product to the cart logic.
     * Checks if the product user is looking for is in the shop and if desired quantity is available.
     *
     * @param productsNames        The list of products names.
     * @param userChoseProductName The name user provided.
     */

    private void addProductToCart(List<String> productsNames, String userChoseProductName) {
        productManager.getProductByName(userChoseProductName).ifPresentOrElse(
                product -> {
                    int quantityAvailableOfProduct = product.getProductQuantity();
                    System.out.println("Quantity available of chosen product: " + quantityAvailableOfProduct);
                    System.out.println("Please provide desired quantity of chosen product:");
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    if (quantityAvailableOfProduct < quantity) {
                        throw new IllegalArgumentException("Chosen quantity unavailable. Please try again.\n");
                    } else {
                        boolean isNewProduct = cart.addProductToCart(product, quantity);
                        if (isNewProduct) {
                            System.out.println("Great! Chosen product added to basket. What next?\n");
                        } else {
                            System.out.println("Quantity of chosen product updated in the basket. What next?\n");
                        }
                    }
                },
                () -> System.out.println("No product with such name. Please try again.\n"
                ));
    }

    /**
     * Method responsible for providing information once desired product is already in the basket.
     * Updating quantity of product in the basket if desired quantity is available.
     *
     * @param us Represents user.
     * @param q  Represents quantity.
     * @param p  Represents product.
     */

    private void productAlreadyInTheCartUpdate(String us, int q, Product p) {
        Product pr = cart.getProductByNameFromCart(us).get();
        System.out.println("You already have " + pr.getProductQuantity() + " of " + pr.getName() + " in your basket.");
        pr.setProductQuantity(pr.getProductQuantity() + q);
        System.out.println("Updating quantity of " + pr.getName() + " in your basket to: " + pr.getProductQuantity() + "\n");
        p.setProductQuantity(p.getProductQuantity() - q);
    }

    /**
     * Method asks for product name to be removed from the cart and considering user answer removes appropriately desired product.
     */
    public void removeProductFromCart() {
        System.out.println("Please provide name of product you would like to remove from your basket:");
        String userProductName = sc.nextLine().toLowerCase();
        checkingIfProductIsInCart(userProductName);
    }

    /**
     * Method checks if mentioned product is already in the cart.
     */
    private void checkingIfProductIsInCart(String productName) {
        cart.getProductByNameFromCart(productName).ifPresentOrElse(
                product -> {
                    System.out.println("Product and quantity you bought:");
                    System.out.println(product.getName() + ": " + product.getProductQuantity());
                    removeProductFromCart(product);
                },
                () -> System.out.println("There is no such product in the basket. Please try again.\n")
        );
    }

    /**
     * This method contains removing product from the cart logic.
     * Checks what quantity of product is selected and considering it provide removing appropriate logic.
     *
     * @param product Product chosen to be removed from the user's cart.
     * @throws InputMismatchException   If not numbers to remove quantity of product are used.
     * @throws IllegalArgumentException If mentioned product is not added in the cart.
     */
    private void removeProductFromCart(Product product) {
        try {
            if (product.getProductQuantity() > 1) {
                System.out.println("How many of mentioned product would you like to remove?");
                int qToBeDeducted = sc.nextInt();
                if (product.getProductQuantity() < qToBeDeducted) {
                    System.out.println("The quantity of product you have in your cart: " + product.getProductQuantity());
                    System.out.println("You can not remove " + qToBeDeducted + " products. Please try again.\n");
                } else {
                    cart.removeProductsFromCartByQuantity(product, qToBeDeducted);
                    productManager.getProductByName(product.getName()).get().setProductQuantity(product.getProductQuantity() + qToBeDeducted);
                    System.out.println(qToBeDeducted + " of " + product.getName() + " correctly removed from your basket.\n");
                }
            } else {
                System.out.println("Removing: " + product.getName());
                cart.removeProductFromCart(product);
                System.out.println("Product correctly removed from your basket.\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("To remove selected quantity of product please use numbers only.");
        } catch (IllegalArgumentException e) {
            System.out.println("There is no such quantity of product in your basket. Please try again.");
        }
    }

    /**
     * This method is responsible for printing current content of the cart.
     * Shows products and summed rounded (to two decimal places) price for it.
     * If there is no products in the cart then throws IllegalStateException.
     *
     * @throws IllegalStateException If in the cart there is no products at all.
     */
    public void showCurrentCart() {
        try {
            double totalSum = cart.getTotalAmount(); //
            BigDecimal totalSumRounded = new BigDecimal(totalSum).setScale(2, RoundingMode.HALF_UP);
            if (!cart.getProductsInCart().isEmpty()) {
                System.out.println("Products in your basket:");
                cart.viewCart();
                System.out.println("\nTotal sum of your basket = " + totalSumRounded + "zł.\n");
            } else {
                throw new IllegalStateException("There are no products in your basket yet. Let's add something!\n");
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method responsible for checking correctness of provided username or city.
     *
     * @param nameOrCity Represent name or city.
     * @return Valid format of the name or the city.
     */
    private String getValidatedNamesOrCity(String nameOrCity) {
        String name;
        boolean isValid;
        do {
            System.out.println(nameOrCity + " please:");
            name = sc.nextLine();
            isValid = true;
            for (int i = 0; i < name.length(); i++) {
                if (!Character.isLetter(name.charAt(i))) {
                    isValid = false;
                    System.out.println("Wrong format. Use only letters. Please try again.\n");
                    break;
                }
            }
        } while (!isValid);
        return name;
    }

    /**
     * Method responsible for checking correctness of provided email.
     *
     * @return Valid format of the email.
     */
    private String getValidatedEmail() {
        String email;
        boolean isValid;
        do {
            System.out.println("Email please:");
            email = sc.nextLine();
            isValid = email.contains("@");
            if (!isValid) {
                System.out.println("Wrong email format. Email must contain \'@\'. Please try again.\n");
            }
        } while (!isValid);
        return email;
    }

    /**
     * Method responsible for checking correctness of provided street.
     *
     * @return Valid format of the street.
     */
    private String getValidatedStreet() {
        String street;
        boolean isValid;
        do {
            System.out.println("Street:");
            street = sc.nextLine();
            boolean hasLetter = false;
            boolean hasDigit = false;

            for (int i = 0; i < street.length(); i++) {
                char streetCh = street.charAt(i);
                if (Character.isLetter(streetCh)) {
                    hasLetter = true;
                }
                if (Character.isDigit(streetCh)) {
                    hasDigit = true;
                }
                if (hasDigit && hasLetter) {
                    break;
                }
            }
            isValid = hasDigit && hasLetter;
            if (!isValid) {
                System.out.println("Please provide full residence details. Not only name of the street.");
            }
        } while (!isValid);
        return street;
    }

    /**
     * This method helps placeOrder method to create the order.
     * Asks user to provide his details to set up the order. Basing on it creates new Customer and then new Order.
     * If user provides wrongly his details, throws InputMismatchException.
     *
     * @throws InputMismatchException If user provides wrongly his details
     */

    private void providingDetailsForOrder() {
        try {
            System.out.println("Login please:");
            String login = sc.nextLine();
            String firstname = getValidatedNamesOrCity("Firstname");
            String surname = getValidatedNamesOrCity("Surname");
            String email = getValidatedEmail();
            System.out.println("Now proceed with your address please:");
            String city = getValidatedNamesOrCity("City");
            String street = getValidatedStreet();

            Customer customer = new Customer(firstname, surname, login, email, city, street);
            List<Product> productsInCart = cart.getProductsInCart();
            double totalAmount = cart.getTotalAmount();
            double totalAmountRounded = getAmountRounded(totalAmount);

            Order order = new Order(generateRandomId(), customer, productsInCart, totalAmountRounded);
            orderProcessor.processOrder(order);
        } catch (InputMismatchException e) {
            System.out.println("Name, surname or email can not be only numbers. Please try again with correct format.");
        }
    }

    /**
     * This method rounds double value to two decimal places.
     *
     * @param amount Amount to be rounded.
     * @return Double value rounded.
     */
    private double getAmountRounded(double amount) {
        BigDecimal bd = BigDecimal.valueOf(amount);
        BigDecimal amountRounded = bd.setScale(2, RoundingMode.HALF_UP);
        return amountRounded.doubleValue();
    }

    /**
     * This method is responsible for placing order action. Checks if there are products in the cart and if some order has been placed already.
     * Also asks the user if he would like to proceed with another order or to end the shopping and leave the shop.
     */
    public void placeOrder() {
        if (cart.getProductsInCart().isEmpty()) {
            System.out.println("There are no product in your basket. Order can not be proceeded. Please first add some product to your basket.\n");
        } else {
            if (orderProcessor.getOrders().isEmpty()) {
                System.out.println("Let's proceed with your order. Please provide your account details:");
            } else {
                System.out.println("Let's proceed with your another order. Please provide your account details:");
            }
            providingDetailsForOrder();
            System.out.println("Would you like to make another order? (YES/NO)");
            String anotherOrderDecision = sc.nextLine().toLowerCase();
            if (anotherOrderDecision.equals("yes")) {
                cart.clearProductCart();
                System.out.println("Awesome! Let's do some more shopping together." + "\n");
            } else if (anotherOrderDecision.equals("no")) {
                leavingStore();
            }
        }
    }

    /**
     * Method responsible for generating random id (used for orders).
     *
     * @return Random int value from 1 to 1000.
     */
    private int generateRandomId() {
        return (int) (Math.random() * 1000);
    }

    /**
     * Prints options available in the shop's menu.
     */
    public void printMenu() {
        System.out.println("Make your selection using numbers:");
        System.out.println("1. Show products available");
        System.out.println("2. Add product to basket by product name");
        System.out.println("3. Delete product from your basket");
        System.out.println("4. Check product availability");
        System.out.println("5. Show actual basket");
        System.out.println("6. Proceed with the order");
        System.out.println("7. Show discounts");
        System.out.println("8. Leave the store");
    }

    /**
     * Used to leave the store without purchase and end the program.
     * Prints thank message to the user and set variable shopOpen to false.
     */
    public void leavingStoreWithNoPurchase() {
        System.out.println("Even if you did not buy anything... thank you for your visit! See you soon!");
        closeShop();
    }

    /**
     * Method used to leave the store. Checks if cart is empty or not, if it is not then print cart's content.
     */

    public void leavingStore() {
        try {
            if (cart.getProductsInCart().isEmpty()) {
                doubleCheckIfLeaving();
            } else if (!orderProcessor.getOrders().isEmpty() && !cart.getProductsInCart().isEmpty()) {
                System.out.println("You bought today:");
                cart.viewCart();
                System.out.println("\nGreat shopping! It was a pleasure! Thank you and see you soon!");
                closeShop();
            } else {
                doubleCheckIfLeaving();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please decide if you want to exit the shop or not by \"yes\" or \"no\".");
        }
    }

    /**
     * Used to double-check if user wants to leave the shop.
     */

    private void doubleCheckIfLeaving() {
        boolean wrongUserAnswer;
        do {
            System.out.println("Are you sure you want to exit the shop without proceeding with the order? (YES/NO)");
            String userSaying = sc.nextLine();
            wrongUserAnswer = false;
            if (userSaying.equalsIgnoreCase("YES")) {
                leavingStoreWithNoPurchase();
            } else if (userSaying.equalsIgnoreCase("NO")) {
                System.out.println("Good choice. Please come back to do some shopping!\n");
            } else {
                wrongUserAnswer = true;
                System.out.println("Please decide if you want to exit the shop or not by \"yes\" or \"no\".");
            }
        } while (wrongUserAnswer);
    }

    /**
     * This method is responsible for checking product's availability.
     * User is asked to provide product name and basing on it method shows either available quantity of mentioned product,
     * or if there is no product in the shop with product name mentioned, prints information about wrong product name.
     */
    public void checkProductAvailabilityByProductName() {
        System.out.println("Provide product name:");
        String productName = sc.nextLine();

        productManager.getProductByName(productName).ifPresentOrElse(
                product -> {
                    int available = product.getProductQuantity();
                    System.out.println(productName + ", availability: " + available + "\n");
                },
                () -> System.out.println("No such product in the store.\n")
        );
    }

    /**
     * Prints information how discounts work in Robaczek Shop.
     */
    public void showShopDiscount() {
        try {
            System.out.println("In Robaczek Shop we offer 2 discounts! See the details below:\n");
            System.out.println("1: If your basket exceeds 10.000zł you will get 5% discount of sum price.");
            System.out.println("2: If your basket exceeds 50.000zł you will get 10% discount of sum price.\n");
            System.out.println("Let's do some shopping!\n");
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }
}

//TODO: dokonczyc git i wrzucic to do repo, merge, nowy branch i spushowac