package com.lmxdawn.api.common.converter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 字符串切割为 List<String>
 */
public class String2StringListConverter {

    /**
     *
     * @param string
     * @param regex 切割的字符
     * @return
     */
    public static List<String> convert(String string, String regex) {
        try {
            if (null == string || "".equals(string)) {
                return Collections.emptyList();
            }
            String[] strings = string.split(regex);
            if (strings.length == 0) {
                return Collections.emptyList();
            }
            return new ArrayList<>(Arrays.asList(strings));
        }catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
