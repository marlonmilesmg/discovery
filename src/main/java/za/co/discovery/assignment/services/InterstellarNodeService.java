package za.co.discovery.assignment.services;

import za.co.discovery.assignment.models.Planet;

import java.util.List;

public interface InterstellarNodeService {
    List<Planet> findShortestPathToDestination(String destination);

}
