package charger;

public class TypeCChargerImpl implements Charger {

    @Override
    public void charge() {
        System.out.println("charging using TypeC charger");
    }

    @Override
    public String getInfo() {
        return new String("this is type c charger");
    }

    @Override
    public void printInfo(String info) {
        System.out.println(info);
    }
}
