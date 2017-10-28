import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * TODO: comment here
 */
public class App {
    //简单的函数体可以省略中括号
	//无参数无返回值
	static void test() throws Exception {

		Runnable r1 = new Runnable() {
			public void run() {
				System.out.println("接口函数实例");
			}
		};
		r1.run();

		Runnable r2 = () -> {System.out.println("接口函数实例");};
		r2.run();

		//简单的函数体可以省略中括号
		Runnable r3 = ()->System.out.println("接口函数实例");
		r3.run();

		Callable<String> c1 = new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "Callable接口函数实现";
			}
		};

		Callable<String> c2 = () ->{ return "Callable接口函数实现"; };
		Callable<String> c3 = () -> "Callable接口函数实现";

		System.out.println(c1.call());
		System.out.println(c2.call());
		System.out.println(c3.call());
	}

	//创建函数接口，并采用lambda表达式实现
	//：有参数无返回值
	//: 有参数有返回值
	static void test2(){
		//有参数无返回值
		UserMapper u1 = new UserMapper() {
			@Override
			public void insert(User user) {
				System.out.println("UserMapper 实例实现: " + user);
			}
		};

		UserMapper u2 = (user)->{System.out.println("UserMapper 实例实现: " + user);};
		UserMapper u3 = (user)->System.out.println("UserMapper 实例实现: " + user);
		u1.insert(new User());
		u2.insert(new User());
		u3.insert(new User());

		//有参数有返回值
		OrderMapper o1 = new OrderMapper() {
			@Override
			public int insert(Order order) {
				System.out.println("OrderMapper 实例实现， order： "  + order);
				return 0;
			}
		};

		OrderMapper o2 = (order) -> {return 0;};
		OrderMapper o3 = (Order order) -> {return 0;};
		OrderMapper o4 = (order) -> 0;
		OrderMapper o5 = (Order order) -> 0;

		System.out.println(o1.insert(new Order()));
		System.out.println(o2.insert(new Order()));
		System.out.println(o3.insert(new Order()));
		System.out.println(o4.insert(new Order()));
		System.out.println(o5.insert(new Order()));


		Function<Integer, Integer> f1 = a->{
			int sum = 0;
			for(int i=1; i<=a; i++){
				sum += i;
			}
			return sum;
		};

		System.out.println(f1.apply(10));
	}

	//库内置函数接口
	static void test3(){
		//1、 supplier
		Supplier<Integer> s1 = new Supplier<Integer>() {
			@Override
			public Integer get() {
				return 1;
			}
		};

		Supplier<Integer> s2 = ()-> {return 1;};
		Supplier<Integer> s3 = ()-> 1;

		System.out.println(s1.get());
		System.out.println(s2.get());
		System.out.println(s3.get());

		//2.Consumer
		Consumer<Integer> c1 = new Consumer<Integer>() {
			@Override
			public void accept(Integer integer) {
				System.out.println("Consumer: input is " + integer);
			}
		};

		Consumer<Integer> c2= (a) -> System.out.println("Consumer: input is " + a);
		Consumer<Integer> c3= a -> System.out.println("Consumer: input is " + a);
		c1.accept(10);
		c2.accept(10);
		c3.accept(10);

		//3.BiConsumer
		BiConsumer<String, Integer> b1 = new BiConsumer<String, Integer>() {
			@Override
			public void accept(String a, Integer b) {
				System.out.println("BiConsumer,(input1, input2) is： （ " + a + "," + b + " );" );
			}
		};
		BiConsumer<String, Integer> b2 = (a, b)-> {System.out.println("BiConsumer,(input1, input2) is： （ " + a + ","  + b + " );" );};
		BiConsumer<String, Integer> b3 = (a, b)-> System.out.println("BiConsumer,(input1, input2) is： （ " + a + "," + b + " );" );

		b1.accept("hello,world", 10000000);
		b2.accept("hello,world", 10000000);
		b3.accept("hello,world", 10000000);

		//4.Function
		Function<String, Integer> f1 = new Function<String, Integer>() {
			@Override
			public Integer apply(String s) {
				System.out.println("Function input is " + s);
				return 1;
			}
		};

		Function<String, Integer> f2 = (s)->{
			System.out.println("Function input is " + s);
			return 1;
		};

		Function<String, Integer> f3 = s ->{
			System.out.println("Function input is " + s);
			return 1;
		};

		System.out.println("Function: " + f1.apply("fuction"));
		System.out.println("Function: " + f2.apply("fuction"));
		System.out.println("Function: " + f3.apply("fuction"));

		//5. UnaryOperator
		UnaryOperator<String> u1 = new UnaryOperator<String>() {
			@Override
			public String apply(String s) {
				System.out.println("UnaryOperator input is " + s);
				return "world";
			}
		};

		UnaryOperator<String> u2 = (s) -> {
			System.out.println("UnaryOperator input is " + s);
			return "world";
		};

		UnaryOperator<String> u3 = s -> {
			System.out.println("UnaryOperator input is " + s);
			return "world";
		};

		//6.BiFunction
		BiFunction<String, Long, Integer> bi1 = new BiFunction<String, Long, Integer>() {
			@Override
			public Integer apply(String s, Long aLong) {
				System.out.println("BiFunction, input is (" + s + "," + aLong + ")");
				return 222;
			}
		};

		BiFunction<String, Long, Integer> bi2 = (s, aLong) -> {
			System.out.println("BiFunction, input is (" + s + "," + aLong + ")");
			return 222;
		};

		System.out.println(bi1.apply("my god", 5789067431473918L));
		System.out.println(bi2.apply("my god", 5789067431473918L));

		//7. BinaryOperator
		BinaryOperator<String> biO1 = new BinaryOperator<String>() {
			@Override
			public String apply(String a, String b) {
				return a + b;
			}
		};
		BinaryOperator<String> biO2 = (a,b)->a+b;
		System.out.println(biO1.apply("hello", " world"));
		System.out.println(biO2.apply("hello", " world"));
	}

	public static void main(String[] args) throws Exception {
		test();

		test2();

		test3();
	}
}


interface UserMapper{
	void insert(User user);
}

interface OrderMapper{
	int insert(Order order);
}

interface Foo{
	int get();
}

class User{}

class Order{}