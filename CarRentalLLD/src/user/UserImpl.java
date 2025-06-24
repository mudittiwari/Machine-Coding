package user;

public class UserImpl implements User{
    private String name;
    private int age;

    public UserImpl(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }
}
