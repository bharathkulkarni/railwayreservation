

import java.util.*;

public class Menu
{   static HashMap<Integer,String> map=new HashMap<>();
	
	public static void main(String args[]) throws Exception
	{
		TrainAdmin ta = TrainAdmin.getTrainAdmin();
		UserSystem us = new UserSystem();
		
		while(true)
		{
			
			map.put(1,"User System");
			map.put(2,"Admin System");
			map.put(3,"Exit");
			disp(map.size(), map);
		
			int firstChoice = Integer.parseInt(StdIn.readLine());
			if(firstChoice == 1)
				us.disp();
			else if(firstChoice == 2)
				ta.adminMenu();
			else
				break;
		}		
	}
	
	static void disp(int nargs, HashMap<Integer,String> map)
	{
		System.out.println("*******************************************");
		for(int i = 1; i<=nargs; i++)
		{
			System.out.println(i+ ") " + map.get(i));
			
		}
		System.out.print("Enter your choice: ");
	}
}

