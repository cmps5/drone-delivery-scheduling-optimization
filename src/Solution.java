import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {
    private final Simulation simulation;
    private List<List<String>> droneCommands;
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

        Random random = new Random();
        int modificationType = random.nextInt(3); // 0-add command; 1-remove command; 2-modify existing command

        // select random drone to update
        int droneId = random.nextInt(newDroneCommands.size());
        List<String> commands = newDroneCommands.get(droneId);

        if (commands.isEmpty() || modificationType == 0) {
            // Add random command
            String newCommand = generateRandomCommand(droneId);
            commands.add(newCommand);
        } else if (modificationType == 1) {
            // Remove random command
            int cmdIndex = random.nextInt(commands.size());
            commands.remove(cmdIndex);
        } else {
            // modify random existing command
            int cmdIndex = random.nextInt(commands.size());
            String modifiedCmd = modifyCommand(commands.get(cmdIndex));
            commands.set(cmdIndex, modifiedCmd);
        }

        return new Solution(newDroneCommands, simulation);
    }

    private String generateRandomCommand(int droneId) {
        String command;
        Random random = new Random();
        int commandType = random.nextInt(3); // L, D, W

        switch (commandType) {
            case 0: { // Load
                // @TODO
                command = "";
            }

            case 1: { // Deliver
                // @TODO
                command = "";
            }
            default: { // Wait
                command = ""; // Does nothing, since I'm not using it in the final representation
            }
        }
        return command;
    }

    private String modifyCommand(String command) {
        // @TODO
        return "";
    }

    public int calculateScore() {
        int totalScore = 0;
        int maxTurns = simulation.getMaxTurns();

        List<Order> orders = simulation.getOrders();
        for (Order order : orders) {
            if (!order.isFulfilled())
                continue;
            totalScore += 100 * (maxTurns - order.getTurn()) / maxTurns;
        }

        return totalScore;
    }
}