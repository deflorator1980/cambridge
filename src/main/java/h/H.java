package main.java.h;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by a on 02.08.17.
 */
public class H {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("A00000", "A0001", "ERR000111", "ERR000112", "ERR000113", "ERR000115",
                "ERR000116", "ERR100114", "ERR200000001", "ERR200000002", "ERR200000003", "DRR2110012", "SRR211001","ABCDEFG1");
        System.out.println(input);
//        input.stream().sorted().forEach(System.out::print);
        List<String> output = input.stream().sorted().collect(Collectors.toList());
        System.out.println(output);
    }
}
