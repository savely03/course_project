package course_project;

public class Employee {
    private final String middleName; // Фамилия
    private final String firstName; // Имя
    private final String lastName; // Отчество
    private final String fullName; // Полное имя
    private int department; // Департамент
    private double salary; // Зарплата
    private static int idCounter; // Счетчик id
    private final int id; // id

    public Employee(String middleName, String firstName, String lastName, int department, int salary) {
        this.middleName = middleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = middleName + " " + firstName + " " + lastName;
        this.department = department;
        this.salary = salary;
        this.id = ++idCounter;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }


    public String printBaseEmployeeInfo() {
        return "id=" + id + ", ФИО=" + fullName + ", Зарплата=" + salary + " руб.";
    }

    @Override
    public String toString() {
        return "Работник{" +
                "id=" + id +
                ", ФИО='" + fullName + '\'' +
                ", Департамент=" + department +
                ", Зарплата=" + salary + "руб." +
                '}';
    }


}
