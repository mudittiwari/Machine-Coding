import enums.VehicleType;
import location.Location;
import location.LocationImpl;
import reservation.Reservation;
import store.Store;
import store.StoreImpl;
import user.User;
import user.UserImpl;
import vehicle.Vehicle;
import vehicle.VehicleImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalServiceDriver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Store> stores = new ArrayList<>();
        List<User> users = new ArrayList<>();

        // ------------------ Create Dummy Stores ------------------
        Location storeOneLocation = new LocationImpl("112", "112", "Alwar", "Rajasthan");
        Store storeOne = new StoreImpl(storeOneLocation);
        stores.add(storeOne);

        Location storeTwoLocation = new LocationImpl("120", "120", "Jaipur", "Rajasthan");
        Store storeTwo = new StoreImpl(storeTwoLocation);
        stores.add(storeTwo);

        // ------------------ Create Dummy Users ------------------
        users.add(new UserImpl("Mudit Tiwari", 25));
        users.add(new UserImpl("Nimit Sharma", 25));
        users.add(new UserImpl("Anika Mehra", 30));
        users.add(new UserImpl("Rohit Singh", 28));

        // ------------------ Create Dummy Vehicles ------------------
        storeOne.addVehicle(new VehicleImpl(VehicleType.TWO_WHEELER, "112233", "Pulsur"));
        storeOne.addVehicle(new VehicleImpl(VehicleType.TWO_WHEELER, "111111", "Activa"));
        storeOne.addVehicle(new VehicleImpl(VehicleType.FOUR_WHEELER, "121212", "Maruti Suzuki"));
        storeOne.addVehicle(new VehicleImpl(VehicleType.FOUR_WHEELER, "223322", "Ford Figo"));

        storeTwo.addVehicle(new VehicleImpl(VehicleType.TWO_WHEELER, "212121", "Duke"));
        storeTwo.addVehicle(new VehicleImpl(VehicleType.TWO_WHEELER, "234321", "KTM RC"));
        storeTwo.addVehicle(new VehicleImpl(VehicleType.FOUR_WHEELER, "555555", "Honda City"));
        storeTwo.addVehicle(new VehicleImpl(VehicleType.FOUR_WHEELER, "555511", "WagonR"));

        // ------------------ Menu Loop ------------------
        while (true) {
            System.out.println("\n===== Car Rental Service =====");
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Reserve a Vehicle");
            System.out.println("3. Add New User");
            System.out.println("4. Add New Vehicle to Store");
            System.out.println("5. Release Reserved Vehicle");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("\n--- Available Vehicles ---");
                    int count = 1;
                    for (Store store : stores) {
                        List<Vehicle> vehicles = store.getVehicles();
                        for (Vehicle v : vehicles) {
                            System.out.printf("%d. %s (%s) - %s [%s] [%s]\n", count++, v.getVehicleName(), v.getVehicleType(), v.getVehicleNumber(), store.getLocation().getCity(), v.isReserved());
                        }
                    }
                }

                case 2 -> {
                    System.out.println("\n--- Registered Users ---");
                    for (User u : users) {
                        System.out.println("- " + u.getName());
                    }

                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine().trim();

                    User matchedUser = null;
                    for (User user : users) {
                        if (user.getName().equalsIgnoreCase(name)) {
                            matchedUser = user;
                            break;
                        }
                    }

                    if (matchedUser == null) {
                        System.out.println("❌ User not found.");
                        break;
                    }

                    List<Vehicle> allVehicles = new ArrayList<>();
                    List<Store> vehicleToStore = new ArrayList<>();

                    int vehicleIndex = 1;
                    System.out.println("\n--- Select a Vehicle to Reserve ---");
                    for (Store store : stores) {
                        for (Vehicle v : store.getVehicles()) {
                            System.out.printf("%d. %s (%s) - %s [%s] [%s]\n", vehicleIndex++, v.getVehicleName(), v.getVehicleType(), v.getVehicleNumber(), store.getLocation().getCity(), v.isReserved());
                            allVehicles.add(v);
                            vehicleToStore.add(store);
                        }
                    }

                    System.out.print("Enter vehicle number to reserve (1-" + allVehicles.size() + "): ");
                    int vChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (vChoice < 1 || vChoice > allVehicles.size()) {
                        System.out.println("❌ Invalid vehicle choice.");
                        break;
                    }

                    Vehicle selected = allVehicles.get(vChoice - 1);
                    Store associatedStore = vehicleToStore.get(vChoice - 1);

                    try {
                        boolean result = associatedStore.reserveVehicle(matchedUser, selected);
                        if(result)
                            System.out.println("✅ Reserved " + selected.getVehicleName() + " for " + matchedUser.getName());
                        else
                            System.out.println("Vehicle is already reserved");
                    } catch (Exception e) {
                        System.out.println("❌ Reservation failed: " + e.getMessage());
                    }
                }

                case 3 -> {
                    System.out.print("Enter new user name: ");
                    String newUserName = scanner.nextLine().trim();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    User newUser = new UserImpl(newUserName, age);
                    users.add(newUser);
                    System.out.println("✅ User created: " + newUser.getName());
                }

                case 4 -> {
                    System.out.println("\n--- Select Store to Add Vehicle ---");
                    for (int i = 0; i < stores.size(); i++) {
                        System.out.println((i + 1) + ". " + stores.get(i).getLocation().getCity());
                    }
                    System.out.print("Enter store number: ");
                    int storeChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (storeChoice < 1 || storeChoice > stores.size()) {
                        System.out.println("❌ Invalid store.");
                        break;
                    }

                    Store selectedStore = stores.get(storeChoice - 1);

                    System.out.print("Enter vehicle name: ");
                    String vehicleName = scanner.nextLine().trim();
                    System.out.print("Enter vehicle number: ");
                    String vehicleNumber = scanner.nextLine().trim();

                    System.out.println("Select vehicle type: 1. TWO_WHEELER  2. FOUR_WHEELER");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine();

                    VehicleType vehicleType = (typeChoice == 1) ? VehicleType.TWO_WHEELER : VehicleType.FOUR_WHEELER;
                    Vehicle newVehicle = new VehicleImpl(vehicleType, vehicleNumber, vehicleName);

                    selectedStore.addVehicle(newVehicle);
                    System.out.println("✅ Vehicle added to store in " + selectedStore.getLocation().getCity());
                }

                case 5 -> {
                    System.out.print("Enter your name to release a vehicle: ");
                    String releaseUserName = scanner.nextLine().trim();

                    User matchedUser = null;
                    for (User user : users) {
                        if (user.getName().equalsIgnoreCase(releaseUserName)) {
                            matchedUser = user;
                            break;
                        }
                    }

                    if (matchedUser == null) {
                        System.out.println("❌ User not found.");
                        break;
                    }

                    // Collect all reservations for this user
                    List<Reservation> userReservations = new ArrayList<>();
                    List<Store> reservationStores = new ArrayList<>();

                    for (Store store : stores) {
                        StoreImpl storeImpl = (StoreImpl) store;

                        // Get reservations from both managers
                        List<Reservation> twoWheelerReservations = storeImpl.getTwoWheelerReservationManager().getReservations();
                        List<Reservation> fourWheelerReservations = storeImpl.getFourWheelerReservationManager().getReservations();

                        // Combine and filter
                        List<Reservation> allReservations = new ArrayList<>();
                        allReservations.addAll(twoWheelerReservations);
                        allReservations.addAll(fourWheelerReservations);

                        for (Reservation reservation : allReservations) {
                            if (reservation.getUser().getName().equalsIgnoreCase(releaseUserName)) {
                                userReservations.add(reservation);
                                reservationStores.add(store);
                            }
                        }
                    }


                    if (userReservations.isEmpty()) {
                        System.out.println("❌ No reservations found for user.");
                        break;
                    }

                    // Show reservations
                    System.out.println("\n--- Your Reservations ---");
                    for (int i = 0; i < userReservations.size(); i++) {
                        Reservation res = userReservations.get(i);
                        System.out.printf("%d. %s (%s) - %s at %s\n",
                                i + 1,
                                res.getVehicle().getVehicleName(),
                                res.getVehicle().getVehicleType(),
                                res.getVehicle().getVehicleNumber(),
                                reservationStores.get(i).getLocation().getCity()
                        );
                    }

                    System.out.print("Select reservation to release (1-" + userReservations.size() + "): ");
                    int rChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (rChoice < 1 || rChoice > userReservations.size()) {
                        System.out.println("❌ Invalid choice.");
                        break;
                    }

                    Reservation toRelease = userReservations.get(rChoice - 1);
                    Store storeToReleaseFrom = reservationStores.get(rChoice - 1);

                    storeToReleaseFrom.releaseVehicle(toRelease);
                    System.out.println("✅ Vehicle released and bill generated successfully.");
                }

                case 6 -> {
                    System.out.println("Exiting system. Thank you!");
                    scanner.close();
                    return;
                }

                default -> System.out.println("❌ Invalid input. Try again.");
            }
        }
    }
}
