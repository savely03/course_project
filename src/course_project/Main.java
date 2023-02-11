package course_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static Employee[] employees = new Employee[10]; // Учитываем, что массив всегда содержит 10 элементов.

    public static void main(String[] args) {
        System.out.println("Курсовой проект (базовая сложность)\n");
        fillEmployeesArray();
        printAllEmployees();
        System.out.println("Сумма затрат на зарплаты в месяц = " + calculateMonthlySumSalary() + " рублей.\n");
        printEmployeesWithMinSalary();
        printEmployeesWithMaxSalary();
        System.out.printf("Среднее значение зарплат = %.2f рублей.\n\n", calculateAvgSalary());
        printEmployeeFullName();
        indexSalaries(employees, 12.5);
        printEmpWithMinSalaryByDep(5);
        printEmpWithMaxSalaryByDep(1);
        System.out.println("Сумма затрат в выбранном отделе = " + calculateSumSalaryByDep(1) + " рублей.\n");
        System.out.println("Средняя зарплата в выбранном отделе = " + calculateAvgSalaryByDep(1) + " рублей.\n");
        indexSalariesByDep(5, 10);
        printEmployeesByDep(5);
        printEmpWithSalaryLessThenInputSalary(150000);
        printEmpWithSalaryMoreOrEqualThenInputSalary(150000);

    }


    /**
     * Метод выводит список всех работников
     */
    public static void printAllEmployees() {
        System.out.println("Список всех работников:");

        // Arrays.stream(employees).forEach(System.out::println); Решение через Stream API */

        for (Employee employee : employees) {
            System.out.println(" " + employee);
        }

        System.out.println();
    }

    /**
     * Метод подсчитывает сумму затрат на зарплаты в месяц
     *
     * @return Сумма затрат на зарплаты в месяц
     */
    public static double calculateMonthlySumSalary() {
        double sumMonthlySalaries = 0;
        for (Employee employee : employees) {
            sumMonthlySalaries += employee.getSalary();
        }
        return sumMonthlySalaries;
    }


    /**
     * Метод сортирует работников по их заработной плате
     *
     * @return Отсортированный массив работников по зарплате
     */
    public static Employee[] sortEmployees() {
        Employee[] employeesCopy = Arrays.copyOf(employees, employees.length);
        Arrays.sort(employeesCopy, Comparator.comparingDouble(Employee::getSalary));
        return employeesCopy;
    }

    /**
     * Метод выводит работников с минимальной зарплатой
     */
    public static void printEmployeesWithMinSalary() {
        System.out.println("Работники с минимальной зарплатой:");

        /* int minSalary = Arrays.stream(employees).
                min(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        Arrays.stream(employees).forEach(employee -> {
            if (employee.getSalary() == minSalary) {
                System.out.println(" " + employee);
            }
        }); Решение через Stream API */

        Employee[] sortEmployees = sortEmployees();
        double minSalary = sortEmployees[0].getSalary();
        for (int i = 0; i < sortEmployees.length && sortEmployees[i].getSalary() == minSalary; i++) {
            System.out.println(" " + sortEmployees[i]);
        }

        System.out.println();
    }

    /**
     * Метод выводит работников с максимальной зарплатой
     */
    public static void printEmployeesWithMaxSalary() {
        System.out.println("Работники с максимальной зарплатой:");

        /* int maxSalary = Arrays.stream(employees).
                max(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        Arrays.stream(employees).forEach(employee -> {
            if (employee.getSalary() == maxSalary) {
                System.out.println(" " + employee);
            }
        }); Решение через Stream API */

        Employee[] sortEmployees = sortEmployees();
        double maxSalary = sortEmployees[sortEmployees.length - 1].getSalary();
        for (int i = sortEmployees.length - 1; i >= 0 && sortEmployees[i].getSalary() == maxSalary; i--) {
            System.out.println(" " + sortEmployees[i]);
        }

        System.out.println();
    }

    /**
     * Метод возвращает среднюю зарплату
     *
     * @return Значение средней зарплаты
     */
    public static double calculateAvgSalary() {
        double sumSalaries = calculateMonthlySumSalary();
        return sumSalaries / employees.length;
    }


    /**
     * Метод выводит ФИО всех работников
     */
    public static void printEmployeeFullName() {
        System.out.println("Список ФИО всех сотрудников:");

        /* Arrays.stream(employees).
                forEach(employee -> System.out.println(" ФИО: " + employee.getFullName())); Решение через Stream API */

        for (Employee employee : employees) {
            System.out.println(" ФИО: " + employee.getFullName());
        }

        System.out.println();
    }


    /**
     * Метод индексирует зарплаты работников
     *
     * @param percent Процент увеличения
     */
    public static void indexSalaries(Employee[] inputEmployees, double percent) {

        /* Arrays.stream(inputEmployees).forEach(employee ->
                employee.setSalary(employee.getSalary() + employee.getSalary() * (percent / 100)));
        Решение через Stream API */

        for (Employee employee : inputEmployees) {
            employee.setSalary(employee.getSalary() + employee.getSalary() * (percent / 100));
        }
    }

    /**
     * Метод вызывает ошибку, если указанный отдел не существует
     *
     * @param department Номер отдела
     */
    public static void checkDepartment(int department) {
        if (department < 1 || department > 5) {
            throw new IllegalArgumentException("Неверный номер департамента!");
        }
    }


    /**
     * Метод возвращает массив работников по выбранному отделу
     *
     * @param department Номер отдела
     * @return Массив работников
     */
    public static Employee[] getEmployeesByDep(int department) {
        checkDepartment(department);
        return Arrays.stream(employees).
                filter(employee -> employee.getDepartment() == department).toArray(Employee[]::new);
    }

    /**
     * Метод выводит работников с минимальной зп в указанном отделе
     *
     * @param department Номер отдела
     */
    public static void printEmpWithMinSalaryByDep(int department) {
        checkDepartment(department);
        System.out.println("Работники с минимальной зарплатой - департамент " + department + ":");

        Employee[] employeesByDep = getEmployeesByDep(department);

        double minDepSalary = Arrays.stream(employeesByDep).
                min(Comparator.comparingDouble(Employee::getSalary)).get().getSalary();

        for (Employee employee : employeesByDep) {
            if (employee.getSalary() == minDepSalary) {
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
    public static void printEmpWithMaxSalaryByDep(int department) {
        checkDepartment(department);
        System.out.println("Работники с максимальной зарплатой - департамент " + department + ":");

        Employee[] employeesByDep = getEmployeesByDep(department);

        double maxDepSalary = Arrays.stream(employeesByDep).
                max(Comparator.comparingDouble(Employee::getSalary)).get().getSalary();

        for (Employee employee : employeesByDep) {
            if (employee.getSalary() == maxDepSalary) {
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
    public static double calculateSumSalaryByDep(int department) {
        checkDepartment(department);
        Employee[] employeesByDep = getEmployeesByDep(department);
        double sumSalaryByDep = 0d;
        for (Employee employee : employeesByDep) {
            sumSalaryByDep += employee.getSalary();
        }
        return sumSalaryByDep;
    }


    /**
     * Метод считает среднюю зарплату в выбранном отделе
     *
     * @param department Номер отдела
     * @return Средняя зарплата по отделу
     */
    public static double calculateAvgSalaryByDep(int department) {
        checkDepartment(department);
        Employee[] employeesByDep = getEmployeesByDep(department);
        double sumSalaryByDep = calculateSumSalaryByDep(department);
        return sumSalaryByDep / employeesByDep.length;
    }


    /**
     * Метод индексирует зарплаты по отделу на указанный процент
     *
     * @param department Номер отдела
     * @param percent    Процент увеличения
     */
    public static void indexSalariesByDep(int department, double percent) {
        checkDepartment(department);
        Employee[] employeesByDep = getEmployeesByDep(department);
        indexSalaries(employeesByDep, percent);
    }

    /**
     * Метод выводит всех работников, указанного отдела
     *
     * @param department Номер отдела
     */
    public static void printEmployeesByDep(int department) {
        checkDepartment(department);
        System.out.println("Работники отдела - " + department + ":");
        Employee[] employeesByDep = getEmployeesByDep(department);
//        Arrays.stream(employeesByDep).forEach(Employee::printBaseEmployeeInfo); Решение через Stream API
        for (Employee employee : employeesByDep) {
            System.out.println(" " + employee.printBaseEmployeeInfo());
        }
        System.out.println();
    }

    /**
     * Метод выводит работников с зарплатой меньше переданной зп
     *
     * @param salary Зарплата
     */
    public static void printEmpWithSalaryLessThenInputSalary(double salary) {
        System.out.println("Работники с зарплатой меньшей: " + salary + " руб:");
        Arrays.stream(employees).forEach(employee -> {
            if (employee.getSalary() < salary) {
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
    public static void printEmpWithSalaryMoreOrEqualThenInputSalary(double salary) {
        System.out.println("Работники с зарплатой большей или равной: " + salary + " руб:");
        Arrays.stream(employees).forEach(employee -> {
            if (employee.getSalary() >= salary) {
                System.out.println(" " + employee.printBaseEmployeeInfo());
            }
        });
        System.out.println();
    }

    /**
     * Метод заполняет массив на основе текстового файла (employees.txt), который содержит ровно 10 работников
     */
    public static void fillEmployeesArray() {
        try (Scanner scanner = new Scanner(new File("employees.txt"))) {
            for (int i = 0; i < employees.length && scanner.hasNextLine(); i++) {
                String[] textEmployee = scanner.nextLine().split(" ");
                employees[i] = new Employee(
                        textEmployee[0], // Фамилия
                        textEmployee[1], // Имя
                        textEmployee[2], // Отчество
                        Integer.parseInt(textEmployee[3]), // Департамент
                        Integer.parseInt(textEmployee[4]) // Зарплата
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}