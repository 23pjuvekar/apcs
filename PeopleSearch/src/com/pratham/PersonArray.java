package com.pratham;

public class PersonArray {

    private Person[] people = new Person[0]; //empty array by default
    private int numComparisons = 0;

    public PersonArray() {
    }

    public void addPerson(String name, int age) {
        //make new array with one more spot
        Person[] peopleNew = new Person[people.length+1];
        for (int i=0; i<people.length; i++) {
            peopleNew[i] = people[i];
        }
        peopleNew[people.length] = new Person(name, age); //add at the end
        people = peopleNew; //assign new array to old one
    }

    public int getNumComparisons() {
        return numComparisons;
    }

    public int getNumPeople() {
        return people.length;
    }

    private Person[] selectionSortByName() {

        // copy persons into new array to sort
        Person[] sortedPeople = new Person[people.length];
        for ( int i=0; i < people.length; i++ ) {
            sortedPeople[i] = people[i];
        }

        if ( sortedPeople.length <= 1 ) {
            return sortedPeople; // nothing to sort
        }

        int n = sortedPeople.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++){
                if (sortedPeople[j].getName().compareToIgnoreCase(sortedPeople[min_idx].getName()) < 0) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            Person temp = sortedPeople[min_idx];
            sortedPeople[min_idx] = sortedPeople[i];
            sortedPeople[i] = temp;
        }

        return sortedPeople;
    }

    private Person[] selectionSortByAge() {

        // copy persons into new array to sort
        Person[] sortedPeople = new Person[people.length];
        for ( int i=0; i < people.length; i++ ) {
            sortedPeople[i] = people[i];
        }

        if ( sortedPeople.length <= 1 ) {
            return sortedPeople; // nothing to sort
        }

        int n = sortedPeople.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++){
                if (sortedPeople[j].getAge() < sortedPeople[min_idx].getAge()) {
                    min_idx = j;
                }
            }
            // Swap the found minimum element with the first element
            Person temp = sortedPeople[min_idx];
            sortedPeople[min_idx] = sortedPeople[i];
            sortedPeople[i] = temp;
        }

        return sortedPeople;
    }

    public Person sequentialSearch(String name) {
        numComparisons = 0;
        if ( people.length == 0 ) {
            return null; // empty array
        }
        for (int i=0; i<people.length; i++) {
            numComparisons++;
            if ( people[i].getName().equalsIgnoreCase(name) ) {
                return people[i];
            }
        }
        return null; // not found
    }

    public Person binarySearch(String name) {
        numComparisons = 0;
        if ( people.length == 0 ) {
            return null; // empty array
        }

        // get sorted list for binary search
        Person[] sortedPeople = selectionSortByName();

        // do the binary search
        int low = 0, high = sortedPeople.length-1;
        while ( low <= high ) {
            numComparisons++;
            int mid = (low+high)/2;
            if ( sortedPeople[mid].getName().toLowerCase().compareToIgnoreCase(name) < 0 ) {
                low = mid + 1;
            } else if ( sortedPeople[mid].getName().compareToIgnoreCase(name) > 0 ) {
                high = mid - 1;
            } else if ( sortedPeople[mid].getName().compareToIgnoreCase(name) == 0 ) {
                return sortedPeople[mid];
            }
        }

        return null; // not found
    }

    public String getAllPeopleSortedByName() {

        String retMessage = "";

        if (getNumPeople() == 0) {
            retMessage = "There are currently no people in the system.";
            return retMessage;
        }

        retMessage += String.format("%-20s %-4s %n", "Name", "Age");
        retMessage += String.format("%-20s %-4s %n", "----", "---");
        Person[] sortedPeople = selectionSortByName();
        for (int i = 0; i < sortedPeople.length; i++) {
            Person person = sortedPeople[i];
            retMessage += String.format("%-20s %-4d %n", person.getName(), person.getAge());
        }

        return retMessage;
    }

    public String getAllPeopleSortedByAge() {

        String retMessage = "";

        if (getNumPeople() == 0) {
            retMessage = "There are currently no people in the system.";
            return retMessage;
        }

        retMessage += String.format("%-20s %-4s %n", "Name", "Age");
        retMessage += String.format("%-20s %-4s %n", "----", "---");
        Person[] sortedPeople = selectionSortByAge();
        for (int i = 0; i < sortedPeople.length; i++) {
            Person person = sortedPeople[i];
            retMessage += String.format("%-20s %-4d %n", person.getName(), person.getAge());
        }

        return retMessage;
    }

    public String getAllSequentialSortResults(String name){

        String retMessage = "";

        Person person = sequentialSearch(name);
        if ( person == null){
            return retMessage = "There is no person with the name " + name;
        }

        retMessage += String.format("%-20s %-4s %n", "Name", "Age");
        retMessage += String.format("%-20s %-4s %n", "----", "---");
        retMessage += String.format("%-20s %-4d %n", person.getName(), person.getAge());
        retMessage += String.format("%nNumber of Comparisons: %d", numComparisons);

        return retMessage;
    }

    public String getAllBinarySortResults(String name){

        String retMessage = "";

        Person person = binarySearch(name);
        if ( person == null){
            return retMessage = "There is no person with the name " + name;
        }

        retMessage += String.format("%-20s %-4s %n", "Name", "Age");
        retMessage += String.format("%-20s %-4s %n", "----", "---");
        retMessage += String.format("%-20s %-4d %n", person.getName(), person.getAge());
        retMessage += String.format("%nNumber of Comparisons: %d", numComparisons);

        return retMessage;
    }

    public String getStatistics() {
        int[] ages = new int[people.length];
        for (int i=0; i<people.length; i++) {
            ages[i] = people[i].getAge();
        }
        Statistics statistics = new Statistics(ages);
        return statistics.getStatistics();
    }

    public boolean modify(String oldName, String newName, int newAge) {

        // find and modify the person
        for (int i=0; i<people.length; i++) {
            if ( people[i].getName().equalsIgnoreCase(oldName) ) {
                people[i].setName(newName);
                people[i].setAge(newAge);
                return true; // found
            }
        }

        return false; // not found
    }

    public boolean delete(String name) {

        // find and delete the person
        for (int i=0; i<people.length; i++) {
            if ( people[i].getName().equalsIgnoreCase(name) ) {

                Person[] people2 = new Person[people.length-1];
                for (int j=0; j<i; j++) {
                    people2[j] = people[j]; // copy over
                }
                for (int j=i+1; j<people.length; j++) {
                    people2[j-1] = people[j]; // copy over
                }
                people = people2; // replace array with new array
                return true; // found
            }
        }

        return false; // not found
    }

}