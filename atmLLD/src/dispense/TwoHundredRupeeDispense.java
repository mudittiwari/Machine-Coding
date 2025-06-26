package dispense;

import atmHall.ATMHall;

public class TwoHundredRupeeDispense extends DispenseAbstract{
    public TwoHundredRupeeDispense(DispenseAbstract nextDispenseAbstract){
        super(nextDispenseAbstract);
    }

    @Override
    public boolean dispense(int level, double amount, ATMHall atm) {
        if(level == 2){
            int totalNotesRequired = (int)(amount/200);
            int totalNotesLeft = atm.getTwoHundredRupeeNotes();
            if(totalNotesLeft < totalNotesRequired){
                return super.dispense(level - 1, amount, atm);
            }
            else{
                System.out.println(totalNotesRequired + " Notes of 200 are dispensed");
                atm.setTwoHundredRupeeNotes(totalNotesLeft - totalNotesRequired);
                return super.dispense(level - 1, (amount-(totalNotesRequired * 200)),atm);
            }
        }
        else{
            return super.dispense(level - 1, amount, atm);
        }
    }
}
