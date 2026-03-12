import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomAllocationService {

    // Stores all allocated room IDs to prevent duplicate assignments
    private Set<String> allocatedRoomIds;

    // Stores assigned room IDs by room type
    private Map<String, Set<String>> assignedRoomsByType;

    public RoomAllocationService() {
        allocatedRoomIds = new HashSet<>();
        assignedRoomsByType = new HashMap<>();
    }

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Check if room is available
        if (availability.getOrDefault(roomType, 0) > 0) {
            String roomId = generateRoomId(roomType);

            // Record the allocation to enforce uniqueness
            allocatedRoomIds.add(roomId);
            assignedRoomsByType.putIfAbsent(roomType, new HashSet<>());
            assignedRoomsByType.get(roomType).add(roomId);

            // Decrement inventory immediately to maintain system consistency
            inventory.updateAvailability(roomType, availability.get(roomType) - 1);

            System.out.println("Booking confirmed for Guest: " + reservation.getGuestName() + ", Room ID: " + roomId);
        } else {
            System.out.println("Booking failed for Guest: " + reservation.getGuestName() + " - No availability.");
        }
    }

    private String generateRoomId(String roomType) {
        // Generates an auto-incrementing ID like "Single-1", "Single-2"
        int count = assignedRoomsByType.getOrDefault(roomType, new HashSet<>()).size() + 1;
        String prefix = roomType.split(" ")[0]; // Extracts "Single" from "Single Room"
        return prefix + "-" + count;
    }
}