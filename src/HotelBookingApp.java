public class HotelBookingApp {
    public static void main(String[] args) {
        System.out.println("Hotel Room Initialization\n");

        Room single = new SingleRoom();
        int singleAvailable = 5;
        System.out.println("Single Room:");
        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailable + "\n");

        Room doubleR = new DoubleRoom();
        int doubleAvailable = 3;
        System.out.println("Double Room:");
        doubleR.displayRoomDetails();
        System.out.println("Available: " + doubleAvailable + "\n");

        Room suite = new SuiteRoom();
        int suiteAvailable = 2;
        System.out.println("Suite Room:");
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}