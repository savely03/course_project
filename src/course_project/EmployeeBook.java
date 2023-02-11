package course_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeBook {
    private final Employee[] employees;

    public EmployeeBook() {
        employees = new Employee[10];
    }

    /**
     * Метод выводит список всех работников
     */
    public void printAllEmployees() {
        System.out.println("Список всех работников:");

        /* Arrays.stream(employees).forEach(employee -> {
            if (employee != null) {
                System.out.println(" " + employee);
            }
        }); Решение через Stream API */

        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(" " + employee);
            }
        }

        System.out.println();
    }

    /**
     * Метод подсчитывает сумму затрат на зарплаты в месяц
     *
     * @return Сумма затрат на зарплаты в месяц
     */
    public double calculateMonthlySumSalary() {
        double sumMonthlySalaries = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                sumMonthlySalaries += employee.getSalary();
            }
        }
        return sumMonthlySalaries;
    }


    /**
     * Метод выводит работников с минимальной зарплатой
     */
    public void printEmployeesWithMinSalary() {
        System.out.println("Работники с минимальной зарплатой:");

        double minSalary = Integer.MAX_VALUE;
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() < minSalary) {
                minSalary = employee.getSalary();
            }
        }

        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() == minSalary) {
                System.out.println(" " + employee);
            }
        }

        System.out.println();
    }

    /**
     * Метод выводит работников с максимальной зарплатой
     */
    public void printEmployeesWithMaxSalary() {
        System.out.println("Работники с максимальной зарплатой:");

        double maxSalary = Integer.MIN_VALUE;
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() > maxSalary) {
                maxSalary = employee.getSalary();
            }
        }

        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() == maxSalary) {
                System.out.println(" " + employee);
            }
        }

        System.out.println();
    }

    /**
     * Метод возвращает среднюю зарплату
     *
     * @return Значение средней зарплаты
     */
    public double calculateAvgSalary() {
        double sumMonthlySalaries = 0d;
        int numberOfEmployees = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                sumMonthlySalaries += employee.getSalary();
                numberOfEmployees++;
            }
        }
        return sumMonthlySalaries / numberOfEmployees;
    }


    /**
     * Метод выводит ФИО всех работников
     */
    public void printEmployeeFullName() {
        System.out.println("Список ФИО всех сотрудников:");

        /* Arrays.stream(employees).
                forEach(employee -> {
                    if (employee != null) {
                        System.out.println(" ФИО: " + employee.getFullName());
                    }
                }); Решение через Stream API */

        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(" ФИО: " + employee.getFullName());
            }
        }

        System.out.println();
    }


    /**
     * Метод индексирует зарплаты работников
     *
     * @param percent Процент увеличения
     */
    public void indexSalaries(double percent) {

        /* Arrays.stream(employees).forEach(employee -> {
            if (employee != null) {
                employee.setSalary(employee.getSalary() + employee.getSalary() * (percent / 100)));
            }
        }); Решение через Stream API */

        for (Employee employee : employees) {
            if (employee != null) {
                employee.setSalary(employee.getSalary() + employee.getSalary() * (percent / 100));
            }
        }
    }

    /**
     * Метод вызывает ошибку, если указанный отдел не существует
     *
     * @param department Номер отдела
     */
    public void checkDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Неверный номер департамента!");
        }
    }


    /**
     * Метод проверяет работника и департамент
     *
     * @param employee   Работника
     * @param department Номер отдела
     * @return Валидность работника
     */
    public boolean checkEmployeeWithDep(Employee employee, int department) {
        return employee != null && employee.getDepartment() == department;
    }

    /**
     * Метод выводит работников с минимальной зп в указанном отделе
     *
     * @param department Номер отдела
     */
    public void printEmpWithMinSalaryByDep(int department) {
        checkDepartment(department);
        System.out.println("Работники с минимальной зарплатой - департамент " + department + ":");

        double minDepSalary = Double.MAX_VALUE;

        for (Employee employee : employees) {
            if (checkEmployeeWithDep(employee, department) && employee.getSalary() < minDepSalary) {
                minDepSalary = employee.getSalary();
            }
        }

        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department && employee.getSalary() == minDepSalary) {
                System.out.println(" " + employee);
            }
        }
        System.out.println();
    }

    /**
     * Метод выводит работников с максимальной зп в указанном отделе
     *
     * @param department Номер отдела
     */
    public void printEmpWithMaxSalaryByDep(int department) {
        checkDepartment(department);
        System.out.println("Работники с максимальной зарплатой - департамент " + department + ":");

        double maxDepSalary = Double.MIN_VALUE;
        for (Employee employee : employees) {
            if (checkEmployeeWithDep(employee, department) && employee.getSalary() > maxDepSalary) {
                maxDepSalary = employee.getSalary();
            }
        }

        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department && employee.getSalary() == maxDepSalary) {
                System.out.println(" " + employee);
            }
        }
        System.out.println();
    }


    /**
     * Метод суммирует зарплаты в выбранном отделе
     *
     * @param department Номер отдела
     * @return Сумма затрат на зарплату по отделу
     */
    public double calculateSumSalaryByDep(int department) {
        checkDepartment(department);
        double sumSalaryByDep = 0d;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                sumSalaryByDep += employee.getSalary();
            }
        }
        return sumSalaryByDep;
    }


    /**
     * Метод считает среднюю зарплату в выбранном отделе
     *
     * @param department Номер отдела
     * @return Средняя зарплата по отделу
     */
    public double calculateAvgSalaryByDep(int department) {
        checkDepartment(department);
        double sumSalaryByDep = 0d;
        int numberOfEmployees = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                sumSalaryByDep += employee.getSalary();
                numberOfEmployees++;
            }
        }
        return sumSalaryByDep / numberOfEmployees;
    }


    /**
     * Метод индексирует зарплаты по отделу на указанный процент
     *
     * @param department Номер отдела
     * @param percent    Процент увеличения
     */
    public void indexSalariesByDep(int department, double percent) {
        checkDepartment(department);
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                employee.setSalary(employee.getSalary() + employee.getSalary() * percent / 100);
            }
        }
    }

    /**
     * Метод выводит всех работников, указанного отдела
     *
     * @param department Номер отдела
     */
    public void printEmployeesByDep(int department) {
        checkDepartment(department);
        System.out.println("Работники отдела - " + department + ":");
        /* Arrays.stream(employees).forEach(employee -> {
            if (employee != null && employee.getDepartment() == department) {
                System.out.println(" " + employee.printBaseEmployeeInfo());
            }
        }); Решение через Stream API */
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                System.out.println(" " + employee.printBaseEmployeeInfo());
            }
        }
        System.out.println();
    }

    /**
     * Метод выводит работников с зарплатой меньше переданной зп
     *
     * @param salary Зарплата
     */
    public void printEmpWithSalaryLessThenInputSalary(double salary) {
        System.out.println("Работники с зарплатой меньшей: " + salary + " руб:");
        Arrays.stream(employees).forEach(employee -> {
            if (employee != null && employee.getSalary() < salary) {
                System.out.println(" " + employee.printBaseEmployeeInfo());
            }
        });
        System.out.println();
    }


    /**
     * Метод выводит работников с зарплатой большей или равной переданной зп
     *
     * @param salary Зарплата
     */
    public void printEmpWithSalaryMoreOrEqualThenInputSalary(double salary) {
        System.out.println("Работники с зарплатой большей или равной: " + salary + " руб:");
        Arrays.stream(employees).forEach(employee -> {
            if (employee != null && employee.getSalary() >= salary) {
                System.out.println(" " + employee.printBaseEmployeeInfo());
            }
        });
        System.out.println();
    }


    /**
     * Метод добавляет нового работника в массив
     *
     * @param firstName  Имя
     * @param middleName Фамилия
     * @param lastName   Отчество
     * @param salary     Зарплата
     * @param department Отдел
     */
    public void addEmployee(String firstName, String middleName, String lastName, double salary, int department) {
        checkDepartment(department);
        Employee employee = new Employee(middleName, firstName, lastName, department, salary);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                return;
            }
        }
    }

    /**
     * Метод удаляет работника по ФИО
     *
     * @param fullName ФИО
     */
    public void deleteEmployee(String fullName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getFullName().equals(fullName)) {
                employees[i] = null;
            }
        }
    }

    /**
     * Метод удаляет работника по id
     *
     * @param id Id работника
     */
    public void deleteEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId() == id) {
                employees[i] = null;
            }
        }
    }

    /**
     * Метод ищет сотрудника по ФИО
     *
     * @param fullName ФИО
     * @return Работник
     */
    public Employee findEmployeeByFullName(String fullName) {
        for (Employee employee : employees) {
            if (employee != null && employee.getFullName().equals(fullName)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Метод меняет зарплату работника
     *
     * @param fullName ФИО
     * @param salary   Зарплата
     */
    public void changeEmployeeSalary(String fullName, double salary) {
        Employee employee;
        if ((employee = findEmployeeByFullName(fullName)) != null) {
            employee.setSalary(salary);
        }
    }


    /**
     * Метод меняет отдел работника
     *
     * @param fullName   ФИО
     * @param department Отдел
     */
    public void changeEmployeeDepartment(String fullName, int department) {
        checkDepartment(department);
        Employee employee;
        if ((employee = findEmployeeByFullName(fullName)) != null) {
            employee.setDepartment(department);
        }
    }


    /**
     * Метод выводит работников, группируя по отделу
     */
    public void printFullNameByDepartment() {
        Map<Integer, List<Employee>> map = Arrays.stream(employees).filter(Objects::nonNull).
                collect(Collectors.groupingBy(Employee::getDepartment));
        for (Map.Entry<Integer, List<Employee>> entry : map.entrySet()) {
            System.out.println("Отдел - " + entry.getKey() + ":");
            for (Employee employee : entry.getValue()) {
                System.out.println(" " + employee.getFullName());
            }
        }
        System.out.println();
    }

    /**
     * Метод заполняет массив на основе текстового файла (employees.txt), который содержит ровно 10 работников
     */
    public void fillEmployeesArray() {
        try (Scanner scanner = new Scanner(new File("employees.txt"))) {
            for (int i = 0; i < 7 && scanner.hasNextLine(); i++) {
                String[] textEmployee = scanner.nextLine().split(" ");
                if (employees[i] == null) {
                    employees[i] = new Employee(
                            textEmployee[0], // Фамилия
                            textEmployee[1], // Имя
                            textEmployee[2], // Отчество
                            Integer.parseInt(textEmployee[3]), // Департамент
                            Integer.parseInt(textEmployee[4]) // Зарплата
                    );
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
