import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class TrainAdmin extends Train {
	
	static Scanner in = new Scanner(System.in);
	private static TrainAdmin tAdmin;

	private TrainAdmin() {
		init("TrainList.txt");
	}

	private void init(String filename) {  
		Train[] t= new Train[10];
		File myFile=new File(filename);
		Scanner scanner;
		int index=0;
		try {
			scanner = new Scanner(myFile);
			scanner.useDelimiter("\\n|\\|");
			String ignore=scanner.nextLine();
			while (scanner.hasNext()) {
				String start= scanner.next();
				String end= scanner.next();
				double price= Double.parseDouble(scanner.next());
				int days= Integer.parseInt(scanner.next());
				int seats= Integer.parseInt(scanner.next().trim());
				Train t_new=new Train(start,end,price,days,seats);
				t[index++]=t_new;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(Train trains:t){
			if(trains!=null)
				TrainList.setTrainList(trains);
		}
		/*TrainScheduler.setTrainList(t[0]);
		TrainScheduler.setTrainList(t[1]);
		TrainScheduler.setTrainList(t[2]);
		TrainScheduler.setTrainList(t[3]);
		TrainScheduler.setTrainList(t[4]);*/
	}

	public static TrainAdmin getTrainAdmin() {
		if (tAdmin == null) {
			synchronized (TrainAdmin.class) {
				if (tAdmin == null) {
					tAdmin = new TrainAdmin();
				}
			}
		}
		return tAdmin;
	}

	public void addTrain() {
		Train train = new Train();
		
		System.out.println("Enter Starting Location-");
		String startCity = in.nextLine().toLowerCase();
		train.setStartCity(startCity);
		
		System.out.println("Enter Destination Location-");
		String destinationCity = in.nextLine().toLowerCase();
		train.setEndCity(destinationCity);
		
		System.out.println("Enter Cost of Train Ticket-");
		double trainFare = in.nextDouble();
		train.setTrainFare(trainFare); 
		
		System.out.println("Enter The Total Number Of Seats on The Train-");
		int totalSeats = in.nextInt();
		train.setNumberOfSeats(totalSeats);
		
		System.out.println("Enter The Trains Operating Days-");
		System.out.println("1=Sunday, 2=Monday, 3=Tuesday, 4=Wednesday, 5=Thursday, 6=Friday, 7=Saturday");
		System.out.println("Example: 125= Sunday,Monday and Thursday , 37= Tuesday and Saturday");
		int days = in.nextInt();

		try {
			FileWriter myWriter = new FileWriter("TrainList.txt",true);
			myWriter.write(System.lineSeparator()+String.format("%s|%s|%f|%d|%d",startCity,destinationCity,trainFare,days,totalSeats));
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		train.setOperatingDays(days);
		TrainList.setTrainList(train);
	}
	
	public boolean trainExists(int n)
	{
		HashMap<Integer, Train> trainList = TrainList.getTrainList();
		if(!trainList.containsKey(n))
			return false;
		return true;
	}
	
	public Train trainSelector(int n)
	{
		
		HashMap<Integer, Train> trainList = TrainList.getTrainList();
		return trainList.get(n);
	}
	
	public void deleteTrain(int n)
	{
		HashMap<Integer, Train> trainList = TrainList.getTrainList();
		if(!trainList.containsKey(n))
		{
			System.out.println("Train Does Not Exist!!! Invalid Train Number");
			return;
		}
		else
		{
			trainList.remove(n);
			System.out.println("Train Deleted Successfully");
		}
	}
	
	public void updateTrain(int n)
	{
		
		System.out.println("Select what you want to Update");
		System.out.println("1) Start City");
		System.out.println("2) Destination city");
		System.out.println("3) Train Ticket Price");
		System.out.println("4) Train Operating Days");
		System.out.println("5) Total Number of Seats");
		int choice = in.nextInt();
		in.nextLine(); 		
		String temp;
		Train t = trainSelector(n);
		switch(choice)
		{
			case 1:
				System.out.println("Enter New Start City:");
				temp = in.nextLine();
				t.setStartCity(temp.toLowerCase());
				break;
			case 2:
				System.out.println("Enter New Destination City:");
				temp = in.nextLine().toLowerCase();
				t.setEndCity(temp);
				break;
			case 3:
				System.out.println("Enter New Ticket Price:");
				double tt = in.nextDouble();
				in.nextLine();
				t.setTrainFare(tt);
				break;
			case 4:
				System.out.println("Enter the New Train Operating Days:");
				int opDay = in.nextInt();
				t.setOperatingDays(opDay);
				break;
			case 5:
				System.out.println("Enter the New Total Number of seats:");
				int seats = in.nextInt();
				t.setNumberOfSeats(seats);
				break;
			default:
				System.out.println("Invalid option selected. Please enter a valid option:");
		}
		
		TrainList.setTrainList(t);
		
	}
	
	public void display()
	{
		HashMap<Integer, Train> trainList = TrainList.getTrainList();
		
	    for (Map.Entry<Integer,Train> entry : trainList.entrySet()) {
	        Integer key = entry.getKey();
	        System.out.println("Train Number: "+key);
	        Train t = entry.getValue();
	        System.out.println("Starting City: "+t.getStartCity());
	        System.out.println("Destination City: "+t.getEndCity());
	        System.out.println("Train Ticket Price: "+ t.getTrainFare());
	        System.out.println("Total Number of seats: "+t.getNumberOfSeats());
	        System.out.print("Train Operating Days : ");
	        HashMap<String,Integer> h = t.getOperatingDays();
			for(String day : h.keySet())
			{
				if(h.get(day) == 1)
					System.out.print(day+" ");
			}
	        System.out.println();
			System.out.println();
	    }
	}
	
	public void adminMenu()
	{   String un;
        String pw;
	      System.out.print("Enter username of admin: ");
	     un=StdIn.readString();
	 
	        System.out.print("Enter password of admin: ");
            pw=StdIn.readString();
	if(un.equals("admin")&&pw.equals("1234")) {
		System.out.println("*******************************************");
		System.out.println("-Admin has Successfully Logged in-");
		mainloop:		
		while(true)
		{	
			System.out.println("*******************************************");
			System.out.println("Please select an option");
			System.out.println("1) Add Train");
			System.out.println("2) Update Train");
			System.out.println("3) Delete Train");
			System.out.println("4) Display Information of all the Trains");
			System.out.println("5) Return to Main Menu");
			int option = in.nextInt();
			in.nextLine();
			TrainAdmin NITK_IT_group = TrainAdmin.getTrainAdmin();
			switch(option)
			{
				case 1:
					System.out.println("-------------------------------------------");
					System.out.println("For Adding Train");
					System.out.println("-------------------------------------------");

					System.out.println("Please fill the train info");
					NITK_IT_group.addTrain();
					break;
				
				case 2:
					System.out.println("-------------------------------------------");
					System.out.println("For Updating Train Information");
					System.out.println("-------------------------------------------");
					System.out.println("Please enter the Train number to update the train's info");
					int n = in.nextInt();
					if(NITK_IT_group.trainExists(n))
						NITK_IT_group.updateTrain(n);
					else
						System.out.println("!!! Invalid train number entered !!!");
					break;
			
				case 3:
					System.out.println("-------------------------------------------");
					System.out.println("For Deleting Train");
					System.out.println("-------------------------------------------");

					System.out.println("Please enter the train number to delete the train from the Records");
					n = in.nextInt();
					if(NITK_IT_group.trainExists(n))
						NITK_IT_group.deleteTrain(n);
					else
						System.out.println("!!! Invalid train number entered !!!");
					break;
				
				case 4:
					System.out.println("-------------------------------------------");
					System.out.println("For Displaying all the trains");
					System.out.println("-------------------------------------------");
					System.out.println("Display");
					NITK_IT_group.display();
					break;

				case 5:
					System.out.println("-------------------------------------------");
					System.out.println("Returning to Main Menu");
					System.out.println("-------------------------------------------");
					break mainloop;			
				
				default:
					System.out.println("!!! Invalid option selected !!!");
			}
		}
	}
	else {
		System.out.println("Incorrect username or password");
	}
		
	} 

}
