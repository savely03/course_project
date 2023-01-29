package course_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static Employee[] employees = new Employee[10]; // Учитываем, что массив всегда содержит 10 элементов.

    public static void main(String[] args) {
        System.out.println("Курсовой проект (базовая сложность)\n");
        fillEmployeesArray();
        printAllEmployees();
        System.out.println("Сумма затрат на зарплаты в месяц = " + calculateMonthlySumSalaries() + " рублей.\n");
        printEmployeeWithMinSalary();
        printEmployeeWithMaxSalary();
        System.out.printf("Среднее значение зарплат = %.2f рублей.\n\n", calculateAvgSalary());
        printEmployeeFullName();
    }


    /**
     * Метод выводит список всех работников
     */
    public static void printAllEmployees() {
        System.out.println("Список всех работников:");

        /* Arrays.stream(employees).
                forEach(employee -> System.out.println(" " + employee)); Решение через Stream API */

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
    public static int calculateMonthlySumSalaries() {
        int sumMonthlySalaries = 0;
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
        Arrays.sort(employeesCopy, Comparator.comparingInt(Employee::getSalary));
        return employeesCopy;
    }

    /**
     * Метод выводит работников с минимальной зарплатой
     */
    public static void printEmployeeWithMinSalary() {
        System.out.println("Работники с минимальной зарплатой:");

        /* int minSalary = Arrays.stream(employees).
                min(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        Arrays.stream(employees).forEach(employee -> {
            if (employee.getSalary() == minSalary) {
                System.out.println(" " + employee);
            }
        }); Решение через Stream API */

        Employee[] sortEmployees = sortEmployees();
        int minSalary = sortEmployees[0].getSalary();
        for (int i = 0; i < sortEmployees.length && sortEmployees[i].getSalary() == minSalary; i++) {
            System.out.println(" " + sortEmployees[i]);
        }

        System.out.println();
    }

    /**
     * Метод выводит работников с максимальной зарплатой
     */
    public static void printEmployeeWithMaxSalary() {
        System.out.println("Работники с максимальной зарплатой:");

        /* int maxSalary = Arrays.stream(employees).
                max(Comparator.comparingInt(Employee::getSalary)).get().getSalary();
        Arrays.stream(employees).forEach(employee -> {
            if (employee.getSalary() == maxSalary) {
                System.out.println(" " + employee);
            }
        }); Решение через Stream API */

        Employee[] sortEmployees = sortEmployees();
        int maxSalary = sortEmployees[sortEmployees.length - 1].getSalary();
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
        int sumSalaries = calculateMonthlySumSalaries();
        return (double) sumSalaries / employees.length;
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
     * Метод заполняет массив на основе текстового файла, который содержит ровно 10 работников
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