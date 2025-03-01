import com.engeto.pokojoveRostliny.Plant;
import com.engeto.pokojoveRostliny.PlantException;
import com.engeto.pokojoveRostliny.PlantManager;
import com.engeto.pokojoveRostliny.PlantManagerFileExeption;

public class Main {

    public static final String FILE_NAME = "resources/kvetiny.txt";
    public static final String DELIMITER = "\t";
    public static final String FILE_NAME_NEW = "resources/kvetiny - new.txt";

    public static void main(String[] args) {

        PlantManager plantManager = new PlantManager();

        //1. úkol - Načíst seznam květin ze souboru
        try {
            plantManager.readPlantManagerFromFile(FILE_NAME, DELIMITER);
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
            plantManager.writePlantManagerToFile(FILE_NAME_NEW, DELIMITER);
        } catch (PlantManagerFileExeption e) {
            System.err.println(e.getMessage());
        }

        //7. úkol - Opětovné načtení seznamu květin ze souboru
        try {
            plantManager.readPlantManagerFromFile(FILE_NAME_NEW, DELIMITER);
        } catch (PlantManagerFileExeption e) {
            System.err.println(e.getMessage());
        }

        //8. úkol - Seřazení květin a výpis na obrazovku
        plantManager.sortPlantsByName();
        System.out.println();
        System.out.println("Seřazené květiny podle názvu:");
        for (Plant plant : plantManager.getCopyOfPlants()) {
            System.out.println(plant.getName());
        }

        System.out.println();
        System.out.println("Řezení podle poslední zálivky:");
        System.out.println(plantManager.sortPlantsByWatering());

        //Navíc oproti zadání projektu
        System.out.println();
        System.out.println("Rostliny, které je potřeba zalít " + plantManager.getPlantsToWater());

        System.out.println();
        for (Plant plant : plantManager.getCopyOfPlants()) {
            System.out.println("Informace o zálivce: " + plant.getWateringInfo());
        }
    }
}