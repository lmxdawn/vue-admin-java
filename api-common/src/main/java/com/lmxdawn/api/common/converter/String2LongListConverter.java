package com.lmxdawn.api.common.converter;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 字符串切割为 List<Long>
 */
public class String2LongListConverter {

    /**
     *
     * @param string
     * @param regex 切割的字符
     * @return
     */
    public static List<Long> convert(String string, String regex) {
        try {
            if (null == string || "".equals(string)) {
                return Collections.emptyList();
            }
            String[] strings = string.split(regex);
            if (strings.length == 0) {
                return Collections.emptyList();
            }

            List<Long> longList = new ArrayList<>();
            for (String str : strings) {
                longList.add(Long.valueOf(str));
            }
            return longList;
        }catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
