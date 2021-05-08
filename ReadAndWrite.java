/*
 * Class: DIT/FT/1B/03
 * Admission Number: 2026453
 * Name:Phan Kiah Fong Nicholas
 */
package JPRG;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadAndWrite {

    protected static Event[] event = new Event[3];
//    protected static Event[] event;

    public static void main(String[] args) throws ClassNotFoundException {
//        Event myEvent = new Event("hoola", "awdawd", "12-Dec-2020", 20, 1000);
//        loadEvent();
//        System.out.print(event);
//        System.out.print(event[0].getName());
//        saveEvent();
        Event[] myEvent = new Event[3];
        myEvent[0] = new Event("John", "John", "12-Dec-2020", 1200, 12);
        myEvent[1] = new Event("Tom", "Tom Enterprises", "13-Dec-2020", 1201, 123);
        myEvent[1] = new Event("Eric", "Eric Pte Ltd", "14-Dec-2020", 1202, 1234);

        deSerializeEvent();
//        serializeEvent(myEvent);
//        saveEvent(myEvent);

//        Event[] p = loadEvent();
//        System.out.println(p[0].getName());
    }

    public static void saveEvent(Event[] evnt) {
        File f = new File("events.dat");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            for (int i = 0; i < evnt.length; i++) {
                os.writeObject(evnt[i]);
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Event[] loadEvent() {
        String s;
        String filedir = System.getProperty("user.dir");
        Path path = Paths.get("events.dat");
        if(Files.exists(path)){
            try {
                deSerializeEvent();
            } catch (ClassNotFoundException ex) {
                System.out.println("Class is not found! Proceeding to use events.txt");
            }
        }
        try {

            FileReader fr = new FileReader(filedir + "\\src\\JPRG\\events.txt");
            BufferedReader br = new BufferedReader(fr);

            int counter = 0;
//            int counter = (Integer.parseInt(br.readLine()));

//            for (int i = 0; i < counter; i++) {
            while (counter <= 2) {
                while ((s = br.readLine()) != null) {
                    String[] results = s.split(";");
                    String eventName = results[1];
                    String organizer = results[2];
                    String date = results[3];
                    int time = Integer.parseInt(results[4]);
                    double fees = Double.parseDouble(results[5]);

                    if (results[counter].equals("F2F")) {
                        event[counter] = new F2FEvents(eventName, organizer, date, time, fees, 0);
                    } else {
                        event[counter] = new OnlineEvents(eventName, organizer, date, time, fees, 0);
                    }
                    counter++;
                }
            }
//            System.out.println(event[counter-1].getName());
            System.out.print("File successfully read");
            br.close();

        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        } catch (IOException ex) {
            System.out.println("IO File error");
        }
        return event;
    }

    public static void serializeEvent(Event[] event) {
        File f = new File("events.dat");

        try {
            ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(f));
            outStream.writeObject(event);
            outStream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static Event[] deSerializeEvent() throws ClassNotFoundException {
        File f = new File("events.dat");

        try {
            ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(f));
            event = (Event[]) inStream.readObject();
//            System.out.println("Name: "+event[0].getName());

            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO File Error");
        }
        return event;
    }
}
