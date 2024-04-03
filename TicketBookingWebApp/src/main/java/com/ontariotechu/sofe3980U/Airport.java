package com.ontariotechu.sofe3980U;
import java.util.*;


public class Airport {
    String airportName;
    public Map<String, Integer> airportDist = new HashMap<String, Integer>();
    public ArrayList<String> directFlight = new ArrayList<>();
    public ArrayList<String> multiFlight = new ArrayList<>();

    //constructor for Airport class
    public Airport(String airportName){
        this.airportName = airportName;
    }

    //This method will add the direct flights for the airport
    public void addDirFlight(String airportName){
        this.directFlight.add(airportName);
    }

    //This method will add a key and a value to the associative array
    public void setAirportDistance(String airport, int flightTime){
        this.airportDist.put(airport, flightTime);
    }

    //this method will find the missing airports that this airport does not fly to
    //then it will generate multiflights from the direct flights
    public void generateMultiFlights(Airport air1, Airport air2, Airport air3, ArrayList<String> airports){

        Airport[] airportArr = { air1, air2, air3 };
        boolean directPath = false;


        //if there are less direct flights than there are airports, we must generate multi stop flights
        if(this.directFlight.size() != (airportArr.length)){

            for(Airport airport: airportArr){
                for(String i : this.directFlight){ //iterates through each airport in the array
                    if(airport.airportName.equals(i)){ //there is no direct path to this airport
                        directPath = true;
                    }
                }

                //now we know which airport is missing
                if(!directPath){
                    String missingAir = airport.airportName;

                    //we must create a multistop flight to this airport by looking through other airports
                    for(Airport air : airportArr){
                        for(String i : air.directFlight){
                            //we iterate through each airport this airport directly flies to
                            //if the airport missing exists here then we add it to the multistop flight arraylist
                            if(i.equals(missingAir)){
                                //adding the multistop flights
                                multiFlight.add(air.airportName + "-->" + i);
                            }
                        }
                    }
                }

                directPath = false;

            }
        }
    }

    public String getAirportName() {
        return this.airportName;
    }
    //this method will get and return the flight time with the given array
    public int getFlightTime(ArrayList<String> flightPath, Airport[] airports){
        int flightTime = 0;
        Airport currentAiport = this;

        for (int i = 0; i < flightPath.size(); i++) {

            //get the name of the airport from the array
            //then aquire the time from the associative array
            for (String j : currentAiport.getAirportDistance().keySet()) {
                if (flightPath.get(i).equals(j)) {
                    flightTime = flightTime + currentAiport.getAirportDistance().get(j);
                }
            }

            //changing the airport we are looking at
            for(Airport airport: airports){
                if(airport.airportName.equals(flightPath.get(i))){
                    currentAiport = airport;
                }
            }

        }

        return flightTime;
    }

    //this method will find out if the airport has a direct flight to this destination
    public boolean checkDirectFlight(Airport destination){
        boolean dirPathExists = false;

        //checking if an airport in the direct flight array matches the destination
        for(String airport: this.directFlight){
            if(airport.equals(destination.airportName)){
                dirPathExists = true;
            }
        }

        return dirPathExists;
    }

    //this method will return all the direct flights within this airport
    public String getDirectFlights(){
        String directFlightsString = "";

        for(String i : this.directFlight){
            directFlightsString = directFlightsString + airportName + "-->" + i + "\n";
        }

        return directFlightsString;
    }
    public List<String> getDirectFlight() {
        return directFlight;
    }
    //this method will return all the direct flights within this airport
    public String getMultiFlights(){
        String multiFlightsString = "";

        for(String i : this.multiFlight){
            multiFlightsString = multiFlightsString + airportName + "-->" + i + "\n";
        }

        return multiFlightsString;
    }

    //method to ge the multiflight array
    public ArrayList<String> returnMultiFlight(){
        return this.multiFlight;
    }

    //method to get the airport flight time array
    public Map<String, Integer> getAirportDistance(){
        return this.airportDist;
    }

}