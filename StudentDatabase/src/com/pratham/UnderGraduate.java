package com.pratham;

public class UnderGraduate extends Student {

    String gradeLevel = "";

    public UnderGraduate(String name, int studentID, String gradeLevel) {
        super(name, studentID);
        this.gradeLevel = gradeLevel;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String print() {
        String retValue = "UnderGraduate - Name: " + name + ", ID: " + studentID + ", Grade level: " + gradeLevel;
        return retValue;
    }

    public boolean equals(UnderGraduate other) {
        return this.gradeLevel.equalsIgnoreCase(other.getGradeLevel());
    }

}
