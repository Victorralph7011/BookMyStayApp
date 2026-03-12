public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Concurrent Booking Simulation");

        // Initialize shared resources
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Seed the shared queue with multiple requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single Room"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double Room"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Subha", "Single Room"));

        // Create booking processor tasks
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(bookingQueue, inventory, allocationService)
        );

        // Start concurrent processing
        t1.start();
        t2.start();

        try {
            // Wait for both threads to finish
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        // Display final state
        System.out.println("\nRemaining Inventory:");
        inventory.getRoomAvailability().forEach((type, count) ->
                System.out.println(type.split(" ")[0] + ": " + count)
        );
    }
}