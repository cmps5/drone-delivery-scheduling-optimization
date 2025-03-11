import java.util.List;

public class Simulation {

    private List<Drone> drones;
    private List<Warehouse> warehouses;
    private List<Order> orders;
    private int maxTurns;
    private int currentTurn;

    public Simulation(List<Drone> drones, List<Warehouse> warehouses, List<Order> orders, int maxTurns) {
        this.drones = drones;
        this.warehouses = warehouses;
        this.orders = orders;
        this.maxTurns = maxTurns;
        this.currentTurn = 0;
    }

    public void run() {

    }

    public void nextTurn() {
        currentTurn++;
    }

    public boolean isFinished() {
        return currentTurn >= maxTurns;
    }

    public List<Drone> getDrones() {
        return drones;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getMaxTurns() {
        return maxTurns;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}
