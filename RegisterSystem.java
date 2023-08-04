
import java.io.FileWriter;
import java.io.IOException;


class RegisterSystem
{	
	void newUser(UserSystem us) throws IOException
	{
		System.out.println("******************NEW USER*****************");
			while(true)
		{
			System.out.print("Enter Username: ");
			String username = StdIn.readLine();
			if(us.users.containsKey(username))
			{
				System.out.println("User already exists! Please try again!");
				continue;
			}
			
			System.out.print("Enter password: ");
			String password = StdIn.readLine();
			try {
				FileWriter myWriter = new FileWriter("LoginDetails.txt",true);
				myWriter.write(System.lineSeparator()+String.format("%s|%s",username,password));
				myWriter.close();
			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
			User user = new User();
			user.username = username;
			user.password = password;
			user.debitBalance =5000.00;
			us.users.put(username, user);
			break;
		}
	}
	
}
