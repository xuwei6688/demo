import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author xuwei
 * @Date 2019/12/3 0003
 * @Version V1.0
 **/
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> c = Class.forName("User");
        User user = (User)c.newInstance();
        user.setName("linghu");
        System.out.println(user.getName());

        Constructor<?> constructor = c.getConstructor();
        User user2 = (User)constructor.newInstance();
        user2.setName("haha");
    }
}
