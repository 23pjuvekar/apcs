package com.pratham;

import BreezySwing.GBFrame;
import BreezySwing.GBPanel;
import BreezySwing.IntegerField;

import javax.swing.*;

import java.awt.*;

import static java.lang.System.exit;

public class StudentDatabaseGui extends GBFrame {

    private Database database = new Database();

    private GBPanel mainPanel;

    //Drop down setup
    private JMenuItem addNewPersonMenu;
    private JMenuItem addNewStudentMenu;
    private JMenuItem addNewUnderGraduateMenu;
    private JMenuItem addNewGraduateMenu;
    private JMenuItem printAllPersonsMenu;
    private JMenuItem printAllStudentsMenu;
    private JMenuItem printAllUnderGraduatesMenu;
    private JMenuItem printAllGraduatesMenu;
    private JMenuItem findByGradeLevelMenu;
    private JMenuItem findByMajorMenu;
    private JMenuItem editPersonMenu;
    private JMenuItem quitMenu;

    //add screen items
    private JTextField nameField;
    private IntegerField idField;
    private JTextField gradeLevelField;
    private JTextField majorField;
    private JButton addPersonButton;
    private JButton addStudentButton;
    private JButton addUnderGraduateButton;
    private JButton addGraduateButton;

    //find buttons
    private JButton findByGradeLevelButton;
    private JButton findByMajorButton;
    private JButton editFindPersonButton;
    private JButton updatePersonButton;
    private JButton updateStudentButton;
    private JButton updateUnderGraduateButton;
    private JButton updateGraduateButton;

    private Person personToUpdate;

    //constructor
    public StudentDatabaseGui() {
        // add menu
        addNewPersonMenu = addMenuItem("Database","Add new Person");
        addNewStudentMenu = addMenuItem("Database","Add new Student");
        addNewUnderGraduateMenu = addMenuItem("Database","Add new UnderGraduate");
        addNewGraduateMenu = addMenuItem("Database","Add new Graduate");
        printAllPersonsMenu = addMenuItem("Database", "Print all Persons");
        printAllStudentsMenu = addMenuItem("Database", "Print all Students");
        printAllUnderGraduatesMenu = addMenuItem("Database", "Print all UnderGraduates");
        printAllGraduatesMenu = addMenuItem("Database", "Print all Graduates");
        findByGradeLevelMenu = addMenuItem("Database", "Find by Grade Level");
        findByMajorMenu = addMenuItem("Database", "Find by Major");
        editPersonMenu = addMenuItem("Database", "Edit Person");
        quitMenu = addMenuItem("Database","Quit");

        populateInitialData();

        refreshMainPanel();
        showSummaryScreen();
    }

    public void populateInitialData() {
        database.addPerson(new Person("John Smith"));
        database.addPerson(new Person("Jane CarMichael"));
        database.addPerson(new Student("Jack McDowell", 14223));
        database.addPerson(new Student("Sam Johnson", 17321));
        database.addPerson(new UnderGraduate("Tim McCarthy", 15442, "freshmen"));
        database.addPerson(new UnderGraduate("Tom Henry", 64555, "junior"));
        database.addPerson(new UnderGraduate("Harry Hanks", 44675, "senior"));
        database.addPerson(new Graduate("Jim Carey", 75664, "Physics"));
        database.addPerson(new Graduate("Chris Rock", 94663, "Statistics"));
    }

    public void refreshMainPanel() {
        if (mainPanel != null) {
            this.remove(mainPanel); //remove previous screen
        }
        mainPanel = addPanel(1, 1, 1, 1); //add new panel
    }

    private void addTextArea(GBPanel panel, String msg) {
        JTextArea infoField = panel.addTextArea(msg, 1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,16);
        infoField.setFont(font);
    }

    public void showSummaryScreen() {
        this.setSize(800,300);
        String msg = "\nThere are currently "+database.getPersonCount()+" persons in the system.\n\nChoose an operation from the dropdown menu.";
        addTextArea(mainPanel, msg);
        revalidate();
    }

    public void showAllPersonsScreen() {
        this.setSize(800,300);
        String toPrint = "";
        for ( int i=0; i<database.getPersonCount(); i++ ) {
            Person person = database.getPerson(i);
            toPrint += person.print() + "\n";
        }
        addTextArea(mainPanel, toPrint);
        revalidate();
    }

    public void showAllStudentsScreen() {
        this.setSize(800,300);
        String toPrint = "";
        for ( int i=0; i<database.getPersonCount(); i++ ) {
            Person person = database.getPerson(i);
            if ( person instanceof Student ) {
                toPrint += person.print() + "\n";
            }
        }
        addTextArea(mainPanel, toPrint);
        revalidate();
    }

    public void showAllUnderGraduatesScreen() {
        this.setSize(800,300);
        String toPrint = "";
        for ( int i=0; i<database.getPersonCount(); i++ ) {
            Person person = database.getPerson(i);
            if ( person instanceof UnderGraduate ) {
                toPrint += person.print() + "\n";
            }
        }
        addTextArea(mainPanel, toPrint);
        revalidate();
    }

    public void showAllGraduatesScreen() {
        this.setSize(800,300);
        String toPrint = "";
        for ( int i=0; i<database.getPersonCount(); i++ ) {
            Person person = database.getPerson(i);
            if ( person instanceof Graduate ) {
                toPrint += person.print() + "\n";
            }
        }
        addTextArea(mainPanel, toPrint);
        revalidate();
    }

    public void showMatchingGradeLevelScreen(String gradeLevel) {
        this.setSize(800,300);
        String toPrint = "";
        for ( int i=0; i<database.getPersonCount(); i++ ) {
            Person person = database.getPerson(i);
            if ( person instanceof UnderGraduate ) {
                UnderGraduate underGraduate = (UnderGraduate)person;
                if ( underGraduate.equals(new UnderGraduate("", 0, gradeLevel)) ) {
                    toPrint += person.print() + "\n";
                }
            }
        }
        if ( toPrint.isEmpty() ) {
            toPrint = "No matching undergraduates found for grade level: " + gradeLevel;
        }
        addTextArea(mainPanel, toPrint);
        revalidate();
    }

    public void showMatchingMajorScreen(String major) {
        this.setSize(800,300);
        String toPrint = "";
        for ( int i=0; i<database.getPersonCount(); i++ ) {
            Person person = database.getPerson(i);
            if ( person instanceof Graduate ) {
                Graduate graduate = (Graduate)person;
                if ( graduate.equals(new Graduate("", 0, major)) ) {
                    toPrint += person.print() + "\n";
                }
            }
        }
        if ( toPrint.isEmpty() ) {
            toPrint = "No matching graduates found for major: " + major;
        }
        addTextArea(mainPanel, toPrint);
        revalidate();
    }

    public void showAddNewPersonScreen() {
        this.setSize(600, 100);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField("", 1, 2, 1, 1);
        addPersonButton = mainPanel.addButton("Add", 2, 1, 2, 1);
    }

    public void showAddNewStudentScreen() {
        this.setSize(600, 125);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField("", 1, 2, 1, 1);
        mainPanel.addLabel("Student ID", 2, 1, 1, 1);
        idField = mainPanel.addIntegerField(1234, 2, 2, 1, 1);
        addStudentButton = mainPanel.addButton("Add", 3, 1, 2, 1);
    }

    public void showAddNewUnderGraduateScreen() {
        this.setSize(600, 150);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField("", 1, 2, 1, 1);
        mainPanel.addLabel("Student ID", 2, 1, 1, 1);
        idField = mainPanel.addIntegerField(1234, 2, 2, 1, 1);
        mainPanel.addLabel("Grade Level", 3, 1, 1, 1);
        gradeLevelField = mainPanel.addTextField("", 3, 2, 1, 1);
        addUnderGraduateButton = mainPanel.addButton("Add", 4, 1, 2, 1);
    }

    public void showAddNewGraduateScreen() {
        this.setSize(600, 150);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField("", 1, 2, 1, 1);
        mainPanel.addLabel("Student ID", 2, 1, 1, 1);
        idField = mainPanel.addIntegerField(1234, 2, 2, 1, 1);
        mainPanel.addLabel("Major", 3, 1, 1, 1);
        majorField = mainPanel.addTextField("", 3, 2, 1, 1);
        addGraduateButton = mainPanel.addButton("Add", 4, 1, 2, 1);
    }

    public void showFindByGradeLevelScreen() {
        this.setSize(600, 100);
        mainPanel.addLabel("Grade Level", 1, 1, 1, 1);
        gradeLevelField = mainPanel.addTextField("", 1, 2, 1, 1);
        findByGradeLevelButton = mainPanel.addButton("Find", 2, 1, 2, 1);
    }

    public void showFindByMajorScreen() {
        this.setSize(600, 100);
        mainPanel.addLabel("Major", 1, 1, 1, 1);
        majorField = mainPanel.addTextField("", 1, 2, 1, 1);
        findByMajorButton = mainPanel.addButton("Find", 2, 1, 2, 1);
    }

    public void showEditFindPersonScreen() {
        this.setSize(600, 100);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField("", 1, 2, 1, 1);
        editFindPersonButton = mainPanel.addButton("Find", 2, 1, 2, 1);
    }

    public void showEditPersonScreen(Person person) {
        personToUpdate = person;
        if ( person instanceof UnderGraduate ) {
            UnderGraduate underGraduate = (UnderGraduate)person;
            this.setSize(600, 150);
            mainPanel.addLabel("Name", 1, 1, 1, 1);
            nameField = mainPanel.addTextField(underGraduate.getName(), 1, 2, 1, 1);
            mainPanel.addLabel("Student ID", 2, 1, 1, 1);
            idField = mainPanel.addIntegerField(underGraduate.getStudentID(), 2, 2, 1, 1);
            mainPanel.addLabel("Grade Level", 3, 1, 1, 1);
            gradeLevelField = mainPanel.addTextField(underGraduate.getGradeLevel(), 3, 2, 1, 1);
            updateUnderGraduateButton = mainPanel.addButton("Update", 4, 1, 2, 1);
        } else if ( person instanceof Graduate ) {
            Graduate graduate = (Graduate)person;
            this.setSize(600, 150);
            mainPanel.addLabel("Name", 1, 1, 1, 1);
            nameField = mainPanel.addTextField(graduate.getName(), 1, 2, 1, 1);
            mainPanel.addLabel("Student ID", 2, 1, 1, 1);
            idField = mainPanel.addIntegerField(graduate.getStudentID(), 2, 2, 1, 1);
            mainPanel.addLabel("Major", 3, 1, 1, 1);
            majorField = mainPanel.addTextField(graduate.getMajor(), 3, 2, 1, 1);
            updateGraduateButton = mainPanel.addButton("Update", 4, 1, 2, 1);
        } else if ( person instanceof Student ) {
            Student student = (Student)person;
            this.setSize(600, 150);
            mainPanel.addLabel("Name", 1, 1, 1, 1);
            nameField = mainPanel.addTextField(student.getName(), 1, 2, 1, 1);
            mainPanel.addLabel("Student ID", 2, 1, 1, 1);
            idField = mainPanel.addIntegerField(student.getStudentID(), 2, 2, 1, 1);
            updateStudentButton = mainPanel.addButton("Update", 4, 1, 2, 1);
        } else {
            this.setSize(600, 150);
            mainPanel.addLabel("Name", 1, 1, 1, 1);
            nameField = mainPanel.addTextField(person.getName(), 1, 2, 1, 1);
            updatePersonButton = mainPanel.addButton("Update", 4, 1, 2, 1);
        }
    }

    //Method for button clicks
    public void buttonClicked(JButton buttonObj) {
        if ( buttonObj == addPersonButton ) {
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            Person person = new Person(nameField.getText());
            if ( !database.addPerson(person) ) {
                messageBox("You have reached the maximum number of persons to add.");
            }
        } else if ( buttonObj == addStudentButton ) {
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            if ( !idField.isValidNumber() ) {
                messageBox("ID has to be a valid number");
                idField.requestFocus();
                return;
            }
            Person person = new Student(nameField.getText(), idField.getNumber());
            if ( !database.addPerson(person) ) {
                messageBox("You have reached the maximum number of persons to add.");
            }
        } else if ( buttonObj == addUnderGraduateButton ) {
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            if ( !idField.isValidNumber() ) {
                messageBox("ID has to be a valid number");
                idField.requestFocus();
                return;
            }
            if ( !gradeLevelField.getText().equals("freshmen") &&
                    !gradeLevelField.getText().equals("sophomore") &&
                    !gradeLevelField.getText().equals("junior") &&
                    !gradeLevelField.getText().equals("senior") ) {
                messageBox("Grade level has to be a valid value:\nfreshmen, sophomore, junior, senior");
                gradeLevelField.requestFocus();
                return;
            }
            Person person = new UnderGraduate(nameField.getText(), idField.getNumber(), gradeLevelField.getText());
            if ( !database.addPerson(person) ) {
                messageBox("You have reached the maximum number of persons to add.");
            }
        } else if (buttonObj == addGraduateButton ) {
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            if ( !idField.isValidNumber() ) {
                messageBox("ID has to be a valid number");
                idField.requestFocus();
                return;
            }
            if ( majorField.getText().isEmpty() ) {
                messageBox("Major cannot be empty");
                majorField.requestFocus();
                return;
            }
            Person person = new Graduate(nameField.getText(), idField.getNumber(), majorField.getText());
            if ( !database.addPerson(person) ) {
                messageBox("You have reached the maximum number of persons to add.");
            }
        } else if ( buttonObj == findByGradeLevelButton ) {
            if ( !gradeLevelField.getText().equals("freshmen") &&
                    !gradeLevelField.getText().equals("sophomore") &&
                    !gradeLevelField.getText().equals("junior") &&
                    !gradeLevelField.getText().equals("senior") ) {
                messageBox("Grade level has to be a valid value:\nfreshmen, sophomore, junior, senior");
                gradeLevelField.requestFocus();
                return;
            }
            refreshMainPanel();
            showMatchingGradeLevelScreen(gradeLevelField.getText());
            return;
        } else if ( buttonObj == findByMajorButton ) {
            if ( majorField.getText().isEmpty() ) {
                messageBox("Major cannot be empty");
                majorField.requestFocus();
                return;
            }
            refreshMainPanel();
            showMatchingMajorScreen(majorField.getText());
            return;
        } else if ( buttonObj == editFindPersonButton ) {
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            for ( int i=0; i<database.getPersonCount(); i++ ) {
                Person person = database.getPerson(i);
                if ( person.getName().contains(nameField.getText()) ) {
                    refreshMainPanel();
                    showEditPersonScreen(person);
                    return;
                }
            }
            messageBox("Person with Name: " + nameField.getText() + " not found.");
            nameField.requestFocus();
            return;
        } else if ( buttonObj == updateUnderGraduateButton ) {
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            if ( !idField.isValidNumber() ) {
                messageBox("ID has to be a valid number");
                idField.requestFocus();
                return;
            }
            if ( !gradeLevelField.getText().equals("freshmen") &&
                    !gradeLevelField.getText().equals("sophomore") &&
                    !gradeLevelField.getText().equals("junior") &&
                    !gradeLevelField.getText().equals("senior") ) {
                messageBox("Grade level has to be a valid value:\nfreshmen, sophomore, junior, senior");
                gradeLevelField.requestFocus();
                return;
            }
            UnderGraduate underGraduate = (UnderGraduate)personToUpdate;
            underGraduate.setName(nameField.getText());
            underGraduate.setStudentID(idField.getNumber());
            underGraduate.setGradeLevel(gradeLevelField.getText());
        } else if ( buttonObj == updateGraduateButton ) {
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            if ( !idField.isValidNumber() ) {
                messageBox("ID has to be a valid number");
                idField.requestFocus();
                return;
            }
            if ( majorField.getText().isEmpty() ) {
                messageBox("Major cannot be empty");
                majorField.requestFocus();
                return;
            }
            Graduate graduate = (Graduate)personToUpdate;
            graduate.setName(nameField.getText());
            graduate.setStudentID(idField.getNumber());
            graduate.setMajor(majorField.getText());
        } else if ( buttonObj == updateStudentButton ) {
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            if ( !idField.isValidNumber() ) {
                messageBox("ID has to be a valid number");
                idField.requestFocus();
                return;
            }
            Student student = (Student)personToUpdate;
            student.setName(nameField.getText());
            student.setStudentID(idField.getNumber());
        } else if ( buttonObj == updatePersonButton ) {
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty");
                nameField.requestFocus();
                return;
            }
            personToUpdate.setName(nameField.getText());
        }
        refreshMainPanel();
        showSummaryScreen();
    }

    //Method for Drop Down
    public void menuItemSelected(JMenuItem menuItem) {
        refreshMainPanel();
        if ( menuItem == addNewPersonMenu ) {
            showAddNewPersonScreen();
        } else if ( menuItem == addNewStudentMenu ) {
            showAddNewStudentScreen();
        } else if ( menuItem == addNewUnderGraduateMenu ) {
            showAddNewUnderGraduateScreen();
        } else if ( menuItem == addNewGraduateMenu ) {
            showAddNewGraduateScreen();
        } else if ( menuItem == printAllPersonsMenu ) {
            showAllPersonsScreen();
        } else if ( menuItem == printAllStudentsMenu ) {
            showAllStudentsScreen();
        } else if ( menuItem == printAllUnderGraduatesMenu ) {
            showAllUnderGraduatesScreen();
        } else if ( menuItem == printAllGraduatesMenu ) {
            showAllGraduatesScreen();
        } else if ( menuItem == findByGradeLevelMenu ) {
            showFindByGradeLevelScreen();
        } else if ( menuItem == findByMajorMenu ) {
            showFindByMajorScreen();
        } else if ( menuItem == editPersonMenu ) {
            showEditFindPersonScreen();
        } else if ( menuItem == quitMenu ) {
            exit(0);
        }
        revalidate();
    }

    public static void main(String[] args) {
        JFrame frm = new StudentDatabaseGui();
        frm.setTitle("Student Database");
        frm.setSize (800, 300);
        frm.setLocationRelativeTo(null); // this displays JFrame to center position of a screen
        frm.setVisible (true);
    }
}
