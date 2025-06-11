package observable;

import observer.ObserverInterface;


public interface ObservableInterface {
    public int getInStockQuantity();
    public String getProductName();
    public void notifyUsers();
    public boolean addUser(ObserverInterface observer);
    public boolean removeUser(ObserverInterface observer);
    public int getData();
    public boolean decreaseQuantity(int changeInQuantity);
    public boolean increaseQuantity(int changeInQuantity);


}
