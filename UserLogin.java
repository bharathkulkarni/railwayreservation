import java.util.Scanner;

class UserLogin
{   static Scanner in = new Scanner(System.in);
	void login(UserSystem us) throws Exception
	{   
		System.out.println("************RETURNING USER*****************");
		
		while(true)
		{
			System.out.print("Enter Username: ");
			String username = in.nextLine();
			System.out.print("Enter password: ");
			String password = in.nextLine();
			if(us.users.containsKey(username))
				if(us.users.get(username).password.equals(password))
				{   System.out.println("-Login Successful-");
					BookingSystem bs = new BookingSystem();
					while(true)
					{
						User loggedInUser = us.getUsers().get(username);
						int val = loggedInUser.bookingSystem.booking(loggedInUser);
						if(val == 1)
							return;
					}
				}
			System.out.println("Credentials don't match! Try again!");
		}
	}
}

