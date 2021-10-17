package com.pratham;

public class EmployeeSales {

    private final int MAX_EMPLOYEES = 10;                        // maximum number of employees
    private Employee[] employees = new Employee[MAX_EMPLOYEES];  // array to store employees
    private int employeeCount = 0;                               // count of employees stored

    public int getEmployeeCount() {
        return employeeCount;
    }

    public Employee getEmployee(int i) {

        //find and return employee
        if ( i >= 0 && i < employeeCount ) {
            return employees[i];
        }

        return null; //return null if i<0 or >=employeeCount
    }

    public Employee addEmployee(String name, double q1Sales, double q2Sales, double q3Sales, double q4Sales) {

        //check if we have space
        if ( employeeCount < MAX_EMPLOYEES ) {

            //create a new object
            Employee newEmployee = new Employee(name);
            newEmployee.setQ1Sales(q1Sales);
            newEmployee.setQ2Sales(q2Sales);
            newEmployee.setQ3Sales(q3Sales);
            newEmployee.setQ4Sales(q4Sales);

            employees[employeeCount] = newEmployee; //add to array
            employeeCount++; //increment count

            return newEmployee; //return new employee
        }

        return null; //return null if we cannot add
    }

    public double getHighestTotalSales() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double highest = -1;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getTotalSales() > highest ) {
                highest = employees[i].getTotalSales();
            }
        }
        return highest;
    }

    public int getHighestTotalSalesCount() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double highest = getHighestTotalSales();
        int count = 0;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getTotalSales() == highest ) {
                count++;
            }
        }
        return count;
    }

    public double getHighestQuarterSales() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double highest = -1;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getQ1Sales() > highest ) {
                highest = employees[i].getQ1Sales();
            }
            if ( employees[i].getQ2Sales() > highest ) {
                highest = employees[i].getQ2Sales();
            }
            if ( employees[i].getQ3Sales() > highest ) {
                highest = employees[i].getQ3Sales();
            }
            if ( employees[i].getQ4Sales() > highest ) {
                highest = employees[i].getQ4Sales();
            }
        }
        return highest;
    }

    public int getHighestQuarterSalesCount() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double highest = getHighestQuarterSales();
        int count = 0;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getQ1Sales() == highest ||
                    employees[i].getQ2Sales() == highest ||
                    employees[i].getQ3Sales() == highest ||
                    employees[i].getQ4Sales() == highest ) {
                count++;
            }
        }
        return count;
    }

    public double getLowestTotalSales() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double lowest = 999999999;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getTotalSales() < lowest ) {
                lowest = employees[i].getTotalSales();
            }
        }
        return lowest;
    }

    public int getLowestTotalSalesCount() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double lowest = getLowestTotalSales();
        int count = 0;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getTotalSales() == lowest ) {
                count++;
            }
        }
        return count;
    }

    public double getLowestQuarterSales() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double lowest = 999999999;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getQ1Sales() < lowest ) {
                lowest = employees[i].getQ1Sales();
            }
            if ( employees[i].getQ2Sales() < lowest ) {
                lowest = employees[i].getQ2Sales();
            }
            if ( employees[i].getQ3Sales() < lowest ) {
                lowest = employees[i].getQ3Sales();
            }
            if ( employees[i].getQ4Sales() < lowest ) {
                lowest = employees[i].getQ4Sales();
            }
        }
        return lowest;
    }

    public int getLowestQuarterSalesCount() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double lowest = getLowestQuarterSales();
        int count = 0;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getQ1Sales() == lowest ||
                    employees[i].getQ2Sales() == lowest ||
                    employees[i].getQ3Sales() == lowest ||
                    employees[i].getQ4Sales() == lowest ) {
                count++;
            }
        }
        return count;
    }

    public double getMeanTotalSales() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double sum = 0;
        for (int i=0; i<employeeCount; i++) {
            sum += employees[i].getTotalSales();
        }
        return sum/employeeCount;
    }

    public double getMeanQuarterSales() {
        if ( employeeCount == 0 ) {
            return 0;
        }
        double sum = 0;
        for (int i=0; i<employeeCount; i++) {
            sum += (employees[i].getQ1Sales() + employees[i].getQ2Sales() +
                    employees[i].getQ3Sales() + employees[i].getQ4Sales());
        }
        return sum/employeeCount/4;
    }

    public double getStdDevTotalSales() {
        if (employeeCount == 0) {
            return 0;
        }
        double mean = getMeanTotalSales();
        double sumSquared = 0;
        for (int i = 0; i < employeeCount; i++) {
            sumSquared += (mean - employees[i].getTotalSales())*(mean - employees[i].getTotalSales());
        }
        return Math.sqrt(sumSquared/employeeCount);
    }

    public double getStdDevQuarterSales() {
        if (employeeCount == 0) {
            return 0;
        }
        double mean = getMeanQuarterSales();
        double sumSquared = 0;
        for (int i = 0; i < employeeCount; i++) {
            sumSquared += (mean - employees[i].getQ1Sales())*(mean - employees[i].getQ1Sales());
            sumSquared += (mean - employees[i].getQ2Sales())*(mean - employees[i].getQ2Sales());
            sumSquared += (mean - employees[i].getQ3Sales())*(mean - employees[i].getQ3Sales());
            sumSquared += (mean - employees[i].getQ4Sales())*(mean - employees[i].getQ4Sales());
        }
        return Math.sqrt(sumSquared/employeeCount/4);
    }
}
