package charger;

public class TypeBChargerImpl implements Charger{

    @Override
    public void charge() {
        System.out.println("charging using TypeB charger");
    }

    @Override
    public String getInfo() {
        return new String("this is type B charger");
    }

    @Override
    public void printInfo(String info) {
        System.out.println(info);
    }
}
