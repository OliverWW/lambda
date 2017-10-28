import java.util.function.Supplier;
import java.io.Closeable;

/**
 * 对象方法引用
 * 抽象方法的第一个参数类型刚好是实例方法的类型，抽象方法的剩余参数恰好可以当做实例方法的参数
 *
 * 第一个参数最好是自定义的类型
 *
 * 语法：
 * className::instMethod
 */
public class Example3 {

	/*
	* 抽象方法没有输入参数，不能使用抽象方法引用
	 */
	public void not(){
		Runnable run = ()->{};
		Closeable c = ()->{};
		Supplier<String> s = ()->"";
	}
}

class Too{
	public Integer fun(String s){
		System.out.println("对象方法引用：" + s);
		return 1;
	}

	public void foo(){
		System.out.println("对象方法引用: invoke");
	}
}
