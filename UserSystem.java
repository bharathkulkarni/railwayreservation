
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

class UserSystem
{
	HashMap<String, User> users;
	
	public UserSystem() throws FileNotFoundException {
		users = new HashMap<>();
		User user = new User();
		user.username ="admin";
		user.password = "admin";
		users.put("admin",user);
		File myFile = new File("LoginDetails.txt");
		Scanner scanner = new Scanner(myFile);
		scanner.useDelimiter("\\n|\\|");
		String ignore = scanner.nextLine();
		while (scanner.hasNext()) {
			User old_user=new User();
			old_user.username=scanner.next().trim();
			old_user.password=scanner.next().trim();
			users.put(old_user.username,old_user);
		}
	}
	
	public HashMap<String, User> getUsers()
	{
		return users;
	}
	
	public void setUsers(String username, User user)
	{
		users.put(username, user);
	}
	void disp() throws Exception
	{
		while(true)
		{	
			Menu.map.put(1,"New User");
			Menu.map.put(2,"Returning User");
			Menu.map.put(3,"Return to previous menu");
			Menu.disp(Menu.map.size(), Menu.map);
			int secondChoice = Integer.parseInt(StdIn.readLine());
			if(secondChoice == 1)
			{
				RegisterSystem rs = new RegisterSystem();
				rs.newUser(this);
			}
			else if(secondChoice == 2)
			{
				UserLogin ls = new UserLogin();
				ls.login(this);
			}
			else 
				return;
		}	
	}
}
