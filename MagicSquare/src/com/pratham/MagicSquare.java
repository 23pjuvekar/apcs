package com.pratham;

public class MagicSquare {

    private int[][] arr = null;

    //Constructor
    MagicSquare(int size) {
        arr = new int[size][size]; //Create new array size x size
        makeArrayZero();
    }

    //Set all array integers to zero
    private void makeArrayZero() {
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 0;
            }
        }
    }

    //Store a number
    public void addNumber(int i, int j, int number) {
        arr[i][j] = number;
    }

    //Get a number
    public int getNumber(int i, int j) {
        return arr[i][j];
    }

    //Check if square is Magic (return 0 if it is not otherwise return constant)
    public int isMagicSquare() {
        int constant = 0;
        int size = arr.length;

        // compare all the rows
        for (int i = 0; i < size; i++) {
            int sumRow = 0;
            for (int j = 0; j < size; j++) {
                sumRow += arr[i][j];
            }
            if (constant == 0) {
                constant = sumRow;
            } else {
                if (sumRow != constant){
                    return 0;
                }
            }
        }
        // compare all the columns
        for (int i = 0; i < size; i++) {
            int sumColumns = 0;
            for (int j = 0; j < size; j++) {
                sumColumns += arr[j][i];
            }
            if (sumColumns != constant){
                return 0;
            }
        }
        // compare the diagonals
        int sumDiag1 = 0;
        for (int i = 0; i < size; i++) {
            sumDiag1 += arr[i][i];
        }
        if (sumDiag1 != constant){
            return 0;
        }
        int sumDiag2 = 0;
        for (int i = 0; i < size; i++) {
            sumDiag2 += arr[i][size - i - 1];
        }
        if (sumDiag2 != constant){
            return 0;
        }

        // all conditions for a magic square are met
        return constant;
    }

    //Used by createSinglyEvenMagicSquare
    private void odd(int[][] magic, int startNum){

        int size = magic.length;
        int row = size-1;
        int col = size/2;
        magic[row][col] = startNum;
        for(int i = 2; i<=size*size; i++){
            if(magic[(row+1)%size][(col+1)%size]== 0){
                row = (row+1)%size;
                col = (col+1)%size;
            }else{
                row = (row-1+size)%size;
            }
            magic[row][col] = i+startNum-1;
        }

    }

    //Create odd magic square 6x6
    private void createSinglyEvenMagicSquare (int startNum) {

        int size = arr.length;

        int halfSize = size/2; //size of ABCD boxes
        int k = (size-2)/4; // to get 'noses' of A & D boxes
        int temp;

        int [] swapCol = new int[size]; // columns which need to swap between C-B & A-D
        int index=0; // index of swapCol

        int [][] miniMagic =  new int [halfSize][halfSize];
        odd(miniMagic, startNum);	//creating odd magic square for A box

        //creating 4 magic boxes
        for (int i=0; i<halfSize; i++)
            for (int j=0; j<halfSize; j++){
                arr[i][j] = miniMagic[i][j]; 	  		  //A box
                arr[i+halfSize][j+halfSize] = miniMagic[i][j]+halfSize*halfSize;   //B box
                arr[i][j+halfSize] = miniMagic[i][j]+2*halfSize*halfSize;       //C box
                arr[i+halfSize][j] = miniMagic[i][j]+3*halfSize*halfSize;       //D box
            }

        for (int i=1; i<=k; i++)
            swapCol[index++] = i;

        for (int i=size-k+2; i<=size; i++)
            swapCol[index++] = i;

        //swaping values between C-B & A-D by known columns
        for (int i=1; i<=halfSize; i++)
            for (int j=1; j<=index; j++){
                temp=arr[i-1][swapCol[j-1]-1];
                arr[i-1][swapCol[j-1]-1]=arr[i+halfSize-1][swapCol[j-1]-1];
                arr[i+halfSize-1][swapCol[j-1]-1]=temp;
            }

        //swaping noses
        temp=arr[k][0];
        arr[k][0]=arr[k+halfSize][0];
        arr[k+halfSize][0]=temp;

        temp=arr[k+halfSize][k];
        arr[k+halfSize][k]=arr[k][k];
        arr[k][k]=temp;
        //end of swaping

    }

    //Create doubly even magic square 4x4, 8x8
    private void createDoublyEvenMagicSquare (int startNum) {

        int size = arr.length;

        int boxSize = size/4;                        //size of boxes
        int counter = startNum; 	                 //counter starting forwards
        int reverseCounter = size*size+(startNum-1); //counter starting backwards

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){

                if(j>=boxSize && j<size-boxSize){
                    if(i>=boxSize && i<size-boxSize) {
                        arr[i][j] = counter;    //central box
                    } else {
                        arr[i][j] = reverseCounter; // up & down boxes
                    }
                } else if(i<boxSize || i>=size-boxSize){
                    arr[i][j]=counter;	         // 4 corners
                }
                else {
                    arr[i][j] = reverseCounter;    // left & right boxes
                }

                counter++;
                reverseCounter--;
            }
        }

    }

    //Create odd magic square 3x3, 5x5, 7x7
    private void createOddMagicSquare(int startNum) {

        int size = arr.length;

        int i = 0, j = (size - 1)/2; //Start at top middle
        int current = startNum;
        arr[i][j] = current;

        int counter = 1;
        while (counter < size * size){ //Loop till everything is stored
            current++;
            int i2 = i - 1;
            if (i2 < 0) {
                i2 = size - 1;
            }
            int j2 = j + 1;
            if (j2 > size - 1) {
                j2 = 0;
            }
            if (arr[i2][j2] != 0) { //If there is a number stored
                i2 = i + 1;
                j2 = j;
            }
            arr[i2][j2] = current; //Store current number
            i = i2;
            j = j2;
            counter++; //Move to next number
        }
    }

    //Generate the magic square
    public void createMagicSquare(int startNum) {

        int size = arr.length;

        makeArrayZero(); //Make all array zeros to start

        if ( size % 2 == 1 ) {
            createOddMagicSquare (startNum);
        } else if ( size % 4 == 0 ) {
            createDoublyEvenMagicSquare (startNum);
        } else {
            createSinglyEvenMagicSquare (startNum);
        }

    }

}
