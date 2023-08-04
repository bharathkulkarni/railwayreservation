
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TrainEnquiry
{  int temp=0;
	static Scanner in = new Scanner(System.in);
	HashMap<Integer,Train> enquire() throws Exception
	{
		HashMap<Integer,Train> trainsAvailable = new HashMap<Integer,Train>();
		
		System.out.println("*************************************");

		System.out.print("1)Enter Start City: ");
		String startCity = in.nextLine();
		System.out.print("2)Enter Destination City: ");
		String endCity = in.nextLine();
		System.out.print("3)Enter travel date (dd/mm/yyyy): ");
		String dateString = in.nextLine();
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String day = sdf.format(date);
		day = day.toUpperCase();
		HashMap<Integer, Train> trainDetails = TrainList.getTrainList();
		for(Map.Entry<Integer, Train> entry : trainDetails.entrySet())
		{
			Train t = entry.getValue();
			String t_startCity = t.getStartCity();
			String t_endCity = t.getEndCity();
			HashMap<String, Integer> operatingDays = t.getOperatingDays();
			if(startCity.equals(t_startCity) && endCity.equals(t_endCity))
			{
				if(operatingDays.get(day) == 1) 
				{  temp=1;
					System.out.println("*******************************************");
					System.out.println("Train number: " + t.getTrainNumber());
					System.out.println("Train start city: " + t_startCity);
					System.out.println("Train end city: " + t_endCity);
					System.out.println("Train fare: " + t.getTrainFare());
					System.out.println("Number of seats available: " + t.getNumberOfSeats());
					trainsAvailable.put(t.getTrainNumber(),t);
				}
				
				
			}
			
		}
		if(temp==0) {
			System.out.println("NO TRAINS FOUND");
		}
		
		return trainsAvailable;
	}
}