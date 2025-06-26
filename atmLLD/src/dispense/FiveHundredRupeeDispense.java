package dispense;

import atmHall.ATMHall;

public class FiveHundredRupeeDispense extends DispenseAbstract{
    public FiveHundredRupeeDispense(DispenseAbstract nextDispenseAbstract){
        super(nextDispenseAbstract);
    }

    @Override
    public boolean dispense(int level, double amount, ATMHall atm) {
        if(level == 3){
            int totalNotesRequired = (int)(amount/500);
            int totalNotesLeft = atm.getFiveHundredRupeeNotes();
            if(totalNotesLeft < totalNotesRequired){
                return super.dispense(level - 1, amount, atm);
            }
            else{
                System.out.println(totalNotesRequired + " Notes of 500 are dispensed");
                atm.setFiveHundredRupeeNotes(totalNotesLeft - totalNotesRequired);
                return super.dispense(level - 1, (amount-(totalNotesRequired * 500)),atm);
            }
        }
        else{
            return super.dispense(level - 1, amount, atm);
        }
    }
}
