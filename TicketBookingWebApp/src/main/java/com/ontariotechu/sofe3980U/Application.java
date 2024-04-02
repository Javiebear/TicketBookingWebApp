package com.ontariotechu.sofe3980U;

import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application{
    public static Airport[] airports;

    public static void main(String[] args) {

    //creating the airport objects
    Airport airJFK = new Airport("JFK"); //KNICKS
    Airport airDEN = new Airport("DEN"); //NUGGETS
    Airport airYYZ = new Airport("YYZ"); //RAPTORS
    Airport airLAX = new Airport("LAX"); //LAKERS

    //all the airport objects that exist
    airports = new Airport[]{ airJFK, airDEN, airYYZ, airLAX };


    //initializing the array of Exising airports
    ArrayList<String> existingAirports = new ArrayList<>();

    //iterates through all the airports
    for(int i = 0; i < airports.length; i++){

        //all the existing airports
        existingAirports.add("JFK");
        existingAirports.add("DEN");
        existingAirports.add("YYZ");
        existingAirports.add("LAX");

        //remove the airport we are currently adding direct paths from the arraylist so we dont have any
        //cyclic paths
        existingAirports.remove(i);

        int numberOfAirports = ((int) (Math.random() * existingAirports.size() - 1) + 2); // 2 - 3
        //iterates for the amount of direct paths this airport will have
        for(int j = 1; j <= numberOfAirports; j++){

            int randomAirport = (int) (Math.random() * existingAirports.size()); // 0 - 3

            //selecting the airport and adding a direct paths to that airport
            airports[i].addDirFlight(existingAirports.get(randomAirport));
            existingAirports.remove(randomAirport);

        }
        //reseting the array list
        existingAirports.clear();
    }
        //for each airport, we must set the flight time to others
        airJFK.setAirportDistance("YYZ", 115 ); //fight time in minutes
        airJFK.setAirportDistance("DEN", 280 ); //fight time in minutes
        airJFK.setAirportDistance("LAX", 385 ); //fight time in minutes
        airJFK.setAirportDistance("JFK", 0 ); //fight time in minutes


        airDEN.setAirportDistance("YYZ", 190 ); //fight time in minutes
        airDEN.setAirportDistance("JFK", 225 ); //fight time in minutes
        airDEN.setAirportDistance("LAX", 155 ); //fight time in minutes
        airDEN.setAirportDistance("DEN", 0 ); //fight time in minutes


        airYYZ.setAirportDistance("JFK", 105 ); //fight time in minutes
        airYYZ.setAirportDistance("DEN", 235 ); //fight time in minutes
        airYYZ.setAirportDistance("LAX", 325 ); //fight time in minutes
        airYYZ.setAirportDistance("YYZ", 0 ); //fight time in minutes


        airLAX.setAirportDistance("YYZ", 280 ); //fight time in minutes
        airLAX.setAirportDistance("DEN", 140 ); //fight time in minutes
        airLAX.setAirportDistance("JFK", 325 ); //fight time in minutes
        airLAX.setAirportDistance("LAX", 0 ); //fight time in minutes
    /*
    //all the existing airports
    existingAirports.add("JFK");
    existingAirports.add("DEN");
    existingAirports.add("YYZ");
    existingAirports.add("LAX");

    //printing out the direct paths
    System.out.println(airJFK.getDirectFlights());
    System.out.println(airDEN.getDirectFlights());
    System.out.println(airYYZ.getDirectFlights());
    System.out.println(airLAX.getDirectFlights());

    //must find multi stop paths for airports that dont have a direct flights to certain airports
    airJFK.generateMultiFlights(airDEN, airLAX, airYYZ, existingAirports);
    airDEN.generateMultiFlights(airJFK, airLAX, airYYZ, existingAirports);
    airYYZ.generateMultiFlights(airDEN, airLAX, airJFK, existingAirports);
    airLAX.generateMultiFlights(airDEN, airJFK, airYYZ, existingAirports);

    existingAirports.clear(); //freeing up space

    //printing out the multistop paths
    System.out.println(airJFK.getMultiFlights());
    System.out.println(airDEN.getMultiFlights());
    System.out.println(airYYZ.getMultiFlights());
    System.out.println(airLAX.getMultiFlights());

     */
        //printing out the direct paths
        System.out.println(airJFK.getDirectFlights());
        System.out.println(airDEN.getDirectFlights());
        System.out.println(airYYZ.getDirectFlights());
        System.out.println(airLAX.getDirectFlights());

    SpringApplication.run(Application.class, args);

    //USER INPUT AFTER CREATING ALL THE AIRPORT STUFF for testing ------------------------------
    //{ airJFK, airDEN, airYYZ, airLAX }

    Scanner input = new Scanner(System.in);

    System.out.println("HELLO AND WELCOME TO OUR FLIGHT CENTER\nPLZ SELECT AN AIRPORT UR COMIN FROM");
    System.out.print("1 - JFK\n2 - DEN\n3 - YYZ\n4 - LAX\nInput: ");
    int originIndex = input.nextInt() - 1;

    System.out.println("PLZ SELECT AN AIRPORT UR FLYING TO");
    System.out.print("1 - JFK\n2 - DEN\n3 - YYZ\n4 - LAX\nInput: ");
    int desitnationIndex = input.nextInt() - 1;
    System.out.println();

    //setting the origin and deistination of this user
    Airport origin = airports[originIndex];
    Airport desitination = airports[desitnationIndex];

    //ask the user if they want a roundtrip
    System.out.println("IS IT A ROUNDTRIP?");
    System.out.print("1 - yes\n2 - no\nInput: ");
    int roundTripChoice = input.nextInt();
    System.out.println();

    boolean roundTrip = false;

    if(roundTripChoice == 1){
        roundTrip = true;
        //what time for return and departure
    }else{
        roundTrip = false;
        //departure time 
    }

    Ticket newTicket = new Ticket();

    //booking the trip
    newTicket.bookTrip(origin, desitination);

    //find out if there is only direct or multistop
    boolean dirPathExists = origin.checkDirectFlight(desitination);

    //initializing the arraylist to create the ticket path
    ArrayList<String> airportSelected = new ArrayList<>();
    airportSelected.add(origin.airportName);
    
    if(!dirPathExists){ //there are no direct flights
        newTicket.createTicket("fname", "lname", "departureDate", roundTrip,  false);
        System.out.println("There are no direct paths to your destination.");
        
        ArrayList<String> originMulti = origin.returnMultiFlight();

        //finding which option the user selected
        for(int i = 0; i < originMulti.size(); i++){
            System.out.println( (i+1) + " - " + origin.airportName + "-->" + originMulti.get(i));
        }

        System.out.println("Please select an alternative multstop flight");

        int multiFlightChoice = input.nextInt() - 1;

        String flightString = originMulti.get(multiFlightChoice);

        //spliting the string
        String[] tmpPath = flightString.split("-->");

        //adding to the arraylist
        for (String airport : tmpPath) {
            airportSelected.add(airport);
        }

        newTicket.createAirportPath(airportSelected);

    }else{ //this is if there is a direct flight
        newTicket.createTicket("fname", "lname", "departureDate", roundTrip,  true);

        airportSelected.add(desitination.airportName);

        newTicket.createAirportPath(airportSelected);
    }

    System.out.println("THE FLIGHT TIME: " + origin.getFlightTime(newTicket.getFlightPath(), airports));
   //System.out.println("***TESTING*** 24 Hour Format:"+ Ticket.TimeConversion(true,"8:21 PM"));
    //System.out.println("***TESTING*** 12 Hour Format:"+ Ticket.TimeConversion(false,"8:21 PM"));
    newTicket.setFlightTime(origin.getFlightTime(newTicket.getFlightPath(), airports));

    System.out.println(newTicket.getFlightTime());


    airportSelected.clear(); //freeing up space

}


}
