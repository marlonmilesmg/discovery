package za.co.discovery.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.discovery.assignment.models.Planet;
import za.co.discovery.assignment.services.InterstellarNodeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/interstellar")
public class InterstellarController {

    private final InterstellarNodeService nodeService;

    @Autowired
    public InterstellarController(InterstellarNodeService nodeService) {
        this.nodeService = nodeService;
    }

    // API endpoint to get the shortest path from Earth (A) to a specific destination planet
    @GetMapping("/shortestPath/{destination}")
    public Map<String, Object> getShortestPathToDestination(@PathVariable String destination) {
        Map<String, Object> response = new HashMap<>();

        List<Planet> shortestPath = nodeService.findShortestPathToDestination(destination);

        if (shortestPath.isEmpty()) {
            response.put("message", "Path to destination not found.");
        } else {
            response.put("message", "Shortest path found.");
            response.put("shortestPath", shortestPath);
        }

        return response;
    }
}
