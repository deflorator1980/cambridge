package main.java.h;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by a on 02.08.17.
 */
public class H {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("A00000", "A0001", "ERR000111", "ERR000112", "ERR000113", "ERR000115",
                "ERR000116", "ERR100114", "ERR200000001", "ERR200000002", "ERR200000003", "DRR2110012", "SRR211001", "ABCDEFG1");
//        System.out.println(input);
//        input.stream().sorted().forEach(System.out::print);
        List<String> output = input.stream().sorted().collect(Collectors.toList());
        System.out.println(output);
        System.out.println(output.get(4));
        String expression = output.get(0);

        Pattern pattern = Pattern.compile("[A-z]+");
        Matcher matcher = pattern.matcher(expression);
        matcher.find();                        //  можно if, если один
        System.out.println(matcher.group(0));  //       выделить все куски в кавычках. Если group() - вместе с кавычками

        Pattern pattern1 = Pattern.compile("[0-9]+");
        Matcher matcher1 = pattern1.matcher(expression);
        matcher1.find();
        System.out.println(matcher1.group(0).length());



//        List<String> letters = new ArrayList<>();
        List<Pair<String, String>> pairs = new ArrayList<>();
        for (String out : output) {
            matcher = pattern.matcher(out);
            matcher.find();
//            letters.add(matcher.group(0));
            matcher1 = pattern1.matcher(out);
            matcher1.find();
            pairs.add(new Pair<>(matcher.group(0), matcher1.group(0)));

        }

        System.out.println(pairs);
    }
}
