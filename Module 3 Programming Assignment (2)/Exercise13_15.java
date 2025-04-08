    import java.math.BigInteger;
    import java.util.Scanner;

    public class Exercise13_15 {
        public static void main(String[] args) {
            // Prompt the user to enter two Rational numbers
            Scanner input = new Scanner(System.in);
            System.out.print("Enter rational r1 with numerator and denominator seperated by a space: ");
            String n1 = input.next();
            String d1 = input.next();

            System.out.print("Enter rational r2 with numerator and denominator seperated by a space: ");
            String n2 = input.next();
            String d2 = input.next();

            RationalUsingBigInteger r1 = new RationalUsingBigInteger(new BigInteger(n1), new BigInteger(d1));
            RationalUsingBigInteger r2 = new RationalUsingBigInteger(new BigInteger(n2), new BigInteger(d2));
            System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
            System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
            System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
            System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
            System.out.println(r2 + " is " + r2.doubleValue());

            input.close();
        }
    }

    class RationalUsingBigInteger extends Number implements Comparable<RationalUsingBigInteger> {

        private BigInteger numerator = BigInteger.ZERO;
        private BigInteger denominator = BigInteger.ONE;

        //starting here
        public RationalUsingBigInteger() {
            this(BigInteger.ZERO, BigInteger.ONE);
        }

        //constructor given the num and den
        public RationalUsingBigInteger(BigInteger numerator, BigInteger denominator) {
            if (denominator.equals(BigInteger.ZERO)) {
                throw new ArithmeticException("Denominator cannot be zero.");
            }

            BigInteger gcd = numerator.gcd(denominator);
            if (denominator.compareTo(BigInteger.ZERO) < 0) {
                gcd = gcd.negate();
            }

            this.numerator = numerator.divide(gcd);
            this.denominator = denominator.divide(gcd);
        }

        // getters for arithmetic logic
        public BigInteger getNumerator() {
            return numerator;
        }

        public BigInteger getDenominator() {
            return denominator;
        }

        // said arithmetic
        public RationalUsingBigInteger add(RationalUsingBigInteger secondRational) {
            BigInteger n = numerator.multiply(secondRational.getDenominator()).add(denominator.multiply(secondRational.getNumerator()));
            BigInteger d = denominator.multiply(secondRational.getDenominator());
            return new RationalUsingBigInteger(n, d);
        }

        public RationalUsingBigInteger subtract(RationalUsingBigInteger secondRational) {
            BigInteger n = numerator.multiply(secondRational.getDenominator()).subtract(denominator.multiply(secondRational.getNumerator()));
            BigInteger d = denominator.multiply(secondRational.getDenominator());
            return new RationalUsingBigInteger(n, d);
        }

        public RationalUsingBigInteger multiply(RationalUsingBigInteger secondRational) {
            BigInteger n = numerator.multiply(secondRational.getNumerator());
            BigInteger d = denominator.multiply(secondRational.getDenominator());
            return new RationalUsingBigInteger(n, d);
        }

        public RationalUsingBigInteger divide(RationalUsingBigInteger secondRational) {
            BigInteger n = numerator.multiply(secondRational.denominator);
            BigInteger d = denominator.multiply(secondRational.numerator);
            return new RationalUsingBigInteger(n, d);
        }

        // Override toString
        @Override
        public String toString() {
            if (denominator.equals(BigInteger.ONE))
                return numerator.toString();
            else
                return numerator + "/" + denominator;
        }

        //compare logic and the rest of the getters
        @Override
        public boolean equals(Object other) {
            if (!(other instanceof RationalUsingBigInteger))
                return false;
            RationalUsingBigInteger r = (RationalUsingBigInteger) other;
            return this.subtract(r).getNumerator().equals(BigInteger.ZERO);
        }

        @Override
        public int compareTo(RationalUsingBigInteger o) {
            BigInteger diff = this.subtract(o).getNumerator();
            return diff.compareTo(BigInteger.ZERO);
        }

        @Override
        public double doubleValue() {
            return numerator.doubleValue() / denominator.doubleValue();
        }

        @Override
        public float floatValue() {
            return (float) doubleValue();
        }

        @Override
        public int intValue() {
            return (int) doubleValue();
        }

        @Override
        public long longValue() {
            return (long) doubleValue();
        }
    }
