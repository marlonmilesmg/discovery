package za.co.discovery.assignment.controller;


import za.co.discovery.assignment.models.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.discovery.assignment.services.InterstellarNodeService;

import java.util.*;

@Service
public class InterstellarNodeServiceImpl implements InterstellarNodeService {

    private final InterstellarNodeRepository nodeRepository;

    @Autowired
    public InterstellarNodeServiceImpl(InterstellarNodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    // Implemented Dijkstra's algorithm to find the shortest path from Earth to the specified destination planet.
    @Override
    public List<Planet> findShortestPathToDestination(String destination) {
        Map<String, Double> distanceMap = new HashMap<>();
        Map<String, Planet> previousNodeMap = new HashMap<>();
        PriorityQueue<Planet> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distanceMap::get));

        // Initialize distances to infinity, except Earth with distance 0
        for (Planet node : getAllNodesFromDatabase()) {
            distanceMap.put(node.getName(), node.getName().equals("Earth") ? 0.0 : Double.POSITIVE_INFINITY);
            previousNodeMap.put(node.getName(), null);
        }

        // Add Earth node to the priority queue
        priorityQueue.add(getEarthNode());

        while (!priorityQueue.isEmpty()) {
            Planet currentNode = priorityQueue.poll();

            // If destination reached, stop the algorithm
            if (currentNode.getName().equals(destination)) {
                break;
            }

            // Iterate through neighbors and update distances
            for (Planet neighbor : currentNode.getNeighbors()) {
                double distanceThroughCurrent = distanceMap.get(currentNode.getName()) + currentNode.getDistance();
                if (distanceThroughCurrent < distanceMap.get(neighbor.getName())) {
                    distanceMap.put(neighbor.getName(), distanceThroughCurrent);
                    previousNodeMap.put(neighbor.getName(), currentNode);
                    priorityQueue.add(neighbor);
                }
            }
        }

        // Reconstruct the shortest path
        List<Planet> shortestPath = new ArrayList<>();
        Planet destinationNode = getNodeByName(destination);
        while (destinationNode != null) {
            shortestPath.add(0, destinationNode);
            destinationNode = previousNodeMap.get(destinationNode.getName());
        }

        return shortestPath;
    }

    // Helper methods to fetch nodes from the database
    private List<Planet> getAllNodesFromDatabase() {
        // Return a list of all nodes.
        return nodeRepository.getAllNodesFromDatabase();
    }

    private Planet getEarthNode() {
        // Return the Earth node.
        return nodeRepository.getEarthNode();
    }

    private Planet getNodeByName(String name) {
        // Return the node with the given name.
        return nodeRepository.getNodeByName(name);
    }
}

