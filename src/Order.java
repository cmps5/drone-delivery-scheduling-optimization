import java.util.HashMap;
import java.util.Map;

public class Order {
    private final int id;
    private final Position position;
    private final Map<Product, Integer> items;
    private boolean isFulfilled;

    public Order(int id, Position position) {
        this.id = id;
        this.position = position;
        this.items = new HashMap<>();
        this.isFulfilled = false;
    }

    public Order(int id, int row, int col) {
        this.id = id;
        this.position = new Position(row, col);
        this.items = new HashMap<>();
        this.isFulfilled = false;
    }

    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void markAsFulfilled() {
        isFulfilled = true;
    }

    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isFulfilled() {
        return isFulfilled;
    }

    @Override
    public String toString() {
        return "Order" + id + ", location=" + position + ", items=" + items;
    }

}
