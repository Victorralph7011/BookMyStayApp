import java.util.Scanner;

public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Booking Validation");
        Scanner scanner = new Scanner(System.in);

        // Initialize required components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            System.out.print("Enter guest name: ");
            String name = scanner.nextLine();

            System.out.print("Enter room type (Single Room/Double Room/Suite Room): ");
            String type = scanner.nextLine();

            // Validate input centrally before processing
            validator.validate(name, type, inventory);

            // If valid, proceed to queue the request
            Reservation res = new Reservation(name, type);
            bookingQueue.addRequest(res);
            System.out.println("Booking request accepted for " + name);

        } catch (InvalidBookingException e) {
            // Handle domain-specific validation errors gracefully
            System.out.println("Booking failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}