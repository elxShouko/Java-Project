// Class: DIT/FT/1B/03
// Admission no: 2026453
// Name: Phan Kiah Fong Nicholas
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPRG;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

/**
 *
 * @author Nicholas
 */
public class EventManagement {

    // Variables initialisation
    int totalFees = 0;
    String message = "";
//    Event[] events = { // Create an instance of the events class array
//        new F2FEvents("Job Fair", "NTU Club", "01-Dec-2020", 2300, 10.90, 20),
//        new F2FEvents("Dancing", "SP Club", "01-Jan-2021", 2200, 69.00, 15),
//        new OnlineEvents("Singing", "NUS Club", "15-Jan-2021", 2000, 50.00, 0)
//    };

    private ReadAndWrite fileReader = new ReadAndWrite();
    Event[] events = fileReader.loadEvent();
//    serialize = ReadAndWrite.serializeEvent();

    public EventManagement() { // Create a constructor with no parameters
    }

    public String displayEvent() { // method for displaying events
        String message;
        int counter = 1;
        message = "S/N     Name         Organizer          Date\\Time               Fees($)\n\n";
        for (int i = 0; i < events.length; i++) {
            message += counter + ".       " + events[i].getName() + "        " + events[i].getOrganizer() + "        " + events[i].getDate() + "              " + events[i].getFees() + "\n";
            counter++;
        }
        return message;
    }

    public String userMenu() { // user menu method
        String secondInp;
        secondInp = JOptionPane.showInputDialog(null, "Enter your option(User):\n\n1. Display all events\n2. Search Event(by Event Name)\n3. Search Event (by Event fees)\n4. Register Event\n 5. Exit\n\n",
                "Event", JOptionPane.QUESTION_MESSAGE);
        return secondInp;
    }

    public String adminMenu() { // admin user method
        String secondInp;
        secondInp = JOptionPane.showInputDialog(null, "Enter your option(Admin):\n\n1. Display all events\n2. Add Event\n3. Delete Event\n4. Exit\n",
                "Event", JOptionPane.QUESTION_MESSAGE);
        return secondInp;
    }

    public String addEvent(String eventName, String newOrganizer, String newDNT, int newTime, double newDoubleFees, boolean type, int limit) { // method to add an event
        Event[] newEvent = new Event[events.length + 1];

        for (int i = 0; i < events.length; i++) {
            newEvent[i] = events[i];
        }

        if (type == true) {
            limit = 0;
            newEvent[events.length] = new OnlineEvents(eventName, newOrganizer, newDNT, newTime, newDoubleFees, limit);
        } else {

            newEvent[events.length] = new F2FEvents(eventName, newOrganizer, newDNT, newTime, newDoubleFees, limit);
        }

//        newEvent[events.length] = new Event(eventName, newOrganizer, newDNT, newTime, newDoubleFees);
        this.events = newEvent;
        fileReader.serializeEvent(events);
        return this.displayEvent();
//        this.displayEvent();
    }

    public String searchEventByName(String eventName) { // method to search by name
        boolean found = false;
        String message = "";
        for (int i = 0; i < events.length; i++) {
            if (eventName.equalsIgnoreCase(events[i].getName())) {
                found = true;
                message = "Name         Organizer          Date\\Time               Fees($)\n\n" + events[i].getName() + "       " + events[i].getOrganizer() + "        " + events[i].getDate() + "             " + events[i].getFees();
            }
        }
        if (found == false) {
            message = "Cannot find the event \"" + eventName + "\"!!";
        }
        return message;
    }

    public String searchEventByFees(double fees) { // search events lower than the specified amount
        int counter = 1;
        String title = "S/N     Name         Organizer          Date\\Time               Fees($)\n";
        

            for (int i = 0; i < events.length; i++) {
                if (fees > events[i].getFees()) {
                    title += counter + ".        " + events[i].getName() + "       " + events[i].getOrganizer() + "           " + events[i].getDate() + "           " + events[i].getFees() + "\n";
                    counter++;
                }
            }
        

        return title;
    }

    public String deleteEvent(String eventName) { // delete events that the user specified
        int index = 0;
        boolean status = false;
        Event[] newEvent = new Event[events.length - 1];
        for (int i = 0; i < events.length; i++) {
            if (eventName.equalsIgnoreCase(events[i].getName())) {
                index = i;
                status = true;
            }
        }
        if (status == false) { // Cannot find event to delete
            String err = "";
            err = "Cannot find the event \"" + eventName + "\" to delete!";
//          JOptionPane.showMessageDialog(null, "Cannot find the event \"" + eventName + "\" to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return err;
        } else { // Found event with the searched name to delete
            for (int k = 0; k < events.length; k++) {
//                System.out.print(k);
                if (k != index) {
                    if (k > index) {
                        newEvent[k - 1] = events[k]; // replaces the newEvent array items with the events items - 1 after deleted item

                    } else {
                        newEvent[k] = events[k]; // replaces the newEvent array item from events items with the same array number

                    }
                }
            }
            this.events = newEvent;
        }
        fileReader.serializeEvent(events);
        return this.displayEvent();

//        this.displayEvent();
    }

    public String registerEvent(String register) {   // method for registering events
        String correct;                            //|
        boolean condition = false;                      //| Initialising variables
        String[] entered = new String[events.length];   //|
//        register = JOptionPane.showInputDialog(null, "Enter the Event name to register:", "Input", JOptionPane.QUESTION_MESSAGE);

        for (int i = 0; i < events.length; i++) {
            if (register.equalsIgnoreCase(events[i].getName())) { // Registers an event that has no duplicate 
                entered[i] = events[i].getName();
                totalFees += events[i].getFees();
                message += "*" + events[i].getName() + "\n";
                condition = true;
            }
        }
        if (condition == false) {
            String err;
            err = "Cannot find the event \"" + register + "\"!!";
            return err;
//            JOptionPane.showMessageDialog(null, "Cannot find the event \"" + register + "\"!!", "Event", JOptionPane.ERROR_MESSAGE);
        }
        correct = "You have registered for the following event(s):\n" + message + "\n\nThe cost is $" + totalFees;
        return correct;
//        JOptionPane.showMessageDialog(null, "You have registered for the following event(s):\n" + message + "\n\nThe cost is $" + totalFees); // prints out the events registered and the total fees
    }

    public void terminateProgram() { // method to end the program
        JOptionPane.showMessageDialog(null, "Program terminated.\nThank You!");
    }

    public void invalidInput() { // method to show error input
        JOptionPane.showMessageDialog(null, "Invalid input! Please enter in the range from 1 to 3.",
                "Error", JOptionPane.ERROR_MESSAGE);
    }

    public String displayEventMessage() {
        String s = "";
        for (int i = 0; i < events.length; i++) {
            s += events[i].getName() + " - " + events[i].displayMessage() + "\n\n";
        }
        return s;
    }

    public String displayCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
