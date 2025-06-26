import atmHall.ATMHall;
import atmHall.ATMHallImpl;
import atmState.IdleState;
import bankAccount.BankAccount;
import bankAccount.BankAccountImpl;
import card.Card;
import card.CardImpl;
import controllers.userController.UserController;
import controllers.userController.UserControllerImpl;
import enums.ATMOptions;
import user.User;
import user.UserImpl;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMHall atm = new ATMHallImpl(10, 0, 0);
        UserController userController = new UserControllerImpl();
        atm.setAtmState(new IdleState());

        // Sample users
        List<User> users = new ArrayList<>();
        Map<String, Card> userCards = new HashMap<>();

        User user1 = new UserImpl("Mudit Tiwari", "mudit@example.com", 25);
        BankAccount acc1 = new BankAccountImpl("ACC123");
        Card card1 = new CardImpl("CARD123", acc1, "1111");
        user1.setBankAccount(acc1);

        User user2 = new UserImpl("Alice Johnson", "alice@example.com", 30);
        BankAccount acc2 = new BankAccountImpl("ACC456");
        Card card2 = new CardImpl("CARD456", acc2, "2222");
        user2.setBankAccount(acc2);

        userController.addUser(user1);
        userController.addUser(user2);
        users.add(user1);
        users.add(user2);
        userCards.put(user1.getEmail(), card1);
        userCards.put(user2.getEmail(), card2);

        while (true) {
            System.out.println("\n===== ATM MACHINE =====");
            System.out.println("Select user:");
            for (int i = 0; i < users.size(); i++) {
                System.out.println((i + 1) + ". " + users.get(i).getName());
            }
            System.out.println("0. Exit");

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            if (userChoice == 0) break;
            if (userChoice < 1 || userChoice > users.size()) {
                System.out.println("Invalid selection.");
                continue;
            }

            User currentUser = users.get(userChoice - 1);
            Card currentCard = userCards.get(currentUser.getEmail());
            BankAccount currentAccount = currentCard.getBankAccount();

            atm.setUser(currentUser);
            atm.setCard(currentCard);
            atm.setAtmState(new IdleState());

            // Insert card
            atm.getAtmState().insertCard(currentCard, atm);

            // Enter PIN
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();
            if (!atm.getAtmState().validatePin(pin, atm)) {
                System.out.println("PIN validation failed. Returning card.");
                continue;
            }

            // Transaction loop
            boolean sessionActive = true;
            while (sessionActive) {
                System.out.println("\n-- Select Transaction --");
                System.out.println("1. Debit");
                System.out.println("2. Credit");
                System.out.println("3. Passbook");
                System.out.println("0. Exit");

                int option = scanner.nextInt();
                ATMOptions selectedOption = null;
                switch (option) {
                    case 1:
                        selectedOption = ATMOptions.DEBIT;
                        break;
                    case 2:
                        selectedOption = ATMOptions.CREDIT;
                        break;
                    case 3:
                        selectedOption = ATMOptions.PASSBOOK;
                        break;
                    case 0:
                        sessionActive = false;
                        continue;
                    default:
                        System.out.println("Invalid choice.");
                        continue;
                }

                System.out.print("Enter amount (or 0 if not applicable): ");
                int amount = scanner.nextInt();

                if (atm.getAtmState().validateSelectedOption(selectedOption, currentAccount, amount)) {
                    atm.getAtmState().processSelectedOption(selectedOption, currentAccount, amount, atm);
                    if(selectedOption.equals(ATMOptions.DEBIT))
                        atm.getAtmState().dispense(atm, amount);
                    atm.getAtmState().generateReceipt(atm);
                    sessionActive = false;
                }
            }
        }
        System.out.println("Thank you for using the ATM. Goodbye!");
    }
}
