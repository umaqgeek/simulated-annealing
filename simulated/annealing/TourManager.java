package simulated.annealing;

import java.util.ArrayList;

/*
 * TourManager.java
 * Holds and keeps track of the cities of a tour
 */

public class TourManager {
	
	//Holds our cities
	private static ArrayList<City>destinationCities = new ArrayList<City>();
	
	/**
	 * Adds a destination city
	 * @param city
	 */
	public static void addCity(City city){
		destinationCities.add(city);
	}
	
	/**
	 * returns a city given its index
	 * @param index
	 * @return city the city at index
	 */
	public static City getCity(int index){
		return(City)destinationCities.get(index);
		
	}
	/**
	 * Return the number of destination cities
	 * @return size the number of destination cities
	 */
	public static int numberOfCities(){
		return destinationCities.size();
	}
	
	
	
	
	

}
