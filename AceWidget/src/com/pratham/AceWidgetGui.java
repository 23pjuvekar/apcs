package com.pratham;

import BreezySwing.*;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

public class AceWidgetGui extends GBFrame {

    //Employee Data
    EmployeeSales employeeSales = new EmployeeSales();

    //Main panel
    private GBPanel mainPanel;

    //Drop down setup
    private JMenuItem addNewEmployeeMenu;
    private JMenuItem showEmployeeSalesMenu;
    private JMenuItem showAllSalesMenu;
    private JMenuItem showHighestSalesMenu;
    private JMenuItem showLowestSalesMenu;
    private JMenuItem quitMenu;

    //add new employee fields
    JTextField  newNameField;
    DoubleField newQ1SalesField;
    DoubleField newQ2SalesField;
    DoubleField newQ3SalesField;
    DoubleField newQ4SalesField;
    JButton     newCreateNewBtn;

    //lookup fields
    JTextField  lookupNameField;
    JButton     lookupCreateNewBtn;
    GBPanel     employeeDetailsPanel;

    public AceWidgetGui() {

        addTestData(); //test data, comment out later

        //main panel
        mainPanel = addPanel(1,1,1,1);
        createInfoScreen();

        //add menu items
        addNewEmployeeMenu = addMenuItem("Employee","Add New");
        showEmployeeSalesMenu = addMenuItem("Employee","Lookup");
        showAllSalesMenu = addMenuItem("Employee","Show All Sales");
        showHighestSalesMenu = addMenuItem("Employee","Show Highest Sales");
        showLowestSalesMenu = addMenuItem("Employee","Show Lowest Sales");
        quitMenu = addMenuItem("Employee", "Quit");
    }

    //add test data
    public void addTestData() {
        employeeSales.addEmployee("Bob Cat", 1010, 200, 550, 390);
        employeeSales.addEmployee("Jack Knife", 150, 410, 100, 190);
        employeeSales.addEmployee("James Smith", 960, 423, 514, 190);
        employeeSales.addEmployee("Tim Burns", 200, 1010, 380, 560);
        employeeSales.addEmployee("John Appleseed", 410, 100, 150, 190);
    }

    //show general info screen
    public void createInfoScreen() {
        String msg = "\nThere are currently "+employeeSales.getEmployeeCount()+" employees in the system.\n\nChoose an operation from the dropdown menu.";
        JTextArea infoField = mainPanel.addTextArea(msg, 1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,16);
        infoField.setFont(font);
    }

    //create New Employee Screen
    public void createNewEmployeeScreen() {
        this.setSize(600,300);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        newNameField = mainPanel.addTextField("", 1, 2, 2, 1);
        newNameField.requestFocus();
        mainPanel.addLabel("Q1 Sales", 2, 1, 1, 1);
        newQ1SalesField = mainPanel.addDoubleField(0.0, 2, 2, 2, 1);
        newQ1SalesField.setPrecision(2);
        mainPanel.addLabel("Q2 Sales", 3, 1, 1, 1);
        newQ2SalesField = mainPanel.addDoubleField(0.0, 3, 2, 2, 1);
        newQ2SalesField.setPrecision(2);
        mainPanel.addLabel("Q3 Sales", 4, 1, 1, 1);
        newQ3SalesField = mainPanel.addDoubleField(0.0, 4, 2, 2, 1);
        newQ3SalesField.setPrecision(2);
        mainPanel.addLabel("Q4 Sales", 5, 1, 1, 1);
        newQ4SalesField = mainPanel.addDoubleField(0.0, 5, 2, 2, 1);
        newQ4SalesField.setPrecision(2);
        newCreateNewBtn = mainPanel.addButton("Create New", 6, 1, 3, 1);
    }

    public void makeBold(JLabel label) {
        Font font = label.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        label.setFont(boldFont);
    }

    //show all sales information
    public void createShowAllSalesScreen() {
        this.setSize(600,100+25*employeeSales.getEmployeeCount());
        makeBold(mainPanel.addLabel("#", 1, 1, 1, 1));
        makeBold(mainPanel.addLabel("Name", 1, 2, 1, 1));
        makeBold(mainPanel.addLabel("Q1 Sales", 1, 3, 1, 1));
        makeBold(mainPanel.addLabel("Q2 Sales", 1, 4, 1, 1));
        makeBold(mainPanel.addLabel("Q3 Sales", 1, 5, 1, 1));
        makeBold(mainPanel.addLabel("Q4 Sales", 1, 6, 1, 1));
        makeBold(mainPanel.addLabel("Total Sales", 1, 7, 1, 1));
        for (int i=0; i<employeeSales.getEmployeeCount(); i++) {
            mainPanel.addLabel((i+1)+"", i+2, 1, 1, 1);
            mainPanel.addLabel(employeeSales.getEmployee(i).getName(), i+2, 2, 1, 1);
            mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ1Sales()), i+2, 3, 1, 1);
            mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ2Sales()), i+2, 4, 1, 1);
            mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ3Sales()), i+2, 5, 1, 1);
            mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ4Sales()), i+2, 6, 1, 1);
            mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getTotalSales()), i+2, 7, 1, 1);
        }
    }

    //show an employees sales
    public void createShowEmployeeSalesScreen() {
        this.setSize(600,75);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        lookupNameField = mainPanel.addTextField("", 1, 2, 1, 1);
        lookupNameField.requestFocus();
        lookupCreateNewBtn = mainPanel.addButton("Lookup", 1, 3, 1, 1);
    }

    //show all high sales
    public void createShowHighestSales() {
        double highest = employeeSales.getHighestTotalSales();
        int highestCount = employeeSales.getHighestTotalSalesCount();
        this.setSize(600,100+25*highestCount);
        makeBold(mainPanel.addLabel("Highest Total Sales:", 1, 1, 1, 1));
        makeBold(mainPanel.addLabel("Name", 2, 1, 1, 1));
        makeBold(mainPanel.addLabel("Q1 Sales", 2, 2, 1, 1));
        makeBold(mainPanel.addLabel("Q2 Sales", 2, 3, 1, 1));
        makeBold(mainPanel.addLabel("Q3 Sales", 2, 4, 1, 1));
        makeBold(mainPanel.addLabel("Q4 Sales", 2, 5, 1, 1));
        makeBold(mainPanel.addLabel("Total Sales", 2, 6, 1, 1));
        int row=3;
        for (int i=0; i<employeeSales.getEmployeeCount(); i++) {
            if ( employeeSales.getEmployee(i).getTotalSales() == highest ) {
                mainPanel.addLabel(employeeSales.getEmployee(i).getName(), row, 1, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ1Sales()), row, 2, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ2Sales()), row, 3, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ3Sales()), row, 4, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ4Sales()), row, 5, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getTotalSales()), row, 6, 1, 1);
                row++;
            }
        }

        double highestQuarter = employeeSales.getHighestQuarterSales();
        int highestQuarterCount = employeeSales.getHighestQuarterSalesCount();
        this.setSize(600,100+25*(highestCount+highestQuarterCount));
        makeBold(mainPanel.addLabel("Highest Quarter Sales:", row++, 1, 1, 1));
        makeBold(mainPanel.addLabel("Name", row, 1, 1, 1));
        makeBold(mainPanel.addLabel("Q1 Sales", row, 2, 1, 1));
        makeBold(mainPanel.addLabel("Q2 Sales", row, 3, 1, 1));
        makeBold(mainPanel.addLabel("Q3 Sales", row, 4, 1, 1));
        makeBold(mainPanel.addLabel("Q4 Sales", row, 5, 1, 1));
        makeBold(mainPanel.addLabel("Total Sales", row++, 6, 1, 1));
        for (int i=0; i<employeeSales.getEmployeeCount(); i++) {
            if ( employeeSales.getEmployee(i).getQ1Sales() == highestQuarter ||
                    employeeSales.getEmployee(i).getQ2Sales() == highestQuarter ||
                    employeeSales.getEmployee(i).getQ3Sales() == highestQuarter ||
                    employeeSales.getEmployee(i).getQ4Sales() == highestQuarter ) {
                mainPanel.addLabel(employeeSales.getEmployee(i).getName(), row, 1, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ1Sales()), row, 2, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ2Sales()), row, 3, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ3Sales()), row, 4, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ4Sales()), row, 5, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getTotalSales()), row++, 6, 1, 1);
                row++;
            }
        }
    }

    //show all low sales
    public void createShowLowestSales() {
        double lowest = employeeSales.getLowestTotalSales();
        int lowestCount = employeeSales.getLowestTotalSalesCount();
        this.setSize(600,100+25*lowestCount);
        makeBold(mainPanel.addLabel("Lowest Total Sales:", 1, 1, 1, 1));
        makeBold(mainPanel.addLabel("Name", 2, 1, 1, 1));
        makeBold(mainPanel.addLabel("Q1 Sales", 2, 2, 1, 1));
        makeBold(mainPanel.addLabel("Q2 Sales", 2, 3, 1, 1));
        makeBold(mainPanel.addLabel("Q3 Sales", 2, 4, 1, 1));
        makeBold(mainPanel.addLabel("Q4 Sales", 2, 5, 1, 1));
        makeBold(mainPanel.addLabel("Total Sales", 2, 6, 1, 1));
        int row=3;
        for (int i=0; i<employeeSales.getEmployeeCount(); i++) {
            if ( employeeSales.getEmployee(i).getTotalSales() == lowest ) {
                mainPanel.addLabel(employeeSales.getEmployee(i).getName(), row, 1, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ1Sales()), row, 2, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ2Sales()), row, 3, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ3Sales()), row, 4, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ4Sales()), row, 5, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getTotalSales()), row, 6, 1, 1);
                row++;
            }
        }

        double lowestQuarter = employeeSales.getLowestQuarterSales();
        int lowestQuarterCount = employeeSales.getLowestQuarterSalesCount();
        this.setSize(600,100+25*(lowestCount+lowestQuarterCount));
        makeBold(mainPanel.addLabel("Lowest Quarter Sales:", row++, 1, 1, 1));
        makeBold(mainPanel.addLabel("Name", row, 1, 1, 1));
        makeBold(mainPanel.addLabel("Q1 Sales", row, 2, 1, 1));
        makeBold(mainPanel.addLabel("Q2 Sales", row, 3, 1, 1));
        makeBold(mainPanel.addLabel("Q3 Sales", row, 4, 1, 1));
        makeBold(mainPanel.addLabel("Q4 Sales", row, 5, 1, 1));
        makeBold(mainPanel.addLabel("Total Sales", row++, 6, 1, 1));
        for (int i=0; i<employeeSales.getEmployeeCount(); i++) {
            if ( employeeSales.getEmployee(i).getQ1Sales() == lowestQuarter ||
                    employeeSales.getEmployee(i).getQ2Sales() == lowestQuarter ||
                    employeeSales.getEmployee(i).getQ3Sales() == lowestQuarter ||
                    employeeSales.getEmployee(i).getQ4Sales() == lowestQuarter ) {
                mainPanel.addLabel(employeeSales.getEmployee(i).getName(), row, 1, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ1Sales()), row, 2, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ2Sales()), row, 3, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ3Sales()), row, 4, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getQ4Sales()), row, 5, 1, 1);
                mainPanel.addLabel(String.format("%.2f", employeeSales.getEmployee(i).getTotalSales()), row++, 6, 1, 1);
                row++;
            }
        }
    }

    //Method for button clicks
    public void buttonClicked(JButton buttonObj){

        //if create new button clicked
        if ( buttonObj == newCreateNewBtn ) {
            //validate the inputs
            if ( newNameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty. Please enter a name.");
                newNameField.requestFocus();
                return;
            }
            if( !newQ1SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q1 sales number.");
                newQ1SalesField.setNumber(0);
                newQ1SalesField.requestFocus();
                return;
            }
            if( !newQ2SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q2 sales number.");
                newQ2SalesField.setNumber(0);
                newQ2SalesField.requestFocus();
                return;
            }
            if( !newQ3SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q3 sales number.");
                newQ3SalesField.setNumber(0);
                newQ3SalesField.requestFocus();
                return;
            }
            if( !newQ4SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q4 sales number.");
                newQ4SalesField.setNumber(0);
                newQ4SalesField.requestFocus();
                return;
            }

            //add new employee record
            if ( employeeSales.addEmployee(newNameField.getText(),
                    newQ1SalesField.getNumber(), newQ2SalesField.getNumber(),
                    newQ3SalesField.getNumber(), newQ4SalesField.getNumber()) == null ) {
                messageBox("Sorry, employee could not be added because you can add maximum of 10.");
            }

            this.remove(mainPanel); //remove previous screen
            mainPanel = addPanel(1, 1, 1, 1); //add mew panel
            createInfoScreen();
            revalidate(); //refresh screen
        }

        //if lookup button is clicked
        if ( buttonObj == lookupCreateNewBtn ) {
            //validate the inputs
            if ( lookupNameField.getText().isEmpty() ) {
                if (employeeDetailsPanel != null) {
                    mainPanel.remove(employeeDetailsPanel);
                }
                this.setSize(600, 75);
                revalidate();
                messageBox("Name cannot be empty. Please enter a name.");
                lookupNameField.requestFocus();
                return;
            }

            //search for name match
            Employee matchedEmployee = null;
            for (int i=0; i<employeeSales.getEmployeeCount(); i++) {
                if ( employeeSales.getEmployee(i).getName().startsWith(lookupNameField.getText()) ) {
                    matchedEmployee = employeeSales.getEmployee(i);
                }
            }
            if (employeeDetailsPanel != null) {
                mainPanel.remove(employeeDetailsPanel);
            }
            employeeDetailsPanel = mainPanel.addPanel(2, 1, 1, 1);
            if ( matchedEmployee != null ) {
                this.setSize(600, 250);
                employeeDetailsPanel.addLabel("Name", 1, 1, 1, 1);
                employeeDetailsPanel.addLabel(matchedEmployee.getName(), 1, 2, 1, 1);
                employeeDetailsPanel.addLabel("Q1 Sales", 2, 1, 1, 1);
                employeeDetailsPanel.addLabel(String.format("%.2f", matchedEmployee.getQ1Sales()), 2, 2, 1, 1);
                employeeDetailsPanel.addLabel("Q2 Sales", 3, 1, 1, 1);
                employeeDetailsPanel.addLabel(String.format("%.2f", matchedEmployee.getQ2Sales()), 3, 2, 1, 1);
                employeeDetailsPanel.addLabel("Q3 Sales", 4, 1, 1, 1);
                employeeDetailsPanel.addLabel(String.format("%.2f", matchedEmployee.getQ3Sales()), 4, 2, 1, 1);
                employeeDetailsPanel.addLabel("Q4 Sales", 5, 1, 1, 1);
                employeeDetailsPanel.addLabel(String.format("%.2f", matchedEmployee.getQ4Sales()), 5, 2, 1, 1);
                employeeDetailsPanel.addLabel("Total Sales", 6, 1, 1, 1);
                employeeDetailsPanel.addLabel(String.format("%.2f", matchedEmployee.getTotalSales()), 6, 2, 1, 1);
                revalidate();
            } else {
                this.setSize(600, 100);
                employeeDetailsPanel.addTextArea("No employee named "+lookupNameField.getText()+" found.", 2, 1, 1, 1);
                revalidate();
            }
        }
    }

    //Method for Drop Down
    public void menuItemSelected(JMenuItem menuItem) {
        if ( mainPanel != null ) {
            this.remove(mainPanel); //remove previous screen
        }
        mainPanel = addPanel(1, 1, 1, 1); //add new panel
        if ( menuItem == addNewEmployeeMenu ) {
            createNewEmployeeScreen(); //create screen
        } else if ( menuItem == showEmployeeSalesMenu ) {
            createShowEmployeeSalesScreen(); //create screen
        } else if ( menuItem == showAllSalesMenu ) {
            createShowAllSalesScreen(); //create screen
        } else if ( menuItem == showHighestSalesMenu ) {
            createShowHighestSales(); //create screen
        } else if ( menuItem == showLowestSalesMenu ) {
            createShowLowestSales(); //create screen
        } else if ( menuItem == quitMenu ) {
            exit(0);
        }
        revalidate();
    }

    public static void main(String[] args) {
        JFrame frm = new AceWidgetGui();
        frm.setTitle("Ace Widget");
        frm.setSize (600, 300);
        frm.setLocationRelativeTo(null); // this displays JFrame to center position of a screen
        frm.setVisible (true);
    }
}
