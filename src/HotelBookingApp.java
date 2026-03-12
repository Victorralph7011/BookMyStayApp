public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Booking Cancellation\n");

        // Initialize components
        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        // 1. Setup: Register an existing booking (ID from UC6)
        String resId = "Single-1";
        String roomType = "Single Room";
        cancellationService.registerBooking(resId, roomType);

        // 2. Perform Cancellation
        cancellationService.cancelBooking(resId, inventory);

        // 3. Show History and Updated State
        cancellationService.showRollbackHistory();

        System.out.println("\nUpdated Single Room Availability: " +
                inventory.getRoomAvailability().get(roomType));
    }
}