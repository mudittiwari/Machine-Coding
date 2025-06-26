package atmState;

import atmHall.ATMHall;

import java.util.Scanner;

public class CardInsertedState extends ATMState{
    @Override
    public String enterPin(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your PIN: ");
        String pin = scanner.nextLine();
        return pin;
    }
    public boolean validatePin(String pin, ATMHall atmHall){
        if(atmHall.getCard().validatePin(pin)){
            atmHall.setAtmState(new SelectOptionState());
            return true;
        }
        return false;
    }
}
