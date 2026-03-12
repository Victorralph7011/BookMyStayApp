import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CancellationService {
    // Stack that stores recently released room IDs for LIFO tracking
    private Stack<String> releasedRoomIds;

    // Maps reservation ID to room type to know which inventory to restore
    private Map<String, String> reservationRoomTypeMap;

    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {
        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation ID " + reservationId + " not found.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Push the released ID onto the stack (LIFO)
        releasedRoomIds.push(reservationId);

        // Restore inventory safely
        int currentCount = inventory.getRoomAvailability().get(roomType);
        inventory.updateAvailability(roomType, currentCount + 1);

        // Remove from active registry
        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    public void showRollbackHistory() {
        System.out.println("\nRollback History (Most Recent First):");
        // Creating a temporary copy to display LIFO order without destroying the stack
        Stack<String> tempStack = (Stack<String>) releasedRoomIds.clone();
        while (!tempStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + tempStack.pop());
        }
    }
}