import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final int id;
    private final Position position;
    private final Map<Product, Integer> stock;

    public Warehouse(int id, Position position) {
        this.id = id;
        this.position = position;
        this.stock = new HashMap<>();
    }

    public Warehouse(int id, int row, int col) {
        this.id = id;
        this.position = new Position(row, col);
        this.stock = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        stock.put(product, stock.getOrDefault(product, 0) + quantity);
    }

    public boolean hasProduct(Product product, int quantity) {
        return stock.getOrDefault(product, 0) >= quantity;
    }

    public void removeProduct(Product product, int quantity) {
        if (hasProduct(product, quantity)) {
            stock.put(product, stock.get(product) - quantity);
        } else {
            throw new IllegalArgumentException("Not enough stock for product " + product.getId());
        }
    }

    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Warehouse" + id + ", location=" + position + ", stock=" + stock;
    }

    public Map<Product, Integer> getStock() {
        return this.stock;
    }
}
