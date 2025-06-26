package atmState;

import atmHall.ATMHall;
import bankAccount.BankAccount;
import card.Card;
import enums.ATMOptions;

public class ATMState {

    //first state methods
    public void insertCard(Card card, ATMHall atmHall){
        System.out.println("method called from invalid state");
    }

    //second state methods
    public String enterPin(){
        System.out.println("method called from invalid state");
        return "";
    }
    public boolean validatePin(String pin, ATMHall atmHall){
        System.out.println("method called from invalid state");
        return false;
    }

    //third state methods
    public ATMOptions selectOptions() {
        System.out.println("method called from invalid state");
        return null;
    }
    public boolean validateSelectedOption(ATMOptions option, BankAccount bankAccount, double amount){
        System.out.println("method called from invalid state");
        return false;
    }
    public void processSelectedOption(ATMOptions option, BankAccount bankAccount, double amount, ATMHall atmHall){
        System.out.println("method called from invalid state");
    }

    //fourth state methods
    public boolean dispense(ATMHall atmHall, double amount){
        System.out.println("method called from invalid state");
        return false;
    }
    public void generateReceipt(ATMHall atmHall){
        System.out.println("method called from invalid state");
    }
}
