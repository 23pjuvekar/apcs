package com.pratham;

public class Fraction implements Number {

    int numerator = 0;
    int denominator = 1;

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
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

    int GCF(int n1, int n2) {

        if ( n1 == 0 || n2 == 0 ) {
            return 1;
        }

        n1 = Math.abs(n1);
        n2 = Math.abs(n2);

        // figures out smaller and greater value between denominator product and numerator product
        int min = n1;
        if (n1 > n2) {
            min = n2;
        }

        // calculates GCF
        int gcf = min;
        while (n1 % gcf != 0 || n2 % gcf != 0) {
            gcf = gcf - 1;
        }
        return gcf;
    }

    @Override
    public Number add(Number num) {
        Fraction fracNum = (Fraction)num;
        Fraction result = new Fraction(0,0);
        result.numerator = this.numerator*fracNum.denominator+this.denominator*fracNum.numerator;
        result.denominator = this.denominator* fracNum.denominator;
        return result;
    }

    @Override
    public Number subtract(Number num) {
        Fraction fracNum = (Fraction)num;
        Fraction result = new Fraction(0,0);
        result.numerator = this.numerator*fracNum.denominator-this.denominator*fracNum.numerator;
        result.denominator = this.denominator* fracNum.denominator;
        return result;
    }

    @Override
    public Number multiply(Number num) {
        Fraction fracNum = (Fraction)num;
        Fraction result = new Fraction(0,0);
        result.numerator = this.numerator*fracNum.numerator;
        result.denominator = this.denominator*fracNum.denominator;
        return result;
    }

    @Override
    public Number divide(Number num) {
        Fraction fracNum = (Fraction)num;
        Fraction result = new Fraction(0,0);
        result.numerator = this.numerator*fracNum.denominator;
        result.denominator = this.denominator*fracNum.numerator;
        return result;
    }

    @Override
    public boolean isNegative() {
        if ( (numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0) ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isZero() {
        if ( numerator == 0 ) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        // simplify
        int gcf = GCF(numerator, denominator);
        int num = Math.abs(numerator/gcf);
        int den = Math.abs(denominator/gcf);

        if (den == 0) {
            return "NaN";
        }
        if (num == 0) {
            return "0";
        }

        if (den == 1) {
            if ( isNegative() ) {
                return "-" + num;
            }
            return "" + num;
        }

        if ( isNegative() ) {
            return "-" + num + "/" + den;
        }
        return "" + num + "/" + den;
    }

}
