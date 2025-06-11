package observable;

import observer.ObserverInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Observable implements ObservableInterface{
    private List<ObserverInterface> observerList;
    private int inStockQuantity;
    private String productName;

    public String getProductName() {
        return productName;
    }

    public int getInStockQuantity() {
        return inStockQuantity;
    }

    public Observable(int inStockQuantity, String productName){
        this.observerList = new ArrayList<>();
        this.inStockQuantity = inStockQuantity;
        this.productName = productName;
    }

    @Override
    public void notifyUsers() {
        observerList.forEach(observer -> {
            observer.notificationReceiver(this);
        });
    }

    @Override
    public boolean addUser(ObserverInterface observer) {
        return this.observerList.add(observer);
    }

    @Override
    public boolean removeUser(ObserverInterface observer) {
        int index = -1;
        for(int i = 0;i< observerList.size() ; i++){
            if(observerList.get(i).getName().equals(observer.getName())){
                index = i;
                break;
            }
        }
        if(index == -1)
            return false;
        observerList.remove(index);
        return true;
    }

    @Override
    public int getData() {
        return this.inStockQuantity;
    }

    @Override
    public boolean decreaseQuantity(int changeInQuantity) {
        if (this.inStockQuantity < changeInQuantity)
            return false;
        this.inStockQuantity -= changeInQuantity;
        if(this.inStockQuantity == 0)
            this.notifyUsers();
        return true;
    }

    @Override
    public boolean increaseQuantity(int changeInQuantity) {
        int previousQuantity = this.inStockQuantity;
        this.inStockQuantity += changeInQuantity;
        if(previousQuantity == 0 && this.inStockQuantity > 0)
            this.notifyUsers();
        return true;
    }
}
