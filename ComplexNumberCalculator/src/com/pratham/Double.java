package com.pratham;

import java.text.DecimalFormat;

public class Double implements Number {

    double value = 0;

    public Double(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public Number add(Number num) {
        Double dblNum = (Double)num;
        return new Double(this.value + dblNum.getValue());
    }

    @Override
    public Number subtract(Number num) {
        Double dblNum = (Double)num;
        return new Double(this.value - dblNum.getValue());
    }

    @Override
    public Number multiply(Number num) {
        Double dblNum = (Double)num;
        return new Double(this.value * dblNum.getValue());
    }

    @Override
    public Number divide(Number num) {
        Double dblNum = (Double)num;
        return new Double(this.value / dblNum.getValue());
    }

    @Override
    public boolean isNegative() {
        if ( value < 0 ) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isZero() {
        if ( value == 0 ) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(value);
    }

}
