package h;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class H0 {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("A00000", "A0001", "ERR000111", "ERR000112", "ERR000113", "ERR000115",
                "ERR000116", "ERR100114", "ERR200000001", "ERR200000002", "ERR200000003", "DRR2110012", "SRR211001", "ABCDEFG1");
        List<String> output = input.stream().sorted().collect(Collectors.toList());
        System.out.println("input:" + input);
        System.out.println("output:" + output);

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
        System.out.println("mmap:" + mmap);

        Set<String> keys = mmap.keySet();
        List<String> result = new ArrayList<>();
        for (String key : keys) {
            List<String> values = mmap.get(key);
            for (String value : values) {
                result.add(key + value);
            }
        }

        System.out.println("result:" + result);
        List sortResult = result.stream().sorted().collect(Collectors.toList());
        System.out.println("sortRes:" + sortResult);

        List<String> vals = mmap.get("ERR");
        System.out.println(vals);

        ListMultimap<Integer, String> numMmap = ArrayListMultimap.create();
        for (String val : vals) {
            numMmap.put(val.length(), val);
        }
        System.out.println(numMmap);

        Set<Integer> keySet = numMmap.keySet();
        for (int key : keySet) {
            dash(numMmap.get(key));
        }

        /**todo
         * скормить Dash и воссоздать
         */

    }

    private static void dash(List<String> strings) {
        System.out.println("\n" + strings);
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
//                strings2.remove(i+1);
                strings2.remove(i);
                i = i - 2;
            }
        }
        System.out.println(strings2);

    }
}
