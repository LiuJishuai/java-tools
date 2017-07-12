package com.jeyson.tools.string;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * Created by  liujishuai
 * Create Date: 2017/7/12 19:31
 * Description: 字符处理类
 */
public class StringUtils implements Serializable {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str != null && !str.equals("")) {
            return false;
        }
        //如果要去除空格
//		if(sk!=null&&!sk.isEmpty()&&!sk.trim().isEmpty()){
//			return true;
//		}
        return true;
    }

    /**
     * 判断字符串是否是邮箱格式
     *
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        if (str.matches("[_a-z\\d\\-\\./]+@[_a-z\\d\\-]+(\\.[_a-z\\d\\-]+)*(\\.(info|biz|com|edu|gov|net|am|bz|cn|cx|hk|jp|tw|vc|vn))$")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否是手机格式
     *
     * @param str
     * @return
     */
    public static boolean isCellPhone(String str) {
        if (str.matches("^[1][34578]\\d{9}$")) {
            return true;
        }
        return false;
    }

    /**
     * 数字转字符串,如果num<=0 则输出"";
     *
     * @param num
     * @return
     */
    public static String numberToString(Object num) {
        if (num == null) {
            return null;
        } else if (num instanceof Integer && (Integer) num > 0) {
            return Integer.toString((Integer) num);
        } else if (num instanceof Long && (Long) num > 0) {
            return Long.toString((Long) num);
        } else if (num instanceof Float && (Float) num > 0) {
            return Float.toString((Float) num);
        } else if (num instanceof Double && (Double) num > 0) {
            return Double.toString((Double) num);
        } else {
            return "";
        }
    }

    /**
     * 字符串转换int，如果异常返回0
     *
     * @param s 输入的字符串
     * @return
     */
    public static int toInt(String s) {
        if (s != null && !"".equals(s.trim())) {
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * 字符串转float 如果异常返回0.00
     *
     * @param s 输入的字符串
     * @return 转换后的float
     */
    public static Float toFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return new Float(0);
        }
    }

    /**
     * 字符串转换double，如果异常返回0
     *
     * @param s 输入的字符串
     * @return
     */
    public static double toDouble(String s) {
        if (s != null && !"".equals(s.trim())) {
            try {
                return Double.parseDouble(s);
            } catch (Exception e) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * 字符串转换long，如果异常返回0L
     *
     * @param s 输入的字符串
     * @return
     */
    public static long toLong(String s) {
        try {
            if (s != null && !"".equals(s.trim()))
                return Long.parseLong(s);
        } catch (Exception exception) {
        }
        return 0L;
    }


    /**
     * 随即生成指定位数的含数字验证码字符串
     * (这里RandomStringUtils需要引用org.apache.commons包）
     *
     * @param bit 指定生成验证码位数
     * @return String
     */
    public static String numRandom(int bit) {
        if (bit == 0)
            bit = 6; // 默认6位
        String str = "";
        str = "0123456789";// 初始化种子
        return RandomStringUtils.random(bit, str);// 返回6位的字符串
    }

    /**
     * 随即生成指定位数的含验证码字符串
     * (这里RandomStringUtils需要引用org.apache.commons包）
     *
     * @param bit 指定生成验证码位数
     * @return String
     */
    public static String random(int bit) {
        if (bit == 0)
            bit = 6; // 默认6位
        // 因为o和0,l和1很难区分,所以,去掉小写的o和l
        String str = "";
        str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz";// 初始化种子
        return RandomStringUtils.random(bit, str);// 返回6位的字符串
    }

    /**
     * 转换编码
     *
     * @param s       源字符串
     * @param fencode 源编码格式
     * @param bencode 目标编码格式
     * @return 目标编码
     */
    public static String changCoding(String s, String fencode, String bencode) {
        String str;
        try {
            if (!StringUtils.isEmpty(s)) {
                str = new String(s.getBytes(fencode), bencode);
            } else {
                str = "";
            }
            return str;
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }
}
