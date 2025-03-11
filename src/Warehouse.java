import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private int id;
    private Position position;
    private Map<Product, Integer> stock;

    public Warehouse(int id, Position position) {
        this.id = id;
        this.position = position;
        this.stock = new HashMap<Product, Integer>();
    }

    public void addProduct(Product product, int quantity) {
        stock.put(product, quantity);
    }

    public boolean hasProduct(Product product, int quantity) {
        return stock.getOrDefault(product, 0) >= quantity;
    }

    public void removeProduct(Product product, int quantity) {
        stock.put(product, stock.get(product) - quantity);
    }

    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

}
