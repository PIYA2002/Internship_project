import java.util.Scanner;
import java.util.ArrayList;

class User {
    String username;
    String password;
    double balance;
    ArrayList<String> transactionHistory;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: $" + amount);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void transfer(User recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.balance += amount;
            transactionHistory.add("Transferred: $" + amount + " to " + recipient.username);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class ATM1 {
    ArrayList<User> users;
    Scanner scanner;

    public ATM1() {
        this.users = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void register() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        users.add(newUser);

        System.out.println("Registration successful.");
    }

    public User login() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                System.out.println("Login successful.");
                return user;
            }
        }

        System.out.println("Invalid username or password.");
        return null;
    }

    public static void main(String[] args) {
        ATM1 atm = new ATM1();
        System.out.println("|----------------------------------------------------------|");
        System.out.println("|------------------ WELCOME TO THE ATM!! ------------------|");
        System.out.println("|----------------------------------------------------------|");
        boolean loggedIn = false;
        User currentUser = null;

        while (!loggedIn) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Select an option:");
            int option = Integer.parseInt(atm.scanner.nextLine());

            switch (option) {
                case 1:
                    atm.register();
                    break;
                case 2:
                    currentUser = atm.login();
                    if (currentUser != null) {
                        loggedIn = true;
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        boolean sessionActive = true;

        while (sessionActive) {
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Logout");
            System.out.println("Select an option:");
            int option = Integer.parseInt(atm.scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.println("Enter deposit amount:");
                    double depositAmount = Double.parseDouble(atm.scanner.nextLine());
                    currentUser.deposit(depositAmount);
                    break;
                case 2:
                    System.out.println("Enter withdrawal amount:");
                    double withdrawAmount = Double.parseDouble(atm.scanner.nextLine());
                    currentUser.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Enter recipient username:");
                    String recipientUsername = atm.scanner.nextLine();
                    User recipient = null;
                    for (User user : atm.users) {
                        if (user.username.equals(recipientUsername)) {
                            recipient = user;
                            break;
                        }
                    }
                    if (recipient != null) {
                        System.out.println("Enter transfer amount:");
                        double transferAmount = Double.parseDouble(atm.scanner.nextLine());
                        currentUser.transfer(recipient, transferAmount);
                    } else {
                        System.out.println("Recipient not found.");
                    }
                    break;
                case 4:
                    currentUser.printTransactionHistory();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    sessionActive = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}


