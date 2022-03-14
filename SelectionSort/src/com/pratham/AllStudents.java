package com.pratham;

public class AllStudents {

    private int studentCounter = 0;
    public final int MAX_STUDENTS = 15;
    private StudentInfo[] studentInfo = null;

    public AllStudents() {
        studentInfo = new StudentInfo[MAX_STUDENTS];
    }

    public int getNumStudents() {
        return studentCounter;
    }

    public StudentInfo addStudent(String name) {

       if (studentCounter >= MAX_STUDENTS) {
           return null;
       }

       StudentInfo newStudent = new StudentInfo(name);
       studentInfo[studentCounter] = newStudent;
       studentCounter++;

       return newStudent;
    }

    public void clearStudents() {
        studentInfo = new StudentInfo[MAX_STUDENTS];
        studentCounter = 0;
    }

    public double[] getAllFinalAverages() {
        double[] finalAverages = new double[studentCounter];
        for ( int i=0; i<studentCounter; i++ ) {
            finalAverages[i] = studentInfo[i].getFinalAvg();
        }
        return finalAverages;
    }

    private void selectionSortByName() {

        int n = studentCounter;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++){
                if (studentInfo[j].getName().compareToIgnoreCase(studentInfo[min_idx].getName()) < 0) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            StudentInfo temp = studentInfo[min_idx];
            studentInfo[min_idx] = studentInfo[i];
            studentInfo[i] = temp;
        }
    }

    private void selectionSortByFinalAverage() {

        int n = studentCounter;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++) {
            // Find the maximum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++){
                if (studentInfo[j].getFinalAvg() < studentInfo[min_idx].getFinalAvg()) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            StudentInfo temp = studentInfo[min_idx];
            studentInfo[min_idx] = studentInfo[i];
            studentInfo[i] = temp;
        }
    }

    public String getAllStudentsSortedByName() {

        String outputStr = "";

        if (studentCounter == 0) {
            outputStr = "There is currently no student info available.";
            return outputStr;
        }

        selectionSortByName();

        for (int i = 0; i < studentCounter; i++) {
            StudentInfo student = studentInfo[i];
            outputStr += String.format("%-25s %.0f %n", student.getName(), student.getFinalAvg());
        }

        return outputStr;
    }

    public String getAllStudentsSortedByFinalAverage() {

        String outputStr = "";

        if (studentCounter == 0) {
            outputStr = "There is currently no student info available.";
            return outputStr;
        }

        selectionSortByFinalAverage();

        // print backwards to show descending final averages
        for (int i = studentCounter-1; i >= 0; i--) {
            StudentInfo student = studentInfo[i];
            outputStr += String.format("%-25s %.0f %n", student.getName(), student.getFinalAvg());
        }

        return outputStr;
    }

}
