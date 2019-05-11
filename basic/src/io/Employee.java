package io;

import java.time.LocalDate;

public class Employee {
    private String name;

    private Double salary;
    /** 雇佣日期 **/
    private LocalDate hireDay;

    public Employee() {
    }

    public Employee(String name, Double salary, LocalDate hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }
}
