package za.co.discovery.assignment.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Planet")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String node;
    private String name;

    private double distance;

    @ManyToMany
    private List<Planet> neighbors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Planet> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Planet> neighbors) {
        this.neighbors = neighbors;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}