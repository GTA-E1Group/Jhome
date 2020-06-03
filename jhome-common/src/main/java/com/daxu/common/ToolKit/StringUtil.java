package com.daxu.common.ToolKit;

public class StringUtil {

    /**
     * 判断字符串是空
     *
     * @param currentStr
     * @return
     */
    public static boolean isBlank(String currentStr) {
        if (currentStr == null || currentStr.isEmpty() || currentStr.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串 不为空
     *
     * @param currentStr
     * @return
     */
    public static boolean isNotBlank(String currentStr) {
        if (currentStr != null && !currentStr.isEmpty() && !currentStr.equals("")) {
            return true;
        }
        return false;
    }

}
