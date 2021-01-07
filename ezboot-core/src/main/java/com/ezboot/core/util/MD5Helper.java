package com.ezboot.core.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author David hua
 * @date 2019-12-17 21:25:43
 */
public class MD5Helper {
    public static String md5(String input) {
        return DigestUtils.md5Hex(input);
    }


    public static void main(String[] args) {
        //e10adc3949ba59abbe56e057f20f883e
        System.out.println(md5("123456"));
        //61c097dcf6dd72c1f8d18b56c55002f3
        System.out.println(md5("EZ-Boot123456"));
    }
}
