package adapter;

import charger.Charger;

public class TypeCAdapterImpl implements TypeCAdapter{

    Charger charger;

    public TypeCAdapterImpl(Charger typeCCharger){
        this.charger = typeCCharger;
    }
    @Override
    public void charge(Charger typeBCharger) {
        System.out.println("USING TYPE C ADAPTER FOR CHARGING");
        String info = typeBCharger.getInfo();
        charger.printInfo(info);
        charger.charge();
    }
}
