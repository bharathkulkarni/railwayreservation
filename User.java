
import java.util.HashMap;
import java.util.Random;

public class User {

	String username;
	String password;
	
	double debitBalance;
	double creditBalance;
	BookingSystem bookingSystem;
	HashMap<Integer,Ticket> bookings;
	
	public HashMap<Integer,Ticket> getBookings() {
		return bookings;
	}

	public void setBookings(Ticket ticket) {
		bookings.put(ticket.getBookingNumber(),ticket);
	}
	
	public void removeBooking(int bookingNumber)
	{
		bookings.remove(bookingNumber);
	}

	User()
	{
		bookingSystem = new BookingSystem();
		bookings = new HashMap<Integer,Ticket>();
	}
	
	
}