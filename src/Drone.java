import java.util.HashMap;
import java.util.Map;

public class Drone {

    private int id;
    private Position position;
    private int maxPayload;
    private final Map<Product, Integer> inventory;

    public Drone(int id, Position position, int maxPayload) {
        this.id = id;
        this.position = position;
        this.maxPayload = maxPayload;
        this.inventory = new HashMap<Product, Integer>();
    }

    public void load(Product product, int quantity, Warehouse warehouse) {

    }

    public void deliver(Product product, int quantity, Order order) {

    }

    public void unload(Product product, int quantity, Warehouse warehouse) {

    }

    public void wait(int turns) {

    }

    public void move(Position position) {

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

}
