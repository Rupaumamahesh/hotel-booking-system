/**
 * ===================================================================
 * Use Case 2: Basic Room Types & Static Availability
 * ===================================================================
 *
 * Description:
 * This abstract class represents a generic hotel room.
 *
 * It models attributes that are intrinsic to a room type
 * and remain constant regardless of availability.
 *
 * Inventory-related concerns are intentionally excluded.
 *
 * @version 2.1
 */
abstract class Room {

    /** Number of beds available in the room. */
    protected int numberOfBeds;

    /** Total size of the room in square feet. */
    protected int squareFeet;

    /** Price charged per night for this room type. */
    protected double pricePerNight;

    /**
     * Constructor used by child classes to
     * initialize common room attributes.
     */
    public Room(int numberOfBeds, int squareFeet, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.squareFeet = squareFeet;
        this.pricePerNight = pricePerNight;
    }

    /** Displays room details. */
    public void displayRoomDetails() {
        System.out.println("Number of Beds: " + numberOfBeds);
        System.out.println("Square Feet: " + squareFeet);
        System.out.println("Price Per Night: ₹" + pricePerNight);
    }
}

/**
 * ===================================================================
 * CLASS - SingleRoom
 * ===================================================================
 */
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

/**
 * ===================================================================
 * CLASS - DoubleRoom
 * ===================================================================
 */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

/**
 * ===================================================================
 * CLASS - SuiteRoom
 * ===================================================================
 */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

/**
 * ===================================================================
 * MAIN CLASS - UseCaseApp
 * ===================================================================
 *
 * Use Case 2: Room Initialization
 */
public class UseCaseApp {

    public static void main(String[] args) {

        // Create room instances
        SingleRoom singleRoom = new SingleRoom();
        DoubleRoom doubleRoom = new DoubleRoom();
        SuiteRoom suiteRoom = new SuiteRoom();

        // Display room details
        System.out.println("=== Single Room Details ===");
        singleRoom.displayRoomDetails();
        System.out.println();

        System.out.println("=== Double Room Details ===");
        doubleRoom.displayRoomDetails();
        System.out.println();

        System.out.println("=== Suite Room Details ===");
        suiteRoom.displayRoomDetails();
    }
}