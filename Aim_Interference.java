import java.util.InputMismatchException;
import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
        return false;
    }
}

class ATM {
    private BankAccount account;

    public ATM(BankAccount bankAccount) {
        account = bankAccount;
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                displayMenu();
                System.out.print("Select an option: ");
                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Clear the invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.println("Your balance: Rs. " + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount;
                        try {
                            depositAmount = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid amount.");
                            scanner.next(); // Clear the invalid input
                            break;
                        }
                        account.deposit(depositAmount);
                        System.out.println("Deposit successful. Your balance: Rs. " + account.getBalance());
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount;
                        try {
                            withdrawAmount = scanner.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid amount.");
                            scanner.next(); // Clear the invalid input
                            break;
                        }
                        if (account.withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful. Your balance: Rs. " + account.getBalance());
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM!");
                        return;
                    default:
                        System.out.println("Invalid option. Please select a valid option.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}

public class Aim_Interference{
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000); // Initial balance
        ATM atm = new ATM(userAccount);
        atm.run();
    }
}
