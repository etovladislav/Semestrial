package ru.kpfu.itis.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
    public static String md5(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }
}
