import jdk.nashorn.internal.ir.FunctionCall;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * TODO: comment here
 */
public class App {
	public String testThis(String str) {
		System.out.println("对象方法引用this:" + str);
		return "对象方法引用this";
	}

	//静态方法引用
	static void test() {
		//无参数有返回值
		Supplier<String> s = () -> Example.put();
		Supplier<String> s2 = Example::put;
		System.out.println("静态方法引用： " + s.get());
		System.out.println("静态方法引用： " + s2.get());

		//有参数无返回值
		Consumer<Integer> c = (size) -> Example.con(size);
		Consumer<Integer> c2 = Example::con;
		c.accept(1000);
		c2.accept(1000);
	}

	//实例方法引用
	static void test2() {
		//无输入有输出
		Supplier<String> s = () -> new Example2().put();
		Supplier<String> s1 = () -> {
			return new Example2().put();
		};
		Supplier<String> s2 = new Example2()::put;
		System.out.println(s.get());
		System.out.println(s1.get());
		System.out.println(s2.get());

		//有输入无输出
		Consumer<Integer> c = (size) -> new Example2().con(size);
		Consumer<Integer> c1 = new Example2()::con;
		c.accept(999);
		c1.accept(999);

		//有输入输出
		Function<String, String> f = str -> str.toUpperCase();
		Function<String, String> f1 = str -> new Example2().toUpper(str);
		Function<String, String> f2 = new Example2()::toUpper;
		System.out.println(f.apply("hello"));
		System.out.println(f1.apply("hello"));
		System.out.println(f2.apply("hello"));
	}

	public void test2_1() {
		Function<String, String> f3 = this::testThis;
		f3.apply("hello,world");
	}

	//对象方法引用
	public static void test3() {
		Consumer<Too> c1 = too->new Too().foo();;
		Consumer<Too> c2 = Too::foo;
		c1.accept(new Too());
		c2.accept(new Too());

		BiConsumer<Too,String> bi1 = (too, str)->new Too().fun(str);
		BiConsumer<Too,String> bi2 = Too::fun;
		bi1.accept(new Too(),"hello");
		bi2.accept(new Too(), "hello");
	}

	//构造方法引用
	public static void test4(){
		System.out.println("****************构造方法引用*****************");
		Supplier<Person> s1 = ()->new Person();
		Supplier<Person> s2 = Person::new;
		s1.get();
		s2.get();

		Consumer<Integer> c1 = (age) -> new Account(age);
		Consumer<Integer> c2 = Account::new;
		c1.accept(123);
		c2.accept(123);

		Function<String, Account> fu2 = (str) -> new Account();
		Function<String, Account> fu3 = Account::new;
		fu2.apply("admin");
		fu3.apply("admin");
	}

	public static void main(String[] args) {
		test();

		test2();
		//this
		App app = new App();
		app.test2_1();

		test3();

		test4();
	}
}
