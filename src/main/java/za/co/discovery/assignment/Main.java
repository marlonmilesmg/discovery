package za.co.discovery.assignment;

import za.co.discovery.assignment.csvimport.CsvImporter;
import za.co.discovery.assignment.services.RouteService;

import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "/home/magonjo/IdeaProjects/assignment/src/main/resources/Discovery HR-Offsite AssignmentV3 0.docx-EmbeddedFile.xlsx - Planet Names.csv";
        CsvImporter.importData(csvFilePath);

//        Endpoint.publish("http://localhost:8080/RouteService", new RouteService());
//        System.out.println("RouteService SOAP web service published at http://localhost:8080/RouteService");
    }
}