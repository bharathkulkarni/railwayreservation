

import java.util.HashMap;
import java.util.Scanner;

class BookingSystem
{
	TrainEnquiry Enq;
	TicketBooking Book;
	PaymentSystem Pmnt;
	TicketCancellation Cncl;
	static Scanner in = new Scanner(System.in);
	public int booking(User user) throws Exception
	{
		
		System.out.println("*******************************************");
		System.out.println("1) View account balance");
		System.out.println("2) Enquire");
		System.out.println("3) Book Ticket");
		System.out.println("4) Cancel Ticket");
		System.out.println("5) Return to previous menu"); 
		
		System.out.print("Enter your choice: ");
		int choice = StdIn.readInt();
		if(choice==1)
		{
			 System.out.println("Balcnce in your account: "+ user.debitBalance+"/-");
			 
			 return 0;
		}
		else if(choice == 2)
		{
			HashMap<Integer,Train> tt = enquiry();
			if(tt.isEmpty())
				 System.out.println("No trains exists for the given information");
			
			return 0; 
		}
		else if(choice == 3)
		{
			HashMap<Integer,Train> tt = enquiry();
			if(!tt.isEmpty())
			{
				
				{
					System.out.println("Select train number to book");
					int n = in.nextInt();
					if(tt.containsKey(n))
					{
						Train t = tt.get(n);
						if(t.getNumberOfSeats() > 0)
						{
							System.out.println("Train has "+ t.getNumberOfSeats()+" seats");
							System.out.println("booking");
							if(Book == null)
								Book = new TicketBooking();
							if(Pmnt == null)
								Pmnt = new PaymentSystem();
							
							if(Pmnt.deductBalance(t,user))
							{
								Book.bookTicket(n,user);
							}
							else
								System.out.println("Booking failed. User has insufficient balance.");
						}
						else
						{
							System.out.println("Train doesn't have any vacant seat");
						}
					}
					else
						System.out.println("Please enter the correct Train Number");
				}
			}
			
			return 0;	
		}
		else if(choice == 4)
		{
			if(Cncl == null)
				Cncl = new TicketCancellation();
			
			Cncl.cancelTicket(user);
		}
		return 1;
	}

	private HashMap<Integer,Train> enquiry() throws Exception  {
		if(Enq == null)
			Enq = new TrainEnquiry();
		
		 HashMap<Integer,Train> t = Enq.enquire();
		 
		 return t;
		 
		
	}
	
	
}
