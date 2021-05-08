// Class: DIT/FT/1B/03
// Admission no: 2026453
// Name: Phan Kiah Fong Nicholas
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPRG;

import java.io.Serializable;

/**
 *
 * @author Nicholas
 */
public class Event implements Serializable{

    // Variables initialisation
    private String name, organizer, date;
    private int time, limit;
    private double fees;

    // Create an empty constructor with no parameters
    public Event() {
    }

    ; 
    
    // Create a constructor with parameters
    public Event(String name, String organizer, String date, int time, double fees) {
        this.name = name;
        this.organizer = organizer;
        this.date = date;
        this.time = time;
        this.fees = fees;
//        this.limit = limit;
    }

    // Gets the name
    public String getName() {
        return name;
    }

    // Gets the organizer
    public String getOrganizer() {
        return organizer;
    }

    // Gets the date
    public String getDate() {
        return date;
    }

    // Gets the time
    public int getTime() {
        return time;
    }

    // Gets the fees
    public double getFees() {
        return fees;
    }

    // Gets the limit
    public int getLimit() {
        return limit;
    }

    // Sets the name to the param's received
    public void setName(String name) {
        this.name = name;
    }

    // Sets the organizer to the param's received
    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    // Sets the date to the param's received
    public void setDate(String date) {
        this.date = date;
    }

    // Sets the time to the param's received
    public void setTime(int time) {
        this.time = time;
    }

    // Sets the fees to the param's received
    public void setFees(double fees) {
        this.fees = fees;
    }

    // Sets the limit to the param's received
    public void setLimit(int limit) {
        this.limit = limit;
    }
    
    public String displayMessage() {
        return "This is an event.";
    }
    
}
