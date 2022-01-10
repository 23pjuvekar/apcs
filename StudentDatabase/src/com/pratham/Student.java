package com.pratham;

public class Student extends Person {

    int studentID = 0;

    public Student(String name, int studentID) {
        super(name);
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String print() {
        String retValue = "Student - Name: " + name + ", ID: " + studentID;
        return retValue;
    }

}
