package com.pratham;

public class EmployeeSales {

    final int MAX_EMPLOYEES = 10;                        // maximum number of employees
    Employee[] employees = new Employee[MAX_EMPLOYEES];  // array to store employees
    int employeeCount = 0;                               // count of employees stored

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
        double highest = -1;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getTotalSales() > highest ) {
                highest = employees[i].getTotalSales();
            }
        }
        return highest;
    }

    public int getHighestTotalSalesCount() {
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
        double lowest = 999999999;
        for (int i=0; i<employeeCount; i++) {
            if ( employees[i].getTotalSales() < lowest ) {
                lowest = employees[i].getTotalSales();
            }
        }
        return lowest;
    }

    public int getLowestTotalSalesCount() {
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

}
