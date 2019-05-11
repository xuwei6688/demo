package io.randomAccess;

import io.Employee;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 把雇员信息写入文件
 * 每个雇员信息字节数=名字80字节 + 薪水8字节 + 雇佣日期3*4 = 100字节
 * 读取的时候就可以确定seek位置。
 */
public class RandomAccessTest {
    /** 用40字符（80字节）表示名字 **/
    private static final int NAME_SIZE = 40;

    /** 每个雇员信息长度100**/
    private static final int RECORD_SIZE = 100;

    public static void main(String[] args) throws IOException {
        Employee employees[] = new Employee[3];
        employees[0] = new Employee("ling hu chong", 8500d, LocalDate.of(1997, 5, 3));
        employees[1] = new Employee("ren woxing", 8500d, LocalDate.of(1997, 5, 3));
        employees[2] = new Employee("ren ying ying", 8500d, LocalDate.of(1997, 5, 3));

        //把信息写入文件，
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"))) {
            for (Employee e : employees) {
                writeData(out, e);
            }
        }

       //从后往前读取各个雇员信息
        List<Employee> employeeList = new ArrayList<>();
        try (RandomAccessFile in = new RandomAccessFile("employee.dat", "r")) {
            int n = (int)in.length() / RECORD_SIZE;

            for (int i = n - 1; i >= 0; i--) {
                in.seek(i * RECORD_SIZE);
                employeeList.add(readData(in));
            }
        }
        for (Employee e : employeeList) {
            System.out.println(e);
        }
    }

    public static void writeData(DataOutput out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), NAME_SIZE, out);

        out.writeDouble(e.getSalary());

        LocalDate hireDay = e.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    public static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(NAME_SIZE, in);
        double salary = in.readDouble();
        int year = in.readInt();
        int month = in.readInt();
        int day = in.readInt();

        return new Employee(name, salary, LocalDate.of(year, month - 1, day));
    }
}
