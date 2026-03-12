public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Booking History and Reporting\n");

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulate confirmed bookings being added to history
        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Generate the summary report
        reportService.generateReport(history);
    }
}