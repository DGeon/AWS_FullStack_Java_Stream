import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        String[] strArr = { "aaa", "ddd", "bbb", "ccc" };
        List<String> list = Arrays.asList(strArr);
        list.forEach(System.out::println);

        // 정렬된 형태의 출력
        System.out.println("==============Arrays=================");
        Arrays.sort(strArr);
        for (String s : strArr) {
            System.out.println(s);
        }
        System.out.println("==============Arrays=================");
        System.out.println("==============list=================");
        // list
        Collections.sort(list);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("==============list=================");
        System.out.println("==============stream=================");
        Stream<String> stream1 = list.stream();
        Stream<String> stream2 = Arrays.stream(strArr);
        System.out.println("===============================");
        stream1.sorted().forEach(System.out::println);
        System.out.println("==============stream=================");

        // Iterator : next(), hasNext()
        Iterator<String> iter = list.iterator();
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());
        System.out.println(iter.next());

        // 일회용이라 다시 정의해줘야 한다.
        iter = list.iterator();
        System.out.println(iter.next());
        System.out.println("===============================");
        // 일회용이라 다시 정의해줘야 한다.
        stream1 = list.stream();
        stream1.sorted().forEach((s) -> System.out.println(s));
        // $("p").css("color","red"
        // IntStream intStream = IntStream.rangeClosed(1, 5);
        IntStream intStream = new Random().ints(10, 0, 10).limit(20);// ints 범위 안주면 무한 스트림
        intStream.forEach(i -> System.out.println(i));

        Stream<String> stream3 = Stream.of("123", "가나다", "12");
        System.out.println("===========================================");
        stream3.forEach(s -> System.out.println(s));

        List<String> list2 = new ArrayList<>();
        list2.add("김길동");
        list2.add("박길동");
        list2.add("박현빈");
        list2.add("홍길동");
        list2.add("김삼순");
        list2.add("김유신");
        // 1. 김씨성을 가진 인물 출력
        list2.stream().filter(str -> str.startsWith("김")).forEach(s -> System.out.println(s));
        // for (int i = 0; i < list2.size(); i++) {
        // if (list2.get(i).substring(0, 1).equals("김")) {
        // System.out.println(list2.get(i));
        // }
        // }

        System.out.println("===========================================");
        // 2. 이름이 길동인 인물 출력
        list2.stream().filter(new Predicate<String>() {
            public boolean test(String t) {
                return t.endsWith("길동");
            };
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println(t);
            }
        });

        File file = new File("c:\\Users\\DGeon\\Desktop");
        // 해당 바탕화면 폴더의 디렉토리가 아닌 파일만 골라서 파일 이름과 파일크기 파일명길이를 출력
        Arrays.stream(file.listFiles()).filter((f) -> {
            return f.isFile();
        }).forEach(f -> System.out.println(f));

        Arrays.stream(file.listFiles()).filter((f) -> {
            return f.isFile();
        }).map((f) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("size", f.length());
            map.put("name", f.getName());
            map.put("name.length", f.getName().length());
            return map;
        }).forEach(f -> System.out.println(f));
    }
}