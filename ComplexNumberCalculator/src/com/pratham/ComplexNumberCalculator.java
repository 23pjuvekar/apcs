package com.pratham;

public class ComplexNumberCalculator {

    public static void main(String[] args) {

        // test code

        Number n1 = new ComplexNumber(new Double(1), new Double(2));
        Number n2 = new ComplexNumber( new Double(3), new Double(0));
        Number n3 = new ComplexNumber(new Double(0), new Double(-5));
        Number n4 = new ComplexNumber( new Double(0),new Double(0));
        Number n5 = new ComplexNumber( new Fraction(1,2), new Fraction(-2,1));
        Number n6 = new ComplexNumber( new Fraction(6,3), new Fraction(2,4));

        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);
        System.out.println(n5);
        System.out.println(n6);

        Number divide = n1.divide(n2);
        System.out.println ( "" + n1 + " / " + n2 + " = " + divide);

        divide = n1.divide(n3);
        System.out.println ( "" + n1 + " / " + n3 + " = " + divide);

        divide = n1.divide(n4);
        System.out.println("" + n1 + " / " + n4 + " = " + divide);

        divide = n5.divide(n6);
        System.out.println ( "" + n5 + " / " + n6 + " = " + divide);
    }
}
