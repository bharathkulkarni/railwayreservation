
import java.util.HashMap;
import java.util.Map;

class Train {  
	static final Map<Integer,String> convertToDay; 
	static {
		convertToDay = Map.of(1, "SUNDAY", 2, "MONDAY", 3, "TUESDAY", 4, "WEDNESDAY", 5, "THURSDAY", 6, "FRIDAY", 7, "SATURDAY");
    }
	
	private String startCity;
	private String endCity;
	private HashMap<String,Integer> operatingDays;
	private Double trainFare;
	private static int trainNumberGenerator = -1;
	private int trainNumber;
	private int numberOfSeats;
	
	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	protected void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
		
	public String getStartCity() {
		return startCity;
	}
	
	protected void setStartCity(String startCity) {
		this.startCity = startCity.toLowerCase();
	}
	
	public HashMap<String, Integer> getOperatingDays() {
		return operatingDays;
	}
	
	protected void setOperatingDays(int Days) {
		
		operatingDays = new HashMap<String,Integer>();
		
		int temp = Days;
		while(temp!=0)
		{
			int rem = temp%10;
			String s = convertToDay.get(rem);
			operatingDays.put(s, 1);
			temp = temp/10;
		}
	}
	
	public String getEndCity() {
		return endCity.toLowerCase();
	}
	
	protected void setEndCity(String endCity) {
		this.endCity = endCity.toLowerCase();
	}
	
	public Double getTrainFare() {
		return trainFare;
	}
	
	protected void setTrainFare(Double trainFare) {
		this.trainFare = trainFare;
	}
	
	public int getTrainNumber(){
		return trainNumber;
	}
	
	Train(){
		trainNumberGenerator++;
		trainNumber = trainNumberGenerator;
	}
	public Train(String start,String end,double price,int days,int seats){
		startCity=start;
		endCity=end;
		trainFare=price;
		setOperatingDays(days);
		numberOfSeats=seats;
		trainNumberGenerator++;
		trainNumber = trainNumberGenerator;
	}
	
}
