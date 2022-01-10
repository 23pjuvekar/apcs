package com.pratham;

public class Graduate extends Student {

    String major = "";

    public Graduate(String name, int studentID, String major) {
        super(name, studentID);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String print() {
        String retValue = "Graduate - Name: " + name + ", ID: " + studentID + ", Major: " + major;
        return retValue;
   }

    public boolean equals(Graduate other) {
        return this.major.equalsIgnoreCase(other.getMajor());
    }

}
