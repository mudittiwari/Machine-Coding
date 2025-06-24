import adapter.TypeCAdapter;
import adapter.TypeCAdapterImpl;
import charger.Charger;
import charger.TypeBChargerImpl;
import charger.TypeCChargerImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Charger typeCCharger = new TypeCChargerImpl();
        Charger typeBCharger = new TypeBChargerImpl();
        TypeCAdapter adapter = new TypeCAdapterImpl(typeCCharger);
        adapter.charge(typeBCharger);
    }
}