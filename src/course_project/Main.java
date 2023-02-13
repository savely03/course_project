package course_project;

public class Main {

    public static void main(String[] args) {
        System.out.println("Курсовой проект (Сложный)\n");
        EmployeeBook employeeBook = new EmployeeBook();
        employeeBook.fillEmployeesArray();
        employeeBook.printAllEmployees();
        System.out.println("Сумма затрат на зарплаты в месяц = " + employeeBook.calculateMonthlySumSalary() + " рублей.\n");
        employeeBook.printEmployeesWithMinSalary();
        employeeBook.printEmployeesWithMaxSalary();
        System.out.printf("Среднее значение зарплат = %.2f рублей.\n\n", employeeBook.calculateAvgSalary());
        employeeBook.printEmployeeFullName();
        employeeBook.indexSalaries(12.5);
        employeeBook.printEmpWithMinSalaryByDep(5);
        employeeBook.printEmpWithMaxSalaryByDep(1);
        System.out.println("Сумма затрат в выбранном отделе = " + employeeBook.calculateSumSalaryByDep(1) + " рублей.\n");
        System.out.println("Средняя зарплата в выбранном отделе = " + employeeBook.calculateAvgSalaryByDep(1) + " рублей.\n");
        employeeBook.indexSalariesByDep(5, 10);
        employeeBook.printEmployeesByDep(5);
        employeeBook.printEmpWithSalaryLessThenInputSalary(150000);
        employeeBook.printEmpWithSalaryMoreOrEqualThenInputSalary(150000);
        employeeBook.addEmployee("Саша", "Александров", "Михайлович", 150000, 2);
        employeeBook.printAllEmployees();
        employeeBook.deleteEmployee(1);
        employeeBook.changeEmployeeDepartment("Сергеев Сергей Сергеевич", 2);
        employeeBook.printFullNameByDepartment();
    }
}