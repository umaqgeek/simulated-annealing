package simulated.annealing;

import java.text.DecimalFormat;

public class SimulatedAnnealingMain {

    private static DecimalFormat DF = new DecimalFormat("#,##0.00");
    
    public static String MAIN_CITY = "MiTC Melaka";

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Object initCities[][] = {
            {"MiTC Melaka", 2.2714843f, 102.28636f},
            {"Zoo Melaka", 2.2778543f, 102.2991534f},
            {"Taman Botanikal Melaka", 2.280702f, 102.2984493f},
            {"Menara Taming Sari Melaka", 2.1909778f, 102.2450914f},
            {"Dataran Pahlawan Melaka", 2.1900741f, 102.2502719f},
            {"Asam Pedas Melaka", 2.1959882f, 102.2411785f},
            {"7e Taman Tasik Melaka", 2.2718076f, 102.2820603f},
            {"Hotel Kobemas Melaka", 2.2683585f, 102.2840123f},
            {"Jabatan Imigrisen Melaka", 2.26617f, 102.2949613f}
        };
        try {
            if (args[0] != null) {
                String fileName = args[0];
                Utility utility = new Utility();
                initCities = utility.getCitiesFromFile(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Arguments error! System will use default config and maps.");
        }
        
        MAIN_CITY = (String) initCities[0][0];

        //Create and add our cities
//        City city = new City("MiTC Melaka", 2.2714843f, 102.28636f);
//        City city2 = new City("Zoo Melaka", 2.2778543f, 102.2991534f);
//        City city3 = new City("Taman Botanikal Melaka", 2.280702f, 102.2984493f);
//        City city4 = new City("Menara Taming Sari Melaka", 2.1909778f, 102.2450914f);
//        City city5 = new City("Dataran Pahlawan Melaka", 2.1900741f, 102.2502719f);
//        City city6 = new City("Asam Pedas Melaka", 2.1959882f, 102.2411785f);
//        City city7 = new City("7e Taman Tasik Melaka", 2.2718076f, 102.2820603f);
//        City city8 = new City("Hotel Kobemas Melaka", 2.2683585f, 102.2840123f);
//        City city9 = new City("Jabatan Imigrisen Melaka", 2.26617f, 102.2949613f);

//        TourManager.addCity(city);
//        TourManager.addCity(city2);
//        TourManager.addCity(city3);
//        TourManager.addCity(city4);
//        TourManager.addCity(city5);
//        TourManager.addCity(city6);
//        TourManager.addCity(city7);
//        TourManager.addCity(city8);
//        TourManager.addCity(city9);
//        TourManager.addCity(city);
        
        for (int indexTour = 0; indexTour < initCities.length; indexTour++) {
            TourManager.addCity(new City((String) initCities[indexTour][0], 
                    (float) initCities[indexTour][1], 
                    (float) initCities[indexTour][2]));
        }
        TourManager.addCity(new City((String) initCities[0][0],
                (float) initCities[0][1],
                (float) initCities[0][2]));

        //Set initial temp
        double temp = 100000;

        //Set Cooling rate
        double coolingRate = 0.003;

        //Create random initial solution
        Tour currentSolution = new Tour();
        currentSolution.generateIndividual();

        System.out.println("Total distance of initial solution: " + DF.format(currentSolution.getTotalDistance()) + " km");
        System.out.println("Tour: " + currentSolution);

		//We would like to keep track of the best solution
        //Assume best solution is the current solution
        Tour best = new Tour(currentSolution.getTour());
        int loop = 1;
        //Loop until system is cool
        while (temp > 1) {
            //create new neighbour tour
            Tour newSolution = new Tour(currentSolution.getTour());

            //Get a random positions in the tour
            int tourPosition1 = Utility.randomInt(1, newSolution.tourSize() - 1);
            int tourPosition2 = Utility.randomInt(1, newSolution.tourSize() - 1);

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
            if (Utility.acceptanceProbability(currentDistance, neighbourDistance, temp) > rand) {
                currentSolution = new Tour(newSolution.getTour());
            }

            //Keep track of the best solution found
            if (currentSolution.getTotalDistance() < best.getTotalDistance()) {
                best = new Tour(currentSolution.getTour());
            }

            //Cool the system
            temp *= 1 - coolingRate;
            loop++;
        }
        System.out.println("loop: " + loop);
        System.out.println("Final solution distance: " + DF.format(best.getTotalDistance()) + " km");
        System.out.println("Tour: " + best);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime + " ms");
    }
}
