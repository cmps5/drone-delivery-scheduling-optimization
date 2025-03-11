import java.util.Map;

public class Order {
    private int id;
    private Position position;
    private Map<Product, Integer> items;
    private boolean isFulfilled;

    public Order(int id, Position position, Map<Product, Integer> items) {
        this.id = id;
        this.position = position;
        this.items = items;
        this.isFulfilled = false;
    }

    public void addProduct(Product product, int quantity) {
        items.put(product, quantity);
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

}
