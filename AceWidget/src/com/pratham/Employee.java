package com.pratham;

public class Employee {

    private String name = null;
    private double q1Sales = 0;
    private double q2Sales = 0;
    private double q3Sales = 0;
    private double q4Sales = 0;

    public Employee(String n) {

        name = n;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getQ1Sales() {

        return q1Sales;
    }

    public void setQ1Sales(double q1Sales) {

        this.q1Sales = q1Sales;
    }

    public double getQ2Sales() {

        return q2Sales;
    }

    public void setQ2Sales(double q2Sales) {

        this.q2Sales = q2Sales;
    }

    public double getQ3Sales() {

        return q3Sales;
    }

    public void setQ3Sales(double q3Sales) {

        this.q3Sales = q3Sales;
    }

    public double getQ4Sales() {

        return q4Sales;
    }

    public void setQ4Sales(double q4Sales) {

        this.q4Sales = q4Sales;
    }

    public double getTotalSales() {

        double totalSales = q1Sales + q2Sales + q3Sales + q4Sales;
        return totalSales;

    }
}
