/**
 * java参数传递是值传递
 */
public class ParameterPassingTest {
    public static void cros1(String str){
        System.out.println("形参：" + str);
        str = "abc";
        System.out.println("形参：" + str);
    }

    public static void cros2(int[] num) {
        System.out.println(num[1]);
        num[1] = 34;
        System.out.println(num[1]);
    }

    public static void cros3(Person person) {
       person.setName("令狐冲");
    }

    public static void cros4(Person person) {
        person = new Person();
        person.setName("令狐冲");
    }

    public static void main(String[] args) {
//        String str = "123";
//        cros1(str);
//        System.out.println(str);
//        输出：123      因为String是不可变类型

//        int[] num = {1, 3, 4, 5};
//        cros2(num);
//        System.out.println(num[1]);
//        输出：34      值改变了

//        Person person = new Person();
//        person.setName("林");
//        cros3(person);
//        System.out.println(person.getName());
//        输出：令狐冲

//        Person person = new Person();
//        person.setName("林");
//        cros4(person);
//        System.out.println(person.getName());
        //输出：林

        /**
         * 总结：所谓的引用传递指的是形参和实参公用一个参数，而java的参数传递只不过是拷贝了一份实参，
         * 这时形参和实参实际上是存放在不同的栈帧中的，只不过它们都指向同一块内存地址。
         */

    }


}
class Person{
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
