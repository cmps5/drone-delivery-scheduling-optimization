import java.util.HashMap;
import java.util.Map;

public class Drone {

    private final Map<Product, Integer> inventory;
    private int id;
    private Position position;
    private int maxPayload;

    public Drone(int id, Position position, int maxPayload) {
        this.id = id;
        this.position = position;
        this.maxPayload = maxPayload;
        this.inventory = new HashMap<>();
    }

    public Drone(int id, int row, int col, int maxPayload) {
        this.id = id;
        this.position = new Position(row, col);
        this.maxPayload = maxPayload;
        this.inventory = new HashMap<>();
    }

    /*
     * Load products from a warehouse.
     */
    public void load(Product product, int quantity, Warehouse warehouse) {
        if (warehouse.hasProduct(product, quantity)) {
            int totalWeight = product.getWeight() * quantity;
            if (getCurrentLoad() + totalWeight <= maxPayload) {
                warehouse.removeProduct(product, quantity);
                inventory.put(product, inventory.getOrDefault(product, 0) + quantity);
                System.out.println("Drone " + id + " loaded " + quantity + " of product " + product.getId()
                        + " from warehouse " + warehouse.getId());
            } else {
                throw new IllegalArgumentException("Drone " + id + " cannot carry more than " + maxPayload + " units.");
            }
        } else {
            throw new IllegalArgumentException(
                    "Warehouse " + warehouse.getId() + " does not have enough stock of product " + product.getId());
        }
    }

    public int getCurrentLoad() {
        return inventory.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getWeight() * entry.getValue())
                .sum();
    }

    /*
     * Deliver products to an order.
     */
    public void deliver(Product product, int quantity, Order order) {
        if (inventory.getOrDefault(product, 0) >= quantity) {
            inventory.put(product, inventory.get(product) - quantity);
            order.addProduct(product, quantity);
            System.out.println("Drone " + id + " delivered " + quantity + " of product " + product.getId()
                    + " to order " + order.getId());
        } else {
            throw new IllegalArgumentException(
                    "Drone " + id + " does not have enough inventory of product " + product.getId());
        }
    }

    /*
     * Unload products from the drone to a warehouse.
     */
    public void unload(Product product, int quantity, Warehouse warehouse) {
        if (inventory.getOrDefault(product, 0) >= quantity) {
            inventory.put(product, inventory.get(product) - quantity);
            warehouse.addProduct(product, quantity);
            System.out.println("Drone " + id + " unloaded " + quantity + " of product " + product.getId()
                    + " at warehouse " + warehouse.getId());
        } else {
            throw new IllegalArgumentException(
                    "Drone " + id + " does not have enough inventory of product " + product.getId());
        }
    }

    public void wait(int turns) {
        System.out.println("Drone " + id + " is waiting for " + turns + " turns.");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getMaxPayload() {
        return maxPayload;
    }

    public void setMaxPayload(int maxPayload) {
        this.maxPayload = maxPayload;
    }

    public Map<Product, Integer> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return "Drone" + id + ", location=" + position + ", inventory=" + inventory;
    }

}
