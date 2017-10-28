/**
 * TODO: comment here
 */
public class Example2 {

	public String put(){
		System.out.println("实例引用");
		return "Hello";
	}

	public void con(Integer size){
		System.out.println("实例引用： " + size);
	}

	public String toUpper(String str){
		System.out.println("实例引用：" + str);
		return str.toUpperCase();
	}
}
