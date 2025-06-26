package dispense;

import atmHall.ATMHall;

public class HundredRupeeDispense extends DispenseAbstract{
    public HundredRupeeDispense(DispenseAbstract nextDispenseAbstract){
        super(nextDispenseAbstract);
    }

    @Override
    public boolean dispense(int level, double amount, ATMHall atm){
        if(level == 1){
            int totalNotesRequired = (int)(amount/100);
            int totalNotesLeft = atm.getHundredRupeeNotes();
            if(totalNotesLeft < totalNotesRequired){
                return super.dispense(level - 1, amount, atm);
            }
            else{
                System.out.println(totalNotesRequired + " Notes of 100 are dispensed");
                atm.setHundredRupeeNotes(totalNotesLeft - totalNotesRequired);
                return super.dispense(level - 1, (amount-(totalNotesRequired * 100)),atm);
            }
        }
        else{
            return super.dispense(level - 1, amount, atm);
        }
    }
}
