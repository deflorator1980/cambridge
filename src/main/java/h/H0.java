package h;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class H0 {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("A00000", "A0001", "ERR000111", "ERR000112", "ERR000113", "ERR000114", "ERR000115",
                "ERR000116", "ERR100114", "ERR200000001", "ERR200000002", "ERR200000003", "DRR2110012", "DRR2110013", "DRR2110014", "DRR21100144", "SRR211001", "ABCDEFG1");

        List<String> output = input.stream().sorted().collect(Collectors.toList());
        System.out.println("input:" + input);
//        System.out.println("output:" + output);

        ListMultimap<String, String> mmap = ArrayListMultimap.create();
        Pattern pattern = Pattern.compile("[A-z]+");
        Pattern pattern1 = Pattern.compile("[0-9]+");
        for (String out : output) {
            Matcher matcher = pattern.matcher(out);
            matcher.find();
            Matcher matcher1 = pattern1.matcher(out);
            matcher1.find();
            mmap.put(matcher.group(0), matcher1.group(0));
        }
//        System.out.println("mmap:" + mmap);

        Set<String> keys = mmap.keySet();
        List<String> result = new ArrayList<>();
        for (String key : keys) {
            List<String> values = mmap.get(key);
            for (String value : values) {
                result.add(key + value);
            }
        }

//        System.out.println("result:" + result);
        List<String> output2 = new ArrayList<>();
        for (String letters : mmap.keySet()) {
            output2.addAll(chifers(mmap, letters));
        }
//        System.out.println(output2);
        output = output2.stream().sorted().collect(Collectors.toList());
        System.out.println("output:" + output);
    }

    private static List<String> chifers(ListMultimap<String, String> mmap, String letter) {
        List<String> vals = mmap.get(letter);
//        System.out.println("vals: " + vals);

        ListMultimap<Integer, String> numMmap = ArrayListMultimap.create();
        for (String val : vals) {
            numMmap.put(val.length(), val);
        }
//        System.out.println("First value — length of number");
//        System.out.println(numMmap);

        List<String> list = new ArrayList<>();
        Set<Integer> keySet = numMmap.keySet();
        for (int key : keySet) {
            if (numMmap.get(key).size() < 2) {
                list.add(letter + numMmap.get(key).toString().replaceAll("\\[", "").replaceAll("\\]", ""));
            } else list.addAll(dash(numMmap.get(key), letter));
        }
        return list;
    }

    private static List<String> dash(List<String> strings, String letter) {
//        System.out.println("\n" + strings);
        List<Integer> nums = new ArrayList<>();
        strings.forEach(s -> nums.add(Integer.parseInt(s)));
//        System.out.println(nums);
        for (int i = 0; i < nums.size(); i++) {
            int a = nums.get(i);
            if (i < nums.size() - 2) {
                int b = nums.get(i + 1);
                int c = nums.get(i + 2);
                if ((a == b - 1) && (b == c - 1)) {
                    strings.set(i + 1, "-");
                }
            }
//            System.out.println(nums.get(i));
        }
//        System.out.println(strings);
//        System.out.println(strings.size());

        List<String> strings2 = new LinkedList<>();
        strings2.addAll(strings);
        for (int i = 0; i < strings2.size(); i++) {
            String a = strings2.get(i);
            if (a == "-") {
                if (strings2.get(i + 1) == "-") {
                    strings2.remove(i);
                    --i;
                }
            }
        }
//        System.out.println(strings2);

        for (int i = 0; i < strings2.size(); i++) {
            if (strings2.get(i) == "-") {
                String a = strings2.get(i - 1) + "-" + strings2.get(i + 1);
                strings2.set(i - 1, a);
                strings2.remove(i);
//                strings2.remove(i+1);
                strings2.remove(i);
                i = i - 2;
            }
        }
//        System.out.println("DASH " + strings2);
        strings2 = strings2.stream().map(s -> letter + s).map(s -> s.replace("-", "-" + letter)).collect(Collectors.toList());
//        System.out.println(strings2);
        return strings2;

    }
}
