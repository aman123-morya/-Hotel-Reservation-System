
import java.time.LocalDate;
import java.util.*;

public class ReservationManager {
    private List<Reservation> reservations = new ArrayList<>();

    public Reservation makeReservation(String customerName, int roomNumber, LocalDate checkIn, LocalDate checkOut, double amount) {
        String reservationId = UUID.randomUUID().toString();
        Reservation reservation = new Reservation(reservationId, roomNumber, customerName, checkIn, checkOut, amount);
        reservations.add(reservation);
        return reservation;
    }

    public boolean cancelReservation(String reservationId) {
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation r = iterator.next();
            if (r.getReservationId().equals(reservationId)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Reservation> getReservations() { return reservations; }
}

