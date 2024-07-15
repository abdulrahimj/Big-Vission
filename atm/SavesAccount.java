package atm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SavesAccount {
    private static int sbalance;
    private static final String BALANCE_FILE = "saves_balance.txt";

    static {
        loadBalance();
    }

    /* getter to get balance */
    public static int getBalance() {
        return sbalance;
    }

    /* options for saves account */
    public static void options() {
        System.out.println("Select your choice (SA)");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer to Current Account");
        System.out.println("5. Previous Menu");
        System.out.println("6. Exit");
        System.out.print("Choice: ");
        int choice = InputHelper.input.nextInt();
        handleChoice(choice);
    }

    /* switch base on the option selected */
    public static void handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Your balance is Le: " + getBalance());
                AccountType.accounts();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                deposit();
                break;
            case 4:
                transferToCurrentAccount();
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
        System.out.print("Amount to withdraw: ");
        int withdra = InputHelper.input.nextInt();
        if (withdra > 0 && withdra <= sbalance) {
            sbalance -= withdra;
            saveBalance();
            System.out.println("You have successfully withdrawn Le: " + withdra);
            System.out.println("Your balance is Le: " + sbalance);
            AccountType.accounts();
        } else if (withdra > sbalance) {
            System.out.println("Insufficient funds. Your balance is Le: " + sbalance);
            AccountType.accounts();
        } else {
            System.out.println("Invalid amount. Please try again.");
            AccountType.accounts();
        }
    }

    /* deposit codes */
    public static void deposit() {
        System.out.print("Amount to deposit: ");
        int deposit = InputHelper.input.nextInt();
        if (deposit > 0) {
            sbalance += deposit;
            saveBalance();
            System.out.println("You have successfully deposited Le: " + deposit);
            System.out.println("Your balance is Le: " + sbalance);
            AccountType.accounts();
        } else {
            System.out.println("Invalid amount. Please try.");
            AccountType.accounts();
        }
    }

    /* deposit transfer from CurrentAccount */
    public static void depositTransfer(int amount) {
        if (amount > 0) {
            sbalance += amount;
            saveBalance();
        }
    }

    /* transfer to CurrentAccount */
    public static void transferToCurrentAccount() {
        System.out.print("Amount to transfer to Current Account: ");
        int transferAmount = InputHelper.input.nextInt();
        if (transferAmount > 0 && transferAmount <= sbalance) {
            sbalance -= transferAmount;
            CurrentAccount.depositTransfer(transferAmount);
            saveBalance();
            System.out.println("You have successfully transferred Le: " + transferAmount + " to current account");
            System.out.println("Your SA balance is Le: " + sbalance);
            AccountType.accounts();
        } else if (transferAmount > sbalance) {
            System.out.println("Insufficient funds. Your SA balance is Le: " + sbalance);
            AccountType.accounts();
        } else {
            System.out.println("Invalid amount. Please try again.");
            AccountType.accounts();
        }
    }

    /* Load balance from file */
    private static void loadBalance() {
        try {
            File file = new File(BALANCE_FILE);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                if (fileScanner.hasNextInt()) {
                    sbalance = fileScanner.nextInt();
                }
                fileScanner.close();
            } else {
                sbalance = 100; // Initial balance if file doesn't exist
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
            writer.write(Integer.toString(sbalance));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to balance file.");
            e.printStackTrace();
        }
    }
}
