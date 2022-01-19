package com.pratham;

public interface Number {

    public Number add(Number num);
    public Number subtract(Number num);
    public Number multiply(Number num);
    public Number divide(Number num);

    public boolean isNegative();
    public boolean isZero();
}
