import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        this.currentTurn = 1;
    }

    public Solution generateInitialSolution() {
        List<List<String>> initialCommands = new ArrayList<>();
        String solution = "";
        for (int i = 0; i < drones.size(); i++) {
            initialCommands.add(new ArrayList<>());
        }

        //System.out.println(orders);

        //boolean[] ordFul = new boolean[orders.size()];

        while (!isFinished()) {
            //System.out.println("Turn " + currentTurn);
            boolean allCompleted = true;
            boolean[] beingUsed = new boolean[drones.size()];

            for (int droneId = 0; droneId < drones.size(); droneId++) {
                Drone drone = drones.get(droneId);

                //System.out.println(drone);
                if (!drone.getInventory().isEmpty()) {
                    continue;
                }

                for (int i = 0; i < orders.size(); i++) {
                    Order order = orders.get(i);
                    //System.out.println("Drone" + droneId + ", " + order);
                    if (beingUsed[droneId]) break;
                    //System.out.println(order);
                    if (order.isFulfilled()) {
                        //System.out.println("Out with " + order);
                        continue;
                    }
                    //System.out.println("Checkup Drone" + droneId + ", order" + order);

                    allCompleted = false; // there is at least one pending order

                    Map<Product, Integer> pendingItems = order.getPendingItems();
                    if (pendingItems.isEmpty()) {
                        continue;
                    }

                    // process pending orders
                    for (Map.Entry<Product, Integer> entry : pendingItems.entrySet()) {
                        Product product = entry.getKey();
                        int needed = entry.getValue();

                        // find warehouse with stock
                        for (Warehouse warehouse : warehouses) {
                            if (!warehouse.hasProduct(product, 1)) {
                                continue;
                            }

                            // calculate possible qty
                            int available = warehouse.getStock().get(product);
                            int possible = Math.min(needed, Math.min(available, (drone.getMaxPayload() - drone.getCurrentLoad()) / product.getWeight()));

                            //System.out.println(drone.getId() + " Before loading " + drone.getInventory());

                            // load
                            drone.load(product, possible, warehouse);
                            String loadCmd = String.format("%d L %d %d %d", droneId, warehouse.getId(), product.getId(), possible);
                            initialCommands.get(droneId).add(loadCmd);
                            solution += loadCmd + "\n";

                            //System.out.println(drone.getId() + " After loading " + drone.getInventory());

                            // deliver
                            drone.deliver(product, possible, order, currentTurn);
                            String deliverCmd = String.format("%d D %d %d %d", droneId, order.getId(), product.getId(), possible);
                            initialCommands.get(droneId).add(deliverCmd);
                            drone.getInventory().remove(product);
                            solution += deliverCmd + "\n";

                            //System.out.println(drone.getId() + " After delivery " + drone.getInventory());

                            //drone.setPosition(order.getPosition());

                            beingUsed[droneId] = true;
                            break; // one item per turn
                        }

                        if (beingUsed[droneId]) {
                            break; // one order per turn per drone
                        }
                    }
                   /* if (order.isFulfilled()) {
                        System.out.println("Order" + order.getId() + " completed");
                        //ordFul[i] = true;
                    }*/
                }
            }

            // to avoid
            if (allCompleted) {
                /*for(int i= 0; i<orders.size(); i++)
                    System.out.println(ordFul[i]);*/
                // System.out.println("All Done");
                break;
            }

            nextTurn();
        }

       /* int ful=0, noFul=0, lastFul=0, i=0;
        for (Order order : orders){
            if (order.isFulfilled()) {
                ful++;
                lastFul = i;

            } else{
                noFul ++;
            }
            i++;
        }
        System.out.println("Total Orders " + orders.size()+ ", ful " + ful
                + " (last ful " + lastFul + "), noFul " + noFul);*/

        // System.out.println(solution);
        return new Solution(initialCommands, this);
    }

    public void nextTurn() {
        currentTurn++;
    }

    public boolean isFinished() {
        return currentTurn > maxTurns;
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
        return "==========================" + "\nGrid: " + rows + "x" + cols + ", Turns: " + maxTurns + "\n==========================" + "\nDrones\n" + dronesToString(drones) + "\n==========================" + "\nWarehouses\n" + warehousesToString(warehouses) + "\n==========================" + "\nOrders\n" + ordersToString(orders) + "\n==========================";
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
