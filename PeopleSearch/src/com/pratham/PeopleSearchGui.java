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
    private JMenuItem showAllPeopleMenu;
    private JMenuItem searchByNameSequentialMenu;
    private JMenuItem searchByNameBinaryMenu;
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

    public PeopleSearchGui() {

        //main panel
        mainPanel = addPanel(1, 1, 1, 1);
        createInfoScreen();

        //add menu items
        addNewPersonMenu = addMenuItem("People", "Add Person");
        showAllPeopleMenu = addMenuItem("People", "Show all People");
        searchByNameSequentialMenu = addMenuItem("People", "Search Sequential");
        searchByNameBinaryMenu = addMenuItem("People", "Search Binary");
        addTestData = addMenuItem("People", "Add Test Data");
        clearAllData = addMenuItem("People", "Clear All Data");
        quitMenu = addMenuItem("People", "Quit");

    }

    //create test data
    public void createTestData() {
        personArray.addPerson("Tom", 44);
        personArray.addPerson("Tim", 38);
        personArray.addPerson("Aaron", 42);
        personArray.addPerson("Kelly", 32);
        personArray.addPerson("Ben", 25);
        personArray.addPerson("Evelyn", 22);
        personArray.addPerson("Kim", 33);
        personArray.addPerson("Zara", 29);
        personArray.addPerson("John", 22);
        personArray.addPerson("Jane", 18);
        personArray.addPerson("Quentin", 33);
        personArray.addPerson("Carol", 26);
        personArray.addPerson("Doug", 37);
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

    //show all people
    void createShowAllPeopleScreen() {
        this.setSize(500,80+16*personArray.getNumPeople());
        JTextArea infoField = mainPanel.addTextArea(
                personArray.getAllPeopleSortedByName(),
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

    void createSearchByNameSequentialScreen(){
        this.setSize(500,150);
        mainPanel.addLabel("Sequential Search", 1,1,1,1);
        mainPanel.addLabel("Name", 2,1,1,1);
        nameSearchField = mainPanel.addTextField("",2,2,2,1);
        nameSearchField.requestFocus();
        seqSearchBtn = mainPanel.addButton("Search",3,1,2,1);
    }

    void showSequentialSearchResults(){
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

    void createSearchByNameBinaryScreen(){
        this.setSize(500,150);
        mainPanel.addLabel("Binary Search", 1,1,1,1);
        mainPanel.addLabel("Name", 2,1,1,1);
        nameSearchField = mainPanel.addTextField("",2,2,2,1);
        nameSearchField.requestFocus();
        binSearchBtn = mainPanel.addButton("Search",3,1,1,1);
    }

    void showBinarySearchResults(){
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
            if ( !CheckName() || !CheckAge() ) {
                return;
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
        if (mainPanel != null) {
            this.remove(mainPanel); //remove previous screen
        }
        mainPanel = addPanel(1, 1, 1, 1); //add new panel
        if (menuItem == addNewPersonMenu) {
            createAddNewPersonScreen();
        } else if (menuItem == showAllPeopleMenu) {
            createShowAllPeopleScreen();
        }  else if (menuItem == searchByNameSequentialMenu) {
            createSearchByNameSequentialScreen();
        }  else if (menuItem == searchByNameBinaryMenu) {
            createSearchByNameBinaryScreen();
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
