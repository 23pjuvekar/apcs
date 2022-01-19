package com.pratham;

public class ComplexNumber implements Number {

    Number real = null;
    Number imaginary = null;

    public ComplexNumber(Number real, Number imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Number getReal() {
        return real;
    }

    public void setReal(Number real) {
        this.real = real;
    }

    public Number getImaginary() {
        return imaginary;
    }

    public void setImaginary(Number imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public Number add(Number num) {
        ComplexNumber complexNum = (ComplexNumber)num;
        Number real = this.real.add(complexNum.real);
        Number imaginary = this.imaginary.add(complexNum.imaginary);
        return new ComplexNumber(real, imaginary);
    }

    @Override
    public Number subtract(Number num) {
        ComplexNumber complexNum = (ComplexNumber)num;
        Number real = this.real.subtract(complexNum.real);
        Number imaginary = this.imaginary.subtract(complexNum.imaginary);
        return new ComplexNumber(real, imaginary);
    }

    @Override
    public Number multiply(Number num) {
        ComplexNumber complexNum = (ComplexNumber)num;
        Number real = this.real.multiply(complexNum.real).subtract(this.imaginary.multiply(complexNum.imaginary));
        Number imaginary = this.real.multiply(complexNum.imaginary).add(this.imaginary.multiply(complexNum.real));
        return new ComplexNumber(real, imaginary);
    }

    @Override
    public Number divide(Number num) {
        ComplexNumber complexNum = (ComplexNumber)num;
        Number numReal = this.real.multiply(complexNum.real).add(this.imaginary.multiply(complexNum.imaginary));;
        Number numImaginary = this.imaginary.multiply(complexNum.real).subtract(this.real.multiply(complexNum.imaginary));;;
        Number den = complexNum.real.multiply(complexNum.real).add(complexNum.imaginary.multiply(complexNum.imaginary));
        numReal = numReal.divide(den);
        numImaginary = numImaginary.divide(den);
        return new ComplexNumber(numReal, numImaginary);
    }

    @Override
    public boolean isNegative() {
        return false;
    }

    @Override
    public boolean isZero() {
        if ( real.isZero() && imaginary.isZero() ) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if ( isZero() ) {
            return "0";
        }
        if ( real.isZero() ) {
            return "" + imaginary + "i";
        }
        if ( imaginary.isZero() ) {
            return "" + real;
        }
        if ( imaginary.isNegative() ) {
            return "" + real + imaginary + "i";
        }
        return "" + real + "+" + imaginary + "i";
    }

}
