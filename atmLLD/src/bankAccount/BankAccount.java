package bankAccount;

import enums.BalanceUpdateType;

public interface BankAccount {
    public boolean updateBalance(double amount, BalanceUpdateType type);
    public String getAccountNumber();
    public double getBalance();
}
