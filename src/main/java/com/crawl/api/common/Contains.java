package com.crawl.api.common;

public class Contains {
    public static final class Batch1{
        public static final int LIMIT_URL_FOR_BATCH_2 = 1000;
    }
    public static final class TypeRobot{
        public static final int TYPE_HACOM_BATCH1 = 1;
        public static final int TYPE_NCPC_BATCH1 = 2;
        public static final int TYPE_HACOM_BATCH2 = 1;
        public static final int TYPE_NCPC_BATCH2 = 2;
        public static final int TYPE_HACOM_BATCH3 = 1;
        public static final int TYPE_NCPC_BATCH3 = 2;
        public static final int TYPE_ALL = 3;
    }
    public static final class RegexString {
        public static final String DATE_TIME = "[0-9]{4}-[0-9]{2}-[0-9]{2}( [0-9]{2}:[0-9]{2}:[0-9]{2})?";
        public static final String MONEY = "[0-9]{1,3}([,][0-9]{3}){0,}([.][0-9]+)?";
        public static final String MONEY2 = "[0-9]{1,3}([.][0-9]{3}){0,}([,][0-9]+)?";
        public static final String NUMBER = "[0-9]{1,}([.][0-9]+)?";
    }
    public static final class ConvertString {
        public static final double getStringToMoney(String str) {
            if (str.matches(RegexString.NUMBER)) return Double.parseDouble(str);
            if (str.matches(RegexString.MONEY)) {
                str = str.replaceAll(",", "");
                return Double.parseDouble(str);
            } else if (str.matches(RegexString.MONEY2)) {
                str = str.replaceAll("[.]", "").replace(",",".");
                return Double.parseDouble(str);
            }
            return -1;
        }
    }
    public static final class Chart {
        public static final int EXECUTE_NUM = 10;
    }
}
