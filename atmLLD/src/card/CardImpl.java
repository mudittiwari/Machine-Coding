package card;

import bankAccount.BankAccount;

public class CardImpl implements Card{
    private String cardNumber;
    private BankAccount bankAccount;
    private String cardPin;

    public CardImpl(String cardNumber, BankAccount bankAccount, String cardPin){
        this.cardNumber = cardNumber;
        this.bankAccount = bankAccount;
        this.cardPin = cardPin;
    }

    @Override
    public boolean validatePin(String cardPin){
        return this.cardPin.equals(cardPin);
    }

    @Override
    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
