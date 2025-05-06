package Pages;

import java.time.Duration;

public class basePage {

    public static int convertStringToInt(String numberStr) {
        try {
            return Integer.parseInt(numberStr.trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Can't convert to int -> " + numberStr);
            return -1;
        }
    }
    public static float convertPriceToFloat(String priceText) {
        try {
            return Float.parseFloat(priceText.replace("$", "").replace(",", "").trim());
        } catch (NumberFormatException e) {
            System.out.println("Error converting price: " + priceText);
            return 0f;
        }
    }


    public static void SleepThreed()
    {
        try {
            Thread.sleep(Duration.ofSeconds(5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



}
