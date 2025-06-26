package atmState;

import atmHall.ATMHall;
import dispense.DispenseAbstract;
import dispense.FiveHundredRupeeDispense;
import dispense.HundredRupeeDispense;
import dispense.TwoHundredRupeeDispense;
import user.User;

public class DispenseState extends ATMState{

    @Override
    public boolean dispense(ATMHall atmHall, double amount){
        //here we have to use the chain of responsibility design pattern
        DispenseAbstract dispense = new FiveHundredRupeeDispense(new TwoHundredRupeeDispense(new HundredRupeeDispense(null)));
        if(dispense.dispense(3, amount, atmHall)) {
            System.out.println("money dispensed successfully");
            return true;
        }
        else{
            System.out.println("money dispense is not possible");
            return false;
        }
    }

    @Override
    public void generateReceipt(ATMHall atmHall){
        System.out.println("receipt generated successfully");
        atmHall.setAtmState(new IdleState());
    }
}
