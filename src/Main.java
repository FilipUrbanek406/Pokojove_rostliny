import com.engeto.pokojoveRostliny.Plant;
import com.engeto.pokojoveRostliny.PlantException;
import com.engeto.pokojoveRostliny.PlantManager;
import com.engeto.pokojoveRostliny.PlantManagerFileExeption;

public class Main {
    public static void main(String[] args) {

        //1. úkol - Načíst seznam květin ze souboru
        PlantManager plantManager = new PlantManager();
        try {
            plantManager.readPlantManagerFromFile("resources/kvetiny.txt", "\t");
        } catch (PlantManagerFileExeption e) {
            System.err.println(e.getMessage());
        }

        //2. úkol - Vypsat informace o zálivcepro pro všechny květiny
        for (Plant plant : plantManager.getCopyOfPlants()) {
            System.out.println(plant.getName() + " zalitá dne: " + plant.getWatering());
        }

        //3. úkol - Přidat novou květinu
        try {
            plantManager.addPlant(new Plant("Kaktus", 14));
        } catch (PlantException e) {
            System.err.println(e.getMessage());
        }

        //4. úkol - Přidat 10 květin
        for (int i = 1; i <= 10; i++) {
            try {
                plantManager.addPlant(new Plant("Tulipán na prodej " + i, 14));
            } catch (PlantException e) {
                System.err.println(e.getMessage());
            }
        }

        //5. úkol - Květinu na 3. pozici odebrat
        plantManager.removePlant(plantManager.getPlantOfIndex(2));

        //6. úkol - Uložení seznamu květin do nového souboru
        try {
            plantManager.writePlantManagerToFile("resources/kvetiny - novy.txt", "\t");
        } catch (PlantManagerFileExeption e) {
            System.err.println(e.getMessage());
        }

        //7. úkol - Opětovné načtení seznamu květin ze souboru
        try {
            plantManager.readPlantManagerFromFile("resources/kvetiny - novy.txt", "\t");
        } catch (PlantManagerFileExeption e) {
            System.err.println(e.getMessage());
        }

        //8. úkol - Seřazení květin a výpis na obrazovku
        plantManager.sortPlantsByName();
        System.out.println("");
        System.out.println("Seřazené květiny podle názvu:");
        for (Plant plant : plantManager.getCopyOfPlants()) {
            System.out.println(plant.getName());
        }
    }
}