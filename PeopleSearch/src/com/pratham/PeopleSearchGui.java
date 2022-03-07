package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.IntegerField;
import BreezySwing.MessageBox;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

public class PeopleSearchGui  extends GBFrame {

    PersonArray personArray = new PersonArray();

    //Main panel
    private GBPanel mainPanel;

    // menus
    private JMenuItem addNewPersonMenu;
    private JMenuItem showAllPeopleMenu;
    private JMenuItem searchByNameSequentialMenu;
    private JMenuItem searchByNameBinaryMenu;
    private JMenuItem quitMenu;

    //add Person fields
    private JTextField nameField;
    private IntegerField ageField;
    private JButton addBtn;

    public PeopleSearchGui() {

        createTestData();

        //main panel
        mainPanel = addPanel(1, 1, 1, 1);
        createInfoScreen();

        //add menu items
        addNewPersonMenu = addMenuItem("People", "Add Person");
        showAllPeopleMenu = addMenuItem("People", "Show all People");
        searchByNameSequentialMenu = addMenuItem("People", "Search Sequential");
        searchByNameBinaryMenu = addMenuItem("People", "Search Binary");
        quitMenu = addMenuItem("People", "Quit");

    }

    //create test data
    public void createTestData() {
        personArray.addPerson("Tom Sawyer", 44);
        personArray.addPerson("Tim Burns", 38);
        personArray.addPerson("Aaron Rogers", 42);
        personArray.addPerson("Kelly Chen", 32);
        personArray.addPerson("Ben Stiles", 25);
        personArray.addPerson("Evelyn Masters", 22);
        personArray.addPerson("Kim Cho", 33);
        personArray.addPerson("Zara Khan", 29);
        personArray.addPerson("John Smith", 22);
        personArray.addPerson("Jane Seymour", 18);
        personArray.addPerson("Quentin Brown", 33);
        personArray.addPerson("Carol Freer", 26);
        personArray.addPerson("Doug Morrison", 37);
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

    //Method for button clicks
    public void buttonClicked(JButton buttonObj) {
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

        }  else if (menuItem == searchByNameBinaryMenu) {

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
