package atm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CurrentAccount {
    private static int balance;
    private static final String BALANCE_FILE = "current_balance.txt";

    static {
        loadBalance();
    }

    /* getter to get balance */
    public static int getBalance() {
        return balance;
    }

    /* options for current account */
    public static void options() {
        System.out.println("\n....................");
        System.out.println("\nSelect your choice (CA)");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer to Saves Account");
        System.out.println("5. Previous Menu");
        System.out.println("6. Exit");
        System.out.print("Choice: ");
        int choice = InputHelper.input.nextInt();
        handleChoice(choice);
    }

    /* switch base on the option selected */
    public static void handleChoice(int choice) {
        System.out.println("\n....................");
        switch (choice) {
            case 1:
                System.out.println("\nYour balance is Le: " + getBalance());
                System.out.println("\n....................\n");
                AccountType.accounts();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                deposit();
                break;
            case 4:
                transferToSavesAccount();
                break;
            case 5:
                AccountType.accounts();
                break;
            case 6:
                break;
            default:
                System.out.println("Invalid selection!");
                break;
        }
    }

    /* withdraw codes */
    public static void withdraw() {
        System.out.print("\nAmount to withdraw: ");
        int withdra = InputHelper.input.nextInt();
        if (withdra > 0 && withdra <= balance) {
            balance -= withdra;
            saveBalance();
            System.out.println("You have successfully withdrawn Le: " + withdra);
            System.out.println("Your balance is Le: " + balance);
            System.out.println("\n....................\n");
            AccountType.accounts();
        } else if (withdra > balance) {
            System.out.println("Insufficient funds. Your balance is Le: " + balance);
            AccountType.accounts();
        } else {
            System.out.println("Invalid amount. Please try again.");
            AccountType.accounts();
        }
    }

    /* deposit codes */
    public static void deposit() {
        System.out.print("\nAmount to deposit: ");
        int deposit = InputHelper.input.nextInt();
        if (deposit > 0) {
            balance += deposit;
            saveBalance();
            System.out.println("You have successfully deposited Le: " + deposit);
            System.out.println("Your balance is Le: " + balance);
            System.out.println("\n....................\n");
            AccountType.accounts();
        } else {
            System.out.println("Invalid amount. Please try again.");
            AccountType.accounts();
        }
    }

    /* transfer to SavesAccount */
    public static void transferToSavesAccount() {
        System.out.print("Amount to transfer to Saves Account: ");
        int transferAmount = InputHelper.input.nextInt();
        if (transferAmount > 0 && transferAmount <= balance) {
            balance -= transferAmount;
            SavesAccount.depositTransfer(transferAmount);
            saveBalance();
            System.out.println("You have successfully transferred Le: " + transferAmount + " to saves account");
            System.out.println("Your CA balance is Le: " + balance);
            AccountType.accounts();
        } else if (transferAmount > balance) {
            System.out.println("Insufficient funds. Your CA balance is Le: " + balance);
            AccountType.accounts();
        } else {
            System.out.println("Invalid amount. Please enter a positive number.");
            AccountType.accounts();
        }
    }

    /* deposit transfer from SavesAccount */
    public static void depositTransfer(int amount) {
        if (amount > 0) {
            balance += amount;
            saveBalance();
        }
    }

    /* Load balance from file */
    private static void loadBalance() {
        try {
            File file = new File(BALANCE_FILE);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                if (fileScanner.hasNextInt()) {
                    balance = fileScanner.nextInt();
                }
                fileScanner.close();
            } else {
                balance = 500; // Initial balance if file doesn't exist
                saveBalance();
            }
        } catch (IOException e) {
            System.out.println("Error reading balance file.");
            e.printStackTrace();
        }
    }

    /* Save balance to file */
    private static void saveBalance() {
        try {
            FileWriter writer = new FileWriter(BALANCE_FILE);
            writer.write(Integer.toString(balance));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to balance file.");
            e.printStackTrace();
        }
    }
}
