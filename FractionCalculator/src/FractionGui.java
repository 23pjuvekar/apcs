// Pratham Juvekar
// APCS Block E
// 9/10/2021
// Fraction Calculator

import javax.swing.*;
import BreezySwing.*;

public class FractionGui extends GBFrame {

    // Number 1 Numerator GUI Setup
    JLabel numOneNumLabel = addLabel("Number 1 Numerator:",1,1,1,1);
    IntegerField numOneNum = addIntegerField(0,1,2,1,1);

    // Number 1 Denominator GUI Setup
    JLabel numOneDeLabel = addLabel("Number 1 Denominator:", 2,1,1,1);
    IntegerField numOneDe = addIntegerField(0,2,2,1,1);

    // Number 2 Numerator GUI Setup
    JLabel numTwoNumLabel = addLabel("Number 2 Numerator:", 3,1,1,1);
    IntegerField numTwoNum = addIntegerField(0,3,2,1,1);

    // Number 2 Denominator GUI Setup
    JLabel numTwoDeLabel = addLabel("Number 2 Denominator:",4,1,1,1);
    IntegerField numTwoDe = addIntegerField(0,4,2,1,1);

    // Answer Section
    JLabel answerLabel = addLabel("Answer:", 5,1,1,1);
    JTextField answerBox = addTextField("",5,2,1,1);

    // Buttons Setup
    JButton resetButton = addButton("Reset", 8,1,1,1);
    JButton addButton = addButton("Add", 6,1,1,1 );
    JButton subtractButton = addButton("Subtract", 6,2,1,1 );
    JButton multiplyButton = addButton("Multiply", 7,1,1,1 );
    JButton divideButton = addButton("Divide", 7,2,1,1 );

    public FractionGui() {
        answerBox.setEditable(false);
    }

    //Method for when button is clicked
    public void buttonClicked(JButton btn){

        //Reset the inputs
        if ( btn == resetButton ) {
            numOneNum.setText("0");
            numOneDe.setText("0");
            numTwoNum.setText("0");
            numTwoDe.setText("0");
            answerBox.setText("");
        }

        //Error Checking for valid inputs
        if ( !numOneNum.isValidNumber() ) {
            answerBox.setText("Enter integer in number 1 numerator");
        } else if ( !numOneDe.isValidNumber() ) {
            answerBox.setText("Enter integer in number 1 denominator");
        } else if ( !numTwoNum.isValidNumber() ) {
            answerBox.setText("Enter integer in number 2 numerator");
        } else if ( !numTwoDe.isValidNumber() ) {
            answerBox.setText("Enter integer in number 2 denominator");
        } else {

            //Gets Numerator values
            int num1Numerator = numOneNum.getNumber();
            int num2Numerator = numTwoNum.getNumber();

            //Gets Denominator values
            int num1Denominator = numOneDe.getNumber();
            int num2Denominator = numTwoDe.getNumber();

            FractionCalculator fc = new FractionCalculator();

            if( btn == addButton ) {
                String answer = fc.add(num1Numerator, num1Denominator, num2Numerator, num2Denominator);
                answerBox.setText(answer);
            } else if( btn == subtractButton ) {
                String answer = fc.subtract(num1Numerator, num1Denominator, num2Numerator, num2Denominator);
                answerBox.setText(answer);
            } else if( btn == multiplyButton ) {
                String answer = fc.multiply(num1Numerator, num1Denominator, num2Numerator, num2Denominator);
                answerBox.setText(answer);
            } else if( btn == divideButton ) {
                String answer = fc.divide(num1Numerator, num1Denominator, num2Numerator, num2Denominator);
                answerBox.setText(answer);
            }

        }

    }

    public static void main(String[] args) {

        // JFrame set up
        JFrame frame = new FractionGui();
        frame.setVisible(true);
        frame.setTitle("Fraction Calculator");
        frame.setSize(600,300);

    }
}



