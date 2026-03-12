import java.io.*;
import java.util.Map;

public class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Integer> entry : inventory.getRoomAvailability().entrySet()) {
                writer.println(entry.getKey() + "=" + entry.getValue());
            }
            System.out.println("Inventory saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    inventory.updateAvailability(parts[0], Integer.parseInt(parts[1]));
                }
            }
            System.out.println("Inventory restored from file.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading inventory: " + e.getMessage() + ". Starting fresh.");
        }
    }
}