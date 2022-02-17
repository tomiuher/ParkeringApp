package utility;

import model.ParkingSpot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class SharedUtilities {


    public static boolean arrayIsEmpty(ArrayList<ParkingSpot> arrayList) {
        return arrayList == null || arrayList.size() == 0;
    }

    public static void printNumberedList(ArrayList printList) {

        for (int i = 0 ; i < printList.size() ; i++) {
            System.out.println("\t" + (i + 1) + ". " + printList.get(i));
        }
        System.out.println("\t" + (printList.size() + 1) + ". Exit");
    }

    public static boolean selectionValidation(ArrayList availableParkingSpotList, int input) {
        //noinspection RedundantIfStatement
        if (input > availableParkingSpotList.size() || input < 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String formatTime(Date date) {
        SimpleDateFormat outputFormatter = new SimpleDateFormat("EEE, MMM d, yyyy");
        return outputFormatter.format(date);
    }


    public static int getIntInput() {
        System.out.print("\n\tEnter selection: ");
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        if(scanner.hasNextInt()) {
            input = scanner.nextInt();
        }
        else {
            System.out.println("\tInvalid selection.");
            input = getIntInput();
        }

        return input;
    }


    public static void processingAnimation() {

        System.out.println("\n                     PROCESSING                        ");
        System.out.print("  (");

        for (int i = 0 ; i < 10 ; i++) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("+++++");
        }

        System.out.print  (")\n");
    }

    public static boolean isStringInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
