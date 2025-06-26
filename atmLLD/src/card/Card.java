package card;

import bankAccount.BankAccount;

public interface Card {
    public boolean validatePin(String cardPin);
    public String getCardNumber();
    public BankAccount getBankAccount();
}
