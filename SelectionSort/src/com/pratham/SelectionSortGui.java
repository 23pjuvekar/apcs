package com.pratham;

import BreezySwing.*;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

public class SelectionSortGui extends GBFrame  {

    AllStudents allStudents = new AllStudents();

    //Main panel
    private GBPanel mainPanel;

    // menus
    private JMenuItem addNewStudentMenu;
    private JMenuItem showSortedByNameMenu;
    private JMenuItem showSortedByFinalAvgMenu;
    private JMenuItem showStatisticsMenu;
    private JMenuItem addTestDataMenu;
    private JMenuItem clearAllDataMenu;
    private JMenuItem quitMenu;

    //add student fields
    private JTextField  nameField;
    private IntegerField test1Field, test2Field, test3Field, test4Field, test5Field;
    private IntegerField quiz1Field, quiz2Field, quiz3Field, quiz4Field, quiz5Field;
    private IntegerField quiz6Field, quiz7Field, quiz8Field;
    private IntegerField hwField;
    private JButton     addBtn;

    public SelectionSortGui() {

        //createTestData();

        //main panel
        mainPanel = addPanel(1,1,1,1);
        createInfoScreen();

        //add menu items
        addNewStudentMenu = addMenuItem("Students", "Add Student");
        showSortedByNameMenu = addMenuItem("Students", "Sort Students by Name");
        showSortedByFinalAvgMenu = addMenuItem("Students", "Sort Students by Grades");
        showStatisticsMenu = addMenuItem("Students", "Show Statistics");
        addTestDataMenu = addMenuItem("Students", "Add Test Data");
        clearAllDataMenu = addMenuItem("Students", "Clear All Data");
        quitMenu = addMenuItem("Students", "Quit");
    }

    //create test data
    public void createTestData() {

        StudentInfo studentInfo = allStudents.addStudent("Alex Smith");
        double[] testGrades = {75, 88, 87, 79, 85};
        studentInfo.setTestGrade(testGrades);
        double[] quizGrades = {78, 70, 88, 84, 85, 82, 89, 91, 94, 95};
        studentInfo.setQuizGrade(quizGrades);
        studentInfo.setHomeworkAvg(80.0);

        StudentInfo studentInfo2 = allStudents.addStudent("Jane Graham");
        double[] testGrades2 = {85, 98, 97, 99, 95};
        studentInfo2.setTestGrade(testGrades2);
        double[] quizGrades2 = {98, 90, 88, 94, 85, 92, 99, 91, 87, 95};
        studentInfo2.setQuizGrade(quizGrades2);
        studentInfo2.setHomeworkAvg(95.0);

        StudentInfo studentInfo3 = allStudents.addStudent("Peter Thomson");
        double[] testGrades3 = {77, 82, 86, 79, 88};
        studentInfo3.setTestGrade(testGrades3);
        double[] quizGrades3 = {79, 85, 81, 93, 95, 83, 95, 78, 99, 91};
        studentInfo3.setQuizGrade(quizGrades3);
        studentInfo3.setHomeworkAvg(92.0);

        StudentInfo studentInfo4 = allStudents.addStudent("John Calhoun");
        double[] testGrades4 = {82, 77, 79, 86, 82};
        studentInfo4.setTestGrade(testGrades4);
        double[] quizGrades4 = {89, 95, 91, 93, 85, 93, 85, 88, 99, 94};
        studentInfo4.setQuizGrade(quizGrades4);
        studentInfo4.setHomeworkAvg(98.0);

        StudentInfo studentInfo5 = allStudents.addStudent("Jonah Reese");
        double[] testGrades5 = {77, 82, 86, 79, 88};
        studentInfo5.setTestGrade(testGrades5);
        double[] quizGrades5 = {79, 85, 81, 93, 95, 83, 95, 78, 99, 91};
        studentInfo5.setQuizGrade(quizGrades5);
        studentInfo5.setHomeworkAvg(92.0);
    }

    //show general info screen
    public void createInfoScreen() {
        this.setSize(800,200);
        String msg = "\nThere are currently "+allStudents.getNumStudents()+" students in the system.\n\nChoose an operation from the dropdown menu.";
        JTextArea infoField = mainPanel.addTextArea(msg, 1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,16);
        infoField.setFont(font);
        revalidate();
    }

    public void showAddStudentScreen() {

        this.setSize(800,500);

        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField("", 1, 2, 2, 1);
        nameField.requestFocus();

        mainPanel.addLabel("Test 1 grade", 2, 1, 1, 1);
        test1Field = mainPanel.addIntegerField(0, 2, 2, 2, 1);
        mainPanel.addLabel("Test 2 grade", 3, 1, 1, 1);
        test2Field = mainPanel.addIntegerField(0, 3, 2, 2, 1);
        mainPanel.addLabel("Test 3 grade", 4, 1, 1, 1);
        test3Field = mainPanel.addIntegerField(0, 4, 2, 2, 1);
        mainPanel.addLabel("Test 4 grade", 5, 1, 1, 1);
        test4Field = mainPanel.addIntegerField(0, 5, 2, 2, 1);
        mainPanel.addLabel("Test 5 grade", 6, 1, 1, 1);
        test5Field = mainPanel.addIntegerField(0, 6, 2, 2, 1);

        mainPanel.addLabel("Quiz 1 grade", 7, 1, 1, 1);
        quiz1Field = mainPanel.addIntegerField(0, 7, 2, 2, 1);
        mainPanel.addLabel("Quiz 2 grade", 8, 1, 1, 1);
        quiz2Field = mainPanel.addIntegerField(0, 8, 2, 2, 1);
        mainPanel.addLabel("Quiz 3 grade", 9, 1, 1, 1);
        quiz3Field = mainPanel.addIntegerField(0, 9, 2, 2, 1);
        mainPanel.addLabel("Quiz 4 grade", 10, 1, 1, 1);
        quiz4Field = mainPanel.addIntegerField(0, 10, 2, 2, 1);
        mainPanel.addLabel("Quiz 5 grade", 11, 1, 1, 1);
        quiz5Field = mainPanel.addIntegerField(0, 11, 2, 2, 1);
        mainPanel.addLabel("Quiz 6 grade", 12, 1, 1, 1);
        quiz6Field = mainPanel.addIntegerField(0, 12, 2, 2, 1);
        mainPanel.addLabel("Quiz 7 grade", 13, 1, 1, 1);
        quiz7Field = mainPanel.addIntegerField(0, 13, 2, 2, 1);
        mainPanel.addLabel("Quiz 8 grade", 14, 1, 1, 1);
        quiz8Field = mainPanel.addIntegerField(0, 14, 2, 2, 1);

        mainPanel.addLabel("Homework grade", 15, 1, 1, 1);
        hwField = mainPanel.addIntegerField(0, 15, 2, 2, 1);

        addBtn = mainPanel.addButton("Add", 16, 1, 3, 1);

        revalidate();
    }

    public void showSortedByNameScreen() {
        this.setSize(800,200);
        JTextArea infoField = mainPanel.addTextArea(
                allStudents.getAllStudentsSortedByName(),
                1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        revalidate();
    }

    public void showSortedByFinalAvgScreen() {
        this.setSize(800,200);
        JTextArea infoField = mainPanel.addTextArea(
                allStudents.getAllStudentsSortedByFinalAverage(),
                1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        revalidate();
    }

    public void showStatisticsScreen() {
        Statistics statistics = new Statistics(allStudents.getAllFinalAverages());
        this.setSize(800,200);
        JTextArea infoField = mainPanel.addTextArea(
                statistics.getStatistics(),
                1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,14);
        infoField.setFont(font);
        revalidate();
    }

    private boolean checkInputs() {

        if ( nameField.getText().isEmpty() ) {
            showMessageBox("Name cannot be empty");
            nameField.requestFocus();
            return false;
        }

        if ( (test1Field.getNumber() < 60 && test1Field.getNumber() != 0) || test1Field.getNumber() > 100 ) {
            showMessageBox("Test 1 grade has to be between 60 and 100");
            test1Field.requestFocus();
            return false;
        }
        if ( (test2Field.getNumber() < 60 && test2Field.getNumber() != 0) || test2Field.getNumber() > 100 ) {
            showMessageBox("Test 2 grade has to be between 60 and 100");
            test2Field.requestFocus();
            return false;
        }
        if ( (test3Field.getNumber() < 60 && test3Field.getNumber() != 0) || test3Field.getNumber() > 100 ) {
            showMessageBox("Test 3 grade has to be between 60 and 100");
            test3Field.requestFocus();
            return false;
        }
        if ( (test4Field.getNumber() < 60 && test4Field.getNumber() != 0) || test4Field.getNumber() > 100 ) {
            showMessageBox("Test 4 grade has to be between 60 and 100");
            test4Field.requestFocus();
            return false;
        }
        if ( (test5Field.getNumber() < 60 && test5Field.getNumber() != 0) || test5Field.getNumber() > 100 ) {
            showMessageBox("Test 5 grade has to be between 60 and 100");
            test5Field.requestFocus();
            return false;
        }

        if ( (quiz1Field.getNumber() < 60 && quiz1Field.getNumber() != 0) || quiz1Field.getNumber() > 100 ) {
            showMessageBox("Quiz 1 grade has to be between 60 and 100");
            quiz1Field.requestFocus();
            return false;
        }
        if ( (quiz2Field.getNumber() < 60 && quiz2Field.getNumber() != 0) || quiz2Field.getNumber() > 100 ) {
            showMessageBox("Quiz 2 grade has to be between 60 and 100");
            quiz2Field.requestFocus();
            return false;
        }
        if ( (quiz3Field.getNumber() < 60 && quiz3Field.getNumber() != 0) || quiz3Field.getNumber() > 100 ) {
            showMessageBox("Quiz 3 grade has to be between 60 and 100");
            quiz3Field.requestFocus();
            return false;
        }
        if ( (quiz4Field.getNumber() < 60 && quiz4Field.getNumber() != 0) || quiz4Field.getNumber() > 100 ) {
            showMessageBox("Quiz 4 grade has to be between 60 and 100");
            quiz4Field.requestFocus();
            return false;
        }
        if ( (quiz5Field.getNumber() < 60 && quiz5Field.getNumber() != 0) || quiz5Field.getNumber() > 100 ) {
            showMessageBox("Quiz 5 grade has to be between 60 and 100");
            quiz5Field.requestFocus();
            return false;
        }
        if ( (quiz6Field.getNumber() < 60 && quiz6Field.getNumber() != 0) || quiz6Field.getNumber() > 100 ) {
            showMessageBox("Quiz 6 grade has to be between 60 and 100");
            quiz6Field.requestFocus();
            return false;
        }
        if ( (quiz7Field.getNumber() < 60 && quiz7Field.getNumber() != 0) || quiz7Field.getNumber() > 100 ) {
            showMessageBox("Quiz 7 grade has to be between 60 and 100");
            quiz7Field.requestFocus();
            return false;
        }
        if ( (quiz8Field.getNumber() < 60 && quiz8Field.getNumber() != 0) || quiz8Field.getNumber() > 100 ) {
            showMessageBox("Quiz 8 grade has to be between 60 and 100");
            quiz8Field.requestFocus();
            return false;
        }

        if ( hwField.getNumber() < 60 || hwField.getNumber() > 100 ) {
            showMessageBox("Homework grade has to be between 60 and 100");
            hwField.requestFocus();
            return false;
        }

        return true;
    }

    //Method for button clicks
    public void buttonClicked(JButton buttonObj) {
        if ( buttonObj == addBtn ) {
            if ( !checkInputs() ) {
                return;
            }

            double[] testGrades = new double[5];
            testGrades[0] = test1Field.getNumber();
            testGrades[1] = test1Field.getNumber();
            testGrades[2] = test3Field.getNumber();
            testGrades[3] = test4Field.getNumber();
            testGrades[4] = test5Field.getNumber();

            double[] quizGrades = new double[8];
            quizGrades[0] = quiz1Field.getNumber();
            quizGrades[1] = quiz2Field.getNumber();
            quizGrades[2] = quiz3Field.getNumber();
            quizGrades[3] = quiz4Field.getNumber();
            quizGrades[4] = quiz5Field.getNumber();
            quizGrades[5] = quiz6Field.getNumber();
            quizGrades[6] = quiz7Field.getNumber();
            quizGrades[7] = quiz8Field.getNumber();

            double hwGrade = hwField.getNumber();

            StudentInfo studentInfo = allStudents.addStudent(nameField.getText());
            studentInfo.setTestGrade(testGrades);
            studentInfo.setQuizGrade(quizGrades);
            studentInfo.setHomeworkAvg(hwGrade);

            this.remove(mainPanel); //remove previous screen
            mainPanel = addPanel(1, 1, 1, 1); //add new panel
            createInfoScreen();
        }
    }

    //Method for Drop Down
    public void menuItemSelected(JMenuItem menuItem) {
        if (mainPanel != null) {
            this.remove(mainPanel); //remove previous screen
        }
        mainPanel = addPanel(1, 1, 1, 1); //add new panel
        if (menuItem == addNewStudentMenu) {
            if ( allStudents.getNumStudents() >= allStudents.MAX_STUDENTS ) {
                showMessageBox("There are "+allStudents.MAX_STUDENTS+ " students in the system.\nYou cannot add anymore students.");
                createInfoScreen();
            } else {
                showAddStudentScreen();
            }
        } else if (menuItem == showSortedByNameMenu) {
            showSortedByNameScreen();
        } else if (menuItem == showSortedByFinalAvgMenu) {
            showSortedByFinalAvgScreen();
        } else if (menuItem == showStatisticsMenu) {
            showStatisticsScreen();
        } else if (menuItem == addTestDataMenu ) {
            this.createTestData();
            createInfoScreen();
        } else if (menuItem == clearAllDataMenu ) {
            allStudents.clearStudents();
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
        JFrame frm = new SelectionSortGui();
        frm.setTitle("Student Information");
        frm.setSize (800, 200);
        frm.setLocationRelativeTo(null); // this displays JFrame to center position of a screen
        frm.setVisible (true);
    }
}
