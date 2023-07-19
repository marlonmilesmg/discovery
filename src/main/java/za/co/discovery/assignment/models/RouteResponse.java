package za.co.discovery.assignment.models;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "RouteResponse", namespace = "http://discovery.co.za/route")
public class RouteResponse {

    private List<String> hops;

    @XmlElement(name = "hops")
    public List<String> getHops() {
        if (hops == null) {
            hops = new ArrayList<>();
        }
        return hops;
    }
}
