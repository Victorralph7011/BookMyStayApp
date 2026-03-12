public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("System Recovery");

        String persistenceFile = "inventory_state.txt";
        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        // 1. Attempt System Recovery
        persistenceService.loadInventory(inventory, persistenceFile);

        // 2. Display Current State after potential recovery
        System.out.println("\nCurrent Inventory:");
        inventory.getRoomAvailability().forEach((type, count) ->
                System.out.println(type.split(" ")[0] + ": " + count)
        );

        // 3. Save State (Preparing for next restart)
        persistenceService.saveInventory(inventory, persistenceFile);
    }
}