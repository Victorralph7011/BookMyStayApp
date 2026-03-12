public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Room Allocation Processing");

        // Initialize Core Systems
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Guest Intent (Queueing Phase)
        Reservation r1 = new Reservation("Abhi", "Single Room");
        Reservation r2 = new Reservation("Subha", "Single Room");
        Reservation r3 = new Reservation("Vanmathi", "Suite Room");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Allocation Phase (Processing the FIFO Queue)
        while (bookingQueue.hasPendingRequests()) {
            Reservation nextRequest = bookingQueue.getNextRequest();
            allocationService.allocateRoom(nextRequest, inventory);
        }
    }
}