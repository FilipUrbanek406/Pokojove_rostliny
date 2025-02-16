package com.engeto.pokojoveRostliny;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlantManager {

    //Nejsem si tím atributem ještě jistý
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

    //získání kopie seznamu rostlin - nejsem si jistý, zda je to správně
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
    public void sortPlantsByWatering() {
        plants.sort(Comparator.comparing(Plant::getWatering));
    }
}
