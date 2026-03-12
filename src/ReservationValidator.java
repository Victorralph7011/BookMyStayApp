import java.util.Map;

public class ReservationValidator {
    public void validate(String guestName, String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        // Validate Guest Name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Validate Room Type (Input Validation)
        Map<String, Integer> availability = inventory.getRoomAvailability();
        if (!availability.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // Guard System State (Constraint Validation)
        if (availability.get(roomType) <= 0) {
            throw new InvalidBookingException("Selected room type is currently unavailable.");
        }
    }
}