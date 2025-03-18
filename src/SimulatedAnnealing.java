import java.util.Random;

public class SimulatedAnnealing {
    public static Solution simulatedAnnealing(Solution initialSolution, double initialTemperature, double coolingRate) {
        Solution currentSolution = initialSolution;
        int currentFitness = currentSolution.calculateFitness();
        double temperature = initialTemperature;
        Random random = new Random();

        while (temperature > 1) {
            Solution neighbor = currentSolution.generateNeighbor();
            int neighborFitness = neighbor.calculateFitness();


            if (neighborFitness > currentFitness) {
                currentSolution = neighbor;
                currentFitness = neighborFitness;
            } else {
                double acceptanceProbability = Math.exp((currentFitness - neighborFitness) / temperature);
                if (random.nextDouble() < acceptanceProbability) {
                    currentSolution = neighbor;
                    currentFitness = neighborFitness;
                }
            }

            temperature *= coolingRate;
        }

        return currentSolution;
    }
}