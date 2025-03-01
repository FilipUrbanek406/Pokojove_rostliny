package com.engeto.pokojoveRostliny;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PlantManager {

    private List<Plant> plants = new ArrayList<>();

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public Plant getPlantOfIndex(int index) {
        return plants.get(index);
    }

    public List<Plant> getCopyOfPlants() {
        return new ArrayList<>(plants);
    }

    //metoda, která má vrátit seznam rostlin, které je třeba zalít (datum zálivky je starší než počet dnů kdy mají být zalité)
    public List<Plant> getPlantsToWater() {
        List<Plant> plantsToWater = new ArrayList<>();
        for (Plant plant : plants) {
            if (plant.getWatering().isBefore(LocalDate.now().minusDays(plant.getFrequencyOfWatering()))) {
                plantsToWater.add(plant);
            }
        }
        return plantsToWater;
    }

    // Seřazení podle názvu rostliny (výchozí řazení)
    public void sortPlantsByName() {
        Collections.sort(plants);
    }

    // Seřazení podle data poslední zálivky
    public List<Plant> sortPlantsByWatering() {
        plants.sort(Comparator.comparing(Plant::getWatering));
        return plants;
    }

    //čtení ze souboru
    public void readPlantManagerFromFile(String fileName, String delimiter) throws PlantManagerFileExeption {
        plants.clear();
        int lineNumber = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                String[] parts = line.split(delimiter);
                if (parts.length != 5) {
                    throw new PlantManagerFileExeption("Chyba při čtení souboru: " + fileName + " na řádku: " + lineNumber + "! Špatný počet položek!");
                }
                Plant newPlant = parsePlant(parts);
                plants.add(newPlant);
            }
        } catch (FileNotFoundException e) {
            throw new PlantManagerFileExeption("Nepodařilo se nalézt soubor: " + fileName + "!");
        } catch (PlantException e) {
            throw new PlantManagerFileExeption("Chyba při čtení souboru: " + fileName + " na řádku: " + lineNumber + "!");
        } catch (NumberFormatException e) {
            throw new PlantManagerFileExeption("Chyba při čtení souboru: " + fileName + " na řádku: " + lineNumber + "! Chybný formát čísla!");
        } catch (DateTimeParseException e) {
            throw new PlantManagerFileExeption("Chyba při čtení souboru: " + fileName + " na řádku: " + lineNumber + "! Chybný formát data!");
        }
    }

    private Plant parsePlant(String[] parts) throws PlantException {
        String name = parts[0].trim();
        String notes = parts[1].trim();
        int frequencyOfWatering = Integer.parseInt(parts[2].trim());
        LocalDate watering = LocalDate.parse(parts[3].trim());
        LocalDate planted = LocalDate.parse(parts[4].trim());
        return new Plant(name, notes, planted, watering, frequencyOfWatering);
    }

    //zápis do souboru
    public void writePlantManagerToFile(String fileName, String delimiter) throws PlantManagerFileExeption {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Plant plant : plants) {
                exportPlant(plant, writer, delimiter);
            }
        } catch (IOException e) {
            throw new PlantManagerFileExeption("Chyba při zápisu do souboru: " + fileName + "!");
        }
    }

    private void exportPlant(Plant plant, PrintWriter writer, String delimiter) {
        writer.println(plant.getName() + delimiter + plant.getNotes() + delimiter + plant.getFrequencyOfWatering() + delimiter + plant.getWatering() + delimiter + plant.getPlanted());
    }
}
