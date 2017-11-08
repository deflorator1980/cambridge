package h;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Dash {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("000111", "000112", "000113", "000115", "000116", "100114", "200000001", "200000002", "200000003", "200000004", "200000006");
//        List<String> strings = Arrays.asList("000111", "000112", "000113","000114", "000115", "000116", "100114", "200000001", "200000002", "200000003", "200000004", "200000006");
        System.out.println(strings);
        List<Integer> nums = new ArrayList<>();
        strings.forEach(s -> {
            nums.add(Integer.parseInt(s));
        });
        System.out.println(nums);
        for (int i = 0; i < nums.size(); i++) {
            int a = nums.get(i);
            if (i < nums.size() - 2) {
                int b = nums.get(i + 1);
                int c = nums.get(i + 2);
                if ((a == b - 1) && (b == c - 1)) {
                    strings.set(i + 1, "-");
                }
            }
            System.out.println(nums.get(i));
        }
        System.out.println(strings);
        System.out.println(strings.size());

        List<String> strings2 = new LinkedList<>();
        strings2.addAll(strings);
        for (int i = 0; i < strings2.size(); i++) {
            String a = strings2.get(i);
            if (a == "-") {
                if(strings2.get(i+1) == "-"){
                    strings2.remove(i);
                    --i;
                }
            }
        }
        System.out.println(strings2);

        for (int i = 0; i < strings2.size(); i++) {
            if (strings2.get(i) == "-") {
                String a =  strings2.get(i-1) + "-" + strings2.get(i+1);
                strings2.set(i-1, a);
                strings2.remove(i);
                strings2.remove(i+1);
                i = i - 2;
            }
        }
        System.out.println(strings2);

//        strings.removeIf(x -> x.equals("-"));
//        System.out.println(strings);


    }
}
