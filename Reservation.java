
import java.time.LocalDate;

public class Reservation {
    private String reservationId;
    private int roomNumber;
    private String customerName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private double totalAmount;

    public Reservation(String reservationId, int roomNumber, String customerName,
                       LocalDate checkIn, LocalDate checkOut, double totalAmount) {
        this.reservationId = reservationId;
        this.roomNumber = roomNumber;
        this.customerName = customerName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalAmount = totalAmount;
    }

    public String getReservationId() { return reservationId; }
    public int getRoomNumber() { return roomNumber; }
    public String getCustomerName() { return customerName; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public double getTotalAmount() { return totalAmount; }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId='" + reservationId + '\'' +
                ", roomNumber=" + roomNumber +
                ", customerName='" + customerName + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", totalAmount=" + totalAmount +
                '}';
    }
}