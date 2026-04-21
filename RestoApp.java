import java.util.ArrayList;
import java.util.List;

// Step A: The Component Interface
interface MenuComponent {
    double getPrice();
    void print();
}

// Step B: The Leaf (Individual Item)
class MenuItem implements MenuComponent {
    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price; // Returns the stored price [cite: 40]
    }

    @Override
    public void print() {
        // Displays as [Name]: P[Price] [cite: 41, 42]
        System.out.println(name + ": P" + String.format("%.2f", price));
    }
}

// Step C: The Composite (Category/Combo)
class MenuCategory implements MenuComponent {
    private String categoryName;
    private List<MenuComponent> menuComponents = new ArrayList<>(); // List of children [cite: 45]

    public MenuCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public void add(MenuComponent component) {
        menuComponents.add(component);
    }

    public void remove(MenuComponent component) {
        menuComponents.remove(component);
    }

    @Override
    public double getPrice() {
        double total = 0;
        // Logic: Iterate through all children and sum their prices [cite: 46]
        for (MenuComponent component : menuComponents) {
            total += component.getPrice();
        }
        return total;
    }

    @Override
    public void print() {
        // Logic: Print category name then call print() on all children [cite: 47]
        System.out.println("\n" + categoryName.toUpperCase());
        for (MenuComponent component : menuComponents) {
            component.print();
        }
    }
}

// Step D: Client Application
public class RestoApp {
    public static void main(String[] args) {
        // 1. Leaves: Create items [cite: 49]
        MenuItem burger = new MenuItem("Classic Burger", 250.00);
        MenuItem fries = new MenuItem("Large Fries", 85.00);
        MenuItem soda = new MenuItem("Root Beer", 60.00);

        // 2. Sub-Composite: Barkada Solo Meal [cite: 50]
        MenuCategory barkadaMeal = new MenuCategory("Barkada Solo Meal");
        barkadaMeal.add(burger);
        barkadaMeal.add(fries);
        barkadaMeal.add(soda);

        // 3. Leaf: Vanilla Sundae [cite: 51]
        MenuItem sundae = new MenuItem("Vanilla Sundae", 45.00);

        // 4. Top-Composite: Main Menu [cite: 52]
        MenuCategory mainMenu = new MenuCategory("Main Menu");
        mainMenu.add(barkadaMeal);
        mainMenu.add(sundae);

        // 5. Test: Call print() and getPrice() [cite: 53]
        mainMenu.print();
        System.out.println("===");
        System.out.println("Total Menu Value: P" + String.format("%.2f", mainMenu.getPrice()));
    }
}