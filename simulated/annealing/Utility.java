package simulated.annealing;

import java.util.Random;

public class Utility {

	/*
	 * Computes and return the Euclidean distance between two cities
	 * @param city1 the first city
	 * @param city2 the second city
	 * @return distance the dist between city1 and city2
	 */
	public static double distance(City city1, City city2){
		// float xDistance = Math.abs(city1.getX() - city2.getX());
		// float yDistance = Math.abs(city1.getY() - city2.getY());
		// double distance = Math.sqrt((xDistance*xDistance)+(yDistance*yDistance));

		double distance = 0.00;
		double 	lon1 = city1.getX(),
						lat1 = city1.getY(),
            lon2 = city2.getX(),
            lat2 = city2.getY();

  	// double R = 6373.00;
    // double dlon = lon2 - lon1;
    // double dlat = lat2 - lat1;
		// double aTemp = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon/2);
    // double a = Math.pow(aTemp, 2);
    // double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
    // distance = R * c;

		double lati1 = lat1 / (180 / Math.PI);
    double lati2 = lat2 / (180 / Math.PI);
    double long1 = lon1 / (180 / Math.PI);
    double long2 = lon2 / (180 / Math.PI);
    double miles = 3963.0 * Math.acos( (Math.sin(lati1) * Math.sin(lati2)) + Math.cos(lati1) * Math.cos(lati2) * Math.cos(long2 - long1) );
    distance = miles * 1.609344;

		return distance;
	}

	/**
	 * calculate the acceptance probability
	 * @param currentDistance the total distance of the current tour
	 * @param newDistance the total distance of the new tour
	 * @param temperature the current temperature
	 * @return value the probability of whether to accept the new tour
	 */
	public static double acceptanceProbability(double currentDistance, double newDistance, double temperature){
		//if the new solution is better, accept it
		if (newDistance < currentDistance){
			return 1.0;
		}
		//If the new solution is worst, calculate an acceptance probability
		return Math.exp((currentDistance - newDistance) / temperature);

	}

	/*
	 * This method returns a random number n such that
	 * 0.0 <= n <= 1.0
	 * @return random such that 0.0 <= random <= 1.0
	 */
	static double randomDouble()
	{
		Random r = new Random();
		return r.nextInt(1000)/ 1000.0;
	}

	/*
	 * return a random int value within a given range
	 * min inclusive... max  not inclusive
	 * @param min the minimum value of the required range(int)
	 * @param max the maximum value of the required range(int)
	 * @return rand a random int value between min and max (min,max)
	 */
	public static int randomInt(int min, int max){
		Random r = new Random();
		double d = min + r.nextDouble() * (max-min);
		return(int)d;
	}


}
