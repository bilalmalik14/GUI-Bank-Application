package coe528.project;

import java.io.*;
import java.nio.file.*;

public class DataManager {

    private static final String DATA_DIRECTORY = "data";

    public static void saveCustomerData(Customer customer) {
        // Ensure the data directory exists
        try {
            Files.createDirectories(Paths.get(DATA_DIRECTORY));
        } catch (IOException e) {
            System.out.println("Error creating data directory: " + e.getMessage());
            return;
        }

        String filename = Paths.get(DATA_DIRECTORY, customer.getUsername() + ".txt").toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(customer.getPassword());
            writer.newLine();
            writer.write(String.valueOf(customer.getBalance()));
            System.out.println("Customer data saved for " + customer.getUsername());
        } catch (IOException e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }
    }

    public static Customer loadCustomerData(String username) {
        Path filePath = Paths.get(DATA_DIRECTORY, username + ".txt");

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String password = reader.readLine();
            double balance = Double.parseDouble(reader.readLine());

            Customer customer = new Customer(username, password);
            customer.deposit(balance - 100); // Assuming initial balance is 100 and adjusting to reflect the correct balance

            return customer;
        } catch (IOException e) {
            System.out.println("Error loading customer data for " + username + ": " + e.getMessage());
            return null;
        }
    }

    public static void deleteCustomerData(String username) {
        Path filePath = Paths.get(DATA_DIRECTORY, username + ".txt");

        try {
            Files.deleteIfExists(filePath);
            System.out.println("Deleted customer data for " + username);
        } catch (IOException e) {
            System.out.println("Error deleting customer data for " + username + ": " + e.getMessage());
        }
    }

    public static Manager loadManagerData() {
        Path filePath = Paths.get(DATA_DIRECTORY, "manager.txt");
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String username = reader.readLine();
            String password = reader.readLine();
            return new Manager(username, password);
        } catch (IOException e) {
            System.out.println("Error reading manager data: " + e.getMessage());
            return null;
        }
    }

    public static boolean customerExists(String username) {
        return Files.exists(Paths.get(DATA_DIRECTORY, username + ".txt"));
    }

}
