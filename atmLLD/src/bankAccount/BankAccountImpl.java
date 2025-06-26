package bankAccount;

import enums.BalanceUpdateType;

public class BankAccountImpl implements BankAccount{

    private String accountNumber;
    private double balance;

    public BankAccountImpl(String accountNumber){
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    @Override
    public boolean updateBalance(double amount, BalanceUpdateType type){
        if (type.equals(BalanceUpdateType.CREDIT)){
            this.balance += amount;
        }
        else{
            this.balance -= amount;
        }
        return true;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
