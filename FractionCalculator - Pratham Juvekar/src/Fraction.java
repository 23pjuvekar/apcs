
public class Fraction {

    private int numerator;
    private int denominator;

    //Constructor
    public Fraction(int num, int den) {
        this.numerator = num;
        this.denominator = den;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public Fraction add(Fraction other) {
        int num = this.numerator*other.denominator + this.denominator*other.numerator;
        int den = this.denominator*other.denominator;
        return new Fraction (num, den);
    }

    public Fraction subtract(Fraction other) {
        int num = this.numerator*other.denominator - this.denominator*other.numerator;
        int den = this.denominator*other.denominator;
        return new Fraction (num, den);
    }

    public Fraction multiply(Fraction other) {
        int num = this.numerator*other.numerator;
        int den = this.denominator*other.denominator;
        return new Fraction (num, den);
    }

    public Fraction divide(Fraction other) {
        int num = this.numerator*other.denominator;
        int den = this.denominator*other.numerator;
        return new Fraction (num, den);
    }

    // Calculates GCF of two numbers
    private int getGCF ( int num1, int num2 ) {

        num1 = Math.abs(num1);
        num2 = Math.abs(num2);

        // Find the smaller number and stores in num2
        if ( num1 < num2 ) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }

        // Finds the GCF
        while ( num2 != 0 ) {
            int temp = num1;
            num1 = num2;
            num2 = temp % num1;
        }

        return num1;
    }

    private Fraction reduce() {
        int gcf = getGCF(this.numerator, this.denominator);
        int num = this.numerator/gcf;
        int den = this.denominator/gcf;
        return new Fraction(num,den);
    }

    public String toString() {
        String answer;

        boolean isNegative = false;
        if ( (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0) ) {
            isNegative = true;
        }

        Fraction reducedFraction = this.reduce();
        int num = Math.abs(reducedFraction.getNumerator());
        int den = Math.abs(reducedFraction.getDenominator());

        if (den == 1) {
            if ( isNegative ) {
                answer = "-" + num + "";
            } else {
                answer =  num + "";
            }
        } else {
            if ( isNegative ) {
                answer =  "-" + num + "/" + den;
            } else {
                answer = num + "/" + den;
            }
        }

        return answer;
    }
}
