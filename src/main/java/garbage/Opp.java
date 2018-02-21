package garbage;

import java.util.List;

public class Opp {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("a", "b", "c");
        List<String> l2 = List.of("a","b","c","d");
//        l2.add("h");
        System.out.println(l2);
        List immutableList = List.of("one","two","three", "four");
        System.out.println(immutableList);
    }
}
