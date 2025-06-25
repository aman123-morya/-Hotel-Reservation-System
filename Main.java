
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        RoomManager roomManager = new RoomManager();
        ReservationManager reservationManager = new ReservationManager();
        PaymentManager paymentManager = new PaymentManager();

        roomManager.getAllRooms().addAll(FileManager.loadRooms());
        reservationManager.getReservations().addAll(FileManager.loadReservations());

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nHotel Reservation System:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter room category (Standard/Deluxe/Suite): ");
                    String cat = scanner.nextLine();
                    List<Room> available = roomManager.searchAvailableRooms(cat);
                    if (available.isEmpty()) {
                        System.out.println("No available rooms in this category.");
                    } else {
                        available.forEach(System.out::println);
                    }
                    break;
                case 2:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter room number to book: ");
                    int roomNum = scanner.nextInt();
                    System.out.print("Enter check-in date (YYYY-MM-DD): ");
                    LocalDate in = LocalDate.parse(scanner.next());
                    System.out.print("Enter check-out date (YYYY-MM-DD): ");
                    LocalDate out = LocalDate.parse(scanner.next());
                    Room selectedRoom = null;
                    for (Room r : roomManager.getAllRooms()) {
                        if (r.getRoomNumber() == roomNum && r.isAvailable()) {
                            selectedRoom = r;
                            break;
                        }
                    }
                    if (selectedRoom == null) {
                        System.out.println("Room not available or invalid number.");
                        break;
                    }
                    double totalAmount = selectedRoom.getPrice() * (out.toEpochDay() - in.toEpochDay());
                    if (paymentManager.processPayment(name, totalAmount)) {
                        Reservation res = reservationManager.makeReservation(name, roomNum, in, out, totalAmount);
                        roomManager.markRoomAsBooked(roomNum);
                        System.out.println("Booking Successful! ID: " + res.getReservationId());
                    }
                    break;
                case 3:
                    System.out.print("Enter reservation ID to cancel: ");
                    String resId = scanner.nextLine();
                    boolean cancelled = reservationManager.cancelReservation(resId);
                    if (cancelled) {
                        System.out.println("Reservation cancelled successfully.");
                    } else {
                        System.out.println("Reservation not found.");
                    }
                    break;
                case 4:
                    reservationManager.getReservations().forEach(System.out::println);
                    break;
                case 5:
                    running = false;
                    FileManager.saveRooms(roomManager.getAllRooms());
                    FileManager.saveReservations(reservationManager.getReservations());
                    System.out.println("Thank you for using Hotel Reservation System. Data saved.");
                    break;
            }
        }
        scanner.close();
    }
}

