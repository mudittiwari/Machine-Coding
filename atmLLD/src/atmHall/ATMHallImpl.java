package atmHall;

import atmState.ATMState;
import atmState.IdleState;
import card.Card;
import user.User;

public class ATMHallImpl implements ATMHall{
    private ATMState atmState;
    private User user;
    private int hundredRupeeNotes;
    private int twoHundredRupeeNotes;
    private int fiveHundredRupeeNotes;
    private int totalAmount;

    private Card card;
    public ATMHallImpl(int hundredRupeeNotes, int twoHundredRupeeNotes, int fiveHundredRupeeNotes){
        this.hundredRupeeNotes = hundredRupeeNotes;
        this.twoHundredRupeeNotes = twoHundredRupeeNotes;
        this.fiveHundredRupeeNotes = fiveHundredRupeeNotes;
        this.totalAmount = (100*hundredRupeeNotes) + (200*twoHundredRupeeNotes) + (500*fiveHundredRupeeNotes);
        this.atmState = new IdleState();
    }

    @Override
    public Card getCard() {
        return card;
    }

    @Override
    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public ATMState getAtmState() {
        return atmState;
    }

    @Override
    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    public int getHundredRupeeNotes() {
        return hundredRupeeNotes;
    }

    public void setHundredRupeeNotes(int hundredRupeeNotes) {
        this.hundredRupeeNotes = hundredRupeeNotes;
    }

    public int getFiveHundredRupeeNotes() {
        return fiveHundredRupeeNotes;
    }

    public void setFiveHundredRupeeNotes(int fiveHundredRupeeNotes) {
        this.fiveHundredRupeeNotes = fiveHundredRupeeNotes;
    }

    public int getTwoHundredRupeeNotes() {
        return twoHundredRupeeNotes;
    }

    public void setTwoHundredRupeeNotes(int twoHundredRupeeNotes) {
        this.twoHundredRupeeNotes = twoHundredRupeeNotes;
    }
}
