package user;

import bankAccount.BankAccount;

public interface User {
    public void setBankAccount(BankAccount bankAccount);
    public String getName();
    public String getEmail();
    public int getAge();
    public BankAccount getBankAccount();
}
