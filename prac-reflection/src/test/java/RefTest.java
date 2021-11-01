import com.prac.reflection.Person;
import org.junit.Test;

import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Properties;

public class RefTest {

    @Test
    public void test1() throws Exception {
        Class<Person> personClass = Person.class;
        // 获取构造器
        Constructor<Person> personConstructor = personClass.getConstructor(String.class, int.class);
        // 通过构造器创建对象
        Person person = personConstructor.newInstance("abc", 12);
        // 调用方法
        person.show();

        // 获得属性
        Field age = personClass.getDeclaredField("age");
        // age是私有变量，需要访问权限
        age.setAccessible(true);
        // 两种赋值方式
        age.set(person,15);
        // 通过对象的set方法
        person.setName("dd");
        // 获取方法
        Method show = personClass.getDeclaredMethod("show");
        // 调用方法，也可以调用私有方法
        show.invoke(person);

        // 无参构造器
        Constructor<Person> personConstructor1 = personClass.getConstructor();
        Person person1 = personConstructor1.newInstance();
        age.set(person1,22);
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person1,"def");
        person1.show();

        // 获取Class的实例的其他三种方式
        Class<? extends Person> personClass1 = person.getClass();

        Class<?> name1 = Class.forName("com.prac.reflection.Person");

        ClassLoader classLoader = RefTest.class.getClassLoader();
        Class<?> clazz = classLoader.loadClass("com.prac.reflection.Person");

        Properties prop = new Properties();
        ClassLoader classLoader1 = RefTest.class.getClassLoader();
        InputStream resource = classLoader1.getResourceAsStream("abc.properties");
        prop.load(resource);


    }

    @Test
    public void test2(){

        Class<Person> personClass = Person.class;
        // 获取运行时类及父类中访问权限为public的属性
        Field[] fields = personClass.getFields();
        // 获取运行时类中声明的所有属性（不包含父类）
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field f : declaredFields) {
            // 获取权限修饰符
            String modifier = Modifier.toString(f.getModifiers());
            // 获取数据类型
            String type = f.getType().getName();
            // 变量名
            String name = f.getName();
        }

        // 获取运行时类及父类中访问权限为public的方法
        Method[] methods = personClass.getMethods();
        // 获取运行时类中声明的所有方法（不包含父类）
        Method[] declaredMethods = personClass.getDeclaredMethods();

        for (Method m : declaredMethods) {
            // 获取方法声明的注解
            Annotation[] annotations = m.getAnnotations();
            // 获取权限修饰符
            String modifier = Modifier.toString(m.getModifiers());
            // 获取返回值类型
            String returnType = m.getReturnType().getName();
            // 获取方法名
            String name = m.getName();
            // 获取形参类型
            Class<?>[] parameterTypes = m.getParameterTypes();
            // 获取形参名
            Parameter[] parameters = m.getParameters();
            // 获取异常
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            for (Class e:exceptionTypes
                 ) {
                String eName = e.getName();

            }
        }

        // 获取类中声明为public的构造器
        Constructor<?>[] constructors = personClass.getConstructors();
        // 获取类中声明的所有构造器
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();

        // 获取运行时类的父类
        personClass.getSuperclass();
        // 获取运行时类的带泛型的父类
        ParameterizedType type = (ParameterizedType) personClass.getGenericSuperclass();
        // 获取运行时类的带泛型的父类的泛型
        type.getActualTypeArguments();

    }

    @Test
    public void test3() throws Exception {
        Class<Person> personClass = Person.class;
        Person person = personClass.newInstance();
        // 根据变量名获取属性
        Field name = personClass.getDeclaredField("name");
        // 私有属性或方法要设置访问权限
        name.setAccessible(true);
        name.set(person,"abc");

        Method show = personClass.getDeclaredMethod("show");
        // 调用方法
        show.invoke(person);
        // 获取构造器
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor();
        declaredConstructor.newInstance();


    }


}
