import java.util.*;

public class Solution {
    private List<List<String>> droneCommands;
    private final Simulation simulation;
    private Random random;

    public Solution(List<List<String>> droneCommands, Simulation simulation) {
        this.droneCommands = droneCommands;
        this.simulation = simulation;
        this.random = new Random();
    }

    public List<List<String>> getDroneCommands() {
        return droneCommands;
    }

    public void setDroneCommands(List<List<String>> droneCommands) {
        this.droneCommands = droneCommands;
    }


    public Solution generateNeighbor() {

        List<List<String>> newDroneCommands = new ArrayList<>();
        for (List<String> commands : droneCommands) {
            newDroneCommands.add(new ArrayList<>(commands));
        }

        // @ TODO

        return new Solution(newDroneCommands, simulation);
    }

    public int calculateFitness() {
        int totalScore = 0;
        int maxTurns = simulation.getMaxTurns();

        List<Order> orders = simulation.getOrders();
        for (Order order : orders) {
            if(!order.isFulfilled())
                continue;
            totalScore += 100 * (maxTurns - order.getTurn())/maxTurns;
        }

        return totalScore;
    }
}