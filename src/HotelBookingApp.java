import java.util.Map;

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Hotel Room Inventory Status\n");

        RoomInventory inventory = new RoomInventory();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        Room single = new SingleRoom();
        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println("Available Rooms: " + availability.get("Single Room") + "\n");

        Room doubleR = new DoubleRoom();
        System.out.println("Double Room:");
        doubleR.displayRoomDetails();
        System.out.println("Available Rooms: " + availability.get("Double Room") + "\n");

        Room suite = new SuiteRoom();
        System.out.println("Suite Room:");
        suite.displayRoomDetails();
        System.out.println("Available Rooms: " + availability.get("Suite Room"));
    }
}