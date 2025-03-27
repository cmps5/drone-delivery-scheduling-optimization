import java.util.HashMap;
import java.util.Map;

public class Order implements Cloneable {
    private final int id;
    private final Position position;
    private final Map<Product, Integer> items;
    private final Map<Product, Integer> deliveredItems;
    private boolean isFulfilled;
    private int turn;

    public Order(int id, Position position) {
        this.id = id;
        this.position = position;
        this.items = new HashMap<>();
        this.deliveredItems = new HashMap<>();
        this.isFulfilled = false;
        this.turn = Integer.MAX_VALUE;
    }

    public Order(int id, int row, int col) {
        this.id = id;
        this.position = new Position(row, col);
        this.items = new HashMap<>();
        this.deliveredItems = new HashMap<>();
        this.isFulfilled = false;
    }

    public void addProduct(Product product, int quantity) {
        items.put(product, items.getOrDefault(product, 0) + quantity);
        deliveredItems.putIfAbsent(product, 0);
    }

    public void addDelivery(Product product, int quantity, int turn) {
        int delivered = deliveredItems.getOrDefault(product, 0);
        deliveredItems.put(product, delivered + quantity);

        // check fulfilled
        if (items.entrySet().stream()
                .allMatch(entry -> deliveredItems.getOrDefault(entry.getKey(), 0) >= entry.getValue())) {
            isFulfilled = true;
            this.turn = turn;
        }
    }

    public Map<Product, Integer> getPendingItems() {
        Map<Product, Integer> pending = new HashMap<>();
        items.forEach((product, requested) -> {
            int delivered = deliveredItems.getOrDefault(product, 0);
            if (delivered < requested) {
                pending.put(product, requested - delivered);
            }
        });
        return pending;
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


    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    @Override
    protected Order clone() throws CloneNotSupportedException {
        return (Order) super.clone();
    }

    @Override
    public String toString() {
        return "Order" + id + ", location=" + position + ", items=" + items + "isFulfilled=" + isFulfilled;
    }

}
