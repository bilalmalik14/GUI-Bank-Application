package coe528.project;

/**
 * Overview: The Manager class represents a manager in the bank system with capabilities to add and delete customers.
 * This class is immutable once set with a username and password, as these cannot be changed after instantiation.
 *
 * Abstraction Function: Represents a manager by their username and password with the role of "manager".
 *
 * Rep Invariant: username and password are non-null and non-empty strings; role is always "manager".
 */
public class Manager extends User {
    /**
     * Constructs a Manager with the specified username and password.
     *
     * @param username the username of the manager
     * @param password the password of the manager
     * @requires username != null && password != null
     * @effects initializes a manager with the specified username and password
     */
    public Manager(String username, String password) {
        super(username, password, "manager");
    }

    /**
     * Adds a new customer to the system.
     *
     * @param username the username of the new customer
     * @param password the password of the new customer
     * @requires username != null && password != null
     * @modifies possibly the data storage where customers are saved
     * @effects adds a new customer with the given username and password to the system
     */
    public void addCustomer(String username, String password) {
        Customer customer = new Customer(username, password);
        DataManager.saveCustomerData(customer);
    }

    /**
     * Deletes an existing customer from the system.
     *
     * @param username the username of the customer to delete
     * @requires username != null
     * @modifies possibly the data storage where customers are saved
     * @effects deletes the customer with the given username from the system
     */
    public void deleteCustomer(String username) {
        DataManager.deleteCustomerData(username);
    }

    /**
     * Returns a string representation of the manager.
     *
     * @return a string representation of the manager
     * @effects returns a string that represents the manager
     */
    @Override
    public String toString() {
        return "Manager{username=" + getUsername() + "}";
    }

    /**
     * Checks the representation invariant of the manager.
     *
     * @return true if the rep invariant holds for this object, false otherwise
     */
    public boolean repOk() {
        return getUsername() != null && !getUsername().isEmpty() &&
               getPassword() != null && !getPassword().isEmpty() &&
               getRole().equals("manager");
    }
}
