package main.java.h;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.*;
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

//        Set<String> list = new HashSet<>();
        String letters;
        ListMultimap<String, String> mmap = ArrayListMultimap.create();
        for (String out : output) {
            matcher = pattern.matcher(out);
            matcher.find();
            matcher1 = pattern1.matcher(out);
            matcher1.find();
            letters = matcher.group(0);
            mmap.put(letters, matcher1.group(0));
//            list.add(letters);
        }
        System.out.println(mmap);
        System.out.println(mmap.keySet());
        for (String lis : mmap.keySet()) {
            ListMultimap<Integer, String> numMmap = ArrayListMultimap.create();
            List<String> numbers = mmap.get(lis);
            if (numbers.size() > 1) {
                System.out.println(numbers);
                for (String num : numbers) {
                    numMmap.put(num.length(), num);
                }
                System.out.println(numMmap);
                System.out.println(numMmap.keySet());
                for (Integer key : numMmap.keySet()) {
                    if (numMmap.get(key).size() > 1) {
                        System.out.println("key: " + numMmap.get(key));
//                        for (String single : numMmap.get(key)) {
//                            System.out.print(" s:" + Integer.parseInt(single));
//                        }
                        Set<Integer> dashSet = new HashSet<>();
                        Map<Integer, Set> dashMap = new HashMap<>();
                        int dashIndex = 0;
                        for (int i = 0; i < numMmap.get(key).size(); i++) {
                            int one = Integer.parseInt(numMmap.get(key).get(i));
                            int two = 0;
                            if ((i + 1) < numMmap.get(key).size()) {
                                two = Integer.parseInt(numMmap.get(key).get(i + 1));
                            } else continue;
                            if (two == (one + 1)) {
                                dashSet.add(i);
                                dashSet.add(i + 1);
                                dashMap.put(dashIndex, dashSet);
                            } else {
                                dashIndex++;
                                dashSet = new HashSet<>();
                            }

                        }
                        System.out.println("\n");
                        System.out.println(dashMap);
                    }
                }
            }
        }

    }
}
