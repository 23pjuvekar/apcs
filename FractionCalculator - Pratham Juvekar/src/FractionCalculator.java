// Pratham Juvekar
// APCS Block E
// 9/10/2021
// Fraction Calculator

public class FractionCalculator {

    public String add (int num1, int den1, int num2, int den2) {

        if (den1 == 0) {
            return "Number 1 denominator cannot be 0";
        }
        if (den2 == 0) {
            return "Number 2 denominator cannot be 0";
        }

        Fraction f1 = new Fraction(num1, den1);
        Fraction f2 = new Fraction(num2, den2);
        Fraction result = f1.add(f2);

        return result.toString();
    }

    public String subtract (int num1, int den1, int num2, int den2) {

        if (den1 == 0) {
            return "Number 1 denominator cannot be 0";
        }
        if (den2 == 0) {
            return "Number 2 denominator cannot be 0";
        }

        Fraction f1 = new Fraction(num1, den1);
        Fraction f2 = new Fraction(num2, den2);
        Fraction result = f1.subtract(f2);

        return result.toString();
    }

    public String multiply (int num1, int den1, int num2, int den2) {

        if (den1 == 0) {
            return "Number 1 denominator cannot be 0";
        }
        if (den2 == 0) {
            return "Number 2 denominator cannot be 0";
        }

        Fraction f1 = new Fraction(num1, den1);
        Fraction f2 = new Fraction(num2, den2);
        Fraction result = f1.multiply(f2);

        return result.toString();
    }

    public String divide (int num1, int den1, int num2, int den2) {

        if (den1 == 0) {
            return "Number 1 denominator cannot be 0";
        }
        if (den2 == 0) {
            return "Number 2 denominator cannot be 0";
        }
        if (num2 == 0) {
            return "Number 2 numerator cannot be 0";
        }

        Fraction f1 = new Fraction(num1, den1);
        Fraction f2 = new Fraction(num2, den2);
        Fraction result = f1.divide(f2);

        return result.toString();
    }
}
