package sample.controllers;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Time;

public class Passenger {
    private int passengerNumber;
    private String firstName;
    private String lastName;
    private int passportNumber;
    private String flightNumber;
    private Date departureDate;
    private Time departureTime;
    private String Seat;


    public Passenger(int passengerNumber, String firstName, String lastName, int passportNumber, String flightNumber, Date departureDate, Time departureTime, String Seat){
        this.passengerNumber = passengerNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.Seat = Seat;

    }


    public int getPassengerNumber(){ return this.passengerNumber; }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){return this.lastName;}

    public int getPassportNumber(){
        return this.passportNumber;
    }

    public String getFlightNumber(){
        return this.flightNumber;
    }

    public Date getDepartureDate(){
        return this.departureDate;
    }

    public Time getDepartureTime(){
        return this.departureTime;
    }

    public String getSeat(){
        return this.Seat;
    }

    }
