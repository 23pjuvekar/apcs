package com.pratham;

import BreezySwing.*;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

public class AceWidgetGui extends GBFrame {

    //Employee Data
    private EmployeeSales employeeSales = new EmployeeSales();

    //Main panel
    private GBPanel mainPanel;

    //Drop down setup
    private JMenuItem addNewEmployeeMenu;
    private JMenuItem showEmployeeSalesMenu;
    private JMenuItem showAllSalesMenu;
    private JMenuItem showHighestSalesMenu;
    private JMenuItem showLowestSalesMenu;
    private JMenuItem showStatisticsMenu;
    private JMenuItem createTestDataMenu;
    private JMenuItem quitMenu;

    //add/edit employee fields
    private JTextField  nameField;
    private DoubleField q1SalesField;
    private DoubleField q2SalesField;
    private DoubleField q3SalesField;
    private DoubleField q4SalesField;
    private JButton     createNewBtn;
    private JButton     updateBtn;

    //lookup fields
    private GBPanel     lookupPanel;
    private JTextField  lookupNameField;
    private JButton     lookupBtn;
    private GBPanel     employeeDetailsPanel;

    //constructor
    public AceWidgetGui() {

        //main panel
        mainPanel = addPanel(1,1,1,1);
        createInfoScreen();

        //add menu items
        addNewEmployeeMenu = addMenuItem("Employee","Add New");
        showEmployeeSalesMenu = addMenuItem("Employee","Lookup");
        showAllSalesMenu = addMenuItem("Employee","Show All Sales");
        showHighestSalesMenu = addMenuItem("Employee","Show Highest Sales");
        showLowestSalesMenu = addMenuItem("Employee","Show Lowest Sales");
        showStatisticsMenu = addMenuItem("Employee", "Show Sales Statistics");
        createTestDataMenu = addMenuItem("Employee", "Add Test Data");
        quitMenu = addMenuItem("Employee", "Quit");
    }

    //create test data
    public void createTestData() {
        employeeSales = new EmployeeSales();
        employeeSales.addEmployee("Bob", 2220.50, 3143.22, 4134.35, 3423.55);
        employeeSales.addEmployee("Jane", 3143.22, 2220.50, 3423.55, 4134.75);
        employeeSales.addEmployee("Tim", 6423.55, 2234.60, 3463.42, 4334.45);
        employeeSales.addEmployee("Jack", 2234.60, 3463.42, 4334.45, 6423.55);
        employeeSales.addEmployee("Tom", 2222.40, 3448.32, 4634.25, 3323.55);
        employeeSales.addEmployee("John", 2224.80, 2144.72, 4737.45, 3421.55);
        employeeSales.addEmployee("Janice", 2144.72, 3421.55, 2224.80, 4737.45);
    }

    //show general info screen
    public void createInfoScreen() {
        this.setSize(800,200);
        String msg = "\nThere are currently "+employeeSales.getEmployeeCount()+" employees in the system.\n\nChoose an operation from the dropdown menu.";
        JTextArea infoField = mainPanel.addTextArea(msg, 1, 1, 1, 1);
        infoField.setEditable(false);
        Font font = new Font("Courier", Font.BOLD,16);
        infoField.setFont(font);
        revalidate();
    }

    //create New Employee Screen
    public void createNewEmployeeScreen() {
        this.setSize(800,300);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField("", 1, 2, 2, 1);
        nameField.requestFocus();
        mainPanel.addLabel("Q1 Sales", 2, 1, 1, 1);
        q1SalesField = mainPanel.addDoubleField(0.0, 2, 2, 2, 1);
        q1SalesField.setPrecision(2);
        mainPanel.addLabel("Q2 Sales", 3, 1, 1, 1);
        q2SalesField = mainPanel.addDoubleField(0.0, 3, 2, 2, 1);
        q2SalesField.setPrecision(2);
        mainPanel.addLabel("Q3 Sales", 4, 1, 1, 1);
        q3SalesField = mainPanel.addDoubleField(0.0, 4, 2, 2, 1);
        q3SalesField.setPrecision(2);
        mainPanel.addLabel("Q4 Sales", 5, 1, 1, 1);
        q4SalesField = mainPanel.addDoubleField(0.0, 5, 2, 2, 1);
        q4SalesField.setPrecision(2);
        createNewBtn = mainPanel.addButton("Create New", 6, 1, 3, 1);
        revalidate();
    }

    //create New Employee Screen
    public void createEditEmployeeScreen(Employee employeeToEdit, int index) {
        this.setSize(800,300);
        mainPanel.addLabel("Name", 1, 1, 1, 1);
        nameField = mainPanel.addTextField(employeeToEdit.getName(), 1, 2, 2, 1);
        nameField.requestFocus();
        mainPanel.addLabel("Q1 Sales", 2, 1, 1, 1);
        q1SalesField = mainPanel.addDoubleField(employeeToEdit.getQ1Sales(), 2, 2, 2, 1);
        q1SalesField.setPrecision(2);
        mainPanel.addLabel("Q2 Sales", 3, 1, 1, 1);
        q2SalesField = mainPanel.addDoubleField(employeeToEdit.getQ2Sales(), 3, 2, 2, 1);
        q2SalesField.setPrecision(2);
        mainPanel.addLabel("Q3 Sales", 4, 1, 1, 1);
        q3SalesField = mainPanel.addDoubleField(employeeToEdit.getQ3Sales(), 4, 2, 2, 1);
        q3SalesField.setPrecision(2);
        mainPanel.addLabel("Q4 Sales", 5, 1, 1, 1);
        q4SalesField = mainPanel.addDoubleField(employeeToEdit.getQ4Sales(), 5, 2, 2, 1);
        q4SalesField.setPrecision(2);
        updateBtn = mainPanel.addButton("Update", 6, 1, 3, 1);
        updateBtn.setActionCommand(""+index);
        revalidate();
    }

    public void makeBold(JLabel label) {
        Font font = label.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize());
        label.setFont(boldFont);
    }

    //show all sales information
    public void createShowAllSalesScreen() {
        this.setSize(800,100+25*employeeSales.getEmployeeCount());
        makeBold(mainPanel.addLabel("Name", 1, 1, 1, 1));
        makeBold(mainPanel.addLabel("Q1 Sales", 1, 2, 1, 1));
        makeBold(mainPanel.addLabel("Q2 Sales", 1, 3, 1, 1));
        makeBold(mainPanel.addLabel("Q3 Sales", 1, 4, 1, 1));
        makeBold(mainPanel.addLabel("Q4 Sales", 1, 5, 1, 1));
        makeBold(mainPanel.addLabel("Total Sales", 1, 6, 1, 1));
        for (int i=0; i<employeeSales.getEmployeeCount(); i++) {
            mainPanel.addLabel(employeeSales.getEmployee(i).getName(), i+2, 1, 1, 1);
            mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ1Sales()), i+2, 2, 1, 1);
            mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ2Sales()), i+2, 3, 1, 1);
            mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ3Sales()), i+2, 4, 1, 1);
            mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ4Sales()), i+2, 5, 1, 1);
            mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getTotalSales()), i+2, 6, 1, 1);
        }
        revalidate();
    }

    //show an employees sales
    public void createLookupEmployeesScreen() {
        this.setSize(800,75);
        lookupPanel = mainPanel.addPanel(1,1,1,1);
        lookupPanel.addLabel("Name", 1, 1, 1, 1);
        lookupNameField = lookupPanel.addTextField("", 1, 2, 1, 1);
        lookupNameField.requestFocus();
        lookupBtn = lookupPanel.addButton("Lookup", 1, 3, 1, 1);
        revalidate();
    }

    //show all high sales
    public void createShowHighestSalesScreen() {
        double highest = employeeSales.getHighestTotalSales();
        int highestCount = employeeSales.getHighestTotalSalesCount();
        this.setSize(800,100+50*highestCount);
        makeBold(mainPanel.addLabel("Highest Total Sales: "+String.format("$%.2f",highest), 1, 1, 1, 1));
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
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ1Sales()), row, 2, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ2Sales()), row, 3, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ3Sales()), row, 4, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ4Sales()), row, 5, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getTotalSales()), row, 6, 1, 1);
                row++;
            }
        }

        double highestQuarter = employeeSales.getHighestQuarterSales();
        int highestQuarterCount = employeeSales.getHighestQuarterSalesCount();
        this.setSize(800,100+50*(highestCount+highestQuarterCount));
        makeBold(mainPanel.addLabel("Highest Quarter Sales: "+String.format("$%.2f",highestQuarter), row++, 1, 1, 1));
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
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ1Sales()), row, 2, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ2Sales()), row, 3, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ3Sales()), row, 4, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ4Sales()), row, 5, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getTotalSales()), row++, 6, 1, 1);
                row++;
            }
        }
        revalidate();
    }

    //show all low sales
    public void createShowLowestSalesScreen() {
        double lowest = employeeSales.getLowestTotalSales();
        int lowestCount = employeeSales.getLowestTotalSalesCount();
        this.setSize(800,100+50*lowestCount);
        makeBold(mainPanel.addLabel("Lowest Total Sales: "+String.format("$%.2f",lowest), 1, 1, 1, 1));
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
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ1Sales()), row, 2, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ2Sales()), row, 3, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ3Sales()), row, 4, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ4Sales()), row, 5, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getTotalSales()), row, 6, 1, 1);
                row++;
            }
        }

        double lowestQuarter = employeeSales.getLowestQuarterSales();
        int lowestQuarterCount = employeeSales.getLowestQuarterSalesCount();
        this.setSize(800,100+50*(lowestCount+lowestQuarterCount));
        makeBold(mainPanel.addLabel("Lowest Quarter Sales: "+String.format("$%.2f",lowestQuarter), row++, 1, 1, 1));
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
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ1Sales()), row, 2, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ2Sales()), row, 3, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ3Sales()), row, 4, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ4Sales()), row, 5, 1, 1);
                mainPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getTotalSales()), row++, 6, 1, 1);
                row++;
            }
        }
        revalidate();
    }

    //show sales statistics
    public void createSalesStatisticsScreen() {

        this.setSize(800,400);

        GBPanel totalSalesStatsPanel = mainPanel.addPanel(1,1,1,1);
        makeBold(totalSalesStatsPanel.addLabel("Total Sales Statistics", 1, 1, 1, 1));
        totalSalesStatsPanel.addLabel("Min", 2, 1, 1, 1);
        totalSalesStatsPanel.addLabel(String.format("$%.2f",employeeSales.getLowestTotalSales()), 2, 2, 1, 1);
        totalSalesStatsPanel.addLabel("Max", 3, 1, 1, 1);
        totalSalesStatsPanel.addLabel(String.format("$%.2f",employeeSales.getHighestTotalSales()), 3, 2, 1, 1);
        totalSalesStatsPanel.addLabel("Mean", 4, 1, 1, 1);
        totalSalesStatsPanel.addLabel(String.format("$%.2f",employeeSales.getMeanTotalSales()), 4, 2, 1, 1);
        totalSalesStatsPanel.addLabel("Std. Dev.", 5, 1, 1, 1);
        totalSalesStatsPanel.addLabel(String.format("%.2f",employeeSales.getStdDevTotalSales()), 5, 2, 1, 1);

        GBPanel quarterSalesStatsPanel = mainPanel.addPanel(2,1,1,1);
        makeBold(quarterSalesStatsPanel.addLabel("Quarter Sales Statistics", 1, 1, 1, 1));
        quarterSalesStatsPanel.addLabel("Min", 2, 1, 1, 1);
        quarterSalesStatsPanel.addLabel(String.format("$%.2f",employeeSales.getLowestQuarterSales()), 2, 2, 1, 1);
        quarterSalesStatsPanel.addLabel("Max", 3, 1, 1, 1);
        quarterSalesStatsPanel.addLabel(String.format("$%.2f",employeeSales.getHighestQuarterSales()), 3, 2, 1, 1);
        quarterSalesStatsPanel.addLabel("Mean", 4, 1, 1, 1);
        quarterSalesStatsPanel.addLabel(String.format("$%.2f",employeeSales.getMeanQuarterSales()), 4, 2, 1, 1);
        quarterSalesStatsPanel.addLabel("Std. Dev.", 5, 1, 1, 1);
        quarterSalesStatsPanel.addLabel(String.format("%.2f",employeeSales.getStdDevQuarterSales()), 5, 2, 1, 1);
        revalidate();
    }

    //Method for button clicks
    public void buttonClicked(JButton buttonObj){

        //if create new button clicked
        if ( buttonObj == createNewBtn) {
            //validate the inputs
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty. Please enter a name.");
                nameField.requestFocus();
                return;
            }
            if( !q1SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q1 sales number.");
                q1SalesField.setNumber(0);
                q1SalesField.requestFocus();
                return;
            }
            if( !q2SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q2 sales number.");
                q2SalesField.setNumber(0);
                q2SalesField.requestFocus();
                return;
            }
            if( !q3SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q3 sales number.");
                q3SalesField.setNumber(0);
                q3SalesField.requestFocus();
                return;
            }
            if( !q4SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q4 sales number.");
                q4SalesField.setNumber(0);
                q4SalesField.requestFocus();
                return;
            }

            //add new employee record
            if ( employeeSales.addEmployee(nameField.getText(),
                    q1SalesField.getNumber(), q2SalesField.getNumber(),
                    q3SalesField.getNumber(), q4SalesField.getNumber()) == null ) {
                messageBox("Sorry, employee could not be added because you can add maximum of 10.");
            }

            this.remove(mainPanel); //remove previous screen
            mainPanel = addPanel(1, 1, 1, 1); //add mew panel
            createInfoScreen();
            revalidate(); //refresh screen
        }

        //if lookup button is clicked
        if ( buttonObj == lookupBtn) {
            //validate the inputs
            if ( lookupNameField.getText().isEmpty() ) {
                if (employeeDetailsPanel != null) {
                    mainPanel.remove(employeeDetailsPanel);
                }
                this.setSize(800, 75);
                revalidate();
                messageBox("Name cannot be empty. Please enter a name.");
                lookupNameField.requestFocus();
                return;
            }

            //search for name match
            if (employeeDetailsPanel != null) {
                mainPanel.remove(employeeDetailsPanel);
            }
            employeeDetailsPanel = mainPanel.addPanel(2, 1, 1, 1);
            int row = 1;
            this.setSize(800,100+25*row);
            makeBold(employeeDetailsPanel.addLabel("Name", row, 1, 1, 1));
            makeBold(employeeDetailsPanel.addLabel("Q1 Sales", row, 2, 1, 1));
            makeBold(employeeDetailsPanel.addLabel("Q2 Sales", row, 3, 1, 1));
            makeBold(employeeDetailsPanel.addLabel("Q3 Sales", row, 4, 1, 1));
            makeBold(employeeDetailsPanel.addLabel("Q4 Sales", row, 5, 1, 1));
            makeBold(employeeDetailsPanel.addLabel("Total Sales", row++, 6, 1, 1));
            boolean matchFound = false;
            for (int i=0; i<employeeSales.getEmployeeCount(); i++) {
                if ( employeeSales.getEmployee(i).getName().startsWith(lookupNameField.getText()) ) {
                    matchFound = true;
                    this.setSize(800,100+25*row);
                    employeeDetailsPanel.addLabel(employeeSales.getEmployee(i).getName(), row, 1, 1, 1);
                    employeeDetailsPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ1Sales()), row, 2, 1, 1);
                    employeeDetailsPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ2Sales()), row, 3, 1, 1);
                    employeeDetailsPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ3Sales()), row, 4, 1, 1);
                    employeeDetailsPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getQ4Sales()), row, 5, 1, 1);
                    employeeDetailsPanel.addLabel(String.format("$%.2f", employeeSales.getEmployee(i).getTotalSales()), row, 6, 1, 1);
                    JButton btn = employeeDetailsPanel.addButton("Edit", row++, 7, 1, 1);
                    btn.setActionCommand(""+i); //store index for button
                }
            }
            if ( !matchFound ) {
                mainPanel.remove(employeeDetailsPanel);
                employeeDetailsPanel = mainPanel.addPanel(2, 1, 1, 1);
                this.setSize(800, 100);
                JTextArea textArea = employeeDetailsPanel.addTextArea("No employee named "+lookupNameField.getText()+" found.", 2, 1, 1, 1);
                textArea.setEditable(false);
            }
            revalidate();
        }

        if ( buttonObj.getText().startsWith("Edit") ) {
            try {
                int index = Integer.parseInt(buttonObj.getActionCommand());
                Employee employeeToEdit = employeeSales.getEmployee(index);
                if ( employeeToEdit == null ) {
                    messageBox("Could not find employee by index "+index);
                } else {
                    if (mainPanel != null) {
                        this.remove(mainPanel);
                    }
                    mainPanel = addPanel(1,1,1,1);
                    createEditEmployeeScreen(employeeToEdit, index);
                }
            } catch (Exception ex) {
                messageBox("Could not find employee by index " + buttonObj.getActionCommand() );
            }
        }

        if ( buttonObj == updateBtn) {
            //validate the inputs
            if ( nameField.getText().isEmpty() ) {
                messageBox("Name cannot be empty. Please enter a name.");
                nameField.requestFocus();
                return;
            }
            if( !q1SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q1 sales number.");
                q1SalesField.setNumber(0);
                q1SalesField.requestFocus();
                return;
            }
            if( !q2SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q2 sales number.");
                q2SalesField.setNumber(0);
                q2SalesField.requestFocus();
                return;
            }
            if( !q3SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q3 sales number.");
                q3SalesField.setNumber(0);
                q3SalesField.requestFocus();
                return;
            }
            if( !q4SalesField.isValidNumber() ) {
                messageBox("Please enter a valid Q4 sales number.");
                q4SalesField.setNumber(0);
                q4SalesField.requestFocus();
                return;
            }

            try {
                int index = Integer.parseInt(buttonObj.getActionCommand());
                Employee employeeToUpdate = employeeSales.getEmployee(index);
                if ( employeeToUpdate == null ) {
                    messageBox("Could not find employee by index "+index);
                } else {
                    employeeToUpdate.setName(nameField.getText());
                    employeeToUpdate.setQ1Sales(q1SalesField.getNumber());
                    employeeToUpdate.setQ2Sales(q2SalesField.getNumber());
                    employeeToUpdate.setQ3Sales(q3SalesField.getNumber());
                    employeeToUpdate.setQ4Sales(q4SalesField.getNumber());
                    this.remove(mainPanel);
                    mainPanel = addPanel(1,1,1,1);
                    createInfoScreen();
                    revalidate(); //refresh screen
                }
            } catch (Exception ex) {
                messageBox("Could not find employee by index " + buttonObj.getActionCommand() );
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
            if ( employeeSales.getEmployeeCount() == 10 ) {
                messageBox("Max number of employees (10) reached. Cannot create any more.");
                createInfoScreen();
            } else {
                createNewEmployeeScreen();
            }
        } else if ( menuItem == showEmployeeSalesMenu ) {
            if ( employeeSales.getEmployeeCount() < 1 ) {
                messageBox("Please input at least 1 employee record.");
                createInfoScreen();
            } else {
                createLookupEmployeesScreen();
            }
        } else if ( menuItem == showAllSalesMenu ) {
            if ( employeeSales.getEmployeeCount() < 1 ) {
                messageBox("Please input at least 1 employee record.");
                createInfoScreen();
            } else {
                createShowAllSalesScreen();
            }
        } else if ( menuItem == showHighestSalesMenu ) {
            if ( employeeSales.getEmployeeCount() < 2 ) {
                messageBox("Please input at least 2 employee records.");
                createInfoScreen();
            } else {
                createShowHighestSalesScreen();
            }
        } else if ( menuItem == showLowestSalesMenu ) {
            if ( employeeSales.getEmployeeCount() < 2 ) {
                messageBox("Please input at least 2 employee records.");
                createInfoScreen();
            } else {
                createShowLowestSalesScreen();
            }
        } else if ( menuItem == showStatisticsMenu ) {
            if (employeeSales.getEmployeeCount() < 2) {
                messageBox("Please input at least 2 employee records.");
                createInfoScreen();
            } else {
                createSalesStatisticsScreen();
            }
        } else if ( menuItem == createTestDataMenu ) {
            createTestData();
            createInfoScreen();
        } else if ( menuItem == quitMenu ) {
            exit(0);
        }
        revalidate();
    }

    public static void main(String[] args) {
        JFrame frm = new AceWidgetGui();
        frm.setTitle("Ace Widget");
        frm.setSize (800, 200);
        frm.setLocationRelativeTo(null); // this displays JFrame to center position of a screen
        frm.setVisible (true);
    }
}
