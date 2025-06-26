package user;

import bankAccount.BankAccount;

public class UserImpl implements User, Comparable<User>{
    private String name;
    private String email;
    private int age;
    private BankAccount bankAccount;

    public UserImpl(String name, String email, int age){
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    @Override
    public int compareTo(User u) {
        if(u.getName().equals(this.getName()) && u.getEmail().equals(this.getEmail()))
            return 1;
        return 0;
    }
}
