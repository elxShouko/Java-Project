/*
 * Class: DIT/FT/1B/03
 * Admission Number: 2026453
 * Name:Phan Kiah Fong Nicholas
 */
package JPRG;

//import javax.swing.JOptionPane;
public class F2FEvents extends Event {

    private int limit;

    public F2FEvents(String name, String organizer, String date, int time, double fees, int limit) {
        super(name, organizer, date, time, fees);
        this.limit = limit;
    }

    public String displayMessage() {
        return "This is a face to face event.\nIt has a limit of " + limit;
    }

}
