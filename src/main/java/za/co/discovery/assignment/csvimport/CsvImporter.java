package za.co.discovery.assignment.csvimport;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import za.co.discovery.assignment.config.DatabaseConfig;
import za.co.discovery.assignment.models.Planet;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.IOException;

public class CsvImporter {

    public static void importData(String csvFilePath) {
        EntityManager em = DatabaseConfig.getEntityManagerFactory().createEntityManager();
        boolean firstLine = true;
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                if (nextLine.length < 2) {
                    System.err.println("Invalid CSV line: " + String.join(",", nextLine));
                    continue;
                }

                String sourcePlanet = nextLine[0];
                String destinationPlanet = nextLine[1];

                Planet source = new Planet();
                source.setName(sourcePlanet);

                Planet destination = new Planet();
                destination.setName(destinationPlanet);

                em.getTransaction().begin();
                em.persist(source);
                em.persist(destination);
                em.getTransaction().commit();
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
