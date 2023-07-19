package za.co.discovery.assignment.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.discovery.assignment.models.Planet;

import java.util.List;

@Repository
public interface InterstellarNodeRepository extends JpaRepository<Planet, Long> {
    // Add custom query methods here if needed
    @Query(value = "SELECT * FROM planet", nativeQuery = true)
    List<Planet> getAllNodesFromDatabase();

    @Query(value = "SELECT * FROM planet WHERE name = 'Earth'", nativeQuery = true)
    Planet getEarthNode();

    @Query(value = "SELECT * FROM planet WHERE name = :name", nativeQuery = true)
    Planet getNodeByName(String name);
}
