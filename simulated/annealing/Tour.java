package simulated.annealing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

/*
 * Tour.java
 * Models a travelling salesman tour
 * Stores a candidate tour through all cities
 */

public class Tour {

	//to hold tour of cities
	//keep our list of cities to visit
	private ArrayList<City> tour = new ArrayList<City>();

	//Assume initial value of distance is 0
	private double distance = 0.00;

	//Constructor
	//starts an empty tour
	public Tour(){
		for (int i = 0; i < TourManager.numberOfCities(); i++){
			tour.add(null);

		}
	}

	//another Constructor
	//starts a tour from another tour
	//copy list of cities that we created
	@SuppressWarnings("unchecked")
	public Tour(ArrayList<City>tour){
		this.tour = (ArrayList<City>) tour.clone();

	}

	/*
	 * Return tour information
	 * @return currentTour @list of cities
	 */

	public ArrayList<City> getTour(){
		return tour;
	}

	/*
	 * Create random tour (ie. individual or candidate solution)
	 *
	 */
	public void generateIndividual(){
		//Loop thorough all our destination cities and add them to our tour
		for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++){
			setCity(cityIndex, TourManager.getCity(cityIndex));
		}
		//Randomly reorder the tour
		Collections.shuffle(tour);

		// search first city ("MiTC Melaka")
		int holdIndex = 0;
		for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++){
			if (getCity(cityIndex).getCityName().contains(SimulatedAnnealingMain.MAIN_CITY)) {
				holdIndex = cityIndex;
				break;
			}
		}
		// swap found index with first city
		City cityTemp = getCity(holdIndex);
		setCity(holdIndex, getCity(0));
		setCity(0, cityTemp);
		// search last city ("MiTC Melaka")
		holdIndex = 1;
		for (int cityIndex = 1; cityIndex < TourManager.numberOfCities(); cityIndex++){
			if (getCity(cityIndex).getCityName().contains(SimulatedAnnealingMain.MAIN_CITY)) {
				holdIndex = cityIndex;
				break;
			}
		}
		// swap found index with last city
		City cityTemp2 = getCity(holdIndex);
		setCity(holdIndex, getCity(TourManager.numberOfCities()-1));
		setCity(TourManager.numberOfCities()-1, cityTemp2);

	}

	/*
	 * Return a city from the tour given the city's index
	 * @param index
	 * @return City at the index
	 */
	public City getCity(int index) {
		return tour.get(index);
	}

	/*
	 * Set a city in a certain position within tour
	 * @param index
	 * @param city
	 */
	public void setCity(int index, City city){
		tour.set(index, city );
		//If the tour has been altered we need to reset the fitness and distance
		distance = 0;
	}

	/*
	 * Computes and returns the total distance of the tour
	 * @return distance total distance of the tour
	 */
	public double getTotalDistance(){
		if (distance == 0){
			double tourDistance = 0;
			//Loop through our tour's cities
			for (int cityIndex=0; cityIndex < tourSize(); cityIndex++){
				//Get city that we are travelling from
				City fromCity = getCity(cityIndex);

				//City that we are travelling to
				City destinationCity;

				//check that we are not on our tour's last city, if we are set our
				//tour's final destination city to our starting city
				if(cityIndex+1 < tourSize()){
					destinationCity = getCity(cityIndex+1);
				}
				else{
					destinationCity = getCity(0);

				}
				//Get the distance between the two cities
				tourDistance += Utility.distance(fromCity, destinationCity);
				}
				distance = tourDistance;
			}
			return distance;
		}
		/*
		 * Get number of cities on our tour
		 * @return number how many cities there are in the tour
		 */
		public int tourSize(){
			return tour.size();
		}

		@Override
		/*
		 * To print out a list of all the cities in the tour
		 */
		public String toString(){
			String s  = "\n- City #1: " + getCity(0).getCityName();
			for (int i =1; i < tourSize(); i++){
				s += "\n- City #"+(i+1)+": " + getCity(i).getCityName();
			}
			return s;
		}
}
