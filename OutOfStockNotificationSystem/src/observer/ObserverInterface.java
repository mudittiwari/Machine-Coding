package observer;

import observable.ObservableInterface;

public interface ObserverInterface {
    public String getName();
    public void notificationReceiver(ObservableInterface observable);
}

