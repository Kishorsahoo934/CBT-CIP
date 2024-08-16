import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountHolderName;
    private String accountNumber;
    private double balance;

    public BankAccount(String accountHolderName, String accountNumber, double initialBalance) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
        } else {
            System.out.println("Invalid withdraw amount.");
        }
    }

    public void transfer(BankAccount recipient, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            System.out.println("Successfully transferred: $" + amount + " to " + recipient.getAccountHolderName());
        } else {
            System.out.println("Invalid transfer amount.");
        }
    }

    @Override
    public String toString() {
        return "Account Holder: " + accountHolderName + ", Account Number: " + accountNumber + ", Balance: $" + balance;
    }
}

public class BANKY {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- BankY System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Display Accounts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    displayAccounts();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void createAccount(Scanner scanner) {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        BankAccount newAccount = new BankAccount(name, accNumber, balance);
        accounts.add(newAccount);
        System.out.println("Account created successfully!");
    }

    private static void deposit(Scanner scanner) {
        BankAccount account = findAccount(scanner);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        }
    }

    private static void withdraw(Scanner scanner) {
        BankAccount account = findAccount(scanner);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        }
    }

    private static void transfer(Scanner scanner) {
        System.out.println("Sender Account:");
        BankAccount sender = findAccount(scanner);
        if (sender != null) {
            System.out.println("Recipient Account:");
            BankAccount recipient = findAccount(scanner);
            if (recipient != null) {
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                sender.transfer(recipient, amount);
            }
        }
    }

    private static BankAccount findAccount(Scanner scanner) {
        System.out.print("Enter account number: ");
        String accNumber = scanner.nextLine();
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accNumber)) {
                return account;
            }
        }
        System.out.println("Account not found.");
        return null;
    }

    private static void displayAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            for (BankAccount account : accounts) {
                System.out.println(account);
            }
        }
    }
}
