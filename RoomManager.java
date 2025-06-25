
import java.util.*;

public class RoomManager {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) { rooms.add(room); }

    public List<Room> searchAvailableRooms(String category) {
        List<Room> result = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                result.add(room);
            }
        }
        return result;
    }

    public void markRoomAsBooked(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setAvailable(false);
                break;
            }
        }
    }

    public void markRoomAsAvailable(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                room.setAvailable(true);
                break;
            }
        }
    }

    public List<Room> getAllRooms() { return rooms; }
}
