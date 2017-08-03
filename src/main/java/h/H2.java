package h;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by a on 02.08.17.
 */
public class H2 {
    public static void main(String[] args) {
        List<String> input = Arrays.asList("A00000", "A0001", "ERR000111", "ERR000112", "ERR000113", "ERR000115",
                "ERR000116", "ERR100114", "ERR200000001", "ERR200000002", "ERR200000003", "DRR2110012", "SRR211001", "ABCDEFG1");
        List<String> output = input.stream().sorted().collect(Collectors.toList());
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

        List<String> multipleNumberList = getMultipleNumbers(mmap);
        System.out.println("multipleNumberList:" + multipleNumberList);

        multipleNumberList.forEach(key ->
                System.out.println("key:" + key + " positions:" + getSameLength(mmap, key))
        );
    }

    private static List<String> getMultipleNumbers(ListMultimap<String, String> mmap) {
        List<String> keyList = new ArrayList<>();
        List<String> valueList;
        for (String key : mmap.keySet()) {
            valueList = mmap.get(key);
            if (valueList.size() > 1) {
                keyList.add(key);
            }
        }
        return keyList;
    }

    private static Set<Integer> getSameLength(ListMultimap mmap, String key) {
        List<String> valuesList = mmap.get(key);
        Set<Integer> sameList = new HashSet<>();
        for (int i = 0; i < valuesList.size() - 1; i++) {
            if (valuesList.get(i).length() == valuesList.get(i + 1).length()) {
                sameList.add(i);
                sameList.add(i + 1);
//                ++i;
            }
        }
        return sameList;
        /**
         * todo return different lists
         */
    }

}
