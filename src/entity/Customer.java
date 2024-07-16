package entity;

/**
 * This class represents the customer in the online shop.
 * Has customer details like: firstname, lastname, login, email and customer's address.
 *
 * @author JB
 */

public class Customer {

    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String city;
    private String street;

    /**
     * Constructs new Customer with details.
     *
     * @param firstName The first name of the customer.
     * @param lastName  The last name of the customer.
     * @param login     The login name of the customer.
     * @param email     The email address of the customer.
     * @param city      The city of customer's residence.
     * @param street    The street of customer's residence.
     */
    public Customer(String firstName, String lastName, String login, String email, String city, String street) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.email = email;
        this.city = city;
        this.street = street;
    }

    //Getters and setters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName +
                ", " + city +
                ", " + street;
    }
}