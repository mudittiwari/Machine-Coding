package atmState;

import atmHall.ATMHall;
import bankAccount.BankAccount;
import enums.ATMOptions;
import enums.BalanceUpdateType;
import enums.StateType;

import java.util.Scanner;

public class SelectOptionState extends ATMState{

    @Override
    public ATMOptions selectOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select an option:");
        for (ATMOptions option : ATMOptions.values()) {
            System.out.println("- " + option.name());
        }
        String input = scanner.nextLine().trim().toUpperCase();
        try {
            ATMOptions selectedOption = ATMOptions.valueOf(input);
            System.out.println("You selected: " + selectedOption);
            return selectedOption;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option selected. Please try again.");
        }
        return null;
    }

    @Override
    public boolean validateSelectedOption(ATMOptions option, BankAccount bankAccount, double amount) {
        if (option.equals(ATMOptions.DEBIT)) {
            if (amount > 0 && amount <= bankAccount.getBalance()) {
                System.out.println("Withdrawal of ₹" + amount + " is approved.");
                return true;
            } else {
                System.out.println("Invalid amount. Either negative or exceeds balance.");
                return false;
            }
        }
        else if(option.equals(ATMOptions.CREDIT)){
            System.out.println("account credit is approved");
            return true;
        }
        System.out.println("Option not supported yet.");
        return false;
    }


    @Override
    public void processSelectedOption(ATMOptions option, BankAccount bankAccount, double amount, ATMHall atmHall){
        if(option.equals(ATMOptions.DEBIT)){
            if(bankAccount.updateBalance(amount, BalanceUpdateType.DEBIT)){
                atmHall.setAtmState(new DispenseState());
            }

        }
        else if(option.equals(ATMOptions.CREDIT)){
            if(bankAccount.updateBalance(amount, BalanceUpdateType.CREDIT)){
                atmHall.setAtmState(new DispenseState());
            }

        }
    }
}
