package com.pratham;

public class Database {

    private final int MAX_PERSONS = 10;
    private Person[] persons = new Person[MAX_PERSONS];
    private int personCount = 0;

    public int getPersonCount() {
        return personCount;
    }

    public Person getPerson(int i) {
        if ( i < 0 || i >= personCount) {
            return null;
        }
        return persons[i];
    }

    public boolean addPerson(Person person) {
        if ( personCount == MAX_PERSONS ) {
            return false; //we reached the max limit
        }
        persons[personCount] = person;
        personCount++;
        return true; //added to end of the array
    }

}
