package com.ontariotechu.sofe3980U;
import java.lang.*;
import java.util.ArrayList;

public class Ticket {
    private String fname;
    private String lname;
    private ArrayList<String> airportPath = new ArrayList<>(); 
    private boolean isDirect;
    private boolean isRoundTrip;
    private String returnDate; //it cna be string but we have to incorperate the 24hr and 12 hr clock
    private String departureDate; //same thing as above
    private boolean isMilitaryTime;
    private Airport destination;
    private Airport origin;
    private String flightTime;
    private String time;


    // This method has 2 paramaters: Origin, Destination
    // bookTrip will book a trip from the users selected origin
    // to the users selected destination and allow the use to proceed
    String bookTrip(Airport origin, Airport destination){
        //check if the origin and desitnation are the same
        if(origin.airportName.equals(destination.airportName)){
            return "invalid";
        }

        this.origin = origin;
        this.destination = destination;
        return "valid";
    }



    public String createTicket(String fname, String lname, String departureDate, boolean isRoundTrip, boolean isDirect){
        this.fname = fname;
        this.lname = lname;
        this.departureDate = departureDate;
        this.isRoundTrip = isRoundTrip;
        this.isDirect = isDirect;

        String ticketValidation = "";

        if(isRoundTrip && isDirect){
            ticketValidation = "Round and Direct trip";
        }else if(!isRoundTrip && isDirect){
            ticketValidation = "One-Way and Direct trip";
        }else if(isRoundTrip && !isDirect){
            ticketValidation = "Round and Indirect trip";
        }else if(!isRoundTrip && !isDirect){
            ticketValidation = "One-Way and Indirect trip";
        }

        return ticketValidation;
    }


    //this method will create the airport path taken by the user who created this ticket
    void createAirportPath(ArrayList<String> airportsSelected ){
        if(this.isRoundTrip){
            this.airportPath = airportsSelected;

            //if it is a round trip we must add this to the path
            for(int i = airportsSelected.size() - 1; i >= 0 ;i--){
                this.airportPath.add(airportsSelected.get(i));
            }
        }else{ //not a round trip
            this.airportPath = airportsSelected;
        }

        for(String i: airportPath){
            System.out.println(i);
        }
    }

    // this method will take isMilitaryTime as a paramater
    // It will set the ticket to have a 24 hour or 12 hour display
    //this func assumes that time is formatted as hh:mm:ss AM/PM
    static String TimeConversion(boolean isMilitaryTime, String time){  //is the user setting time or are we???
        if(isMilitaryTime==true){
            String[] parts = time.split(":");
            int hour = Integer.parseInt(parts[0]);
            String mins = parts[1].substring(0, 2);
            if (time.contains("AM") && hour == 12) {hour = 0;}
            if (time.contains("PM") && hour != 12) {hour += 12;}
            String militaryHour = String.format("%02d", hour);
            return militaryHour + ":" + mins;
        }
        else{
            return time;
        }
    }

    //this method will return the arrayList
    public ArrayList<String> getFlightPath(){
        return this.airportPath;
    }

    //public set date(){}

    //this method will return the flight time for this ticket
    public void setFlightTime(int time){

        int mins = time % 60;
        int hours = 0;

        if(mins != time){
            hours = (time - mins)/60;
        } 

        this.flightTime = hours + "Hour(s) " + mins + "Minutes";
    }

    public String getTime() {
        return this.time;
    }
    public String getFname() {
        return this.fname;
    }
    public String getLname() {
        return this.lname;
    }
    public Airport getOrigin() {
        return this.origin;
    }
    public Airport getDestination() {
        return this.destination;
    }
    public String getDepartureDate() {
        return this.departureDate;
    }
    public boolean isRoundTrip() {
        return this.isRoundTrip;
    }
    public String getFlightTime(){
        return this.flightTime;
    }
    public ArrayList<String> getAirportPath() {
        return airportPath;
    }

}
