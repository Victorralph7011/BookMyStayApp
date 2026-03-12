public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Room Search\n");

        // Initialize Inventory and Room Definitions
        RoomInventory inventory = new RoomInventory();
        Room single = new SingleRoom();
        Room doubleR = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Initialize Search Service
        RoomSearchService searchService = new RoomSearchService();

        // Execute Search (Read-Only)
        searchService.searchAvailableRooms(inventory, single, doubleR, suite);
    }
}