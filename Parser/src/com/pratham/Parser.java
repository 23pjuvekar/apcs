package com.pratham;

public class Parser {

    String expression = null;
    String[] parts = new String[0];

    private int start = 0; //start index
    private int end = 0; //end index

    private void addPart(String part) {
        if ( parts == null ) {
            parts = new String[1];
        } else {
            String[] newParts = new String[parts.length+1];
            for( int i=0; i<parts.length; i++ ) {
                newParts[i] = parts[i];
            }
            parts = newParts;
        }
        parts[parts.length-1] = part; //add to the end
    }

    private void replaceParts(int start, int end, String part) {
        String[] newParts = new String[parts.length-2];
        int index = 0;
        for (int i=0; i<start; i++) {
            newParts[index++] = parts[i];
        }
        newParts[index++] = part;
        for (int i=end+1; i<parts.length; i++) {
            newParts[index++] = parts[i];
        }
        parts = newParts;
    }

    private void skipSpaces() {
        for (int i=start; i<expression.length(); i++) {
            char nextChar = expression.charAt(i);
            if (Character.isWhitespace(nextChar)) {
                start++; end++; //skip white spaces
            } else {
                break;
            }
        }
    }

    private void parseNumber() throws Exception  {

        skipSpaces(); //skip spaces
        if ( start >= expression.length() ) {
            throw new Exception ("No number found at position "+start);
        }

        //check next character for negative sign
        char nextChar = expression.charAt(start);
        if ( nextChar == '-' ) {
            if ( parts.length > 0 ) {
                String lastOperator = parts[parts.length-1];
                //negative can follow only * or \
                if ( !lastOperator.equals("*") && !lastOperator.equals("/") ) {
                    throw new Exception ("Unexpected negative sign at position "+start);
                }
            }
            end++; //negative is ok so move to next
        }

        for ( int i=end; i<expression.length(); i++ ) {
            nextChar = expression.charAt(i);
            if ( Character.isDigit(nextChar) ) {
                end++; //digit so move to next
            } else {
                break; //end of number so stop
            }
        }
        String number = expression.substring(start, end);
        if ( number.isEmpty() || number.equals("-") ) {
            throw new Exception("No number found at position "+start);
        }
        addPart(number);
        start = end;
    }

    private void parseOperator() throws Exception {

        skipSpaces(); //skip spaces

        //next character has to an operator
        char nextChar = expression.charAt(start);
        if ( nextChar != '+' && nextChar != '-' &&
                nextChar != '*' && nextChar != '/' ) {
            throw new Exception("Unexpected operator " + nextChar + " at " + start);
        }

        addPart(""+nextChar); //store the operator
        start++; end++; //move forward
    }

    private void evaluateMulDivParts() {
        for (int i=1; i<parts.length; i+=2 ) {
            //first do * and /
            if ( parts[i].equals("*") || parts[i].equals("/") ) {
                int num1 = Integer.parseInt(parts[i-1]);
                int num2 = Integer.parseInt(parts[i+1]);
                String answer;
                if ( parts[i].equals("*") ) {
                    answer = "" + (num1*num2);
                } else {
                    answer = "" + (num1/num2);
                }
                replaceParts(i-1,i+1, answer); //replace answer
                i -= 2; //move back loop
            }
        }
    }

    private void evaluateAddSubParts() {
        for (int i=1; i<parts.length; i+=2 ) {
            //first do * and /
            if ( parts[i].equals("+") || parts[i].equals("-") ) {
                int num1 = Integer.parseInt(parts[i-1]);
                int num2 = Integer.parseInt(parts[i+1]);
                String answer;
                if ( parts[i].equals("+") ) {
                    answer = "" + (num1+num2);
                } else {
                    answer = "" + (num1-num2);
                }
                replaceParts(i-1,i+1, answer); //replace answer
                i -= 2; //move back loop
            }
        }
    }

    private void evaluateParts() {
        evaluateMulDivParts();
        evaluateAddSubParts();
    }

    public String evaluate ( String expr ) throws Exception  {

        expression = expr.trim(); //remove spaces at start and end

        //empty string not allowed
        if ( expression.isEmpty() ) {
            throw new Exception ("expression cannot be empty");
        }

        //check expression starts with =
        if ( expression.charAt(0) != '=' ) {
            return expression; //return back expression
        }
        start++; end++; //skip the starting =

        //break up the expression
        parseNumber();
        while ( end < expression.length() ) {
            parseOperator();
            parseNumber();
            skipSpaces();
        }

        //evaluate the string using order of operations
        evaluateParts();

        return parts[0]; //return what remains as answer
    }

    public static void main (String[] args) {
        try {
            System.out.println(new Parser().evaluate("  78  + 23  "));
        } catch (Exception ex) {
            System.out.println (ex.getMessage());
        }
        try {
            System.out.println(new Parser().evaluate(" =  -56  +2  "));
        } catch (Exception ex) {
            System.out.println (ex.getMessage());
        }
        try {
            System.out.println(new Parser().evaluate(" =  -7*-2 + 3 / 4 +6 "));
        } catch (Exception ex) {
            System.out.println (ex.getMessage());
        }
    }
}