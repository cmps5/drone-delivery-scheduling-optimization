import java.util.List;

public class Simulation {

    private final int rows;
    private final int cols;
    private final List<Drone> drones;
    private final List<Warehouse> warehouses;
    private final List<Order> orders;
    private final int maxTurns;
    private int currentTurn;

    public Simulation(int rows, int cols, List<Drone> drones, List<Warehouse> warehouses, List<Order> orders, int maxTurns) {
        this.rows = rows;
        this.cols = cols;
        this.drones = drones;
        this.warehouses = warehouses;
        this.orders = orders;
        this.maxTurns = maxTurns;
        this.currentTurn = 0;
    }

    public void run() {
        while (!isFinished()) {
            System.out.println("Turn " + currentTurn);
            // @TODO
            nextTurn();
        }

    }

    public void nextTurn() {
        currentTurn++;
    }

    public boolean isFinished() {
        return currentTurn >= maxTurns;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
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

    @Override
    public String toString() {
        return "==========================" +
                "\nGrid: " + rows + "x" + cols + ", Turns: " + maxTurns +
                "\n==========================" +
                "\nDrones\n" + dronesToString(drones) +
                "\n==========================" +
                "\nWarehouses\n" + warehousesToString(warehouses) +
                "\n==========================" +
                "\nOrders\n" + ordersToString(orders) +
                "\n==========================";
    }

    private String dronesToString(List<Drone> drones) {
        StringBuilder dronesString = new StringBuilder();
        for (Drone drone : drones) {
            dronesString.append(drone.toString()).append("\n");
        }
        return dronesString.toString();
    }
    private String warehousesToString(List<Warehouse> warehouses) {
        StringBuilder warehousesString = new StringBuilder();
        for (Warehouse warehouse : warehouses) {
            warehousesString.append(warehouse.toString()).append("\n");
        }
        return warehousesString.toString();
    }

    private String ordersToString(List<Order> orders) {
        StringBuilder ordersString = new StringBuilder();
        for (Order order : orders) {
            ordersString.append(order.toString()).append("\n");
        }
        return ordersString.toString();
    }

}
