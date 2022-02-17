package payment;

import utility.SharedUtilities;

import java.util.Random;

public class Visa_Mastercard {


    public static boolean verifyPayment() {

        Random random = new Random();

        SharedUtilities.processingAnimation();

        return (random.nextInt(10 - 1 + 1) + 1) != 10;
    }


}
