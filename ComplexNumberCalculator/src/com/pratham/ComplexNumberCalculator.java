package com.pratham;

import BreezySwing.DoubleField;
import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.IntegerField;

import javax.swing.*;

public class ComplexNumberCalculator extends GBFrame {

    private GBPanel mainPanel;

    private GBPanel answerPanel;
    private JTextArea answerField;

    private GBPanel typePanel;
    private JButton doubleBtn;
    private JButton fractionBtn;

    private GBPanel inputPanel;

    private DoubleField num1RealField;
    private DoubleField num1ImaginaryField;
    private DoubleField num2RealField;
    private DoubleField num2ImaginaryField;
    private JButton doubleAddBtn;
    private JButton doubleSubtractBtn;
    private JButton doubleMultiplyBtn;
    private JButton doubleDivideBtn;

    private IntegerField num1RealNumField;
    private IntegerField num1RealDenField;
    private IntegerField num1ImaginaryNumField;
    private IntegerField num1ImaginaryDenField;
    private IntegerField num2RealNumField;
    private IntegerField num2RealDenField;
    private IntegerField num2ImaginaryNumField;
    private IntegerField num2ImaginaryDenField;
    private JButton fractionAddBtn;
    private JButton fractionSubtractBtn;
    private JButton fractionMultiplyBtn;
    private JButton fractionDivideBtn;

    ComplexNumberCalculator() {

        this.setSize(400, 300);
        mainPanel = addPanel(1, 1, 1, 1);

        answerPanel = mainPanel.addPanel(1, 1, 1, 1);
        answerField = answerPanel.addTextArea("", 1, 1, 1, 1);
        answerField.setEditable(false);

        typePanel = mainPanel.addPanel(2,1,1,1);
        doubleBtn = typePanel.addButton("Double", 1,1,1,1);
        fractionBtn = typePanel.addButton("Fraction", 1,2,1,1);

        inputPanel = mainPanel.addPanel(3, 1, 1, 1);
        showDoubleInputPanel();
    }

    public void showDoubleInputPanel() {

        this.setSize(500, 300);

        doubleBtn.setEnabled(false);
        fractionBtn.setEnabled(true);

        mainPanel.remove(inputPanel);
        inputPanel = mainPanel.addPanel(3, 1, 1, 1);

        inputPanel.addLabel("Real", 1, 2, 1, 1);
        inputPanel.addLabel("Imaginary", 1, 3, 1, 1);

        inputPanel.addLabel("Complex Number 1: ", 2, 1, 1, 1);
        num1RealField = inputPanel.addDoubleField(1, 2, 2, 1, 1);
        num1ImaginaryField = inputPanel.addDoubleField(2, 2, 3, 1, 1);

        inputPanel.addLabel("Complex Number 2: ", 3, 1, 1, 1);
        num2RealField = inputPanel.addDoubleField(3, 3, 2, 1, 1);
        num2ImaginaryField = inputPanel.addDoubleField(4, 3, 3, 1, 1);

        doubleAddBtn = inputPanel.addButton("+", 4, 1, 1, 1);
        doubleSubtractBtn= inputPanel.addButton("-", 4, 2, 1, 1);
        doubleMultiplyBtn = inputPanel.addButton("*", 4, 3, 1, 1);
        doubleDivideBtn = inputPanel.addButton("/", 4, 4, 1, 1);

        revalidate();
    }

    public void showFractionInputPanel() {

        this.setSize(500, 360);

        doubleBtn.setEnabled(true);
        fractionBtn.setEnabled(false);

        mainPanel.remove(inputPanel);
        inputPanel = mainPanel.addPanel(3, 1, 1, 1);

        inputPanel.addLabel("Real", 1, 2, 1, 1);
        inputPanel.addLabel("Imaginary", 1, 3, 1, 1);

        inputPanel.addLabel("Complex Number 1: ", 2, 1, 1, 1);
        num1RealNumField = inputPanel.addIntegerField(1, 2, 2, 1, 1);
        num1RealDenField = inputPanel.addIntegerField(2, 3, 2, 1, 1);
        num1ImaginaryNumField = inputPanel.addIntegerField(3, 2, 3, 1, 1);
        num1ImaginaryDenField = inputPanel.addIntegerField(4, 3, 3, 1, 1);

        inputPanel.addLabel("Complex Number 2: ", 4, 1, 1, 1);
        num2RealNumField = inputPanel.addIntegerField(1, 4, 2, 1, 1);
        num2RealDenField = inputPanel.addIntegerField(3, 5, 2, 1, 1);
        num2ImaginaryNumField = inputPanel.addIntegerField(2, 4, 3, 1, 1);
        num2ImaginaryDenField = inputPanel.addIntegerField(4, 5, 3, 1, 1);

        fractionAddBtn = inputPanel.addButton("+", 6, 1, 1, 1);
        fractionSubtractBtn = inputPanel.addButton("-", 6, 2, 1, 1);
        fractionMultiplyBtn = inputPanel.addButton("*", 6, 3, 1, 1);
        fractionDivideBtn = inputPanel.addButton("/", 6, 4, 1, 1);

        revalidate();
    }

    public void buttonClicked(JButton buttonObj) {
        if ( buttonObj == doubleBtn ) {
            showDoubleInputPanel();
        }
        if ( buttonObj == fractionBtn) {
            showFractionInputPanel();
        }
        if ( buttonObj == doubleAddBtn ) {
            double num1real = num1RealField.getNumber();
            double num1imaginary = num1ImaginaryField.getNumber();
            double num2real = num2RealField.getNumber();
            double num2imaginary = num2ImaginaryField.getNumber();
            Number c1 = new ComplexNumber(new Double(num1real), new Double(num1imaginary));
            Number c2 = new ComplexNumber(new Double(num2real), new Double(num2imaginary));
            Number result = c1.add(c2);
            answerField.setText("(" + c1 + ") + (" + c2 + ") = " + result);
        }
        if ( buttonObj == doubleSubtractBtn ) {
            double num1real = num1RealField.getNumber();
            double num1imaginary = num1ImaginaryField.getNumber();
            double num2real = num2RealField.getNumber();
            double num2imaginary = num2ImaginaryField.getNumber();
            Number c1 = new ComplexNumber(new Double(num1real), new Double(num1imaginary));
            Number c2 = new ComplexNumber(new Double(num2real), new Double(num2imaginary));
            Number result = c1.subtract(c2);
            answerField.setText("(" + c1 + ") - (" + c2 + ") = " + result);
        }
        if ( buttonObj == doubleMultiplyBtn ) {
            double num1real = num1RealField.getNumber();
            double num1imaginary = num1ImaginaryField.getNumber();
            double num2real = num2RealField.getNumber();
            double num2imaginary = num2ImaginaryField.getNumber();
            Number c1 = new ComplexNumber(new Double(num1real), new Double(num1imaginary));
            Number c2 = new ComplexNumber(new Double(num2real), new Double(num2imaginary));
            Number result = c1.multiply(c2);
            answerField.setText("(" + c1 + ") * (" + c2 + ") = " + result);
        }
        if ( buttonObj == doubleDivideBtn ) {
            double num1real = num1RealField.getNumber();
            double num1imaginary = num1ImaginaryField.getNumber();
            double num2real = num2RealField.getNumber();
            double num2imaginary = num2ImaginaryField.getNumber();
            Number c1 = new ComplexNumber(new Double(num1real), new Double(num1imaginary));
            Number c2 = new ComplexNumber(new Double(num2real), new Double(num2imaginary));
            Number result = c1.divide(c2);
            answerField.setText("(" + c1 + ") / (" + c2 + ") = " + result);
        }
        if ( buttonObj == fractionAddBtn ) {
            int num1realNum = num1RealNumField.getNumber();
            int num1realDen = num1RealDenField.getNumber();
            int num1imaginaryNum = num1ImaginaryNumField.getNumber();
            int num1imaginaryDen = num1ImaginaryDenField.getNumber();
            int num2realNum = num2RealNumField.getNumber();
            int num2realDen = num2RealDenField.getNumber();
            int num2imaginaryNum = num2ImaginaryNumField.getNumber();
            int num2imaginaryDen = num2ImaginaryDenField.getNumber();
            Number c1 = new ComplexNumber(new Fraction(num1realNum, num1realDen), new Fraction(num1imaginaryNum, num1imaginaryDen));
            Number c2 = new ComplexNumber(new Fraction(num2realNum, num2realDen), new Fraction(num2imaginaryNum, num2imaginaryDen));
            Number result = c1.add(c2);
            answerField.setText("(" + c1 + ") + (" + c2 + ") = " + result);
        }
        if ( buttonObj == fractionSubtractBtn ) {
            int num1realNum = num1RealNumField.getNumber();
            int num1realDen = num1RealDenField.getNumber();
            int num1imaginaryNum = num1ImaginaryNumField.getNumber();
            int num1imaginaryDen = num1ImaginaryDenField.getNumber();
            int num2realNum = num2RealNumField.getNumber();
            int num2realDen = num2RealDenField.getNumber();
            int num2imaginaryNum = num2ImaginaryNumField.getNumber();
            int num2imaginaryDen = num2ImaginaryDenField.getNumber();
            Number c1 = new ComplexNumber(new Fraction(num1realNum, num1realDen), new Fraction(num1imaginaryNum, num1imaginaryDen));
            Number c2 = new ComplexNumber(new Fraction(num2realNum, num2realDen), new Fraction(num2imaginaryNum, num2imaginaryDen));
            Number result = c1.subtract(c2);
            answerField.setText("(" + c1 + ") - (" + c2 + ") = " + result);
        }
        if ( buttonObj == fractionMultiplyBtn ) {
            int num1realNum = num1RealNumField.getNumber();
            int num1realDen = num1RealDenField.getNumber();
            int num1imaginaryNum = num1ImaginaryNumField.getNumber();
            int num1imaginaryDen = num1ImaginaryDenField.getNumber();
            int num2realNum = num2RealNumField.getNumber();
            int num2realDen = num2RealDenField.getNumber();
            int num2imaginaryNum = num2ImaginaryNumField.getNumber();
            int num2imaginaryDen = num2ImaginaryDenField.getNumber();
            Number c1 = new ComplexNumber(new Fraction(num1realNum, num1realDen), new Fraction(num1imaginaryNum, num1imaginaryDen));
            Number c2 = new ComplexNumber(new Fraction(num2realNum, num2realDen), new Fraction(num2imaginaryNum, num2imaginaryDen));
            Number result = c1.multiply(c2);
            answerField.setText("(" + c1 + ") * (" + c2 + ") = " + result);

        }
        if ( buttonObj == fractionDivideBtn ) {
            int num1realNum = num1RealNumField.getNumber();
            int num1realDen = num1RealDenField.getNumber();
            int num1imaginaryNum = num1ImaginaryNumField.getNumber();
            int num1imaginaryDen = num1ImaginaryDenField.getNumber();
            int num2realNum = num2RealNumField.getNumber();
            int num2realDen = num2RealDenField.getNumber();
            int num2imaginaryNum = num2ImaginaryNumField.getNumber();
            int num2imaginaryDen = num2ImaginaryDenField.getNumber();
            Number c1 = new ComplexNumber(new Fraction(num1realNum, num1realDen), new Fraction(num1imaginaryNum, num1imaginaryDen));
            Number c2 = new ComplexNumber(new Fraction(num2realNum, num2realDen), new Fraction(num2imaginaryNum, num2imaginaryDen));
            Number result = c1.divide(c2);
            answerField.setText("(" + c1 + ") / (" + c2 + ") = " + result);

        }
    }

    public static void main(String[] args) {
        ComplexNumberCalculator gui = new ComplexNumberCalculator();
        gui.setTitle("Complex Number Calculator");
        gui.setLocationRelativeTo(null); //this displays JFrame to center position of a screen
        gui.setVisible (true);
    }
}
