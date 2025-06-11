import observable.Observable;
import observer.Observer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Observable> productMap = new HashMap<>();
        Map<String, Observer> userMap = new HashMap<>();

        while (true) {
            System.out.println("\n=== Stock Management Menu ===");
            System.out.println("1. Add Product (Observable)");
            System.out.println("2. Add User (Observer) to Product");
            System.out.println("3. Remove User from Product");
            System.out.println("4. Decrease Product Stock");
            System.out.println("5. Increase Product Stock");
            System.out.println("6. View Product Stock");
            System.out.println("7. List Observers per Product");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String pname = sc.nextLine();
                    System.out.print("Enter initial stock: ");
                    int stock = Integer.parseInt(sc.nextLine());
                    productMap.put(pname, new Observable(stock, pname));
                    System.out.println("Product '" + pname + "' added.");
                    break;

                case 2:
                    System.out.print("Enter user name: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter product to observe: ");
                    String observePname = sc.nextLine();
                    Observable targetProduct = productMap.get(observePname);
                    if (targetProduct != null) {
                        Observer observer = new Observer(uname);
                        if (targetProduct.addUser(observer)) {
                            userMap.put(uname, observer);
                            System.out.println(uname + " is now observing " + observePname);
                        } else {
                            System.out.println("Failed to add observer.");
                        }
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter user name to remove: ");
                    String removeUname = sc.nextLine();
                    Observer observerToRemove = userMap.get(removeUname);
                    if (observerToRemove != null) {
                        boolean removed = false;
                        for (Observable obs : productMap.values()) {
                            if (obs.removeUser(observerToRemove)) {
                                removed = true;
                                break;
                            }
                        }
                        if (removed) {
                            userMap.remove(removeUname);
                            System.out.println(removeUname + " removed from observing.");
                        } else {
                            System.out.println("User was not observing any product.");
                        }
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter product name: ");
                    String decreasePname = sc.nextLine();
                    Observable productToUpdateDecrease = productMap.get(decreasePname);
                    if (productToUpdateDecrease != null) {
                        System.out.print("Enter quantity to reduce: ");
                        int reduceQty = Integer.parseInt(sc.nextLine());
                        if (productToUpdateDecrease.decreaseQuantity(reduceQty)) {
                            System.out.println("Stock updated. Remaining: " + productToUpdateDecrease.getInStockQuantity());
                        } else {
                            System.out.println("Not enough stock to reduce.");
                        }
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter product name: ");
                    String increasePname = sc.nextLine();
                    Observable productToUpdate = productMap.get(increasePname);
                    if (productToUpdate != null) {
                        System.out.print("Enter quantity to increase: ");
                        int increaseQty = Integer.parseInt(sc.nextLine());
                        if (productToUpdate.increaseQuantity(increaseQty)) {
                            System.out.println("Stock updated. Remaining: " + productToUpdate.getInStockQuantity());
                        }
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 6:
                    System.out.println("--- Product Stock ---");
                    productMap.forEach((name, obs) -> {
                        System.out.println(name + ": " + obs.getInStockQuantity());
                    });
                    break;

                case 7:
                    System.out.println("--- Observers per Product ---");
                    for (Map.Entry<String, Observable> entry : productMap.entrySet()) {
                        System.out.print(entry.getKey() + ": ");
                        List<String> observerNames = new ArrayList<>();
                        for (Observer obs : userMap.values()) {
                            if (entry.getValue().removeUser(obs)) {
                                observerNames.add(obs.getName());
                                entry.getValue().addUser(obs);  // add back after check
                            }
                        }
                        System.out.println(observerNames.isEmpty() ? "No observers" : observerNames);
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
