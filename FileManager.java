
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class FileManager {
    private static final String ROOM_FILE = "rooms.txt";
    private static final String RESERVATION_FILE = "reservations.txt";

    public static void saveRooms(List<Room> rooms) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ROOM_FILE))) {
            for (Room room : rooms) {
                writer.write(room.getRoomNumber() + "," + room.getCategory() + "," + room.isAvailable() + "," + room.getPrice());
                writer.newLine();
            }
        }
    }

    public static List<Room> loadRooms() throws IOException {
        List<Room> rooms = new ArrayList<>();
        File file = new File(ROOM_FILE);
        if (!file.exists()) return rooms;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Room room = new Room(Integer.parseInt(parts[0]), parts[1], Boolean.parseBoolean(parts[2]), Double.parseDouble(parts[3]));
                rooms.add(room);
            }
        }
        return rooms;
    }

    public static void saveReservations(List<Reservation> reservations) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESERVATION_FILE))) {
            for (Reservation r : reservations) {
                writer.write(r.getReservationId() + "," + r.getRoomNumber() + "," + r.getCustomerName() + "," +
                        r.getCheckIn() + "," + r.getCheckOut() + "," + r.getTotalAmount());
                writer.newLine();
            }
        }
    }

    public static List<Reservation> loadReservations() throws IOException {
        List<Reservation> reservations = new ArrayList<>();
        File file = new File(RESERVATION_FILE);
        if (!file.exists()) return reservations;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Reservation reservation = new Reservation(parts[0], Integer.parseInt(parts[1]), parts[2],
                        LocalDate.parse(parts[3]), LocalDate.parse(parts[4]), Double.parseDouble(parts[5]));
                reservations.add(reservation);
            }
        }
        return reservations;
    }
}

