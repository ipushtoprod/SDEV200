public class Converter {
    /** Returns a double of meters given feet */
    public static double feetToMeter(double feet) {
        return 0.305 * feet;
    }
    /** Returns a double of feet given meters */
    public static double meterToFeet(double meter) {
        return 3.279 * meter;
    }
    /** the main */
    public static void main(String[] args) {

        double[] feetValues = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0 , 8.0, 9.0, 10.0};
        double[] meterValues = {20.0, 25.0, 30.0, 35.0, 40.0 , 45.0, 50.0, 55.0, 60.0, 65.0};

        System.out.println(String.format("%10s %10s   %10s %10s", "Feet", "Meters", "Meters", "Feet"));
        System.out.println("--------------------------------------------------");

        for(int i = 0; i < 10; i++) {
            System.out.println(String.format("%10.1f %10.3f   %10.1f %10.3f",
                    feetValues[i], feetToMeter(feetValues[i]), meterValues[i], meterToFeet(meterValues[i])));
        }

    }
}
