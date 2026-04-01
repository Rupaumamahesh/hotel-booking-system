import java.util.HashMap;
import java.util.Map;

/**
 * ===================================================================
 * BASE CLASSES - Room & Room Types
 * ===================================================================
 */
abstract class Room {
    protected int numberOfBeds;
    protected int squareFeet;
    protected double pricePerNight;

    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Number of Beds: " + numberOfBeds);
        System.out.println("Square Feet: " + squareFeet);
        System.out.println("Price Per Night: ₹" + pricePerNight);
    }
}

class SingleRoom extends Room {
    public SingleRoom() { super(1, 250, 1500.0); }
}

class DoubleRoom extends Room {
    public DoubleRoom() { super(2, 400, 2500.0); }
}

class SuiteRoom extends Room {
    public SuiteRoom() { super(3, 750, 5000.0); }
}

/**
 * ===================================================================
 * CLASS - RoomInventory
 * ===================================================================
 */
class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() { initializeInventory(); }

    private void initializeInventory() {
        roomAvailability = new HashMap<>();
        roomAvailability.put("SingleRoom", 5);
        roomAvailability.put("DoubleRoom", 3);
        roomAvailability.put("SuiteRoom", 2);
    }

    public Map<String, Integer> getRoomAvailability() { return roomAvailability; }

    public void updateAvailability(String roomType, int count) {
        roomAvailability.put(roomType, count);
    }

    public boolean isAvailable(String roomType) {
        return roomAvailability.getOrDefault(roomType, 0) > 0;
    }

    public boolean bookRoom(String roomType) {
        if (isAvailable(roomType)) {
            roomAvailability.put(roomType, roomAvailability.get(roomType) - 1);
            return true;
        }
        return false;
    }
}

/**
 * ===================================================================
 * MAIN CLASS - UseCase3InventorySetup
 * ===================================================================
 */
public class UseCase3InventorySetup {

    public static void main(String[] args) {
        // Create room instances
        SingleRoom singleRoom = new SingleRoom();
        DoubleRoom doubleRoom = new DoubleRoom();
        SuiteRoom suiteRoom = new SuiteRoom();

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();
        Map<String, Integer> availability = inventory.getRoomAvailability();

        // Display initial inventory
        System.out.println("=== Initial Hotel Room Inventory ===\n");
        displayRoomStatus(singleRoom, "SingleRoom", inventory);
        displayRoomStatus(doubleRoom, "DoubleRoom", inventory);
        displayRoomStatus(suiteRoom, "SuiteRoom", inventory);

        // Demonstrate booking logic
        System.out.println("\n=== Booking Demonstration ===\n");

        attemptBooking(inventory, "SingleRoom", 2);
        attemptBooking(inventory, "DoubleRoom", 3);
        attemptBooking(inventory, "SuiteRoom", 3); // exceeds availability

        // Display final inventory
        System.out.println("\n=== Final Hotel Room Inventory ===\n");
        displayRoomStatus(singleRoom, "SingleRoom", inventory);
        displayRoomStatus(doubleRoom, "DoubleRoom", inventory);
        displayRoomStatus(suiteRoom, "SuiteRoom", inventory);
    }

    // Helper method to display room status
    private static void displayRoomStatus(Room room, String roomType, RoomInventory inventory) {
        System.out.println(roomType + ":");
        room.displayRoomDetails();
        int count = inventory.getRoomAvailability().get(roomType);
        System.out.println("Available Rooms: " + count);
        System.out.println("Status: " + (inventory.isAvailable(roomType) ? "Available" : "Sold Out"));
        System.out.println();
    }

    // Helper method to attempt booking multiple rooms
    private static void attemptBooking(RoomInventory inventory, String roomType, int numberToBook) {
        System.out.println("Attempting to book " + numberToBook + " " + roomType + "(s)...");
        for (int i = 0; i < numberToBook; i++) {
            if (inventory.bookRoom(roomType)) {
                System.out.println("✓ " + roomType + " booked successfully");
            } else {
                System.out.println("✗ " + roomType + " booking failed - no availability");
            }
        }
        System.out.println("Remaining " + roomType + " Rooms: " + inventory.getRoomAvailability().get(roomType) + "\n");
    }
}