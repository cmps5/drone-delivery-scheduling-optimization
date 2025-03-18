import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Parse the input file
            Simulation simulation = readInput("input/ex.in");

            // Print the simulation details
            System.out.println(simulation);

            Solution initialSolution = simulation.generateInitialSolution();

            // Apply Hill Climbing
            Solution bestHillClimbingSolution = HillClimbing.hillClimbing(initialSolution);
            System.out.println("Best fitness (Hill Climbing): " + bestHillClimbingSolution.calculateFitness());

            // Apply Simulated Annealing
            Solution bestSASolution = SimulatedAnnealing.simulatedAnnealing(initialSolution, 1000, 0.99);
            System.out.println("Best fitness (Simulated Annealing): " + bestSASolution.calculateFitness());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Simulation readInput(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        // Read simulation parameters
        line = reader.readLine();
        String[] params = line.split(" ");
        int rows = Integer.parseInt(params[0]);
        int cols = Integer.parseInt(params[1]);
        int numDrones = Integer.parseInt(params[2]);
        int maxTurns = Integer.parseInt(params[3]);
        int maxPayload = Integer.parseInt(params[4]);

        // Read product types
        line = reader.readLine();
        int numProductTypes = Integer.parseInt(line);

        line = reader.readLine();
        String[] productWeights = line.split(" ");
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < numProductTypes; i++) {
            products.add(new Product(i, Integer.parseInt(productWeights[i])));
        }

        // Read warehouses
        line = reader.readLine();
        int numWarehouses = Integer.parseInt(line);

        List<Warehouse> warehouses = new ArrayList<>();
        for (int i = 0; i < numWarehouses; i++) {
            line = reader.readLine();
            String[] location = line.split(" ");
            int warehouseRow = Integer.parseInt(location[0]);
            int warehouseCol = Integer.parseInt(location[1]);

            line = reader.readLine();
            String[] stock = line.split(" ");
            Warehouse warehouse = new Warehouse(i, warehouseRow, warehouseCol);
            for (int j = 0; j < numProductTypes; j++) {
                int quantity = Integer.parseInt(stock[j]);
                if (quantity > 0) {
                    warehouse.addProduct(products.get(j), quantity);
                }
            }
            warehouses.add(warehouse);
        }

        // Read orders
        line = reader.readLine();
        int numOrders = Integer.parseInt(line);

        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < numOrders; i++) {
            line = reader.readLine();
            String[] location = line.split(" ");
            int orderRow = Integer.parseInt(location[0]);
            int orderCol = Integer.parseInt(location[1]);

            line = reader.readLine();
            int numItems = Integer.parseInt(line);

            line = reader.readLine();
            String[] items = line.split(" ");
            Order order = new Order(i, orderRow, orderCol);
            for (int j = 0; j < numItems; j++) {
                int productId = Integer.parseInt(items[j]);
                order.addProduct(products.get(productId), 1);
            }
            orders.add(order);
        }

        reader.close();

        // Create drones
        List<Drone> drones = new ArrayList<>();
        for (int i = 0; i < numDrones; i++) {
            drones.add(new Drone(i, warehouses.get(0).getPosition(), maxPayload));
        }

        return new Simulation(rows, cols, drones, warehouses, orders, maxTurns);

    }
}
