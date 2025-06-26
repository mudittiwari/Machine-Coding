package atmState;

import atmHall.ATMHall;
import card.Card;

import java.util.Scanner;

public class IdleState extends ATMState{

    @Override
    public void insertCard(Card card, ATMHall atmHall){
        atmHall.setCard(card);
        atmHall.setAtmState(new CardInsertedState());
    }
}
