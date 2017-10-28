import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TODO: comment here
 */
public class App_3 {
	//数组
	static void streamGenerate() {
		System.out.println("*************Array*****************");
		String[] arr = { "a", "b", "c", "d" };
		Stream<String> stream = Stream.of(arr);
		stream.forEach(System.out::println);
	}

	//List
	static void streamGenerate2() {
		System.out.println("*************List******************");
		List<String> list = Arrays.asList("a", "b", "c", "d");
		Stream<String> stream = list.stream();
		stream.forEach(System.out::println);
	}

	//Stream.generate
	static void streamGenerate3() {
		System.out.println("*************generate**************");
		Stream<Integer> stream = Stream.generate(() -> 1);
		stream.limit(10).forEach(System.out::println);
	}

	//Stream.iterate
	static void streamGenerate4() {
		System.out.println("*************iterate****************");
		Stream<Integer> stream = Stream.iterate(1, x -> x + 1);
		stream.limit(10).forEach(System.out::println);
	}

	//String.chars
	static void streamGenerate5() {
		System.out.println("*************String.chars***********");
		String str = "abcde";
		IntStream stream = str.chars();

		stream.forEach(System.out::println);
	}

	//Files.lines
	static void streamGenerate6() throws IOException {
		System.out.println("*************Files.lines***********");
		Files.lines(Paths.get("./data/test.txt")).forEach(System.out::println);

	}

	static void midleOperate() {
		System.out.println("------------------Filter-----------------");
		Arrays.asList(1,2,3,4,5).stream().filter(x->x%2==0).forEach(System.out::println);
	}

	static void midleOperate2() {
		System.out.println("-----------sorted, skip, limit------------");
		List<Integer> list = Stream.iterate(1, x -> x + 1).limit(50).sorted((a,b) -> b-a).skip(20).limit(10).collect(
				Collectors.toList());
		System.out.println(list);

	}

	static void midleOperate3() {
		System.out.println("-----------------distinct---------------------");
		Arrays.asList(1, 3, 4, 2, 2, 5, 1).stream().distinct().forEach(System.out::println);
	}

	static void midleOperate4() {
		System.out.println("----------map,mapToInt ---------------------");
		String str = "111,222,333,444,555";
		int sum = Stream.of(str.split(",")).mapToInt(x->Integer.valueOf(x)).sum();
		System.out.println("sum: " + sum);

		sum = Stream.of(str.split(",")).map(x -> Integer.valueOf(x)).mapToInt(x -> x).sum();
		System.out.println("sum:" + sum);

		sum = Stream.of(str.split(",")).mapToInt(Integer::valueOf).sum();
		System.out.println("sum:" + sum);

		Stream.of(str.split(",")).map(x->new User(x)).forEach(System.out::println);
		Stream.of(str.split(",")).map(User::new).forEach(System.out::println);
	}

	//终止操作

	//并行操作
	static void parallelOperation(){
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@并行操作@@@@@@@@@@@@@@@@@@");
		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");
		Optional<Integer> max = Stream.iterate(1, x -> x+1).limit(200).peek(x -> {
			System.out.println(x + " : " + Thread.currentThread().getName());
		}).parallel().max(Integer::compare);

		System.out.println(max);
	}

	public static void main(String[] args) throws IOException {
		//流生成
		streamGenerate();
		streamGenerate2();
		streamGenerate3();
		streamGenerate4();
		streamGenerate5();
		streamGenerate6();

		//中间操作
		midleOperate();
		midleOperate2();
		midleOperate3();
		midleOperate4();

		//终止操作

		//并行操作
		parallelOperation();
	}
}

class User {
	private String name;
	public User(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
}
