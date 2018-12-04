package simulated.annealing;

import java.text.DecimalFormat;

public class SimulatedAnnealingMain {

	private static DecimalFormat DF = new DecimalFormat("#,##0.00");

	public static void main(String[] args){

		long startTime = System.currentTimeMillis();

		//Create and add our cities
		City city = new City("MiTC Melaka", 2.2714843f, 102.28636f);
		TourManager.addCity(city);
		City city2 = new City("Zoo Melaka", 2.2778543f, 102.2991534f);
		TourManager.addCity(city2);
		City city3 = new City("Taman Botanikal Melaka", 2.280702f, 102.2984493f);
		TourManager.addCity(city3);
		City city4 = new City("Menara Taming Sari Melaka", 2.1909778f, 102.2450914f);
		TourManager.addCity(city4);
		City city5 = new City("Dataran Pahlawan Melaka", 2.1900741f, 102.2502719f);
		TourManager.addCity(city5);
		City city6 = new City("Asam Pedas Melaka", 2.1959882f, 102.2411785f);
		TourManager.addCity(city6);
		City city7 = new City("7e Taman Tasik Melaka", 2.2718076f, 102.2820603f);
		TourManager.addCity(city7);
		City city8 = new City("Hotel Kobemas Melaka", 2.2683585f, 102.2840123f);
		TourManager.addCity(city8);
		City city9 = new City("Jabatan Imigrisen Melaka", 2.26617f, 102.2949613f);
		TourManager.addCity(city9);
		// City city10 = new City("Klang",222,345);
		// TourManager.addCity(city10);
		// City city11 = new City("Kota Bharu",211,100);
		// TourManager.addCity(city11);
		// City city12 = new City("Sandakan",333,444);
		// TourManager.addCity(city12);
		TourManager.addCity(city);


		//Set initial temp
		double temp = 100000;

		//Set Cooling rate
		double coolingRate = 0.003;

		//Create random initial solution
		Tour currentSolution = new Tour();
		currentSolution.generateIndividual();

		System.out.println("Total distance of initial solution: "+ DF.format(currentSolution.getTotalDistance()) + " km");
		System.out.println("Tour: " + currentSolution);

		//We would like to keep track of the best solution
		//Assume best solution is the current solution
		Tour best = new Tour(currentSolution.getTour());
int loop=1;
		//Loop until system is cool
		while (temp > 1){
			//create new neighbour tour
			Tour newSolution = new Tour (currentSolution.getTour());

			//Get a random positions in the tour
			int tourPosition1 = Utility.randomInt(1, newSolution.tourSize()-1);
			int tourPosition2 = Utility.randomInt(1, newSolution.tourSize()-1);

			// LOOK ABOVE :kne ade loop to check supaya dua dua tour position ni x sama

			//Get the cities at selected position in the tour array
			City citySwap1 = newSolution.getCity(tourPosition1);
			City citySwap2 = newSolution.getCity(tourPosition2);

			//Swap the cities
			newSolution.setCity(tourPosition2, citySwap1);
			newSolution.setCity(tourPosition1, citySwap2);

			//Get energy of solutions
			double currentDistance = currentSolution.getTotalDistance();
			double neighbourDistance = newSolution.getTotalDistance();

			//Decide if we should accept the neighbour or not
			double rand = Utility.randomDouble();
			if (Utility.acceptanceProbability(currentDistance, neighbourDistance, temp) > rand){
				currentSolution = new Tour(newSolution.getTour());
			}

			//Keep track of the best solution found
			if (currentSolution.getTotalDistance() < best.getTotalDistance()){
				best = new Tour(currentSolution.getTour());
			}

			//Cool the system
			temp *= 1 - coolingRate;
loop++;
		}
		System.out.println("loop: "+loop);
		System.out.println("Final solution distance: " + DF.format(best.getTotalDistance()) + " km");
		System.out.println("Tour: " +best);

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
    System.out.println(elapsedTime + " ms");
	}
}
