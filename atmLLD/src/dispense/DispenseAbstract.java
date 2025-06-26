package dispense;

import atmHall.ATMHall;

public class DispenseAbstract {
    private static int HUNDRED = 1;
    private static int TWO_HUNDRED = 2;
    private static int FIVE_HUNDRED = 3;

    private DispenseAbstract nextDispenseAbstract;

    public DispenseAbstract(DispenseAbstract nextDispenseAbstract){
        this.nextDispenseAbstract = nextDispenseAbstract;
    }

    public boolean dispense(int level, double amount, ATMHall atm){
        if(amount == 0)
            return true;
        else if(this.nextDispenseAbstract == null)
            return false;
        return nextDispenseAbstract.dispense(level, amount, atm);
    }
}
