package za.co.discovery.assignment.services;

import za.co.discovery.assignment.controller.InterstellarNodeServiceImpl;
import za.co.discovery.assignment.models.Planet;
import za.co.discovery.assignment.models.RouteRequest;
import za.co.discovery.assignment.models.RouteResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService(targetNamespace = "http://localhost:8080/discovery/assignment/route")
public class RouteService {
private InterstellarNodeServiceImpl interstellarNodeService;
    @WebMethod(operationName = "getRoute")
    public RouteResponse getRoute(@WebParam(name = "RouteRequest") RouteRequest request) {
        String source = request.getSource();
        String destination = request.getDestination();

        // Find the shortest path (hops) between the source and destination using Dijkstra's algorithm
        List<Planet> shortestPath = interstellarNodeService.findShortestPathToDestination(destination);

        RouteResponse response = new RouteResponse();
        if (shortestPath != null) {
            for (Planet node : shortestPath) {
                response.getHops().add(node.getName());
            }
        } else {
            // Handle the case where the source or destination planet is not found
            response.getHops().add("Route not found");
        }

        return response;
    }
}

