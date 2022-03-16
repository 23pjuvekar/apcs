package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.IntegerField;
import BreezySwing.MessageBox;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

public class PeopleSearchGui extends GBFrame {

    PersonArray personArray = new PersonArray();

    //Main panel
    private GBPanel mainPanel;

    // menus
    private JMenuItem addNewPersonMenu;
    private JMenuItem showAllPeopleNameMenu;
    private JMenuItem showAllPeopleAgeMenu;
    private JMenuItem searchByNameSequentialMenu;
    private JMenuItem searchByNameBinaryMenu;
    private JMenuItem showAgeStatistics;
    private JMenuItem addTestData;
    private JMenuItem clearAllData;
    private JMenuItem quitMenu;

    //add Person fields
    private JTextField nameField;
    private JTextField nameSearchField;
    private IntegerField ageField;
    private JButton addBtn;
    private JButton seqSearchBtn;
    private JButton binSearchBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton saveBtn;

    final int MAX_PERSONS = 20;

    public PeopleSearchGui() {

        //main panel
        mainPanel = addPanel(1, 1, 1, 1);
        createInfoScreen();

        //add menu items
        addNewPersonMenu = addMenuItem("People", "Add Person");
        showAllPeopleNameMenu = addMenuItem("People", "Show all People ordered by Name");
        showAllPeopleAgeMenu = addMenuItem("People", "Show all People ordered by Age");
        searchByNameSequentialMenu = addMenuItem("People", "Search Sequential");
        searchByNameBinaryMenu = addMenuItem("People", "Search Binary");
        showAgeStatistics = addMenuItem("People", "Show Age Statistics");
        addTestData = addMenuItem("People", "Add Test Data");
        clearAllData = addMenuItem("People", "Clear All Data");
        quitMenu = addMenuItem("People", "Quit");

    }

    //create test data
    public void createTestData() {
        personArray = new PersonArray(); // reset all persons
        personArray.addPerson("Tom", 44);
        personArray.addPerson("Tim", 38);
        personArray.addPerson("Aaron", 42);
        personArray.addPerson("Kelly", 32);
        personArray.addPerson("Ben", 25);
        personArray.addPerson("Evelyn", 22);
        personArray.addPerson("Herbert", 22);
        personArray.addPerson("Kim", 33);
        personArray.addPerson("Zara", 29);
        personArray.addPerson("Abel", 52);
        personArray.addPerson("John", 32);
        personArray.addPerson("Carol", 27);
        personArray.addPerson("Jane", 18);
        personArray.addPerson("Quentin", 55);
        personArray.addPerson("Carol", 26);
        personArray.addPerson("Doug", 37);
        personArray.addPerson("Fred", 55);
        personArray.addPerson("Kate", 28);
        personArray.addPerson("Liam", 35);
        personArray.addPerson("BOB", 17);
    }

    //show general info screen
    public void createInfoScreen() {
        this.setSize(500,200);
        String msg = "\nThere are currently "+personArray.getNumPeople()+" people in the system.\n\nChoose an operation from the dropdown menu.";
        JTextArea infoField = mainPanel.addTextArea(msg, 1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,16);
        infoField.setFont(font);
        revalidate();
    }

    //add new person
    void createAddNewPersonScreen() {
        this.setSize(500,150);

        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField("", 1, 2, 2, 1);
        nameField.requestFocus();

        mainPanel.addLabel("Age", 2, 1, 1, 1);
        ageField = mainPanel.addIntegerField(0, 2, 2, 2, 1);

        addBtn = mainPanel.addButton("Add", 3, 1, 3, 1);

        revalidate();
    }

    //edit person
    void createEditPersonScreen() {
        this.setSize(500,150);

        Person person = personArray.sequentialSearch(nameSearchField.getText());

        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField(person.getName(), 1, 2, 2, 1);
        nameField.requestFocus();

        mainPanel.addLabel("Age", 2, 1, 1, 1);
        ageField = mainPanel.addIntegerField(person.getAge(), 2, 2, 2, 1);

        saveBtn = mainPanel.addButton("Save", 3, 1, 3, 1);

        revalidate();
    }

    //show all people by name
    void createShowAllPeopleNameScreen() {
        this.setSize(500,80+16*personArray.getNumPeople());
        JTextArea infoField = mainPanel.addTextArea(
                personArray.getAllPeopleSortedByName(),
                1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        revalidate();
    }

    //show all people by age
    void createShowAllPeopleAgeScreen() {
        this.setSize(500,80+16*personArray.getNumPeople());
        JTextArea infoField = mainPanel.addTextArea(
                personArray.getAllPeopleSortedByAge(),
                1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        revalidate();
    }

    private boolean CheckName() {
        if ( nameField.getText().isEmpty() ) {
            showMessageBox("Name cannot be empty");
            nameField.requestFocus();
            return false;
        }
        return true;
    }

    private boolean CheckNameSearch() {
        if ( nameSearchField.getText().isEmpty() ) {
            showMessageBox("Name cannot be empty");
            nameSearchField.requestFocus();
            return false;
        }
        return true;
    }

    private boolean CheckAge() {
        if ( !ageField.isValidNumber() ) {
            showMessageBox("Age needs to be a valid number");
            ageField.requestFocus();
            return false;
        }
        if ( ageField.getNumber() < 1 || ageField.getNumber() > 100 ) {
            showMessageBox("Age should be between 1 and 99");
            ageField.requestFocus();
            return false;
        }
        return true;
    }

    private void createSearchByNameSequentialScreen(){
        this.setSize(500,150);
        mainPanel.addLabel("Sequential Search", 1,1,1,1);
        mainPanel.addLabel("Name", 2,1,1,1);
        nameSearchField = mainPanel.addTextField("",2,2,2,1);
        nameSearchField.requestFocus();
        seqSearchBtn = mainPanel.addButton("Search",3,1,2,1);
    }

    private void showSequentialSearchResults(){
        this.setSize(500,150);
        JTextArea infoField = mainPanel.addTextArea(
                personArray.getAllSequentialSortResults(nameSearchField.getText()),
                1,1,2,1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        if ( personArray.sequentialSearch(nameSearchField.getText()) != null ) {
            editBtn = mainPanel.addButton("Edit", 2, 1, 1, 1);
            deleteBtn = mainPanel.addButton("Delete", 2, 2, 1, 1);
        }
        revalidate();
    }

    private void createSearchByNameBinaryScreen(){
        this.setSize(500,150);
        mainPanel.addLabel("Binary Search", 1,1,1,1);
        mainPanel.addLabel("Name", 2,1,1,1);
        nameSearchField = mainPanel.addTextField("",2,2,2,1);
        nameSearchField.requestFocus();
        binSearchBtn = mainPanel.addButton("Search",3,1,1,1);
    }

    private void showBinarySearchResults(){
        this.setSize(500,150);
        JTextArea infoField = mainPanel.addTextArea(
                personArray.getAllBinarySortResults(nameSearchField.getText()),
                1,1,2,1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        if ( personArray.binarySearch(nameSearchField.getText()) != null ) {
            editBtn = mainPanel.addButton("Edit", 2, 1, 1, 1);
            deleteBtn = mainPanel.addButton("Delete", 2, 2, 1, 1);
        }
        revalidate();
    }

    private void createShowAgeStatisticsScreen() {
        this.setSize(500,150);
        JTextArea infoField = mainPanel.addTextArea(
                personArray.getStatistics(),
                1,1,2,1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        revalidate();
    }

    private void resetScreen() {
        if (mainPanel != null) {
            this.remove(mainPanel); //remove previous screen
        }
        mainPanel = addPanel(1, 1, 1, 1); //add new panel
    }

    //Method for button clicks
    public void buttonClicked(JButton buttonObj) {
        if(buttonObj == addBtn){
            if ( !CheckName() || !CheckAge() ) {
                return;
            }
            if ( personArray.sequentialSearch(nameField.getText()) != null ) {
                showMessageBox("Person with name "+nameField.getText()+" already exists.\nPlease use a different name.");
                nameField.requestFocus();
                return;
            }
            personArray.addPerson(nameField.getText(), ageField.getNumber());
            resetScreen();
            createInfoScreen();
        } else if (buttonObj == seqSearchBtn){
            if ( !CheckNameSearch() ) {
                return;
            }
            resetScreen();
            showSequentialSearchResults();
        } else if (buttonObj == binSearchBtn){
            if ( !CheckNameSearch() ) {
                return;
            }
            resetScreen();
            showBinarySearchResults();
        } else if ( buttonObj == editBtn ) {
            resetScreen();
            createEditPersonScreen();
        } else if ( buttonObj == deleteBtn ) {
            if ( personArray.delete(nameSearchField.getText()) ) {
                showMessageBox("Deleted " + nameSearchField.getText() + " successfully");
            } else {
                showMessageBox("Could not delete " + nameSearchField.getText());
            }
            resetScreen();
            createInfoScreen();
        } else if ( buttonObj == saveBtn ) {
            if ( !CheckNameSearch() || !CheckName() || !CheckAge() ) {
                return;
            }
            if ( !nameSearchField.getText().equalsIgnoreCase(nameField.getText()) ) {
                if ( personArray.sequentialSearch(nameField.getText()) != null ) {
                    showMessageBox("Person with name " + nameField.getText() + " already exists.\nPlease use a different name.");
                    nameField.requestFocus();
                    return;
                }
            }
            if ( personArray.modify(nameSearchField.getText(), nameField.getText(), ageField.getNumber()) ) {
                showMessageBox("Saved " + nameField.getText() + " successfully");
            } else {
                showMessageBox("Could not save " + nameField.getText());
            }
            resetScreen();
            createInfoScreen();
        }
    }

    //Method for Drop Down
    public void menuItemSelected(JMenuItem menuItem) {
        resetScreen();
        if (menuItem == addNewPersonMenu) {
            if ( personArray.getNumPeople() >= MAX_PERSONS ) {
                showMessageBox("You have reached the limit of maximum "+MAX_PERSONS+" people.");
                createInfoScreen();
            } else {
                createAddNewPersonScreen();
            }
        } else if (menuItem == showAllPeopleNameMenu) {
            createShowAllPeopleNameScreen();
        } else if (menuItem == showAllPeopleAgeMenu) {
            createShowAllPeopleAgeScreen();
        }  else if (menuItem == searchByNameSequentialMenu) {
            createSearchByNameSequentialScreen();
        }  else if (menuItem == searchByNameBinaryMenu) {
            createSearchByNameBinaryScreen();
        } else if (menuItem == showAgeStatistics) {
            createShowAgeStatisticsScreen();
        } else if (menuItem == addTestData) {
            createTestData();
            createInfoScreen();
        } else if (menuItem == clearAllData) {
            personArray = new PersonArray(); //create new array to clear
            createInfoScreen();
        }  else if (menuItem == quitMenu) {
            exit(0);
        }
        revalidate();
    }

    private void showMessageBox(String msg) {
        MessageBox msgBox = new MessageBox( this, msg);
        msgBox.setSize(400, 100);
        msgBox.setLocationRelativeTo(null); //center
        msgBox.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frm = new PeopleSearchGui();
        frm.setTitle("People Search");
        frm.setSize (500, 200);
        frm.setLocationRelativeTo(null); // this displays JFrame to center position of a screen
        frm.setVisible (true);
    }
}
