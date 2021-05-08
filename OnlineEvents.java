/*
 * Class: DIT/FT/1B/03
 * Admission Number: 2026453
 * Name:Phan Kiah Fong Nicholas
 */
package JPRG;

//import javax.swing.JOptionPane;
public class OnlineEvents extends Event {

    private int limit = 0;

    public OnlineEvents(String name, String organizer, String date, int time, double fees, int limit) {
        super(name, organizer, date, time, fees);
        this.limit = limit;
    }

    public String displayMessage() {
        return "This is an online event.\nThere is no limit on the number of participants.";
    }

}
