public class HillClimbing {

    public static Solution hillClimbing(Solution initialSolution) {
        Solution currentSolution = initialSolution;
        int currentFitness = currentSolution.calculateFitness();

        while (true) {
            Solution neighbor = currentSolution.generateNeighbor();
            int neighborFitness = neighbor.calculateFitness();

            // break if the neighbor solution is worse
            if (neighborFitness <= currentFitness) {
                break;
            }

            currentSolution = neighbor;
            currentFitness = neighborFitness;
        }

        return currentSolution;
    }
}