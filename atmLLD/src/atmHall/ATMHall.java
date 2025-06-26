package atmHall;

import atmState.ATMState;
import card.Card;
import user.User;

public interface ATMHall {
    public Card getCard();
    public void setCard(Card card);
    public ATMState getAtmState();
    public void setAtmState(ATMState atmState);
    public User getUser();
    public void setUser(User user);
    public int getHundredRupeeNotes();

    public void setHundredRupeeNotes(int hundredRupeeNotes);

    public int getFiveHundredRupeeNotes();

    public void setFiveHundredRupeeNotes(int fiveHundredRupeeNotes);

    public int getTwoHundredRupeeNotes();

    public void setTwoHundredRupeeNotes(int twoHundredRupeeNotes);
}
