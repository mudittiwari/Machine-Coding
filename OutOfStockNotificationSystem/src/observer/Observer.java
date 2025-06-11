package observer;

import observable.ObservableInterface;

public class Observer implements ObserverInterface{

    public String getName() {
        return name;
    }

    private final String name;

    public Observer(String name){
        this.name = name;
    }

    @Override
    public void notificationReceiver(ObservableInterface observable) {
        int data = observable.getData();
        if(data == 0){
            System.out.println(observable.getProductName() + " is out of stock");
        }
        else{
            System.out.println(observable.getProductName() + " is in the stock!!");
        }
    }
}
