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

        /**
         * скормить Dash и вооссоздать
         */

    }
}
