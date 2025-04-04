# Drone Delivery Optimization - Hash Code 2016 Qualification Round

## Overview
This project is an attempt to solve the "Delivery" problem from Google's Hash Code 2016 qualification round. The challenge involves optimizing drone deliveries in a simulated environment to maximize the score by fulfilling customer orders as quickly as possible.

## How to Run (Developed on IntelliJ IDEA)
1. Clone the repository to your local machine.
2. Open IntelliJ IDEA.
3. Choose "Open" from the IntelliJ IDEA welcome screen and select the cloned project directory.
4. Once the project is open, navigate to the `Main` class.
5. Right-click on the `Main` class and select "Run Main.main()" from the context menu.

## Problem Description
Given a fleet of drones, a list of customer orders and availability of the individual products in warehouses, the goal is to minimize the time to fulfil customer orders by optimizing drone operations (loading, delivering, unloading, waiting).

### Key Components:
- **Map**: two-dimensional grid.
- **Products**: items represented by types, available quantities and weight.
- **Warehouses**: Locations with limited product stock.
- **Orders**: Customer requests specifying products and delivery locations.
- **Drones**: Fleet transporting products between warehouses and customers.

### Hard Constraints:
- **Drone Capacity**: Total weight ≤ drone’s max payload.
- **Warehouse Stock**: Items loaded ≤ available stock.
- **Order Fulfilment**: Items delivered ≤ customer order.
- **Time Limit**: All commands completed within simulation time.

## Solution Approach
The current implementation includes:
### 1. Core Components:
- **Drone**: Handles loading, delivering, and moving products.
- **Product**: Models products with IDs and weights.
- **Warehouse**: Stores products and manages inventory.
- **Order**: Tracks customer orders and fulfillment status.
- **Position**: Represents grid coordinates with distance calculations.

### 2. Simulation Engine:
- **Simulation**: Manages the overall simulation state and turn progression.
- Reads input files and initializes all components.
### 3. Optimization Algorithms:
- **HillClimbing**
- **Simulated Annealing**
### 4. Main Program:
- **Main**: Entry point that reads input and runs simulations.

## Next Steps
The code provides a solid foundation but has several areas for improvement:
### 1. Missing Features:
- Complete neighbor generation in *Solution.generateNeighbor()*, more precisely *Solution.generateRandomCommand(int droneId)* and *Solution.modifyCommand(String command)*.
- Accurate turn calculation based on drone movements.
- Consider drone locations.
### 2. Optimization Opportunities:
- Better initial solution generation.
- More sophisticated optimization strategies.
 
## References
This implementation is based on the official Hash Code 2016 qualification round problem statement (included as [hashcode2016_qualification_task.pdf](doc%2Fhashcode2016_qualification_task.pdf)).


